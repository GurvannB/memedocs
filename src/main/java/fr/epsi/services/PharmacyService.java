package fr.epsi.services;

import fr.epsi.entities.Pharmacy;
import fr.epsi.repositories.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PharmacyService {
    private final PharmacyRepository pharmacyRepository;

    public List<Pharmacy> getAll() {
        return pharmacyRepository.findAll();
    }
}
