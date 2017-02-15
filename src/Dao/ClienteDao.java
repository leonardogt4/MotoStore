package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import Model.Cliente;
import Model.Venda;
import Utils.ConnectionUtil;

public class ClienteDao {
	
	private ArrayList<Cliente> listaCliente;
    private static ClienteDao instanciaRep;
    private Connection con = ConnectionUtil.getConnection();
    
    /*SINGLETON*/
    public static ClienteDao obterInstancia(){
        if ( instanciaRep == null ){
            instanciaRep = new ClienteDao();
        }
        return instanciaRep;
    }
    
    public ClienteDao(){
        this.listaCliente = new ArrayList<Cliente>();
    }
    
    //Inserir Cliente no Banco
    public void inserir(Cliente cliente) throws SQLException{
    	try {
            String query = "INSERT INTO cliente ( nomeCliente, telefone, celular, email, cnh, cpf, renda ) "
            		+ "VALUES ( ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, cliente.getNomeCliente() );
            pst.setString(2, cliente.getTelefone() );
            pst.setString(3, cliente.getCelular() );
            pst.setString(4, cliente.getEmail() );
            pst.setString(5, cliente.getCnh() );
            pst.setString(6, cliente.getCpf() );
            pst.setDouble(7, cliente.getRenda() );
            
            
            pst.execute();
            
            con.commit();
            con.close();
        } catch (SQLException ex) {
        	con.rollback();
            ex.printStackTrace();
        }
    }
    
    //Lista todos os Clientes cadastrados quando o campo de pesquisa da View tiver vazio
    public ArrayList<Cliente> listarTodos() throws SQLException{
    	
    	 try {
    		 
    		 this.listaCliente = new ArrayList<Cliente>();
             Statement st = con.createStatement();
             String query = "SELECT * FROM cliente";
             ResultSet rs = st.executeQuery(query);
             
             while( rs.next() ){
                 Cliente cliente = new Cliente();
                 cliente.setCodCliente(rs.getInt("codCliente"));
                 cliente.setNomeCliente(rs.getString("nomeCliente"));
                 cliente.setTelefone(rs.getString("telefone"));
                 cliente.setCelular(rs.getString("celular"));
                 cliente.setEmail(rs.getString("email"));
                 cliente.setCnh(rs.getString("cnh"));
                 cliente.setCpf(rs.getString("cpf"));
                 cliente.setRenda(rs.getDouble("renda"));
                 
                 this.listaCliente.add( cliente );
             }
             con.commit();
         } catch (SQLException ex) {
        	 con.rollback();
             ex.printStackTrace();
         }
         
         return this.listaCliente;
    	
    }
    
    //Pesquisa por Nome do Cliente
    public ArrayList<Cliente> consultarNome(Cliente cliente){
    	
        String sql = "SELECT * FROM cliente WHERE nomeCliente like ?";   
        ArrayList<Cliente> listaCliente = new ArrayList<>();         
        
        try{
        	PreparedStatement query = con.prepareStatement(sql);

            query.setString( 1, "%"+cliente.getNomeCliente()+"%");

//         Exibe a query se ela for executada com sucesso   
//		   System.err.println(query);
            
            ResultSet rs = query.executeQuery();
            
            while(rs.next()){
            	 Cliente cli = new Cliente();
              
                 cli.setNomeCliente(rs.getString("nomeCliente"));
                 cli.setTelefone(rs.getString("telefone"));
                 cli.setRenda(Double.parseDouble( rs.getString("renda") ));
                 
                 
                 listaCliente.add(cli);
            }
//            rs.close();
            
//            con.close();
              
        } catch(SQLException ex){
        	ex.printStackTrace();
        }  
        return listaCliente;
    }
    

    //Verifica se o CPF já existe no Banco, para validar que outro Cliente não use um CPF já cadastrado.
    public Boolean clienteCpfExiste(Cliente cliente) throws SQLException{
    	
    	try {
    		String sql = "SELECT * FROM cliente WHERE cpf = ?";
    		PreparedStatement query = con.prepareStatement(sql);
           
            query.setString(1, cliente.getCpf());
            ResultSet rs = query.executeQuery();
            
            while( rs.next() ){
            	if(rs.getString("cpf").equals(cliente.getCpf())){
            		return true;
            	}
                
            }
            con.close(); 
		} catch (Exception e) {
			throw new SQLException("clienteCPFExiste(): " + e.getMessage() );
		}
    	
    	return false;	
    }
    
  //Verifica se a CNH já existe no Banco, para validar que outro Cliente não use uma CNH já cadastrada.
    public Boolean clienteCnhExiste(Cliente cliente) throws SQLException{
    	
    	try {
    		String sql = "SELECT * FROM cliente WHERE cnh = ?";
    		PreparedStatement query = con.prepareStatement(sql);
           
            query.setString(1, cliente.getCnh());
            ResultSet rs = query.executeQuery();
            
            while( rs.next() ){
            	if(rs.getString("cnh").equals(cliente.getCnh())){
            		return true;
            	}
                
            }
            con.close(); 
		} catch (Exception e) {
			throw new SQLException("clienteCNHExiste(): " + e.getMessage() );
		}
    	
    	return false;	
    }

