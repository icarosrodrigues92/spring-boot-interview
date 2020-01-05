package br.com.compasso.demo.service;

import java.util.List;

import br.com.compasso.demo.model.Cliente;
import br.com.compasso.demo.model.dto.ClienteDTO;

public interface ClienteService {

	Cliente findById(String id);

	List<Cliente> listAll();

	List<Cliente> listByNome(String nome);

	void delete(String id);

	Cliente save(ClienteDTO input);

	Cliente update(String id, ClienteDTO input);
}
