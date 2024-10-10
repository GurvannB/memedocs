package fr.epsi.dtos;

import fr.epsi.entities.Prescription;
import fr.epsi.entities.PrescriptionMedicine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionDto {
    private UUID id;
    private PharmacyDto pharmacy;
    private List<PrescriptionMedicineDto> medicines;
    private String patient;

    public static PrescriptionDto from(Prescription prescription) {
        return PrescriptionDto.builder()
                .id(prescription.getId())
                .pharmacy(prescription.getPharmacy() != null ? PharmacyDto.lightFrom(prescription.getPharmacy()) : null)
                .medicines(prescription.getMedicines() != null ? prescription.getMedicines().stream().map(PrescriptionMedicineDto::from).toList() : List.of())
                .patient(prescription.getPatient())
                .build();
    }

    public static PrescriptionDto lightFrom(Prescription prescription) {
        return PrescriptionDto.builder()
                .id(prescription.getId())
                .medicines(prescription.getMedicines() != null ? prescription.getMedicines().stream().map(PrescriptionMedicineDto::from).toList() : List.of())
                .patient(prescription.getPatient())
                .build();
    }
}
