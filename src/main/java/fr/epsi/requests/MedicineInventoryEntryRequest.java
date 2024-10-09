package fr.epsi.requests;

import lombok.Data;

import java.util.UUID;

@Data
public class MedicineInventoryEntryRequest {
    private UUID medicineId;
    private String label;
    private int quantity;
}
