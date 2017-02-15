package View;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.ClienteController;
import Controller.VendaController;
import Dao.ClienteDao;
import Dao.VendaDao;
import Model.Cliente;
import Model.Venda;
import Model.Vendedor;

import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PesquisaVendaUI extends JInternalFrame {
	
	private JComboBox<Cliente> jcbCliente;
	private ArrayList<Venda> listaVendas;
	
	private JTable jtTabelaPesquisaVendas;
	private JTextField jtfValorInicial;
	private JTextField jtfValorFinal;
	
	private ArrayList<Cliente> listaClientesVenda = new ClienteController().listaClientesVenda();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PesquisaVendaUI frame = new PesquisaVendaUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void tabelaPesquisaVendas(){
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.setColumnIdentifiers(new String[] {"Nome Cliente","FormaPagamento","Valor"});
        this.listaVendas = VendaController.obterInstancia().listaVendas();
        for ( int i=0; i < this.listaVendas.size(); i++){
            modelo.addRow(new Object[] { 
            	this.listaVendas.get(i).getCliente().getNomeCliente(),
                this.listaVendas.get(i).getFormaPagamento(),
                this.listaVendas.get(i).getValor()
                });
        }  
        jtTabelaPesquisaVendas.setModel(modelo);
        
    }

	
	public PesquisaVendaUI() {
		setClosable(true);
		setTitle("Pesquisa de Vendas");
		setBounds(100, 100, 456, 345);
		
		JLabel jlValorInicial = new JLabel("Valor Inicial:");
		
		JLabel jlCliente = new JLabel("Cliente:");
		
		jcbCliente = new JComboBox<Cliente>();
		DefaultComboBoxModel<Cliente> modelCliente = new DefaultComboBoxModel<Cliente>();
		for (Cliente cliente : listaClientesVenda) {
			modelCliente.addElement(cliente);
		}
		jcbCliente.setModel(modelCliente);
		
		JButton btnPesquisar = new JButton("");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Double valorInicial=0.00;
				Double valorFinal=0.00;
			//	if(jtfNomeCliente.getText().equals("")){
				if(jcbCliente.getItemCount() != 0) {
				tabelaPesquisaVendas();
				
		
				Venda venda = new Venda();
				venda.setCliente( jcbCliente.getSelectedItem().toString() );
				
				try {
				//	Venda venda = new Venda();
					VendaDao.obterInstancia().consultarNomeVenda(venda, valorFinal, valorFinal, null);
					tabelaPesquisaVendas();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		}
	});
		btnPesquisar.setIcon(new ImageIcon(PesquisaVendaUI.class.getResource("/img/PesquisaVenda.png")));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnRemover = new JButton("");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// remover
	            try{
	                listaVendas = VendaController.obterInstancia().listaVendas();
	               VendaController.obterInstancia().
	                        remover(listaVendas.get(jtTabelaPesquisaVendas.getSelectedRow()).getCodVenda());
	               tabelaPesquisaVendas();
	                
	            } catch ( ArrayIndexOutOfBoundsException a){
	                JOptionPane.showMessageDialog(null, "Selecione um cliente");
	            } catch (Exception e1){
	                JOptionPane.showMessageDialog(null, e1.getMessage());
	            } 
	            
	            }
//				
			
		});
		btnRemover.setIcon(new ImageIcon(PesquisaVendaUI.class.getResource("/img/RemoveVenda.png")));
		
		jtfValorInicial = new JTextField();
		jtfValorInicial.setColumns(10);
		
		JLabel lblValorFinal = new JLabel("Valor Final:");
		
		jtfValorFinal = new JTextField();
		jtfValorFinal.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnPesquisar, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addGap(32)
							.addComponent(btnRemover)
							.addGap(46))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(jlValorInicial)
								.addComponent(jlCliente))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(jtfValorInicial, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
									.addComponent(lblValorFinal)
									.addGap(27)
									.addComponent(jtfValorFinal, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
								.addComponent(jcbCliente, 0, 312, Short.MAX_VALUE))))
					.addGap(39))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(jlValorInicial)
							.addComponent(jtfValorInicial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(jcbCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(jlCliente))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(jtfValorFinal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblValorFinal))))
					.addGap(28)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnRemover, 0, 0, Short.MAX_VALUE)
						.addComponent(btnPesquisar, 0, 0, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		jtTabelaPesquisaVendas = new JTable();
		jtTabelaPesquisaVendas.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Nome do Cliente", "Forma de Pagamento", "Valor"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		jtTabelaPesquisaVendas.getColumnModel().getColumn(0).setResizable(false);
		jtTabelaPesquisaVendas.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtTabelaPesquisaVendas.getColumnModel().getColumn(1).setResizable(false);
		jtTabelaPesquisaVendas.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtTabelaPesquisaVendas.getColumnModel().getColumn(2).setResizable(false);
		jtTabelaPesquisaVendas.getColumnModel().getColumn(2).setPreferredWidth(40);
		scrollPane.setViewportView(jtTabelaPesquisaVendas);
		getContentPane().setLayout(groupLayout);

	}
}
