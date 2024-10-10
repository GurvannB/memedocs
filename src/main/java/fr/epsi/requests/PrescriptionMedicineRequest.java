package fr.epsi.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.UUID;

@Data
public class PrescriptionMedicineRequest {
    @NotNull
    private UUID medicineId;

    @Positive
    private int quantity;
}
