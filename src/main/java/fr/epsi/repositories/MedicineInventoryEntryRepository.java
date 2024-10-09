package fr.epsi.repositories;

import fr.epsi.entities.MedicineInventoryEntry;
import fr.epsi.entities.MedicineInventoryEntryKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MedicineInventoryEntryRepository extends JpaRepository<MedicineInventoryEntry, MedicineInventoryEntryKey> {
    Optional<MedicineInventoryEntry> findByKey_PharmacyIdAndKey_MedicineId(UUID pharmacyId, UUID medicineId);
}
