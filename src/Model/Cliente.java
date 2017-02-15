package Model;

public class Cliente {
	
	private Integer codCliente;
	private String nomeCliente;
	private String telefone;
	private String celular;
	private String email;
	private String cnh;
	private String cpf;
	private Double renda;
	private Endereco endereco;
	
	public Cliente(Integer codCliente, String nomeCliente, String telefone,
			String celular, String email, String cnh, String cpf, Double renda,
			Endereco endereco) {
		this.codCliente = codCliente;
		this.nomeCliente = nomeCliente;
		this.telefone = telefone;
		this.celular = celular;
		this.email = email;
		this.cnh = cnh;
		this.cpf = cpf;
		this.renda = renda;
		this.endereco = endereco;
	}
	

	public Cliente() {
		
	}

	public Cliente(String string) {
		// TODO Stub de construtor gerado automaticamente
	}


	public Integer getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Integer codCliente) {
		this.codCliente = codCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCnh() {
		return cnh;
	}

	public void setCnh(String cnh) {
		this.cnh = cnh;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Double getRenda() {
		return renda;
	}

	public void setRenda(Double renda) {
		this.renda = renda;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	@Override
	public String toString() {
		return this.nomeCliente;
	}


	
	
	
	

}
