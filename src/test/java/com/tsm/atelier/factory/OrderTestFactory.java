package com.tsm.atelier.factory;

import com.tsm.atelier.domain.order.Order;
import com.tsm.atelier.domain.order.OrderItem;
import com.tsm.atelier.domain.order.OrderStatus;
import com.tsm.atelier.domain.order.ShippingAddress;
import com.tsm.atelier.domain.order.dto.v1.request.CheckoutItemRequestDTO;
import com.tsm.atelier.domain.order.dto.v1.request.CheckoutRequestDTO;
import com.tsm.atelier.domain.order.dto.v1.request.ShippingAddressRequestDTO;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class OrderTestFactory {

  public static Order aOrder() {
    Order order = new Order();
    order.setId(UUID.randomUUID());
    order.setUserId(UUID.randomUUID());
    order.setStatus(OrderStatus.PENDING_PAYMENT);
    order.setTotalAmount(new BigDecimal("299.90"));
    order.setShippingAddress(aShippingAddress());
    order.addItem(aOrderItem(order));
    return order;
  }

  public static OrderItem aOrderItem(Order order) {
    OrderItem item = new OrderItem();
    item.setId(1L);
    item.setOrder(order);
    item.setProductVariantId(1L);
    item.setProductName("Vestido Linho");
    item.setColorName("Areia");
    item.setSizeName("P");
    item.setUnitPrice(new BigDecimal("299.90"));
    item.setQuantity(1);
    item.setImageUrl("http://image.url");
    return item;
  }

  public static ShippingAddress aShippingAddress() {
    return new ShippingAddress(
        "Thierry S.", "01310-100", "Av Paulista", "1578", null, "Bela Vista", "São Paulo", "SP");
  }

  public static CheckoutRequestDTO aCheckoutRequest() {
    return new CheckoutRequestDTO(
        List.of(new CheckoutItemRequestDTO(1L, 1)), aShippingAddressRequest());
  }

  public static ShippingAddressRequestDTO aShippingAddressRequest() {
    return new ShippingAddressRequestDTO(
        "Thierry S.", "01310-100", "Av Paulista", "1578", null, "Bela Vista", "São Paulo", "SP");
  }
}
