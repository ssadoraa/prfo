package com.carlosribeiro.apirestful.controller;

import com.carlosribeiro.apirestful.model.Produto;
import com.carlosribeiro.apirestful.model.ResultadoPaginado;
import com.carlosribeiro.apirestful.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping   // http://localhost:8080/produtos
    public List<Produto> recuperarProdutos() {
        return produtoService.recuperarProdutos();
    }

    @PostMapping   // http://localhost:8080/produtos
    public Produto cadastrarProduto(@RequestBody Produto produto) {
        return produtoService.cadastrarProduto(produto);
    }

    @PutMapping
    public ResponseEntity<Produto> alterarProduto(@RequestBody Produto produto) {
        Produto umProduto = produtoService.alterarProduto(produto);
        return new ResponseEntity<Produto>(umProduto,HttpStatus.OK);
    }

    @DeleteMapping ("{idProduto}")     // http://localhost:8080/produtos/1
    public Produto removerProduto(@PathVariable("idProduto") Long id) {
        return produtoService.removerProduto(id);
    }

    @GetMapping("categoria/{idCategoria}")             // http://localhost:8080/produtos/categoria/1
    public List<Produto> recuperarProdutosPorIdDaCategoria(@PathVariable("idCategoria") Long idCategoria) {
        System.out.println(idCategoria);
        return produtoService.recuperarProdutosPorIdDaCategoria(idCategoria);
    }

    @GetMapping("categorias")    // http://localhost:8080/produtos/categorias
    public List<Produto> recuperarProdutosComCategoria() {
        return produtoService.recuperarProdutosComCategoria();
    }

    // http://localhost:8080/produtos/paginacao?pagina=0&tamanho=5
    @GetMapping("paginacao")
    public ResultadoPaginado<Produto> recuperarProdutosComPaginacao(
            @RequestParam(value = "pagina", defaultValue = "0") int pagina,
            @RequestParam(value = "tamanho", defaultValue = "3") int tamanho,
            @RequestParam(value = "nome", defaultValue = "") String nome) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        Page<Produto> page = produtoService.recuperarProdutosComPaginacao(nome, pageable);
        ResultadoPaginado<Produto> resultadoPaginado = new ResultadoPaginado<>(
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber(),
                page.getContent());
        return resultadoPaginado;
    }

    // localhost:8080/produtos/slugCategoria/frutas
    @GetMapping("slugCategoria/{slug}")
    public List<Produto> recuperarProdutosPorSlugDaCategoria(@PathVariable("slug") String slug) {
        return produtoService.recuperarProdutosPorSlugDaCategoria(slug);
    }

    // http://localhost:8080/produtos/categoria/paginacao?pagina=0&tamanho=6&slug=frutas
    @GetMapping("categoria/paginacao")
    public ResultadoPaginado<Produto> recuperarProdutosPaginadosPorSlugDaCategoria(
            @RequestParam(value = "pagina", defaultValue = "0") int pagina,
            @RequestParam(value = "tamanho", defaultValue = "3") int tamanho,
            @RequestParam(value = "slug", defaultValue = "") String slug) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        Page<Produto> page = produtoService.recuperarProdutosPaginadosPorSlugDaCategoria(slug, pageable);
        ResultadoPaginado<Produto> resultadoPaginado = new ResultadoPaginado<>(
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber(),
                page.getContent());
        return resultadoPaginado;
    }

}
