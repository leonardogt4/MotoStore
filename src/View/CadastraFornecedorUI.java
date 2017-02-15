package View;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;

import Controller.ClienteController;
import Controller.FabricanteController;
import Controller.FabricanteException;
import Controller.ModeloController;
import Controller.MotocicletaController;
import Dao.FabricanteDao;
import Model.Fabricante;
import Model.Modelo;

public class CadastraFornecedorUI extends JInternalFrame {
	private JTextField jtfNomeFabricante;
	private JTextField jtfModeloMotocicleta;
	private JTable jtTabelaCadastraFornecedor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastraFornecedorUI frame = new CadastraFornecedorUI(null);
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
	public CadastraFornecedorUI( final Modelo model) {
		setClosable(true);
		setTitle("CADASTRAR FORNECEDOR");
		setBounds(100, 100, 379, 359);
		
		JLabel lblNomeFrabricante = new JLabel("Nome Frabricante:");
		
		JLabel lblModeloDaMotocicleta = new JLabel("Modelo da Motocicleta:");
		
		jtfNomeFabricante = new JTextField();
		jtfNomeFabricante.setColumns(10);
		
		jtfModeloMotocicleta = new JTextField();
		jtfModeloMotocicleta.setColumns(10);
		
		
		
		
		if(model != null){
			//Editar
			jtfNomeFabricante.setText( model.getFabricante().getNomeFabricante() );
			jtfModeloMotocicleta.setText( model.getNomeModelo() );
            
            ModeloController mc = new ModeloController();
            
            try {
            	mc.editarFornecedor(model);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}  
            
		}
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton jbnAdicionaFornecedor = new JButton("");
		jbnAdicionaFornecedor.setIcon(new ImageIcon(CadastraFornecedorUI.class.getResource("/img/AddMoto.png")));
		jbnAdicionaFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel dtm = (DefaultTableModel) jtTabelaCadastraFornecedor.getModel();
				dtm.addRow( new String[] {
						jtfNomeFabricante.getText(),
						jtfModeloMotocicleta.getText()
						
				});
				
				if(dtm.getRowCount() > 1){
					
					JOptionPane.showMessageDialog(null, "Permitido apenas uma linha", "Aviso!",JOptionPane.WARNING_MESSAGE);
					int row = jtTabelaCadastraFornecedor.getRowCount();
					dtm.removeRow( row -1 );
					return;
				}
			}
		});
		
		JButton btnRemoveFornecedor = new JButton("");
		btnRemoveFornecedor.setIcon(new ImageIcon(CadastraFornecedorUI.class.getResource("/img/RemoveMoto.png")));
		btnRemoveFornecedor.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dtm = (DefaultTableModel) jtTabelaCadastraFornecedor.getModel();
				
				int row = jtTabelaCadastraFornecedor.getSelectedRow();
				
				if ( row > -1 ) {
					dtm.removeRow( row );
				} else {
					JOptionPane.showMessageDialog( null , "Voce deve selecionar ao menos uma linha");
				}
				
			}
		});
		
		JButton btnConcluir = new JButton("");
		btnConcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModeloController modelControl = new ModeloController();
				//FabricanteController fabController = new FabricanteController();
				
				if (model == null ) {
					
					Fabricante fab = new Fabricante();
					fab.setNomeFabricante(jtfNomeFabricante.getText());
					//fab.getModelo().setNomeModelo(jtfModeloMotocicleta.getText());
					
					Modelo mod = new Modelo();
					
					mod.setFabricante(fab);
					mod.setNomeModelo( jtfModeloMotocicleta.getText() );
					
					FabricanteDao dao = new FabricanteDao();
					
					try {
						
						if(dao.fabricanteExiste(mod.getFabricante())){
							//"O Fornecedor " + mod.getFabricante().getNomeFabricante() + " já existe! Deseja cadastrar outro fabricante com o mesmo nome ?";
							int resposta = JOptionPane.showConfirmDialog(null, "O Fornecedor " + mod.getFabricante().getNomeFabricante() + " já existe! Deseja cadastrar outro fabricante com o mesmo nome ?"
									, "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
							if(resposta == JOptionPane.NO_OPTION){
							 return;
							}else{
							 modelControl.inserir(mod);
							 JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso");
						 }
							//"O Fornecedor " + mod.getFabricante().getNomeFabricante() + " já existe! Deseja cadastrar outro fabricante com o mesmo nome ?"
						}else{
							modelControl.inserir(mod);
							JOptionPane.showMessageDialog(null, "Novo Fornecedor cadastrado com sucesso");
							
						}
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null,
			                    "Erro ao cadastrar fornecedor View!",
			                    "Erro de validação",
			                    JOptionPane.ERROR_MESSAGE);
						e2.printStackTrace();
						
					}
					
				}else {
					
					//Editar	
					jtfNomeFabricante.setText( model.getFabricante().getNomeFabricante() );
					jtfModeloMotocicleta.setText( model.getNomeModelo() );
		            
		            ModeloController mc = new ModeloController();
		            
		            try {
		            	mc.editarFornecedor(model);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}  
					
				}
				
			}
		});
		btnConcluir.setIcon(new ImageIcon(CadastraFornecedorUI.class.getResource("/img/ConcluirMoto.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(lblNomeFrabricante)
							.addGap(26)
							.addComponent(jtfNomeFabricante, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(lblModeloDaMotocicleta)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(jtfModeloMotocicleta, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(jbnAdicionaFornecedor)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnRemoveFornecedor)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnConcluir))
								.addComponent(scrollPane, 0, 0, Short.MAX_VALUE))
							.addGap(15)))
					.addGap(17))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNomeFrabricante)
						.addComponent(jtfNomeFabricante, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblModeloDaMotocicleta)
						.addComponent(jtfModeloMotocicleta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnConcluir)
						.addComponent(btnRemoveFornecedor)
						.addComponent(jbnAdicionaFornecedor))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		
		jtTabelaCadastraFornecedor = new JTable();
		jtTabelaCadastraFornecedor.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Fabricante", "Modelo"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		jtTabelaCadastraFornecedor.getColumnModel().getColumn(0).setResizable(false);
		jtTabelaCadastraFornecedor.getColumnModel().getColumn(0).setPreferredWidth(50);
		jtTabelaCadastraFornecedor.getColumnModel().getColumn(1).setResizable(false);
		jtTabelaCadastraFornecedor.getColumnModel().getColumn(1).setPreferredWidth(120);
		scrollPane.setViewportView(jtTabelaCadastraFornecedor);
		getContentPane().setLayout(groupLayout);

	}
}
