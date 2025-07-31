package com.tm.tsmatelier.controller.v1

import com.tm.tsmatelier.core.dtos.reponse.ProductResponse
import com.tm.tsmatelier.core.dtos.request.ProductRequest
import com.tm.tsmatelier.core.mapper.ProductMapper
import com.tm.tsmatelier.core.service.ProductService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

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
}
