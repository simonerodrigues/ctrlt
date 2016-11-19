package br.com.ctrlt.dao;

import java.util.List;

public interface DAO<Entidade> {
	
	//M�todo para listar as entidades
	public List<Entidade> listar(String criterio);
	
	//Retorna a entidade pelo c�digo
	public Entidade pesquisarPorId(long id);
	
	//M�todo para cadastro das entidades
	public boolean cadastrar(Entidade entidade);
	
	//M�todo para alterar as entidades
	public boolean alterar(Entidade entidade);
	
	//M�todo para excluir as entidades por objeto
	public boolean excluir(Entidade entidade);
	
	//M�todo para excluir as entidades por objeto
	public boolean excluirPorId(int id);

}
