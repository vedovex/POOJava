package com.concessionaria.domain;

public class Carro {
	
	private String placa;
	private String cor;
	private String modelo;
	private String ano;
	private Double preco;
	private String situacao;
	
	
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getSituacao() {
		return situacao;
	}
	
	public void setSituacao(String situcao) {
		this.situacao = situcao;
	}
	

}
