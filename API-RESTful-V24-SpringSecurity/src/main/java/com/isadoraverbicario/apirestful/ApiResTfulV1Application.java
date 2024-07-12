package com.isadoraverbicario.apirestful;

import com.isadoraverbicario.apirestful.enumeration.Role;
import com.isadoraverbicario.apirestful.model.Categoria;
import com.isadoraverbicario.apirestful.model.Produto;
import com.isadoraverbicario.apirestful.model.Usuario;
import com.isadoraverbicario.apirestful.repository.CategoriaRepository;
import com.isadoraverbicario.apirestful.repository.ProdutoRepository;
import com.isadoraverbicario.apirestful.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class ApiResTfulV1Application implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ApiResTfulV1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Usuario admin = usuarioRepository.findByUsername("admin");
		if (admin == null) {
			Usuario usuario = new Usuario();
			usuario.setUsername("admin");
			usuario.setPassword(passwordEncoder.encode("12345"));
			usuario.setRole(Role.ADMIN);
			usuario.setAccountNonLocked(true);
			usuario.setCredentialsNonExpired(true);
			usuario.setAccountNonExpired(true);
			usuario.setEnabled(true);

			usuarioRepository.save(usuario);
		}

		Usuario isadora = usuarioRepository.findByUsername("isadora");
		if (isadora == null) {
			Usuario usuario = new Usuario();
			usuario.setUsername("isadora");
			usuario.setPassword(passwordEncoder.encode("isa123"));
			usuario.setRole(Role.USER);
			usuario.setAccountNonLocked(true);
			usuario.setCredentialsNonExpired(true);
			usuario.setAccountNonExpired(true);
			usuario.setEnabled(true);

			usuarioRepository.save(usuario);
		}

		Usuario user = usuarioRepository.findByUsername("user");
		if (user == null) {
			Usuario usuario = new Usuario();
			usuario.setUsername("user");
			usuario.setPassword(passwordEncoder.encode("isa123"));
			usuario.setRole(Role.USER);
			usuario.setAccountNonLocked(true);
			usuario.setCredentialsNonExpired(true);
			usuario.setAccountNonExpired(true);
			usuario.setEnabled(true);

			usuarioRepository.save(usuario);
		}

		Categoria automotivo = new Categoria("Automotivo", "automotivo");
		categoriaRepository.save(automotivo);

		Categoria beleza = new Categoria("Beleza", "beleza");
		categoriaRepository.save(beleza);

		Categoria brinquedos = new Categoria("Brinquedos", "brinquedos");
		categoriaRepository.save(brinquedos);

		Categoria decoracao = new Categoria("Decoração", "decoracao");
		categoriaRepository.save(decoracao);

		Categoria eletronicos = new Categoria("Eletrônicos", "eletronicos");
		categoriaRepository.save(eletronicos);

		Categoria escritorio = new Categoria("Escritório", "escritorio");
		categoriaRepository.save(escritorio);

		Categoria esportes = new Categoria("Esportes", "esportes");
		categoriaRepository.save(esportes);

		Categoria ferramentas = new Categoria("Ferramentas", "ferramentas");
		categoriaRepository.save(ferramentas);

		Categoria informatica = new Categoria("Informática", "informatica");
		categoriaRepository.save(informatica);

		Categoria jardinagem = new Categoria("Jardinagem", "jardinagem");
		categoriaRepository.save(jardinagem);

		Categoria livros = new Categoria("Livros", "livros");
		categoriaRepository.save(livros);

		Categoria moveis = new Categoria("Móveis", "moveis");
		categoriaRepository.save(moveis);

		Categoria musica = new Categoria("Música", "musica");
		categoriaRepository.save(musica);

		Categoria pet = new Categoria("Pet", "pet");
		categoriaRepository.save(pet);

		Categoria roupas = new Categoria("Roupas", "roupas");
		categoriaRepository.save(roupas);

		Produto calca = new Produto(
				"calca.png",
				"Calça",
				"Tecido de algodão, tamanho variável",
				"novo",
				BigDecimal.valueOf(59.90),
				LocalDate.of(2024, 7, 12),
				roupas,
				1L);
		produtoRepository.save(calca);

		Produto casaco = new Produto(
				"casaco.png",
				"Casaco",
				"Material sintético, tamanho M",
				"novo",
				BigDecimal.valueOf(89.99),
				LocalDate.of(2024, 7, 12),
				roupas,
				2L);
		produtoRepository.save(casaco);

		Produto bola = new Produto(
				"bola.png",
				"Bola",
				"Bola de futebol tamanho padrão",
				"novo",
				BigDecimal.valueOf(29.99),
				LocalDate.of(2024, 7, 12),
				brinquedos,
				3L);
		produtoRepository.save(bola);

		Produto celular = new Produto(
				"celular.png",
				"Celular",
				"Smartphone Iphone, IOS, 128GB",
				"novo",
				BigDecimal.valueOf(1499.99),
				LocalDate.of(2024, 7, 12),
				informatica,
				1L);
		produtoRepository.save(celular);

		Produto coleira = new Produto(
				"coleira.png",
				"Coleira",
				"Coleira de nylon para cachorro, ajustável",
				"novo",
				BigDecimal.valueOf(19.99),
				LocalDate.of(2024, 7, 12),
				pet,
				1L);
		produtoRepository.save(coleira);

		Produto geladeira = new Produto(
				"geladeira.png",
				"Geladeira",
				"Geladeira frost-free, capacidade 300L",
				"novo",
				BigDecimal.valueOf(1999.99),
				LocalDate.of(2024, 7, 12),
				eletronicos,
				1L);
		produtoRepository.save(geladeira);

		Produto livro = new Produto(
				"livro.png",
				"Livro",
				"Best-seller de romance, 300 páginas",
				"usado",
				BigDecimal.valueOf(12.50),
				LocalDate.of(2023, 8, 15),
				livros,
				1L);
		produtoRepository.save(livro);

		celular = new Produto(
				"celular.png",
				"Celular",
				"Smartphone Iphone, IOS, 128GB",
				"novo",
				BigDecimal.valueOf(1499.99),
				LocalDate.of(2024, 7, 12),
				informatica,
				1L);
		produtoRepository.save(celular);

		Produto martelo = new Produto(
				"martelo.png",
				"Martelo",
				"Martelo de ferro, cabo de madeira",
				"novo",
				BigDecimal.valueOf(29.99),
				LocalDate.of(2024, 7, 12),
				ferramentas,
				1L);
		produtoRepository.save(martelo);

		Produto placaMae = new Produto(
				"placa.png",
				"Placa Mãe",
				"Placa mãe para processadores Intel, chipset XYZ",
				"novo",
				BigDecimal.valueOf(299.99),
				LocalDate.of(2024, 7, 12),
				eletronicos,
				1L);
		produtoRepository.save(placaMae);

		bola = new Produto(
				"bola.png",
				"Bola",
				"Bola de futebol tamanho padrão",
				"novo",
				BigDecimal.valueOf(29.99),
				LocalDate.of(2024, 7, 12),
				brinquedos,
				3L);
		produtoRepository.save(bola);

		bola = new Produto(
				"bola.png",
				"Bola",
				"Bola de futebol tamanho padrão",
				"novo",
				BigDecimal.valueOf(29.99),
				LocalDate.of(2024, 7, 12),
				brinquedos,
				1L);
		produtoRepository.save(bola);

	}
}
