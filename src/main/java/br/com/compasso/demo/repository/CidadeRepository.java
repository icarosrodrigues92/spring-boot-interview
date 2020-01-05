package br.com.compasso.demo.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.compasso.demo.model.Cidade;

@Repository
public interface CidadeRepository  extends JpaRepository<Cidade, BigInteger> {

	@Query("SELECT c FROM Cidade c WHERE c.nome LIKE %:nome% AND c.estado = :estado")
	List<Cidade> findByNomeLikeAndEstado(@Param("nome") String nome, @Param("estado") String estado);

	@Query("SELECT c FROM Cidade c WHERE c.nome LIKE %:nome%")
	List<Cidade> findByNomeLike(@Param("nome") String nome);

	List<Cidade> findByEstado(String estado);

}
