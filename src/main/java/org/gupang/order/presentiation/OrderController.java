package org.gupang.order.presentiation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gupang.order.application.OrderService;
import org.gupang.order.application.dto.OrderResult;
import org.gupang.order.presentiation.dto.PostOrderRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<UUID> createOrder(@Valid @RequestBody PostOrderRequestDto postOrderRequestDto){
        UUID orderId = orderService.createOrder(postOrderRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResult> getOrder(@PathVariable UUID orderId){
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

    @PatchMapping("/{orderId}/cancel")
    public ResponseEntity<Void> cancelOrder(@PathVariable UUID orderId){
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok().build();
    }
}
