package com.tm.tsmatelier.core.service

import com.tm.tsmatelier.core.dtos.request.ProductRequest
import com.tm.tsmatelier.core.dtos.request.UpdateProductRequest
import com.tm.tsmatelier.core.entity.ProductEntity
import com.tm.tsmatelier.core.exception.ProductAlreadyExistsException
import com.tm.tsmatelier.core.exception.ProductNotFoundException
import com.tm.tsmatelier.core.mapper.ProductMapper
import com.tm.tsmatelier.core.repository.ProductRepository
import org.springframework.orm.ObjectOptimisticLockingFailureException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val productMapper: ProductMapper,
) {
    @Transactional
    fun createProduct(request: ProductRequest): ProductEntity {
        validateNewProduct(request)
        return productRepository.save(productMapper.toEntity(request))
    }

    fun getProducts(collectionSlug: String?): List<ProductEntity> =
        collectionSlug?.let { productRepository.findByCollectionSlugIgnoreCase(it) }
            ?: productRepository.findAll() // depois trocar para paginação

    @Transactional
    fun deleteProduct(id: UUID) {
        if (!productRepository.existsById(id)) {
            throw ProductNotFoundException("ID: $id")
        }
        productRepository.deleteById(id)
    }

    @Transactional
    fun updateProduct(
        id: UUID,
        request: UpdateProductRequest,
    ): ProductEntity {
        val product = findAndValidateProduct(id, request.version)
        productMapper.applyUpdates(product, request)

        return productRepository.save(product)
    }

    private fun validateNewProduct(request: ProductRequest) {
        val variantSkus = request.variants.map { it.sku }

        if (productRepository.existsByNameOrSkuOrVariantSku(
                request.name,
                request.sku,
                variantSkus,
            )
        ) {
            throw ProductAlreadyExistsException(
                "Já existe produto ou variante com Name '${request.name}', SKU '${request.sku}' ou um dos SKUs de variantes.",
            )
        }
    }

    private fun findAndValidateProduct(
        id: UUID,
        version: Long?,
    ): ProductEntity {
        val product =
            productRepository
                .findById(id)
                .orElseThrow { ProductNotFoundException("Product with ID $id not found.") }

        if (version != null && version != product.version) {
            throw ObjectOptimisticLockingFailureException(
                "O produto foi alterado por outra transação. Versão enviada: $version, Versão no banco: ${product.version}",
                null,
            )
        }
        return product
    }
}
