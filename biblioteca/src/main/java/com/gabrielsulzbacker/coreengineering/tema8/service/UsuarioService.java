package com.gabrielsulzbacker.coreengineering.tema8.service;

import com.gabrielsulzbacker.coreengineering.tema8.dao.UsuarioDAO;
import com.gabrielsulzbacker.coreengineering.tema8.exception.LivroException;
import com.gabrielsulzbacker.coreengineering.tema8.exception.UsuarioException;
import com.gabrielsulzbacker.coreengineering.tema8.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioService {

	private UsuarioDAO usuarioDao = new UsuarioDAO();
	private List<Usuario> listaUsuarios;
	
	public UsuarioService(){
		listaUsuarios = new ArrayList<Usuario>();
	}
    
	public boolean cadastrarUsuario(Usuario usuario) {
		if (usuario == null) {
			throw new UsuarioException("Favor informar um usuário a ser cadastrado.");
		}
		listaUsuarios.forEach(usuarioCadastrado -> {
			if (usuarioCadastrado.equals(usuario))
				throw new UsuarioException("O usuário já está cadastrado.");
		});
		listaUsuarios.add(usuario);
		usuarioDao.salvarArquivo(listaUsuarios);
		return true;
	}
	
	public boolean cadastrarUsuarioNulo(Usuario usuario) {
		listaUsuarios.forEach(usuarioCadastrado -> {
			if (usuarioCadastrado.equals(usuario))
				throw new UsuarioException("O usuário já está cadastrado.");
		});
		listaUsuarios.add(usuario);
		usuarioDao.salvarArquivo(listaUsuarios);
		return true;
	}
	
	public boolean removerUsuario(Usuario usuario) {
		if (!listaUsuarios.contains(usuario)) {
			throw new LivroException("O usuário não foi localizado.");
		}
		listaUsuarios.remove(usuario);
		usuarioDao.salvarArquivo(listaUsuarios);
		return true;
	}
    
	public Optional<Usuario> consultarUsuarioPeloId(Integer id) {
		return usuarioDao.lerArquivo().stream().filter(usuario -> usuario.getId().equals(id)).findAny();
	}
	
    public List<Usuario> listarUsuarios(){
    	return listaUsuarios;
    }
}