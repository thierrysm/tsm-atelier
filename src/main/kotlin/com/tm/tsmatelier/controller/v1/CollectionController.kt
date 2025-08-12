package com.tm.tsmatelier.controller.v1

import com.tm.tsmatelier.core.dtos.reponse.CollectionResponse
import com.tm.tsmatelier.core.dtos.request.CollectionRequest
import com.tm.tsmatelier.core.mapper.CollectionMapper
import com.tm.tsmatelier.core.service.CollectionService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/api/v1/collections")
class CollectionController(
    private val collectionService: CollectionService,
    private val collectionMapper: CollectionMapper,
) {
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    fun createCollection(
        @Valid @RequestBody request: CollectionRequest,
    ): ResponseEntity<CollectionResponse> {
        val newCollection = collectionService.createCollection(request)
        val location =
            ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{slug}")
                .buildAndExpand(newCollection.slug)
                .toUri()
        return ResponseEntity.created(location).body(collectionMapper.toResponseDto(newCollection))
    }
}
