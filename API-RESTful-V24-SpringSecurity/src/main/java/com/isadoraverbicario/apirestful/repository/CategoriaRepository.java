package com.isadoraverbicario.apirestful.repository;

import com.isadoraverbicario.apirestful.model.Categoria;

import jakarta.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("select c from Categoria c left outer join fetch c.produtos where c.id = 1")
    Optional<Categoria> recuperarCategoriaComProdutosPorIdDaCategoria(long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Categoria p where p.id = :id")
    Optional<Categoria> recuperarPorIdComLock(Long id);
}
