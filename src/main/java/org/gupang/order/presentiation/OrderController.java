package org.gupang.order.presentiation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gupang.order.application.OrderService;
import org.gupang.order.application.dto.OrderResult;
import org.gupang.order.presentiation.dto.OrderResponse;
import org.gupang.order.presentiation.dto.PostOrderRequestDto;
import org.gupang.order.presentiation.dto.UpdateOrderRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<OrderResponse> getOrder(@PathVariable UUID orderId){
        OrderResult orderResult = orderService.getOrder(orderId);
        return ResponseEntity.ok(OrderResponse.from(orderResult));
    }

    @GetMapping
    public ResponseEntity<Page<OrderResponse>> getAllOrders(Pageable pageable){
        Page<OrderResult> results = orderService.getOrders(pageable);
        return ResponseEntity.ok(results.map(OrderResponse::from));
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<OrderResponse> updateOrder(
            @PathVariable UUID orderId,
           @Valid @RequestBody UpdateOrderRequestDto requestDto){

        OrderResult result = orderService.updateOrder(
                orderId,
                requestDto.address(),
                requestDto.detailAddress(),
                requestDto.message(),
                requestDto.productId(),
                requestDto.quantity()
        );
        return ResponseEntity.ok(OrderResponse.from(result));
    }

    @PatchMapping("/{orderId}/cancel")
    public ResponseEntity<Void> cancelOrder(@PathVariable UUID orderId){
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{orderId}/shipping")
    public ResponseEntity<Void> startShipping(@PathVariable UUID orderId){
        orderService.startShipping(orderId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{orderId}/complete")
    public ResponseEntity<Void> completeOrder(@PathVariable UUID orderId){
        orderService.completeOrder(orderId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID orderId){
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }
}
