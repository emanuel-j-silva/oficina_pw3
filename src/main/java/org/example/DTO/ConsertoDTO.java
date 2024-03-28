package org.example.DTO;

import org.example.Model.Mecanico;
import org.example.Model.Veiculo;

public record ConsertoDTO(String dataEntrada, String dataSaida,
                          MecanicoDTO mecanico, VeiculoDTO veiculo) {
}
