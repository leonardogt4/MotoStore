package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Model.Cliente;
import Model.Fabricante;
import Model.Modelo;
import Model.Motocicleta;
import Model.Saida;
import Model.Venda;
import Model.Vendedor;
import Utils.ConnectionUtil;

public class SaidaDao {
	
	private static SaidaDao instanceRep;
	private ArrayList<Saida> listaSaidas;
	private Connection con = ConnectionUtil.getConnection();
	
	public static SaidaDao obterInstancia(){
		if (instanceRep == null) {
			instanceRep = new SaidaDao();
		}
		return instanceRep;
	}//SINGLETON
	
	
	public SaidaDao(){//conexao com o banco
		this.listaSaidas = new ArrayList<Saida>();
	}
	
	//Inserir Saida no Banco
    public void inserir(Saida saida) throws SQLException{
    	try {
            String query = "INSERT INTO saida ( dataSaida, codCliente, codMotocicleta) "
            		+ "VALUES ( ?, ?, ?)";
            
            PreparedStatement pst = con.prepareStatement(query);
            pst.setDate(1, new java.sql.Date(saida.getDataSaida().getTime()));
            
            pst.setInt(2, saida.getCliente().getCodCliente() );
            pst.setInt(3, saida.getMotocicleta().getCodMotocicleta() );          
            
            pst.execute();
            System.out.println("Passou no Dao com sucesso");
            con.commit();
         //   con.close();
        } catch (SQLException ex) {
        	con.rollback();
            ex.printStackTrace();
            ex.getMessage();
            
        }
    }
    
    //Lista todos as Saidas cadastradas quando o campo de pesquisa da View tiver vazio
    public ArrayList<Saida> listarTodos() throws SQLException{
    	
    	 try {
    		 
    		 this.listaSaidas = new ArrayList<Saida>();
             Statement st = con.createStatement();
             String query = "SELECT c.nomeCliente, s.dataSaida, s.codSaida	" +
        		"FROM saida s " +
        		"JOIN cliente c on s.codCliente = c.codCliente";
           
             ResultSet rs = st.executeQuery(query);
//             System.err.println(query);          
             while(rs.next()){
             	Cliente cli = new Cliente();
             	cli.setNomeCliente(rs.getString("c.nomeCliente"));
             	
             	 Saida sai = new Saida();
             	 sai.setCliente(cli);
                  sai.setDataSaida(rs.getDate("s.dataSaida"));
                  sai.setCodSaida(rs.getInt("s.codSaida"));
                  
                  
                  listaSaidas.add(sai);
             }
             
             con.commit();
         } catch (SQLException ex) {
        	 con.rollback();
             ex.printStackTrace();
         }
         
         return this.listaSaidas;
    	
    }
    
    public ArrayList<Saida> consultarNome(Saida saida){
    	
        String sql = "SELECT c.nomeCliente, s.dataSaida	" +
        		"FROM saida s " +
        		"JOIN cliente c on s.codCliente = c.codCliente WHERE c.nomeCliente LIKE ?";   
        ArrayList<Saida> listaCliente = new ArrayList<>();         
        
        try{
        	PreparedStatement query = con.prepareStatement(sql);

            query.setString( 1, "%"+saida.getCliente().getNomeCliente()+"%");
            /*query.setDate(2, new java.sql.Date(saida.getDataSaida().getTime()));*/
            
            
//         Exibe a query se ela for executada com sucesso   
//		   System.err.println(query);
            
            ResultSet rs = query.executeQuery();
            
            while(rs.next()){
            	Cliente cli = new Cliente();
            	cli.setNomeCliente(rs.getString("c.nomeCliente"));
            	
            	 Saida sai = new Saida();
            	 sai.setCliente(cli);
                 sai.setDataSaida(rs.getDate("s.dataSaida"));
                 
                 
                 
                 listaCliente.add(sai);
            }
//            rs.close();
            
//            con.close();
              
        } catch(SQLException ex){
        	ex.printStackTrace();
        }  
        return listaCliente;
    }
    
 
    public void remover(Integer id) throws Exception{
	     try {
	        String query = "DELETE FROM saida WHERE codSaida = ?";
	        PreparedStatement st = con.prepareStatement(query);
	        st.setInt(1, id);
	        st.executeUpdate();
	        
	        con.commit();
	    } catch (SQLException ex) {
	 	   con.rollback();
	        ex.printStackTrace();
	
	    }
}
 
 
}
