package org.gupang.order.application;

import lombok.RequiredArgsConstructor;
import org.gupang.order.domain.DeliveryService;
import org.gupang.order.infrastructure.dto.DeliveryRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryInfoProvider {

    private final DeliveryService deliveryService;

    public void createDelivery(DeliveryRequest request) {
        deliveryService.postDelivery(request);
    }
}
