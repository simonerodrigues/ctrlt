package br.com.ctrlt.json;

/**
 * Classe de modelo para resposta genérica em JSON de requisição JavaScript
 * 
 * @author Simone Santos Rodrigues
 * @version 1.0
 * 
 * Este sistema foi desenvolvido sob a licença GPL/GNU versão 3, onde qualquer pessoa poderá copiar e distribuir cópias sem alterações deste documento de licença.
 * Consulte mais informações sobre essa licença no arquivo gpl.txt que contêm na raiz do projeto.
 * 
 */

public class ResponseJsonWithId {
	private String status = null;
	private String result = null;
	private Long id = 0l;

	/**
	 * Getter da mensagem do status da operação realizada
	 * 
	 * @author Simone Santos Rodrigues
	 * @version 1.0
	 * @return status Mensagem do status da operação realizada
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Setter da mensagem do status da operação realizada
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
	 * Getter da mensagem do resultado da operação realizada
	 * 
	 * @author Simone Santos Rodrigues
	 * @version 1.0
	 * @return result Mensagem do resultado da operação realizada
	 */
	public Object getResult() {
		return result;
	}

	/**
	 * Getter do ID do objeto incluso
	 * 
	 * @author Simone Santos Rodrigues
	 * @version 1.0
	 * @return result Mensagem do resultado da operação realizada
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
	 * @param result Mensagem da operação realizada
	 * @return void
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	

}