package com.gabrielsulzbacker.cloudnative.tema2.dominio;

public class Servico {

	private ServicoType servicoType;
	private String nome;
	private Double preco;

	public Servico(ServicoType servicoType) {
		this.servicoType = servicoType;
	}

	public void efetuar() {
		switch (servicoType) {
		case BANHO:
			nome = "Banho";
			preco = 40.00;
			break;
		case BANHO_PERFUMADO:
			nome = "Banho com perfume";
			preco = 45.00;
			break;
		case BANHO_SECO:
			nome = "Banho a seco";
			preco = 30.00;
			break;
		case BANHO_SECO_PERFUMADO:
			nome = "Banho a seco com perfume";
			preco = 35.00;
			break;
		case TOSA_CURTA:
			nome = "Tosa curta";
			preco = 50.00;
			break;
		case TOSA_LONGA:
			nome = "Tosa longa";
			preco = 60.00;
			break;
		}
	}
	
	public double getPreco() {
		return preco;
	}

	@Override
	public String toString() {
		return "Serviço: " + nome + " / Preço: " + preco + "\n";
	}

}