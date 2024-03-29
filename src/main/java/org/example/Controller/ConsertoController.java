package org.example.Controller;

import jakarta.validation.Valid;
import org.example.DTO.ConsertoDTO;
import org.example.Model.Conserto;
import org.example.Model.Mecanico;
import org.example.Model.Veiculo;
import org.example.Repository.ConsertoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsertoController {

    @Autowired ConsertoRepository consertoRepository;

    @PostMapping("/consertos")
    public ResponseEntity<Conserto> salvarConserto(@RequestBody @Valid ConsertoDTO consertoDTO){
        var conserto = new Conserto();
        conserto.setDataEntrada(consertoDTO.dataEntrada());
        conserto.setDataSaida(consertoDTO.dataSaida());

        var mecanico = new Mecanico(consertoDTO.mecanico().getNome(),consertoDTO.mecanico().getAnosExp());
        var veiculo = new Veiculo(consertoDTO.veiculo().getMarca(),consertoDTO.veiculo().getModelo(),
                                    consertoDTO.veiculo().getAno());
        conserto.setMecanico(mecanico);
        conserto.setVeiculo(veiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(consertoRepository.save(conserto));
    }

}
