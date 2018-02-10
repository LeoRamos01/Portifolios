package br.com.duxus.insight.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.duxus.insight.business.PortifolioBusiness;
import br.com.duxus.insight.model.Portifolio;

@RestController
public class API {
	
	@Autowired
	private Portifolio portifolio;
	
	@Autowired
	private List<Portifolio> portifolios;
	
	@Autowired
	private PortifolioBusiness portifolioBusiness;
	
	@GetMapping("/buscar/{id}")
	public @ResponseBody Portifolio buscaPorId(@PathVariable Long id) {
		
		portifolio = portifolioBusiness.buscarPor(id);
		
		return portifolio;
		
	}
	
	@GetMapping("/buscar/all")
	public @ResponseBody List<Portifolio> buscaTodos() {
		
		portifolios = portifolioBusiness.buscarTodos();
		
		return portifolios;
		
	}
}
