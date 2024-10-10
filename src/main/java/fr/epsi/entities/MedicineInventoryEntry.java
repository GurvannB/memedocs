package fr.epsi.entities;

import fr.epsi.requests.MedicineInventoryEntryRequest;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicineInventoryEntry {
    @EmbeddedId
    private MedicineInventoryEntryKey key;

    @MapsId("pharmacyId")
    @ManyToOne
    private Pharmacy pharmacy;

    @MapsId("medicineId")
    @ManyToOne
    private Medicine medicine;

    @Column(nullable = false)
    private int quantity;
}
