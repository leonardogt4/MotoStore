package View;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import Controller.ClienteController;
import Controller.FabricanteController;
import Controller.ModeloController;
import Dao.FabricanteDao;
import Dao.SaidaDao;
import Model.Cliente;
import Model.Fabricante;
import Model.Modelo;
import Model.Saida;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class PesquisaFornecedorUI extends JInternalFrame {
	private JTextField jtfFornecedorMotocicleta;
	private JTable jtTabelaPesquisaFornecedor;
	
	private ArrayList<Modelo> listaFornecedor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PesquisaFornecedorUI frame = new PesquisaFornecedorUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Cria tabela pra listar todos os fornecedores
	public void atualizarTabelaFornecedor() throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new String[] {"Fornecedor","Modelo"});
        this.listaFornecedor =  ModeloController.obterInstancia().listaTodos();
        for ( int i=0; i < this.listaFornecedor.size(); i++){
            modelo.addRow(new Object[] { 
            	this.listaFornecedor.get(i).getFabricante().getNomeFabricante().toString(), 
                this.listaFornecedor.get(i).getNomeModelo(),
                });
        }  
        jtTabelaPesquisaFornecedor.setModel(modelo);
        
    }
	

	//Cria tabela pra listar todos os fornecedores
	public void atualizarTabelaFornecedorPorCampos(Modelo model) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new String[] {"Fornecedor","Modelo"});
        this.listaFornecedor =  ModeloController.obterInstancia().listaCampos(model);
        for ( int i=0; i < this.listaFornecedor.size(); i++){
            modelo.addRow(new Object[] { 
            	this.listaFornecedor.get(i).getFabricante().getNomeFabricante(), 
                this.listaFornecedor.get(i).getNomeModelo(),
                });
        }  
        jtTabelaPesquisaFornecedor.setModel(modelo);
        
    }

	/**
	 * Create the frame.
	 */
	public PesquisaFornecedorUI() {
		setTitle("Pesquisa Fornecedor");
		setClosable(true);
		
		setBounds(100, 100, 398, 345);
		
		JLabel lblNomeDoFornecedor = new JLabel("Fabricante:");
		
		jtfFornecedorMotocicleta = new JTextField();
		jtfFornecedorMotocicleta.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnPesquisaFornecedor = new JButton("");
		btnPesquisaFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Pesquisar
				if( jtfFornecedorMotocicleta.getText().equals("")){
					try {
						atualizarTabelaFornecedor();
					} catch (Exception e1) {
						
						e1.printStackTrace();
					}
				}else{
					
					Fabricante fab = new Fabricante();
					fab.setNomeFabricante( jtfFornecedorMotocicleta.getText().trim() );
					
					Modelo model = new Modelo();
					model.setFabricante(fab);
					
					try {
						FabricanteDao.obterInstancia().consultarNomeModelo(model);
						atualizarTabelaFornecedorPorCampos(model);
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Erro pesquisa View");
						e2.printStackTrace();
					}
					
				}
				
			}
		});
		btnPesquisaFornecedor.setIcon(new ImageIcon(PesquisaFornecedorUI.class.getResource("/img/PesquisaFornecedor.png")));
		
		JButton btnEditaFornecedor = new JButton("");
		btnEditaFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					

					listaFornecedor = FabricanteDao.obterInstancia().listaTodosModelo();
		
					
					CadastraFornecedorUI cadFornEdit = new  CadastraFornecedorUI( listaFornecedor.get(jtTabelaPesquisaFornecedor.getSelectedRow()));	
					PrincipalUI.obterInstancia().getContentPane().add(cadFornEdit);
					cadFornEdit.setVisible(true);
					
			}catch ( ArrayIndexOutOfBoundsException a){
			   JOptionPane.showMessageDialog(null, "Selecione um fornecedor");	
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage());
			}
			}
		});
		btnEditaFornecedor.setIcon(new ImageIcon(PesquisaFornecedorUI.class.getResource("/img/EditFornecedor.png")));
		
		JButton btnRemoveFornecedor = new JButton("");
		btnRemoveFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// remover
	            try{
	                listaFornecedor = ModeloController.obterInstancia().listaTodos();
	                ModeloController.obterInstancia().
	                        remover(listaFornecedor.get(jtTabelaPesquisaFornecedor.getSelectedRow()).getFabricante().getCodFabricante());
	                atualizarTabelaFornecedor();
	                JOptionPane.showMessageDialog(null, "Fornecedor excluído com sucesso!");
	                
	            } catch ( ArrayIndexOutOfBoundsException a){
	                JOptionPane.showMessageDialog(null, "Selecione um cliente");
	            } catch (Exception e1){
	                JOptionPane.showMessageDialog(null, e1.getMessage());
	            } 
	            
	            }
			
		});
		btnRemoveFornecedor.setIcon(new ImageIcon(PesquisaFornecedorUI.class.getResource("/img/RemoveFornecedor.png")));
		
		jtTabelaPesquisaFornecedor = new JTable();
		jtTabelaPesquisaFornecedor.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome do Fornecedor", "Nome do Modelo"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		jtTabelaPesquisaFornecedor.getColumnModel().getColumn(0).setResizable(false);
		jtTabelaPesquisaFornecedor.getColumnModel().getColumn(0).setPreferredWidth(50);
		jtTabelaPesquisaFornecedor.getColumnModel().getColumn(1).setResizable(false);
		jtTabelaPesquisaFornecedor.getColumnModel().getColumn(1).setPreferredWidth(100);
		scrollPane.setViewportView(jtTabelaPesquisaFornecedor);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnPesquisaFornecedor)
							.addGap(6)
							.addComponent(btnEditaFornecedor)
							.addGap(6)
							.addComponent(btnRemoveFornecedor))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(37, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNomeDoFornecedor)
					.addGap(10)
					.addComponent(jtfFornecedorMotocicleta)
					.addGap(37))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNomeDoFornecedor))
						.addComponent(jtfFornecedorMotocicleta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(38)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnPesquisaFornecedor)
						.addComponent(btnEditaFornecedor)
						.addComponent(btnRemoveFornecedor))
					.addGap(65))
		);
		getContentPane().setLayout(groupLayout);

	}
}
