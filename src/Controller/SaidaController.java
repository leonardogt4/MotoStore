package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Dao.ClienteDao;
import Dao.SaidaDao;
import Dao.VendaDao;
import Model.Cliente;
import Model.Motocicleta;
import Model.Saida;





public class SaidaController {

private static SaidaController instanciaRep;
	
	
	public static SaidaController obterInstancia(){
	        if ( instanciaRep == null ){
	            instanciaRep = new SaidaController();
	        }
	        return instanciaRep;
	    }
	
	public Boolean validaCamposSaida(Saida listaSaidas) throws Exception {
		
		
		if(listaSaidas.getCliente() == null ){
			throw new Exception("O Cliente não pode estar vazio");
		}
		
		if(listaSaidas.getMotocicleta() == null){
			throw new Exception("O Modelo não pode estar vazio");
		}
		if (listaSaidas.getDataSaida() == null){
			throw new Exception("A data não pode estar vazia");
		}
		
		return true;
	}
	
	
	public void inserir(Saida listaSaidas) throws Exception, SQLException{
		//limparCampos();
		
		if(validaCamposSaida(listaSaidas) == true){
			
			try {
				
				SaidaDao.obterInstancia().inserir(listaSaidas);
				System.out.println("Passou no Controller com sucesso");
			} catch (Exception e) {
	            e.printStackTrace();
	            return;
	        }
			
		}else{
			JOptionPane.showMessageDialog(null,
                    "Algum campo da saida inválido!",
                    "Erro de validação",
                    JOptionPane.ERROR_MESSAGE);
			return;
		}
		
			
	}

	public void editar (Saida listaSaidas) throws Exception, SQLException {
		
		if(validaCamposSaida(listaSaidas) == true){
			

			try {
				//SaidaDao.obterInstancia().editar(listaSaidas);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
	                    e.getMessage(),
	                    "ERRO Controller ao editar a Saida",
	                    JOptionPane.ERROR_MESSAGE);
	            return;
			}
		}else{
			JOptionPane.showMessageDialog(null,
                    "Algum campo da venda inválido!",
                    "Erro de validação",
                    JOptionPane.ERROR_MESSAGE);
			return;
		}		
	}
		
	public ArrayList<Saida> listas() throws Exception {
		return SaidaDao.obterInstancia().listarTodos();
		
	}
	
	public ArrayList<Saida> consultarNome(Saida saida){
    	return SaidaDao.obterInstancia().consultarNome(saida);
    }
	
	public void excluir(Integer id) throws Exception{
		/*if (id == null){
			throw new Exception("Selecione uma Saida ");*/
		//}
		SaidaDao.obterInstancia().remover(id);
	} 
	

	

}
