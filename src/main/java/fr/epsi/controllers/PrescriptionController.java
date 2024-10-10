package fr.epsi.controllers;

import fr.epsi.dtos.PrescriptionDto;
import fr.epsi.entities.Prescription;
import fr.epsi.requests.PrescriptionRequest;
import fr.epsi.services.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/prescriptions")
public class PrescriptionController {
    private final PrescriptionService prescriptionService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PrescriptionDto> getAll() {
        List<Prescription> prescriptions = prescriptionService.getAll();
        return prescriptions.stream().map(PrescriptionDto::from).toList();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{prescriptionId}")
    public PrescriptionDto getPrescriptionById(@PathVariable UUID prescriptionId) {
        Prescription prescription = prescriptionService.getById(prescriptionId);
        return PrescriptionDto.from(prescription);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PrescriptionDto createPrescription(@Validated @RequestBody PrescriptionRequest request) {
        Prescription prescription = prescriptionService.create(request);
        return PrescriptionDto.from(prescription);
    }
}
