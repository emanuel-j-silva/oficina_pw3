package org.example.Service;

import org.example.Exception.ConsertoNotFoundException;
import org.example.Model.Conserto;
import org.example.Repository.ConsertoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindOneConsertoService {

    @Autowired
    ConsertoRepository consertoRepository;

    public Conserto executar(Long id){
        var conserto = consertoRepository.findById(id)
                .orElseThrow(()-> new ConsertoNotFoundException());
        return conserto;
    }
}
