package fr.epsi.exceptions.pharmacies;

import fr.epsi.exceptions.ControllerException;
import org.springframework.http.HttpStatus;

public class MedicineInventoryEntryNotFoundException extends ControllerException {
    public MedicineInventoryEntryNotFoundException() {super("Entrée dans l'inventaire non trouvée", HttpStatus.NOT_FOUND);}
}
