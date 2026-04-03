package org.gupang.order.domain;

import org.gupang.order.domain.dto.OrderCompanyInfo;
import org.gupang.order.domain.dto.OrderProductInfo;

import java.util.UUID;

public interface OrderProductService {
    OrderCompanyInfo getOrderCompanyInfo(UUID companyId);

    OrderProductInfo getOrderProductInfo(UUID productId);
}
