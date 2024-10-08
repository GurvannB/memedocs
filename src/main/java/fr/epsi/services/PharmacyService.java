package fr.epsi.services;

import fr.epsi.dtos.PharmacyDto;
import fr.epsi.entities.Address;
import fr.epsi.entities.Pharmacy;
import fr.epsi.exceptions.pharmacies.PharmacyNotFoundException;
import fr.epsi.repositories.PharmacyRepository;
import fr.epsi.requests.PharmacyPatchRequest;
import fr.epsi.requests.PharmacyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PharmacyService {
    private final PharmacyRepository pharmacyRepository;

    public List<Pharmacy> getAll() {
        return pharmacyRepository.findAll();
    }

    public Pharmacy getById(UUID pharmacyId) {
        return pharmacyRepository.findById(pharmacyId).orElseThrow(PharmacyNotFoundException::new);
    }

    public Pharmacy create(PharmacyRequest request) {
        Pharmacy pharmacy = Pharmacy.from(request);
        return pharmacyRepository.save(pharmacy);
    }

    public Pharmacy update(UUID pharmacyId, PharmacyPatchRequest request) {
        Pharmacy pharmacy = getById(pharmacyId);
        if (request.getName() != null) pharmacy.setName(request.getName());
        if (request.getSiret() != null) pharmacy.setSiret(request.getSiret());
        if (request.getAddress() != null) pharmacy.setAddress(Address.from(request.getAddress()));
        return pharmacyRepository.save(pharmacy);
    }

    public void delete(UUID pharmacyId) {
        pharmacyRepository.deleteById(pharmacyId);
    }
}
