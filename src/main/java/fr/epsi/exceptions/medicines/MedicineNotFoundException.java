package fr.epsi.exceptions.medicines;

import fr.epsi.exceptions.ControllerException;
import org.springframework.http.HttpStatus;

public class MedicineNotFoundException extends ControllerException {
    public MedicineNotFoundException() {super("Médicament non trouvé", HttpStatus.NOT_FOUND);}
}
