package br.com.ctrlt.model;

import java.util.Calendar;
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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "trabalhoDeConclusao")
public class TrabalhoDeConclusao {
	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 200, nullable = false)
	@NotBlank(message = "{trabalhoDeConclusao.titulo.vazio}")
	@Size.List({
		@Size(min = 2, message = "{trabalhoDeConclusao.titulo..min}"),
		@Size(max = 200, message = "{trabalhoDeConclusao.titulo.max}")
	})
	private String titulo;
	
	@Lob
	private String resumo;
	
	@Temporal(TemporalType.TIMESTAMP)	// Data e Hora
	@DateTimeFormat(pattern = "dd/MM/yyy HH:mm")
	@Column(nullable = false)
	private Calendar dataPublicao;
	
	@OneToMany(mappedBy = "trabalhoDeConclusao")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Aluno> listaAlunos;
	
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "trabalhoDeConclusao_professor",
		joinColumns = @JoinColumn(name = "id_trabalhoDeConclusao"),
		inverseJoinColumns = @JoinColumn(name = "id_professor")
	)
	private List<Professor> listaProfessores;
	
	@OneToMany(mappedBy = "trabalhoDeConclusao")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Anexo> listaAnexos;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // faz select na permissao quando fizer em admConteudo
	@JoinColumn(name = "id_monografia", nullable = true)
	private Monografia monografia;
	
	@Column(nullable = false)
	private boolean ativo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public Calendar getDataPublicao() {
		return dataPublicao;
	}

	public void setDataPublicao(Calendar dataPublicao) {
		this.dataPublicao = dataPublicao;
	}

	public List<Aluno> getListaAlunos() {
		return listaAlunos;
	}

	public void setListaAlunos(List<Aluno> listaAlunos) {
		this.listaAlunos = listaAlunos;
	}

	public List<Professor> getListaProfessores() {
		return listaProfessores;
	}

	public void setListaProfessores(List<Professor> listaProfessores) {
		this.listaProfessores = listaProfessores;
	}

	public List<Anexo> getListaAnexos() {
		return listaAnexos;
	}

	public void setListaAnexos(List<Anexo> listaAnexos) {
		this.listaAnexos = listaAnexos;
	}

	public Monografia getMonografia() {
		return monografia;
	}

	public void setMonografia(Monografia monografia) {
		this.monografia = monografia;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}	

}
