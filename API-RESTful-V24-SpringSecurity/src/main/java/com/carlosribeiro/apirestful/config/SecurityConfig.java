package com.carlosribeiro.apirestful.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter; // responsável por configurar a
                                           // segurança da nossa aplicação.

    // Toda classe anotada com @Configuration possui métodos anotados com @Bean.
    // Estes métodos são executados pelo spring no startup da aplicação.
    // Os objetos retornados por esses métodos são salvos pelo spring para que
    // possam ser injetados em outras classes que os solicitarem, por exemplo,
    // com @Autowired.

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        // No objeto httpSecurity recebido por esse método, vamos adicionar algumas configurações
        System.out.println("3. securityFilterChain de SecurityConfig");

        httpSecurity
            .csrf(csrf -> csrf.disable())
            // Desabilita segurança para csrf - lança uma Exception
            // A segurança para csrf está sendo desabilitada pelo fato de que o Spring não irá
            // gerenciar a autenticação através do uso de sessões no servidor. A autenticação
            // sem o uso de sessões evita que o servidor tenha um número grande de sessões para
            // gerenciar. Isto é, o cliente precisará enviar um token de autenticação a cada nova
            // requisição. A linha abaixo ativa a autenticação stateless
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // Abaixo vamos definir quais urls desejamos proteger.
            // Observe que para submeter uma requisição de login não há nenhuma restrição.
            .authorizeHttpRequests(
                authorize ->
                    authorize
                        // Qualquer usuário pode submeter uma requisição do tipo POST para
                        // /autenticacao/login
                        .requestMatchers(HttpMethod.POST, "/autenticacao/login").permitAll()
                        // Qualquer usuário pode submeter uma requisição do tipo POST para
                        // para /autenticacao/cadastrarUsuario - naturalmente esta autorização
                        // deve ser removida após o cadastro de um usuário ADMIN.
                        .requestMatchers(HttpMethod.POST, "/autenticacao/cadastrarUsuario").permitAll()

                        // para um usuario poder submeter um POST, PUT ou DELETE ele deve
                        // possuir o perfil ADMIN
                        .requestMatchers(HttpMethod.POST).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")

                        // Para qq outra requisição não há nenhuma restrição.
                        .anyRequest().permitAll()
            )

            // Antes de efetuar as verificações acima, para saber quem é o usuário que está
            // acessando o servidor, deverá ser executado o método doFilterInternal de
            // SecurityFilter que irá recuperar do token, quem é o usuário que está acessando
            // o servidor e se ele tem o Role necessário para fazer executar o endpoint.

            // O UsernamePasswordAuthenticationFilter é uma classe do Spring Security que é
            // urilizada para autenticação baseada em formulário. Para aplicações REST que
            // preferem uma abordagem stateless e sem formulários, o UsernamePasswordAuthentication
            // Filter não faz nada apesar de ser incluído na lista de filtros. Em aplicações REST
            // a autenticação geralmente é efetuada através do uso de tokens JWT (JSON Web Tokens)
            // ou OAuth, o que permite que a autenticação seja efetuada de forma stateless.
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build(); // cria o objeto SecurityFilterChain que será salvo pelo Spring.
                                     // Mas note, examinando todas as classes desse projeto, que nenhuma
                                     // classe utiliza este objeto. Isto é, ele é utilizado apenas pelo
                                     // Spring.
    }

    // O método abaixo é chamado pelo método login() de AuthenticationController.
    // Quando, em AuthenticationController, chamamos o método authenticate de AuthenticationManager,
    // será utilizado por default o userDetailsService para recuperar o usuario (normalmente do BD)
    // e verificar se a senha fornecida é igual à senha armazenada no banco.

    // Esse método está retornando a implementação default.
    // Ele é chamado pelo Spring no startup da aplicação.
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration)
            throws Exception {

        // A classe AuthenticationManager é apenas um container para authentication providers.
        // Na maioria dos casos, o AuthenticationManager default é suficiente.

        // Este mecanismo permite que seja plugado um outro esquema de autenticação, como uma
        // autenticação contra um LDAP (protocolo padrão) ou contra um servidor Active Directory
        // (Microsoft), por exemplo.

        System.out.println("2. authenticationManager de SecurityConfig");
        return authenticationConfiguration.getAuthenticationManager();
    }

    // O Spring utiliza o objeto retornado pelo método abaixo para criptografar a senha
    // fornecida pelo usuário no processo de login, com o objetivo de verificar se a senha
    // fornecida pelo usuario no processo de login é igual à senha armazenada no banco de
    // dados.

    // Esse método é chamado pelo Spring no startup da aplicação
    @Bean
    public PasswordEncoder passwordEncoder() {
        System.out.println("1. passwordEncoder de SecurityConfig");
        return new BCryptPasswordEncoder();  // Usado para criptografar a senha fornecida
    }
}
