package com.sistema.controleestoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sistema.controleestoque.service.UsuarioService;

@Controller
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrarUsuario")
    @ResponseBody
    public String cadastrarUsuario(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String role) {

        usuarioService.cadastrarNovoUsuario(username, password, role);
        return "Usuário cadastrado com sucesso!";
    }
}

