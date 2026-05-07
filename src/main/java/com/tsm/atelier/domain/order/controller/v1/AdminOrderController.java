package com.tsm.atelier.domain.order.controller.v1;

import com.tsm.atelier.domain.order.dto.v1.request.OrderStatusUpdateDTO;
import com.tsm.atelier.domain.order.dto.v1.response.OrderDetailsResponseDTO;
import com.tsm.atelier.domain.order.dto.v1.response.OrderSummaryResponseDTO;
import com.tsm.atelier.domain.order.service.OrderService;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/orders")
@RequiredArgsConstructor
public class AdminOrderController {

  private final OrderService orderService;

  @GetMapping
  public ResponseEntity<Page<OrderSummaryResponseDTO>> getAllOrders(
      @PageableDefault(size = 20) Pageable pageable) {
    return ResponseEntity.ok(orderService.getAllOrders(pageable));
  }

  @GetMapping("/{orderId}")
  public ResponseEntity<OrderDetailsResponseDTO> getOrderDetails(@PathVariable UUID orderId) {
    return ResponseEntity.ok(orderService.getOrderDetailsForAdmin(orderId));
  }

  @PatchMapping("/{orderId}/status")
  public ResponseEntity<OrderDetailsResponseDTO> updateOrderStatus(
      @PathVariable UUID orderId, @Valid @RequestBody OrderStatusUpdateDTO request) {
    return ResponseEntity.ok(orderService.updateOrderStatus(orderId, request));
  }
}
