package org.gupang.order.application;

import lombok.RequiredArgsConstructor;
import org.gupang.common.exception.CustomException;
import org.gupang.order.domain.OrderProductService;
import org.gupang.order.domain.dto.OrderProductInfo;
import org.gupang.order.exception.OrderErrorCode;
import org.gupang.order.presentiation.dto.PostOrderItemRequestDto;
import org.gupang.order.presentiation.dto.PostOrderRequestDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderValidator {
    private final OrderProductService orderProductService;

    public UUID validateAndGetSupplierId(PostOrderRequestDto postOrderRequestDto) {
        List<UUID> productIds = postOrderRequestDto.orderItems().stream().map(PostOrderItemRequestDto::productId).toList();
        List<OrderProductInfo> productInfos = orderProductService.getOrderProductInfo(productIds);

        if (productInfos.isEmpty()) {
            throw new CustomException(OrderErrorCode.SUPPLIER_NOT_FOUND);
        }
        if (productInfos.size() != postOrderRequestDto.orderItems().size()) {
            throw new CustomException(OrderErrorCode.INVALID_PRODUCT_FOR_SUPPLIER);
        }
        UUID firstCompanyId = productInfos.get(0).companyId();

        Map<UUID,OrderProductInfo> productInfoMap = productInfos.stream().collect(Collectors.toMap(OrderProductInfo::productId,info->info));

        for (PostOrderItemRequestDto itemDto : postOrderRequestDto.orderItems()) {
            OrderProductInfo info = productInfoMap.get(itemDto.productId());

            if (!info.companyId().equals(firstCompanyId)) {
                throw new CustomException(OrderErrorCode.INVALID_PRODUCT_FOR_SUPPLIER);
            }
            if (info.stock() < itemDto.quantity()) {
                throw new CustomException(OrderErrorCode.OUT_OF_STOCK);
            }
        }
        return firstCompanyId;
    }
}