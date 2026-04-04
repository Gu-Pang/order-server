package org.gupang.order.application;

import lombok.RequiredArgsConstructor;
import org.gupang.order.application.dto.OrderRawData;
import org.gupang.order.domain.OrderProductService;
import org.gupang.order.domain.dto.OrderCompanyInfo;
import org.gupang.order.domain.dto.OrderProductInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderInfoProvider {
    private final OrderProductService orderProductService;

    public OrderRawData getOrderRawData(List<UUID> productIds){
        List<OrderProductInfo> productInfos = orderProductService.getOrderProductInfo(productIds);
        UUID supplierId = productInfos.get(0).companyId();
        OrderCompanyInfo companyInfo = orderProductService.getOrderCompanyInfo(supplierId);
        return new OrderRawData(productInfos, companyInfo);
    }
}
