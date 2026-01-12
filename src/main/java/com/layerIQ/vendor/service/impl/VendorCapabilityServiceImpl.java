package com.layerIQ.vendor.service.impl;

import com.layerIQ.exceptions.BadRequestException;
import com.layerIQ.exceptions.ResourceNotFoundException;
import com.layerIQ.vendor.dto.VendorCapabilityRequest;
import com.layerIQ.vendor.dto.VendorCapabilityResponse;
import com.layerIQ.vendor.model.VendorCapability;
import com.layerIQ.vendor.repository.VendorCapabilityRepository;
import com.layerIQ.vendor.service.VendorCapabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VendorCapabilityServiceImpl implements VendorCapabilityService {

    private final VendorCapabilityRepository repository;

    @Override
    public VendorCapabilityResponse create(VendorCapabilityRequest request) {

        if (request.getVendorId() == null) {
            throw new BadRequestException("Vendor id is required");
        }

        VendorCapability vc = new VendorCapability();
        vc.setVendorId(request.getVendorId());
        vc.setCapability(request.getCapability());
        vc.setMinLayer(request.getMinLayer());
        vc.setMaxLayer(request.getMaxLayer());
        vc.setNotes(request.getNotes());

        return toResponse(repository.save(vc));
    }

    @Override
    public List<VendorCapabilityResponse> getByVendor(Long vendorId) {
        return repository.findByVendorId(vendorId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public VendorCapabilityResponse update(Long id, VendorCapabilityRequest request) {

        VendorCapability vc = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Capability not found"));

        if (request.getCapability() == null) {
            throw new BadRequestException("Capability is required");
        }

        vc.setCapability(request.getCapability());
        vc.setMinLayer(request.getMinLayer());
        vc.setMaxLayer(request.getMaxLayer());
        vc.setNotes(request.getNotes());

        return toResponse(repository.save(vc));
    }


    @Override
    public void delete(Long id) {
        VendorCapability vc = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Capability not found"));
        repository.delete(vc);
    }

    private VendorCapabilityResponse toResponse(VendorCapability vc) {
        return VendorCapabilityResponse.builder()
                .id(vc.getId())
                .vendorId(vc.getVendorId())
                .capability(vc.getCapability())
                .minLayer(vc.getMinLayer())
                .maxLayer(vc.getMaxLayer())
                .notes(vc.getNotes())
                .build();
    }
}
