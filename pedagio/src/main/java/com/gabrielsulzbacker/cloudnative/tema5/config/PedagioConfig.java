package com.gabrielsulzbacker.cloudnative.tema5.config;

import com.gabrielsulzbacker.cloudnative.tema5.dominio.*;
import com.gabrielsulzbacker.cloudnative.tema5.service.PedagioService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Bean;

@Configuration
@ComponentScan(basePackages = "com.gabrielsulzbacker.cloudnative.tema5")
public class PedagioConfig {

	@Bean(name = "ONIBUS")
	public Onibus onibus() {
		return new Onibus();
	}

	@Bean(name = "MOTO")
	public Moto moto() {
		return new Moto();
	}

	@Bean(name = "BICICLETA")
	public Bicicleta bicicleta() {
		return new Bicicleta();
	}

	@Bean(name = "CAMINHAO")
	public Caminhao caminhao() {
		return new Caminhao();
	}

	@Bean(name = "FUSCA")
	public Fusca fusca() {
		return new Fusca();
	}

	@Bean
	public PedagioService pedagio() {
		return new PedagioService();
	}

}