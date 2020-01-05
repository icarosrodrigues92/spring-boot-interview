package br.com.compasso.demo.service.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.demo.model.Cidade;
import br.com.compasso.demo.model.dto.CidadeDTO;
import br.com.compasso.demo.repository.CidadeRepository;
import br.com.compasso.demo.service.CidadeService;

@Service
public class CidadeServiceImpl implements CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Override
	public List<Cidade> listAll() {
		return cidadeRepository.findAll();
	}

	@Override
	public List<Cidade> listByNomeAndEstado(String nome, String estado) {
		return cidadeRepository.findByNomeLikeAndEstado(nome, estado);
	}

	@Override
	public List<Cidade> listByNome(String nome) {
		return cidadeRepository.findByNomeLike(nome);
	}

	@Override
	public List<Cidade> listByEstado(String estado) {
		return cidadeRepository.findByEstado(estado);
	}

	@Override
	public Cidade findById(String id) {

		Cidade output = null;
		Optional<Cidade> opt = cidadeRepository.findById(new BigInteger(id));

		if (opt.isPresent()) {
			output = opt.get();
		}

		return output;
	}

	@Override
	public void delete(String id) {
		cidadeRepository.deleteById(new BigInteger(id));
	}

	@Override
	public Cidade save(CidadeDTO input) {

		Cidade cidade = new Cidade();
		cidade.setNome(input.getNome());
		cidade.setEstado(input.getEstado());

		return cidadeRepository.save(cidade);
	}

	@Override
	public Cidade update(String id, CidadeDTO input) {

		Cidade cidade = this.findById(id);
		if (cidade != null) {
			cidade.setNome(input.getNome());
			cidade.setEstado(input.getEstado());
			cidadeRepository.save(cidade);
		}
		return cidade;
	}
}
