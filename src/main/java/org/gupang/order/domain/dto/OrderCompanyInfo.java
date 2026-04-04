package org.gupang.order.domain.dto;

import java.util.UUID;

public record OrderCompanyInfo(
        UUID companyId,
        String companyName,
        String companyAddress,
        String companyAddressDetail
) {
}
