package com.carlosribeiro.apirestful.authentication;

import com.carlosribeiro.apirestful.enumeration.Role;

public record UsuarioRecord(String username, String password, Role role) {
}



















// public class UsuarioRecord {
//    private String username;
//    private String password;
//    private Role role;
//
//    public UsuarioRecord(String username, String password, Role role) {
//        this.username = username;
//        this.password = password;
//        this.role = role;
//    }
//
//    public String username() {
//        return username;
//    }
//
//    public String password() {
//        return password;
//    }
//
//    public Role role() {
//        return role;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        ...
//    }
//
//    @Override
//    public int hashCode() {
//        ...
//    }
//
//    @Override
//    public String toString() {
//        ...
//    }
// }