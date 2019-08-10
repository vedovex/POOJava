package com.concessionaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.concessionaria.domain.Carro;
import com.concessionaria.factory.Conexaofactory;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarroDAO {

	public List<Carro> listAll() throws Exception {
		ArrayList<Carro> list = new ArrayList<Carro>();

		try {
			Connection con = Conexaofactory.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT placa, cor, modelo, ano, preco,situacao " + "FROM carro"
					+ " where carro.placa not in(select venda.placa from venda);");
			while (rs.next()) {

				Carro c = new Carro();
				c.setPlaca(rs.getString("placa"));
				c.setCor(rs.getString("cor"));
				c.setModelo(rs.getString("modelo"));
				c.setAno(rs.getString("ano"));
				c.setPreco(rs.getDouble("preco"));
				c.setSituacao(rs.getString("situacao"));
				list.add(c);
			}
			stmt.close();
			rs.close();
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados:" + ex);
			throw ex;
		}
		return list;
	}

	public Carro editar(Carro carro) throws Exception {
		String sqlInsert = "UPDATE carro SET  cor= ?, modelo=?, ano=?, preco=?, situacao=? where placa = ?";

		try {
			Connection con = Conexaofactory.getConnection();
			PreparedStatement ps = con.prepareStatement(sqlInsert);

			ps.setString(1, carro.getCor());
			ps.setString(2, carro.getModelo());
			ps.setString(3, carro.getAno());
			ps.setDouble(4, carro.getPreco());
			ps.setString(5, carro.getSituacao());
			ps.setString(6, carro.getPlaca());
			int ret = ps.executeUpdate();
			System.out.println("ATUALIZADO!!");
			if (ret != 1) {
				throw new Exception("Valor não foi alterado por erro de banco.");
			}
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados" + ex);
			throw ex;
		}
		return carro;
	}

	public Carro excluir(Carro carro) throws Exception {

		String sqlExcluir = "DELETE FROM carro where placa = ?";
		try {
			Connection con = Conexaofactory.getConnection();
			PreparedStatement ps = con.prepareStatement(sqlExcluir);

			ps.setString(1, carro.getPlaca());

			int ret = ps.executeUpdate();
			if (ret != 1) {
				throw new Exception("Valor não foi excluído por erro de banco.");
			}
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados" + ex);
			throw ex;
		}
		return carro;
	}

	public Carro inserir(Carro carro) {
		String sqlInsert = "INSERT INTO carro (PLACA, COR, MODELO, ANO, PRECO, SITUACAO) VALUES ( ?, ?, ?, ?, ?, ?)";

		try {
			Connection con = Conexaofactory.getConnection();
			PreparedStatement ps = con.prepareStatement(sqlInsert);

			ps.setString(1, carro.getPlaca());
			ps.setString(2, carro.getCor());
			ps.setString(3, carro.getModelo());
			ps.setString(4, carro.getAno());
			ps.setDouble(5, carro.getPreco());
			ps.setString(6, carro.getSituacao());

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
		return carro;
	}

}