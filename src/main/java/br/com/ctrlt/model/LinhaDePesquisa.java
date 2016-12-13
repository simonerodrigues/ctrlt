package br.com.ctrlt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Classe de modelo da linha de pesquisa
 * 
 * @author Simone Santos Rodrigues
 * @version 1.0
 * 
 * Este sistema foi desenvolvido sob a licença GPL/GNU versão 3, onde qualquer pessoa poderá copiar e distribuir cópias sem alterações deste documento de licença.
 * Consulte mais informações sobre essa licença no arquivo gpl.txt que contêm na raiz do projeto.
 * 
 */

@Entity
@Table(name = "linhaDePesquisa")
public class LinhaDePesquisa {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 100, nullable = false)
	@NotBlank(message = "{linhaDePesquisa.nome.vazio}")
	@Size.List({
		@Size(min = 2, message = "{linhaDePesquisa.nome.min}"),
		@Size(max = 100, message = "{linhaDePesquisa.nome.max}")
	})
	private String nome;
	
	@Column(nullable = false)
	private boolean ativo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
