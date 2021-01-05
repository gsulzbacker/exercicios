package com.gabrielsulzbacker.coreengineering.tema8.service;

import com.gabrielsulzbacker.coreengineering.tema8.dao.LivroDAO;
import com.gabrielsulzbacker.coreengineering.tema8.exception.LivroException;
import com.gabrielsulzbacker.coreengineering.tema8.exception.UsuarioException;
import com.gabrielsulzbacker.coreengineering.tema8.modelo.Livro;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LivroService {

	private LivroDAO livroDao = new LivroDAO();
	private List<Livro> listaLivros;

	public LivroService() {
		listaLivros = new ArrayList<Livro>();
	}

	public boolean cadastrarLivro(Livro livro) {
		if (livro == null) {
			throw new LivroException("Favor informar um livro a ser cadastrado.");
		}
		listaLivros.forEach(livroCadastrado -> {
			if (livroCadastrado.equals(livro))
				throw new LivroException("O livro já está cadastrado.");
		});
		listaLivros.add(livro);
		livroDao.salvarArquivo(listaLivros);
		return true;
	}

	public List<Livro> listarLivros() {
		return listaLivros;
	}

	public boolean removerLivro(Livro livro) {
		if (!listaLivros.contains(livro)) {
			throw new LivroException("O livro não foi localizado.");
		}
		if (livro.getStatus() == true) {
			throw new LivroException("O livro está emprestado e não pode ser excluído.");
		}
		listaLivros.remove(livro);
		livroDao.salvarArquivo(listaLivros);
		return true;
	}

	public Optional<Livro> consultarLivroPorNome(String nome) {
		Optional<Livro> LivroProcurado = listaLivros.stream().filter(livro -> livro.getNome().contains(nome)).findAny();

		if (!LivroProcurado.isPresent()) {
			throw new LivroException("O livro não foi localizado.");
		}
		return LivroProcurado;
	}

	public List<Livro> consultarLivroPorAutor(String autor) {
		List<Livro> listaAutor = listaLivros.stream().filter(livro -> livro.getAutor().contains(autor))
				.collect(Collectors.toList());
		if (listaAutor.isEmpty()) {
			throw new LivroException("Nenhum livro desse autor foi localizado.");
		}
		return listaAutor;
	}

	public Optional<Livro> consultarLivroPeloId(Integer id) {
		Optional<Livro> livroProcurado = listaLivros.stream().filter(livro -> livro.getId().equals(id)).findAny();
		if (!livroProcurado.isPresent()) {
			throw new LivroException("O livro não foi localizado.");
		}
		return livroProcurado;
	}
}