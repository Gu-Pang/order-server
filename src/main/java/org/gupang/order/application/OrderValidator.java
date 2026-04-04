package org.gupang.order.application;

import org.gupang.common.exception.CustomException;
import org.gupang.order.application.dto.OrderRawData;
import org.gupang.order.domain.dto.OrderCompanyInfo;
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

public class OrderValidator {
    public void validate(PostOrderRequestDto request, OrderRawData rawData){
        List<OrderProductInfo> productInfos = rawData.productInfos();
        OrderCompanyInfo companyInfo = rawData.companyInfo();

        if(productInfos == null || productInfos.isEmpty() ){
            throw new CustomException(OrderErrorCode.SUPPLIER_NOT_FOUND);
        }

        if(productInfos.size() != request.orderItems().size()){
            throw new CustomException(OrderErrorCode.PRODUCT_NOT_BELONG_TO_SUPPLIER);
        }

        Map<UUID,OrderProductInfo> productInfoMap = productInfos.stream().collect(Collectors.toMap(OrderProductInfo::productId,info->info));

        UUID targetSupplierId = companyInfo.companyId();

        for(PostOrderItemRequestDto itemRequestDto : request.orderItems()){
            OrderProductInfo info = productInfoMap.get(itemRequestDto.productId());

            if(itemRequestDto.quantity() < 1){
                throw new CustomException(OrderErrorCode.INVALID_ORDER_QUANTITY);
            }

            if(!info.companyId().equals(targetSupplierId)){
                throw new CustomException(OrderErrorCode.MULTI_SUPPLIER_NOT_ALLOWED);
            }

            if(info.stock() < itemRequestDto.quantity()){
                throw new CustomException(OrderErrorCode.OUT_OF_STOCK);
            }
        }


    }
}