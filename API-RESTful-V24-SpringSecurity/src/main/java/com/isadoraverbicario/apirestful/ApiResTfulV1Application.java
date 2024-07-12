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

		Usuario user = usuarioRepository.findByUsername("user");
		if (user == null) {
			Usuario usuario = new Usuario();
			usuario.setUsername("user");
			usuario.setPassword(passwordEncoder.encode("12345"));
			usuario.setRole(Role.USER);
			usuario.setAccountNonLocked(true);
			usuario.setCredentialsNonExpired(true);
			usuario.setAccountNonExpired(true);
			usuario.setEnabled(true);

			usuarioRepository.save(usuario);
		}

		Categoria fruta = new Categoria("Frutas", "frutas");
		categoriaRepository.save(fruta);

		Categoria legume = new Categoria("Legumes", "legumes");
		categoriaRepository.save(legume);

		Categoria verdura = new Categoria("Verduras", "verduras");
		categoriaRepository.save(verdura);

		Produto produto = new Produto(
				"abacate.png",
				"Abacate",
				"1 unidade aprox. 750g",
				"Usado",
				"Ativo",
				BigDecimal.valueOf(2.45),
				LocalDate.of(2023, 4, 26),
				fruta,
				1L);
		produtoRepository.save(produto);
		
		produto = new Produto(
				"abobrinha.jpg",
				"Abacate",
				"1 unidade aprox. 750g",
				"Usado",
				"Ativo",
				BigDecimal.valueOf(2.45),
				LocalDate.of(2023, 4, 26),
				fruta,
				2L);
		produtoRepository.save(produto);
		
		produto = new Produto(
				"abacate.png",
				"Abacate",
				"1 unidade aprox. 750g",
				"Usado",
				"Ativo",
				BigDecimal.valueOf(2.45),
				LocalDate.of(2023, 4, 26),
				fruta,
				1L);
		produtoRepository.save(produto);
		produto = new Produto(
				"abacate.png",
				"Abacate",
				"1 unidade aprox. 750g",
				"Usado",
				"Ativo",
				BigDecimal.valueOf(2.45),
				LocalDate.of(2023, 4, 26),
				fruta,
				1L);
		produtoRepository.save(produto);
		produto = new Produto(
				"abacate.png",
				"Abacate",
				"1 unidade aprox. 750g",
				"Usado",
				"Ativo",
				BigDecimal.valueOf(2.45),
				LocalDate.of(2023, 4, 26),
				fruta,
				1L);
		produtoRepository.save(produto);
		produto = new Produto(
				"abacate.png",
				"Abacate",
				"1 unidade aprox. 750g",
				"Usado",
				"Ativo",
				BigDecimal.valueOf(2.45),
				LocalDate.of(2023, 4, 26),
				fruta,
				1L);
		produtoRepository.save(produto);
		produto = new Produto(
				"abacate.png",
				"Abacate",
				"1 unidade aprox. 750g",
				"Usado",
				"Ativo",
				BigDecimal.valueOf(2.45),
				LocalDate.of(2023, 4, 26),
				fruta,
				1L);
		produtoRepository.save(produto);
		produto = new Produto(
				"abacate.png",
				"Abacate",
				"1 unidade aprox. 750g",
				"Usado",
				"Ativo",
				BigDecimal.valueOf(2.45),
				LocalDate.of(2023, 4, 26),
				fruta,
				1L);
		produtoRepository.save(produto);
		produto = new Produto(
				"abacate.png",
				"Abacate",
				"1 unidade aprox. 750g",
				"Usado",
				"Ativo",
				BigDecimal.valueOf(2.45),
				LocalDate.of(2023, 4, 26),
				fruta,
				1L);
		produtoRepository.save(produto);
		produto = new Produto(
				"abacate.png",
				"Abacate",
				"1 unidade aprox. 750g",
				"Usado",
				"Ativo",
				BigDecimal.valueOf(2.45),
				LocalDate.of(2023, 4, 26),
				fruta,
				1L);
		produtoRepository.save(produto);
		produto = new Produto(
				"abacate.png",
				"Abacate",
				"1 unidade aprox. 750g",
				"Usado",
				"Ativo",
				BigDecimal.valueOf(2.45),
				LocalDate.of(2023, 4, 26),
				fruta,
				1L);
		produtoRepository.save(produto);
		produto = new Produto(
				"abacate.png",
				"Abacate",
				"1 unidade aprox. 750g",
				"Usado",
				"Ativo",
				BigDecimal.valueOf(2.45),
				LocalDate.of(2023, 4, 26),
				fruta,
				1L);
		produtoRepository.save(produto);
		produto = new Produto(
				"abacate.png",
				"Abacate",
				"1 unidade aprox. 750g",
				"Usado",
				"Ativo",
				BigDecimal.valueOf(2.45),
				LocalDate.of(2023, 4, 26),
				fruta,
				1L);
		produtoRepository.save(produto);
		produto = new Produto(
				"abacate.png",
				"Abacate",
				"1 unidade aprox. 750g",
				"Usado",
				"Ativo",
				BigDecimal.valueOf(2.45),
				LocalDate.of(2023, 4, 26),
				fruta,
				1L);
		produtoRepository.save(produto);
		produto = new Produto(
				"abacate.png",
				"Abacate",
				"1 unidade aprox. 750g",
				"Usado",
				"Ativo",
				BigDecimal.valueOf(2.45),
				LocalDate.of(2023, 4, 26),
				fruta,
				1L);
		produtoRepository.save(produto);
		produto = new Produto(
				"abacate.png",
				"Abacate",
				"1 unidade aprox. 750g",
				"Usado",
				"Ativo",
				BigDecimal.valueOf(2.45),
				LocalDate.of(2023, 4, 26),
				fruta,
				1L);
		produtoRepository.save(produto);
		produto = new Produto(
				"abacate.png",
				"Abacate",
				"1 unidade aprox. 750g",
				"Usado",
				"Ativo",
				BigDecimal.valueOf(2.45),
				LocalDate.of(2023, 4, 26),
				fruta,
				1L);
		produtoRepository.save(produto);
		produto = new Produto(
				"abacate.png",
				"Abacate",
				"1 unidade aprox. 750g",
				"Usado",
				"Ativo",
				BigDecimal.valueOf(2.45),
				LocalDate.of(2023, 4, 26),
				fruta,
				1L);
		produtoRepository.save(produto);
		produto = new Produto(
				"abacate.png",
				"Abacate",
				"1 unidade aprox. 750g",
				"Usado",
				"Ativo",
				BigDecimal.valueOf(2.45),
				LocalDate.of(2023, 4, 26),
				fruta,
				1L);
		produtoRepository.save(produto);
		produto = new Produto(
				"abacate.png",
				"Abacate",
				"1 unidade aprox. 750g",
				"Usado",
				"Ativo",
				BigDecimal.valueOf(2.45),
				LocalDate.of(2023, 4, 26),
				fruta,
				1L);
		produtoRepository.save(produto);
		produto = new Produto(
				"abacate.png",
				"Abacate",
				"1 unidade aprox. 750g",
				"Usado",
				"Ativo",
				BigDecimal.valueOf(2.45),
				LocalDate.of(2023, 4, 26),
				fruta,
				1L);
		produtoRepository.save(produto);
	}
}
