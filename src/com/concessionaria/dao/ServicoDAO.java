package com.concessionaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.concessionaria.domain.Carro;
import com.concessionaria.domain.Desc_servico;
import com.concessionaria.domain.Servico;
import com.concessionaria.factory.Conexaofactory;

public class ServicoDAO {

	public List<Servico> listAll() throws Exception {
		ArrayList<Servico> list = new ArrayList<Servico>();

		try {
			Connection con = Conexaofactory.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT c.placa, d.descricao, s.id,  s.periodo FROM servico s inner join carro c on s.placa=c.placa "
					+ "inner join desc_servico d on s.descricao=d.id;");
			while (rs.next()) {

				Carro c = new Carro();
				c.setPlaca(rs.getString("c.placa"));
				
				Desc_servico d = new Desc_servico();
				d.setDescricao(rs.getString("d.descricao"));
				
				Servico s = new Servico();
				s.setId(rs.getInt("s.id"));
				s.setDescricao(d);
				s.setPeriodo(rs.getString("s.periodo"));
				s.setPlaca(c);

				list.add(s);
			}
			stmt.close();
			rs.close();
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados:" + ex);
			throw ex;
		}
		return list;
	}

	public Servico inserir(Servico servico, Carro carro, Desc_servico descricao) {
		String sqlInsert = "INSERT INTO servico(descricao, periodo, placa)VALUES(?, ?, ?);";

		try {
			Connection con = Conexaofactory.getConnection();
			PreparedStatement ps = con.prepareStatement(sqlInsert);

			ps.setInt(1, descricao.getId());
			ps.setString(2, servico.getPeriodo());
			ps.setString(3, carro.getPlaca());

			int ret = ps.executeUpdate();

			if (ret != 1) {
				throw new Exception("Valor não foi inserido por erro de banco.");
			}
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados" + ex);
			try {
				throw ex;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Erro ao inserir!");
				e.printStackTrace();
			}
		}
		return servico;
	}

}
