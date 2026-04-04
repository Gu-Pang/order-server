package org.gupang.order.infrastructure;

import lombok.RequiredArgsConstructor;
import org.gupang.order.domain.OrderProductService;
import org.gupang.order.domain.dto.OrderCompanyInfo;
import org.gupang.order.domain.dto.OrderProductInfo;
import org.gupang.order.infrastructure.dto.CompanyResponseDto;
import org.gupang.order.infrastructure.dto.ProductResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderProductServiceImpl implements OrderProductService {

    private final CompanyProductClient companyProductClient;

    @Override
    public List<OrderProductInfo> getOrderProductInfo(List<UUID> productIds) {
        List<ProductResponseDto> productResponseDtos = companyProductClient.getProducts(productIds);

        return productResponseDtos.stream().map(OrderProductInfo::from).toList();
    }

    @Override
    public OrderCompanyInfo getOrderCompanyInfo(UUID companyId) {
        CompanyResponseDto responseDto = companyProductClient.getCompany(companyId);

        return new OrderCompanyInfo(
                responseDto.companyId(),
                responseDto.companyName(),
                responseDto.companyAddress(),
                responseDto.companyAddressDetail()
        );
    }
}
