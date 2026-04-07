package org.gupang.order.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;


public record OrderCompanyInfo(
        @JsonProperty(value = "id")
        UUID companyId,

        @JsonProperty(value = "name")
        String companyName,

        @JsonProperty(value = "address")
        String companyAddress,

        @JsonProperty(value = "addressDetail")
        String companyAddressDetail
) {
}