    //Editar um Cliente já cadastrado
    public void editar(Cliente cliente){
        
        try {
            String query = "UPDATE cliente SET nomeCliente = ?, telefone = ?, celular = ?, email = ?, cnh = ?, cpf = ?, renda = ? "
                         + "WHERE codCliente = ?";
            
            PreparedStatement st = con.prepareStatement(query);
         
            st.setString(1, cliente.getNomeCliente());
            st.setString(2, cliente.getTelefone());
            st.setString(3, cliente.getCelular());
            st.setString(4, cliente.getEmail());
            st.setString(5, cliente.getCnh());
            st.setString(6, cliente.getCpf());
            st.setDouble(7, cliente.getRenda());
            st.setInt(8, cliente.getCodCliente());
            
            st.executeUpdate();
            
//            con.close();
            con.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
    }
    
    
    //Remove o Cliente do Banco
    public void remover(Integer id) throws Exception{
        try {
           String query = "DELETE FROM cliente WHERE codCliente = ?";
           PreparedStatement st = con.prepareStatement(query);
           st.setInt(1, id);
           st.executeUpdate();
           
           con.commit();
       } catch (SQLException ex) {
    	   con.rollback();
           ex.printStackTrace();
       }
   }
    
    
    
    public ArrayList<Cliente> listaClientesVenda(){
    	
    	
    	 try {
    		 
    		 this.listaCliente = new ArrayList<Cliente>();
             Statement st = con.createStatement();
             String query = "SELECT c.codCliente,c.nomeCliente FROM cliente c "
             		+ "JOIN venda v ON v.codCliente = c.codCliente";
             
             ResultSet rs = st.executeQuery(query);
             
             while( rs.next() ){
            	 Cliente cli = new Cliente();
            	 cli.setCodCliente(rs.getInt("c.codCliente"));
            	 cli.setNomeCliente( rs.getString("c.nomeCliente"));
            	 
              //   Venda ven = new Venda();
              //   ven.setFormaPagamento( rs.getString("v.formaPagamento"));
              //   ven.setValor( rs.getDouble("v.valor"));
              
                 
                 this.listaCliente.add( cli );
             }
             con.commit();
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
         
         return this.listaCliente;
   	
    }
    
    //popular ID Cliente
    public void popularIdentificacaoCliente(Cliente cliente) throws SQLException {
        String sql = "SELECT codCliente FROM cliente WHERE codCliente = ?";

        try{
        	 PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, cliente.getNomeCliente() );
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                cliente.setCodCliente(rs.getInt("codCliente"));
            }
        } catch(SQLException ex){
            throw new SQLException("popularIdentificacao() "+ex.getMessage());
        }
    }
    
    //popular ID Venda
    public void popularIdentificacaoVenda(Venda venda) throws SQLException {
        String sql = "SELECT codVenda FROM venda WHERE codVenda = ?";

        try{
        	 PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, venda.getCodVenda() );
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
            	
                venda.setCodVenda(rs.getInt("codVenda"));
            }
        } catch(SQLException ex){
            throw new SQLException("popularIdentificacao() "+ex.getMessage());
        }
    }
    
    
    
    
 public ArrayList<Cliente> consular(Venda venda, Double valorInicial, Double valorFinal, Cliente cliente) throws SQLException{
    	
    	String sql = "SELECT c.nomeCliente FROM cliente c"
    			+ " JOIN venda v ON v.codCliente = c.codCliente";
    	
    	String where = new String();
    	ArrayList<Cliente> consulta = new ArrayList<Cliente>();
    	
    	if(cliente != null){
    		this.popularIdentificacaoCliente(cliente);
    		if( where.isEmpty() ){
    			where += String.format("c.nomeCliente = '%s'", cliente);
    		}else{
    			where += String.format(" AND c.nomeCliente = '%s'", cliente);
    		}
    	}
    	
    	if(venda.getValor() != null || !venda.getValor().equals("")){
    		this.popularIdentificacaoCliente(cliente);
    		if( where.isEmpty() ){
    			where += String.format("v.valor = '%s'", venda.getValor() );
    		}else{
    			where += String.format(" WHERE " + venda.getValor() + " BETWEEN " + valorInicial + " AND " + valorFinal);
    		}
    	}

    	
    	 if( !where.isEmpty() ){
             sql += String.format("WHERE %s", where);
         }
    	 
    	 try (PreparedStatement pst = con.prepareStatement(sql)){	
    		 
    		 if(venda != null){
    			 pst.setInt(1, venda.getCodVenda() );
    		 }
    		 

    		 ResultSet rs = pst.executeQuery();
    		 
    		 while( rs.next() ){
    			 
    			 Cliente cli = new Cliente();
    			 cli.setNomeCliente( rs.getString("c.nomeCliente") );
    			 
    			 Venda ven = new Venda();
    			 ven.setCliente(cli);
    			 ven.setCodVenda( rs.getInt("v.codVenda") );
    			 ven.setValor( rs.getDouble("v.valor"));
    			 
    			 
    			 this.listaCliente.add( cli ); 
    		 }
    		 
    		 
    		 con.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	 
    	return this.listaCliente;
  
    }
 
 public ArrayList<Cliente> consultarNomeVenda(Cliente cliente){
 	
     String sql = "SELECT * FROM cliente WHERE nomeCliente like ?";   
     ArrayList<Cliente> listaCliente = new ArrayList<>();         
     
     try{
     	PreparedStatement query = con.prepareStatement(sql);

         query.setString( 1, "%"+cliente.getNomeCliente()+"%");

//      Exibe a query se ela for executada com sucesso   
//		   System.err.println(query);
         
         ResultSet rs = query.executeQuery();
         
         while(rs.next()){
         	 Cliente cli = new Cliente();
           
              cli.setNomeCliente(rs.getString("nomeCliente"));
              cli.setTelefone(rs.getString("telefone"));
              cli.setRenda(Double.parseDouble( rs.getString("renda") ));
              
              
              listaCliente.add(cli);
         }
//         rs.close();
         
//         con.close();
           
     } catch(SQLException ex){
     	ex.printStackTrace();
     }  
     return listaCliente;
 }
    

}
