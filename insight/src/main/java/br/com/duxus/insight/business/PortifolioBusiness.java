package br.com.duxus.insight.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.duxus.insight.dao.PortifolioDAO;
import br.com.duxus.insight.model.Portifolio;
import br.com.duxus.insight.model.Tag;

@Service
public class PortifolioBusiness {

	@Autowired
	private Portifolio portifolio;

	@Autowired
	private List<Portifolio> portifolios;

	@Autowired
	private PortifolioDAO portifolioDAO;
	
	 @Autowired 
	 private HttpServletRequest request;
	 

	private void carregarTags(List<Portifolio> portifolios) {

		for (Portifolio portifolio : portifolios) {
			portifolio.getLista().addAll(portifolio.getListaTags());
		}

	}

	private void carregarTags(Portifolio portifolio) {

		portifolio.getLista().addAll(portifolio.getListaTags());

	}

	private Set<Tag> criarTags(String tags) {

		tags = tags.toLowerCase();

		List<Tag> lista = new ArrayList<>();
		List<String> result = Arrays.asList(tags.split("\\s*,\\s*"));

		for (String string : result) {
			Tag tag = new Tag();

			tag.setDescricao(string);

			lista.add(tag);
		}

		Set<Tag> set = new HashSet<>(lista);

		return set;
	}


	private String salvarArquivo(Portifolio portifolio) {

		MultipartFile mpf = portifolio.getArquivo();

		String uploadsDir = "/uploads/";
		String realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);

		String nome = realPathtoUploads + portifolio.getNome();

		return nome;
	}

	public void salvar(Portifolio portifolio, String tags) {

		Set<Tag> listaTags = criarTags(tags);

		portifolio.setListaTags(listaTags);

		portifolio.setCaminhoArquivo(salvarArquivo(portifolio));
		
		String descricao = portifolio.getDescricao();
		portifolio.setDescricao(descricao.trim());

		portifolioDAO.save(portifolio);

	}

	public List<Portifolio> listar(String tags) {
		portifolios = new ArrayList<>();

		if (tags == null || tags.isEmpty()) {
			portifolios = portifolioDAO.findAll();
		} else {
			portifolios = listarPorTags(tags);
		}

		carregarTags(portifolios);

		return portifolios;
	}

	public Portifolio buscarPor(Long id) {
		Portifolio portifolio = portifolioDAO.findById(id);

		carregarTags(portifolio);

		return portifolio;
	}

	public List<Portifolio> listarPorTags(String tags) {
		Set<Tag> listaTags = criarTags(tags);

		List<Tag> lista = new ArrayList<>(listaTags);
		
		List<String> strings = new ArrayList<>();

		List<Portifolio> portifolios = new ArrayList<>();
		
		for (Tag t : lista) {
			strings.add(t.toString());
		}
		
		portifolios.addAll(portifolioDAO.find(strings));
		
		Set<Portifolio> setPortifolios = new HashSet<>(portifolios);
		
		portifolios = new ArrayList<>(setPortifolios);

		return portifolios;
	}

	public void deletar(Long id) {

		portifolio = portifolioDAO.findById(id);

		if (portifolio != null)
			portifolioDAO.delete(portifolio);
	}

	public List<Portifolio> buscarTodos() {

		portifolios = portifolioDAO.findAll();
		return portifolios;
	}

}