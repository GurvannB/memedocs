package fr.epsi.exceptions.pharmacies;

import fr.epsi.exceptions.ControllerException;
import org.springframework.http.HttpStatus;

public class PharmacyNotFoundException extends ControllerException {
    public PharmacyNotFoundException() {super("Pharmacie non trouvée", HttpStatus.NOT_FOUND);}
}
