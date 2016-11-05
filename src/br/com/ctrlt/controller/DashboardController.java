package br.com.ctrlt.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ctrlt.model.Dashboard;

@Controller
public class DashboardController {
	
	@PersistenceContext
	private EntityManager manager; // gerenciar as conexoes de banco de dados.

	@ResponseBody
	@RequestMapping(value = "rest/dashboard", method = RequestMethod.POST)
	public Dashboard carregarInformacoes() {
		Dashboard dashboard = new Dashboard();
		
		dashboard.setNumeroTrabalhosDeConclusao((Long) manager.createQuery("SELECT COUNT(t) FROM TrabalhoDeConclusao t").getSingleResult());
		dashboard.setNumeroMonografias((Long) manager.createQuery("SELECT COUNT(m) FROM Monografia m").getSingleResult());
		dashboard.setNumeroAnexos((Long) manager.createQuery("SELECT COUNT(a) FROM Anexo a").getSingleResult());
		
		dashboard.setNumeroAdministradoresDeConteudo((Long) manager.createQuery("SELECT COUNT(a) FROM AdministradorDeConteudo a").getSingleResult());
		dashboard.setNumeroProfessores((Long) manager.createQuery("SELECT COUNT(p) FROM Professor p").getSingleResult());
		dashboard.setNumeroAlunos((Long) manager.createQuery("SELECT COUNT(a) FROM Aluno a").getSingleResult());
		
		return dashboard;
	}
	
}
