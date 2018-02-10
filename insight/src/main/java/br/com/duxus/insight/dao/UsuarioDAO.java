package br.com.duxus.insight.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.duxus.insight.model.Portifolio;
import br.com.duxus.insight.model.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, Integer>{
	
	Usuario findById(Long id);
	
	Usuario findByNome(String nome);
	
	Usuario findByIdAndHash(Long id, String hash);
}
