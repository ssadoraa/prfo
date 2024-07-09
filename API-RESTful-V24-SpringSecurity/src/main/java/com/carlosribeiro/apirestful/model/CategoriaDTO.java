package com.carlosribeiro.apirestful.model;

import java.util.List;

public record CategoriaDTO(Long id, String nome, List<Produto> lista) {

}
