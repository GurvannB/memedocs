package fr.epsi.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
public class MedicineInventoryEntryRequest {
    private UUID medicineId;

    @NotNull
    @NotEmpty
    private String label;

    @PositiveOrZero
    private int quantity;
}
