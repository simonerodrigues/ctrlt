package br.com.ctrlt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import br.com.ctrlt.model.Aluno;

@Repository
@EnableTransactionManagement(proxyTargetClass = true)
public class AlunoDAO implements DAO<Aluno> {
	@PersistenceContext
	private EntityManager manager; // gerenciar as conexoes de banco de dados.

	@SuppressWarnings("unchecked")
	@Override
	public List<Aluno> listar(String criterio) {
		Query query = manager.createQuery("SELECT a FROM Aluno a " + criterio);

		List<Aluno> listaAlunos = query.getResultList();

		return listaAlunos;
	}

	@Override
	public Aluno pesquisarPorId(long id) {
		// O metodo find do hibernate ja pesquisa pela chave primária.
		Aluno aluno = manager.find(Aluno.class, id);

		return aluno;
	}

	@Override
	@Transactional
	public boolean cadastrar(Aluno aluno) {
		try {
			manager.persist(aluno); // o metodo persist faz o insert
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			
			return false;
		}
	}

	@Override
	@Transactional
	public boolean alterar(Aluno aluno) {
		try {
			manager.merge(aluno); // o metodo merge faz a alteracao
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			
			return false;
		}
	}

	@Override
	@Transactional
	public boolean excluir(Aluno aluno) {
		try {
			manager.remove(manager.merge(aluno)); 
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			
			return false;
		}
	}

	@Override
	@Transactional
	public boolean excluirPorId(int id) {
		Aluno aluno = pesquisarPorId(id);
		try {
			manager.remove(aluno); 
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			
			return false;
		}
	}
	
	public boolean verificarRaExistente(Aluno aluno){
		Query query = manager.createQuery("SELECT a FROM Aluno a "
				+ "WHERE a.ra = :ra AND a.id <> :id")
				.setParameter("ra", aluno.getRa())
				.setParameter("id", aluno.getId());

		return (query.getResultList().size() > 0);
	}
	
	public boolean verificarLoginExistente(Aluno aluno){
		Query query = manager.createQuery("SELECT a FROM Aluno a "
				+ "WHERE a.login = :login AND a.id <> :id")
				.setParameter("login", aluno.getLogin())
				.setParameter("id", aluno.getId());

		return (query.getResultList().size() > 0);
	}
	
	public boolean verificarEmailAlternativoExistente(Aluno aluno){
		Query query = manager.createQuery("SELECT a FROM Aluno a "
				+ "WHERE a.emailAlternativo = :emailAlternativo AND a.id <> :id")
				.setParameter("emailAlternativo", aluno.getEmailAlternativo())
				.setParameter("id", aluno.getId());

		return (query.getResultList().size() > 0);
	}
	
	public boolean verificarEmailFatecExistente(Aluno aluno){
		Query query = manager.createQuery("SELECT a FROM Aluno a "
				+ "WHERE a.emailFatec = :emailFatec AND a.emailFatec <> '' AND a.id <> :id")
				.setParameter("emailFatec", aluno.getEmailFatec())
				.setParameter("id", aluno.getId());

		return (query.getResultList().size()> 0);
	}

	public Aluno logar(String login, String senha){
		
		senha = new String(Base64.encodeBase64(senha.getBytes()));
		
		TypedQuery<Aluno> query = manager.createQuery("SELECT a FROM Aluno a "
				+ "WHERE a.login = :login "
				+ "AND a.senha = :senha", Aluno.class)
				.setParameter("login", login)
				.setParameter("senha", senha);
		
		try{
			return query.getSingleResult();
		}catch(Exception e){
			return null;
		}
	}

	public EntityManager getEntityManager() {
		return manager;
	}
}
