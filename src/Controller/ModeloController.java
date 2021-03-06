package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Dao.FabricanteDao;
import Dao.ModeloDao;
import Model.Fabricante;
import Model.Modelo;



public class ModeloController {

private static ModeloController instanciaRep;
	
	
	public static ModeloController obterInstancia(){
	        if ( instanciaRep == null ){
	            instanciaRep = new ModeloController();
	        }
	        return instanciaRep;
	    }
	
	public Boolean validaCamposModelo(Modelo listaModelo) throws ModeloException {
		
		
		if(listaModelo.getNomeModelo().equals("") || listaModelo == null || listaModelo.getNomeModelo().isEmpty()){
			throw new ModeloException("O Modelo nao pode estar vazio");
           
		}
		
		
		return true;
	}
	
	
	public void inserir(Modelo listaModelo) throws ModeloException, SQLException{
		//limparCampos();
		
		if(validaCamposModelo(listaModelo) == true){
			
			try {
				FabricanteDao.obterInstancia().inserirModelo(listaModelo);
			} catch (ModeloException e) {
	            JOptionPane.showMessageDialog(null,
	                    e.getMessage(),
	                    "ERRO Controller ao inserir ",
	                    JOptionPane.ERROR_MESSAGE);
	            return;
	        }
			
		}else{
			JOptionPane.showMessageDialog(null,
                    "Algum campo do modelo inv�lido!",
                    "Erro de valida��o",
                    JOptionPane.ERROR_MESSAGE);
			return;
		}
		
			
	}

	public void editarFornecedor (Modelo modelo) throws ModeloException, SQLException {
		
		if(validaCamposModelo(modelo) == true){
		
			if(	modelo.getNomeModelo() == null || modelo.getNomeModelo().equals("")){
				throw new ModeloException("Nome Obrigat�rio");
			} 
			
			FabricanteDao.obterInstancia().editarFornecedor(modelo);
			System.out.println("Passou no Controller");
		}
	}
	
	public void remover(Integer id) throws Exception{
		if (id == null){
			throw new ModeloException("Selecione um ");
		}
		FabricanteDao.obterInstancia().removerFabricante(id);
	}
	
	public ArrayList<Modelo> listaTodos() throws ModeloException {
		return FabricanteDao.obterInstancia().listaTodosModelo();
	}
	
	public ArrayList<Modelo>  listarModelo_Fab() throws ModeloException{
		
			try {
				return ModeloDao.obterInstancia().listarModelo_Fab();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return null;
		
	
}
	public ArrayList<Modelo> listaCampos(Modelo model) throws ModeloException {
		try {
			return FabricanteDao.obterInstancia().listaCampos(model);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}



}
