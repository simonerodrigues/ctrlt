package br.com.ctrlt.dao;

import java.util.List;

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
