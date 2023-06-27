package entidades;

public class Vendedor {
	private int idVendedor;
	private String nome;
	private String email;
	
	public Vendedor() {
		
	}
	public Vendedor(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}
	public Vendedor(int idVendedor, String nome, String email) {
		this.idVendedor = idVendedor;
		this.email = email;
		this.nome = nome;
	}
	public int getIdVendedor() {
		return idVendedor;
	}
	public void setIdVendedor(int idVendedor) {
		this.idVendedor = idVendedor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setSenha(String email) {
		this.email = email;
	}
	
}
