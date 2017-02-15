package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Dao.FabricanteDao;
import Model.Fabricante;

public class FabricanteController {

private static FabricanteController instanciaRep;
	
	
	public static FabricanteController obterInstancia(){
	        if ( instanciaRep == null ){
	            instanciaRep = new FabricanteController();
	        }
	        return instanciaRep;
	    }
	
	public Boolean validaCamposFabricante(Fabricante listafabricante) throws FabricanteException {
		
		
		if(listafabricante.getNomeFabricante().equals("") || listafabricante == null || listafabricante.getNomeFabricante().isEmpty()){
			throw new FabricanteException("O fabricante nao pode estar vazio");
           
		}
		
		
		return true;
	}
	
	


//	public void editar (Fabricante fab) throws FabricanteException, SQLException {
//		
//		if(validaCamposFabricante(fab) == true){
//		
////			if(	listafabricante.getNomeFabricante() == null || listafabricante.getNomeFabricante().equals("")){
////				throw new FabricanteException("Nome Obrigatório");
////			} 
////			
//			FabricanteDao.obterInstancia().editarFabricante(fab);
//			System.out.println("Passou no COntroller");
//		}
//	}
	
	public void remover(Integer id) throws FabricanteException{
		if (id == null){
			throw new FabricanteException("Selecione um Fabricante");
		}
		try {
			FabricanteDao.obterInstancia().removerFabricante(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Fabricante> listaFabricantes() throws FabricanteException {
		return FabricanteDao.obterInstancia().listaTodosFabricante();
	}
	
}
