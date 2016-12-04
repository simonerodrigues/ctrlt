package br.com.ctrlt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import br.com.ctrlt.model.Periodo;

@Repository
@EnableTransactionManagement(proxyTargetClass = true)
public class PeriodoDAO implements DAO<Periodo> {
	@PersistenceContext
	private EntityManager manager; // gerenciar as conexoes de banco de dados.

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Periodo> listar(String criterio) {
		Query query = manager.createQuery("SELECT p FROM Periodo p " + criterio);

		List<Periodo> listaPeriodos = query.getResultList();

		return listaPeriodos;
	}

	@Override
	@Transactional
	public Periodo pesquisarPorId(long id) {
		// O metodo find do hibernate ja pesquisa pela chave primária.
		Periodo periodo = manager.find(Periodo.class, id);

		return periodo;
	}

	@Override
	@Transactional
	public boolean cadastrar(Periodo periodo) {
		try {
			manager.persist(periodo); // o metodo persist faz o insert
		
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	@Transactional
	public boolean alterar(Periodo periodo) {
		try {
			manager.merge(periodo); // o metodo merge faz a alteracao
		
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	@Transactional
	public boolean excluir(Periodo periodo) {
		try {
			manager.remove(manager.merge(periodo)); 
		
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	@Transactional
	public boolean excluirPorId(int id) {
		Periodo periodo = pesquisarPorId(id);
		
		try {
			manager.remove(periodo); 
		
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
