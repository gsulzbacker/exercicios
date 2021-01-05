package com.gabrielsulzbacker.cloudnative.tema5.service;

import com.gabrielsulzbacker.cloudnative.tema5.dominio.*;
import com.gabrielsulzbacker.cloudnative.tema5.exception.PedagioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import java.util.Map;
import java.util.HashMap;

public class PedagioService {

	@Autowired
	private ApplicationContext context;

	public double efetuarPagamento(VeiculoType veiculo, Double valor, Integer eixos) {

		if (veiculo == null || valor == null || eixos == null) {
			throw new PedagioException("Valores nulos: todos os campos s�o de preenchimento obrigat�rio!");
		} else if (!context.containsBean(veiculo.toString())) {
			throw new PedagioException(
					"Ve�culo inv�lido: favor informar um dos ve�culos dispon�veis: ONIBUS, MOTO, BICICLETA, CAMINHAO ou FUSCA.");
		} else if (veiculo.equals(VeiculoType.CAMINHAO) && eixos <= 0) {
			throw new PedagioException("Eixos inv�lidos: favor informar a quantidade de eixos do caminh�o.");
		}

		Veiculo veic = context.getBean(veiculo.toString(), Veiculo.class);
		double preco = veic.getPreco();

		if (veiculo.equals(VeiculoType.CAMINHAO)) {
			Caminhao caminhao = (Caminhao) veic;
			preco = caminhao.getPreco() + (caminhao.getAdicional() * eixos);
		}

		if (valor < preco) {
			throw new PedagioException(
					"Valor insuficiente (" + valor + ")! Favor efetuar o pagamento integral do ped�gio (" + preco
							+ "). Diferen�a: " + (preco - valor));
		}

		return valor - preco;
	}

	public Map<String, Double> listarPrecos() {
		Map<String, Double> listaPrecos = new HashMap<>();

		for (VeiculoType veic : VeiculoType.values()) {
			Veiculo veiculo = context.getBean(veic.toString(), Veiculo.class);
			listaPrecos.put(veic.toString(), veiculo.getPreco());
		}

		return listaPrecos;
	}
}
