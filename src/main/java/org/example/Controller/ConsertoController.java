package org.example.Controller;

import jakarta.validation.Valid;
import org.example.DTO.AtualizaConsertoDTO;
import org.example.DTO.ConsertoDTO;
import org.example.DTO.DadosListagemConsertoDTO;
import org.example.Model.Conserto;
import org.example.Model.Mecanico;
import org.example.Model.Veiculo;
import org.example.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConsertoController {

    @Autowired SalvarConsertoService salvarConserto;

    @Autowired FindAllConsertoService findAll;
    @Autowired FindOneConsertoService findOne;
    @Autowired
    FindAllAtivoConsertoService findAllAtivo;
    @Autowired DeleteConsertoService deleteConserto;

    @PostMapping("/consertos")
    public ResponseEntity<Conserto> salvarConserto(@RequestBody @Valid ConsertoDTO consertoDTO){
        var conserto = new Conserto();
        conserto.setDataEntrada(consertoDTO.dataEntrada());
        conserto.setDataSaida(consertoDTO.dataSaida());

        var mecanico = new Mecanico();
        mecanico.setNome(consertoDTO.mecanico().nome());
        Integer anosExp = consertoDTO.mecanico().anosExp();
        if (anosExp != null) {
            mecanico.setAnosExp(consertoDTO.mecanico().anosExp());
        }else{
            mecanico.setAnosExp(null);
        }

        var veiculo = new Veiculo(consertoDTO.veiculo().marca(),consertoDTO.veiculo().modelo(),
                                    Integer.valueOf(consertoDTO.veiculo().ano()),consertoDTO.veiculo().cor());
        conserto.setMecanico(mecanico);
        conserto.setVeiculo(veiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvarConserto.executar(conserto));
    }

    @GetMapping("/consertos")
    public ResponseEntity<Page<Conserto>> findAllConsertos(@PageableDefault Pageable pageable){
        var pageConsertos = findAll.executar(pageable);

        return pageConsertos != null ?
                ResponseEntity.status(HttpStatus.OK).body(pageConsertos):
                ResponseEntity.noContent().build();
    }

    @GetMapping("/consertos/alguns-dados")
    public ResponseEntity<Page<DadosListagemConsertoDTO>> findDadosListagemConsertos(
            @PageableDefault Pageable pageable){
        var pageDadosConsertos = findAllAtivo.executar(pageable).map(DadosListagemConsertoDTO::new);
        return pageDadosConsertos != null ?
                ResponseEntity.status(HttpStatus.OK).body(pageDadosConsertos):
                ResponseEntity.noContent().build();
    }

    @GetMapping("/consertos/{id}")
    public ResponseEntity<Object> findOneConserto(@PathVariable(value = "id")Long id){
        var conserto = findOne.executar(id);
        return ResponseEntity.status(HttpStatus.OK).body(conserto);
    }

    @PutMapping("/consertos/{id}")
    public ResponseEntity<Object> atualizarConserto(@PathVariable(value = "id")Long id,
                                                    @RequestBody AtualizaConsertoDTO consertoDTO){
        var conserto = findOne.executar(id);
        if (consertoDTO.dataSaida() != null) conserto.setDataSaida(consertoDTO.dataSaida());
        if (consertoDTO.nome() != null) conserto.getMecanico().setNome(consertoDTO.nome());
        if (consertoDTO.anosExp() != null) conserto.getMecanico().setAnosExp(consertoDTO.anosExp());

        return ResponseEntity.status(HttpStatus.OK).body(salvarConserto.executar(conserto));
    }

    @DeleteMapping("/consertos/{id}")
    public ResponseEntity<Object> deleteConserto(@PathVariable(value = "id")Long id){
        var conserto = findOne.executar(id);
        return ResponseEntity.status(HttpStatus.OK).body(deleteConserto.executar(conserto));
    }
}

