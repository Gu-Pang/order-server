package org.gupang.order.domain.dto;

import java.util.UUID;

public record OrderCompanyInfo(
        UUID companyId,
        String companyName,
        String company_address,
        String company_address_detail
) {
}
