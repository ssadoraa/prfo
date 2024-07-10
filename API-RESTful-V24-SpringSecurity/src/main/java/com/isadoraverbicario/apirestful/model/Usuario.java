package com.isadoraverbicario.apirestful.model;

import com.isadoraverbicario.apirestful.enumeration.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor // em função do Builder
@Builder // Facilita a criação de objetos
@Entity
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "A 'Conta' deve ser informada.")
    private String username;

    @NotEmpty(message = "A 'Senha' deve ser informada.")
    private String password;

    // armazena no BD ADMIN ou USER em vez de 0 e 1
    @Enumerated(EnumType.STRING)
    private Role role;

    // Todos os métodos get/set implementados pelo Lombok
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public Usuario(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Um usuário com o Role ADMIN também terá o Role USER daí o
    // método getAuthorities() retornar uma collection e não apenas
    // um Role. Não é obrigatório um Role mais acima na hierarquia
    // ter todos os Roles mais abaixo. Você pode montar a collection
    // de Roles como quiser.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // System.out.println("getAuthorities de User");
        if (this.role == Role.ADMIN)
            return List.of(
                new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_USER"));

        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
