package br.com.ctrlt.model;

public class Dashboard {
	private long numeroTrabalhosDeConclusao;
	private long numeroMonografias;
	private long numeroAnexos;
	private long numeroAdministradoresDeConteudo;
	private long numeroProfessores;
	private long numeroAlunos;

	public Dashboard() {

	}

	public Dashboard(long numeroTrabalhosDeConclusao, long numeroMonografias, long numeroAnexos,
			long numeroAdministradoresDeConteudo, long numeroProfessores, long numeroAlunos) {
		super();
		this.numeroTrabalhosDeConclusao = numeroTrabalhosDeConclusao;
		this.numeroMonografias = numeroMonografias;
		this.numeroAnexos = numeroAnexos;
		this.numeroAdministradoresDeConteudo = numeroAdministradoresDeConteudo;
		this.numeroProfessores = numeroProfessores;
		this.numeroAlunos = numeroAlunos;
	}

	public long getNumeroTrabalhosDeConclusao() {
		return numeroTrabalhosDeConclusao;
	}

	public void setNumeroTrabalhosDeConclusao(long numeroTrabalhosDeConclusao) {
		this.numeroTrabalhosDeConclusao = numeroTrabalhosDeConclusao;
	}

	public long getNumeroMonografias() {
		return numeroMonografias;
	}

	public void setNumeroMonografias(long numeroMonografias) {
		this.numeroMonografias = numeroMonografias;
	}

	public long getNumeroAnexos() {
		return numeroAnexos;
	}

	public void setNumeroAnexos(long numeroAnexos) {
		this.numeroAnexos = numeroAnexos;
	}

	public long getNumeroAdministradoresDeConteudo() {
		return numeroAdministradoresDeConteudo;
	}

	public void setNumeroAdministradoresDeConteudo(long numeroAdministradoresDeConteudo) {
		this.numeroAdministradoresDeConteudo = numeroAdministradoresDeConteudo;
	}

	public long getNumeroProfessores() {
		return numeroProfessores;
	}

	public void setNumeroProfessores(long numeroProfessores) {
		this.numeroProfessores = numeroProfessores;
	}

	public long getNumeroAlunos() {
		return numeroAlunos;
	}

	public void setNumeroAlunos(long numeroAlunos) {
		this.numeroAlunos = numeroAlunos;
	}

}
