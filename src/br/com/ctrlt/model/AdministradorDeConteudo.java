package br.com.ctrlt.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "administradorDeConteudo")
public class AdministradorDeConteudo {
	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 30, nullable = false)
	@NotBlank(message = "{administradorDeConteudo.login.vazio}")
	@Size.List({
		@Size(min = 4, message = "{administradorDeConteudo.login.min}"),
		@Size(max = 30, message = "{administradorDeConteudo.login.max}")
	})
	private String login;
	
	@Column(length = 60, nullable = false)
	@NotBlank(message = "{administradorDeConteudo.senha.vazio}")
	@Size.List({
		@Size(min = 4, message = "{administradorDeConteudo.senha.min}"),
		@Size(max = 60, message = "{administradorDeConteudo.senha.max}")
	})
	@JsonIgnore
	private String senha;
	
	@Column(length = 100, nullable = false)
	@NotBlank(message = "{administradorDeConteudo.nome.vazio}")
	@Size.List({
		@Size(min = 2, message = "{administradorDeConteudo.nome.min}"),
		@Size(max = 100, message = "{administradorDeConteudo.nome.max}")
	})
	private String nome;
	
	@Column(nullable = true)
	@Email(message = "{administradorDeConteudo.emailFatec.valido}")
	private String emailFatec;
	
	@Column(nullable = false)
	@NotBlank(message = "{administradorDeConteudo.emailAlternativo.vazio}")
	@Email(message = "{administradorDeConteudo.emailAlternativo.valido}")
	private String emailAlternativo;
	
	@Column(length = 14, nullable = true)
	private String telefone;
	
	@Column(length = 15, nullable = true)
	private String celular;
	
	@Column(nullable = false)
	private boolean ativo;
	
	// FetchType.EAGER = faz select na permissao quando fizer em admConteudo
	// CascadeType.ALL = salva a permissao assim que salvar admConteudo
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL) 
	@JoinColumn(name = "id_permissao")
	private Permissao permissao;

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
		this.senha = senha;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

}
