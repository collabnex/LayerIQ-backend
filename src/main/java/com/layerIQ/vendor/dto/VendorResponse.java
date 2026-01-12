package com.layerIQ.vendor.dto;

import com.layerIQ.vendor.enums.VendorStatus;
import com.layerIQ.vendor.enums.VendorType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VendorResponse {

    private Long id;
    private String uuid;
    private Long userId;
    private VendorType vendorType;
    private String displayName;
    private String legalName;
    private String website;
    private VendorStatus status;
}
