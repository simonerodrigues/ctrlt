package br.com.ctrlt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import br.com.ctrlt.model.TrabalhoDeConclusao;

@Repository
@EnableTransactionManagement(proxyTargetClass = true)
public class TrabalhoDeConclusaoDAO implements DAO<TrabalhoDeConclusao> {
	@PersistenceContext
	private EntityManager manager; // gerenciar as conexoes de banco de dados.

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<TrabalhoDeConclusao> listar(String criterio) {
		Query query = manager.createQuery("SELECT t FROM TrabalhoDeConclusao t " + criterio);

		List<TrabalhoDeConclusao> listaTCC = query.getResultList();

		return listaTCC;
	}

	@Override
	@Transactional
	public TrabalhoDeConclusao pesquisarPorId(long id) {
		// O metodo find do hibernate ja pesquisa pela chave primária.
		TrabalhoDeConclusao trabalhoDeConclusao = manager.find(TrabalhoDeConclusao.class, id);

		return trabalhoDeConclusao;
	}

	@Override
	@Transactional
	public boolean cadastrar(TrabalhoDeConclusao trabalhoDeConclusao) {
		try {
			manager.persist(trabalhoDeConclusao); // o metodo persist faz o insert
		
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	@Transactional
	public boolean alterar(TrabalhoDeConclusao trabalhoDeConclusao) {
		try {
			manager.merge(trabalhoDeConclusao); // o metodo merge faz a alteracao
		
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	@Transactional
	public boolean excluir(TrabalhoDeConclusao trabalhoDeConclusao) {
		try {
			manager.remove(manager.merge(trabalhoDeConclusao)); 
		
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	@Transactional
	public boolean excluirPorId(int id) {
		TrabalhoDeConclusao trabalhoDeConclusao = pesquisarPorId(id);
		
		try {
			manager.remove(trabalhoDeConclusao); 
		
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<TrabalhoDeConclusao> listarOsMaisBaixados(int quantidade){
		Query query = manager.createQuery("SELECT t FROM TrabalhoDeConclusao t "
				+ "JOIN FETCH t.monografia m "
				+ "WHERE t.ativo = true "
				+ "ORDER BY m.numeroDownloads DESC");

		List<TrabalhoDeConclusao> listaTCC = query.setMaxResults(quantidade).getResultList();
		
		return listaTCC;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<TrabalhoDeConclusao> listarOsRecemAdicionados(int quantidade){
		Query query = manager.createQuery("SELECT t FROM TrabalhoDeConclusao t "
				+ "JOIN FETCH t.monografia m "
				+ "WHERE t.ativo = true "
				+ "ORDER BY m.dataUpload DESC");

		List<TrabalhoDeConclusao> listaTCC = query.setMaxResults(quantidade).getResultList();
		
		return listaTCC;
	}

	public EntityManager getEntityManager() {
		return manager;
	}
	
}
