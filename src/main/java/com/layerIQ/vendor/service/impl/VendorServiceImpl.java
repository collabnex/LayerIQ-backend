package com.layerIQ.vendor.service.impl;

import com.layerIQ.vendor.dto.VendorRequest;
import com.layerIQ.vendor.dto.VendorResponse;
import com.layerIQ.vendor.model.Vendor;
import com.layerIQ.vendor.model.VendorRole;
import com.layerIQ.vendor.model.VendorRoleId;
import com.layerIQ.vendor.model.VendorRoleType;
import com.layerIQ.vendor.repository.VendorRepository;
import com.layerIQ.vendor.service.VendorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    // ================= CREATE =================
    @Override
    public VendorResponse createVendor(VendorRequest request) {

        Vendor vendor = Vendor.builder().build();

        // Add roles
        for (VendorRoleType roleType : request.getRoles()) {
            VendorRole role = VendorRole.builder()
                    .id(new VendorRoleId(null, roleType))
                    .vendor(vendor)
                    .build();

            vendor.getRoles().add(role);
        }

        Vendor saved = vendorRepository.save(vendor);
        return mapToResponse(saved);
    }

    // ================= READ ALL =================
    @Override
    @Transactional(readOnly = true)
    public List<VendorResponse> getAllVendors() {
        return vendorRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ================= READ ONE =================
    @Override
    @Transactional(readOnly = true)
    public VendorResponse getVendor(Long id) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Vendor not found with id: " + id)
                );

        return mapToResponse(vendor);
    }

    // ================= UPDATE =================
    @Override
    public VendorResponse updateVendor(Long id, VendorRequest request) {

        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Vendor not found with id: " + id)
                );

        // Clear old roles
        vendor.getRoles().clear();

        // Add new roles
        for (VendorRoleType roleType : request.getRoles()) {
            VendorRole role = VendorRole.builder()
                    .id(new VendorRoleId(id, roleType))
                    .vendor(vendor)
                    .build();

            vendor.getRoles().add(role);
        }

        Vendor updated = vendorRepository.save(vendor);
        return mapToResponse(updated);
    }

    // ================= DELETE =================
    @Override
    public void deleteVendor(Long id) {

        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Vendor not found with id: " + id)
                );

        vendorRepository.delete(vendor);
        // roles auto-deleted due to cascade + orphanRemoval
    }

    // ================= MAPPER =================
    private VendorResponse mapToResponse(Vendor vendor) {

        Set<VendorRoleType> roles = vendor.getRoles()
                .stream()
                .map(r -> r.getId().getRole())
                .collect(Collectors.toSet());

        return VendorResponse.builder()
                .id(vendor.getId())
                .roles(roles)
                .build();
    }
}
