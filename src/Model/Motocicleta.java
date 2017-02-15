package Model;

public class Motocicleta {
	
	private Integer codMotocicleta;
	private String placa;
	private String cor;
	private Double km;
	private String estilo;
	private String categoria;
	private Integer ano;
	private String combustivel;
	private Modelo modelo;
	private Fabricante fabricante;
	private Double valorMoto;
	
	public Motocicleta() {
		
	}

	public Motocicleta(Integer codMotocicleta, String placa, String cor,
			Double km, String estilo, String categoria, Integer ano,
			String combustivel, Modelo modelo, Fabricante fabricante,
			Double valorMoto) {
		
		this.codMotocicleta = codMotocicleta;
		this.placa = placa;
		this.cor = cor;
		this.km = km;
		this.estilo = estilo;
		this.categoria = categoria;
		this.ano = ano;
		this.combustivel = combustivel;
		this.modelo = modelo;
		this.fabricante = fabricante;
		this.valorMoto = valorMoto;
	}








	public Double getValorMoto() {
		return valorMoto;
	}








	public void setValorMoto(Double valorMoto) {
		this.valorMoto = valorMoto;
	}








	public Fabricante getFabricante() {
		return fabricante;
	}




	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}




	public Integer getCodMotocicleta() {
		return codMotocicleta;
	}

	public void setCodMotocicleta(Integer codMotocicleta) {
		this.codMotocicleta = codMotocicleta;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Double getKm() {
		return km;
	}

	public void setKm(Double km) {
		this.km = km;
	}


	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	@Override
	public String toString() {
		return this.modelo.getNomeModelo();
	}
	
	
	
	
	

}
