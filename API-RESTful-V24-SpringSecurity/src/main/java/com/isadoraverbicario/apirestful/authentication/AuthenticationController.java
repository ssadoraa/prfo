package com.isadoraverbicario.apirestful.authentication;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.isadoraverbicario.apirestful.config.TokenService;
import com.isadoraverbicario.apirestful.exception.UsuarioJaCadastradoException;
import com.isadoraverbicario.apirestful.model.Usuario;
import com.isadoraverbicario.apirestful.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("autenticacao")
public class AuthenticationController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("login")
    public TokenResponse login(@RequestBody AuthenticationRecord authenticationRecord) {

        System.out.println(">>> Entrou no método login de AuthenticationController");
        var usernamePassword = new UsernamePasswordAuthenticationToken
                (authenticationRecord.username(), authenticationRecord.password());
        System.out.println("usernamePassword = " + usernamePassword);
        try {
            System.out.println("Antes de chamar authenticationManager.authenticate(usernamePassword)");
            Authentication authentication = authenticationManager.authenticate(usernamePassword);

            System.out.println("Após de chamar authenticationManager.authenticate(usernamePassword)");
            System.out.println("Antes de gerar o token.");
            var token = tokenService.gerarToken((Usuario) authentication.getPrincipal());
            System.out.println("Após gerar o token = " + token);

            return new TokenResponse(token);
        }
        catch(IllegalArgumentException |
              JWTCreationException |
              AuthenticationException e) {
            System.out.println("A classe da exceção: " + e.getClass().getName());
            throw e;
        }
    }

    @PostMapping("cadastrarUsuario")
    public Usuario cadastrarUsuario(@RequestBody UsuarioRecord usuarioRecord) {

        if (usuarioService.loadUserByUsername(usuarioRecord.username()) == null) {

            Usuario usuario = new Usuario();
            usuario.setUsername(usuarioRecord.username());
            usuario.setPassword(passwordEncoder.encode(usuarioRecord.password()));
            usuario.setRole(usuarioRecord.role());
            usuario.setAccountNonLocked(true);
            usuario.setCredentialsNonExpired(true);
            usuario.setAccountNonExpired(true);
            usuario.setEnabled(true);

            usuarioService.cadastrarUsuario(usuario);
            return usuario;
        }
        else {
            throw new UsuarioJaCadastradoException(
                    "Usuário " + usuarioRecord.username() + " já cadastrado.");
        }
    }
}
