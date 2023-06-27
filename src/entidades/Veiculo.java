package entidades;

public class Veiculo {
	private int idveiculo;
	private String marca;
	private String modelo;
	private int ano;
	private Vendedor vendedor;
	private double valor;
	
    public Veiculo(int idveiculo, String marca, String modelo, int ano, double valor, Vendedor vendedor) {
        
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.idveiculo = idveiculo;
        this.vendedor = vendedor;
        this.valor = valor;
    }
	public Veiculo() {
		
	}
	public int getIdveiculo() {
		return idveiculo;
	}

	public void setIdveiculo(int idveiculo) {
		this.idveiculo = idveiculo;
	}		

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public Vendedor getVendedor() {
		return vendedor;
	}
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
}
