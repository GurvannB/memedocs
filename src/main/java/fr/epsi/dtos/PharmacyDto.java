package fr.epsi.dtos;

import fr.epsi.entities.Pharmacy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PharmacyDto {
    private UUID id;
    private String name;
    private String siret;
    private AddressDto address;

    public static PharmacyDto from(Pharmacy pharmacy) {
        return PharmacyDto.builder()
                .id(pharmacy.getId())
                .name(pharmacy.getName())
                .siret(pharmacy.getSiret())
                .address(AddressDto.from(pharmacy.getAddress()))
                .build();
    }
}
