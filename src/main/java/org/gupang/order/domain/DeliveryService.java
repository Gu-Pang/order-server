package org.gupang.order.domain;

import org.gupang.order.infrastructure.dto.DeliveryRequest;

public interface DeliveryService {

    void postDelivery(DeliveryRequest request);
}
