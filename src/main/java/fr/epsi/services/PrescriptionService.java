package fr.epsi.services;

import fr.epsi.entities.Medicine;
import fr.epsi.entities.Pharmacy;
import fr.epsi.entities.Prescription;
import fr.epsi.entities.PrescriptionMedicine;
import fr.epsi.entities.PrescriptionMedicineKey;
import fr.epsi.exceptions.medicines.MedicineNotFoundException;
import fr.epsi.exceptions.pharmacies.PharmacyNotFoundException;
import fr.epsi.exceptions.prescriptions.PrescriptionNotFoundException;
import fr.epsi.repositories.MedicineRepository;
import fr.epsi.repositories.PharmacyRepository;
import fr.epsi.repositories.PrescriptionRepository;
import fr.epsi.requests.PrescriptionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final PharmacyRepository pharmacyRepository;
    private final MedicineRepository medicineRepository;

    public List<Prescription> getAll() {
        return prescriptionRepository.findAll();
    }

    public Prescription getById(UUID id) {
        return prescriptionRepository.findById(id).orElseThrow(PrescriptionNotFoundException::new);
    }

    public Prescription create(PrescriptionRequest request) {
        Prescription prescription = Prescription.builder()
                .pharmacy(null)
                .medicines(request.getMedicines().stream()
                        .map((medicineRequest) -> {
                            Medicine medicine = medicineRepository.findById(medicineRequest.getMedicineId()).orElseThrow(MedicineNotFoundException::new);
                            return PrescriptionMedicine.builder()
                                    .medicine(medicine)
                                    .quantity(medicineRequest.getQuantity())
                                    .build();
                        })
                        .toList())
                .patient(request.getPatient())
                .build();

        return prescriptionRepository.save(prescription);
    }

    public Prescription affect(UUID prescriptionId, UUID pharmacyId) {
        Prescription prescription = prescriptionRepository.findById(prescriptionId).orElseThrow(PrescriptionNotFoundException::new);
        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId).orElseThrow(PharmacyNotFoundException::new);
        prescription.setPharmacy(pharmacy);
        return prescriptionRepository.save(prescription);
    }
}
