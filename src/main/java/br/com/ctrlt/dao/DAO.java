package br.com.ctrlt.dao;

import java.util.List;

/**
 * Interface DAO - Implementada nas demais DAO
 * 
 * @author Simone Santos Rodrigues
 * @version 1.0
 * 
 * Este sistema foi desenvolvido sob a licença GPL/GNU versão 3, onde qualquer pessoa poderá copiar e distribuir cópias sem alterações deste documento de licença.
 * Consulte mais informações sobre essa licença no arquivo gpl.txt que contêm na raiz do projeto.
 * 
 */

public interface DAO<Entidade> {
	
	//Método para listar as entidades
	public List<Entidade> listar(String criterio);
	
	//Retorna a entidade pelo código
	public Entidade pesquisarPorId(long id);
	
	//Método para cadastro das entidades
	public boolean cadastrar(Entidade entidade);
	
	//Método para alterar as entidades
	public boolean alterar(Entidade entidade);
	
	//Método para excluir as entidades por objeto
	public boolean excluir(Entidade entidade);
	
	//Método para excluir as entidades por objeto
	public boolean excluirPorId(int id);

}
