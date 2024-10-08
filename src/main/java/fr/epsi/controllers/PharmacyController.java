package fr.epsi.controllers;

import fr.epsi.dtos.PharmacyDto;
import fr.epsi.services.PharmacyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pharmacies")
public class PharmacyController {
    private final PharmacyService pharmacyService;

    @GetMapping
    public List<PharmacyDto> getAllPharmacies() {
        return pharmacyService.getAll().stream().map(PharmacyDto::from).toList();
    }
}
