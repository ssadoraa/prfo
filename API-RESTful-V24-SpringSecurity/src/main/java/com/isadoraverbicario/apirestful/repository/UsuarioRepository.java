package com.isadoraverbicario.apirestful.repository;

import com.isadoraverbicario.apirestful.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
}
