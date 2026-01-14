package com.layerIQ.vendor.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vendor_roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendorRole {

    @EmbeddedId
    private VendorRoleId id;

    /* FK → vendor_profiles.id */
    @MapsId("vendorId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;
}
