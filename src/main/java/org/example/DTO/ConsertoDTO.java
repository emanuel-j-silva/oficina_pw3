package org.example.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

public record ConsertoDTO( @Pattern(regexp = "(\\d{2}/\\d{2}/\\d{4}$)",
                                    message = "A data deve estar no formato 'dd/MM/yyyy'") String dataEntrada,
                           @Pattern(regexp = "(\\d{2}/\\d{2}/\\d{4}$)",
                                   message = "A data deve estar no formato 'dd/MM/yyyy'") String dataSaida,
                          @Valid MecanicoDTO mecanico, @Valid VeiculoDTO veiculo) {
}
