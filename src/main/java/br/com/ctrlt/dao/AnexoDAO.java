package br.com.ctrlt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import br.com.ctrlt.model.Anexo;

@Repository
@EnableTransactionManagement(proxyTargetClass = true)
public class AnexoDAO implements DAO<Anexo> {
	@PersistenceContext
	private EntityManager manager; // gerenciar as conexoes de banco de dados.

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Anexo> listar(String criterio) {
		Query query = manager.createQuery("SELECT a FROM Anexo a " + criterio);

		List<Anexo> listaAnexos = query.getResultList();

		return listaAnexos;
	}

	@Override
	@Transactional
	public Anexo pesquisarPorId(long id) {
		// O metodo find do hibernate ja pesquisa pela chave primária.
		Anexo anexo = manager.find(Anexo.class, id);

		return anexo;
	}

	@Override
	@Transactional
	public boolean cadastrar(Anexo anexo) {
		try {
			manager.persist(anexo); // o metodo persist faz o insert
		
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	@Transactional
	public boolean alterar(Anexo anexo) {
		try {
			manager.merge(anexo); // o metodo merge faz a alteracao
		
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	@Transactional
	public boolean excluir(Anexo anexo) {
		try {
			manager.remove(manager.merge(anexo)); 
		
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	@Transactional
	public boolean excluirPorId(int id) {
		Anexo anexo = pesquisarPorId(id);
		
		try {
			manager.remove(anexo); 
		
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
