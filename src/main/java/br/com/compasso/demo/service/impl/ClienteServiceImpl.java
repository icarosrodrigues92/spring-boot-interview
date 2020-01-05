package br.com.compasso.demo.service.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.compasso.demo.model.Cliente;
import br.com.compasso.demo.model.dto.ClienteDTO;
import br.com.compasso.demo.repository.ClienteRepository;
import br.com.compasso.demo.service.CidadeService;
import br.com.compasso.demo.service.ClienteService;
import br.com.compasso.demo.util.AppUtils;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private CidadeService cidadeService;

	@Override
	public Cliente findById(String id) {

		Cliente output = null;

		Optional<Cliente> opt = clienteRepository.findById(new BigInteger(id));
		if (opt.isPresent()) {
			output = opt.get();
		}

		return output;
	}

	@Override
	public List<Cliente> listAll() {
		return clienteRepository.findAll();
	}

	@Override
	public List<Cliente> listByNome(String nome) {
		return clienteRepository.findByNomeLike(nome);
	}

	@Override
	public void delete(String id) {
		clienteRepository.deleteById(new BigInteger(id));
	}

	@Override
	public Cliente save(ClienteDTO input) {

		Cliente cliente = new Cliente();
		cliente.setNome(input.getNome());
		cliente.setSexo(input.getSexo());
		// Converte uma string no padrão dd/MM/yyyy em Date. Retorna nulo caso fora do
		// padrão. Idade é calculada a partir do nascimento.
		cliente.setNascimento(AppUtils.convertStringToLocalDate(input.getNascimento()));
		cliente.setCidade(cidadeService.findById(input.getCidadeId()));
		return clienteRepository.save(cliente);
	}

	@Override
	public Cliente update(String id, ClienteDTO input) {

		Cliente cliente = this.findById(id);

		if (cliente != null) {
			cliente.setNome(input.getNome());
			cliente.setSexo(input.getSexo());
			cliente.setNascimento(AppUtils.convertStringToLocalDate(input.getNascimento()));
			if (StringUtils.hasText(input.getCidadeId())) {
				cliente.setCidade(cidadeService.findById(input.getCidadeId()));
			}
		}

		return clienteRepository.save(cliente);
	}
}
