package org.example.Service;

import org.example.Model.Conserto;
import org.example.Repository.ConsertoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllConsertoService {

    @Autowired
    ConsertoRepository consertoRepository;

    public Page<Conserto> executar(Pageable pageable){
        var pageConsertos = consertoRepository.findAll(pageable);
        return pageConsertos;
    }
}
