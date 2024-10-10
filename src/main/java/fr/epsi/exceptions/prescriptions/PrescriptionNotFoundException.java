package fr.epsi.exceptions.prescriptions;

import fr.epsi.exceptions.ControllerException;
import org.springframework.http.HttpStatus;

public class PrescriptionNotFoundException extends ControllerException {
    public PrescriptionNotFoundException() {super("Ordonnance non trouvée", HttpStatus.NOT_FOUND);}
}
