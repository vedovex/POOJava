package com.concessionaria.domain;

public class Servico {
	private int id;
	private Desc_servico descricao = new Desc_servico();
	private String periodo;
	private Carro placa;
	
	public void setPlaca(Carro placa) {
		this.placa = placa;
	}
	
	public Carro getPlaca() {
		return placa;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDescricao(Desc_servico descricao) {
		this.descricao = descricao;
	}
	
	public Desc_servico getDescricao() {
		return descricao;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

}
