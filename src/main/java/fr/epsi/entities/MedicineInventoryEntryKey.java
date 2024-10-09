package fr.epsi.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class MedicineInventoryEntryKey {
    private UUID pharmacyId;
    private UUID medicineId;
}
