package br.com.ctrlt.model;

/**
 * Classe de modelo do Dashboard do projeto
 * 
 * @author Simone Santos Rodrigues
 * @version 1.0
 * 
 * Este sistema foi desenvolvido sob a licença GPL/GNU versão 3, onde qualquer pessoa poderá copiar e distribuir cópias sem alterações deste documento de licença.
 * Consulte mais informações sobre essa licença no arquivo gpl.txt que contêm na raiz do projeto.
 * 
 */

public class Dashboard {
	private long numeroTrabalhosDeConclusao;
	private long numeroMonografias;
	private long numeroAnexos;
	private long numeroAdministradoresDeConteudo;
	private long numeroProfessores;
	private long numeroAlunos;
	private String tamanhoArquivos;
	private long numeroDownloads;

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

	public String getTamanhoArquivos() {
		return tamanhoArquivos;
	}

	public void setTamanhoArquivos(String tamanhoArquivos) {
		this.tamanhoArquivos = tamanhoArquivos;
	}

	public long getNumeroDownloads() {
		return numeroDownloads;
	}

	public void setNumeroDownloads(long numeroDownloads) {
		this.numeroDownloads = numeroDownloads;
	}

}
