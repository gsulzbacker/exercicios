package com.gabrielsulzbacker.cloudnative.tema2.service;

import com.gabrielsulzbacker.cloudnative.tema2.dominio.*;
import com.gabrielsulzbacker.cloudnative.tema2.exception.PetException;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Comparator;
import java.util.stream.Collectors;

public class PetService {

	private Map<Integer, Pet> listaPets;
	private Map<Integer, List<Servico>> listaServicos;

	public PetService() {
		this.listaPets = new HashMap<>();
		this.listaServicos = new HashMap<>();
	}

	public void adicionarPet(Pet pet) {
		if (pet.getId() == null || pet.getNome() == null || pet.getRaca() == null || pet.getIdade() == null)
			throw new PetException("Todos os dados do cadastro devem ser informados!");
		listaPets.put(pet.getId(), pet);
		listaServicos.put(pet.getId(), new ArrayList<>());
	}

	public void excluirPet(Integer id) {
		if (id == null)
			throw new PetException("O ID do Pet deve ser informado!");
		listaPets.remove(id);
	}

	public void efetuarServico(Integer id, ServicoType servicoType) {
		Pet pet = listaPets.get(id);
		Servico servico = new Servico(servicoType);
		servico.efetuar();
		pet.setConsumo(pet.getConsumo() + servico.getPreco());
		listaServicos.get(id).add(servico);
	}

	public List<Pet> buscaPorIdade(Integer idade) {
		if (idade == null)
			throw new PetException("A idade do pet deve ser informada!");
		return listaPets.values().stream().filter(pet -> idade.equals(pet.getIdade())).collect(Collectors.toList());
	}

	public Map<Integer, List<Servico>> relatorioServicos() {
		return listaServicos;
	}

	public List<Pet> relatorioTop10PetsPorConsumo() {
		return listaPets.values().stream().sorted(Comparator.comparing(Pet::getConsumo).reversed()).limit(10)
				.collect(Collectors.toList());
	}
	
	public Pet getPet(Integer id) {
		return listaPets.get(id);
	}
	
}
