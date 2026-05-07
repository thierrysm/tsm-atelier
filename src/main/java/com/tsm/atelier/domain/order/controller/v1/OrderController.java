package com.tsm.atelier.domain.order.controller.v1;

import com.tsm.atelier.domain.auth.User;
import com.tsm.atelier.domain.order.dto.v1.request.CheckoutRequestDTO;
import com.tsm.atelier.domain.order.dto.v1.response.CheckoutResponseDTO;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @PostMapping("/checkout")
  public ResponseEntity<CheckoutResponseDTO> checkout(
      @AuthenticationPrincipal User user, @Valid @RequestBody CheckoutRequestDTO request) {
    return ResponseEntity.ok(orderService.checkout(user, request));
  }

  @GetMapping("/me")
  public ResponseEntity<Page<OrderSummaryResponseDTO>> getMyOrders(
      @AuthenticationPrincipal User user, @PageableDefault(size = 20) Pageable pageable) {
    return ResponseEntity.ok(orderService.getMyOrders(user, pageable));
  }

  @GetMapping("/me/{orderId}")
  public ResponseEntity<OrderDetailsResponseDTO> getMyOrderDetails(
      @AuthenticationPrincipal User user, @PathVariable UUID orderId) {
    return ResponseEntity.ok(orderService.getMyOrderDetails(user, orderId));
  }
}
