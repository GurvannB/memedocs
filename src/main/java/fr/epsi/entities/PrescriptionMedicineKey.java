package fr.epsi.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionMedicineKey {
    private UUID prescriptionId;
    private UUID medicineId;
}
