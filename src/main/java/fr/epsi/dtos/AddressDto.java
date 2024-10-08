package fr.epsi.dtos;

import fr.epsi.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private Long number;
    private String street;
    private String complement;
    private String city;
    private Long postalCode;

    public static AddressDto from(Address address) {
        return AddressDto.builder()
                .number(address.getNumber())
                .street(address.getStreet())
                .complement(address.getComplement())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .build();
    }
}
