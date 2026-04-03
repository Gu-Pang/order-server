package org.gupang.order.application;

import lombok.RequiredArgsConstructor;
import org.gupang.common.exception.CustomException;
import org.gupang.order.domain.OrderProductService;
import org.gupang.order.domain.dto.OrderCompanyInfo;
import org.gupang.order.domain.dto.OrderProductInfo;
import org.gupang.order.exception.OrderErrorCode;
import org.gupang.order.presentiation.dto.OrderItemRequestDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderValidator {
    private final OrderProductService orderProductService;

    public OrderCompanyInfo validateOrder(List<OrderItemRequestDto> itemDtos) {
        if (itemDtos.isEmpty()) {
            throw new CustomException(OrderErrorCode.EMPTY_ORDER);
        }

        OrderProductInfo firstProduct = orderProductService.getOrderProductInfo(itemDtos.get(0).productId());
        UUID targetSupplierId = firstProduct.companyId();

        OrderCompanyInfo companyInfo = orderProductService.getOrderCompanyInfo(targetSupplierId);
        if (companyInfo == null) {
            throw new CustomException(OrderErrorCode.SUPPLIER_NOT_FOUND);
        } // 이 업체가 있는지 없는지 유효성 검사는 없어도 될 듯함.

        for (OrderItemRequestDto itemDto : itemDtos) {
            OrderProductInfo product = orderProductService.getOrderProductInfo(itemDto.productId());

            if (!product.companyId().equals(targetSupplierId)) {
                throw new CustomException(OrderErrorCode.INVALID_PRODUCT_FOR_SUPPLIER);
            }
            if (product.stock() < itemDto.quantity()) {
                throw new CustomException(OrderErrorCode.OUT_OF_STOCK);
            }
        }

        return companyInfo;
    }
}