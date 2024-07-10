package com.isadoraverbicario.apirestful.service;

import com.isadoraverbicario.apirestful.exception.EntidadeNaoEncontradaException;
import com.isadoraverbicario.apirestful.model.Categoria;
import com.isadoraverbicario.apirestful.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Optional<Categoria> recuperarCategoria(Long idCategoria) {
        return categoriaRepository.findById(idCategoria);
    }

    public Categoria recuperarCategoriaComProdutos(Long idCategoria) {
        return categoriaRepository.recuperarCategoriaComProdutosPorIdDaCategoria(idCategoria)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Categoria número " + idCategoria + " não encontrada"));
    }

    public List<Categoria> recuperarCategorias() {
        return categoriaRepository.findAll();
    }
}
