package com.layerIQ.vendor.model;

import com.layerIQ.vendor.enums.VendorStatus;
import com.layerIQ.vendor.enums.VendorType;
import com.layerIQ.vendor.enums.VerificationLevel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "vendor_profiles")
@Getter
@Setter
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 36)
    private String uuid;

    @Column(nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VendorType vendorType;

    @Column(nullable = false)
    private String displayName;

    private String legalName;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer yearsOfExperience;
    private Integer teamSize;
    private String website;

    private BigDecimal rating = BigDecimal.ZERO;
    private Integer totalProjects = 0;

    private Boolean verified = false;

    @Enumerated(EnumType.STRING)
    private VerificationLevel verificationLevel = VerificationLevel.BASIC;

    @Enumerated(EnumType.STRING)
    private VendorStatus status = VendorStatus.DRAFT;
}
