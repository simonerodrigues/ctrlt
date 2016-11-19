package br.com.ctrlt.json;

/**
 * Classe de modelo para resposta gen�rica em JSON de requisi��o JavaScript
 * 
 * @author Simone Santos Rodrigues
 * @version 1.0
 */

public class ResponseJsonWithId {
	private String status = null;
	private String result = null;
	private Long id = 0l;

	/**
	 * Getter da mensagem do status da opera��o realizada
	 * 
	 * @author Simone Santos Rodrigues
	 * @version 1.0
	 * @return status Mensagem do status da opera��o realizada
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Setter da mensagem do status da opera��o realizada
	 * 
	 * @author Simone Santos Rodrigues
	 * @version 1.0
	 * @param status Status da mensagem ("SUCCESS" ou "FAIL")
	 * @return void
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Getter da mensagem do resultado da opera��o realizada
	 * 
	 * @author Simone Santos Rodrigues
	 * @version 1.0
	 * @return result Mensagem do resultado da opera��o realizada
	 */
	public Object getResult() {
		return result;
	}

	/**
	 * Getter do ID do objeto incluso
	 * 
	 * @author Simone Santos Rodrigues
	 * @version 1.0
	 * @return result Mensagem do resultado da opera��o realizada
	 */
	public void setResult(String result) {
		this.result = result;
	}

	public Long getId() {
		return id;
	}

	/**
	 * Setter do ID do objeto incluso
	 * 
	 * @author Simone Santos Rodrigues
	 * @version 1.0
	 * @param result Mensagem da opera��o realizada
	 * @return void
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	

}