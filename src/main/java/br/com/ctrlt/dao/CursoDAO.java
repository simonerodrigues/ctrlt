package br.com.ctrlt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import br.com.ctrlt.model.Curso;

@Repository
@EnableTransactionManagement(proxyTargetClass = true)
public class CursoDAO implements DAO<Curso> {
	@PersistenceContext
	private EntityManager manager; // gerenciar as conexoes de banco de dados.

	@SuppressWarnings("unchecked")
	@Override
	public List<Curso> listar(String criterio) {
		Query query = manager.createQuery("SELECT c FROM Curso c " + criterio);

		List<Curso> listaCursos = query.getResultList();

		return listaCursos;
	}

	@Override
	public Curso pesquisarPorId(long id) {
		// O metodo find do hibernate ja pesquisa pela chave primária.
		Curso curso = manager.find(Curso.class, id);

		return curso;
	}

	@Override
	@Transactional
	public boolean cadastrar(Curso curso) {
		try {
			manager.persist(curso); // o metodo persist faz o insert

			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	@Transactional
	public boolean alterar(Curso curso) {
		try {
			manager.merge(curso); // o metodo merge faz a alteracao

			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	@Transactional
	public boolean excluir(Curso curso) {
		try {
			manager.remove(manager.merge(curso)); 

			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public boolean excluirPorId(int id) {
		Curso curso = pesquisarPorId(id);
		
		try {
			manager.remove(curso); 
			
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
