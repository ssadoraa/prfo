package com.carlosribeiro.apirestful.authentication;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.carlosribeiro.apirestful.config.TokenService;
import com.carlosribeiro.apirestful.exception.UsuarioJaCadastradoException;
import com.carlosribeiro.apirestful.model.Usuario;
import com.carlosribeiro.apirestful.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("autenticacao")   // http://localhost:8080/autenticacao
public class AuthenticationController {

    @Autowired
    private UsuarioService usuarioService;  // @Service

    @Autowired
    private PasswordEncoder passwordEncoder;  // @Bean

    @Autowired
    private AuthenticationManager authenticationManager;  // @Bean

    @Autowired
    private TokenService tokenService;  // @Service

    @PostMapping("login")      // http://localhost:8080/autenticacao/login
    public TokenResponse login(@RequestBody AuthenticationRecord authenticationRecord) {

        System.out.println(">>> Entrou no método login de AuthenticationController");
        var usernamePassword = new UsernamePasswordAuthenticationToken
                (authenticationRecord.username(), authenticationRecord.password());
        System.out.println("usernamePassword = " + usernamePassword);
        try {
            System.out.println("Antes de chamar authenticationManager.authenticate(usernamePassword)");
            Authentication authentication = authenticationManager.authenticate(usernamePassword);
            // O método authenticate() chama automaticamente o método loadUserByUsername de UsarioService.
            // O AuthenticationManager chama o método loadUserByUsername quando estamos utilizando o
            // AuthenticationManager padrão. Se estivermos utilizando um outro AuthenticationManager
            // (por exemplo, um AuthenticationManager que faça uso de um LDAP para autenticar o usuario)
            // nesse caso o método loadUserByUsername() não será executado. Será chamado o método apropriado
            // do AuthenticationProvider que estará sendo usado para validar as credenciais.

            System.out.println("Após de chamar authenticationManager.authenticate(usernamePassword)");
            // O método authenticate recebe um token criado acima.
            // O spring irá autenticar o usuário utilizando o BCrypt para criptografar a senha
            // fornecida no login para que ela possa ser comparada com a senha que se encontra
            // no BD.

            // Uma vez autenticado o usuário, vamos utilizar o objeto authentication retornado
            // acima para gerar o token que será utilizado pelo usuário em todas as requisições
            // que necessitam de autenticação.
            System.out.println("Antes de gerar o token.");
            var token = tokenService.gerarToken((Usuario) authentication.getPrincipal());
            System.out.println("Após gerar o token = " + token);

            return new TokenResponse(token);
        }
        catch(IllegalArgumentException |
              JWTCreationException |
              AuthenticationException e) {

            // O método generateToken lança as exceções:
            // - IllegalArgumentException que estende RuntimeException
            // - JWTCreationException que estende RuntimeException

            // O método authenticate() lança as exceções:
            // - InternalAuthenticationServiceException - Conta e senha erradas ou não informadas
            // - BadCredentialsException - Conta existente, senha errada
            // Ambas subclasses de AuthenticationException
            System.out.println("A classe da exceção: " + e.getClass().getName());
            throw e;
        }
    }

    @PostMapping("cadastrarUsuario")       // http://localhost:8080/autenticacao/cadastrarUsuario
    public Usuario cadastrarUsuario(@RequestBody UsuarioRecord usuarioRecord) {

        // Como vamos cadastrar um novo usuário ele não pode existir no BD.
        if (usuarioService.loadUserByUsername(usuarioRecord.username()) == null) {

            // O método passwordEncoder.encode() abaixo lança a exceção java.lang.
            // IllegalArgumentException caso não seja informada uma senha para o usuário
            // a ser cadastrado.

            // As outras duas validações (o username deve ser informado ou o role deve ser
            // informado) são capturadas pelo Hibernate Validator quando o objeto usuario
            // é criado abaixo.

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
