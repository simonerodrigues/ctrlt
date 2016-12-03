package br.com.ctrlt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import br.com.ctrlt.model.Monografia;

@Repository
@EnableTransactionManagement(proxyTargetClass = true)
public class MonografiaDAO implements DAO<Monografia> {
	@PersistenceContext
	private EntityManager manager; // gerenciar as conexoes de banco de dados.

	@SuppressWarnings("unchecked")
	@Override
	public List<Monografia> listar(String criterio) {
		Query query = manager.createQuery("SELECT m FROM Monografia m " + criterio);

		List<Monografia> listaMonografias = query.getResultList();

		return listaMonografias;
	}

	@Override
	public Monografia pesquisarPorId(long id) {
		// O metodo find do hibernate ja pesquisa pela chave primária.
		Monografia monografia = manager.find(Monografia.class, id);

		return monografia;
	}

	@Override
	@Transactional
	public boolean cadastrar(Monografia monografia) {
		try {
			manager.persist(monografia); // o metodo persist faz o insert
		
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	@Transactional
	public boolean alterar(Monografia monografia) {
		try {
			manager.merge(monografia); // o metodo merge faz a alteracao
		
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	@Transactional
	public boolean excluir(Monografia monografia) {
		try {
			manager.remove(manager.merge(monografia)); 
		
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	@Transactional
	public boolean excluirPorId(int id) {
		Monografia monografia = pesquisarPorId(id);
		
		try {
			manager.remove(monografia); 
		
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
