package View;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DefaultComboBoxModel;

import Controller.ClienteController;
import Controller.FabricanteController;
import Controller.MotocicletaController;
import Controller.VendaController;
import Model.Cliente;
import Model.Fabricante;
import Model.Modelo;
import Model.Motocicleta;
import Model.Venda;
import Model.Vendedor;
import Utils.ItemVendaTableModel;



import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.util.ArrayList;

public class CadastraVendaUI extends JInternalFrame {

	private JTable jtCadastroDeVenda;
	
	private JTextField  jtfValor;
	private JComboBox<Vendedor> jcbVendedor;
	private JComboBox<Cliente> jcbCliente;
	private JComboBox<Fabricante> jcbFabricante;
	private JComboBox<Motocicleta> jcbMotocicleta;
	private JDateChooser dateChooserDataVenda;
	private Double somaTotal = 0.00;
	private JLabel lblValortotal;
	private Double somaTotalRemovido=0.00;
	private JTable jtListaItensVenda;

	private ArrayList<Vendedor> listaVendedor = new VendaController().listaVendedor();
	private ArrayList<Cliente> listaClientes = new ClienteController().listarTodos();
			
	private ArrayList<Fabricante> listaFabricantes = new FabricanteController()
			.listaFabricantes();
	private ArrayList<Motocicleta> listaModelos = new MotocicletaController().listaModelos();
			
	private ArrayList<Venda> listaVendas = new VendaController().listaVendas();



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastraVendaUI frame = new CadastraVendaUI();
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
	public CadastraVendaUI() {
		setMaximizable(true);

		setClosable(true);
		setTitle("Cadastro Venda");
		setBounds(100, 100, 563, 383);

		JLabel jlbVendedor = new JLabel("Vendedor:");

		jcbVendedor = new JComboBox<Vendedor>();
		DefaultComboBoxModel<Vendedor> modelVendedor = new DefaultComboBoxModel<Vendedor>();
		for (Vendedor vendedor : listaVendedor) {
			modelVendedor.addElement(vendedor);
		}
		jcbVendedor.setModel(modelVendedor);

		JLabel jlbFormaDePagamento = new JLabel("Forma de Pagamento:");

		final JComboBox jcbFormaPagamento = new JComboBox();
		jcbFormaPagamento.setModel(new DefaultComboBoxModel(new String[] {
				"Dinheiro", "Cart\u00E3o/Cr\u00E9dito",
				"Cart\u00E3o/D\u00E9bito", "Cheque", "Outra" }));

		JLabel jlbCliente = new JLabel("Cliente:");

		jcbCliente = new JComboBox<Cliente>();
		DefaultComboBoxModel<Cliente> modelCliente = new DefaultComboBoxModel<Cliente>();
		for (Cliente cliente : listaClientes) {
			modelCliente.addElement(cliente);
		}
		jcbCliente.setModel(modelCliente);

		JLabel jlbFabricante = new JLabel("Fabricante:");

		jcbFabricante = new JComboBox<Fabricante>();
		DefaultComboBoxModel<Fabricante> modelFabricante = new DefaultComboBoxModel<Fabricante>();
		for (Fabricante fabricante : listaFabricantes) {
			modelFabricante.addElement(fabricante);
		}
		jcbFabricante.setModel(modelFabricante);

		JLabel jlbMotocicleta = new JLabel("Motocicleta:");

		jcbMotocicleta = new JComboBox<Motocicleta>();
		DefaultComboBoxModel<Motocicleta> modelMotocicleta = new DefaultComboBoxModel<Motocicleta>();
		for (Motocicleta motocicleta : listaModelos) {
			modelMotocicleta.addElement(motocicleta);
		}
		jcbMotocicleta.setModel(modelMotocicleta);

		JLabel jlbDataVenda = new JLabel("Data:");

		//JDateChooser dateChooserDataVenda = new JDateChooser();
		dateChooserDataVenda = new JDateChooser();
		getContentPane().add(dateChooserDataVenda);
		
		JButton btnAdicionar = new JButton("");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Inserir
					
				Venda venda = new Venda();
				
				
				venda.setVendedor((Vendedor)jcbVendedor.getSelectedItem());
				venda.setFornecedor((Fabricante)jcbFabricante.getSelectedItem());
				venda.setCliente((Cliente)jcbCliente.getSelectedItem());
				venda.setMotocicleta((Motocicleta)jcbMotocicleta.getSelectedItem());
				venda.setFormaPagamento(jcbFormaPagamento.getSelectedItem().toString());
				venda.setDataVenda(dateChooserDataVenda.getDate());
				venda.setValor(Double.parseDouble(jtfValor.getText() ));
				venda.setValorTotal( venda.getValor() * venda.getQuantidade() );
			
				
				listaVendas.add(venda);
				
			//	jtListaItensVenda.setModel(new ItemVendaTableModel(listaVendas));	

				
				somaTotal += venda.getValorTotal();
				lblValortotal.setText(somaTotal.toString());
				
				try {
					VendaController veController = new VendaController();
					
					if (veController.validaCamposVendas(venda)) {
						
						
					//	jtListaItensVenda.setModel(new ItemVendaTableModel(listaVendas));	

						DefaultTableModel dtm = (DefaultTableModel) jtCadastroDeVenda.getModel();
						dtm.addRow(new Object[] {

						jcbVendedor.getSelectedItem(),
								jcbCliente.getSelectedItem(),
								Double.parseDouble(jtfValor.getText()) });		

					}

				} catch (Exception e3) {
					JOptionPane.showMessageDialog(null, e3.getMessage(),
							"Erro ao venda VendaUI",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnAdicionar.setIcon(new ImageIcon(CadastraVendaUI.class
				.getResource("/img/AddVenda.png")));

		JButton btnConcluir = new JButton("");
		btnConcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Venda venda = new Venda();

				venda.setVendedor((Vendedor)jcbVendedor.getSelectedItem());
				venda.setFornecedor((Fabricante)jcbFabricante.getSelectedItem());
				venda.setCliente((Cliente)jcbCliente.getSelectedItem());
				venda.setMotocicleta((Motocicleta)jcbMotocicleta.getSelectedItem());
				venda.setFormaPagamento(jcbFormaPagamento.getSelectedItem().toString());
				venda.setDataVenda(dateChooserDataVenda.getDate());
				venda.setValor(Double.parseDouble(jtfValor.getText()));
				venda.setValorTotal(Double.parseDouble(lblValortotal.getText() ));
				venda.setQuantidade(venda.getQuantidade());
						
	
				VendaController veController = new VendaController();
				
				
				//Inserir
				
				try {
					
//					if(veController.validaCamposVendas(venda)){
						veController.inserir(venda);
						
	//				}
					
				} catch (Exception e3) {
					e3.printStackTrace();
					//JOptionPane.showMessageDialog(null, e3.getMessage(), "Erro ao inserir Venda View", JOptionPane.ERROR_MESSAGE );
				}
				
			}
			
			
		});
	
		
		btnConcluir.setIcon(new ImageIcon(CadastraVendaUI.class
				.getResource("/img/ConcluirVenda.png")));

