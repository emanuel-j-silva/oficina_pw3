package org.example.Service;

import org.example.DTO.DadosListagemConsertoDTO;
import org.example.Model.Conserto;
import org.example.Repository.ConsertoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindListagemConsertoService {

    @Autowired
    ConsertoRepository consertoRepository;

    public Page<DadosListagemConsertoDTO> executar(Pageable pageable) {
        Page<Conserto> pageDadosListagemConserto = consertoRepository.findAll(pageable);
        return pageDadosListagemConserto.map(DadosListagemConsertoDTO::new);

    }
}
