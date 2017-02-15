package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Cliente;
import Model.Vendedor;
import Utils.ConnectionUtil;

public class VendedorDao {
	

private ArrayList<Vendedor> listaVendedor;
private static VendedorDao instanciaRep;
private Connection con = ConnectionUtil.getConnection();

	
	//Singleton
	public static VendedorDao obterInstancia(){
		if ( instanciaRep == null ){
	        instanciaRep = new VendedorDao();
	    }
	    return instanciaRep;
	}
	
	public VendedorDao(){
	    this.listaVendedor = new ArrayList<Vendedor>();
	}

	public ArrayList<Vendedor> listarTodos() throws SQLException{
	    this.listaVendedor = new ArrayList<Vendedor>();
	    try {
	        Statement st = con.createStatement();
	        String query = "SELECT * FROM VENDEDOR";
	        ResultSet rs = st.executeQuery(query);
	        while( rs.next() ){
	        	Vendedor v = new Vendedor();
	           v.setCodVendedor(rs.getInt("codVendedor"));
	           v.setNomeVendedor(rs.getString("nomeVendedor"));
	           
	            
	            this.listaVendedor.add( v );
	        }
	        
	        con.commit();
	        con.close();
	    } catch (SQLException ex) {
	    	con.rollback();
	        ex.printStackTrace();
	    }
	    
	    return this.listaVendedor;
	
	}

	public void excluir(Vendedor vendedor) throws Exception{
	    try {
	       String query = "DELETE FROM vendedor WHERE codVendedor = ?";
	       PreparedStatement st = con.prepareStatement(query);
	       st.setInt(1, vendedor.getCodVendedor());
	       st.executeUpdate();
	       
	       con.commit();
	       con.close();
	   } catch (SQLException ex) {
		   con.rollback();
	       ex.printStackTrace();
	   }
	}

}




