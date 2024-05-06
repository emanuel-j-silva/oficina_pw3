package org.example.Service;

import org.example.Model.Conserto;
import org.example.Repository.ConsertoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindAllAtivoConsertoService {

    @Autowired
    ConsertoRepository consertoRepository;

    public Page<Conserto> executar(Pageable pageable){
        var pageConsertos = consertoRepository.findByAtivoTrue(pageable);
        return pageConsertos;
    }
}
