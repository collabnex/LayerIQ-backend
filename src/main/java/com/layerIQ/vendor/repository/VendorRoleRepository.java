package com.layerIQ.vendor.repository;

import com.layerIQ.vendor.model.VendorRole;
import com.layerIQ.vendor.model.VendorRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRoleRepository extends JpaRepository<VendorRole, VendorRoleId> {
}
