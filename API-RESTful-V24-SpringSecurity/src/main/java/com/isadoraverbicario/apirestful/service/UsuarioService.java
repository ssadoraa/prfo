package com.isadoraverbicario.apirestful.service;

import com.isadoraverbicario.apirestful.model.Usuario;
import com.isadoraverbicario.apirestful.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario cadastrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("==> Executou o método loadUserByUsername()");
        return usuarioRepository.findByUsername(username);
    }
}
