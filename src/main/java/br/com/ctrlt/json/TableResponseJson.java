package br.com.ctrlt.json;

/**
 * Classe de modelo para resposta de dados para DataTable em JSON de requisi��o
 * JavaScript
 * 
 * @author Simone Santos Rodrigues
 * @version 1.0
 */

public class TableResponseJson {
	private int draw;
	private int recordsTotal;
	private int recordsFiltered;
	private Object data;

	/**
	 * Getter do n�mero de compara��o da DataTable
	 * 
	 * @author Simone Santos Rodrigues
	 * @version 1.0
	 * @return draw N�mero de compara��o fornecido pela DataTable
	 */
	public int getDraw() {
		return draw;
	}

	/**
	 * Setter do n�mero de compara��o da DataTable
	 * 
	 * @author Simone Santos Rodrigues
	 * @version 1.0
	 * @param draw N�mero de compara��o fornecido pela DataTable
	 * @return void
	 */
	public void setDraw(int draw) {
		this.draw = draw;
	}

	/**
	 * Getter da quantidade total de registros
	 * 
	 * @author Simone Santos Rodrigues
	 * @version 1.0
	 * @return recordsTotal N�mero total de resgistros da DataTable
	 */
	public int getRecordsTotal() {
		return recordsTotal;
	}

	/**
	 * Setter do n�mero de registros na DataTable
	 * 
	 * @author Simone Santos Rodrigues
	 * @version 1.0
	 * @param recordsTotal N�mero de registros na DataTable
	 * @return void
	 */
	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	/**
	 * Getter da quantidade total de registros filtrados
	 * 
	 * @author Simone Santos Rodrigues
	 * @version 1.0
	 * @return recordsFiltered N�mero total de resgistros filtrados da DataTable
	 */
	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	/**
	 * Setter do n�mero de registros filtrados na DataTable
	 * 
	 * @author Simone Santos Rodrigues
	 * @version 1.0
	 * @param recordsFiltered N�mero de registros filtrados na DataTable
	 * @return void
	 */
	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	/**
	 * Getter da lista com os dados da DataTable
	 * 
	 * @author Simone Santos Rodrigues
	 * @version 1.0
	 * @return data Lista com os dados da DataTable
	 */
	public Object getData() {
		return data;
	}

	/**
	 * Setter da lista com os dados da DataTable
	 * 
	 * @author Simone Santos Rodrigues
	 * @version 1.0
	 * @param data Lista com os dados da DataTable
	 * @return void
	 */
	public void setData(Object data) {
		this.data = data;
	}

}