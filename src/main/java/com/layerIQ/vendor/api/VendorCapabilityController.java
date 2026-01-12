package com.layerIQ.vendor.api;

import com.layerIQ.exceptions.BadRequestException;
import com.layerIQ.exceptions.ResourceNotFoundException;
import com.layerIQ.vendor.dto.VendorCapabilityRequest;
import com.layerIQ.vendor.dto.VendorCapabilityResponse;
import com.layerIQ.vendor.service.VendorCapabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vendor-capabilities")
@RequiredArgsConstructor
public class VendorCapabilityController {

    private final VendorCapabilityService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody VendorCapabilityRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.create(request));

        } catch (BadRequestException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", e.getMessage()));

        } catch (Exception e) {
            e.printStackTrace(); //
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "message", "Failed to create capability",
                            "error", e.getMessage()
                    ));
        }
    }


    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<?> getByVendor(@PathVariable Long vendorId) {
        try {
            List<VendorCapabilityResponse> list =
                    service.getByVendor(vendorId);

            if (list.isEmpty()) {
                throw new ResourceNotFoundException("No capabilities found");
            }

            return ResponseEntity.ok(list);

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Failed to fetch capabilities"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody VendorCapabilityRequest request) {

        try {
            return ResponseEntity.ok(service.update(id, request));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "message", "Internal server error",
                            "cause", e.getClass().getSimpleName()
                    ));
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Failed to delete capability"));
        }
    }
}
