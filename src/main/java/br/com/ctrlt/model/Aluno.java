package br.com.ctrlt.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "aluno")
public class Aluno implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(length = 30, nullable = false)
	@NotBlank(message = "{aluno.login.vazio}")
	@Size.List({ @Size(min = 4, message = "{aluno.login.min}"), @Size(max = 30, message = "{aluno.login.max}") })
	private String login;

	@Column(nullable = false)
	@NotBlank(message = "{aluno.senha.vazio}")
	@Size.List({ 
		@Size(min = 4, message = "{aluno.senha.min}"), 
		@Size(max = 255, message = "{aluno.senha.max}")
	})
	@JsonIgnore
	private String senha;

	@Column(length = 100, nullable = false)
	@NotBlank(message = "{aluno.nome.vazio}")
	@Size.List({ @Size(min = 2, message = "{aluno.nome.min}"), @Size(max = 100, message = "{aluno.nome.max}") })
	private String nome;

	@Column(length = 30, nullable = false)
	@NotBlank(message = "{aluno.ra.vazio}")
	@Size.List({ @Size(min = 2, message = "{aluno.ra.min}"), @Size(max = 30, message = "{aluno.ra.max}") })
	private String ra;

	@Column(nullable = true)
	@Email(message = "{aluno.emailFatec.valido}")
	private String emailFatec;

	@Column(nullable = false)
	@NotBlank(message = "{aluno.emailAlternativo.vazio}")
	@Email(message = "{aluno.emailAlternativo.valido}")
	private String emailAlternativo;

	@Column(length = 14, nullable = true)
	private String telefone;
	
	@Column(length = 15, nullable = true)
	private String celular;

	@Column(nullable = false)
	private boolean ativo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_curso")
	@NotNull(message = "{aluno.cursoPeriodo.vazio}")
	private Curso curso;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_periodo")
	@NotNull(message = "{aluno.cursoPeriodo.vazio}")
	private Periodo periodo;

	@ManyToOne
	@JoinColumn(name = "id_trabalhoDeConclusao")
	@JsonBackReference
	private TrabalhoDeConclusao trabalhoDeConclusao;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = new String(Base64.encodeBase64(senha.getBytes()));
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	public String getEmailFatec() {
		return emailFatec;
	}

	public void setEmailFatec(String emailFatec) {
		this.emailFatec = emailFatec;
	}

	public String getEmailAlternativo() {
		return emailAlternativo;
	}

	public void setEmailAlternativo(String emailAlternativo) {
		this.emailAlternativo = emailAlternativo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	public TrabalhoDeConclusao getTrabalhoDeConclusao() {
		return trabalhoDeConclusao;
	}

	public void setTrabalhoDeConclusao(TrabalhoDeConclusao trabalhoDeConclusao) {
		this.trabalhoDeConclusao = trabalhoDeConclusao;
	}

}
