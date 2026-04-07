package org.gupang.order.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CompanyResponseDto(
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
