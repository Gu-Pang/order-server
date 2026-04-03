package org.gupang.order.domain.dto;

import java.util.UUID;

public record OrderProductInfo(
        UUID productId,
        UUID companyId,
        Integer stock,
        Long price
) {
}
