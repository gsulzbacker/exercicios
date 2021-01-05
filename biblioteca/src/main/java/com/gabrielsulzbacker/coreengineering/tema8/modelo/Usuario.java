package com.gabrielsulzbacker.coreengineering.tema8.modelo;

public class Usuario {

	private Integer id;
	private String nome;
	private Integer livros = 0;
	private Integer emprestimos = 0;

	public Usuario(Integer id, String nome) {
		this.nome = nome;
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Integer getLivros() {
		return livros;
	}

	public void setLivros(Integer livros) {
		this.livros = livros;
	}

	public Integer getEmprestimos() {
		return emprestimos;
	}

	public void setEmprestimos(Integer emprestimos) {
		this.emprestimos = emprestimos;
	}

	@Override
	public String toString() {
		return "ID: " + id + " / Nome: " + nome + " / Livros: " + livros + " / Empréstimos: " + emprestimos + "\n";
	}
}