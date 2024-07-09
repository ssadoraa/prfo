package com.carlosribeiro.apirestful.service;

import com.carlosribeiro.apirestful.model.Usuario;
import com.carlosribeiro.apirestful.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Não há um método save() na interface UserDetailsService.
    // Ela só possui o método loadUserByUsername().
    public Usuario cadastrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // O spring security executa o método abaixo automaticamente sempre que alguém
    // tentar se autenticar na nossa aplicação.

    // A interface UserDetailsService possui apenas um método que é o
    // loadUserByUsername, logo, ele precisa ser implementado aqui.
    @Override
    public Usuario loadUserByUsername(String username) throws UsernameNotFoundException {
        // Esse método é chamado pelo método login e register de AuthenticationController.
        System.out.println("==> Executou o método loadUserByUsername()");
        return usuarioRepository.findByUsername(username);
    }
}
