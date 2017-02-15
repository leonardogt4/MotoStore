package Utils;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Model.Venda;


public class ItemVendaTableModel extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int COL_NOMEVENDEDOR = 0;
	private static final int COL_NOMECLIENTE = 1;
	private static final int COL_VALORVENDA = 2;
	

	private List<Venda> valores;       

	//Esse é um construtor, que recebe a nossa lista de clientes
	public ItemVendaTableModel(List<Venda> valores) {
		this.valores = new ArrayList<Venda>(valores);
	}

	public int getRowCount() {
		//Quantas linhas tem sua tabela? Uma para cada item da lista.
		return valores.size();
	}

	public int getColumnCount() {
		//Quantas colunas tem a tabela? Nesse exemplo, só 2.
		return 2;
	}

	public String getColumnName(int column) {
		//Qual é o nome das nossas colunas?
		if (column == COL_NOMEVENDEDOR) return "Nome Vendedor";
		if (column == COL_NOMECLIENTE) return "Nome Cliente";
		if (column == COL_VALORVENDA) return "Valor Venda";
	
		return ""; //Nunca deve ocorrer
	}

	public Object getValueAt(int row, int column) {
		//Precisamos retornar o valor da coluna column e da linha row.
		Venda ItemVenda = valores.get(row);
		if (column == COL_NOMEVENDEDOR) 
			return ItemVenda.getVendedor().getNomeVendedor();
		else 
			if (column == COL_NOMECLIENTE) 
				return ItemVenda.getCliente().getNomeCliente();
			else 
				if (column == COL_VALORVENDA) 
					return ItemVenda.getValor();
				
		return ""; //Nunca deve ocorrer
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Venda ItemVenda = valores.get(rowIndex);
		//Vamos alterar o valor da coluna columnIndex na linha rowIndex com o valor aValue passado no parâmetro.
		//Note que vc poderia alterar 2 campos ao invés de um só.
		if (columnIndex == COL_NOMEVENDEDOR) 
			ItemVenda.getVendedor().setNomeVendedor(aValue.toString());
		else 
			if (columnIndex == COL_NOMECLIENTE) 
				ItemVenda.getCliente().setNomeCliente(aValue.toString());
			else 
				if(columnIndex == COL_VALORVENDA)
					ItemVenda.setValor(Double.parseDouble(aValue.toString()));
			
	}

	public Class<?> getColumnClass(int columnIndex) {
		//Qual a classe das nossas colunas? Como estamos exibindo texto, é string.
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		//Indicamos se a célula da rowIndex e da columnIndex é editável. Nossa tabela toda é.
		return true;
	}
	//Já que esse tableModel é de clientes, vamos fazer um get que retorne um objeto cliente inteiro.
	//Isso elimina a necessidade de chamar o getValueAt() nas telas. 
	public Venda get(int row) {
		return valores.get(row);
	}

}
