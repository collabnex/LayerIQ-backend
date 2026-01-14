package com.layerIQ.vendor.dto;

import com.layerIQ.vendor.enums.VendorStatus;
import com.layerIQ.vendor.enums.VendorType;
import com.layerIQ.vendor.model.VendorRoleType;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class VendorRequest {

    private Long userId;
    private VendorType vendorType;
    private String displayName;
    private String legalName;
    private String description;
    private Integer yearsOfExperience;
    private Integer teamSize;
    private String website;
    private VendorStatus status;
    private Set<VendorRoleType> roles;
}
