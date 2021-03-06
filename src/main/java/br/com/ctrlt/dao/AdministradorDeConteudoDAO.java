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

import br.com.ctrlt.model.AdministradorDeConteudo;

/**
 * DAO do Administrador de Conteúdo
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
public class AdministradorDeConteudoDAO implements DAO<AdministradorDeConteudo> {
	@PersistenceContext
	private EntityManager manager; // gerenciar as conexoes de banco de dados.

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<AdministradorDeConteudo> listar(String criterio) {
		Query query = manager.createQuery("SELECT a FROM AdministradorDeConteudo a " + criterio);

		List<AdministradorDeConteudo> listaAdministradoresDeConteudo = query.getResultList();

		return listaAdministradoresDeConteudo;
	}

	@Override
	@Transactional
	public AdministradorDeConteudo pesquisarPorId(long id) {
		// O metodo find do hibernate ja pesquisa pela chave primária.
		AdministradorDeConteudo administradorDeConteudo = manager.find(AdministradorDeConteudo.class, id);

		return administradorDeConteudo;
	}

	@Override
	@Transactional
	public boolean cadastrar(AdministradorDeConteudo administradorDeConteudo) {
		try {
			manager.persist(administradorDeConteudo); // o metodo persist faz o insert
		
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	@Transactional
	public boolean alterar(AdministradorDeConteudo administradorDeConteudo) {
		try {
			manager.merge(administradorDeConteudo); // o metodo merge faz a alteracao
		
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	@Transactional
	public boolean excluir(AdministradorDeConteudo administradorDeConteudo) {
		try {
			manager.remove(manager.merge(administradorDeConteudo)); 
	
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	@Transactional
	public boolean excluirPorId(int id) {
		AdministradorDeConteudo administradorDeConteudo = pesquisarPorId(id);
	
		try {
			manager.remove(administradorDeConteudo); 
	
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}
	
	@Transactional
	public boolean verificarLoginExistente(AdministradorDeConteudo administradorDeConteudo){
		Query query = manager.createQuery("SELECT a FROM AdministradorDeConteudo a "
				+ "WHERE a.login = :login AND a.id <> :id")
				.setParameter("login", administradorDeConteudo.getLogin())
				.setParameter("id", administradorDeConteudo.getId());

		return (query.getResultList().size() > 0);
	}
	
	@Transactional
	public boolean verificarEmailAlternativoExistente(AdministradorDeConteudo administradorDeConteudo){
		Query query = manager.createQuery("SELECT a FROM AdministradorDeConteudo a "
				+ "WHERE a.emailAlternativo = :emailAlternativo AND a.id <> :id")
				.setParameter("emailAlternativo", administradorDeConteudo.getEmailAlternativo())
				.setParameter("id", administradorDeConteudo.getId());

		return (query.getResultList().size() > 0);
	}
	
	@Transactional
	public boolean verificarEmailFatecExistente(AdministradorDeConteudo administradorDeConteudo){
		Query query = manager.createQuery("SELECT a FROM AdministradorDeConteudo a "
				+ "WHERE a.emailFatec = :emailFatec AND a.emailFatec <> '' AND a.id <> :id")
				.setParameter("emailFatec", administradorDeConteudo.getEmailFatec())
				.setParameter("id", administradorDeConteudo.getId());

		return (query.getResultList().size()> 0);
	}
	
	@Transactional
	public AdministradorDeConteudo logar(String login, String senha){
		
		senha = new String(Base64.encodeBase64(senha.getBytes()));
		
		TypedQuery<AdministradorDeConteudo> query = manager.createQuery("SELECT a FROM AdministradorDeConteudo a "
				+ "WHERE a.login = :login "
				+ "AND a.senha = :senha", AdministradorDeConteudo.class)
				.setParameter("login", login)
				.setParameter("senha", senha);
		
		try{
			return query.getSingleResult();
		}catch(Exception e){
			return null;
		}	
	}
	
	@Transactional
	public AdministradorDeConteudo pesquisarPorEmail(String email){
		TypedQuery<AdministradorDeConteudo> query = manager.createQuery("SELECT a FROM AdministradorDeConteudo a "
				+ "WHERE a.emailAlternativo = :email OR "
				+ "a.emailFatec = :email", AdministradorDeConteudo.class)
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
