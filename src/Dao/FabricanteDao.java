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

public class FabricanteDao {

	private ArrayList<Fabricante> listaFabricante;
	private ArrayList<Modelo> listaModelo = new ArrayList<Modelo>();
    private static FabricanteDao instanciaRep;
    private Connection con = ConnectionUtil.getConnection();
    
    /*SINGLETON*/
    public static FabricanteDao obterInstancia(){
        if ( instanciaRep == null ){
            instanciaRep = new FabricanteDao();
        }
        return instanciaRep;
    }
    
    public FabricanteDao(){
        this.listaFabricante = new ArrayList<Fabricante>();
    }
    
    
	public ArrayList<Fabricante> listaTodosFabricante(){
		
		try{
		 Statement st = con.createStatement();
         String query = " SELECT f.nomeFabricante, m.nomeModelo, f.codFabricante"
         		+ 		" FROM fabricante f"
         		+ 		" JOIN modelo m ON m.codFabricante = f.codFabricante;";
         ResultSet rs = st.executeQuery(query);
         
         while( rs.next() ){
        	 
             Modelo mod = new Modelo();
             Fabricante fabri = new Fabricante();
             
             fabri.setCodFabricante( rs.getInt("codFabricante") );
             fabri.setNomeFabricante( rs.getString("nomeFabricante") );
             mod.setFabricante(fabri);
             
             this.listaFabricante.add( fabri );
         }
         
         
     } catch (SQLException ex) {
         ex.printStackTrace();
     }
     
     return this.listaFabricante;
	
	}
	
	
	//Pesquisa por Nome do Fabricante
    public ArrayList<Modelo> listaCampos(Modelo modelo) throws SQLException{
    	
    	this.listaModelo = new ArrayList<Modelo>();
    	this.popularIdentificacaoModelo(modelo);
    	
    	 String sql = "SELECT f.codFabricante, f.nomeFabricante, m.codModelo, m.nomeModelo"
    	 		+ " FROM fabricante f"
    	 		+ " JOIN modelo m ON m.codFabricante = f.codFabricante WHERE f.nomeFabricante LIKE ?"; 
    	 
         ArrayList<Cliente> listaCliente = new ArrayList<>();         
         
         try{
         	PreparedStatement pst = con.prepareStatement(sql);

             pst.setString( 1, modelo.getFabricante().getNomeFabricante()+"%");
             
           ResultSet rs = pst.executeQuery();
            
//            System.err.println(sql);
            
            while(rs.next()){
            	 Fabricante fabri = new Fabricante();	
            	 fabri.setCodFabricante( rs.getInt("f.codFabricante"));
            	 fabri.setNomeFabricante(rs.getString("f.nomeFabricante"));
            	 
            	 Modelo model = new Modelo();
            	 model.setFabricante(fabri);
            	 model.setCodModelo( rs.getInt("m.codModelo"));
            	 model.setNomeModelo( rs.getString("m.nomeModelo"));
   
            	 listaModelo.add(model);
            }
            rs.close();
            
        } catch(SQLException ex){
        	ex.printStackTrace();
        }  
        
        return listaModelo;
    }

//	//Editar um fabricante existente
//    public void editarFabricante(Fabricante fabricante) throws SQLException{
//    	
//    	this.popularIdentificacaoFabricante(fabricante);
//        
//        try {
//            String query = "UPDATE fabricante SET codFabricante = ?, nomeFabricante = ? WHERE codFabricante = ?";
//            PreparedStatement st = con.prepareStatement(query);
//            
//            st.setInt(1, fabricante.getCodFabricante() );
//            st.setString(2, fabricante.getNomeFabricante() );
//            st.setInt(3, fabricante.getCodFabricante() );
//            
//            st.executeUpdate();
//            
//            System.err.println(query);
//            
//            con.commit();
//            System.out.println("Passou no Dao");
//            
//        } catch (SQLException ex) {
//        	con.rollback();
//            ex.printStackTrace();
//        }
//        
//        
//    }
    
    //Remover um Fabricante Existente
    public void removerFabricante(Integer id) throws Exception{
        try {
           String query = "DELETE FROM fabricante WHERE codFabricante = ?";
           PreparedStatement st = con.prepareStatement(query);
           st.setInt(1, id );
           
           st.executeUpdate();
           
           con.commit();
           
         
       } catch (SQLException ex) {
    	   con.rollback();
           ex.printStackTrace();
       }
   }
    
    
   //////////////////////////////////////////////////		Metodos MODELO		/////////////////////////////////////////////////////////////
    
