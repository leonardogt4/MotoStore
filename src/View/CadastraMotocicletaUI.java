package View;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.xml.ws.Service.Mode;

import Controller.ClienteController;
import Controller.FabricanteController;
import Controller.MotocicletaController;
import Dao.MotocicletaDao;
import Model.Cliente;
import Model.Fabricante;
import Model.Modelo;
import Model.Motocicleta;

import java.awt.Font;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;

public class CadastraMotocicletaUI extends JInternalFrame {
	
	private JTextField jtfPlaca;
	private JTextField jtfAno;
	private JTextField jtfKm;
	private JTable jtTabelaCadastroMotocicleta;
	private JComboBox<Fabricante> jcbFabricante;
	private JComboBox<Motocicleta> jcbModelo;
	private JComboBox<String> jcbCategoria;
	private JComboBox<String> jcbEstilo;
	private JComboBox<String>jcbCorPredominante;
	private JComboBox<String>jcbCombustivel;
	

	private ArrayList<Motocicleta> listaMotocicleta;
	private ArrayList<Fabricante> listaFabricantes = new FabricanteController().listaFabricantes();




private ArrayList<Motocicleta> listaModelos = new MotocicletaController().listaModelos();
	

	
	public void jtTabelaCadastroMotocicleta() throws SQLException{
		
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new String[] {"Fabricante","Modelo","Categoria"});
        listaMotocicleta = MotocicletaController.obterInstancia().listaCadastroMoto();
        for ( int i=0; i < listaMotocicleta.size(); i++){
            modelo.addRow(new Object[] { 
            		listaMotocicleta.get(i).getModelo().getFabricante().getNomeFabricante(),
            		listaMotocicleta.get(i).getModelo().getNomeModelo(),
            		listaMotocicleta.get(i).getCategoria()
                });
        }  
        jtTabelaCadastroMotocicleta.setModel(modelo);
        
    }
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastraMotocicletaUI frame = new CadastraMotocicletaUI(null);
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
	public CadastraMotocicletaUI(final Motocicleta moto) {
		setClosable(true);
		setTitle("Cadastro de Motocicleta");
		setBounds(100, 100, 619, 476);
		
		JLabel lblFabricante = new JLabel("Fabricante: ");
		
		jcbFabricante = new JComboBox<Fabricante>();
		DefaultComboBoxModel<Fabricante> modelFabricante = new DefaultComboBoxModel<Fabricante>();
		for (Fabricante fabricante : listaFabricantes) {
			modelFabricante.addElement(fabricante);
		}
		jcbFabricante.setModel(modelFabricante);
		
		JLabel lblModelo = new JLabel("Modelo: ");
		
		jcbModelo = new JComboBox<Motocicleta>();
		DefaultComboBoxModel<Motocicleta> modelMotocicleta = new DefaultComboBoxModel<Motocicleta>();
		for (Motocicleta motocicleta : listaModelos) {
			modelMotocicleta.addElement(motocicleta);
		}
		jcbModelo.setModel(modelMotocicleta);
		
		
		
		JLabel lblCategoria = new JLabel("Categoria: ");
		
		jcbCategoria = new JComboBox();
		jcbCategoria.setModel(new DefaultComboBoxModel(new String[] {"Nova ", "Semi - Nova"}));
		
		JLabel lblEstilo = new JLabel("Estilo: ");
		
		jcbEstilo = new JComboBox();
		jcbEstilo.setModel(new DefaultComboBoxModel(new String[] {"Street\t", "Naked\t", "Sport", "Trail\t", "Scooter\t", "City\t", "Custom\t", "Touring\t", "SuperMotard"}));
		
		JLabel lblCorPredominante = new JLabel("Cor Predominante: ");
		
		jcbCorPredominante = new JComboBox();
		jcbCorPredominante.setModel(new DefaultComboBoxModel(new String[] {"Vermelho", "Laranja", "Amarelo", "Verde", "Azul", "Violeta", "Neutro", "Branco", "Cinza", "Preta", "Outra"}));
		
		JLabel lblCombustivel = new JLabel("Combustivel:");
		
		jcbCombustivel = new JComboBox();
		jcbCombustivel.setModel(new DefaultComboBoxModel(new String[] {"ALCOOL", "GASOLINA", "OUTRO"}));
		
		JLabel lblPlaca = new JLabel("Placa:");
		
		jtfPlaca = new JTextField();
		jtfPlaca.setColumns(10);
		
		JLabel lblKm = new JLabel("KM:");
		jtfKm  = new JTextField();
		jtfKm.setColumns(10);
		
		JLabel lblAno = new JLabel("Ano: ");
		jtfAno  = new JTextField();
		jtfAno.setColumns(10);
		
		if (moto != null) {
			
			//Editar
		
			 
			jcbFabricante.setSelectedItem(moto.getFabricante());
			jcbEstilo.setSelectedItem(moto.getEstilo());
			jcbModelo.setSelectedItem(moto.getModelo());
			jcbCorPredominante.getSelectedItem().toString();
			jcbCategoria.getSelectedItem().toString();
			jcbCombustivel.getSelectedItem();
			jtfAno.setText(moto.getAno().toString());
			jtfPlaca.setText(moto.getPlaca());
			jtfKm.setText(moto.getKm().toString() );
			
		//	System.out.println(jcbFabricante.getSelectedItem().toString());
			
			MotocicletaController mm = new MotocicletaController();
			
			try {
				mm.editar(moto);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "erro ao inserir na view" );
				e.printStackTrace();
			}
			
			
		}
		
		
		
		
		JButton btnAdicionar = new JButton("");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
								
				//Inserir
				
				
				Modelo model = new Modelo();
				model.setNomeModelo(jcbModelo.getSelectedItem().toString());
				
				
				
				try {
					
					Motocicleta moto = new Motocicleta();
					
					moto.setAno(Integer.parseInt(jtfAno.getText() ) );
					moto.setKm(Double.parseDouble(jtfKm.getText().toString()));
					moto.setPlaca(jtfPlaca.getText().toString());
					moto.setFabricante((Fabricante)jcbFabricante.getSelectedItem());
					moto.setModelo(model);

					moto.setCategoria(jcbCategoria.getSelectedItem().toString());
					moto.setCombustivel(jcbCombustivel.getSelectedItem().toString());
					moto.setCor(jcbCorPredominante.getSelectedItem().toString());
					moto.setEstilo(jcbEstilo.getSelectedItem().toString());
					
				
					
					MotocicletaController motoController = new MotocicletaController();
					
					
					if(motoController.validaMotocicleta(moto)){
						
						String nome = jcbCategoria.getSelectedItem().toString();
						System.out.println("teste categoria = " + nome);
						
						DefaultTableModel dtm = (DefaultTableModel) jtTabelaCadastroMotocicleta.getModel();
						dtm.addRow( new Object[] {
								
								
								jcbFabricante.getSelectedItem(),
								jcbModelo.getSelectedItem(),
								
								jcbCategoria.getSelectedItem()
								
									
						});
						
						
					}
			
					
				}catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null,
		                    "Campo Obrigatórios não foram preenchidos!",
		                    "Erro de validação",
		                    JOptionPane.ERROR_MESSAGE);
					return;
				}catch (Exception e3) {
					JOptionPane.showMessageDialog(null, e3.getMessage(), "Erro ao inserir ClienteUI na Lista ", JOptionPane.ERROR_MESSAGE );
					}
				
				
				
			}
		});
				
				
				
				
		
		btnAdicionar.setHorizontalAlignment(SwingConstants.LEADING);
		btnAdicionar.setVerticalAlignment(SwingConstants.TOP);
		btnAdicionar.setIcon(new ImageIcon(CadastraMotocicletaUI.class.getResource("/img/AddMoto.png")));
		btnAdicionar.setForeground(new Color(0, 0, 0));
		btnAdicionar.setFont(new Font("Tahoma", Font.BOLD, 11));;
		btnAdicionar.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JTextArea textArea = new JTextArea();
		
		JButton btnConcluir = new JButton("");
		btnConcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				Fabricante fab = (Fabricante) jcbFabricante.getSelectedItem();
				
				Modelo model = new Modelo();
				model.setFabricante(fab);
				model.setNomeModelo( jcbModelo.getSelectedItem().toString() );
				
				Motocicleta moto = new Motocicleta();
				
				moto.setAno(Integer.parseInt(jtfAno.getText() ) );
				moto.setKm(Double.parseDouble(jtfKm.getText().toString()));
				moto.setPlaca(jtfPlaca.getText());
				moto.setFabricante((Fabricante)jcbFabricante.getSelectedItem());
				moto.setCategoria(jcbCategoria.getSelectedItem().toString());
				moto.setCombustivel(jcbCombustivel.getSelectedItem().toString());
				moto.setCor(jcbCorPredominante.getSelectedItem().toString());
				moto.setEstilo(jcbEstilo.getSelectedItem().toString());
				moto.setModelo(((Motocicleta)jcbModelo.getSelectedItem()).getModelo() );
				
				MotocicletaController motoControl = new MotocicletaController();
				
				
				//Inserir
				
				try {
					
					if(motoControl.validaMotocicleta(moto)){
						motoControl.inserir(moto);;
						JOptionPane.showMessageDialog(null, "Motocicleta inserido com sucesso.");
					}
					
				} catch (Exception e3) {
					
					e3.printStackTrace();
				}
				
			}
				
			
		});
		btnConcluir.setIcon(new ImageIcon(CadastraMotocicletaUI.class.getResource("/img/ConcluirMoto.png")));
		
		JScrollPane jscrollPane = new JScrollPane();
		
		JButton btnRemover = new JButton("");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel dtm = (DefaultTableModel) jtTabelaCadastroMotocicleta.getModel();
				
				int row = jtTabelaCadastroMotocicleta.getSelectedRow();
					
				if ( row > -1 ) {
					dtm.removeRow( row );
				} else {
					JOptionPane.showMessageDialog( null , "Voce deve selecionar ao menos uma linha");
				}
				
			}
				
		});
		btnRemover.setIcon(new ImageIcon(CadastraMotocicletaUI.class.getResource("/img/RemoveMoto.png")));
		
		
		
		jtTabelaCadastroMotocicleta = new JTable();
		jtTabelaCadastroMotocicleta.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {"Fabricante", "Modelo", "Categoria"}) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		jtTabelaCadastroMotocicleta.getColumnModel().getColumn(0).setResizable(false);
		jtTabelaCadastroMotocicleta.getColumnModel().getColumn(0).setPreferredWidth(68);
		jtTabelaCadastroMotocicleta.getColumnModel().getColumn(1).setResizable(false);
		jtTabelaCadastroMotocicleta.getColumnModel().getColumn(1).setPreferredWidth(130);
		jtTabelaCadastroMotocicleta.getColumnModel().getColumn(2).setResizable(false);
		jtTabelaCadastroMotocicleta.getColumnModel().getColumn(2).setPreferredWidth(40);
		jscrollPane.setViewportView(jtTabelaCadastroMotocicleta);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(51)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblFabricante)
									.addGap(18)
									.addComponent(jcbFabricante, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
									.addGap(136)
									.addComponent(jcbEstilo, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblModelo)
									.addGap(35)
									.addComponent(jcbModelo, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
									.addGap(157)
									.addComponent(jcbCorPredominante, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblCategoria, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(jcbCategoria, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
									.addGap(143)
									.addComponent(jcbCombustivel, 0, 109, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(12)
											.addComponent(jscrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblPlaca)
											.addGap(45)
											.addComponent(jtfPlaca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
											.addComponent(lblAno)
											.addGap(4)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblCombustivel)
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(jtfAno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
													.addGap(41)
													.addComponent(lblKm)
													.addGap(1)
													.addComponent(jtfKm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addComponent(lblCorPredominante)
												.addComponent(lblEstilo)))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(btnAdicionar)
											.addGap(6)
											.addComponent(btnRemover)
											.addGap(6)
											.addComponent(btnConcluir)))
									.addPreferredGap(ComponentPlacement.RELATED)))))
					.addGap(96))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFabricante)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(jcbFabricante, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblEstilo))
						.addComponent(jcbEstilo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(lblModelo))
						.addComponent(jcbModelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(jcbCorPredominante, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCorPredominante))))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(28)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(jcbCategoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblCombustivel))
								.addComponent(jcbCombustivel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(32)
							.addComponent(lblCategoria)))
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(jtfPlaca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblPlaca))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblAno))
						.addComponent(jtfAno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblKm))
						.addComponent(jtfKm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addComponent(jscrollPane, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAdicionar)
						.addComponent(btnRemover)
						.addComponent(btnConcluir))
					.addGap(45)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		getContentPane().setLayout(groupLayout);

	}
}
