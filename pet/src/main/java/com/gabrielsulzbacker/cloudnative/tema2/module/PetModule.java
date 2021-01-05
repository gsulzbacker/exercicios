package com.gabrielsulzbacker.cloudnative.tema2.module;

import com.gabrielsulzbacker.cloudnative.tema2.service.PetService;
import com.google.inject.AbstractModule;

public class PetModule extends AbstractModule{

	@Override
	public void configure() {
		bind(PetService.class);
	}
		
}
