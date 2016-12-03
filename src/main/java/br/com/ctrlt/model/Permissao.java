package br.com.ctrlt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "permissao")
public class Permissao {
	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private boolean manterAluno;
	
	@Column(nullable = false)
	private boolean manterProfessor;
	
	@Column(nullable = false)
	private boolean manterLinhaDePesquisa;
	
	@Column(nullable = false)
	private boolean manterCurso;
	
	@Column(nullable = false)
	private boolean manterPeriodo;
	
	@Column(nullable = false)
	private boolean publicarTCC;
	
	@Column(nullable = false)
	private boolean associacoes;
	
	@Column(nullable = false)
	private boolean extrairRelatorio;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isManterAluno() {
		return manterAluno;
	}

	public void setManterAluno(boolean manterAluno) {
		this.manterAluno = manterAluno;
	}

	public boolean isManterProfessor() {
		return manterProfessor;
	}

	public void setManterProfessor(boolean manterProfessor) {
		this.manterProfessor = manterProfessor;
	}

	public boolean isManterLinhaDePesquisa() {
		return manterLinhaDePesquisa;
	}

	public void setManterLinhaDePesquisa(boolean manterLinhaDePesquisa) {
		this.manterLinhaDePesquisa = manterLinhaDePesquisa;
	}

	public boolean isManterCurso() {
		return manterCurso;
	}

	public void setManterCurso(boolean manterCurso) {
		this.manterCurso = manterCurso;
	}

	public boolean isManterPeriodo() {
		return manterPeriodo;
	}

	public void setManterPeriodo(boolean manterPeriodo) {
		this.manterPeriodo = manterPeriodo;
	}

	public boolean isPublicarTCC() {
		return publicarTCC;
	}

	public void setPublicarTCC(boolean publicarTCC) {
		this.publicarTCC = publicarTCC;
	}

	public boolean isAssociacoes() {
		return associacoes;
	}

	public void setAssociacoes(boolean associacoes) {
		this.associacoes = associacoes;
	}

	public boolean isExtrairRelatorio() {
		return extrairRelatorio;
	}

	public void setExtrairRelatorio(boolean extrairRelatorio) {
		this.extrairRelatorio = extrairRelatorio;
	}

}
