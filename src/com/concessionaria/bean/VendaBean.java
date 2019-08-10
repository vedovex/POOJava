package com.concessionaria.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.concessionaria.dao.VendaDAO;
import com.concessionaria.domain.Carro;
import com.concessionaria.domain.Venda;
import com.concessionaria.util.JSFUtil;

@ManagedBean(name = "MBVenda")
@ViewScoped

public class VendaBean implements Serializable {
	
	private List<Venda> itens;
	
	private Venda venda = new Venda();
	private Carro carro = new Carro();
	
	
	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public List<Venda> getItens() {
		return itens;
	}

	public void setItens(List<Venda> itens) {
		this.itens = itens;
	}


	@PostConstruct 											/* esse metodo automaticamente vai ser chmado antes da pagina */

	private void listar() throws Exception {
		try {
			VendaDAO dao = new VendaDAO();
			itens = dao.listAll();

		} catch (SQLException ex) {
			ex.printStackTrace(); 						/* imprimi log de transação para mostra erro */
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	
	public void salvar() throws Exception {

		try {
			VendaDAO dao = new VendaDAO();
			dao.inserir(venda, carro);
			itens = dao.listAll();

			JSFUtil.adicionarMensagemSucesso("Venda cadastrada com Sucesso!!");

		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}
	
}
