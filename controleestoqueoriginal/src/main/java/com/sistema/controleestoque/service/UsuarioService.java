package com.sistema.controleestoque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sistema.controleestoque.model.Usuario;
import com.sistema.controleestoque.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void cadastrarNovoUsuario(String username, String password, String role) {
        Usuario novoUsuario = new Usuario();
        novoUsuario.setUsername(username);
        novoUsuario.setPassword(passwordEncoder.encode(password)); // Criptografa a senha
        novoUsuario.setRole(role);
        usuarioRepo.save(novoUsuario);
    }
}
