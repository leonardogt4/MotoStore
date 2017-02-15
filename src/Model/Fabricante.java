package Model;

import java.util.ArrayList;

public class Fabricante {
	
	private Integer codFabricante;
	private String nomeFabricante;
	private Modelo modelo;
	private ArrayList<Modelo> modelos;
	
	public Fabricante() {
		
	}
	

	public Fabricante(Integer codFabricante, String nomeFabricante,
			Modelo modelo) {
		super();
		this.codFabricante = codFabricante;
		this.nomeFabricante = nomeFabricante;
		this.modelo = modelo;
	}

	

	public ArrayList<Modelo> getModelos() {
		return modelos;
	}


	public void setModelos(ArrayList<Modelo> modelos) {
		this.modelos = modelos;
	}


	public Integer getCodFabricante() {
		return codFabricante;
	}

	public void setCodFabricante(Integer codFabricante) {
		this.codFabricante = codFabricante;
	}

	public String getNomeFabricante() {
		return nomeFabricante;
	}

	public void setNomeFabricante(String nomeFabricante) {
		this.nomeFabricante = nomeFabricante;
	}


	@Override
	public String toString() {
		return this.nomeFabricante;
	}

	public Modelo getModelo() {
		if ( modelo == null ) {
			modelo = new Modelo();
		}
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	
	
	

}
