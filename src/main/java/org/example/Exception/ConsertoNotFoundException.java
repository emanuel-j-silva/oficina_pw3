package org.example.Exception;


public class ConsertoNotFoundException extends RuntimeException{

    public ConsertoNotFoundException(){
        super("Conserto não encontrado.");
    }
}
