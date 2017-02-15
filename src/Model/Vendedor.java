package Model;

public class Vendedor {
	
	private Integer codVendedor;
	private String nomeVendedor;
	
	public Vendedor() {
		
	}

	public Vendedor(Integer codVendedor, String nomeVendedor) {
		
		this.codVendedor = codVendedor;
		this.nomeVendedor = nomeVendedor;
	}

	public Integer getCodVendedor() {
		return codVendedor;
	}

	public void setCodVendedor(Integer codVendedor) {
		this.codVendedor = codVendedor;
	}

	
	public String getNomeVendedor() {
		return nomeVendedor;
	}

	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}

	@Override
	public String toString() {
		return this.nomeVendedor;
	}
	

	
	
	
	
	

}
