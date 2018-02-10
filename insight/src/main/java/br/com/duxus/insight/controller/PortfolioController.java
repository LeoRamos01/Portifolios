package br.com.duxus.insight.controller;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import br.com.duxus.insight.business.PortifolioBusiness;
import br.com.duxus.insight.model.Portifolio;
import br.com.duxus.insight.model.StatusPortfolio;
import br.com.duxus.insight.model.Usuario;

@Controller
@RequestMapping("/portifolio")
public class PortfolioController {

	private final String CADASTRAR = "cadastrar";
	private final String LISTAR = "listar";

	private String tags = null;

	private MultipartFile arquivo;

	@Autowired
	private Portifolio portifolio;
	
	@Autowired
	private List<Portifolio> portifolios;

	@Autowired
	private PortifolioBusiness portifolioBusiness;

	@GetMapping("/cadastrar")
	public ModelAndView acessarCadastro() {
		ModelAndView model = new ModelAndView(CADASTRAR);

		portifolio = new Portifolio();
		
		model.addObject(portifolio);

		return model;
	}

	@GetMapping("/listar")
	public ModelAndView listar() {
		ModelAndView model = new ModelAndView(LISTAR);

		List<Portifolio> portifolios = portifolioBusiness.listar(tags);

		model.addObject("portifolios", portifolios);
		
		model.addObject("todasTags", tags);
		
		return model;
	}
	
	@GetMapping("/todos")
	public ModelAndView todos() {
		
		this.tags = "";
		
		ModelAndView model = new ModelAndView(LISTAR);

		List<Portifolio> portifolios = portifolioBusiness.listar(null);

		model.addObject("portifolios", portifolios);
		
		model.addObject("todasTags", tags);
		
		return model;
	}

	@PostMapping("/salvar")
	public ModelAndView cadastrar(@Validated Portifolio portifolio, Errors errors, RedirectAttributes attributes) {

		if(errors.hasErrors() || this.tags == null || this.tags.isEmpty()) {
			return new ModelAndView(CADASTRAR);
		}
		
		ModelAndView model = new ModelAndView("redirect:/portifolio/cadastrar");

		portifolio.setArquivo(arquivo);

		try {
		portifolioBusiness.salvar(portifolio, this.tags);
		this.tags = null;
		
		attributes.addFlashAttribute("mensagem", "Portifólio salvo com sucesso!");
		} catch (Exception e) {
			
			attributes.addFlashAttribute("erros", e.getMessage());
			return new ModelAndView(CADASTRAR);
		}

		return model;
	}

	@PostMapping("/tags")
	@ResponseStatus(value = HttpStatus.OK)
	public void armazenarTags(@RequestParam("tags") String tags) {
		this.tags = tags;
	}

	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		ModelAndView model = new ModelAndView(CADASTRAR);

		Portifolio portifolio = portifolioBusiness.buscarPor(id);

		model.addObject(portifolio);
		model.addObject("todasTags", portifolio.getLista());
		
		this.tags = null;

		return model;
	}

	@PostMapping("/upload")
	@ResponseStatus(value = HttpStatus.OK)
	public void setArquivo(MultipartHttpServletRequest request) {

		Iterator<String> itr = request.getFileNames();

		MultipartFile mpf = request.getFile(itr.next());

		arquivo = mpf;
	}

	@DeleteMapping("/excluir/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ModelAndView excluir(@PathVariable Long id) {

		ModelAndView model = new ModelAndView(LISTAR);

		portifolioBusiness.deletar(id);

		return model;
	}

	@ModelAttribute("todosStatus")
	public List<StatusPortfolio> todosStatus() {
		return Arrays.asList(StatusPortfolio.values());
	}

	@RequestMapping(value = "/download/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String buscaPorId(@PathVariable Long id) {
		Gson gson = new Gson();
		
		portifolio = portifolioBusiness.buscarPor(id);

		String json = gson.toJson(portifolio);
		
		return json;

	}
	
	@RequestMapping(value = "/download/all", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String buscaTodos() {
		
		Gson gson = new Gson();
		
		portifolios = portifolioBusiness.buscarTodos();
		
		String json = gson.toJson(portifolios);
		
		return json;
		
	}
}