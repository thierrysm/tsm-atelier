package com.tm.tsmatelier.controller.v1

import com.tm.tsmatelier.core.dtos.reponse.ProductResponse
import com.tm.tsmatelier.core.dtos.request.ProductRequest
import com.tm.tsmatelier.core.dtos.request.UpdateProductRequest
import com.tm.tsmatelier.core.mapper.ProductMapper
import com.tm.tsmatelier.core.service.ProductService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import java.util.UUID

@RestController
@RequestMapping("/api/v1/products")
class ProductController(
    private val productService: ProductService,
    private val productMapper: ProductMapper,
) {
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    fun createProduct(
        @Valid @RequestBody request: ProductRequest,
    ): ResponseEntity<ProductResponse> {
        val createdProduct = productService.createProduct(request)
        val productResponse = productMapper.toResponseDto(createdProduct)

        val location: URI =
            ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdProduct.id)
                .toUri()

        return ResponseEntity.created(location).body(productResponse)
    }

    @GetMapping
    fun getProducts(
        @RequestParam(name = "collection", required = false) collectionSlug: String?,
    ): ResponseEntity<List<ProductResponse>> {
        val response = productService.getProducts(collectionSlug).map { productMapper.toResponseDto(it) }

        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    fun deleteProduct(
        @PathVariable id: UUID,
    ): ResponseEntity<Unit> {
        productService.deleteProduct(id)

        return ResponseEntity.noContent().build()
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    fun updateProduct(
        @PathVariable id: UUID,
        @Valid @RequestBody request: UpdateProductRequest,
    ): ResponseEntity<ProductResponse> {
        val updatedProductEntity = productService.updateProduct(id, request)

        val productResponse = productMapper.toResponseDto(updatedProductEntity)

        return ResponseEntity.ok(productResponse)
    }
}
