package com.carlosribeiro.apirestful.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.carlosribeiro.apirestful.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class TokenService {
    // A chave secreta fornecida abaixo foi gerado com o url abaixo:
    // https://asecuritysite.com/encryption/plain

    // Caso tenha sido definida uma variável de ambiente denominada CHAVE-SECRETA
    // no sistema operacional, o valor dela será utilizado.
    // Caso contrário, o valor de CHAVE-SECRETA será 105... (o valor default)

    // Ponha o mouse em cima de CHAVE-SECRETA abaixo e aparecerá api.security.token.secret
    // que é o valor definido em application.properties

    @Value("${api.security.token.secret}")
    private String secret;
    private static final int DURACAO_TOKEN = 15 * 60 * 60 * 1000;  // 15H

    // Gera o token a partir do username e do algoritmo HMAC256.
    public String gerarToken(Usuario usuario)
        throws IllegalArgumentException, JWTCreationException{
        // O algoritmo de geração de token que será utilizado
        // A secret faz com que os hash(es) gerados sejam únicos.
        // E além da chave secret o algoritmo ainda utiliza o sal
        // para que duas senhas iguais não apareçam iguais no BD.
        Algorithm algoritmo = Algorithm.HMAC256(secret);
        String token = JWT.create()
                .withIssuer("com.carlosribeiro")     // emissor do token (íssuer)
                .withSubject(usuario.getUsername())  // o usuário que vai receber o token
                // Quando o usuário fizer uma requisição o username dele estará no token e
                // poderá ser recuperado pela aplicação.
                .withExpiresAt(Instant.now().plus(Duration.ofSeconds(DURACAO_TOKEN)))
                .sign(algoritmo);   // o algoritmo gera o token
        return token;
    }

    // Método para validar o token. Retorna o username do usuário.
    public String validarToken(String token)
        throws IllegalArgumentException, JWTVerificationException {
        // Para que o algoritmo de hash gere saídas baseadas na chave secreta.
        Algorithm algoritmo = Algorithm.HMAC256(secret);
        return JWT.require(algoritmo)   // informa o algoritmo que será utilizado
                  .withIssuer("com.carlosribeiro")  // informa quem emitiu o token
                  .build()              // cria e retorna um JWTVerifier
                  .verify(token)        // retorna um DecodedJWT - descriptografa o token
                                        // e gera JWTVerificationException
                  .getSubject();        // retorna o username (VEJA withSubject() acima)
    }
}
