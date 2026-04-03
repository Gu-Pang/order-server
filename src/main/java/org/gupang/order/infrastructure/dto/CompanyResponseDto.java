package org.gupang.order.infrastructure.dto;

import java.util.UUID;

public record CompanyResponseDto(
        UUID companyId,
        String companyName,
        String companyAddress,
        String companyAddressDetail
) {
}
