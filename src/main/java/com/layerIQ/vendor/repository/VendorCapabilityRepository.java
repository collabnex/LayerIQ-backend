package com.layerIQ.vendor.repository;

import com.layerIQ.vendor.model.VendorCapability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendorCapabilityRepository
        extends JpaRepository<VendorCapability, Long> {

    List<VendorCapability> findByVendorId(Long vendorId);
}
