package com.concessionaria.bean;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.concessionaria.dao.CarroDAO;
import com.concessionaria.domain.Carro;
import com.concessionaria.domain.Servico;
import com.concessionaria.util.JSFUtil;

@ManagedBean(name = "MBCarro")
@ViewScoped /* mantem estado da beam enquanto houver requisiçoes */

public class CarroBean {

	private Servico servico;
	private Carro carro;
	private List<Carro> itens;
	private List<Carro> itensFiltrados;

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public List<Carro> getItens() {
		return itens;
	}

	public void setItens(List<Carro> itens) {
		this.itens = itens;
	}

	public void setItensFiltrados(List<Carro> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public List<Carro> getItensFiltrados() {
		return itensFiltrados;
	}

	@PostConstruct /* esse metodo automaticamente vai ser chmado antes da pagina */

	private void listar() throws Exception {
		try {
			CarroDAO dao = new CarroDAO();
			itens = dao.listAll();

		} catch (SQLException ex) {
			ex.printStackTrace(); /* imprimi log de transação para mostra erro */
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void preparaSalvar() {
		carro = new Carro();

	}

	public void salvar() throws Exception {

		try {
			CarroDAO dao = new CarroDAO();
			dao.inserir(carro);
			itens = dao.listAll();

			JSFUtil.adicionarMensagemSucesso("Carro cadastrado com Sucesso!!");

		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}

	public void excluir() throws Exception {

		try {
			CarroDAO dao = new CarroDAO();
			dao.excluir(carro);
			itens = dao.listAll();

			JSFUtil.adicionarMensagemSucesso("Carro excluido!!");

		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());/* imprimi a msg do banco */
		}
	}

	public void editar() throws Exception {

		try {
			CarroDAO dao = new CarroDAO();
			dao.editar(carro);
			itens = dao.listAll();

			JSFUtil.adicionarMensagemSucesso("Carro editado com sucesso!!");

		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());/* imprimi a msg do banco */
		}

	}

}
