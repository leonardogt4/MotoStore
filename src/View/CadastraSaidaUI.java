package View;
import java.awt.EventQueue;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import Controller.ClienteController;
import Controller.MotocicletaController;
import Controller.SaidaController;
import Model.Cliente;
import Model.Motocicleta;
import Model.Saida;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastraSaidaUI extends JInternalFrame {
 
	private JComboBox<Cliente> jcbCliente;
	private JComboBox<Motocicleta> jcbMotocicleta;
	private JDateChooser dateChooserDataSaida;
	
	private ArrayList<Cliente> listaClientes = new ClienteController().listaClientesVenda();
	private ArrayList<Motocicleta> listaModelos = new MotocicletaController().listaModelos();
	//private ArrayList<Saida> listaSaidas = new SaidaController().listas();
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastraSaidaUI frame = new CadastraSaidaUI();
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
	public CadastraSaidaUI() {
		setClosable(true);
		setTitle("Cadastra Sa\u00EDda");
		setBounds(100, 100, 342, 300);
		
		JLabel lblCliente = new JLabel("Cliente:");
		
		jcbCliente = new JComboBox<Cliente>();
		DefaultComboBoxModel<Cliente> modelCliente = new DefaultComboBoxModel<Cliente>();
		for (Cliente cliente : listaClientes) {
			modelCliente.addElement(cliente);
		}
		jcbCliente.setModel(modelCliente);
		
		JLabel lblModeloDaMotocicleta = new JLabel("Modelo da Motocicleta:");
		
		jcbMotocicleta = new JComboBox<Motocicleta>();
		DefaultComboBoxModel<Motocicleta> modelMotocicleta = new DefaultComboBoxModel<Motocicleta>();
		for (Motocicleta motocicleta : listaModelos) {
			modelMotocicleta.addElement(motocicleta);
		}
		jcbMotocicleta.setModel(modelMotocicleta);
		
		JLabel lblDataDaSada = new JLabel("Data da Sa\u00EDda:");
		
		dateChooserDataSaida = new JDateChooser();
		
		getContentPane().add(dateChooserDataSaida);
		
		JButton btnCadastraSaida = new JButton("");
		btnCadastraSaida.setToolTipText("Concluir");
		btnCadastraSaida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Insercao no BD
				Saida saida = new Saida();
				
				saida.setCliente((Cliente)jcbCliente.getSelectedItem());
				saida.setMotocicleta((Motocicleta)jcbMotocicleta.getSelectedItem());
				saida.setDataSaida(dateChooserDataSaida.getDate());
				
				
				SaidaController saidaCont = new SaidaController();
				
				try {
					
					if(saidaCont.validaCamposSaida(saida)){
						saidaCont.inserir(saida);
						JOptionPane.showMessageDialog(null, "Saida inserido com sucesso.");
					}
					
				} catch (Exception e3) {
					JOptionPane.showMessageDialog(null, e3.getMessage(), "Erro ao inserir Saida View", JOptionPane.ERROR_MESSAGE );
				}
				
			}
			
			
		
	});
		btnCadastraSaida.setIcon(new ImageIcon(CadastraSaidaUI.class.getResource("/img/Concluir.png")));
		
		JButton btnRemoveSaida = new JButton("");
		btnRemoveSaida.setToolTipText("Fechar");
		btnRemoveSaida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnRemoveSaida.setIcon(new ImageIcon(CadastraSaidaUI.class.getResource("/img/Fechar.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnCadastraSaida)
							.addGap(18)
							.addComponent(btnRemoveSaida, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCliente)
							.addGap(18)
							.addComponent(jcbCliente, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblModeloDaMotocicleta)
								.addComponent(lblDataDaSada))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(jcbMotocicleta, 0, 169, Short.MAX_VALUE)
								.addComponent(dateChooserDataSaida, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(37))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCliente)
						.addComponent(jcbCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblModeloDaMotocicleta)
						.addComponent(jcbMotocicleta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDataDaSada)
						.addComponent(dateChooserDataSaida, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(54)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnRemoveSaida, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnCadastraSaida, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);

	}
}
