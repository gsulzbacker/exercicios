package com.gabrielsulzbacker.coreengineering.tema8.modelo;

import java.time.LocalDate;

public class Emprestimo {

	private Integer idUsuario;
	private Integer idLivro;
	private LocalDate dataEmprestimo;
	private LocalDate dataDevolucao;
	private double multaAtraso;

	public Emprestimo(Integer idUsuario, Integer idLivro, LocalDate dataEmprestimo) {
		this.idUsuario = idUsuario;
		this.idLivro = idLivro;
		this.dataEmprestimo = dataEmprestimo;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public Integer getIdLivro() {
		return idLivro;
	}

	public LocalDate getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(LocalDate dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public double getMultaAtraso() {
		return multaAtraso;
	}

	public void setMultaAtraso(double multaAtraso) {
		this.multaAtraso = multaAtraso;
	}

	@Override
	public String toString() {
		return "ID do usu�rio: " + idUsuario + " / ID do livro: " + idLivro + " / Data do empr�stimo: " + dataEmprestimo
				+ " / Data da devolu��o: " + dataDevolucao + "\n";
	}
}