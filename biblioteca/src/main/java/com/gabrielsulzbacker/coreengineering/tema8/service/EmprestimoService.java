package com.gabrielsulzbacker.coreengineering.tema8.service;

import com.gabrielsulzbacker.coreengineering.tema8.dao.EmprestimoDAO;
import com.gabrielsulzbacker.coreengineering.tema8.exception.EmprestimoException;
import com.gabrielsulzbacker.coreengineering.tema8.modelo.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoService {

	private EmprestimoDAO emprestimoDao = new EmprestimoDAO();
	private List<Emprestimo> listaEmprestimos;

	public EmprestimoService() {
		this.listaEmprestimos = new ArrayList<Emprestimo>();
	}

	public boolean emprestarLivro(Usuario usuario, Livro livro) {
		
		if (livro.getStatus() == true) {
			throw new EmprestimoException("O livro está emprestado.");
		}
		if (usuario.getLivros() == 5) {
			throw new EmprestimoException("O usuário atingiu o limite máximo de empréstimos ativos.");
		}
		listaEmprestimos.add(new Emprestimo(usuario.getId(), livro.getId(), LocalDate.now()));
		livro.setStatus(true);
		usuario.setLivros(usuario.getLivros() + 1);
		usuario.setEmprestimos(usuario.getEmprestimos() + 1);
		emprestimoDao.salvarArquivo(listaEmprestimos);
		return true;
	}

	public boolean devolverLivro(Usuario usuario, Livro livro) {
		for (Emprestimo emprestimo : listaEmprestimos) {
			if (emprestimo.getIdUsuario().equals(usuario.getId()) && emprestimo.getIdLivro().equals(livro.getId())) {
				emprestimo.setDataDevolucao(LocalDate.now());
				emprestimo.setMultaAtraso(calcularMulta(emprestimo));
				if (emprestimo.getMultaAtraso() > 0.00) {
					throw new EmprestimoException(
							"Devolução efetuada com atraso. Multa a pagar: " + emprestimo.getMultaAtraso());
				}
				livro.setStatus(false);
				usuario.setLivros(usuario.getLivros() - 1);
				emprestimoDao.salvarArquivo(listaEmprestimos);
			}
		}
		return true;
	}

	public boolean renovarEmprestimo(Usuario usuario, Livro livro) {
		for (Emprestimo emprestimo : listaEmprestimos) {
			if (emprestimo.getIdUsuario().equals(usuario.getId()) && emprestimo.getIdLivro().equals(livro.getId())) {
				emprestimo.setDataDevolucao(LocalDate.now());
				emprestimo.setMultaAtraso(calcularMulta(emprestimo));
				if (emprestimo.getMultaAtraso() > 0.00) {
					throw new EmprestimoException("Renovação não efetuada! O usuário possui uma multa em aberto: "
							+ emprestimo.getMultaAtraso());
				}
				emprestimo.setDataEmprestimo(LocalDate.now());
				emprestimoDao.salvarArquivo(listaEmprestimos);
			}
		}
		return true;
	}

	public double calcularMulta(Emprestimo emprestimo) {
		double multa = 0.00;
		long diasAtraso = ChronoUnit.DAYS.between(emprestimo.getDataEmprestimo(), emprestimo.getDataDevolucao());
		if (diasAtraso > 7) {
			multa = (diasAtraso - 7) * 5.00;
		}
		return multa;
	}

	public List<Emprestimo> listarEmprestimos() {
		return listaEmprestimos;
	}

}