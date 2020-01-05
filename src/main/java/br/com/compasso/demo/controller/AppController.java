package br.com.compasso.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.demo.model.Cidade;
import br.com.compasso.demo.model.Cliente;
import br.com.compasso.demo.model.dto.CidadeDTO;
import br.com.compasso.demo.model.dto.ClienteDTO;
import br.com.compasso.demo.service.CidadeService;
import br.com.compasso.demo.service.ClienteService;

@RestController
public class AppController {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private CidadeService cidadeService;

	@RequestMapping(value = "cliente", method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> listClientes(@RequestParam(required = false, name = "nome") String nome) {

		List<Cliente> output = null;

		if (StringUtils.isEmpty(nome)) {
			output = clienteService.listAll();
		} else {
			output = clienteService.listByNome(nome);
		}

		return new ResponseEntity<List<Cliente>>(output, HttpStatus.OK);
	}

	@RequestMapping(value = "cliente/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> findCliente(@PathVariable("id") String id) {

		return new ResponseEntity<Cliente>(clienteService.findById(id), HttpStatus.OK);
	}

	@RequestMapping(value = "cidade", method = RequestMethod.GET)
	public ResponseEntity<List<Cidade>> listCidades(@RequestParam(required = false, name = "nome") String nome,
			@RequestParam(required = false, name = "estado") String estado) {

		List<Cidade> output = null;

		if (StringUtils.isEmpty(nome) && StringUtils.isEmpty(estado)) {
			output = cidadeService.listAll();
		} else if (!StringUtils.isEmpty(nome) && !StringUtils.isEmpty(estado)) {
			output = cidadeService.listByNomeAndEstado(nome, estado);
		} else if (!StringUtils.isEmpty(nome) && StringUtils.isEmpty(estado)) {
			output = cidadeService.listByNome(nome);
		} else if (StringUtils.isEmpty(nome) && !StringUtils.isEmpty(estado)) {
			output = cidadeService.listByEstado(estado);
		}

		return new ResponseEntity<List<Cidade>>(output, HttpStatus.OK);
	}

	@RequestMapping(value = "cidade/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cidade> findCidade(@PathVariable("id") String id) {

		return new ResponseEntity<Cidade>(cidadeService.findById(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/cidade/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteCidade(@PathVariable("id") String id) {

		cidadeService.delete(id);

		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteCliente(@PathVariable("id") String id) {

		clienteService.delete(id);

		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@RequestMapping(value = "/cidade", method = RequestMethod.POST)
	public ResponseEntity<Cidade> saveCidade(@RequestBody CidadeDTO input) {

		return new ResponseEntity<Cidade>(cidadeService.save(input), HttpStatus.OK);
	}

	@RequestMapping(value = "/cidade/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Cidade> updateCidade(@PathVariable("id") String id, @RequestBody CidadeDTO input) {

		return new ResponseEntity<Cidade>(cidadeService.update(id, input), HttpStatus.OK);
	}

	@RequestMapping(value = "/cliente", method = RequestMethod.POST)
	public ResponseEntity<Cliente> saveCliente(@RequestBody ClienteDTO input) {

		return new ResponseEntity<Cliente>(clienteService.save(input), HttpStatus.OK);
	}

	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Cliente> updateCliente(@PathVariable("id") String id, @RequestBody ClienteDTO input) {

		return new ResponseEntity<Cliente>(clienteService.update(id, input), HttpStatus.OK);
	}

}
