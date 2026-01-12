package com.layerIQ.vendor.dto;

import com.layerIQ.vendor.enums.CapabilityType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VendorCapabilityResponse {

    private Long id;
    private Long vendorId;
    private CapabilityType capability;
    private Integer minLayer;
    private Integer maxLayer;
    private String notes;
}
