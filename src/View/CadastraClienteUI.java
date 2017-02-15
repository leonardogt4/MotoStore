package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import Model.Cliente;

public class CadastraClienteUI extends JInternalFrame {
	private JTextField jtfCliente;
	private JTextField jtfCnh;
	private JTextField jtfCpf;
	private JTextField jtfRenda;
	private JTextField jtfTelefoneFixo;
	private JTextField jtfTelefoneCelular;
	private JTextField jtfEmail;
	private JTable jtTabelaCadastraCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastraClienteUI frame = new CadastraClienteUI(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastraClienteUI(final Cliente cliente) {
		
		//Verificar codigo ClienteUI do sistema de vendas e implementar as configurações pra inserir cliente
		setClosable(true);
		setTitle("Cadastro Cliente");
		setBounds(100, 100, 424, 440);
		
		JLabel jblNome = new JLabel("Nome:");
		
		JLabel jblCnh = new JLabel("CNH:");
		
		JLabel jblCpf = new JLabel("CPF:");
		
		JLabel jblRenda = new JLabel("Renda:");
		
		JLabel jblTelefoneFixo = new JLabel("Telefone Fixo:");
		
		JLabel jblTelefoneCelular = new JLabel("Telefone Celular:");
		
		JLabel jblEmail = new JLabel("Email:");
		
		jtfCliente = new JTextField();
		jtfCliente.setColumns(10);
		
		jtfCnh = new JTextField();
		jtfCnh.setColumns(10);
		
		jtfCpf = new JTextField();
		jtfCpf.setColumns(10);
		
		jtfRenda = new JTextField();
		jtfRenda.setColumns(10);
		
		jtfTelefoneFixo = new JTextField();
		jtfTelefoneFixo.setColumns(10);
		
		jtfTelefoneCelular = new JTextField();
		jtfTelefoneCelular.setColumns(10);
		
		jtfEmail = new JTextField();
		jtfEmail.setColumns(10);
		
		
		if(cliente != null){
			//Editar
			jtfCliente.setText(cliente.getNomeCliente());
			jtfCnh.setText(cliente.getCnh());
			jtfCpf.setText(cliente.getCpf());
            jtfEmail.setText(cliente.getEmail());
            jtfRenda.setText(cliente.getRenda().toString());
            jtfTelefoneCelular.setText(cliente.getCelular());
            jtfTelefoneFixo.setText(cliente.getTelefone());
            
            ClienteController cc = new ClienteController();
            try {
				cc.editar(cliente);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}  
            
		}
		
		JButton btnAdicionar = new JButton("");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				//Inserir
				Cliente cliente = new Cliente();
				cliente.setNomeCliente( jtfCliente.getText()  );
				cliente.setTelefone( jtfTelefoneFixo.getText() );
				cliente.setCelular( jtfTelefoneCelular.getText() );
				cliente.setEmail( jtfEmail.getText() );
				cliente.setCnh( jtfCnh.getText() );
				cliente.setCpf( jtfCpf.getText() );
				cliente.setRenda( Double.parseDouble(jtfRenda.getText()) );
				
				
				try {
					
					ClienteController cliController = new ClienteController();
					if(cliController.ValidaCamposCliente(cliente)){
						
						DefaultTableModel dtm = (DefaultTableModel) jtTabelaCadastraCliente.getModel();
						dtm.addRow( new String[] {
									jtfCliente.getText(),
									jtfCpf.getText()
									
						});
						
						
					}
					
				} catch (Exception e3) {
					JOptionPane.showMessageDialog(null, e3.getMessage(), "Erro ao inserir ClienteUI na Lista ", JOptionPane.ERROR_MESSAGE );
				}
				
				
			}
		});
		btnAdicionar.setIcon(new ImageIcon(CadastraClienteUI.class.getResource("/img/AddCliente.png")));
		
		JButton btnConcluir = new JButton("");
		btnConcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if(cliente != null){
					
					cliente.setNomeCliente( jtfCliente.getText()  );
					cliente.setTelefone( jtfTelefoneFixo.getText() );
					cliente.setCelular( jtfTelefoneCelular.getText() );
					cliente.setEmail( jtfEmail.getText() );
					cliente.setCnh( jtfCnh.getText() );
					cliente.setCpf( jtfCpf.getText() );
					cliente.setRenda( Double.parseDouble(jtfRenda.getText()) );
		           
		            
		            
		            ClienteController cc = new ClienteController();
		            try {
						//cc.editar(cliente);
		            	cc.editar(cliente);
						
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					
		           
		       }else{
					
					//Inserir
					Cliente cliente = new Cliente();
					cliente.setNomeCliente( jtfCliente.getText()  );
					cliente.setTelefone( jtfTelefoneFixo.getText() );
					cliente.setCelular( jtfTelefoneCelular.getText() );
					cliente.setEmail( jtfEmail.getText() );
					cliente.setCnh( jtfCnh.getText() );
					cliente.setCpf( jtfCpf.getText() );
					cliente.setRenda( Double.parseDouble(jtfRenda.getText()) );
					ClienteController cliController = new ClienteController();
					
					try {
						
						if(cliController.ValidaCamposCliente(cliente)){
							cliController.inserir(cliente);
							JOptionPane.showMessageDialog(null, "Cliente inserido com sucesso.");
						}
						
					} catch (Exception e3) {
						JOptionPane.showMessageDialog(null, e3.getMessage(), "Erro ao inserir Cliente View", JOptionPane.ERROR_MESSAGE );
					}
					
				}
				
				
			}
		});
		btnConcluir.setIcon(new ImageIcon(CadastraClienteUI.class.getResource("/img/ConcluirCliente.png")));
		
		
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane jscrollPane = new JScrollPane();
		
		JButton btnRemover = new JButton("");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
				DefaultTableModel dtm = (DefaultTableModel) jtTabelaCadastraCliente.getModel();
					
				int row = jtTabelaCadastraCliente.getSelectedRow();
					
				if ( row > -1 ) {
					dtm.removeRow( row );
				} else {
					JOptionPane.showMessageDialog( null , "Voce deve selecionar ao menos uma linha");
				}
					
			}
		});
		btnRemover.setIcon(new ImageIcon(CadastraClienteUI.class.getResource("/img/RemoveCliente.png")));
		
		jtTabelaCadastraCliente = new JTable();
		jtTabelaCadastraCliente.setModel(new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
				"Nome Cliente", "CPF"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		jtTabelaCadastraCliente.getColumnModel().getColumn(0).setResizable(false);
		jtTabelaCadastraCliente.getColumnModel().getColumn(0).setPreferredWidth(130);
		jtTabelaCadastraCliente.getColumnModel().getColumn(1).setResizable(false);
		jscrollPane.setViewportView(jtTabelaCadastraCliente);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(11)
							.addComponent(jblNome)
							.addGap(10)
							.addComponent(jtfCliente, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(jblCnh)
							.addGap(23)
							.addComponent(jtfCnh, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
							.addGap(36)
							.addComponent(jblTelefoneFixo)
							.addGap(31)
							.addComponent(jtfTelefoneFixo, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(jblCpf)
							.addGap(25)
							.addComponent(jtfCpf, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
							.addGap(36)
							.addComponent(jblTelefoneCelular)
							.addGap(18)
							.addComponent(jtfTelefoneCelular, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(11)
							.addComponent(jblRenda)
							.addGap(12)
							.addComponent(jtfRenda, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
							.addGap(36)
							.addComponent(jblEmail)
							.addGap(29)
							.addComponent(jtfEmail, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(19)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnAdicionar)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnRemover)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnConcluir))
								.addComponent(jscrollPane, GroupLayout.PREFERRED_SIZE, 373, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(jblNome))
						.addComponent(jtfCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(jblCnh))
						.addComponent(jtfCnh, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(jblTelefoneFixo))
						.addComponent(jtfTelefoneFixo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(jblCpf)
						.addComponent(jtfCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(jblTelefoneCelular))
						.addComponent(jtfTelefoneCelular, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(jblRenda))
						.addComponent(jtfRenda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(jblEmail))
						.addComponent(jtfEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jscrollPane, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnConcluir, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnAdicionar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnRemover, 0, 0, Short.MAX_VALUE))
					.addContainerGap(31, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}

	
}
