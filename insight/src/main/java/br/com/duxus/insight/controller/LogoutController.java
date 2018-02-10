package br.com.duxus.insight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import br.com.duxus.insight.business.UsuarioBusiness;
import br.com.duxus.insight.model.Usuario;

@Controller
@RequestMapping("/logout")
public class LogoutController {

	@Autowired
	private UsuarioBusiness usuarioBusiness;

	@PostMapping("/{id}/{hash}")
	@ResponseStatus(value = HttpStatus.OK)
	public ModelAndView validar(@PathVariable Long id, @PathVariable String hash) {

		ModelAndView model = new ModelAndView();

		Usuario usuario = usuarioBusiness.buscaPor(id, hash);

		if (usuario == null) {
			usuario = new Usuario();

			model.addObject(usuario);
			new ModelAndView("login");
		}

		model.addObject("userId", usuario.getId());
		model.addObject("token", usuario.getHash());

		return model;
	}

	@PostMapping("/{id}/")
	public ResponseEntity<Boolean> logout(@PathVariable Long id) {

		Usuario usuario = usuarioBusiness.buscaPor(id);

		Boolean validado = (usuario != null);

		return new ResponseEntity<Boolean>(validado, HttpStatus.OK);
	}
}
