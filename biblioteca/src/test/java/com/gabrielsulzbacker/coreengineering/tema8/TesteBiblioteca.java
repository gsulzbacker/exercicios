package com.gabrielsulzbacker.coreengineering.tema8;

import com.gabrielsulzbacker.coreengineering.tema8.exception.*;
import com.gabrielsulzbacker.coreengineering.tema8.modelo.*;
import com.gabrielsulzbacker.coreengineering.tema8.service.*;
import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDate;
import java.util.Optional;

public class TesteBiblioteca {

	private UsuarioService usuario = new UsuarioService();
	private LivroService livro = new LivroService();
	private EmprestimoService emprestimo = new EmprestimoService();
	
	Usuario usuario1 = new Usuario(1, "Gabriel Sulzbacker");
	Usuario usuario2 = new Usuario(2, "Tiago Gonçalves");
	Usuario usuario3 = new Usuario(3, "Giordan Betat");
	
	Livro livro1 = new Livro(1, "O poder do agora", "Eckhart Tolle");
	Livro livro2 = new Livro(2, "Mindset", "Carol Dweck");
	Livro livro3 = new Livro(3, "O monge e o executivo", "James Hunter");
	Livro livro4 = new Livro(4, "O segredo", "Rhonda Byrne");
	Livro livro5 = new Livro(5, "Inteligência emocional", "Daniel Goleman");
	Livro livro6 = new Livro(6, "Em busca do sentido", "Viktor Frankl");
	
	@Test
	public void cadastrarUsuario() {
		usuario.cadastrarUsuario(usuario2);
		usuario.cadastrarUsuario(usuario3);		
		Assert.assertTrue(usuario.listarUsuarios().contains(usuario2));
		Assert.assertFalse(usuario.listarUsuarios().contains(usuario1));
	}
	
	@Test(expected = UsuarioException.class)
	public void cadastrarUsuarioEmBranco() {
		usuario.cadastrarUsuario(null);	
	}
	
	@Test(expected = UsuarioException.class)
	public void cadastrarUsuarioDuplicado() {
		usuario.cadastrarUsuario(usuario3);
		usuario.cadastrarUsuario(usuario3);		
	}	
	
	@Test
	public void cadastrarLivro() {
		livro.cadastrarLivro(livro1);
		livro.cadastrarLivro(livro2);		
		Assert.assertTrue(livro.listarLivros().contains(livro1));
		Assert.assertFalse(livro.listarLivros().contains(livro3));
	}
	
	@Test(expected = LivroException.class)
	public void cadastrarLivroEmBranco() {
		livro.cadastrarLivro(null);	
	}
	
	@Test(expected = LivroException.class)
	public void cadastrarLivroDuplicado() {
		livro.cadastrarLivro(livro2);
		livro.cadastrarLivro(livro2);		
	}	
	
	@Test(expected = LivroException.class)
	public void removerLivroEmprestado() {
		usuario.cadastrarUsuario(usuario1);
		livro.cadastrarLivro(livro3);
		emprestimo.emprestarLivro(usuario1, livro3);	
		livro.removerLivro(livro3);
	}	
	
	@Test
	public void consultarLivroPorNome() {
		livro.cadastrarLivro(livro5);
		Assert.assertEquals(livro.consultarLivroPorNome("Inteligência emocional"), Optional.of(livro5));
	}
	
	@Test(expected = LivroException.class)
	public void consultarLivroPorNomeNaoExistente() {
		livro.cadastrarLivro(livro3);
		livro.consultarLivroPorNome("O executivo e o monge");
	}
	
	@Test
	public void emprestarLivro() {
		usuario.cadastrarUsuario(usuario3);
		livro.cadastrarLivro(livro3);
		Assert.assertTrue(emprestimo.emprestarLivro(usuario3, livro3));
	}
	
	@Test(expected = UsuarioException.class)
	public void fazerEmprestimoSemPreencherUsuario() {
		usuario.cadastrarUsuario(null);
		livro.cadastrarLivro(livro6);
		emprestimo.emprestarLivro(null, livro6);
	}
	
	@Test(expected = LivroException.class)
	public void fazerEmprestimoSemPreencherLivro() {
		usuario.cadastrarUsuario(usuario2);
		livro.cadastrarLivro(null);
		emprestimo.emprestarLivro(usuario2, null);
	}
	
	@Test(expected = EmprestimoException.class)
	public void emprestarLivroQueEstaEmprestado() {
		usuario.cadastrarUsuario(usuario1);
		usuario.cadastrarUsuario(usuario2);
		livro.cadastrarLivro(livro3);
		emprestimo.emprestarLivro(usuario1, livro3);
		emprestimo.emprestarLivro(usuario2, livro3);
	}
	
	@Test(expected = EmprestimoException.class)
	public void emprestarLivroParaUsuarioComMaximoDeLivros() {
		usuario.cadastrarUsuario(usuario1);
		livro.cadastrarLivro(livro1);
		livro.cadastrarLivro(livro2);
		livro.cadastrarLivro(livro3);
		livro.cadastrarLivro(livro4);
		livro.cadastrarLivro(livro5);
		livro.cadastrarLivro(livro6);
		emprestimo.emprestarLivro(usuario1, livro1);
		emprestimo.emprestarLivro(usuario1, livro2);
		emprestimo.emprestarLivro(usuario1, livro3);
		emprestimo.emprestarLivro(usuario1, livro4);
		emprestimo.emprestarLivro(usuario1, livro5);
		emprestimo.emprestarLivro(usuario1, livro6);
	}
	
	@Test
	public void renovarEmprestimo() {
		usuario.cadastrarUsuario(usuario3);
		livro.cadastrarLivro(livro2);
		emprestimo.emprestarLivro(usuario3, livro2);
		Assert.assertTrue(emprestimo.renovarEmprestimo(usuario3, livro2));
	}
	
	Emprestimo emprestimo1 = new Emprestimo(1, 1, LocalDate.now());
	
	@Test
	public void devolverLivro() {
		usuario.cadastrarUsuario(usuario2);
		livro.cadastrarLivro(livro1);
		emprestimo.emprestarLivro(usuario2, livro1);
		Assert.assertTrue(emprestimo.devolverLivro(usuario2, livro1));
	}
	
}
