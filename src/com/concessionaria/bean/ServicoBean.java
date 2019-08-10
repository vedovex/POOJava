package com.concessionaria.bean;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.concessionaria.dao.Desc_servicoDAO;
import com.concessionaria.dao.ServicoDAO;
import com.concessionaria.domain.Carro;
import com.concessionaria.domain.Desc_servico;
import com.concessionaria.domain.Servico;
import com.concessionaria.util.JSFUtil;

@ManagedBean(name ="MBServico")
@ViewScoped
public class ServicoBean {

	private List<Servico> itens;
	private List<Servico> itensFiltrados;
	
	private Servico servico = new Servico();
	private Carro carro = new Carro();
	private Desc_servico desc = new Desc_servico();
	
	private List<Desc_servico> comboDesc;

	public Desc_servico getDesc() {
		return desc;
	}

	public void setDesc(Desc_servico desc) {
		this.desc = desc;
	}

	public void setComboDesc(List<Desc_servico> comboDesc) {
		this.comboDesc = comboDesc;
	}
	
	public List<Desc_servico> getComboDesc() {
		return comboDesc;
	}
	
	public List<Servico> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(List<Servico> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public List<Servico> getItens() {
		return itens;
	}

	public void setItens(List<Servico> itens) {
		this.itens = itens;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	@PostConstruct /* esse metodo automaticamente vai ser chmado antes da pagina */

	private void listar() throws Exception {
		try {
			ServicoDAO dao = new ServicoDAO();
			itens = dao.listAll();

		} catch (SQLException ex) {
			ex.printStackTrace(); /* imprimi log de transação para mostra erro */
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	
	public void salvar() throws Exception {

		try {
			ServicoDAO dao = new ServicoDAO();
			dao.inserir(servico, carro, desc);
			itens = dao.listAll();

			JSFUtil.adicionarMensagemSucesso("Servico cadastrado com Sucesso!!");

		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}
	
	public void preparaNovo() {
		try {
		servico = new Servico();
		Desc_servicoDAO dao = new Desc_servicoDAO();
		
		comboDesc = dao.listAll();
		
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
		
	}

}
