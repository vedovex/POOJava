package com.concessionaria.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.concessionaria.domain.Desc_servico;
import com.concessionaria.factory.Conexaofactory;

public class Desc_servicoDAO {
	
	
	public List<Desc_servico> listAll() throws Exception {
		ArrayList<Desc_servico> list = new ArrayList<Desc_servico>();

		try {
			Connection con = Conexaofactory.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, descricao FROM desc_servico;");
			while (rs.next()) {

				Desc_servico d = new Desc_servico();
				
				d.setId(rs.getInt("id"));
				d.setDescricao(rs.getString("descricao"));
				
				list.add(d);
			}
			stmt.close();
			rs.close();
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados:" + ex);
			throw ex;
		}
		return list;
	}
}
