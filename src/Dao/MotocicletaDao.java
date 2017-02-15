package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;






import Model.Cliente;
import Model.Fabricante;
import Model.Modelo;
import Model.Motocicleta;
import Utils.ConnectionUtil;

public class MotocicletaDao {
	private ArrayList<Motocicleta> listaCategoria;
	private static MotocicletaDao instanceRep;
	private ArrayList<Motocicleta> listaMotocicleta;
	private Connection con = ConnectionUtil.getConnection();
	
	public static MotocicletaDao obterInstancia(){
		if (instanceRep == null) {
			instanceRep = new MotocicletaDao();
		}
		return instanceRep;
	}//SINGLETON
	
	
	public MotocicletaDao(){//conexao com o banco
		this.listaMotocicleta = new ArrayList<Motocicleta>();
	}
	
	 public void inserir(Motocicleta moto) throws SQLException{
	    	try {
	            String query = "INSERT INTO motocicleta ( placa, cor, km, estilo, categoria, ano, combustivel,codModelo ) "
	            		+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
	            
	            PreparedStatement pst = con.prepareStatement(query);
	            pst.setString(1, moto.getPlaca() );
	            pst.setString(2, moto.getCor() );
	            pst.setDouble(3, moto.getKm() );
	            pst.setString(4, moto.getEstilo() );
	            pst.setString(5, moto.getCategoria() );
	            pst.setInt(6, moto.getAno() );
	            pst.setString(7, moto.getCombustivel() );
	            pst.setInt(8, moto.getModelo().getCodModelo());
	           
	             
	            pst.execute();
	            
	            con.commit();
	          //  con.close();
	        } catch (SQLException ex) {
	        	con.rollback();
	            ex.printStackTrace();
	        }
	 }
	 
	    
	    public ArrayList<Motocicleta> listarTodosMotocicleta() throws SQLException{
	    	
	    	 try {
	    		 
	    		 this.listaMotocicleta = new ArrayList<Motocicleta>();
	             Statement st = con.createStatement();
	             String query = "SELECT * FROM motocicleta";
	             ResultSet rs = st.executeQuery(query);
	             
	             while( rs.next() ){
	            	 Modelo model = new Modelo();
	            	 Motocicleta moto = new Motocicleta();
	            	 
	            	 model.setNomeModelo(rs.getString("nomeModelo"));
	            	 moto.setModelo(model);
	            	 
	            	 
	                 moto.setPlaca(rs.getString("placa"));
	                 moto.setCor(rs.getString("cor"));
	                 moto.setKm(rs.getDouble("km"));
	                 moto.setEstilo(rs.getString("estilo"));
	                 moto.setCategoria(rs.getString("categoria"));
	                 moto.setAno(rs.getInt("ano"));
	                 moto.setCombustivel(rs.getString("combustivel"));
	                
	                 this.listaMotocicleta.add( moto );
	             }
	             
	             con.commit();
	           //  con.close();
	         } catch (SQLException ex) {
	        //	 con.rollback();
	             ex.printStackTrace();
	         }
	         
	         return this.listaMotocicleta;
	    	
	    }
	      
	    public ArrayList<Motocicleta> listarModeloMotocicleta() throws SQLException{
	    	
	    	 try {
	    		 
	    		 this.listaMotocicleta = new ArrayList<Motocicleta>();
	             Statement st = con.createStatement();
	             String query ="Select  m.nomeModelo, m.codModelo, mot.codMotocicleta, mot.ano," +
	             		       " mot.km, mot.placa, mot.cor,  mot.estilo, mot.categoria, mot.combustivel," +
	             		       " f.nomeFabricante from motocicleta mot inner join modelo m on " +
	             		       "mot.codModelo= m.codModelo inner join fabricante f on " +
	             		       "f.codFabricante=m.codFabricante" ;
	             
	             ResultSet rs = st.executeQuery(query);
	             
	             while( rs.next() ){
	            	 Modelo model = new Modelo();
	            	 Motocicleta moto = new Motocicleta();
	            	 Fabricante f = new Fabricante();
	            	 
	            	 model.setFabricante(f);
	            	 
	            	 model.setNomeModelo(rs.getString("m.nomeModelo"));
	            	 model.setCodModelo(rs.getInt("m.codModelo"));
	            	
	            	 moto.setCodMotocicleta(rs.getInt("mot.codMotocicleta"));
	            	 moto.setAno(rs.getInt("mot.ano"));
	            	 moto.setKm(rs.getDouble("mot.km"));
	            	 moto.setPlaca(rs.getString("mot.placa"));
	            	 moto.setCor(rs.getString("mot.cor"));
	            	 moto.setEstilo(rs.getString("mot.estilo"));
	            	 moto.setCategoria(rs.getString("mot.categoria"));
	            	 moto.setCombustivel(rs.getString("mot.combustivel"));
	            	 moto.setModelo(model);
	            	 
	            	 f.setNomeFabricante(rs.getString("f.nomeFabricante"));
	            	 
	            	 
	              
	                
	                 this.listaMotocicleta.add( moto );
	             }
	             
	             con.commit();
//	             con.close();
	         } catch (SQLException ex) {
//	        	 con.rollback();
	             ex.printStackTrace();
	         }
	         
	         return this.listaMotocicleta;
	    	
	    }
	    
	    
	    //popular ID Modelo
	    public void popularIdentificacaoModelo(Modelo modelo) throws SQLException {
	        String sql = "SELECT codModelo FROM modelo WHERE nomeModelo = ?";

	        try{
	        	 PreparedStatement pst = con.prepareStatement(sql);

	            pst.setString(1, modelo.getNomeModelo());
	            ResultSet rs = pst.executeQuery();

	            while (rs.next()) {
	                modelo.setCodModelo(rs.getInt("codModelo"));
	            }
	        } catch(SQLException ex){
	            throw new SQLException("popularIdentificacao() "+ex.getMessage());
	        }
	    }
	    
