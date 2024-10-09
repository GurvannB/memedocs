package fr.epsi.dtos;

import fr.epsi.entities.MedicineInventoryEntry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicineInventoryEntryDto {
    private MedicineDto medicine;
    private int quantity;

    public static MedicineInventoryEntryDto from(MedicineInventoryEntry medicineInventoryEntry) {
        return MedicineInventoryEntryDto.builder()
                .medicine(MedicineDto.from(medicineInventoryEntry.getMedicine()))
                .quantity(medicineInventoryEntry.getQuantity())
                .build();
    }
}
