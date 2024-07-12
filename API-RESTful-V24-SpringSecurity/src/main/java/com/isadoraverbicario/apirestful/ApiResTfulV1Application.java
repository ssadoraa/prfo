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
		
		Categoria fruta = new Categoria("Frutas", "frutas");
		categoriaRepository.save(fruta);
		
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
		
	
	}
}
