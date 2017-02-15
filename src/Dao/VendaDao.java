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
import Model.Venda;
import Model.Vendedor;
import Utils.ConnectionUtil;



public class VendaDao {

private ArrayList<Venda> listaVenda;
private ArrayList<Cliente> listaCliente;
private static VendaDao instanciaRep;
private Connection con = ConnectionUtil.getConnection();

	
	//Singleton
	
	public static VendaDao obterInstancia(){
	    if ( instanciaRep == null ){
	        instanciaRep = new VendaDao();
	    }
	    	return instanciaRep;
	}
	
	public VendaDao() {
		this.listaVenda = new ArrayList<Venda>();	
	}

	//Insere uma Venda recebido por parametro
	public void inserir(Venda venda) throws SQLException{
		  try {
	            String query = "INSERT INTO VENDA (formaPagamento,dataVenda,valor,quantidade," +
	            		"codCliente,codMotocicleta,codVendedor )" + " VALUES (?,?,?,?,?,?,?)";
	            PreparedStatement pst = con.prepareStatement(query);
	            // pst.setInt(1, venda.getCodVenda());
	            pst.setString(1, venda.getFormaPagamento());
	            pst.setDate(2, new java.sql.Date(venda.getDataVenda().getTime()));
	            pst.setDouble(3, venda.getValorTotal());
	            pst.setInt(4, venda.getQuantidade());
	            pst.setInt(5, venda.getCliente().getCodCliente());
	            pst.setInt(6, venda.getMotocicleta().getCodMotocicleta());
	            pst.setInt(7, venda.getVendedor().getCodVendedor());
	            
	            pst.execute();   
	            
	            con.commit();
	            
	        } catch (SQLException ex) {
	        	con.rollback();
	            ex.printStackTrace();
	        }
	  
		
	}
	
	 //Lista todos as Vendas cadastradas quando o campo de pesquisa da View tiver vazio
    public ArrayList<Venda> listarTodos(){
    	
    	 try {
    		 
    		 this.listaVenda = new ArrayList<Venda>();
             Statement st = con.createStatement();
             String query = "SELECT c.codCliente,c.nomeCliente, v.formaPagamento, v.valor, v.codVenda  FROM venda v"
            		 + " join cliente c on c.codCliente=v.codCliente";
             ResultSet rs = st.executeQuery(query);
             
             while( rs.next() ){
            	 Cliente c = new Cliente();
            	 
            	 c.setCodCliente(rs.getInt("c.codCliente"));
            	 c.setNomeCliente(rs.getString("c.nomeCliente"));
            	 
                 Venda ven = new Venda();
              
                 ven.setFormaPagamento( rs.getString("v.formaPagamento"));
                 ven.setCliente(c);
              //   ven.setDataVenda( rs.getDate("dataVenda"));
                 ven.setValor( rs.getDouble("v.valor"));
                 ven.setCodVenda(rs.getInt("v.codVenda"));
                // ven.setQuantidade( rs.getInt("quantidade"));
                 
                 this.listaVenda.add( ven );
             }
             con.commit();
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
         
         return this.listaVenda;
    	
    }
    
    //lista somente o nome do Cliente da Venda no combobox da tela pesquisa clientesui
//    public ArrayList<Cliente> listaClientesVenda(){
//    	//ok
//    	
//    	 try {
//    		 
//    		 this.listaVenda = new ArrayList<Venda>();
//             Statement st = con.createStatement();
//             String query = "SELECT c.nomeCliente FROM cliente c "
//             		+ "JOIN venda v ON v.codCliente = c.codCliente";
//             
//             ResultSet rs = st.executeQuery(query);
//             
//             while( rs.next() ){
//            	 Cliente cli = new Cliente();
//            	 cli.setNomeCliente( rs.getString("c.nomeCliente"));
//            	 
//                 Venda ven = new Venda();
//              //   ven.setFormaPagamento( rs.getString("v.formaPagamento"));
//              //   ven.setValor( rs.getDouble("v.valor"));
//              
//                 
//                 this.listaVenda.add( ven );
//             }
//             con.commit();
//         } catch (SQLException ex) {
//             ex.printStackTrace();
//         }
//         
//         return this.listaCliente;
//   	
//    }
    
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
    

    
    //Pesquisa de venda pelo campo da tela de pesquisa
    
    //Pesquisa por nome do Cliente, Valor Inicial e Valor Final
    public ArrayList<Venda> consultarNomeVenda(Venda venda, Double valorInicial, Double valorFinal, Cliente cliente) throws SQLException{
    	
    	String sql = "SELECT c.nomeCliente FROM cliente c"
    			+ " JOIN venda v ON v.codCliente = c.codCliente";
    	
    	String where = new String();
    	ArrayList<Venda> consulta = new ArrayList<Venda>();
    	
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
    			 
    			 
    			 this.listaVenda.add( ven ); 
    		 }
    		 
    		 
    		 con.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	 
    	return this.listaVenda;
  
    }
    
    
    
