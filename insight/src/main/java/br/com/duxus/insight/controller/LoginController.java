package br.com.duxus.insight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.duxus.insight.business.UsuarioBusiness;
import br.com.duxus.insight.model.Usuario;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private Usuario usuario;
	
	@Autowired
	private UsuarioBusiness usuarioBusiness;
	
	@GetMapping
	public ModelAndView home() {
		ModelAndView model = new ModelAndView("login");

		model.addObject(usuario);
		
		return model;
	}
	
	@PostMapping("/efetuar")
	public ModelAndView login(@Validated Usuario usuario) {
		ModelAndView model = new ModelAndView("listar");
		
		usuario = usuarioBusiness.buscarPor(usuario.getNome(), usuario.getSenha());
		
		if (usuario == null) {
			
			usuario = new Usuario();
			
			model.addObject(usuario);
			return new ModelAndView("login");
		}
		
		model.addObject("userId", usuario.getId());
		model.addObject("token", usuario.getHash());

		return model;
	}
}
