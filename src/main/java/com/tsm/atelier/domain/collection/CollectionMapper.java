package com.tsm.atelier.domain.collection;

import com.tsm.atelier.domain.collection.dto.v1.request.CollectionRequestDTO;
import com.tsm.atelier.domain.collection.dto.v1.response.CollectionResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CollectionMapper {

  CollectionResponseDTO toResponse(Collection collection);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "products", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  Collection toEntity(CollectionRequestDTO dto);
}
