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

import br.com.ctrlt.model.Professor;

@Repository
@EnableTransactionManagement(proxyTargetClass = true)
public class ProfessorDAO implements DAO<Professor> {
	@PersistenceContext
	private EntityManager manager; // gerenciar as conexoes de banco de dados.
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Professor> listar(String criterio) {
		Query query = manager.createQuery("SELECT p FROM Professor p " + criterio);

		List<Professor> listaProfessores = query.getResultList();

		return listaProfessores;
	}

	@Override
	@Transactional
	public Professor pesquisarPorId(long id) {
		// O metodo find do hibernate ja pesquisa pela chave primárip.
		Professor professor = manager.find(Professor.class, id);

		return professor;
	}

	@Override
	@Transactional
	public boolean cadastrar(Professor professor) {
		try {
			manager.persist(professor); // o metodo persist faz o insert
		
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	@Transactional
	public boolean alterar(Professor professor) {
		try {
			manager.merge(professor); // o metodo merge faz a alteracao
		
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	@Transactional
	public boolean excluir(Professor professor) {
		try {
			manager.remove(manager.merge(professor));
		
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	@Transactional
	public boolean excluirPorId(int id) {
		Professor professor = pesquisarPorId(id);
		
		try {
			manager.remove(professor); 
		
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}
	
	@Transactional
	public boolean verificarLoginExistente(Professor professor){
		Query query = manager.createQuery("SELECT p FROM Professor p "
				+ "WHERE p.login = :login AND p.id <> :id")
				.setParameter("login", professor.getLogin())
				.setParameter("id", professor.getId());

		return (query.getResultList().size() > 0);
	}
	
	@Transactional
	public boolean verificarEmailAlternativoExistente(Professor professor){
		Query query = manager.createQuery("SELECT p FROM Professor p "
				+ "WHERE p.emailAlternativo = :emailAlternativo AND p.id <> :id")
				.setParameter("emailAlternativo", professor.getEmailAlternativo())
				.setParameter("id", professor.getId());

		return (query.getResultList().size() > 0);
	}
	
	@Transactional
	public boolean verificarEmailFatecExistente(Professor professor){
		Query query = manager.createQuery("SELECT p FROM Professor p "
				+ "WHERE p.emailFatec = :emailFatec AND p.emailFatec <> '' AND p.id <> :id")
				.setParameter("emailFatec", professor.getEmailFatec())
				.setParameter("id", professor.getId());

		return (query.getResultList().size()> 0);
	}
	
	@Transactional
	public Professor logar(String login, String senha){
		
		senha = new String(Base64.encodeBase64(senha.getBytes()));
		
		TypedQuery<Professor> query = manager.createQuery("SELECT p FROM Professor p "
				+ "WHERE p.login = :login "
				+ "AND p.senha = :senha", Professor.class)
				.setParameter("login", login)
				.setParameter("senha", senha);
		
		try{
			return query.getSingleResult();
		}catch(Exception e){
			return null;
		}
	}
	
	@Transactional
	public Professor pesquisarPorEmail(String email){
		TypedQuery<Professor> query = manager.createQuery("SELECT p FROM Professor p "
				+ "WHERE p.emailAlternativo = :email OR "
				+ "p.emailFatec = :email", Professor.class)
				.setParameter("email", email);
		
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
