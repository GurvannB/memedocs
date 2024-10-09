package fr.epsi.services;

import fr.epsi.entities.Address;
import fr.epsi.entities.Medicine;
import fr.epsi.entities.MedicineInventoryEntry;
import fr.epsi.entities.MedicineInventoryEntryKey;
import fr.epsi.entities.Pharmacy;
import fr.epsi.exceptions.pharmacies.PharmacyNotFoundException;
import fr.epsi.repositories.MedicineInventoryEntryRepository;
import fr.epsi.repositories.MedicineRepository;
import fr.epsi.repositories.PharmacyRepository;
import fr.epsi.requests.MedicineInventoryEntryRequest;
import fr.epsi.requests.PharmacyPatchRequest;
import fr.epsi.requests.PharmacyRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PharmacyService {
    private final PharmacyRepository pharmacyRepository;
    private final MedicineRepository medicineRepository;
    private final MedicineInventoryEntryRepository medicineInventoryEntryRepository;

    public List<Pharmacy> getAll() {
        return pharmacyRepository.findAll();
    }

    public Pharmacy getById(UUID pharmacyId) {
        return pharmacyRepository.findById(pharmacyId).orElseThrow(PharmacyNotFoundException::new);
    }

    public Pharmacy create(PharmacyRequest request) {
        Pharmacy pharmacy = Pharmacy.from(request);
        return pharmacyRepository.save(pharmacy);
    }

    public Pharmacy update(UUID pharmacyId, PharmacyPatchRequest request) {
        Pharmacy pharmacy = getById(pharmacyId);
        if (request.getName() != null) pharmacy.setName(request.getName());
        if (request.getSiret() != null) pharmacy.setSiret(request.getSiret());
        if (request.getAddress() != null) pharmacy.setAddress(Address.from(request.getAddress()));
        return pharmacyRepository.save(pharmacy);
    }

    public void delete(UUID pharmacyId) {
        pharmacyRepository.deleteById(pharmacyId);
    }

    @Transactional
    public Pharmacy addToInventory(UUID pharmacyId, MedicineInventoryEntryRequest request) {
        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId).orElseThrow(PharmacyNotFoundException::new);
        Medicine medicine = null;
        if (request.getMedicineId() != null) {
            medicine = medicineRepository.findById(request.getMedicineId()).orElse(medicineRepository.save(
                    Medicine.builder().label(request.getLabel()).build()
            ));
        } else {
            medicine = medicineRepository.findByLabel(request.getLabel()).orElse(medicineRepository.save(
                    Medicine.builder().label(request.getLabel()).build()
            ));
        }

        Optional<MedicineInventoryEntry> optionalMedicineInventoryEntry = medicineInventoryEntryRepository.findByKey_PharmacyIdAndKey_MedicineId(pharmacy.getId(), medicine.getId());
        MedicineInventoryEntry medicineInventoryEntry = null;
        if (optionalMedicineInventoryEntry.isPresent()) {
            MedicineInventoryEntry entry = optionalMedicineInventoryEntry.get();
            entry.setQuantity(entry.getQuantity() + request.getQuantity());
            medicineInventoryEntry = entry;
        } else {
            medicineInventoryEntry = MedicineInventoryEntry.builder()
                    .key(MedicineInventoryEntryKey.builder().pharmacyId(pharmacy.getId()).medicineId(medicine.getId()).build())
                    .pharmacy(pharmacy)
                    .medicine(medicine)
                    .quantity(request.getQuantity())
                    .build();
        }
        medicineInventoryEntryRepository.save(medicineInventoryEntry);
        return pharmacyRepository.findById(pharmacyId).orElseThrow(PharmacyNotFoundException::new);
    }
}
