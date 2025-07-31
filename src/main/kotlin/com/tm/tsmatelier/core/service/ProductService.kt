package com.tm.tsmatelier.core.service

import com.tm.tsmatelier.core.dtos.request.ProductRequest
import com.tm.tsmatelier.core.entity.ProductEntity
import com.tm.tsmatelier.core.exception.ProductAlreadyExistsException
import com.tm.tsmatelier.core.mapper.ProductMapper
import com.tm.tsmatelier.core.repository.ProductRepository
import com.tm.tsmatelier.core.repository.ProductVariantRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val productVariantRepository: ProductVariantRepository,
    private val productMapper: ProductMapper,
) {
    @Transactional
    fun createProduct(request: ProductRequest): ProductEntity {
        if (productRepository.existsByName(request.name)) {
            throw ProductAlreadyExistsException("name: ${request.name}")
        }
        if (productRepository.existsBySku(request.sku)) {
            throw ProductAlreadyExistsException("SKU: ${request.sku}")
        }
        request.variants.forEach { variantRequest ->
            if (productVariantRepository.existsBySku(variantRequest.sku)) {
                throw ProductAlreadyExistsException("SKU: '${variantRequest.sku}'")
            }
        }

        val newProduct = productMapper.toEntity(request)

        val savedProduct = productRepository.save(newProduct)

        return savedProduct
    }
}
