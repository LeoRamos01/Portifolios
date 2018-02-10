package br.com.duxus.insight.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.duxus.insight.dao.ClienteDAO;
import br.com.duxus.insight.model.Cliente;

@Service
public class ClienteBusiness {
	@Autowired
	private ClienteDAO clienteDAO;
	
	public Cliente salvar(Cliente cliente) {
		Cliente clienteNovo = clienteDAO.save(cliente);
		
		return clienteNovo;
	}
}
