package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Veiculo;
import entidades.Vendedor;

public class DaoVeiculo {
	
	public boolean inserir(Veiculo veiculo) throws SQLException {
				
		Connection conexao = ConnectionFactory.getConexao();
		
		String sql = "insert into veiculo (idveiculo, marca, modelo, ano, idvendedor, valor) values(?, ?, ? , ? , ?, ?);";
		PreparedStatement ps = conexao.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

		ps.setInt(1, veiculo.getIdveiculo());
		ps.setString(2, veiculo.getMarca());
		ps.setString(3, veiculo.getModelo());
		ps.setInt(4, veiculo.getAno());
		ps.setInt(5, veiculo.getVendedor().getIdVendedor());
		ps.setDouble(6, veiculo.getValor());
		
		int linhasAfetadas = ps.executeUpdate();
		
		ResultSet r = ps.getGeneratedKeys();
		
		if( r.next() ) {
			int id = r.getInt(1);	
			veiculo.setIdveiculo(id);
		}
		
		return (linhasAfetadas == 1 ? true : false);
		
	}

	public boolean atualizar(Veiculo veiculo) throws SQLException {

		Connection con = ConnectionFactory.getConexao();
		

		String sql = "update veiculo set marca = ?, modelo = ?, ano = ?, valor = ? where idveiculo = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, veiculo.getMarca());
		ps.setString(2, veiculo.getModelo());
		ps.setInt(3, veiculo.getAno());
		ps.setDouble(4, veiculo.getValor());
		ps.setInt(5, veiculo.getIdveiculo());
		

		if( ps.executeUpdate() == 1) {
			return true;
		}else {
			return false;
		}
	}

	public boolean excluir(int id) throws SQLException {
		Connection conn = ConnectionFactory.getConexao();
		
		String sql = "delete from veiculo where idveiculo = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		
		if( ps.executeUpdate() == 1) {
			return true;
		}else {
			return false;
		}
	}

	public Veiculo buscar(int idBuscado) throws SQLException {
		
		Connection con = ConnectionFactory.getConexao();
		
		String sql = "select * from VEICULO where IDVEICULO = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idBuscado);
		
		ResultSet result = ps.executeQuery();
		
		Veiculo veiculo = null;
		
		if( result.next() ) {
			int id = result.getInt("idveiculo");
			String marca = result.getString("marca");
			String modelo = result.getString("modelo");
			int ano = result.getInt("ano");
			double valor = result.getDouble("valor");
			int idVend = result.getInt("idvendedor");
			
			DaoVendedor daoVend = new DaoVendedor();
			Vendedor ven = daoVend.buscarPorId(idVend);
			
			veiculo = new Veiculo(id, marca, modelo, ano, valor, ven);
		}
		return veiculo;
	}

	public List<Veiculo> buscarT() throws SQLException {
		Connection con = ConnectionFactory.getConexao();
		
		String sql = "select * from veiculo";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ResultSet result = ps.executeQuery();
		
		ArrayList<Veiculo> veiculo = new ArrayList<Veiculo>();
		
		DaoVendedor daoVend = new DaoVendedor();

		while( result.next() ) {
			int id = result.getInt("idveiculo");
			String marca = result.getString("marca");
			String modelo = result.getString("modelo");
			int ano = result.getInt("ano");
			double valor = result.getDouble("valor");
			int idVendedor = result.getInt("idvendedor");
			
			Vendedor ven = daoVend.buscarPorId(idVendedor);
			
			Veiculo v = new Veiculo(id, marca,modelo, ano,valor, ven);
	
			veiculo.add(v);
		}
		
		return veiculo;
	}

	public List<Veiculo> pesquisarPorModelo(String texto) throws SQLException {
		Connection con = ConnectionFactory.getConexao();
		
		String sql = "select * from veiculo where modelo like ? ";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, "%"+texto+"%");
		
		ResultSet result = ps.executeQuery();
		
		List<Veiculo> veiculo = new ArrayList<Veiculo>();
		
		DaoVendedor daoVend = new DaoVendedor();
		
		while( result.next() ) {
			int id = result.getInt("idveiculo");
			String marca = result.getString("marca");
			String modelo = result.getString("modelo");
			int ano = result.getInt("ano");
			int idVend = result.getInt("idvendedor");
			double valor = result.getDouble("valor");
			
			Vendedor vend = daoVend.buscarPorId(idVend);
			Veiculo v = new Veiculo(id, marca, modelo, ano,valor, vend);
	
			veiculo.add(v);
		}
		
		return veiculo;
	}
	
}