    //Inserir somente Modelo no Banco
    public void inserirSomenteModelo(Modelo model) throws SQLException{
    	try {
            String query = "INSERT INTO modelo ( codModelo, nomeModelo, codFabricante ) "
            		+ "VALUES ( ?, ?, ? )";
            
            PreparedStatement pst = con.prepareStatement(query);
            this.popularIdentificacaoModelo(model);
            this.popularIdentificacaoFabricante(model.getFabricante());
            
            pst.setInt(1, model.getCodModelo() );
            pst.setString(2, model.getNomeModelo() );
            pst.setInt(3, model.getFabricante().getCodFabricante() );
  
            pst.execute();
            
            con.commit();
        } catch (SQLException ex) {
        	con.rollback();
            ex.printStackTrace();
        }
    }
    
    
    
    //popular ID Fabricante
    public void popularIdentificacaoFabricante(Fabricante fab) throws SQLException {
        String sql = "SELECT MAX(codFabricante) AS codigo FROM fabricante ";

        try{
        	 PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                fab.setCodFabricante(rs.getInt("codigo") + 1);
            }
        } catch(SQLException ex){
            throw new SQLException("popularIdentificacao() "+ex.getMessage());
        }
    }
    
    //popular ID Modelo
    public void popularIdentificacaoModelo(Modelo model) throws SQLException {
        String sql = "SELECT MAX(codModelo) AS codigo FROM modelo ";

        try{
        	 PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                model.setCodModelo(rs.getInt("codigo") + 1);
            }
        } catch(SQLException ex){
            throw new SQLException("popularIdentificacao() "+ex.getMessage());
        }
    }
    
    //Verifica se o Fabricante já  está cadastrado.
    public Boolean fabricanteExiste(Fabricante fabri) throws SQLException{
    	
    	try {
    		String sql = "SELECT * FROM fabricante WHERE nomeFabricante = ?";
    		PreparedStatement query = con.prepareStatement(sql);
           
            query.setString(1, fabri.getNomeFabricante() );
            ResultSet rs = query.executeQuery();
            
            while( rs.next() ){
            	
            	if(rs.getString("nomeFabricante").equals(fabri.getNomeFabricante())){
            		return true;
            	}
                
            }
		} catch (Exception e) {
			throw new SQLException("fabricanteExiste(): " + e.getMessage() );
		}
    	
    	return false;	
    }
    
    
  //Inserir um novo Modelo
  	public void inserirModelo(Modelo modelo) throws SQLException{
  		
  		try {
  
  				
  				this.popularIdentificacaoFabricante( modelo.getFabricante() );
  	  			this.popularIdentificacaoModelo( modelo );
  				
  				 String query = "INSERT INTO fabricante ( codFabricante, nomeFabricante ) VALUES ( ?, ? )";
  	            
  	            String queryModel = "INSERT INTO modelo ( codModelo, nomeModelo, codFabricante )"
  	                    + "VALUES (?, ?, ?)";
  	            
  	            PreparedStatement pst = con.prepareStatement(query);
  	            PreparedStatement pst2 = con.prepareStatement(queryModel);
  	            con.setAutoCommit(false);
  	           
  	            pst.setInt(1, modelo.getFabricante().getCodFabricante() );
  	            pst.setString(2, modelo.getFabricante().getNomeFabricante());
  	               
  	            pst.executeUpdate();
  	            
  	           
  	                pst2 = con.prepareStatement(queryModel);
  	                
  	                pst2.setInt(1, modelo.getCodModelo() );
  	                pst2.setString(2, modelo.getNomeModelo());
  	                pst2.setInt(3, modelo.getFabricante().getCodFabricante() );
  	                
  	                pst2.executeUpdate();
  	                con.commit();
  	                
  	                System.err.println(query + "\n" + queryModel);
  	            
  	            pst.execute();
  			

        } catch (SQLException ex) {
        	con.rollback();
            ex.printStackTrace();
        }
  		
  	}
  	
  	
  //Editar Fabricante e Modelo
  	public void editarFornecedor(Modelo modelo) throws SQLException{
  		
  		try {
  			
  			this.popularIdentificacaoFabricante( modelo.getFabricante() );
  			this.popularIdentificacaoModelo( modelo );
  			
            String query = "UPDATE fabricante SET codFabricante = ?, nomeFabricante = ? WHERE codFabricante = ?";
            
            String queryModel = "UPDATE modelo SET codModelo = ?, nomeModelo = ?, codFabricante = ? WHERE codModelo = ? "
                    + "VALUES (?, ?, ?)";
            
            PreparedStatement pst = con.prepareStatement(query);
            PreparedStatement pst2 = con.prepareStatement(queryModel);
            con.setAutoCommit(false);
            
            pst.setInt(1, modelo.getFabricante().getCodFabricante() );
            pst.setString(2, modelo.getFabricante().getNomeFabricante() );
            pst.setInt(3, modelo.getFabricante().getCodFabricante() );
            
            pst.executeUpdate();
            System.err.println(query);
            
           
                pst2 = con.prepareStatement(queryModel);
                
                pst2.setInt(1, modelo.getCodModelo() );
                pst2.setString(2, modelo.getNomeModelo());
                pst2.setInt(3, modelo.getFabricante().getCodFabricante() );
                pst2.setInt(4, modelo.getCodModelo() );
                
                pst2.executeUpdate();
                con.commit();
                
            pst.execute();

        } catch (SQLException ex) {
        	con.rollback();
            ex.printStackTrace();
        }
  		
  	}
  	
  	//Lista todos os Modelos
  	public ArrayList<Modelo> listaTodosModelo(){
  		
  		try{
  			this.listaModelo = new ArrayList<Modelo>();
  			
  			
  		 Statement st = con.createStatement();
           String query = "SELECT m.codModelo, f.codFabricante, m.nomeModelo, f.nomeFabricante "
           		+ "FROM fabricante f "
           		+ " JOIN modelo m ON m.codFabricante = f.codFabricante ORDER BY f.nomeFabricante";
           
           ResultSet rs = st.executeQuery(query);
           
           while( rs.next() ){
        	   Fabricante fab = new Fabricante();
        	   fab.setCodFabricante( rs.getInt("f.codFabricante"));
        	   fab.setNomeFabricante( rs.getString("f.nomeFabricante"));
          	 
               Modelo model = new Modelo();
               model.setCodModelo(rs.getInt("m.codModelo"));
               model.setNomeModelo(rs.getString("m.nomeModelo"));
               model.setFabricante(fab);
     
               model.setFabricante(fab);
               
               this.listaModelo.add( model );
           }
           
           
       } catch (SQLException ex) {
           ex.printStackTrace();
       }
       
       return this.listaModelo;
  	
  	}
  	
  	//Pesquisa por Nome do Modelo
      public ArrayList<Modelo> consultarNomeModelo(Modelo modelo) throws SQLException{
          String sql = "SELECT * FROM modelo WHERE nomeModelo like ?";   
          ArrayList<Fabricante> listaFabricante = new ArrayList<>();         
          
          try{
        	  
        	  this.listaModelo = new ArrayList<Modelo>();
        	  
          	PreparedStatement query = con.prepareStatement(sql);

              query.setString( 1, "%"+modelo.getNomeModelo()+"%");

              ResultSet rs = query.executeQuery();
              
              while(rs.next()){
              	 Modelo model = new Modelo();
              	 
              	 model.setNomeModelo(rs.getString("nomeModelo"));
                   
                   listaModelo.add(model);
              }
              rs.close();
              
          } catch(SQLException ex){
          	ex.printStackTrace();
          }  
          return listaModelo;
      }

//  	//Editar um modelo existente
//      public void editarModelo(Modelo modelo) throws SQLException{
//    	  
//    	  this.popularIdentificacaoModelo(modelo);
//          
//          try {
//              String query = "UPDATE modelo SET nomeModelo = ? WHERE codModelo = ?";
//              PreparedStatement st = con.prepareStatement(query);
//              
//              st.setString(1, modelo.getNomeModelo() );
//              st.setInt(2, modelo.getCodModelo() );
//            
//              st.executeUpdate();
//              
//              System.err.println(query);
//              
//              con.commit();
//              System.out.println("Passou no Dao");
//              
//          } catch (SQLException ex) {
//        	  con.rollback();
//              ex.printStackTrace();
//          }
//          
//          
//      }
      
      //Remover um Modelo Existente
      public void removerModelo(Integer id) throws SQLException{
          try {
             String query = "DELETE FROM modelo WHERE codModelo = ?";
             PreparedStatement st = con.prepareStatement(query);
             st.setInt(1, id );
             
             st.executeUpdate();
             
             con.commit();
             
         } catch (SQLException ex) {
        	 con.rollback();
             ex.printStackTrace();
         }
     }
	
	
	
}
