package org.example.Service;

import org.example.Exception.ConsertoNotFoundException;
import org.example.Model.Conserto;
import org.example.Repository.ConsertoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteConsertoService {

    @Autowired
    ConsertoRepository consertoRepository;

    public String executar(Conserto conserto){
        if (!consertoRepository.existsById(conserto.getId())){
            throw new ConsertoNotFoundException();
        }
        conserto.setAtivo(false);
        consertoRepository.save(conserto);
        return "Conserto deletado com sucesso.";
    }
}