  //Editar uma Venda já cadastrado
    public void editar(Venda venda) throws SQLException{
        
        try {
            String query = "UPDATE venda SET formaPagamento = ?, dataVenda = ?, quantidade = ?, codCliente = ?, codMotocicleta = ?, codVendedor = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, venda.getFormaPagamento() );
            st.setDate(2, new java.sql.Date (venda.getDataVenda().getTime() ));
            st.setInt(3, venda.getQuantidade() );
            st.setInt(4, venda.getCliente().getCodCliente() );
            st.setInt(5, venda.getMotocicleta().getCodMotocicleta() );
            
            
            st.executeUpdate();
            
            con.commit();
           
        } catch (SQLException ex) {
        	con.rollback();
            ex.printStackTrace();
        }
        
        
    }
    
    
    //Remove o Venda do Banco
    public void remover(Integer id) throws SQLException{
        try {
           String query = "DELETE FROM venda WHERE codVenda = ?";
           PreparedStatement st = con.prepareStatement(query);
           st.setInt(1, id);
           st.executeUpdate();
           
           con.commit();
         
       } catch (SQLException ex) {
    	   con.rollback();
           ex.printStackTrace();
       }
   }
    
	
	
	public Integer verificaExistencia(Venda venda){
		for(int i = 0; i < this.listaVenda.size(); i ++){
			
			if(this.listaVenda.get(i).equals(venda)){
				return i;
			}
		}
		return null;
	}
	
	 public ArrayList<Venda> listarRelatorio(){
	    	
    	 try {
    		 
    		 this.listaVenda = new ArrayList<Venda>();
             Statement st = con.createStatement();
             String query = " select  c.nomeCliente, mo.nomeModelo, vend.nomeVendedor, v.valor, v.dataVenda from venda v" + 
					 		" join cliente c on c.codCliente=v.codCliente" +
					 		" join vendedor vend on vend.codVendedor=v.codVendedor" +
					 		" join motocicleta mot on mot.codMotocicleta=v.codMotocicleta" +
					 		" join modelo mo on mo.codModelo=mot.codModelo";
				
            
             ResultSet rs = st.executeQuery(query);
             
             while( rs.next() ){
            	
            	 
            	 Cliente c = new Cliente();
            	 
            	// c.setCodCliente(rs.getInt("c.codCliente"));
            	 c.setNomeCliente(rs.getString("c.nomeCliente"));
            	 
            	 Modelo mo = new Modelo();
            	 mo.setNomeModelo(rs.getString("mo.nomeModelo"));
            	 
            	 Motocicleta mot = new Motocicleta();
            	 mot.setModelo(mo);
            	 
            	 Vendedor vend = new Vendedor();
                 Venda ven = new Venda();
                
                 
                 ven.setCliente(c);
                 ven.setVendedor(vend);
                 ven.setMotocicleta(mot);
            	 vend.setNomeVendedor(rs.getString("vend.nomeVendedor"));
            	 ven.setValor( rs.getDouble("v.valor"));
                 ven.setDataVenda( rs.getDate("v.dataVenda"));
               
                 
                 this.listaVenda.add( ven );
             }
             con.commit();
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
         
         return this.listaVenda;
    	
    }
	

}

