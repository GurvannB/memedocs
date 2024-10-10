package fr.epsi.dtos;

import fr.epsi.entities.Pharmacy;
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
public class PharmacyDto {
    private UUID id;
    private String name;
    private String siret;
    private AddressDto address;
    private List<MedicineInventoryEntryDto> inventory;
    private List<PrescriptionDto> prescriptions;

    public static PharmacyDto from(Pharmacy pharmacy) {
        return PharmacyDto.builder()
                .id(pharmacy.getId())
                .name(pharmacy.getName())
                .siret(pharmacy.getSiret())
                .address(AddressDto.from(pharmacy.getAddress()))
                .inventory(pharmacy.getInventory() != null ? pharmacy.getInventory().stream().map(MedicineInventoryEntryDto::from).toList() : List.of())
                .prescriptions(pharmacy.getPrescriptions() != null ? pharmacy.getPrescriptions().stream().map(PrescriptionDto::lightFrom).toList() : List.of())
                .build();
    }

    public static PharmacyDto lightFrom(Pharmacy pharmacy) {
        PharmacyDto pharmacyDto = PharmacyDto.from(pharmacy);
        pharmacyDto.setInventory(null);
        return pharmacyDto;
    }
}
