package org.gupang.order.infrastructure;

import org.gupang.order.infrastructure.dto.CompanyResponseDto;
import org.gupang.order.infrastructure.dto.ProductResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "company-server")
public interface CompanyProductClient {

    @GetMapping("/api/v1/products/list/{product_id}")
    List<ProductResponseDto> getProducts(@PathVariable("product_id") List<UUID> productIds);

    @GetMapping("/api/v1/companies/{company_id}")
    CompanyResponseDto getCompany(@PathVariable("company_id") UUID companyId);
}

