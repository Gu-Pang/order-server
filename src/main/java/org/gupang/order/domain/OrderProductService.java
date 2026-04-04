package org.gupang.order.domain;

import org.gupang.order.domain.dto.OrderCompanyInfo;
import org.gupang.order.domain.dto.OrderProductInfo;

import java.util.List;
import java.util.UUID;

public interface OrderProductService {

    List<OrderProductInfo> getOrderProductInfo(List<UUID> productIds);

    OrderCompanyInfo getOrderCompanyInfo(UUID companyId);
}
