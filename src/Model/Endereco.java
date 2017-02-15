package Model;

public class Endereco {
	
	private Integer codEndereco;
	private String cep;
	private String cidade;
	private String rua;
	private Integer numero;
	
	public Endereco() {
		
	}

	public Integer getCodEndereco() {
		return codEndereco;
	}

	public void setCodEndereco(Integer codEndereco) {
		this.codEndereco = codEndereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "Endereco [codEndereco=" + codEndereco + ", cep=" + cep
				+ ", cidade=" + cidade + ", rua=" + rua + ", numero=" + numero
				+ "]";
	}
	
	
	
	

}
