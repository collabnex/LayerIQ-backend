package com.layerIQ.vendor.service;

import com.layerIQ.vendor.dto.VendorRequest;
import com.layerIQ.vendor.dto.VendorResponse;

import java.util.List;

public interface VendorService {

    // CREATE
    VendorResponse createVendor(VendorRequest request);

    // READ ALL
    List<VendorResponse> getAllVendors();

    // READ ONE
    VendorResponse getVendor(Long id);

    // UPDATE
    VendorResponse updateVendor(Long id, VendorRequest request);

    // DELETE
    void deleteVendor(Long id);
}
