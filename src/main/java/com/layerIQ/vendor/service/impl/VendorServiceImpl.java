package com.layerIQ.vendor.service.impl;

import com.layerIQ.exceptions.VendorNotFoundException;
import com.layerIQ.vendor.model.Vendor;
import com.layerIQ.vendor.repository.VendorRepository;
import com.layerIQ.vendor.service.VendorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    @Override
    public Vendor create(Vendor vendor) {
        if (vendor.getDisplayName() == null || vendor.getDisplayName().isBlank()) {
            throw new IllegalArgumentException("Display name is required");
        }

        vendor.setUuid(UUID.randomUUID().toString());
        return vendorRepository.save(vendor);
    }

    @Override
    public List<Vendor> getAll() {
        return vendorRepository.findAll();
    }

    @Override
    public Vendor getById(Long id) {
        return vendorRepository.findById(id)
                .orElseThrow(() -> new VendorNotFoundException(id));
    }

    @Override
    public Vendor update(Long id, Vendor data) {
        Vendor vendor = getById(id);

        vendor.setDisplayName(data.getDisplayName());
        vendor.setLegalName(data.getLegalName());
        vendor.setDescription(data.getDescription());
        vendor.setWebsite(data.getWebsite());
        vendor.setStatus(data.getStatus());

        return vendorRepository.save(vendor);
    }

    @Override
    public void delete(Long id) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new VendorNotFoundException(id));

        vendorRepository.delete(vendor);
    }
}
