package br.com.ctrlt.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "professor")
public class Professor {
	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 30, nullable = false)
	@NotBlank(message = "{professor.login.vazio}")
	@Size.List({
		@Size(min = 4, message = "{professor.login.min}"),
		@Size(max = 30, message = "{professor.login.max}")
	})
	private String login;
	
	@Column(length = 60, nullable = false)
	@NotBlank(message = "{professor.senha.vazio}")
	@Size.List({
		@Size(min = 4, message = "{professor.senha.min}"),
		@Size(max = 60, message = "{professor.senha.max}")
	})
	private String senha;
	
	@Column(length = 100, nullable = false)
	@NotBlank(message = "{professor.nome.vazio}")
	@Size.List({
		@Size(min = 2, message = "{professor.nome.min}"),
		@Size(max = 100, message = "{professor.nome.max}")
	})
	private String nome;
	
	@Column(nullable = true)
	private String emailFatec;
	
	@Column(nullable = false)
	@NotBlank(message = "{professor.emailAlternativo.vazio}")
	@Email(message = "{professor.emailAlternativo.valido}")
	private String emailAlternativo;
	
	@Column(length = 14, nullable = true)
	private String telefone;
	
	@Column(length = 15, nullable = true)
	private String celular;
	
	@Column(nullable = false)
	private boolean ativo;
	
	@Column(nullable = false)
	private boolean coordenador;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "professor_linhaDePesquisa",
		joinColumns = @JoinColumn(name = "id_professor"),
		inverseJoinColumns = @JoinColumn(name = "id_linhaDePesquisa")
	)
	private List<LinhaDePesquisa> listaLinhaDePesquisa;

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

	public boolean isCoordenador() {
		return coordenador;
	}

	public void setCoordenador(boolean coordenador) {
		this.coordenador = coordenador;
	}

	public List<LinhaDePesquisa> getListaLinhaDePesquisa() {
		return listaLinhaDePesquisa;
	}

	public void setListaLinhaDePesquisa(List<LinhaDePesquisa> listaLinhaDePesquisa) {
		this.listaLinhaDePesquisa = listaLinhaDePesquisa;
	}

}
