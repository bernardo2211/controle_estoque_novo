package com.sistema.controleestoque.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/patp", "/criadores", "/Login").permitAll() // Acesse a página de login
                .requestMatchers("/api/login").permitAll() // Permite acesso à API de login
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/pesquisa").hasAnyRole("USER", "ADMIN") // Permite acesso para USER e ADMIN
                .requestMatchers("/api/admin/**").hasRole("ADMIN") // Restringe para ADMIN
                .requestMatchers("/cadastro").hasRole("ADMIN")
                .requestMatchers("/adicionar").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/Login") // A página de login personalizada
                .permitAll()
                .defaultSuccessUrl("/pesquisa", true) // Redireciona para /pesquisa após login bem-sucedido
                .failureUrl("/Login?error=true") // Redireciona para a página de login com erro
            )
            .logout(logout -> logout
                .logoutUrl("/api/logout")
                .logoutSuccessUrl("/Login?logout=true") // Redireciona após logout
            )
            .csrf().disable(); // Desabilita CSRF para testes, se necessário
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
