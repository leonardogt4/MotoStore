package View;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;

import Controller.ClienteController;
import Controller.VendaController;
import Model.Cliente;
import Model.Venda;
import Model.Vendedor;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;

public class RelatorioUI extends JInternalFrame {
	private JTable jtTabelaRelatorio;
	private JTextField jtfValorTotalVenda;
	private ArrayList<Venda> relatorioDaVenda;
	
	
	private JComboBox<Cliente> jcbCliente;
	private JComboBox<Vendedor> jcbVendedor;
	
	
	private ArrayList<Cliente> listaClientes = new ClienteController().listaClientesVenda();
	private ArrayList<Vendedor> listaVendedor = new VendaController().listaVendedor();

	
	
	public void tabelaPesquisaVendas(){
			DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new String[] {"Vendedor "," Cliente","Motocicleta","Valor","Data da Venda"});
        this.relatorioDaVenda = VendaController.obterInstancia().listaVendas();
        for ( int i=0; i < this.relatorioDaVenda.size(); i++){
            modelo.addRow(new Object[] { 
            	this.relatorioDaVenda.get(i).getVendedor().getNomeVendedor(),
                this.relatorioDaVenda.get(i).getCliente().getNomeCliente(),
                this.relatorioDaVenda.get(i).getMotocicleta().getModelo(),
                this.relatorioDaVenda.get(i).getValor(),
                this.relatorioDaVenda.get(i).getDataVenda()
                });
        }  
        jtTabelaRelatorio.setModel(modelo);
        
    }
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RelatorioUI frame = new RelatorioUI();
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
	public RelatorioUI() {
		setClosable(true);
		setTitle("Relatorio de Vendas");
		setBounds(100, 100, 595, 431);
		
		JLabel lbCliente = new JLabel("Cliente:");
		
		jcbCliente = new JComboBox<Cliente>();
		DefaultComboBoxModel<Cliente> modelCliente = new DefaultComboBoxModel<Cliente>();
		for (Cliente cliente : listaClientes) {
			modelCliente.addElement(cliente);
		}
		jcbCliente.setModel(modelCliente);
		
		
		JLabel lblVendedor = new JLabel("Vendedor:");
		
		jcbVendedor = new JComboBox<Vendedor>();
		DefaultComboBoxModel<Vendedor> modelVendedor = new DefaultComboBoxModel<Vendedor>();
		for (Vendedor vendedor : listaVendedor) {
			modelVendedor.addElement(vendedor);
		}
		jcbVendedor.setModel(modelVendedor);
		
		
		JLabel jlDataInicio = new JLabel("Data In\u00EDcio:");
		
		JLabel lblDataFim = new JLabel("Data Fim:");
		
		JDateChooser jdateChooserInicio = new JDateChooser();
		
		JDateChooser jdateChooserFim = new JDateChooser();
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnPesquisar = new JButton("");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				tabelaPesquisaVendas();
				
				
			}
		});
		btnPesquisar.setIcon(new ImageIcon(RelatorioUI.class.getResource("/img/ReportMoto.png")));
		
		JButton btnExportar = new JButton("");
		btnExportar.setIcon(new ImageIcon(RelatorioUI.class.getResource("/img/ExportMotoPDF.png")));
		
		JLabel jlbTotalDaVenda = new JLabel("Total da Venda:");
		jlbTotalDaVenda.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		jtfValorTotalVenda = new JTextField();
		jtfValorTotalVenda.setText("R$");
		jtfValorTotalVenda.setEditable(false);
		jtfValorTotalVenda.setColumns(10);
		
		jtTabelaRelatorio = new JTable();
		jtTabelaRelatorio.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, ""},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Vendedor", "Cliente", "Motocicleta", "Valor", "Data da Venda"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, Double.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, true, true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		jtTabelaRelatorio.getColumnModel().getColumn(0).setResizable(false);
		jtTabelaRelatorio.getColumnModel().getColumn(0).setPreferredWidth(64);
		jtTabelaRelatorio.getColumnModel().getColumn(1).setResizable(false);
		jtTabelaRelatorio.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtTabelaRelatorio.getColumnModel().getColumn(2).setResizable(false);
		jtTabelaRelatorio.getColumnModel().getColumn(2).setPreferredWidth(100);
		jtTabelaRelatorio.getColumnModel().getColumn(3).setResizable(false);
		jtTabelaRelatorio.getColumnModel().getColumn(3).setPreferredWidth(40);
		jtTabelaRelatorio.getColumnModel().getColumn(4).setResizable(false);
		jtTabelaRelatorio.getColumnModel().getColumn(4).setPreferredWidth(60);
		scrollPane.setViewportView(jtTabelaRelatorio);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblVendedor)
							.addGap(4)
							.addComponent(jcbVendedor, GroupLayout.PREFERRED_SIZE, 322, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(jlDataInicio)
							.addGap(18)
							.addComponent(jdateChooserInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lbCliente)
							.addGap(18)
							.addComponent(jcbCliente, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblDataFim)
							.addGap(27)
							.addComponent(jdateChooserFim, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(jlbTotalDaVenda)
								.addGap(18)
								.addComponent(jtfValorTotalVenda, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnPesquisar)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnExportar))
							.addComponent(scrollPane, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 550, GroupLayout.PREFERRED_SIZE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblVendedor)
						.addComponent(jcbVendedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(jlDataInicio))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(jdateChooserInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(lbCliente))
						.addComponent(jcbCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(lblDataFim))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(jdateChooserFim, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(36)
							.addComponent(jlbTotalDaVenda))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(34)
							.addComponent(jtfValorTotalVenda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(15)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnPesquisar)
								.addComponent(btnExportar, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);

	}
}
