package fr.epsi.dtos;

import fr.epsi.entities.Prescription;
import fr.epsi.entities.PrescriptionMedicine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionMedicineDto {
    private MedicineDto medicine;
    private int quantity;

    public static PrescriptionMedicineDto from(PrescriptionMedicine prescriptionMedicine) {
        return PrescriptionMedicineDto.builder()
                .medicine(MedicineDto.from(prescriptionMedicine.getMedicine()))
                .quantity(prescriptionMedicine.getQuantity())
                .build();
    }
}
