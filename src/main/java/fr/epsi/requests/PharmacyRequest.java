package fr.epsi.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class PharmacyRequest {
    @NotNull
    @NotEmpty
    private String name;

    @Length(min = 14, max = 14)
    private String siret;

    @Valid
    @NotNull
    private AddressRequest address;
}
