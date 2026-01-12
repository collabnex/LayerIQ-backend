package com.layerIQ.vendor.service;

import com.layerIQ.vendor.dto.VendorCapabilityRequest;
import com.layerIQ.vendor.dto.VendorCapabilityResponse;

import java.util.List;

public interface VendorCapabilityService {

    VendorCapabilityResponse create(VendorCapabilityRequest request);

    List<VendorCapabilityResponse> getByVendor(Long vendorId);

    VendorCapabilityResponse update(Long id, VendorCapabilityRequest request);

    void delete(Long id);
}
