package com.carlosribeiro.apirestful.service;

import com.carlosribeiro.apirestful.exception.EntidadeDestacadaException;
import com.carlosribeiro.apirestful.exception.EntidadeNaoEncontradaException;
import com.carlosribeiro.apirestful.model.Categoria;
import com.carlosribeiro.apirestful.model.CategoriaDTO;
import com.carlosribeiro.apirestful.model.Produto;
import com.carlosribeiro.apirestful.repository.CategoriaRepository;
import com.carlosribeiro.apirestful.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

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
