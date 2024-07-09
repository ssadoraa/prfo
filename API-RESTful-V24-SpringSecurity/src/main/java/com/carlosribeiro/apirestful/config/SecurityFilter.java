package com.carlosribeiro.apirestful.config;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.carlosribeiro.apirestful.exception.EntidadeNaoEncontradaException;
import com.carlosribeiro.apirestful.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    // @Qualifier("handlerExceptionResolver")
    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // O método abaixo será executado para cada requisição encaminhada ao servidor
    // (OncePerRequestFilter). Ele será será executado para recuperar o username do
    // usuário que enviou o token na requisição.
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = recuperarToken(request);
            if (token != null) {
                // O método validarToken(token) irá retornar uma exceção do tipo
                // JWTVerificationException caso o token seja inválido. E, nesse
                // caso, será exibida a exceção 401 UNAUTHORIZED no browser em
                // função da inclusão desta exceção no GlobalExceptionHandler.

                // Acontece que o filtro é executado antes do controller e o
                // GlobalExceptionHandler, que é um "controller advice", só entra
                // em ação para exceções lançadas pelo controller. Para que o
                // controller advice possa tratar a exceção gerada pelo filtro é
                // preciso capturar a exceção e regerá-la com:
                // resolver.resolveException(request, response, null, e);
                // conforme fizemos no catch abaixo.
                String username = tokenService.validarToken(token);
                UserDetails usuario = usuarioRepository.findByUsername(username);
                if (usuario == null) {
                    throw new EntidadeNaoEncontradaException(
                        "Usuário " + username + " não encontrado no banco de dados.");
                }
                // Abaixo estamos criando um objeto do tipo UsernamePasswordAuthenticationToken
                // que irá conter o objeto usuario e seus perfis (roles). Em seguida, salvamos
                // esse objeto no objeto securityContext do spring para que ele possa ser
                // recuperado ao ser preciso verificar se o usuário logado tem permissão para
                // executar o endpoint.
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("===> Salvou o token no contexto (request) do spring security.");
            }
            else {
                System.out.println("===> Sem token.");
            }
            System.out.println(">>>> Terminando a execução de SecurityFilter.");
            System.out.println(">>>> Vai chamar o filtro seguinte: UsernamePasswordAuthenticationFilter (que não irá fazer nada)");
            System.out.println(">>>> Em seguida será verificado se o usuário tem permissão para executar o endpoint.");
            filterChain.doFilter(request, response);

      //} catch JWTVerificationException e) {
        } catch (Exception e) {
            System.out.println(">>>> Classe de exceção: " + e.getClass().getName());
            handlerExceptionResolver.resolveException(request, response, null, e);
        }
    }
    private String recuperarToken(HttpServletRequest request) {
        // Recupera o header Authorization.
        String authHeader = request.getHeader("Authorization");
        // Se (authHeader == null) significa que não há nenhum token nessa requisição.
        if (authHeader == null) return null;
        // retorna um String contendo o valor do token recebido
        return authHeader.replace("Bearer ", "");  // Pronúncia: Béarer
        // O token Bearer possui o seguinte formato:
        // "Bearer 2da67da08bbe36d42ed9735305a15cfc7f2da67da08bbe36d42"
        // Removendo a palavra Bearer fica assim:
        // "2da67da08bbe36d42ed9735305a15cfc7f2da67da08bbe36d42"
    }
}
