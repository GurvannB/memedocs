package fr.epsi.repositories;

import fr.epsi.entities.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PharmacyRepository extends JpaRepository<Pharmacy, UUID> {
}
