package br.com.compasso.demo.model.dto;

public class ClienteDTO {

	private String nome;

	private String sexo;

	private String nascimento;

	private String cidadeId;

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

	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}

	public String getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(String cidadeId) {
		this.cidadeId = cidadeId;
	}
}
