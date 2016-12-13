package br.com.ctrlt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import br.com.ctrlt.model.LinhaDePesquisa;

/**
 * DAO da Linha de Pesquisa
 * 
 * @author Simone Santos Rodrigues
 * @version 1.0
 * 
 * Este sistema foi desenvolvido sob a licença GPL/GNU versão 3, onde qualquer pessoa poderá copiar e distribuir cópias sem alterações deste documento de licença.
 * Consulte mais informações sobre essa licença no arquivo gpl.txt que contêm na raiz do projeto.
 * 
 */

@Repository
@EnableTransactionManagement(proxyTargetClass = true)
public class LinhaDePesquisaDAO implements DAO<LinhaDePesquisa> {
	@PersistenceContext
	private EntityManager manager; // gerenciar as conexoes de banco de dados.

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<LinhaDePesquisa> listar(String criterio) {
		Query query = manager.createQuery("SELECT l FROM LinhaDePesquisa l " + criterio);

		List<LinhaDePesquisa> listaLinhaDePesquisas = query.getResultList();

		return listaLinhaDePesquisas;
	}

	@Override
	@Transactional
	public LinhaDePesquisa pesquisarPorId(long id) {
		// O metodo find do hibernate ja pesquisa pela chave primária.
		LinhaDePesquisa linhaDePesquisa = manager.find(LinhaDePesquisa.class, id);

		return linhaDePesquisa;
	}

	@Override
	@Transactional
	public boolean cadastrar(LinhaDePesquisa linhaDePesquisa) {
		try {
			manager.persist(linhaDePesquisa); // o metodo persist faz o insert
		
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	@Transactional
	public boolean alterar(LinhaDePesquisa linhaDePesquisa) {
		try {
			manager.merge(linhaDePesquisa); // o metodo merge faz a alteracao
		
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	@Transactional
	public boolean excluir(LinhaDePesquisa linhaDePesquisa) {
		try {
			manager.remove(manager.merge(linhaDePesquisa)); 
		
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	@Transactional
	public boolean excluirPorId(int id) {
		LinhaDePesquisa linhaDePesquisa = pesquisarPorId(id);
		
		try {
			manager.remove(linhaDePesquisa); 
		
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	public EntityManager getEntityManager() {
		return manager;
	}
	
}