	    //popular ID Motocicleta
	    public void popularIdentificacaoMotocicleta(Motocicleta moto) throws SQLException {
	        String sql = "SELECT codMotocicleta FROM motocicleta WHERE placa = ?";

	        try{
	        	 PreparedStatement pst = con.prepareStatement(sql);

	            pst.setString(1, moto.getPlaca() );
	            ResultSet rs = pst.executeQuery();

	            while (rs.next()) {
	                moto.setPlaca(rs.getString("placa"));
	            }
	        } catch(SQLException ex){
	            throw new SQLException("popularIdentificacao() "+ex.getMessage());
	        }
	    }
	    
	    //Pesquisa por campos deve conter : fabricante, categoria, modelo;
	    public ArrayList<Motocicleta> consultar(Motocicleta motocicleta, Modelo modelo) throws SQLException{
	    	
	    	String sql = "SELECT mol.nomeModelo, mot.categoria, mot.placa "
	    			+ "FROM motocicleta mot "
	    			+ "JOIN modelo mol ON mol.codModelo = mot.codMotocicleta";
	    	
	    	String where = new String();
	    	ArrayList<Motocicleta> consulta = new ArrayList<Motocicleta>();
	    	
	    	if(modelo != null){
	    		this.popularIdentificacaoModelo(modelo);
	    		if( where.isEmpty() ){
	    			where += String.format("mol.nomeModelo = '%s'", modelo);
	    		}else{
	    			where += String.format(" AND mol.nomeModelo = '%s'", modelo);
	    		}
	    	}
	    	
	    	if(motocicleta != null){
	    		this.popularIdentificacaoMotocicleta(motocicleta);
	    		if( where.isEmpty() ){
	    			where += String.format("mot.categoria = '%s'", motocicleta.getCategoria() );
	    		}else{
	    			where += String.format(" AND mot.categoria = '%s'", motocicleta.getCategoria() );
	    		}
	    	}
	    	
	    	if(motocicleta.getModelo().getFabricante().getNomeFabricante() != null){
	    		if( where.isEmpty() ){
	    			where += String.format("fab.nomeFabricante = '%s'", motocicleta.getModelo().getFabricante().getNomeFabricante() );
	    		}else{
	    			where += String.format(" JOIN fabricante fab ON mol.codModelo = fab.codFabricante AND fab.nomeFabricante = '%s'", motocicleta.getModelo().getFabricante().getNomeFabricante() );
	    		}
	    	}
	    	
	    	 if( !where.isEmpty() ){
	             sql += String.format("WHERE %s", where);
	         }
	    	 
	    	 try (PreparedStatement pst = con.prepareStatement(sql)){	
	    		 
	    		 if(modelo != null){
	    			 pst.setInt(1, modelo.getCodModelo() );
	    		 }
	    		 
	    		 if(motocicleta != null){
	    			 pst.setInt(2, motocicleta.getCodMotocicleta() );
	    		 }
	    		 
	    		 ResultSet rs = pst.executeQuery();
	    		 
	    		 while( rs.next() ){
	    			 
	    			 Fabricante fabri = new Fabricante();
	    			 fabri.setCodFabricante( rs.getInt("codFabricante") );
	    			 fabri.setNomeFabricante( rs.getString("nomeFabricante") );
	    			 
	    			 Modelo mod = new Modelo();
	    			 mod.setCodModelo( rs.getInt("codModelo") );
	    			 mod.setNomeModelo( rs.getString("nomeModelo") );
	    			 mod.setFabricante(fabri);
	    			 
	    			 Motocicleta moto = new Motocicleta();
	    			 moto.setCodMotocicleta( rs.getInt("codMotocicleta") );
	    			 moto.setModelo(mod);
	    			 
	    			 this.listaMotocicleta.add(moto); 
	    		 }
	    		 
	    		 
	    		 
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	 
	    	return this.listaMotocicleta;
	  
	    }
	    
	    
	    
	
	    public void editar(Motocicleta moto) throws SQLException{
	        
	        try {
	            String query = "UPDATE motocicleta SET placa = ?, cor = ?, km = ?, estilo = ?, categoria = ?, ano = ?, combustivel = ?" +
	            		" Where codMotocicleta=?";
	            PreparedStatement st = con.prepareStatement(query);
	            st.setString(1, moto.getPlaca() );
	            st.setString(2, moto.getCor() );
	            st.setDouble(3, moto.getKm() );
	            st.setString(4, moto.getEstilo() );
	            st.setString(5, moto.getCategoria() );
	            st.setInt(6, moto.getAno() );
	            st.setString(7, moto.getCombustivel() );
	            st.setInt(8, moto.getCodMotocicleta());
	            
	            
	            st.executeUpdate();
	            System.err.println(query);
	            
	            con.commit();
	         //   con.close();
	        } catch (SQLException ex) {
	        	con.rollback();
	            ex.printStackTrace();
	        }
	        
	        
	    }
	    
	    public ArrayList<Motocicleta> listarPesquisaMotocicleta() throws SQLException{
	    	
	    	 try {
	    		 
	    		 this.listaMotocicleta = new ArrayList<Motocicleta>();
	             Statement st = con.createStatement();
	             String query =" Select m.codModelo, m.nomeModelo, mot.Categoria, mot.placa, mot.codMotocicleta, mot.ano from motocicleta mot " + 
	        		 		   " inner join modelo m on mot.codModelo= m.codModelo" +
	        		 		   " inner join fabricante f on f.codFabricante=m.codFabricante";	

	             ResultSet rs = st.executeQuery(query);
	             
	             while( rs.next() ){
	            	 Modelo model = new Modelo();
	            	 Motocicleta moto = new Motocicleta();
	            	 
	            	 model.setCodModelo(rs.getInt("m.codModelo"));
	            	 model.setNomeModelo(rs.getString("m.nomeModelo"));
	            	 
	            	 moto.setModelo(model);
	            	 moto.setCategoria(rs.getString("mot.Categoria"));
	            	 moto.setPlaca(rs.getString("mot.placa"));
	            	 moto.setCodMotocicleta(rs.getInt("mot.codMotocicleta"));
	            	 moto.setAno(rs.getInt("mot.ano"));
	            	 
	            	 
	            	 
	               
	                
	                 this.listaMotocicleta.add( moto );
	             }
	             
	             con.commit();
//	             con.close();
	         } catch (SQLException ex) {
//	        	 con.rollback();
	             ex.printStackTrace();
	         }
	         
	         return this.listaMotocicleta;
	    	
	    }
	    
	    
	    
	    
	    
	    
	/*  //listar Categoria
	    public String listaCategoriaMotocicleta(Motocicleta moto) throws SQLException {
	        
	    	String sql = "Select categoria from motocicleta";
	    	
	    try {
	    	
	    	PreparedStatement pst = con.prepareStatement(sql);
	    	
	    	ResultSet rs = pst.executeQuery();
			
		} catch (Exception e) {
			// TODO: handle exception
	 	}
	    	
*/    
  
	    public void remover(Integer id) throws Exception{
	        try {
	           String query = "DELETE FROM motocicleta WHERE codMotocicleta = ?";
	           PreparedStatement st = con.prepareStatement(query);
	           st.setInt(1, id );
	           st.executeUpdate();
	           
	           con.commit();
	          // con.close();
	       } catch (SQLException ex) {
	    	   con.rollback();
	           ex.printStackTrace();
	       }
	   }


		public ArrayList<Motocicleta> listarCadastroMotocicleta() {


 try {
	    		 
	    		 this.listaMotocicleta = new ArrayList<Motocicleta>();
	             Statement st = con.createStatement();
	             String query ="Select m.codModelo, m.nomeModelo, mot.Categoria, mot.ano, f.nomeFbricante  from motocicleta mot " + 
	        		 		  "inner join modelo m on mot.codModelo= m.codModelo" +
	        		 		  "inner join fabricante f on f.codFabricante=m.codFabricante";	

	             ResultSet rs = st.executeQuery(query);
	             
	             while( rs.next() ){
	            	 Modelo model = new Modelo();
	            	 Motocicleta moto = new Motocicleta();
	            	 Fabricante fab = new Fabricante();
	            	 moto.setFabricante(fab);
	            	 
	            	 
	            	 model.setNomeModelo(rs.getString("m.nomeModelo"));
	            	 
	            	 moto.setModelo(model);
	            	 moto.setCategoria(rs.getString("mot.Categoria"));
	            	 moto.setAno(rs.getInt("mot.ano"));
	            	 fab.setNomeFabricante(rs.getString("f.nomeFabricante"));
	            	 
	            	 
	            	 
	                System.out.println("Consulta modelo motocicleta");
	                
	                 this.listaMotocicleta.add( moto );
	             }
	             
	             con.commit();
//	             con.close();
	         } catch (SQLException ex) {
//	        	 con.rollback();
	             ex.printStackTrace();
	         }
	         
	         return this.listaMotocicleta;
	    	
	    }
	    

}