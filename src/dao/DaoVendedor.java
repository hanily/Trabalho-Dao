package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Vendedor;

public class DaoVendedor {
	
	public boolean inserir(Vendedor vend) throws SQLException {
		
		Connection conexao = ConnectionFactory.getConexao();
		
		String sql = "INSERT INTO vendedor (nome, email) VALUES (?, ?);";
		PreparedStatement ps = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		
		ps.setString(1, vend.getNome());
		ps.setString(2, vend.getEmail());

		int linhasAfetadas = ps.executeUpdate();
		
		ResultSet r = ps.getGeneratedKeys();
		
		if( r.next() ) {
			int id = r.getInt(1);	
			vend.setIdVendedor(id);
		}
		
		return (linhasAfetadas == 1 ? true : false);
		
	}
	
	public Vendedor buscarPorId(int idBuscado) throws SQLException {
		Connection con = ConnectionFactory.getConexao();
		
		String sql = "select * from vendedor where idvendedor = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idBuscado);
		
		ResultSet result = ps.executeQuery();
		
		Vendedor vend = null;
		
		if( result.next() ) {
			int id = result.getInt("idvendedor");
			String nome = result.getString("nome");
			String email = result.getString("email");
			
			vend = new Vendedor(id, nome, email);
		}
		
		return vend;
	}
	
	public List<Vendedor> buscarT() throws SQLException {
		Connection con = ConnectionFactory.getConexao();
		
		String sql = "select * from vendedor";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ResultSet result = ps.executeQuery();
		
		List<Vendedor> vend = new ArrayList<Vendedor>();
		
		while( result.next() ) {
			int id = result.getInt("idvendedor");
			String nome = result.getString("nome");
			String email = result.getString("email");
			
			Vendedor vende = new Vendedor(id, nome, email);
	
			vend.add(vende);
		}
		
		return vend;
	}
}
