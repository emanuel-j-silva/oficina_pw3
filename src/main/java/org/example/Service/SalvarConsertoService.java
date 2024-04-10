package org.example.Service;

import org.example.Model.Conserto;
import org.example.Repository.ConsertoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalvarConsertoService {

    @Autowired
    ConsertoRepository consertoRepository;

    public Conserto executar(Conserto conserto){
        return consertoRepository.save(conserto);
    }
}
