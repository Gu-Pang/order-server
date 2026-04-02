package org.gupang.order.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class DeliveryInfo {

    @Column(nullable = false)
    private String address;

    private String detailAddress;

    public  DeliveryInfo(String address, String detailAddress) {
        this.address = address;
        this.detailAddress = detailAddress;
    }
}
