package com.gabrielsulzbacker.coreengineering.tema8.modelo;

public class Livro {

	private Integer id;
	private String nome;
	private String autor;
	private boolean status;

	public Livro(Integer id, String nome, String autor) {
		this.id = id;
		this.nome = nome;
		this.autor = autor;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getAutor() {
		return autor;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ID: " + id + " / Nome: " + nome + " / Autor: " + autor + "\n";
	}
}