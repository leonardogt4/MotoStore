package Model;

import java.util.ArrayList;

public class Modelo {

	private Integer codModelo;
	private String nomeModelo;
	private Fabricante fabricante;
	private ArrayList<Fabricante> fabricantes;
	
	public Modelo() {
		
	}
	
	public Modelo(Integer codModelo, String nomeModelo, Fabricante fabricante) {
		super();
		this.codModelo = codModelo;
		this.nomeModelo = nomeModelo;
		this.fabricante = fabricante;
	}



	public Integer getCodModelo() {
		return codModelo;
	}

	public void setCodModelo(Integer codModelo) {
		this.codModelo = codModelo;
	}

	public String getNomeModelo() {
		return nomeModelo;
	}

	public void setNomeModelo(String nomeModelo) {
		this.nomeModelo = nomeModelo;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	
	

	public ArrayList<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(ArrayList<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	@Override
	public String toString() {
		return this.nomeModelo;
	
	
	
	}
	
	
}
