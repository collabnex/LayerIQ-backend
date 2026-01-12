package com.layerIQ.vendor.service;

import com.layerIQ.vendor.model.Vendor;

import java.util.List;

public interface VendorService {

    Vendor create(Vendor vendor);

    List<Vendor> getAll();

    Vendor getById(Long id);

    Vendor update(Long id, Vendor vendor);

    void delete(Long id);
}
