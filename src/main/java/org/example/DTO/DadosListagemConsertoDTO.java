package org.example.DTO;

import org.example.Model.Conserto;

public record DadosListagemConsertoDTO(Long id, String dataEntrada, String dataSaida, String nome,
                                        String marca, String modelo) {
    public DadosListagemConsertoDTO(Conserto conserto){
        this(conserto.getId(), conserto.getDataEntrada(), conserto.getDataSaida(),
                conserto.getMecanico().getNome(), conserto.getVeiculo().getMarca(),
                conserto.getVeiculo().getModelo());
    }
}
