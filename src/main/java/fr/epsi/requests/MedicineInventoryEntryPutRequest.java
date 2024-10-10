package fr.epsi.requests;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class MedicineInventoryEntryPutRequest {
    @PositiveOrZero
    private int quantity;
}
