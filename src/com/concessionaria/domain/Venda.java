package com.concessionaria.domain;

import java.io.Serializable;

public class Venda implements Serializable {
	
	private int id;
	private String nome;
	private String cpf;
	private String dataNascimento;
	private String rua;
	private String n;
	private String cidade;
	private Carro placa;
	
	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getN() {
		return n;
	}

	public void setN(String n) {
		this.n = n;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Carro getPlaca() {
		return placa;
	}

	public void setPlaca(Carro placa) {
		this.placa = placa;
	}


}
