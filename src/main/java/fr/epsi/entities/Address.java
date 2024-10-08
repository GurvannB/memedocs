package fr.epsi.entities;

import fr.epsi.requests.AddressRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Long number;

    @Column(nullable = false)
    private String street;

    private String complement;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private Long postalCode;

    public static Address from(AddressRequest address) {
        return Address.builder()
                .number(address.getNumber())
                .street(address.getStreet())
                .complement(address.getComplement())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .build();
    }
}
