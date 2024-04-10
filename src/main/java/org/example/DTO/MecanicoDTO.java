package org.example.DTO;

import jakarta.validation.constraints.NotBlank;

public record MecanicoDTO(@NotBlank String nome, Integer anosExp) {
}
