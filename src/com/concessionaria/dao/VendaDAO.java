package com.concessionaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.concessionaria.domain.Carro;
import com.concessionaria.domain.Venda;
import com.concessionaria.factory.Conexaofactory;

public class VendaDAO {
	
	public List<Venda> listAll() throws Exception {
		ArrayList<Venda> list = new ArrayList<Venda>();

		try {
			Connection con = Conexaofactory.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT c.placa, c.modelo, c.situacao, c.preco, v.id, v.nome, v.cpf, v.data_nascimento, v.rua, v.n, v.cidade "
					+ "FROM venda v inner join carro c on v.placa=c.placa ");
			while (rs.next()) {

				Carro c = new Carro();
				c.setPlaca(rs.getString("c.placa"));
				c.setModelo(rs.getString("c.modelo"));
				c.setSituacao(rs.getString("c.situacao"));
				c.setPreco(rs.getDouble("c.preco"));
				
				Venda v = new Venda();
				v.setId(rs.getInt("v.id"));
				v.setNome(rs.getString("v.Nome"));
				v.setCpf(rs.getString("v.cpf"));
				v.setDataNascimento(rs.getString("v.data_nascimento"));
				v.setRua(rs.getString("v.rua"));
				v.setN(rs.getString("v.n"));
				v.setCidade(rs.getString("v.cidade"));
				v.setPlaca(c);

				list.add(v);

			}
			stmt.close();
			rs.close();
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados:" + ex);
			throw ex;
		}
		return list;
	}

	public Venda inserir(Venda venda, Carro carro) {
		String sqlInsert = "INSERT INTO venda (nome, cpf, data_nascimento, rua, n, cidade, placa) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {
			Connection con = Conexaofactory.getConnection();
			PreparedStatement ps = con.prepareStatement(sqlInsert);

			ps.setString(1, venda.getNome());
			ps.setString(2, venda.getCpf());
			ps.setString(3, venda.getDataNascimento());
			ps.setString(4, venda.getRua());
			ps.setString(5, venda.getN());
			ps.setString(6, venda.getCidade());
			ps.setString(7, carro.getPlaca());

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
		return venda;
	}

}
