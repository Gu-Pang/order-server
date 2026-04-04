package org.gupang.order.application.dto;

import org.gupang.order.domain.dto.OrderCompanyInfo;
import org.gupang.order.domain.dto.OrderProductInfo;

import java.util.List;

public record OrderRawData(
        List<OrderProductInfo> productInfos,
        OrderCompanyInfo companyInfo
) {
}
