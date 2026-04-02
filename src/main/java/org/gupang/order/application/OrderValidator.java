package org.gupang.order.application;

import lombok.RequiredArgsConstructor;
import org.gupang.common.exception.CustomException;
import org.gupang.order.exception.OrderErrorCode;
import org.gupang.order.infrastructure.CompanyProductClient;
import org.gupang.order.infrastructure.dto.ProductResponseDto;
import org.gupang.order.presentiation.dto.OrderItemRequestDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderValidator {
    private final CompanyProductClient companyProductClient;

    public void validateOrder(UUID supplierId, List<OrderItemRequestDto> itemDtos){
        if(companyProductClient.getCompany(supplierId) == null){
            throw new CustomException(OrderErrorCode.SUPPLIER_NOT_FOUND);
        }
        for(OrderItemRequestDto itemDto : itemDtos){
            ProductResponseDto product = companyProductClient.getProduct(itemDto.productId());
            if(!product.companyId().equals(supplierId)){
                throw new CustomException(OrderErrorCode.INVALID_PRODUCT_FOR_SUPPLIER);
            }
            if(product.stock() < itemDto.quantity()){
                throw new CustomException(OrderErrorCode.OUT_OF_STOCK);
            }
        }
    }
}
