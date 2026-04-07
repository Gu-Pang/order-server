package org.gupang.order.infrastructure;

import lombok.RequiredArgsConstructor;
import org.gupang.order.domain.DeliveryService;
import org.gupang.order.infrastructure.dto.DeliveryRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryClient deliveryClient;

    @Override
    public void postDelivery(DeliveryRequest request) {
        deliveryClient.createDelivery(request);
    }
}
