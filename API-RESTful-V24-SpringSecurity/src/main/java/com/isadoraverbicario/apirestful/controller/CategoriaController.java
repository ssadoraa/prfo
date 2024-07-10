package com.isadoraverbicario.apirestful.controller;

import com.isadoraverbicario.apirestful.exception.EntidadeNaoEncontradaException;
import com.isadoraverbicario.apirestful.model.Categoria;
import com.isadoraverbicario.apirestful.model.CategoriaDTO;
import com.isadoraverbicario.apirestful.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> recuperarCategorias() {
        return categoriaService.recuperarCategorias();
    }

    @PostMapping
    public Categoria cadastrarCategoria(@RequestBody Categoria categoria) {
        return categoriaService.cadastrarCategoria(categoria);
    }

    @PutMapping
    public ResponseEntity<Categoria> alterarCategoria(@RequestBody Categoria categoria) {
        Categoria umCategoria = categoriaService.alterarCategoria(categoria);
        return new ResponseEntity<Categoria>(umCategoria,HttpStatus.OK);
    }

    @GetMapping("{idCategoria}")
    public Categoria recuperarCategoria(@PathVariable("idCategoria") Long idCategoria) {
        return categoriaService.recuperarCategoria(idCategoria)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Categoria número " + idCategoria + " não encontrada"));
    }

    @GetMapping("{idCategoria}/produtos")
    public CategoriaDTO recuperarCategoriaComProdutos(@PathVariable("idCategoria") Long idCategoria) {
        Categoria categoria = categoriaService.recuperarCategoriaComProdutos(idCategoria);
        return new CategoriaDTO(categoria.getId(), categoria.getNome(), categoria.getProdutos());
    }
}
