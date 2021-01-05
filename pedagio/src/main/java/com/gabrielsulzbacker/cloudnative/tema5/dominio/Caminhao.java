package com.gabrielsulzbacker.cloudnative.tema5.dominio;

public class Caminhao implements Veiculo {

	double adicionalPorEixo = 1.50;
	
	@Override
	public double getPreco() {
		return 3.95;
	}
	
	public double getAdicional() {
		return adicionalPorEixo;
	}

}