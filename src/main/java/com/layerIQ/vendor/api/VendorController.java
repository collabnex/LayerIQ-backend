package com.layerIQ.vendor.api;

import com.layerIQ.vendor.dto.VendorRequest;
import com.layerIQ.vendor.dto.VendorResponse;
import com.layerIQ.vendor.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
@RequiredArgsConstructor
public class VendorController {

    private final VendorService vendorService;

    // ✅ CREATE
    @PostMapping
    public ResponseEntity<VendorResponse> create(
            @RequestBody VendorRequest request) {

        VendorResponse response = vendorService.createVendor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ✅ READ ALL
    @GetMapping
    public ResponseEntity<List<VendorResponse>> getAll() {
        return ResponseEntity.ok(vendorService.getAllVendors());
    }

    // ✅ READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<VendorResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(vendorService.getVendor(id));
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<VendorResponse> update(
            @PathVariable Long id,
            @RequestBody VendorRequest request) {

        return ResponseEntity.ok(
                vendorService.updateVendor(id, request)
        );
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vendorService.deleteVendor(id);
        return ResponseEntity.noContent().build();
    }
}