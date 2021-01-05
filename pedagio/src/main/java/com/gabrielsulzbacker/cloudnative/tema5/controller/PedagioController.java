package com.gabrielsulzbacker.cloudnative.tema5.controller;

import com.gabrielsulzbacker.cloudnative.tema5.service.PedagioService;
import com.gabrielsulzbacker.cloudnative.tema5.dominio.VeiculoType;
import com.gabrielsulzbacker.cloudnative.tema5.exception.PedagioException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Map;

@RestController
@RequestMapping("/pedagio")
public class PedagioController {

	@Autowired
	private PedagioService pedagio;

	@GetMapping
	public ResponseEntity<String> calcularPedagio(@RequestParam VeiculoType veiculo, @RequestParam Double valor,
			@RequestParam Integer eixos) {

		if (veiculo == null || valor == null || eixos == null) {
			throw new PedagioException("Valores nulos: todos os campos são de preenchimento obrigatório!");
		}

		return new ResponseEntity<>("Veículo: " + veiculo + " / " + "Pagamento: " + valor + " / " + "Troco: "
				+ pedagio.efetuarPagamento(veiculo, valor, eixos), HttpStatus.OK);
	}

	@ExceptionHandler
	public ResponseEntity<String> validarPedagio(PedagioException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = "/precos")
	public Map<String, Double> listarPrecos() {
		return pedagio.listarPrecos();
	}

}