package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

//colocar focus e move to front


/**
 * @author Caio Pereira
 *
 */
public class PrincipalUI extends JFrame {

	private JPanel contentPane;
	private static PrincipalUI instancia;
	
	//SINGLETON
	public static PrincipalUI obterInstancia(){
		if ( instancia == null ){
			instancia = new PrincipalUI();
		}
		return instancia;
	}
	/**
	 * Launch the application.
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalUI frame = obterInstancia();
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
	public PrincipalUI() {
		
		setTitle("MotoStore ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 450, 300);
		//this.setState(JFrame.MAXIMIZED_BOTH);
		this.setSize( Toolkit.getDefaultToolkit().getScreenSize() );
		this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		
		
		JDesktopPane planoFundo = new JDesktopPane();
		planoFundo.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
		
		setContentPane( planoFundo );
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu jmCadastrarMotocicleta = new JMenu("Motocicleta");
		menuBar.add(jmCadastrarMotocicleta);
		
		JMenuItem jmiCadastrarMotocicleta = new JMenuItem("Cadastrar");
		jmiCadastrarMotocicleta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CadastraMotocicletaUI cadMoto = new CadastraMotocicletaUI(null);
				getContentPane().add(cadMoto);
				cadMoto.setVisible(true);
				
			}
			
		});
		jmCadastrarMotocicleta.add(jmiCadastrarMotocicleta);
		
		JMenuItem jmiPesquisarMotocicleta = new JMenuItem("Pesquisar");
		jmiPesquisarMotocicleta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PesquisaMotocicletaUI pesMoto = new PesquisaMotocicletaUI();
				getContentPane().add(pesMoto);
				pesMoto.setVisible(true);
			}
		});
		jmCadastrarMotocicleta.add(jmiPesquisarMotocicleta);
		
		JMenu jmCliente = new JMenu("Cliente");
		menuBar.add(jmCliente);
		
		JMenuItem jmiCadastrarCliente = new JMenuItem("Cadastrar");
		jmiCadastrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CadastraClienteUI cadCli = new CadastraClienteUI(null);
				cadCli.setFocusable(true);
				//Mover a tela por cima de outra
				cadCli.moveToFront();
				cadCli.requestFocus();
				getContentPane().add(cadCli, 0);
				try {
					cadCli.setSelected(true);
				} catch (PropertyVetoException e1) {
					
					e1.printStackTrace();
				}
				cadCli.setVisible(true);
				
			}
		});
		jmCliente.add(jmiCadastrarCliente);
		
		JMenuItem jmiPesquisarCliente = new JMenuItem("Pesquisar");
		jmiPesquisarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				PesquisaClienteUI pesCli = new PesquisaClienteUI();
				pesCli.setFocusable(true);
				//Mover a tela por cima de outra
				pesCli.moveToFront();
				pesCli.requestFocus();
				getContentPane().add(pesCli, 0);
				try {
					pesCli.setSelected(true);
				} catch (Exception e3) {
					e3.printStackTrace();
				}
				pesCli.setVisible(true);
			}
		});
		jmCliente.add(jmiPesquisarCliente);
		
		JMenu jmVenda = new JMenu("Venda");
		menuBar.add(jmVenda);
		
		JMenuItem jmiCadastrarVenda = new JMenuItem("Cadastrar");
		jmiCadastrarVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CadastraVendaUI cadVenda = new CadastraVendaUI();
				getContentPane().add(cadVenda);
				cadVenda.setVisible(true);
				
			}
		});
		jmVenda.add(jmiCadastrarVenda);
		
		JMenuItem jmiPesquisarVenda = new JMenuItem("Pesquisar");
		jmiPesquisarVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PesquisaVendaUI pesVenda = new PesquisaVendaUI();
				getContentPane().add(pesVenda);
				pesVenda.setVisible(true);
			}
		});
		jmVenda.add(jmiPesquisarVenda);
		
		JMenu mnFornecedor = new JMenu("Fornecedor");
		menuBar.add(mnFornecedor);
		
		JMenuItem jmiCadastrar = new JMenuItem("Cadastrar");
		jmiCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CadastraFornecedorUI cadForn = new CadastraFornecedorUI(null);
				getContentPane().add(cadForn);
				cadForn.setVisible(true);
			}
		});
		mnFornecedor.add(jmiCadastrar);
		
		JMenuItem jmiPesquisarFornecedor = new JMenuItem("Pesquisar");
		jmiPesquisarFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				PesquisaFornecedorUI pesForn = new PesquisaFornecedorUI();
				getContentPane().add(pesForn);
				pesForn.setVisible(true);
			}
				
			
		});
		mnFornecedor.add(jmiPesquisarFornecedor);
		
		JMenu mnSaida = new JMenu("Sa\u00EDda");
		menuBar.add(mnSaida);
		
		JMenuItem jmiCadastrarSaida = new JMenuItem("Cadastrar");
		jmiCadastrarSaida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				CadastraSaidaUI cadSaida = new CadastraSaidaUI();
				getContentPane().add(cadSaida);
				cadSaida.setVisible(true);
				
				
				
			}
		});
		mnSaida.add(jmiCadastrarSaida);
		
		JMenuItem jmiPesquisar = new JMenuItem("Pesquisar");
		jmiPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				PesquisaSaidaUI pesSaida = new PesquisaSaidaUI();
				getContentPane().add(pesSaida);
				pesSaida.setVisible(true);
			}
		});
		mnSaida.add(jmiPesquisar);
		
		JMenu jmRelatorio = new JMenu("Relatorio");
		menuBar.add(jmRelatorio);
		
		JMenuItem jmiRelatorioGeral = new JMenuItem("Relatorio Geral");
		jmiRelatorioGeral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RelatorioUI relatorio = new RelatorioUI();
				getContentPane().add(relatorio);
				relatorio.setVisible(true);
			}
		});
		jmRelatorio.add(jmiRelatorioGeral);
		
		JMenu jmSair = new JMenu("Sair");
		
		menuBar.add(jmSair);
		
		JMenuItem jmiFechar = new JMenuItem("Fechar");
		jmiFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
				
			}
		});
		jmSair.add(jmiFechar);
		
		JLabel lbPrincipal = new JLabel("");
		lbPrincipal.setVerticalAlignment(SwingConstants.TOP);
		lbPrincipal.setIcon(new ImageIcon(PrincipalUI.class.getResource("/img/Principal.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(lbPrincipal, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(lbPrincipal, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
		);
		getContentPane().setLayout(groupLayout);
	}
}
