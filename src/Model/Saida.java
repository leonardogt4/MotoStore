package Model;

import java.util.Date;

public class Saida {
	
	private Integer codSaida;
	private Date dataSaida;
	private Cliente cliente;
	private Motocicleta motocicleta;
	
	
	public Saida() {
		
	}

	public Saida(Integer codSaida, Date dataSaida, Cliente cliente,
			Motocicleta motocicleta) {
		
		this.codSaida = codSaida;
		this.dataSaida = dataSaida;
		this.cliente = cliente;
		this.motocicleta = motocicleta;
	}
	
	public Integer getCodSaida() {
		return codSaida;
	}
	public void setCodSaida(Integer codSaida) {
		this.codSaida = codSaida;
	}
	public Date getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Motocicleta getMotocicleta() {
		return motocicleta;
	}
	public void setMotocicleta(Motocicleta motocicleta) {
		this.motocicleta = motocicleta;
	}



	@Override
	public String toString() {
		return "Saida [codSaida=" + codSaida + ", dataSaida=" + dataSaida
				+ ", cliente=" + cliente + ", motocicleta=" + motocicleta
				 + "]";
	}
	
	
	
	
	
	
	

}
