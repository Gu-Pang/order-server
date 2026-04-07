package org.gupang.order.infrastructure;

import org.gupang.order.infrastructure.dto.DeliveryRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "delivery-server")
public interface DeliveryClient {

    @PostMapping("/api/v1/deliveries")
    Void createDelivery(@RequestBody DeliveryRequest request);
}
