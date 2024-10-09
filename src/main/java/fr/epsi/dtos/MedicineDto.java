package fr.epsi.dtos;

import fr.epsi.entities.Medicine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicineDto {
    private UUID id;
    private String label;

    public static MedicineDto from (Medicine medicine) {
        return MedicineDto.builder()
                .id(medicine.getId())
                .label(medicine.getLabel())
                .build();
    }
}
