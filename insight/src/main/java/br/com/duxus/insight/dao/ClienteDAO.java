package br.com.duxus.insight.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.duxus.insight.model.Cliente;

public interface ClienteDAO extends JpaRepository<Cliente, Integer> {

}
