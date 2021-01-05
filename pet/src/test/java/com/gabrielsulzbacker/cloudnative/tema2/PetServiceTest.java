package com.gabrielsulzbacker.cloudnative.tema2;

import com.gabrielsulzbacker.cloudnative.tema2.dominio.Pet;
import com.gabrielsulzbacker.cloudnative.tema2.dominio.ServicoType;
import com.gabrielsulzbacker.cloudnative.tema2.module.PetModule;
import com.gabrielsulzbacker.cloudnative.tema2.service.PetService;
import com.gabrielsulzbacker.cloudnative.tema2.exception.PetException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

public class PetServiceTest {

	Injector injector = Guice.createInjector(new PetModule());
	PetService petShop = injector.getInstance(PetService.class);

	Pet pet1 = new Pet(1, "Zeus", "Bulldog", 3);
	Pet pet2 = new Pet(2, "Mel", "Golden Retriever", 3);
	Pet pet3 = new Pet(3, null, "Chihuahua", 5);
	Pet pet4 = new Pet(4, "Fumaça", null, 1);

	@Before
	public void inicializar() {
		petShop.adicionarPet(pet1);
		petShop.adicionarPet(pet2);
	}

	@Test
	public void adicionarPet() {
		Assert.assertEquals("Mel", petShop.getPet(2).getNome());
	}

	@Test(expected = PetException.class)
	public void adicionarPetSemNome() {
		petShop.adicionarPet(pet3);
	}

	@Test(expected = PetException.class)
	public void adicionarPetSemRaca() {
		petShop.adicionarPet(pet4);
	}

	@Test
	public void excluirPet() {
		petShop.excluirPet(1);
		Assert.assertNull(petShop.getPet(1));
	}

	@Test(expected = PetException.class)
	public void excluirPetSemInformarId() {
		petShop.excluirPet(null);
	}

	@Test
	public void efetuarBanho() {
		petShop.efetuarServico(1, ServicoType.BANHO);
		Assert.assertEquals(40.00, pet1.getConsumo(), 0);
	}

	@Test
	public void efetuarBanhoPerfumado() {
		petShop.efetuarServico(1, ServicoType.BANHO_PERFUMADO);
		Assert.assertEquals(45.00, pet1.getConsumo(), 0);
	}

	@Test
	public void efetuarBanhoSeco() {
		petShop.efetuarServico(1, ServicoType.BANHO_SECO);
		Assert.assertEquals(30.00, pet1.getConsumo(), 0);
	}

	@Test
	public void efetuarBanhoSecoPerfumado() {
		petShop.efetuarServico(1, ServicoType.BANHO_SECO_PERFUMADO);
		Assert.assertEquals(35.00, pet1.getConsumo(), 0);
	}

	@Test
	public void efetuarTosaCurta() {
		petShop.efetuarServico(1, ServicoType.TOSA_CURTA);
		Assert.assertEquals(50.00, pet1.getConsumo(), 0);
	}

	@Test
	public void efetuarTosaLonga() {
		petShop.efetuarServico(1, ServicoType.TOSA_LONGA);
		Assert.assertEquals(60.00, pet1.getConsumo(), 0);
	}

	@Test
	public void consultarServicosEfetuados() {
		petShop.efetuarServico(1, ServicoType.BANHO_SECO);
		petShop.efetuarServico(1, ServicoType.TOSA_CURTA);
		Assert.assertEquals(2, petShop.relatorioServicos().size());
	}

	@Test
	public void buscarPetsPorIdade() {
		Assert.assertEquals(2, petShop.buscaPorIdade(3).size());
	}

	@Test(expected = PetException.class)
	public void buscarPetsPorIdadeSemInformarIdade() {
		petShop.buscaPorIdade(null);
	}

}
