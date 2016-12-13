package br.com.ctrlt.model;

import java.math.BigInteger;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Classe de modelo do Anexo
 * 
 * @author Simone Santos Rodrigues
 * @version 1.0
 * 
 * Este sistema foi desenvolvido sob a licença GPL/GNU versão 3, onde qualquer pessoa poderá copiar e distribuir cópias sem alterações deste documento de licença.
 * Consulte mais informações sobre essa licença no arquivo gpl.txt que contêm na raiz do projeto.
 * 
 */

@Entity
@Table(name = "anexo")
public class Anexo {
	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 100, nullable = false)
	@NotBlank(message = "{anexo.nome.vazio}")
	private String nome;
	
	@Column(nullable = false)
	private String caminho;
	
	@Column(length = 20, nullable = false)
	private String extensao;
	
	@Column(nullable = false)
	private BigInteger tamanho;
	
	@Temporal(TemporalType.TIMESTAMP)	// Data e Hora
	@DateTimeFormat(pattern = "dd/MM/yyy HH:mm")
	@Column(nullable = false)
	private Calendar dataUpload;
	
	@Column(nullable = false)
	private long numeroDownloads;
	
	@Column(nullable = false)
	private boolean visivel;
	
	@ManyToOne
	@JoinColumn(name="id_trabalhoDeConclusao")
	@JsonBackReference
	private TrabalhoDeConclusao trabalhoDeConclusao;
	
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

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public BigInteger getTamanho() {
		return tamanho;
	}

	public void setTamanho(BigInteger tamanho) {
		this.tamanho = tamanho;
	}

	public Calendar getDataUpload() {
		return dataUpload;
	}

	public void setDataUpload(Calendar dataUpload) {
		this.dataUpload = dataUpload;
	}

	public long getNumeroDownloads() {
		return numeroDownloads;
	}

	public void setNumeroDownloads(long numeroDownloads) {
		this.numeroDownloads = numeroDownloads;
	}

	public boolean isVisivel() {
		return visivel;
	}

	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
	}

	public TrabalhoDeConclusao getTrabalhoDeConclusao() {
		return trabalhoDeConclusao;
	}

	public void setTrabalhoDeConclusao(TrabalhoDeConclusao trabalhoDeConclusao) {
		this.trabalhoDeConclusao = trabalhoDeConclusao;
	}
	
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
