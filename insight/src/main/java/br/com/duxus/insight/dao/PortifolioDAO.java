package br.com.duxus.insight.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.duxus.insight.model.Portifolio;

public interface PortifolioDAO extends JpaRepository<Portifolio, Integer> {

	Portifolio findById(Long id);

	@Query(value = "SELECT p.* FROM portifolio p JOIN tagPortifolio tp ON tp.idPortifolio = p.id JOIN tag t ON t.id = tp.idTag WHERE t.descricao IN (:descricao)", nativeQuery = true)
	List<Portifolio> find(@Param("descricao") List<String> descricao);

}
