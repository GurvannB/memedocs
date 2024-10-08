package fr.epsi.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PharmacyPatchRequest {
    private String name;

    private String siret;

    @Valid
    private AddressRequest address;
}
