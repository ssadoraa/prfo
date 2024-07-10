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
				true,
				100,
				BigDecimal.valueOf(2.45),
				LocalDate.of(2023, 4, 26),
				fruta);
		produtoRepository.save(produto);

		produto = new Produto(
				"abobrinha.png",
				"Abobrinha",
				"1 unidade aprox. 250g",
				false,
				500,
				BigDecimal.valueOf(1.1),
				LocalDate.of(2023, 5, 22),
				legume);
		produtoRepository.save(produto);

		produto = new Produto(
				"abobora.png",
				"Abóbora",
				"1 unidade aprox. 1,9kg",
				true,
				400,
				BigDecimal.valueOf(4.7),
				LocalDate.of(2023, 3, 24),
				legume);
		produtoRepository.save(produto);

		produto = new Produto(
				"acelga.png",
				"Acelga",
				"1 maço de aprox. 400g",
				true,
				120,
				BigDecimal.valueOf(4.99),
				LocalDate.of(2023, 3, 12),
				verdura);
		produtoRepository.save(produto);

		produto = new Produto(
				"agriao.png",
				"Agrião",
				"1 maço de aprox. 200g",
				true,
				340,
				BigDecimal.valueOf(2.5),
				LocalDate.of(2023, 5, 17),
				verdura);
		produtoRepository.save(produto);

		produto = new Produto(
				"alface.png",
				"Alface",
				"1 maço de aprox. 200g",
				true,
				220,
				BigDecimal.valueOf(4.99),
				LocalDate.of(2023, 5, 14),
				verdura);
		produtoRepository.save(produto);

		produto = new Produto(
				"banana.png",
				"Banana",
				"1 unidade aprox. 165g",
				true,
				350,
				BigDecimal.valueOf(1.05),
				LocalDate.of(2023, 2, 22),
				fruta);
		produtoRepository.save(produto);

		produto = new Produto(
				"beringela.png",
				"Beringela",
				"1 unidade aprox. 370g",
				true,
				720,
				BigDecimal.valueOf(1.85),
				LocalDate.of(2023, 2, 23),
				legume);
		produtoRepository.save(produto);

		produto = new Produto(
				"brocolis.png",
				"Brócolis",
				"1 unidade aprox. 300g",
				true,
				600,
				BigDecimal.valueOf(5.39),
				LocalDate.of(2023, 3, 28),
				verdura);
		produtoRepository.save(produto);

		produto = new Produto(
				"cebola.png",
				"Cebola",
				"1 unidade aprox. 200g",
				true,
				95,
				BigDecimal.valueOf(0.56),
				LocalDate.of(2023, 4, 30),
				legume);
		produtoRepository.save(produto);

		produto = new Produto(
				"cenoura.png",
				"Cenoura",
				"1 unidade aprox. 180g",
				true,
				350,
				BigDecimal.valueOf(1.01),
				LocalDate.of(2023, 5, 29),
				legume);
		produtoRepository.save(produto);

		produto = new Produto(
				"cereja.png",
				"Cereja",
				"1 unidade aprox. 250g",
				true,
				240,
				BigDecimal.valueOf(11.23),
				LocalDate.of(2023, 5, 11),
				fruta);
		produtoRepository.save(produto);

		produto = new Produto(
				"abacate.png",
				"Abacate",
				"1 unidade aprox. 750g",
				true,
				100,
				BigDecimal.valueOf(2.45),
				LocalDate.of(2023, 4, 26),
				fruta);
		produtoRepository.save(produto);

		produto = new Produto(
				"abobrinha.png",
				"Abobrinha",
				"1 unidade aprox. 250g",
				false,
				500,
				BigDecimal.valueOf(1.1),
				LocalDate.of(2023, 5, 22),
				legume);
		produtoRepository.save(produto);

		produto = new Produto(
				"abobora.png",
				"Abóbora",
				"1 unidade aprox. 1,9kg",
				true,
				400,
				BigDecimal.valueOf(4.7),
				LocalDate.of(2023, 3, 24),
				legume);
		produtoRepository.save(produto);

		produto = new Produto(
				"acelga.png",
				"Acelga",
				"1 maço de aprox. 400g",
				true,
				120,
				BigDecimal.valueOf(4.99),
				LocalDate.of(2023, 3, 12),
				verdura);
		produtoRepository.save(produto);

		produto = new Produto(
				"agriao.png",
				"Agrião",
				"1 maço de aprox. 200g",
				true,
				340,
				BigDecimal.valueOf(2.5),
				LocalDate.of(2023, 5, 17),
				verdura);
		produtoRepository.save(produto);

		produto = new Produto(
				"alface.png",
				"Alface",
				"1 maço de aprox. 200g",
				true,
				220,
				BigDecimal.valueOf(4.99),
				LocalDate.of(2023, 5, 14),
				verdura);
		produtoRepository.save(produto);

		produto = new Produto(
				"banana.png",
				"Banana",
				"1 unidade aprox. 165g",
				true,
				350,
				BigDecimal.valueOf(1.05),
				LocalDate.of(2023, 2, 22),
				fruta);
		produtoRepository.save(produto);

		produto = new Produto(
				"beringela.png",
				"Beringela",
				"1 unidade aprox. 370g",
				true,
				720,
				BigDecimal.valueOf(1.85),
				LocalDate.of(2023, 2, 23),
				legume);
		produtoRepository.save(produto);

		produto = new Produto(
				"brocolis.png",
				"Brócolis",
				"1 unidade aprox. 300g",
				true,
				600,
				BigDecimal.valueOf(5.39),
				LocalDate.of(2023, 3, 28),
				verdura);
		produtoRepository.save(produto);

		produto = new Produto(
				"cebola.png",
				"Cebola",
				"1 unidade aprox. 200g",
				true,
				95,
				BigDecimal.valueOf(0.56),
				LocalDate.of(2023, 4, 30),
				legume);
		produtoRepository.save(produto);

		produto = new Produto(
				"cenoura.png",
				"Cenoura",
				"1 unidade aprox. 180g",
				true,
				350,
				BigDecimal.valueOf(1.01),
				LocalDate.of(2023, 5, 29),
				legume);
		produtoRepository.save(produto);

		produto = new Produto(
				"cereja.png",
				"Cereja",
				"1 unidade aprox. 250g",
				true,
				240,
				BigDecimal.valueOf(11.23),
				LocalDate.of(2023, 5, 11),
				fruta);
		produtoRepository.save(produto);

		produto = new Produto(
				"abacate.png",
				"Abacate",
				"1 unidade aprox. 750g",
				true,
				100,
				BigDecimal.valueOf(2.45),
				LocalDate.of(2023, 4, 26),
				fruta);
		produtoRepository.save(produto);

		produto = new Produto(
				"abobrinha.png",
				"Abobrinha",
				"1 unidade aprox. 250g",
				false,
				500,
				BigDecimal.valueOf(1.1),
				LocalDate.of(2023, 5, 22),
				legume);
		produtoRepository.save(produto);

		produto = new Produto(
				"abobora.png",
				"Abóbora",
				"1 unidade aprox. 1,9kg",
				true,
				400,
				BigDecimal.valueOf(4.7),
				LocalDate.of(2023, 3, 24),
				legume);
		produtoRepository.save(produto);

		produto = new Produto(
				"acelga.png",
				"Acelga",
				"1 maço de aprox. 400g",
				true,
				120,
				BigDecimal.valueOf(4.99),
				LocalDate.of(2023, 3, 12),
				verdura);
		produtoRepository.save(produto);

		produto = new Produto(
				"agriao.png",
				"Agrião",
				"1 maço de aprox. 200g",
				true,
				340,
				BigDecimal.valueOf(2.5),
				LocalDate.of(2023, 5, 17),
				verdura);
		produtoRepository.save(produto);

		produto = new Produto(
				"alface.png",
				"Alface",
				"1 maço de aprox. 200g",
				true,
				220,
				BigDecimal.valueOf(4.99),
				LocalDate.of(2023, 5, 14),
				verdura);
		produtoRepository.save(produto);

		produto = new Produto(
				"banana.png",
				"Banana",
				"1 unidade aprox. 165g",
				true,
				350,
				BigDecimal.valueOf(1.05),
				LocalDate.of(2023, 2, 22),
				fruta);
		produtoRepository.save(produto);

		produto = new Produto(
				"beringela.png",
				"Beringela",
				"1 unidade aprox. 370g",
				true,
				720,
				BigDecimal.valueOf(1.85),
				LocalDate.of(2023, 2, 23),
				legume);
		produtoRepository.save(produto);

		produto = new Produto(
				"brocolis.png",
				"Brócolis",
				"1 unidade aprox. 300g",
				true,
				600,
				BigDecimal.valueOf(5.39),
				LocalDate.of(2023, 3, 28),
				verdura);
		produtoRepository.save(produto);

		produto = new Produto(
				"cebola.png",
				"Cebola",
				"1 unidade aprox. 200g",
				true,
				95,
				BigDecimal.valueOf(0.56),
				LocalDate.of(2023, 4, 30),
				legume);
		produtoRepository.save(produto);

		produto = new Produto(
				"cenoura.png",
				"Cenoura",
				"1 unidade aprox. 180g",
				true,
				350,
				BigDecimal.valueOf(1.01),
				LocalDate.of(2023, 5, 29),
				legume);
		produtoRepository.save(produto);

		produto = new Produto(
				"cereja.png",
				"Cereja",
				"1 unidade aprox. 250g",
				true,
				240,
				BigDecimal.valueOf(11.23),
				LocalDate.of(2023, 5, 11),
				fruta);
		produtoRepository.save(produto);
	}
}
