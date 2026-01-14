package com.layerIQ.vendor.model;

import com.layerIQ.vendor.enums.VendorStatus;
import com.layerIQ.vendor.enums.VendorType;
import com.layerIQ.vendor.enums.VerificationLevel;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "vendor_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    /* One Vendor → Many Roles */
    @OneToMany(
            mappedBy = "vendor",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<VendorRole> roles = new HashSet<>();

    public void addRole(VendorRoleType roleType) {
        VendorRole role = VendorRole.builder()
                .id(new VendorRoleId(null, roleType))
                .vendor(this)
                .build();
        roles.add(role);
    }

    public void removeRole(VendorRoleType roleType) {
        roles.removeIf(r -> r.getId().getRole() == roleType);
    }
}
