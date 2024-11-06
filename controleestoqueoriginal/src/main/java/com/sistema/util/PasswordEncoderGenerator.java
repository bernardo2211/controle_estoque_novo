package com.sistema.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        // Senha do administrador
        String senhaAdmin = "123";
        String senhaAdminCodificada = encoder.encode(senhaAdmin);
        System.out.println("Senha do admin codificada: " + senhaAdminCodificada);
        
        // Senha do usuário comum
        String senhaUser = "123";
        String senhaUserCodificada = encoder.encode(senhaUser);
        System.out.println("Senha do user codificada: " + senhaUserCodificada);
    }
}
