package com.layerIQ.vendor.api;

import com.layerIQ.vendor.model.Vendor;
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

    // CREATE
    @PostMapping
    public ResponseEntity<Vendor> create(@RequestBody Vendor vendor) {
        Vendor created = vendorService.create(vendor);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<Vendor>> getAll() {
        return ResponseEntity.ok(vendorService.getAll());
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<Vendor> getById(@PathVariable Long id) {
        return ResponseEntity.ok(vendorService.getById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Vendor> update(
            @PathVariable Long id,
            @RequestBody Vendor vendor) {

        Vendor updated = vendorService.update(id, vendor);
        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vendorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
