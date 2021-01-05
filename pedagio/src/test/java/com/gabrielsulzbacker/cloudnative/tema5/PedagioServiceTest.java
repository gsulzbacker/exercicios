package com.gabrielsulzbacker.cloudnative.tema5;

import com.gabrielsulzbacker.cloudnative.tema5.config.PedagioConfig;
import com.gabrielsulzbacker.cloudnative.tema5.service.PedagioService;
import com.gabrielsulzbacker.cloudnative.tema5.dominio.VeiculoType;
import com.gabrielsulzbacker.cloudnative.tema5.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.junit.Assert;
import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PedagioConfig.class)

public class PedagioServiceTest {

	@Autowired
	private PedagioService pedagio;

	@Test
	public void efetuarPagamentoDePedagioParaOnibus() {
		Assert.assertEquals(0, pedagio.efetuarPagamento(VeiculoType.ONIBUS, 1.59, 2), 0);
	}

	@Test
	public void efetuarPagamentoDePedagioParaMoto() {
		Assert.assertEquals(0, pedagio.efetuarPagamento(VeiculoType.MOTO, 1.00, 2), 0);
	}

	@Test
	public void efetuarPagamentoDePedagioParaBicicleta() {
		Assert.assertEquals(0, pedagio.efetuarPagamento(VeiculoType.BICICLETA, 0.49, 2), 0);
	}

	@Test
	public void efetuarPagamentoDePedagioParaCaminhao() {
		Assert.assertEquals(0, pedagio.efetuarPagamento(VeiculoType.CAMINHAO, 6.95, 2), 0);
	}

	@Test
	public void efetuarPagamentoDePedagioParaFusca() {
		Assert.assertEquals(0, pedagio.efetuarPagamento(VeiculoType.FUSCA, 2.11, 2), 0);
	}

	@Test
	public void efetuarPagamentoDePedagioComTroco() {
		Assert.assertEquals(3.05, pedagio.efetuarPagamento(VeiculoType.CAMINHAO, 10.00, 2), 0);
	}

	@Test(expected = PedagioException.class)
	public void efetuarPagamentoDePedagioParaCaminhaoSemEixos() {
		pedagio.efetuarPagamento(VeiculoType.CAMINHAO, 3.95, null);
	}

	@Test(expected = PedagioException.class)
	public void efetuarPagamentoDePedagioComValorInsuficiente() {
		pedagio.efetuarPagamento(VeiculoType.ONIBUS, 1.50, 0);
	}

	@Test(expected = PedagioException.class)
	public void efetuarPagamentoDePedagioComVeiculoEmBranco() {
		pedagio.efetuarPagamento(null, 5.00, 2);
	}

	@Test(expected = PedagioException.class)
	public void efetuarPagamentoDePedagioComValorEmBranco() {
		pedagio.efetuarPagamento(VeiculoType.MOTO, null, 2);
	}

	@Test(expected = PedagioException.class)
	public void efetuarPagamentoDePedagioComEixosEmBranco() {
		pedagio.efetuarPagamento(VeiculoType.FUSCA, 2.11, null);
	}

}
