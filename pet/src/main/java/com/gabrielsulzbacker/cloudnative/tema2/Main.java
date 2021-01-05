package com.gabrielsulzbacker.cloudnative.tema2;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gabrielsulzbacker.cloudnative.tema2.dominio.*;
import com.gabrielsulzbacker.cloudnative.tema2.service.PetService;
import com.gabrielsulzbacker.cloudnative.tema2.module.PetModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {
	
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new PetModule());
		PetService petShop = injector.getInstance(PetService.class);
		Log logger = LogFactory.getLog(Main.class);

		Pet pet1 = new Pet(1, "Zeus", "Bulldog", 3);
		Pet pet2 = new Pet(2, "Mel", "Golden Retriever", 3);

		petShop.adicionarPet(pet1);
		petShop.adicionarPet(pet2);
		petShop.efetuarServico(1, ServicoType.BANHO_SECO);
		petShop.efetuarServico(1, ServicoType.TOSA_CURTA);		
		petShop.efetuarServico(2, ServicoType.BANHO_PERFUMADO);
		petShop.efetuarServico(2, ServicoType.TOSA_LONGA);

		logger.info(petShop.buscaPorIdade(3));
		logger.info(petShop.relatorioServicos());
		logger.info(petShop.relatorioTop10PetsPorConsumo());
	}
}
