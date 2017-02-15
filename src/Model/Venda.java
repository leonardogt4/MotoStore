package Model;

import java.util.Date;

public class Venda {

	private Integer id;
	private String formaPagamento;
	private Date dataVenda;
	private Double valor;
	private int quantidade = 1;
	private Motocicleta motocicleta;
	private Fabricante fornecedor;
	private Cliente cliente;
	private Vendedor vendedor;
	private Double valorTotal;
	
	
	public Venda() {
		
	}
	


	public Venda(Integer id, String formaPagamento, Date dataVenda,
			Double valor, int quantidade, Motocicleta motocicleta,
			Fabricante fornecedor, Cliente cliente, Vendedor vendedor,
			Double valorTotal) {
		
		this.id = id;
		this.formaPagamento = formaPagamento;
		this.dataVenda = dataVenda;
		this.valor = valor;
		this.quantidade = quantidade;
		this.motocicleta = motocicleta;
		this.fornecedor = fornecedor;
		this.cliente = cliente;
		this.vendedor = vendedor;
		this.valorTotal = valorTotal;
	}



	public Integer getCodVenda() {
		return id;
	}

	public void setCodVenda(Integer codVenda) {
		this.id = codVenda;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Motocicleta getMotocicleta() {
		return motocicleta;
	}

	public void setMotocicleta(Motocicleta motocicleta) {
		this.motocicleta = motocicleta;
	}

	public Fabricante getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fabricante fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor){
		this.vendedor = vendedor;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

		
	public Date getDataVenda() {
		return dataVenda;
	}



	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}



	@Override
	public String toString() {
		return this.cliente.getNomeCliente() + this.vendedor.getNomeVendedor() + this.valor;
	}
	
	


	public void setMotocicleta(String string) {
		// TODO Stub de método gerado automaticamente
		
	}



	public void setFornecedor(String string) {
		// TODO Stub de método gerado automaticamente
		
	}


	public void setCliente(String string) {
		// TODO Stub de método gerado automaticamente
		
	}


	public void setDataVenda(String string) {
		// TODO Stub de método gerado automaticamente
		
	}



	


	
}
