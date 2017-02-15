package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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
import Dao.ClienteDao;
import Model.Cliente;

public class PesquisaClienteUI extends JInternalFrame {
	private ArrayList<Cliente> listaCliente;
	private JTable jtTabelaPesquisaCliente;
	private JTextField jtfNomeCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PesquisaClienteUI frame = new PesquisaClienteUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void atualizarTabelaClientes() throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new String[] {"Nome","Telefone","Renda"});
        this.listaCliente = ClienteController.obterInstancia().listarTodos();
        for ( int i=0; i < this.listaCliente.size(); i++){
            modelo.addRow(new Object[] { 
            	this.listaCliente.get(i).getNomeCliente(), 
                this.listaCliente.get(i).getTelefone(),
                this.listaCliente.get(i).getRenda()
                });
        }  
        jtTabelaPesquisaCliente.setModel(modelo);
        
    }
	
	//Cria a tabela pra pesquisa somente por nome
		public void tabelaListaNome(Cliente cliente){
			DefaultTableModel modelo = new DefaultTableModel();
	        modelo.setColumnIdentifiers(new String[] {"Nome","Telefone","Renda"});
	        this.listaCliente = ClienteController.obterInstancia().consultarNome(cliente);
	        for ( int i=0; i < this.listaCliente.size(); i++){
	            modelo.addRow(new Object[] {
	            	this.listaCliente.get(i).getNomeCliente(), 
	                this.listaCliente.get(i).getTelefone(),
	                this.listaCliente.get(i).getRenda()
	                });
	        }  
	        jtTabelaPesquisaCliente.setModel(modelo);
		}

	/**
	 * Create the frame.
	 */
	public PesquisaClienteUI() {
		setClosable(true);
		setTitle("Pesquisa de Clientes");
		setBounds(100, 100, 402, 334);
		
		JLabel lbNomeDoCliente = new JLabel("Nome do Cliente:");
		
		JButton btnPesquisaCliente = new JButton("");
		btnPesquisaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(jtfNomeCliente.getText().equals("") && jtfNomeCliente.getText() == null){
					try {
						atualizarTabelaClientes();
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}else{
					Cliente cliente = new Cliente();
					cliente.setNomeCliente( jtfNomeCliente.getText().trim() );
					
					try {
						ClienteDao.obterInstancia().consultarNome(cliente);
						tabelaListaNome(cliente);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnPesquisaCliente.setIcon(new ImageIcon(PesquisaClienteUI.class.getResource("/img/Pesquisar.png")));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					

				listaCliente = ClienteController.obterInstancia().listarTodos();
	
				
				CadastraClienteUI cadCliEdit = new  CadastraClienteUI( listaCliente.get(jtTabelaPesquisaCliente.getSelectedRow()));	
				PrincipalUI.obterInstancia().getContentPane().add(cadCliEdit);
				cadCliEdit.setVisible(true);
		}catch ( ArrayIndexOutOfBoundsException a){
		   JOptionPane.showMessageDialog(null, "Selecione um cliente");	
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}
			}
				
			});
		btnEditar.setIcon(new ImageIcon(PesquisaClienteUI.class.getResource("/img/EditCliente.png")));
		
		JButton btnDeletar = new JButton("");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// remover
	            try{
	                listaCliente = ClienteController.obterInstancia().listarTodos();
	                ClienteController.obterInstancia().
	                        remover(listaCliente.get(jtTabelaPesquisaCliente.getSelectedRow()).getCodCliente());
	                atualizarTabelaClientes();
	                
	            } catch ( ArrayIndexOutOfBoundsException a){
	                JOptionPane.showMessageDialog(null, "Selecione um cliente");
	            } catch (Exception e1){
	                JOptionPane.showMessageDialog(null, e1.getMessage());
	            } 
	            
	            }
//				
			
		});
		btnDeletar.setIcon(new ImageIcon(PesquisaClienteUI.class.getResource("/img/RemoveCliente.png")));
		
		jtfNomeCliente = new JTextField();
		jtfNomeCliente.setColumns(10);
		
		jtTabelaPesquisaCliente = new JTable();
		jtTabelaPesquisaCliente.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, ""},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Nome do Cliente", "Telefone", "Renda"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		jtTabelaPesquisaCliente.getColumnModel().getColumn(0).setResizable(false);
		jtTabelaPesquisaCliente.getColumnModel().getColumn(0).setPreferredWidth(115);
		jtTabelaPesquisaCliente.getColumnModel().getColumn(1).setResizable(false);
		jtTabelaPesquisaCliente.getColumnModel().getColumn(2).setResizable(false);
		jtTabelaPesquisaCliente.getColumnModel().getColumn(2).setPreferredWidth(63);
		scrollPane.setViewportView(jtTabelaPesquisaCliente);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lbNomeDoCliente)
							.addGap(10)
							.addComponent(jtfNomeCliente, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnPesquisaCliente, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnEditar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDeletar))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lbNomeDoCliente))
						.addComponent(jtfNomeCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisaCliente))
					.addGap(27)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnDeletar, 0, 0, Short.MAX_VALUE)
						.addComponent(btnEditar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
}
