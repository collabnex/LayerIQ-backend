package com.layerIQ.vendor.model;

import com.layerIQ.vendor.enums.CapabilityType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "vendor_capabilities")
@Getter
@Setter
public class VendorCapability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vendor_id", nullable = false)
    private Long vendorId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CapabilityType capability;

    @Column(name = "min_layer")
    private Integer minLayer;

    @Column(name = "max_layer")
    private Integer maxLayer;

    @Column(name = "notes")
    private String notes;
}
