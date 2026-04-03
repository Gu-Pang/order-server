package org.gupang.order.infrastructure;

import lombok.RequiredArgsConstructor;
import org.gupang.order.domain.OrderProductService;
import org.gupang.order.domain.dto.OrderCompanyInfo;
import org.gupang.order.domain.dto.OrderProductInfo;
import org.gupang.order.infrastructure.dto.CompanyResponseDto;
import org.gupang.order.infrastructure.dto.ProductResponseDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderProductServiceImpl implements OrderProductService {
    private final CompanyProductClient companyProductClient;
    @Override
    public OrderCompanyInfo getOrderCompanyInfo(UUID companyId) {
        CompanyResponseDto companyResponseDto = companyProductClient.getCompany(companyId);
        return new OrderCompanyInfo(
                companyResponseDto.companyId(),
                companyResponseDto.companyName(),
                companyResponseDto.company_address(),
                companyResponseDto.company_address_detail()
        );
    }

    @Override
    public OrderProductInfo getOrderProductInfo(UUID productId) {
        ProductResponseDto productResponseDto = companyProductClient.getProduct(productId);

        return new OrderProductInfo(
                productResponseDto.productId(),
                productResponseDto.companyId(),
                productResponseDto.stock(),
                productResponseDto.price()
        );
    }
}
