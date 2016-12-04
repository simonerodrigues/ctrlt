	package br.com.ctrlt.model;

import java.math.BigInteger;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "monografia")
public class Monografia {
	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 100, nullable = false)
	@NotBlank(message = "{monografia.nome.vazio}")
	private String nome;
	
	@Column(nullable = false)
	private String caminho;
	
	@Column(length = 20, nullable = false)
	private String extensao;
	
	@Column(nullable = false)
	private BigInteger tamanho;
	
	@Temporal(TemporalType.TIMESTAMP) //Data e Hora
	@DateTimeFormat(pattern = "dd/MM/yyy HH:mm")
	@Column(nullable = false)
	private Calendar dataUpload;
	
	@Column(nullable = false)
	private long numeroDownloads;
	
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

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
