package com.sistema.controleestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sistema.controleestoque.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
}
