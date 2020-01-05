package br.com.compasso.demo.service;

import java.util.List;

import br.com.compasso.demo.model.Cidade;
import br.com.compasso.demo.model.dto.CidadeDTO;

public interface CidadeService {

	List<Cidade> listAll();

	List<Cidade> listByNomeAndEstado(String nome, String estado);

	List<Cidade> listByNome(String nome);

	List<Cidade> listByEstado(String estado);

	Cidade findById(String id);

	void delete(String id);

	Cidade save(CidadeDTO input);

	Cidade update(String id, CidadeDTO input);
}
