package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import Controller.ClienteController;
import Controller.SaidaController;
import Dao.SaidaDao;
import Model.Cliente;
import Model.Saida;

public class PesquisaSaidaUI extends JInternalFrame {
	
	
	private JTable jtTabelaPesquisaSaidas;
	
	private ArrayList<Saida> listaSaidas;
	
	//private ArrayList<Cliente> listaClientes = new ClienteController().listaClientesVenda();
	//private ArrayList<Vendedor> listasDeVendedores = new VendaController().listaVendedor();//precisa alterar para vendedores que somente efetuaram venda
	private JTextField jtfPesquisaSaidaCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PesquisaSaidaUI frame = new PesquisaSaidaUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void atualizarTabelaSaida() throws Exception{
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new String[] {"Cliente","Data"});
        this.listaSaidas = SaidaController.obterInstancia().listas();
        for ( int i=0; i < this.listaSaidas.size(); i++){
            modelo.addRow(new Object[] { 
                this.listaSaidas.get(i).getCliente().getNomeCliente(),
                this.listaSaidas.get(i).getDataSaida()
                });
        }  
        jtTabelaPesquisaSaidas.setModel(modelo);
        
    }
	
	//Cria a tabela pra pesquisa somente por nome
		public void tabelaListaNome(Saida saida){
			DefaultTableModel modelo = new DefaultTableModel();
		       modelo.setColumnIdentifiers(new String[] {"Cliente","Data"});
		       this.listaSaidas = SaidaController.obterInstancia().consultarNome(saida);
		       for ( int i=0; i < this.listaSaidas.size(); i++){
		           modelo.addRow(new Object[] {
		           	this.listaSaidas.get(i).getCliente().getNomeCliente(), 
		               this.listaSaidas.get(i).getDataSaida(),
		               
		               });
		       }  
		       jtTabelaPesquisaSaidas.setModel(modelo);
		}
 
	
	
	public PesquisaSaidaUI() {
		setClosable(true);
		setTitle("Pesquisa de Sa\u00EDdas");
		setBounds(100, 100, 373, 353);
				
		JLabel lblCliente = new JLabel("Cliente:");
		
		
	
		
		JButton btnPesquisaSaida = new JButton("");
		btnPesquisaSaida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Pesquisar
				if( jtfPesquisaSaidaCliente.getText().equals("") && jtfPesquisaSaidaCliente.getText() == null){
					try {
						atualizarTabelaSaida();
					} catch (Exception e1) {
						
						e1.printStackTrace();
					}
				}else{
					Cliente cli = new Cliente();
					cli.setNomeCliente(jtfPesquisaSaidaCliente.getText());
					
					Saida saida = new Saida();
					saida.setCliente(cli);
					//saida.getCliente().setNomeCliente( jtfPesquisaSaidaCliente.getText().trim()) ;
					
					try {
						SaidaDao.obterInstancia().consultarNome(saida);
						tabelaListaNome(saida);
					} catch (Exception e1) {
					
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnPesquisaSaida.setIcon(new ImageIcon(PesquisaSaidaUI.class.getResource("/img/Pesquisar.png")));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnRemoveSaida = new JButton("");
		btnRemoveSaida.setToolTipText("Remover Sa\u00EDda");
		btnRemoveSaida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//remover
				try{
	                listaSaidas = SaidaController.obterInstancia().listas();
	                SaidaController.obterInstancia().
                    excluir(listaSaidas.get(jtTabelaPesquisaSaidas.getSelectedRow()).getCodSaida());
	                atualizarTabelaSaida();
	                        
	               
	                
	            } catch ( ArrayIndexOutOfBoundsException a){
	                JOptionPane.showMessageDialog(null, "Selecione um cliente");
	            } catch (Exception e1){
	            	System.out.println("caiu aqui");
	                e1.printStackTrace();
	            } 
	            
				}
	                
	        
			
		});
		btnRemoveSaida.setIcon(new ImageIcon(PesquisaSaidaUI.class.getResource("/img/RemoveMoto.png")));
		
		jtTabelaPesquisaSaidas = new JTable();
		jtTabelaPesquisaSaidas.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Cliente", "Data"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		jtTabelaPesquisaSaidas.getColumnModel().getColumn(0).setResizable(false);
		jtTabelaPesquisaSaidas.getColumnModel().getColumn(1).setResizable(false);
		scrollPane.setViewportView(jtTabelaPesquisaSaidas);
		
		jtfPesquisaSaidaCliente = new JTextField();
		jtfPesquisaSaidaCliente.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(10, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(lblCliente)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(jtfPesquisaSaidaCliente, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnPesquisaSaida, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(17)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnRemoveSaida, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE))))
					.addGap(34))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(14)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCliente)
								.addComponent(jtfPesquisaSaidaCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(btnPesquisaSaida))
					.addGap(56)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnRemoveSaida)
					.addContainerGap(47, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
}
