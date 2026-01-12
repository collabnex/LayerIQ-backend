package com.layerIQ.vendor.dto;

import com.layerIQ.vendor.enums.CapabilityType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorCapabilityRequest {

    private Long vendorId;
    private CapabilityType capability;
    private Integer minLayer;
    private Integer maxLayer;
    private String notes;
}
