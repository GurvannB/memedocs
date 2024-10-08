package fr.epsi.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {
    @NotNull
    @Positive
    private Long number;

    @NotEmpty
    private String street;

    private String complement;

    @NotEmpty
    private String city;

    @NotNull
    @Min(10000)
    @Max(99999)
    private Long postalCode;
}
