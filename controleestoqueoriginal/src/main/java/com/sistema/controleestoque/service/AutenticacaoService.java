package com.sistema.controleestoque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.sistema.controleestoque.model.Usuario;
import com.sistema.controleestoque.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario usuario = usuarioRepository.findByUsername(username);
    if (usuario == null) {
        throw new UsernameNotFoundException("Usuário não encontrado");
    }
    return User.builder()
            .username(usuario.getUsername())
            .password(usuario.getPassword()) // A senha deve estar codificada
            .roles(usuario.getRole()) // Retorna a role como está, sem modificar
            .build();
}

}
