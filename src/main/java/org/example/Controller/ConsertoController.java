package org.example.Controller;

import jakarta.validation.Valid;
import org.example.DTO.ConsertoDTO;
import org.example.DTO.DadosListagemConsertoDTO;
import org.example.Model.Conserto;
import org.example.Model.Mecanico;
import org.example.Model.Veiculo;
import org.example.Service.FindAllConsertoService;
import org.example.Service.FindListagemConsertoService;
import org.example.Service.SalvarConsertoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsertoController {

    @Autowired
    SalvarConsertoService salvarConserto;

    @Autowired
    FindAllConsertoService findAll;

    @Autowired
    FindListagemConsertoService findAllListagemConserto;

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
        return ResponseEntity.status(HttpStatus.OK).body(pageConsertos);
    }

    @GetMapping("/consertos/alguns-dados")
    public ResponseEntity<Page<DadosListagemConsertoDTO>> findDadosListagemConsertos(
            @PageableDefault Pageable pageable){
        var pageDadosConsertos =  findAllListagemConserto.executar(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(pageDadosConsertos);
    }
}
