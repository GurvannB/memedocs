package fr.epsi.controllers;

import fr.epsi.dtos.PharmacyDto;
import fr.epsi.entities.Pharmacy;
import fr.epsi.requests.MedicineInventoryEntryPutRequest;
import fr.epsi.requests.MedicineInventoryEntryRequest;
import fr.epsi.requests.PharmacyPatchRequest;
import fr.epsi.requests.PharmacyRequest;
import fr.epsi.services.PharmacyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pharmacies")
public class PharmacyController {
    private final PharmacyService pharmacyService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PharmacyDto> getAllPharmacies() {
        return pharmacyService.getAll().stream().map(PharmacyDto::from).toList();
    }

    @GetMapping("/{pharmacyId}")
    @ResponseStatus(HttpStatus.OK)
    public PharmacyDto getPharmacyById(@PathVariable UUID pharmacyId) {
        Pharmacy pharmacy = pharmacyService.getById(pharmacyId);
        return PharmacyDto.from(pharmacy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PharmacyDto createPharmacy(@Valid @RequestBody PharmacyRequest request) {
        Pharmacy pharmacy = pharmacyService.create(request);
        return PharmacyDto.from(pharmacy);
    }

    @PatchMapping("/{pharmacyId}")
    @ResponseStatus(HttpStatus.OK)
    public PharmacyDto updatePharmacy(@PathVariable UUID pharmacyId, @Valid @RequestBody PharmacyPatchRequest request) {
        Pharmacy pharmacy = pharmacyService.update(pharmacyId, request);
        return PharmacyDto.from(pharmacy);
    }

    @DeleteMapping("/{pharmacyId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePharmacy(@PathVariable UUID pharmacyId) {
        pharmacyService.delete(pharmacyId);
    }

    @PostMapping("/{pharmacyId}/inventory")
    @ResponseStatus(HttpStatus.CREATED)
    public PharmacyDto addToInventory(@PathVariable UUID pharmacyId, @Valid @RequestBody MedicineInventoryEntryRequest request) {
        Pharmacy pharmacy = pharmacyService.addToInventory(pharmacyId, request);
        return PharmacyDto.from(pharmacy);
    }

    @PutMapping("/{pharmacyId}/inventory/{medicineId}")
    @ResponseStatus(HttpStatus.OK)
    public PharmacyDto updateInventoryQuantity(@PathVariable UUID pharmacyId, @PathVariable UUID medicineId, @Valid @RequestBody MedicineInventoryEntryPutRequest request) {
        Pharmacy pharmacy = pharmacyService.updateInventoryQuantity(pharmacyId, medicineId, request);
        return PharmacyDto.from(pharmacy);
    }

    @DeleteMapping("/{pharmacyId}/inventory/{medicineId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteInventoryEntry(@PathVariable UUID pharmacyId, @PathVariable UUID medicineId) {
        pharmacyService.deleteInventoryEntry(pharmacyId, medicineId);
    }
}
