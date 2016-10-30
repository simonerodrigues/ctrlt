package br.com.ctrlt.model;

import java.util.Date;

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
	private double tamanho;
	
	@Temporal(TemporalType.TIMESTAMP)	// Data e Hora
	@DateTimeFormat(pattern = "dd/MM/yyy HH:mm")
	@Column(nullable = false)
	private Date dataUpload;
	
	@Column(nullable = false)
	private int numeroDownload;
	
	@Column(nullable = false)
	private boolean visivel;
	
	@ManyToOne
	@JoinColumn(name="id_trabalhoDeConclusao")
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

	public double getTamanho() {
		return tamanho;
	}

	public void setTamanho(double tamanho) {
		this.tamanho = tamanho;
	}

	public Date getDataUpload() {
		return dataUpload;
	}

	public void setDataUpload(Date dataUpload) {
		this.dataUpload = dataUpload;
	}

	public int getNumeroDownload() {
		return numeroDownload;
	}

	public void setNumeroDownload(int numeroDownload) {
		this.numeroDownload = numeroDownload;
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
