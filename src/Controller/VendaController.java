package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Dao.VendaDao;
import Dao.VendedorDao;
import Model.Cliente;
import Model.Venda;
import Model.Vendedor;


public class VendaController {
	
	 private static VendaController instanciaRep;
	    
	    public static VendaController obterInstancia(){
	        if ( instanciaRep == null ){
	            instanciaRep = new VendaController();
	        }
	        return instanciaRep;
	    }
	    
	    public Boolean validaCamposVendas(Venda vendas) throws Exception {
			
//
//			if(vendas.getFornecedor() == null){
//				throw new Exception ("Selecione um Fabricante");
//			}
//			if(vendas.getMotocicleta() == null){
//				throw new Exception ("Selecione uma Motocicleta");
//			}
//			if (vendas.getCliente() == null){
//				throw new Exception("Selecione um Cliente");
//			}
//			if(vendas.getFormaPagamento() == null){
//				throw new Exception ("Selecione uma Forma de Pagamento");
//			}
//			
//			if(vendas.getDataVenda() == null){
//				throw new Exception("Selecione a Data da Venda ");
//			}
//			if (vendas.getValor() == null || vendas.getValor().equals("")){
//				throw new Exception("Selecione um valor para a venda");
//			}
//			if (vendas.getVendedor() == null){
//				throw new Exception("Selecione um vendedor");
//			}
//			else {
				return true;
			}		
//		}
	
	//Valida os campos de Venda e chama o metodo do Venda DAO pra gravar no Banco de Dados
	
	
	    public void inserir (Venda vendas) throws Exception{
	    	
	    	
					VendaDao.obterInstancia().inserir(vendas);
				
		
	    }
									
	//Valida os campos de Venda e chama o metodo do Venda DAO pra editar no Banco de Dados
	    public void editar (Venda vendas) throws  Exception, SQLException{
			if (validaCamposVendas(vendas) == true){
				
			
				try {
					VendaDao.obterInstancia().editar(vendas);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
		                    e.getMessage(),
		                    "ERRO Controller ao editar a Venda",
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
	
	//Chama o metodo do Venda  DAO pra excluir no Banco de Dados
	   public void remover(Integer id) throws Exception {
			if ( id == null){
	            throw new Exception("Selecione uma Venda");
	        }
	        VendaDao.obterInstancia().remover(id);
		}
	
	//Chama o metodo do ListaVendas e DAO pra consultar lista no Banco de Dados
	public ArrayList<Venda> listaVendas(){
		return VendaDao.obterInstancia().listarRelatorio();
	}
	
	public ArrayList<Vendedor> listaVendedor() {
		
		try {
			return VendedorDao.obterInstancia().listarTodos();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	

}
