package br.com.compasso.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Sql({ "schema.sql" })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class AppTests {

	@Autowired
	private MockMvc mvc;

	@Test
	void findCidade() throws Exception {

		mvc.perform(MockMvcRequestBuilders.get("/cidade/1")).andExpect(status().isOk());
	}

	@Test
	void listCidades() throws Exception {

		mvc.perform(MockMvcRequestBuilders.get("/cidade")).andExpect(status().isOk());
	}

	@Test
	void listCidadesByNome() throws Exception {

		mvc.perform(MockMvcRequestBuilders.get("/cidade?nome=Recife")).andExpect(status().isOk());
	}

	@Test
	void listCidadesByEstado() throws Exception {

		mvc.perform(MockMvcRequestBuilders.get("/cidade?estado=PE")).andExpect(status().isOk());
	}

	@Test
	void listCidadesByNomeAndEstado() throws Exception {

		mvc.perform(MockMvcRequestBuilders.get("/cidade?nome=Recife&estado=PE")).andExpect(status().isOk());
	}

	@Test
	void saveCidade() throws Exception {

		mvc.perform(MockMvcRequestBuilders.post("/cidade").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content("{\"nome\":\"Cabo\",\"estado\":\"PE\"}").accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());
	}

	@Test
	void updateCidade() throws Exception {

		mvc.perform(MockMvcRequestBuilders.put("/cidade/2").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content("{\"nome\":\"Olinda\",\"estado\":\"PE\"}").accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());
	}

	@Test
	void findCliente() throws Exception {

		mvc.perform(MockMvcRequestBuilders.get("/cliente/1")).andExpect(status().isOk());
	}

	@Test
	void listClientes() throws Exception {

		mvc.perform(MockMvcRequestBuilders.get("/cliente")).andExpect(status().isOk());
	}

	@Test
	void listClientesByNome() throws Exception {

		mvc.perform(MockMvcRequestBuilders.get("/cliente?nome=Recife")).andExpect(status().isOk());
	}

	@Test
	void saveCliente() throws Exception {

		mvc.perform(MockMvcRequestBuilders.post("/cliente").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content("{\"nome\":\"Dayse\",\"sexo\":\"F\",\"nascimento\":\"24/07/1992\",\"cidadeId\":\"1\"}")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
	}

	@Test
	void updateCliente() throws Exception {

		mvc.perform(MockMvcRequestBuilders.put("/cliente/1").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content("{ \"nome\":\"Dayse\",\"sexo\":\"F\",\"nascimento\":\"24/07/1991\",\"cidadeId\":\"1\" }")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
	}

	@Test
	void deleteCliente() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/cliente/1")).andExpect(status().isOk());
	}
	
	@Test
	void deleteCidade() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/cidade/999")).andExpect(status().isOk());
	}
}
