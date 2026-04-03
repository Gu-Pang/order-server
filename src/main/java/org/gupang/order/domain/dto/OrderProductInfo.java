package org.gupang.order.domain.dto;

import org.gupang.order.infrastructure.dto.ProductResponseDto;

import java.util.UUID;

public record OrderProductInfo(
        UUID productId,
        UUID companyId,
        Integer stock,
        Long price
) {
    public static OrderProductInfo from(ProductResponseDto  productResponseDto) {
        return new OrderProductInfo(
                productResponseDto.productId(),
                productResponseDto.companyId(),
                productResponseDto.stock(),
                productResponseDto.price()
        );
    }
}
