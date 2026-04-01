package org.gupang.order.presentiation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gupang.order.application.OrderService;
import org.gupang.order.presentiation.dto.PostOrderRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/test")
    public String test(){
        return "test성공!!!!!";
    }


    @PostMapping
    public void createOrder(@Valid @RequestBody PostOrderRequestDto postOrderRequestDto){
        orderService.createOrder(postOrderRequestDto);
    }
}
