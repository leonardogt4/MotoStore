package Controller;


import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Dao.ClienteDao;
import Dao.VendaDao;
import Model.Cliente;

public class ClienteController {
	
private static ClienteController instanciaRep;
    
    public static ClienteController obterInstancia(){
        if ( instanciaRep == null ){
            instanciaRep = new ClienteController();
        }
        return instanciaRep;
    }
	
	
	public Boolean ValidaCamposCliente(Cliente cliente){

		
		if(cliente == null){
			JOptionPane.showMessageDialog(null,
                    "O Cliente não pode estar em branco ou nulo",
                    "Erro de validação", JOptionPane.ERROR_MESSAGE);
            return false;
		}
		
		if(cliente.getNomeCliente().equals("") || cliente.getNomeCliente() == null || cliente.getNomeCliente().isEmpty()){
			JOptionPane.showMessageDialog(null,
                    "O nome do Cliente não pode estar em branco ou nulo",
                    "Erro de validação", JOptionPane.ERROR_MESSAGE);
            return false;
		}
		if(cliente.getTelefone().equals("") || cliente.getTelefone() == null || cliente.getTelefone().isEmpty()){
			JOptionPane.showMessageDialog(null,
                    "O telefone do Cliente não pode estar em branco ou nulo",
                    "Erro de validação", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if ( cliente.getTelefone().equals("000000000") || cliente.getTelefone().equals("11111111111") || cliente.getTelefone().equals("22222222222")
		          || cliente.getTelefone().equals("33333333333") || cliente.getTelefone().equals("44444444444") || cliente.getTelefone().equals("55555555555")
		          || cliente.getTelefone().equals("66666666666") || cliente.getTelefone().equals("77777777777") || cliente.getTelefone().equals("88888888888")
		          || cliente.getTelefone().equals("99999999999")) {

					JOptionPane.showMessageDialog(null,
                    "O telefone não é válido!",
                    "Erro de validação", JOptionPane.ERROR_MESSAGE);
					return false;
		}
		if(cliente.getCelular().equals("") ||cliente.getCelular() == null || cliente.getCelular().isEmpty()){
			JOptionPane.showMessageDialog(null,
                    "O celular do Cliente não pode estar em branco ou nulo",
                    "Erro de validação", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if ( cliente.getCelular().equals("00000000000") || cliente.getCelular().equals("11111111111") || cliente.getCelular().equals("22222222222")
		          || cliente.getCelular().equals("33333333333") || cliente.getCelular().equals("44444444444") || cliente.getCelular().equals("55555555555")
		          || cliente.getCelular().equals("66666666666") || cliente.getCelular().equals("77777777777") || cliente.getCelular().equals("88888888888")
		          || cliente.getCelular().equals("99999999999")) {

					JOptionPane.showMessageDialog(null,
                  "O celular não é válido!",
                  "Erro de validação", JOptionPane.ERROR_MESSAGE);
					return false;
		}
		if(cliente.getEmail().equals("") || cliente.getCelular().equals(null) || cliente.getEmail().isEmpty()){
			JOptionPane.showMessageDialog(null,
                    "O email do Cliente não pode estar em branco ou nulo",
                    "Erro de validação", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(!cliente.getEmail().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                +"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
			JOptionPane.showMessageDialog(null,
					"E-mail com formato incorreto!",
					"Erro de validação", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(cliente.getCnh().equals("") || cliente.getCnh().equals(null)  || cliente.getCnh().isEmpty()){
			JOptionPane.showMessageDialog(null,
                    "A Cnh do Cliente não pode estar em branco ou nulo",
                    "Erro de validação", JOptionPane.ERROR_MESSAGE);
			return false;
		}
        if( validaCnh(cliente.getCnh()) == false ){         
            JOptionPane.showMessageDialog(null,
                    "CNH com formato incorreto!",
                    "Erro de validação", JOptionPane.ERROR_MESSAGE);
            return false;
        } 
		if(cliente.getCpf().equals("") || cliente.getCpf().equals(null) || cliente.getCpf().isEmpty()){
			JOptionPane.showMessageDialog(null,
                    "O CPF do Cliente não pode estar em branco ou nulo",
                    "Erro de validação", JOptionPane.ERROR_MESSAGE);
			return false;
		}
//		if( validaCpf(cliente.getCpf()) == false ){
//            JOptionPane.showMessageDialog(null,
//                    "CPF com formato incorreto!",
//                    "Erro de validação", JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
		if(cliente.getRenda().equals("") || cliente.getRenda().equals(null)){
			JOptionPane.showMessageDialog(null,
                    "A RENDA do Cliente não pode estar em branco ou nulo",
                    "Erro de validação", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(cliente.getRenda() == 0){
			 JOptionPane.showMessageDialog(null,
                     "Não é permitido valor da renda com valor zero ou nulo!",
                     "Erro de validação",
                     JOptionPane.ERROR_MESSAGE);
			 return false;
		}
		if(cliente.getRenda() < 0){
			 JOptionPane.showMessageDialog(null,
                     "Não é permitido valor menor que 0 para a renda!",
                     "Erro de validação",
                     JOptionPane.ERROR_MESSAGE);
			 return false;
		}
		
//		if (clienteCpfExiste(cliente).equals(true)) {
//            JOptionPane.showMessageDialog(null,
//                                          "Já existe um Cliente cadastrado com este CPF!",
//                                          "Erro de validação",
//                                          JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
//		if (clienteCnhExiste(cliente).equals(true)) {
//            JOptionPane.showMessageDialog(null,
//                                          "Já existe um Cliente cadastrado com esta CNH!",
//                                          "Erro de validação",
//                                          JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
//		
		if(cliente.getNomeCliente().equals("") || cliente.getTelefone().equals("") || cliente.getCelular().equals("")
				|| cliente.getEmail().equals("") || cliente.getCnh().equals("") || cliente.getCpf().equals("") ||cliente.getRenda().equals("")){
			
			JOptionPane.showMessageDialog(null,
                    "Não é permitido campos brancos ou nulos!",
                    "Erro de validação", JOptionPane.ERROR_MESSAGE);
            return false;
		}
		
		if(cliente.getNomeCliente().equals("") && cliente.getTelefone().equals("") && cliente.getCelular().equals("")
				&& cliente.getEmail().equals("") && cliente.getCnh().equals("") && cliente.getCpf().equals("") &&cliente.getRenda().equals("")){
			
			JOptionPane.showMessageDialog(null,
                    "Não é permitido campos brancos ou nulos!",
                    "Erro de validação", JOptionPane.ERROR_MESSAGE);
            return false;
		}
		
		//Retorna true se não houver nenhum campo inválido
		return true;
		
		
		
		
	}
	
	
	public void inserir(Cliente cliente){
		//limparCampos();
		
		if(ValidaCamposCliente(cliente) == true){
			
			try {
				ClienteDao.obterInstancia().inserir(cliente);
			} catch (Exception e) {
	            JOptionPane.showMessageDialog(null,
	                    e.getMessage(),
	                    "ERRO Controller ao inserir Cliente",
	                    JOptionPane.ERROR_MESSAGE);
	            return;
	        }
			
		}else{
			JOptionPane.showMessageDialog(null,
                    "Algum campo de Cliente inválido!",
                    "Erro de validação",
                    JOptionPane.ERROR_MESSAGE);
			return;
		}
		
			
	}
	
	 //Consulta por nome do Cliente
    public ArrayList<Cliente> consultarNome(Cliente cliente){
    	return ClienteDao.obterInstancia().consultarNome(cliente);
    }
	

	
	public boolean validaCpf(String cpf) {

        String valor = cpf.replace(".", "");
        valor = valor.replace("-", "");
        cpf = valor;

        if ( cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222")
          || cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555")
          || cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888")
          || cpf.equals("99999999999")) {

            return false;
        }

        int d1, d2;
        int digito1, digito2, resto;
        int digitoCPF;
        String nDigResult;

        d1 = d2 = 0;
        digito1 = digito2 = resto = 0;

        for (int nCount = 1; nCount < cpf.length() - 1; nCount++) {
            digitoCPF = Integer.valueOf(cpf.substring(nCount - 1, nCount)).intValue();

            //multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.
            d1 = d1 + (11 - nCount) * digitoCPF;

            //para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.
            d2 = d2 + (12 - nCount) * digitoCPF;
        };

        //Primeiro resto da divisão por 11.
        resto = (d1 % 11);

        //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
        if (resto < 2) {
            digito1 = 0;
        } else {
            digito1 = 11 - resto;
        }

        d2 += 2 * digito1;

        //Segundo resto da divisão por 11.
        resto = (d2 % 11);

        //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
        if (resto < 2) {
            digito2 = 0;
        } else {
            digito2 = 11 - resto;
        }

        //Digito verificador do CPF que está sendo validado.
        String nDigVerific = cpf.substring(cpf.length() - 2, cpf.length());

        //Concatenando o primeiro resto com o segundo.
        nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

        //comparar o digito verificador do cpf com o primeiro resto + o segundo resto.
        if (nDigVerific.equals(nDigResult)) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean validaCnh(String cnh) {
        if ( cnh.equals("00000000000") || cnh.equals("11111111111") || cnh.equals("22222222222")
          || cnh.equals("33333333333") || cnh.equals("44444444444") || cnh.equals("55555555555")
          || cnh.equals("66666666666") || cnh.equals("77777777777") || cnh.equals("88888888888")
          || cnh.equals("99999999999")) {

            return false;
        } else {
            return true;
        }

    }
    
    public ArrayList<Cliente> listarTodos() {//Pesquisa Cliente
        
    	try {
			return ClienteDao.obterInstancia().listarTodos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return null;
        
    }
    
  /*  public ArrayList<Cliente> consultarNome(Cliente cliente) throws Exception{
        return ClienteDao.obterInstancia().consultarNome(cliente);
        }*/
    
    public Boolean clienteCpfExiste(Cliente cliente){
    	  boolean retorno = false;
    	  ClienteDao dao = new ClienteDao();

          try{
              
              retorno =  dao.clienteCpfExiste(cliente);
              
          } catch (SQLException ex) {
              
        	  JOptionPane.showMessageDialog(null,
                      ex.getStackTrace(),
                      "Erro de validação",
                      JOptionPane.ERROR_MESSAGE);

          } catch (Exception e) {
              
        	  JOptionPane.showMessageDialog(null,
        			  e.getStackTrace(),
                      "Erro de validação",
                      JOptionPane.ERROR_MESSAGE);

          }
          
          return retorno;
    }
    
    public Boolean clienteCnhExiste(Cliente cliente){
  	  boolean retorno = false;
  	  ClienteDao dao = new ClienteDao();

        try{
            
            retorno =  dao.clienteCnhExiste(cliente);
            
        } catch (SQLException ex) {
            
      	  JOptionPane.showMessageDialog(null,
                    "Já existe um Cliente cadastrado com esta CNH!",
                    "Erro de validação",
                    JOptionPane.ERROR_MESSAGE);

        } catch (Exception e) {
            
      	  JOptionPane.showMessageDialog(null,
                    "Já existe um Cliente cadastrado com esta CNH!",
                    "Erro de validação",
                    JOptionPane.ERROR_MESSAGE);

        }
        
        return retorno;
  }
    

    public void editar(Cliente cliente) throws Exception{
        if ( cliente == null ){
            throw new Exception("Selecione um cliente");
        }
        if ( cliente.getNomeCliente().trim().equals("") ){
            throw new Exception("Preencha o nome do cliente");
        }
        if ( cliente.getTelefone().trim().equals("") ){
            throw new Exception("Preencha o celular do cliente");
        }
        if ( cliente.getCelular().trim().equals("") ){
            throw new Exception("Preencha o celular do cliente");
        }
        if ( cliente.getEmail().trim().equals("") ){
            throw new Exception("Preencha o email do cliente");
        }
        if ( cliente.getCnh().trim().equals("") ){
            throw new Exception("Preencha o celular do cliente");
        }
        if ( cliente.getCpf().trim().equals("") ){
            throw new Exception("Preencha o CPF do cliente");
        }
        if ( cliente.getRenda().equals("") ){
            throw new Exception("Preencha o celular do cliente");
        }
        try {
        	 ClienteDao.obterInstancia().editar(cliente);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getStackTrace());
		}
       
    }
    
    public void remover(Integer id) throws Exception{//Remove cliente
        if ( id == null){
            throw new Exception("Selecione um cliente");
        }
        ClienteDao.obterInstancia().remover(id);
    }
    
    
	public ArrayList<Cliente> listaClientesVenda(){
		return ClienteDao.obterInstancia().listaClientesVenda();
	}

//	public ArrayList<Cliente> listaCli(){
//		
//		return ClienteDao.obterInstancia().consular(venda, valorInicial, valorFinal, cliente);
//		
//	}
}
