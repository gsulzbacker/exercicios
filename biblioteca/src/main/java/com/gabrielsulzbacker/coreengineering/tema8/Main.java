package com.gabrielsulzbacker.coreengineering.tema8;

import com.gabrielsulzbacker.coreengineering.tema8.modelo.*;
import com.gabrielsulzbacker.coreengineering.tema8.service.*;

public class Main {

	public static void main(String[] args) {

		UsuarioService usuario = new UsuarioService();
		LivroService livro = new LivroService();
		EmprestimoService emprestimo = new EmprestimoService();
		RelatorioService relatorio = new RelatorioService(usuario, emprestimo);
		
		Usuario usuario1 = new Usuario(1, "Gabriel Sulzbacker");
		Usuario usuario2 = new Usuario(2, "Tiago Gonçalves");
		Usuario usuario3 = new Usuario(3, "Giordan Betat");
		Usuario usuario4 = new Usuario(4, "Alice Nascimento");
		Usuario usuario5 = new Usuario(5, "Debora Amorim");
		Usuario usuario6 = new Usuario(6, "Gabriela Kersul");
		Usuario usuario7 = new Usuario(7, "Suane Vallim");
		Usuario usuario8 = new Usuario(8, "Eduarda Trindade");
		Usuario usuario9 = new Usuario(9, "Caio Nilson");
		Usuario usuario10 = new Usuario(10, "Bruno de Lima");
		
		usuario.cadastrarUsuario(usuario1);
		usuario.cadastrarUsuario(usuario2);
		usuario.cadastrarUsuario(usuario3);
		usuario.cadastrarUsuario(usuario4);
		usuario.cadastrarUsuario(usuario5);
		usuario.cadastrarUsuario(usuario6);
		usuario.cadastrarUsuario(usuario7);
		usuario.cadastrarUsuario(usuario8);
		usuario.cadastrarUsuario(usuario9);
		usuario.cadastrarUsuario(usuario10);	
		
		usuario.removerUsuario(usuario4);
		usuario.removerUsuario(usuario8);	
		
		System.out.println("\n USUÁRIOS CADASTRADOS:\n" + usuario.listarUsuarios());

		Livro livro1 = new Livro(1, "O poder do agora", "Eckhart Tolle");
		Livro livro2 = new Livro(2, "Mindset", "Carol Dweck");
		Livro livro3 = new Livro(3, "O monge e o executivo", "James Hunter");
		Livro livro4 = new Livro(4, "O segredo", "Rhonda Byrne");
		Livro livro5 = new Livro(5, "Inteligência emocional", "Daniel Goleman");
		Livro livro6 = new Livro(6, "Em busca do sentido", "Viktor Frankl");
		Livro livro7 = new Livro(7, "Você pode curar sua vida", "Louise Hay");
		Livro livro8 = new Livro(8, "O maior vendedor do mundo", "Og Mandino");
		Livro livro9 = new Livro(9, "Essencialismo", "Greg McKeown");
		Livro livro10 = new Livro(10, "O poder do hábito", "Charles Duhigg");

		livro.cadastrarLivro(livro1);
		livro.cadastrarLivro(livro2);
		livro.cadastrarLivro(livro3);
		livro.cadastrarLivro(livro4);
		livro.cadastrarLivro(livro5);
		livro.cadastrarLivro(livro6);
		livro.cadastrarLivro(livro7);
		livro.cadastrarLivro(livro8);
		livro.cadastrarLivro(livro9);
		livro.cadastrarLivro(livro10);
		
		livro.removerLivro(livro3);
		livro.removerLivro(livro7);
		
		System.out.println("\nLIVROS CADASTRADOS:\n" + livro.listarLivros());
				
		emprestimo.emprestarLivro(usuario9, livro1);
		emprestimo.emprestarLivro(usuario9, livro2);
		emprestimo.emprestarLivro(usuario9, livro3);
		emprestimo.emprestarLivro(usuario9, livro4);
		emprestimo.emprestarLivro(usuario8, livro5);
		emprestimo.emprestarLivro(usuario8, livro6);
		emprestimo.emprestarLivro(usuario8, livro7);
		emprestimo.emprestarLivro(usuario7, livro8);
		emprestimo.emprestarLivro(usuario7, livro9);
		
		emprestimo.devolverLivro(usuario9, livro3);
		emprestimo.devolverLivro(usuario8, livro7);
		emprestimo.devolverLivro(usuario7, livro9);
		
		emprestimo.renovarEmprestimo(usuario9, livro4);
		emprestimo.renovarEmprestimo(usuario7, livro8);		
		
		System.out.println("\nRELATÓRIO DE USUÁRIOS TOP 10:\n" + relatorio.listarUsuariosTop10());

	}
}
