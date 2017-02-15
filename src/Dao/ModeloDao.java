package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Fabricante;
import Model.Modelo;
import Model.Motocicleta;
import Utils.ConnectionUtil;

public class ModeloDao {

		
		private static ModeloDao instanceRep;
		private ArrayList<Modelo> listaModelo;
		private Connection con = ConnectionUtil.getConnection();
		
		public static ModeloDao obterInstancia(){
			if (instanceRep == null) {
				instanceRep = new ModeloDao();
			}
			return instanceRep;
		}//SINGLETON
		
		
		public ModeloDao(){//conexao com o banco
			this.listaModelo = new ArrayList<Modelo>();
		}
		
		
		  public ArrayList<Modelo> listarModelo_Fab() throws SQLException{
		    	
		    	 try {
		    		 
		    		 this.listaModelo = new ArrayList<Modelo>();
		             Statement st = con.createStatement();
		             String query ="Select  m.nomeModelo,m.codModelo from modelo m " + 
		        		 		   " join fabricante f on m.codFabricante= f.codFabricante";
		             ResultSet rs = st.executeQuery(query);
		             
		             while( rs.next() ){
		            	 Modelo m = new Modelo();
		            	 Fabricante  f = new Fabricante();
		            	 
		            	 
		            	 m.setNomeModelo(rs.getString("m.nomeModelo"));
		            	 m.setCodModelo(rs.getInt("m.codModelo"));
		            	 m.setFabricante(f);
		            	 
		            	 
		              
		                
		                 this.listaModelo.add(m);
		             }
		             
		             con.commit();
//		             con.close();
		         } catch (SQLException ex) {
//		        	 con.rollback();
		             ex.printStackTrace();
		         }
		         
		         return this.listaModelo;
		    	
		    }
		    

}