		JScrollPane jscrollPane = new JScrollPane();

		JButton btnRemover = new JButton("");
		btnRemover.addActionListener(new ActionListener() {
		

			
			public void actionPerformed(ActionEvent arg0) {
				
int opcao = JOptionPane.showConfirmDialog(null, "Deseja remover o item selecionado?","Excluir item da venda", JOptionPane.YES_NO_OPTION);
				
				if(opcao == 0){
					
		DefaultTableModel dtm = (DefaultTableModel) jtCadastroDeVenda.getModel();
		Venda venda = new Venda();
		
					int row = jtCadastroDeVenda.getSelectedRow();
						
					if ( row > -1 ) {
						
						 dtm.removeRow( row );
						
					    for(int i = 0; i < jtCadastroDeVenda.getRowCount(); i++){
					    	
					    	somaTotal-=somaTotal+Double.parseDouble(jtCadastroDeVenda.getValueAt(i,2).toString());

						
					    	somaTotal -=  venda.getValor();	
					    	lblValortotal.setText(somaTotal.toString());	
						
						
					    }			
								
							
								
								
					} else {
						JOptionPane.showMessageDialog( null , "Voce deve selecionar ao menos uma linha");
					}
						
				}
					
				}
				
			});
		btnRemover.setIcon(new ImageIcon(CadastraVendaUI.class
				.getResource("/img/RemoveVenda.png")));

		JLabel jlValor = new JLabel("Valor:");

		jtCadastroDeVenda = new JTable();
		jtCadastroDeVenda
				.setModel(new DefaultTableModel(new Object[][] {},
						new String[] { "Nome Vendedor", "Nome Cliente",
								"Valor Venda" }) {
					Class[] columnTypes = new Class[] { String.class,
							String.class, Double.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
		jtCadastroDeVenda.getColumnModel().getColumn(0).setResizable(false);
		jtCadastroDeVenda.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtCadastroDeVenda.getColumnModel().getColumn(1).setResizable(false);
		jtCadastroDeVenda.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtCadastroDeVenda.getColumnModel().getColumn(2).setResizable(false);
		jscrollPane.setViewportView(jtCadastroDeVenda);

		jtfValor = new JTextField();
		jtfValor.setColumns(10);

		JLabel lblTotalDaVenda = new JLabel("Total da Venda:");
		lblTotalDaVenda.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblValortotal = new JLabel("");
		lblValortotal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(jlbFormaDePagamento)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jcbFormaPagamento, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(jlbDataVenda)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(dateChooserDataVenda, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(jlValor)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jtfValor, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(20)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(jlbCliente)
									.addGap(23)
									.addComponent(jcbCliente, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(jlbVendedor)
									.addGap(10)
									.addComponent(jcbVendedor, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(jlbMotocicleta)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(jcbMotocicleta, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(jlbFabricante)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(jcbFabricante, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(29)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(jscrollPane, GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblTotalDaVenda)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblValortotal, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
									.addComponent(btnAdicionar)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnRemover)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnConcluir)))))
					.addGap(40))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(3)
									.addComponent(jlbVendedor))
								.addComponent(jcbVendedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(27)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(3)
									.addComponent(jlbCliente))
								.addComponent(jcbCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(21))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(jlbFabricante)
								.addComponent(jcbFabricante, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(30)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(jlbMotocicleta)
								.addComponent(jcbMotocicleta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(jlbFormaDePagamento))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(jtfValor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(jlValor))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(dateChooserDataVenda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(jcbFormaPagamento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(jlbDataVenda)))))
					.addGap(58)
					.addComponent(jscrollPane, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnAdicionar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnRemover, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblTotalDaVenda)
							.addComponent(lblValortotal, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnConcluir, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(9))
		);
		getContentPane().setLayout(groupLayout);

	}
}
