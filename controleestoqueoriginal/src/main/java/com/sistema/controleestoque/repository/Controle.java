package com.sistema.controleestoque.repository;

import org.springframework.web.bind.annotation.GetMapping;

public class Controle {

    @GetMapping("")
    public String mensagem() {
        return "Hello World!";
    }

}
