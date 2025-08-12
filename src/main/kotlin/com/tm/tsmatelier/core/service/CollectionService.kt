package com.tm.tsmatelier.core.service

import com.tm.tsmatelier.core.dtos.request.CollectionRequest
import com.tm.tsmatelier.core.entity.CollectionEntity
import com.tm.tsmatelier.core.exception.CollectionAlreadyExistsException
import com.tm.tsmatelier.core.repository.CollectionRepository
import com.tm.tsmatelier.core.repository.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.text.Normalizer

@Service
class CollectionService(
    private val collectionRepository: CollectionRepository,
    private val productRepository: ProductRepository,
) {
    @Transactional
    fun createCollection(request: CollectionRequest): CollectionEntity {
        val slug = generateSlug(request.name)

        if (collectionRepository.existsBySlug(slug)) {
            throw CollectionAlreadyExistsException("Slug $slug")
        }

        val newCollection =
            CollectionEntity(
                name = request.name,
                slug = slug,
                description = request.description,
            )

        return collectionRepository.save(newCollection)
    }

    private fun generateSlug(input: String): String {
        val normalized = Normalizer.normalize(input, Normalizer.Form.NFD)
        val accentRemoved = Regex("\\p{InCombiningDiacriticalMarks}+").replace(normalized, "")
        return accentRemoved
            .lowercase()
            .replace(Regex("[^a-z0-9\\s-]"), "")
            .trim()
            .replace(Regex("\\s+"), "-")
            .replace(Regex("-+"), "-")
    }
}
