package br.com.duxus.insight.business;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;

import br.com.duxus.insight.dao.UsuarioDAO;
import br.com.duxus.insight.model.Usuario;
import br.com.duxus.insight.util.EncriptaDecripta;

@Service
public class UsuarioBusiness {
	@Autowired
	private Usuario usuario;

	@Autowired
	private UsuarioDAO usuarioDAO;

	public Usuario buscarPor(String nome, String senha) {

		usuario = usuarioDAO.findByNome(nome);

		if (usuario != null) {
			String senhaCrip = EncriptaDecripta.codificaBase64(senha);
			Boolean autenticado = (senhaCrip.equals(usuario.getSenha()) ? true : false);

			if (autenticado) {
				
				String hash = gerarToken();
				
				usuario.setHash(hash);
				
				usuarioDAO.save(usuario);
				
				return usuario;
			}
		}

		return null;
	}

	private String gerarToken() {

		LocalDateTime date = LocalDateTime.now();

		String agora = date.toString();

		String sha256hex = Hashing.sha256().hashString(agora, StandardCharsets.UTF_8).toString();

		if (sha256hex.length() < 30) {
			return sha256hex;
		}

		return sha256hex.substring(0, 30);
	}

	public Usuario buscaPor(Long id, String hash) {
		
		usuario = usuarioDAO.findByIdAndHash(id, hash);
		
		return usuario;
	}

	public Usuario buscaPor(Long id) {
		usuario = usuarioDAO.findById(id);
		
		return usuario;
	}

}