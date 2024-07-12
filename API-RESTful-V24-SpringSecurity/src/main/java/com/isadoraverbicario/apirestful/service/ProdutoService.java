package com.isadoraverbicario.apirestful.service;

import com.isadoraverbicario.apirestful.exception.EntidadeDestacadaException;
import com.isadoraverbicario.apirestful.exception.EntidadeNaoEncontradaException;
import com.isadoraverbicario.apirestful.exception.EntidadeTransienteException;
import com.isadoraverbicario.apirestful.model.Produto;
import com.isadoraverbicario.apirestful.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> recuperarProdutos() {
        return produtoRepository.recuperarProdutosComCategoria();
    }

    public Produto cadastrarProduto(Produto produto) {
        if (produto.getId() == null) {
            return produtoRepository.save(produto);
        }
        else {
            throw new EntidadeDestacadaException(
                "Tentando cadastrar um objeto destacado.");
        }
    }

    @Transactional
    public Produto alterarProduto(Produto produto) {
        if (produto.getId() == null) {
            throw new EntidadeTransienteException("Tentando alterar um objeto transiente.");
        }
        else {
            produtoRepository.recuperarPorIdComLock(produto.getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                      "Produto número " + produto.getId() + " não encontrado."));
            return produtoRepository.save(produto);
        }
    }

    public Produto removerProduto(Long id) {
        Produto p = produtoRepository.recuperarProdutoPorId(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                "Produto número " + id + " não encontrado."));
        produtoRepository.delete(p);
        return p;
    }

    public Produto recuperarProdutoById(Long id) {
        return produtoRepository.recuperarProdutoPorId(id).orElse(null);
    }
    
    public List<Produto> recuperarProdutosPorIdDaCategoria(Long idCategoria) {
        return produtoRepository.findByCategoriaId(idCategoria);
    }

    public List<Produto> recuperarProdutosComCategoria() {
        return produtoRepository.recuperarProdutosComCategoria();
    }

    public Page<Produto> recuperarProdutosComPaginacao(String nome, Long usuadioId, Pageable pageable) {
        System.out.println(pageable);
        return produtoRepository.recuperarProdutosComPaginacao(nome, usuadioId, pageable);
    }

    public List<Produto> recuperarProdutosPorSlugDaCategoria(String slug) {
        return produtoRepository.findByCategoriaSlug(slug);
    }

    public Page<Produto> recuperarProdutosPaginadosPorSlugDaCategoria(String slug, Pageable pageable) {
        if(!slug.isEmpty()) {
            return produtoRepository.recuperarProdutosPaginadosPorSlugDaCategoria(slug, pageable);
        }
        else {
            return produtoRepository.recuperarProdutosPaginados(pageable);
        }
    }
}
