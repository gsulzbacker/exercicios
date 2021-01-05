package com.gabrielsulzbacker.coreengineering.tema8.service;

import com.gabrielsulzbacker.coreengineering.tema8.modelo.Emprestimo;
import com.gabrielsulzbacker.coreengineering.tema8.modelo.Usuario;
import com.gabrielsulzbacker.coreengineering.tema8.service.UsuarioService;
import com.gabrielsulzbacker.coreengineering.tema8.service.EmprestimoService;
import java.util.List;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.stream.Collectors;

public class RelatorioService {

	UsuarioService usuarioService = new UsuarioService();
	EmprestimoService emprestimoService = new EmprestimoService();

	public RelatorioService(UsuarioService usuarioService, EmprestimoService emprestimoService) {
		this.usuarioService = usuarioService;
		this.emprestimoService = emprestimoService;
	}

	public List<Emprestimo> listarEmprestimos() {
		List<Emprestimo> listaEmprestimos = emprestimoService.listarEmprestimos();
		return listaEmprestimos;
	}

	public String listarUsuariosTop10() {
		StringBuilder relatorioTop10 = new StringBuilder();
		List<Usuario> listaUsuarios = usuarioService.listarUsuarios();
		listaUsuarios.stream().sorted(Comparator.comparing(Usuario::getEmprestimos).reversed()).limit(10)
				.collect(Collectors.toList()).forEach(l -> relatorioTop10.append(l));

		return relatorioTop10.toString();
	}

	public List<Emprestimo> listarAtrasados() {
		List<Emprestimo> listaEmprestimos = emprestimoService.listarEmprestimos();
		List<Emprestimo> listaAtrasados = new ArrayList<Emprestimo>();
		for (Emprestimo emprestimo : listaEmprestimos) {
			long numDays = ChronoUnit.DAYS.between(emprestimo.getDataEmprestimo(), LocalDate.now());
			if (numDays > 7) {
				listaAtrasados.add(emprestimo);
			}
		}
		return listaAtrasados;
	}
}