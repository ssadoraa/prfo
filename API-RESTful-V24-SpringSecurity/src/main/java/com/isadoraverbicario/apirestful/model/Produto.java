package com.isadoraverbicario.apirestful.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "A 'Imagem' deve ser informada.")
    private String imagem;

    @NotEmpty(message = "O 'Nome' deve ser informado.")
    private String nome;

    @NotEmpty(message = "A 'Descrição' deve ser informada.")
    private String descricao;
    
    @NotEmpty(message = "A 'Condição' deve ser informada.")
    private String condicao;

    @NotNull(message = "O 'Valor Estimado' deve ser informado.")
    @DecimalMin(inclusive = true, value="0.1", message = "O 'Valor Estimado' deve ser maior ou igual a 0.1.")
    private BigDecimal valorEstimado;

    @NotNull(message = "A 'Data de Cadastro' deve ser informada.")
    private LocalDate dataCadastro;

    @ManyToOne
    private Categoria categoria;

    @NotNull
    private Long usuarioId;

    public Produto(String imagem,
                   String nome,
                   String descricao,
                   String condicao,
                   BigDecimal valorEstimado,
                   LocalDate dataCadastro,
                   Categoria categoria,
                   Long usuarioId) {
        this.imagem = imagem;
        this.nome = nome;
        this.descricao = descricao;
        this.condicao = condicao;
        this.valorEstimado = valorEstimado;
        this.dataCadastro = dataCadastro;
        this.categoria = categoria;
        this.usuarioId = usuarioId;
    }
}
