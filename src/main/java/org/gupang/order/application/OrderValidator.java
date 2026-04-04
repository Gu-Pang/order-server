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
    public void validate(PostOrderRequestDto postOrderRequestDto, OrderRawData rawData) {
        validateHasData(rawData.productInfos());
        validateIsProduct(postOrderRequestDto,rawData.productInfos());
        validateBusinessRules(postOrderRequestDto,rawData);
    }

    private void validateHasData(List<OrderProductInfo> productInfos){
        if(productInfos==null||productInfos.isEmpty()){
            throw new CustomException(OrderErrorCode.SUPPLIER_NOT_FOUND);
        }
    }
    private void validateIsProduct(PostOrderRequestDto postOrderRequestDto, List<OrderProductInfo> productInfos){
        if(productInfos.size() != postOrderRequestDto.orderItems().size()){
            throw new CustomException(OrderErrorCode.PRODUCT_NOT_BELONG_TO_SUPPLIER);
        }
    }
   private void validateBusinessRules(PostOrderRequestDto postOrderRequestDto, OrderRawData rawData){
       Map<UUID, OrderProductInfo> productMap = createProductMap(rawData.productInfos());
       UUID supplierId = rawData.companyInfo().companyId();

       for(PostOrderItemRequestDto itemRequestDto : postOrderRequestDto.orderItems()){
           OrderProductInfo info = productMap.get(itemRequestDto.productId());
           checkItemDetails(itemRequestDto,info,supplierId);
       }
   }

    private Map<UUID, OrderProductInfo> createProductMap(List<OrderProductInfo> productInfos) {
        return productInfos.stream()
                .collect(Collectors.toMap(OrderProductInfo::productId, info -> info));
    }

    private void checkItemDetails(PostOrderItemRequestDto request, OrderProductInfo info, UUID supplierId) {
        if (request.quantity() < 1) {
            throw new CustomException(OrderErrorCode.INVALID_ORDER_QUANTITY);
        }
        if (!info.companyId().equals(supplierId)) {
            throw new CustomException(OrderErrorCode.MULTI_SUPPLIER_NOT_ALLOWED);
        }
        if (info.stock() < request.quantity()) {
            throw new CustomException(OrderErrorCode.OUT_OF_STOCK);
        }
    }

}