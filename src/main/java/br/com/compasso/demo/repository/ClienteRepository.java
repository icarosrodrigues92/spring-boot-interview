package br.com.compasso.demo.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.compasso.demo.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, BigInteger> {

	@Query("SELECT c FROM Cliente c WHERE c.nome LIKE %:nome%")
	List<Cliente> findByNomeLike(@Param("nome") String nome);
}
