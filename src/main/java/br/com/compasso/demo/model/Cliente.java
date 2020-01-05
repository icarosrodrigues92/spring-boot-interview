package br.com.compasso.demo.model;

import java.math.BigInteger;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import br.com.compasso.demo.util.AppUtils;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_sequence")
	@SequenceGenerator(name = "cliente_sequence", sequenceName = "cliente_seq")
	private BigInteger id;

	@Column(name = "NOME")
	private String nome;

	@Column(name = "SEXO")
	private String sexo;

	@Column(name = "NASCIMENTO")
	private LocalDate nascimento;

	@Column(name = "IDADE")
	private int idade;

	@ManyToOne
	private Cidade cidade;

	public BigInteger getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.idade = AppUtils.calculateAge(nascimento, LocalDate.now());
		this.nascimento = nascimento;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public int getIdade() {
		return idade;
	}
}
