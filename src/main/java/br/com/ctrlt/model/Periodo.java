package br.com.ctrlt.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "periodo")
public class Periodo {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 100, nullable = false)
	@NotBlank(message = "{periodo.nome.vazio}")
	@Size.List({
		@Size(min = 2, message = "{periodo.nome.min}"),
		@Size(max = 100, message = "{periodo.nome.max}")
	})
	private String nome;
	
	@Column(length = 2, nullable = false)
	@NotBlank(message = "{periodo.sigla.vazio}")
	@Size.List({
		@Size(min = 2, message = "{periodo.sigla.size}"),
		@Size(max = 2, message = "{periodo.sigla.size}")
	})
	private String sigla;
	
	@Column(columnDefinition = "text")
	private String descricao;
	
	@Column(nullable = false)
	private boolean ativo;
	
	@ManyToMany(mappedBy = "listaPeriodo")
	private List<Curso> listaCurso;

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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
