package com.tsm.atelier.domain.order.mapper;

import com.tsm.atelier.domain.order.Order;
import com.tsm.atelier.domain.order.OrderItem;
import com.tsm.atelier.domain.order.dto.v1.response.OrderDetailsResponseDTO;
import com.tsm.atelier.domain.order.dto.v1.response.OrderItemResponseDTO;
import com.tsm.atelier.domain.order.dto.v1.response.OrderSummaryResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

  OrderDetailsResponseDTO toDetailsResponse(Order order);

  OrderItemResponseDTO toItemResponse(OrderItem item);

  @Mapping(target = "itemsCount", expression = "java(calculateItemsCount(order))")
  OrderSummaryResponseDTO toSummaryResponse(Order order);

  default int calculateItemsCount(Order order) {
    if (order.getItems() == null) {
      return 0;
    }
    return order.getItems().stream().mapToInt(OrderItem::getQuantity).sum();
  }
}
