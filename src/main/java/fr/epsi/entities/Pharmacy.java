package fr.epsi.entities;

import fr.epsi.requests.PharmacyRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String siret;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pharmacy", fetch = FetchType.LAZY)
    private List<MedicineInventoryEntry> inventory;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pharmacy", fetch = FetchType.LAZY)
    private List<Prescription> prescriptions;

    public static Pharmacy from(PharmacyRequest request) {
        return Pharmacy.builder()
                .name(request.getName())
                .siret(request.getSiret())
                .address(Address.from(request.getAddress()))
                .build();
    }
}
