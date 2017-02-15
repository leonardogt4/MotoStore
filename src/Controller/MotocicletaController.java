package Controller;

import java.awt.HeadlessException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import Dao.MotocicletaDao;
import Model.Motocicleta;




public class MotocicletaController {
	
	private static MotocicletaController instanciaRep;

	public static MotocicletaController obterInstancia(){
	    if ( instanciaRep == null ){
	        instanciaRep = new MotocicletaController();
	    }
	    return instanciaRep;
	}

		
	public boolean validaMotocicleta (Motocicleta moto) throws Exception {//Valida campos
		if (moto.getKm().equals("") || moto.getKm() == null){
			throw new Exception("Km Obrigatória");
			
		}
		if (moto.getKm() < 0 || moto.getKm() > 300000.00){// Inseri a km entre 0 300000.00
			throw new Exception("Insira uma km valida");
			
		}
		if ( moto.getPlaca().length() != 8 ){
			throw new Exception("Insira uma Placa Valida");
			
		}
					
		
		if ( moto.getAno().equals("")  && moto.getKm().equals("")) {
			throw new Exception("Campos obrigatórios");
		}
		
		

		if (moto.getAno() < 1900 || moto.getAno() > 2030 || moto.getAno() == null){
			throw new Exception("Insira um ano valido");
			
		}
		if (moto.getEstilo() == null) {
			throw new Exception("Escolha um estilo para moto");
			
		}
		if (moto.getCategoria() == null){
			throw new Exception("Escolha o modelo da moto");
			
		}
		if (moto.getCor() == null){
			throw new Exception("Escolha uma cor para moto");
		
		}
//		if (moto.getModelo().getFabricante() == null ){
//			throw new Exception("Escolha um fabricante para moto");
//		}
		else{
			return true;
		}
	}

	
	
	public void inserir (Motocicleta moto) throws  Exception{//Chama o dao para inserir no bd
		if(validaMotocicleta(moto) == true){
			
			try {
				MotocicletaDao.obterInstancia().inserir(moto);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
	                    e.getMessage(),
	                    "ERRO Controller ao inserir Motocicleta",
	                    JOptionPane.ERROR_MESSAGE);
	            return;
			}
			
		}else{
			JOptionPane.showMessageDialog(null,
                    "Algum campo da Motocicleta inválido!",
                    "Erro de validação",
                    JOptionPane.ERROR_MESSAGE);
			return;
		}		
	}
	
	
	public void editar (Motocicleta moto) throws  Exception{//Chama o dao para editar e insirir no bd
//		if (validaMotocicleta(moto) == true){
			
		
			try {
				MotocicletaDao.obterInstancia().editar(moto);
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,
	                    e.getMessage(),
	                    "ERRO Controller ao editar Motocicleta " + e.getStackTrace(),
	                    JOptionPane.ERROR_MESSAGE);
	            return;
			}
////		}else{
//			JOptionPane.showMessageDialog(null,
//                    "Algum campo da Motocicleta inválido!",
//                    "Erro de validação",
//                    JOptionPane.ERROR_MESSAGE);
//			return;
//		}		
	}
	
	public void remover(Integer id) throws Exception {//remove do bd
		if ( id == null){
            throw new Exception("Selecione uma Motocicleta");
        }
        MotocicletaDao.obterInstancia().remover(id);
	}
	
	
	public ArrayList<Motocicleta> listaModelos(){//lista todos
		
		try {
			return MotocicletaDao.obterInstancia().listarModeloMotocicleta();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
		public ArrayList<Motocicleta> listaPesquisaMoto(){//lista todos
			
			try {
				return MotocicletaDao.obterInstancia().listarPesquisaMotocicleta();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		
		}
			

			public ArrayList<Motocicleta> listaCadastroMoto(){//lista todos
				
				try {
					return MotocicletaDao.obterInstancia().listarCadastroMotocicleta();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
					
			
		
		
		
		
	}
	
	
		
		
		
	
	
	
}
	
	
	
	
	
