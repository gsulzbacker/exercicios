package com.gabrielsulzbacker.cloudnative.tema2.dominio;

public class Pet {

	private Integer id;
	private String nome;
	private String raca;
	private Integer idade;
	private double consumo;

	public Pet(Integer id, String nome, String raca, Integer idade) {
		this.id = id;
		this.nome = nome;
		this.raca = raca;
		this.idade = idade;
	}
	
	public Integer getId() {
		return id;
	}

	public Integer getIdade() {
		return idade;
	}

	public String getNome() {
		return nome;
	}

	public String getRaca() {
		return raca;
	}

	public double getConsumo() {
		return consumo;
	}

	public void setConsumo(Double consumo) {
		this.consumo = consumo;
	}

	@Override
	public String toString() {
		return "ID: " + id + " / Nome: " + nome + " / Raça: " + raca + " / Idade: " + idade + " / Total consumido: R$ " + consumo + "\n";
	}

}