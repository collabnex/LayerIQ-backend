package com.layerIQ.vendor.dto;

import com.layerIQ.vendor.enums.VendorStatus;
import com.layerIQ.vendor.enums.VendorType;
import com.layerIQ.vendor.model.VendorRoleType;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

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
    private Set<VendorRoleType> roles;
}
