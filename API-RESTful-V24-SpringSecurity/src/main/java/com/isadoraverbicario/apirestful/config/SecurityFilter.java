package com.isadoraverbicario.apirestful.config;

import com.isadoraverbicario.apirestful.exception.EntidadeNaoEncontradaException;
import com.isadoraverbicario.apirestful.repository.UsuarioRepository;
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

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = recuperarToken(request);
            if (token != null) {
                String username = tokenService.validarToken(token);
                UserDetails usuario = usuarioRepository.findByUsername(username);
                if (usuario == null) {
                    throw new EntidadeNaoEncontradaException(
                        "Usuário " + username + " não encontrado no banco de dados.");
                }

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

        } catch (Exception e) {
            System.out.println(">>>> Classe de exceção: " + e.getClass().getName());
            handlerExceptionResolver.resolveException(request, response, null, e);
        }
    }
    private String recuperarToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
