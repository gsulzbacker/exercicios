package com.gabrielsulzbacker.coreengineering.tema8.dao;

import com.gabrielsulzbacker.coreengineering.tema8.modelo.Livro;
import com.gabrielsulzbacker.coreengineering.tema8.exception.ArquivoException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.common.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class LivroDAO {

	private String arquivo = "src/main/resources/livros.json";
	private Gson gson = new GsonBuilder().create();

	public void salvarArquivo(List<Livro> livro) {
		String file = gson.toJson(livro);
		try (FileWriter fw = new FileWriter(arquivo); BufferedWriter bw = new BufferedWriter(fw)) {
			bw.write(file);
		} catch (IOException e) {
			throw new ArquivoException("Erro ao salvar o arquivo: " + e.getMessage());
		}
	}

	public List<Livro> lerArquivo() {
		List<Livro> listaLivros = null;
		File file = new File(arquivo);
		Type typeFile = new TypeToken<List<Livro>>() {
		}.getType();

		try (FileReader fr = new FileReader(arquivo); BufferedReader br = new BufferedReader(fr)) {
			if (!file.exists()) {
				file.createNewFile();
			}
			listaLivros = gson.fromJson(br, typeFile);
			return listaLivros;

		} catch (IOException e) {
			throw new ArquivoException("Erro na leitura do arquivo: " + e.getMessage());
		}

	}

}