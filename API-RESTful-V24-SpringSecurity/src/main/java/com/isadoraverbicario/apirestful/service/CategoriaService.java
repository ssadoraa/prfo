package com.isadoraverbicario.apirestful.service;

import com.isadoraverbicario.apirestful.exception.EntidadeDestacadaException;
import com.isadoraverbicario.apirestful.exception.EntidadeNaoEncontradaException;
import com.isadoraverbicario.apirestful.exception.EntidadeTransienteException;
import com.isadoraverbicario.apirestful.model.Categoria;
import com.isadoraverbicario.apirestful.repository.CategoriaRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Categoria cadastrarCategoria(Categoria categoria) {
        if (categoria.getId() == null) {
            return categoriaRepository.save(categoria);
        }
        else {
            throw new EntidadeDestacadaException(
                "Tentando cadastrar um objeto destacado.");
        }
    }

    @Transactional
    public Categoria alterarCategoria(Categoria categoria) {
        if (categoria.getId() == null) {
            throw new EntidadeTransienteException("Tentando alterar um objeto transiente.");
        }
        else {
            categoriaRepository.recuperarPorIdComLock(categoria.getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                      "Categoria número " + categoria.getId() + " não encontrado."));
            return categoriaRepository.save(categoria);
        }
    }

    public Categoria removerCategoria(Long id) {
        Categoria c = categoriaRepository.recuperarCategoriaPorId(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                "Categoria número " + id + " não encontrado."));
        categoriaRepository.delete(c);
        return c;
    }

    public Categoria recuperarCategoriaComProdutos(Long idCategoria) {
        return categoriaRepository.recuperarCategoriaComProdutosPorIdDaCategoria(idCategoria)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Categoria número " + idCategoria + " não encontrada"));
    }

    public List<Categoria> recuperarCategorias() {
        return categoriaRepository.findAll();
    }

    public Page<Categoria> recuperarCategoriasComPaginacao(String nome, Pageable pageable) {
        return categoriaRepository.recuperarCategoriasComPaginacao(nome, pageable);
    }
}
