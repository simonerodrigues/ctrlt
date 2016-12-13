package br.com.ctrlt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import br.com.ctrlt.model.Permissao;

/**
 * DAO da Permissao
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
public class PermissaoDAO implements DAO<Permissao> {
	@PersistenceContext
	private EntityManager manager; // gerenciar as conexoes de banco de dados.

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Permissao> listar(String criterio) {
		Query query = manager.createQuery("SELECT p FROM Permissao p " + criterio);

		List<Permissao> listaPermissao = query.getResultList();

		return listaPermissao;
	}

	@Override
	@Transactional
	public Permissao pesquisarPorId(long id) {
		// O metodo find do hibernate ja pesquisa pela chave primária.
		Permissao permissao = manager.find(Permissao.class, id);

		return permissao;
	}

	@Override
	@Transactional
	public boolean cadastrar(Permissao permissao) {
		try {
			manager.persist(permissao); // o metodo persist faz o insert
		
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	@Transactional
	public boolean alterar(Permissao permissao) {
		try {
			manager.merge(permissao); // o metodo merge faz a alteracao
		
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	@Transactional
	public boolean excluir(Permissao permissao) {
		try {
			manager.remove(manager.merge(permissao)); 
		
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	@Transactional
	public boolean excluirPorId(int id) {
		Permissao permissao = pesquisarPorId(id);
		
		try {
			manager.remove(permissao); 
		
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
