package org.example.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record VeiculoDTO(@NotBlank String marca, @NotBlank String modelo,
                         @NotBlank @Pattern(regexp = "(\\d{4}$)") String ano, String cor) {
}
