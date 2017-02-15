package View;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.ClienteController;
import Controller.FabricanteController;
import Controller.ModeloController;
import Controller.MotocicletaController;
import Model.Fabricante;
import Model.Modelo;
import Model.Motocicleta;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class PesquisaMotocicletaUI extends JInternalFrame {
	
	
	private JTable jtTabelaPesquisaMotocicleta;
	private JComboBox<Fabricante> jcbFabricante;
	private JComboBox<Modelo> jcbModelo;
	private JComboBox<String> jcbCategoria;
	private ArrayList<Motocicleta> listaMotocicleta;
	
	private ArrayList<Fabricante> listaFabricantes = new FabricanteController()
	.listaFabricantes();
	private ArrayList<Modelo> listaModelos = new ModeloController().listarModelo_Fab();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PesquisaMotocicletaUI frame = new PesquisaMotocicletaUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public void atualizarTabelaPesquisaMoto() throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new String[] {"Motocicleta","Categoria","Placa"});
        listaMotocicleta = MotocicletaController.obterInstancia().listaPesquisaMoto();
        for ( int i=0; i < listaMotocicleta.size(); i++){
            modelo.addRow(new Object[] { 
            		listaMotocicleta.get(i).getModelo().getNomeModelo(), 
            		listaMotocicleta.get(i).getCategoria(),
            		listaMotocicleta.get(i).getPlaca()
                });
        }  
        jtTabelaPesquisaMotocicleta.setModel(modelo);
        
    }
	
	/**
	 * Create the frame.
	 */
	public PesquisaMotocicletaUI() {
		setClosable(true);
		setTitle("Pesquisa de Motocicletas");
		setBounds(100, 100, 452, 377);
		
		JLabel lbFabricante = new JLabel("Fabricante:");
		
		JLabel lbModelo = new JLabel("Modelo:");
		
		jcbFabricante = new JComboBox<Fabricante>();
		DefaultComboBoxModel<Fabricante> modelFabricante = new DefaultComboBoxModel<Fabricante>();
		for (Fabricante fabricante : listaFabricantes) {
			modelFabricante.addElement(fabricante);
		}
		jcbFabricante.setModel(modelFabricante);

		JLabel jlbMotocicleta = new JLabel("Motocicleta:");

		jcbModelo = new JComboBox<Modelo>();
		DefaultComboBoxModel<Modelo> modelModelo = new DefaultComboBoxModel<Modelo>();
		for (Modelo modelo : listaModelos) {
			modelModelo.addElement(modelo);
		}
		jcbModelo.setModel(modelModelo);

		
		JButton btnPesquisaMotocicleta = new JButton("");
		btnPesquisaMotocicleta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		//		jcbCategoria.getSelectedItem().toString();
				
				
				try {
					atualizarTabelaPesquisaMoto();
				} catch (SQLException e) {
					// TODO Bloco catch gerado automaticamente
					e.printStackTrace();
				}
				
				
			}
		});
		btnPesquisaMotocicleta.setIcon(new ImageIcon(PesquisaMotocicletaUI.class.getResource("/img/Pesquisar.png")));
		
		jcbCategoria = new JComboBox();
		jcbCategoria.setModel(new DefaultComboBoxModel(new String[] {"Nova ", "Semi - Nova"}));
		
		JLabel lbCategoria = new JLabel("Categoria:");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					

					listaMotocicleta = MotocicletaController.obterInstancia().listaModelos();
		
					
					CadastraMotocicletaUI cadMotoEdit = new CadastraMotocicletaUI ( listaMotocicleta.get(jtTabelaPesquisaMotocicleta.getSelectedRow()));
					PrincipalUI.obterInstancia().getContentPane().add(cadMotoEdit);
					cadMotoEdit.setVisible(true);
			}catch ( ArrayIndexOutOfBoundsException a){
			   JOptionPane.showMessageDialog(null, "Selecione um cliente");	
			} catch (Exception e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(null, e2.getMessage());
			}
				}
					
				});
		btnEditar.setIcon(new ImageIcon(PesquisaMotocicletaUI.class.getResource("/img/EditMoto.png")));
		
		JButton btnDeletar = new JButton("\r\n");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// remover
	            try{
	                listaMotocicleta = MotocicletaController.obterInstancia().listaPesquisaMoto();
	                MotocicletaController.obterInstancia().
	                        remover(listaMotocicleta.get(jtTabelaPesquisaMotocicleta.getSelectedRow()).getCodMotocicleta());
	                atualizarTabelaPesquisaMoto();
	                
	            } catch ( ArrayIndexOutOfBoundsException a){
	                JOptionPane.showMessageDialog(null, "Selecione um cliente");
	            } catch (Exception e1){
	                JOptionPane.showMessageDialog(null, e1.getMessage());
	            } 
	            
	           	
			}
		});
		btnDeletar.setIcon(new ImageIcon(PesquisaMotocicletaUI.class.getResource("/img/RemoveMoto.png")));
		
		jtTabelaPesquisaMotocicleta = new JTable();
		jtTabelaPesquisaMotocicleta.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Motocicleta", "Categoria", "Placa"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		jtTabelaPesquisaMotocicleta.getColumnModel().getColumn(0).setResizable(false);
		jtTabelaPesquisaMotocicleta.getColumnModel().getColumn(0).setPreferredWidth(130);
		jtTabelaPesquisaMotocicleta.getColumnModel().getColumn(1).setResizable(false);
		jtTabelaPesquisaMotocicleta.getColumnModel().getColumn(1).setPreferredWidth(35);
		jtTabelaPesquisaMotocicleta.getColumnModel().getColumn(2).setResizable(false);
		jtTabelaPesquisaMotocicleta.getColumnModel().getColumn(2).setPreferredWidth(32);
		scrollPane.setViewportView(jtTabelaPesquisaMotocicleta);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnEditar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDeletar))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, 0, 0, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lbFabricante)
									.addGap(18)
									.addComponent(jcbFabricante, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
									.addGap(45)
									.addComponent(lbCategoria)
									.addGap(18)
									.addComponent(jcbCategoria, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lbModelo)
									.addGap(35)
									.addComponent(jcbModelo, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnPesquisaMotocicleta)))))
					.addGap(39))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lbFabricante))
						.addComponent(jcbFabricante, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lbCategoria))
						.addComponent(jcbCategoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(22)
							.addComponent(lbModelo))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(19)
							.addComponent(jcbModelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnPesquisaMotocicleta))
					.addGap(42)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnDeletar)
						.addComponent(btnEditar))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);

	}
}
