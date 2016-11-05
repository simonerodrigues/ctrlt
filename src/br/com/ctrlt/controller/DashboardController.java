package br.com.ctrlt.controller;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.io.FileUtils;
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
		
		//Trabalhos de Conclusão
		dashboard.setNumeroTrabalhosDeConclusao((Long) manager.createQuery("SELECT COUNT(t) FROM TrabalhoDeConclusao t").getSingleResult());
		dashboard.setNumeroMonografias((Long) manager.createQuery("SELECT COUNT(m) FROM Monografia m").getSingleResult());
		dashboard.setNumeroAnexos((Long) manager.createQuery("SELECT COUNT(a) FROM Anexo a").getSingleResult());
		
		//Usuários do Sistema
		dashboard.setNumeroAdministradoresDeConteudo((Long) manager.createQuery("SELECT COUNT(a) FROM AdministradorDeConteudo a").getSingleResult());
		dashboard.setNumeroProfessores((Long) manager.createQuery("SELECT COUNT(p) FROM Professor p").getSingleResult());
		dashboard.setNumeroAlunos((Long) manager.createQuery("SELECT COUNT(a) FROM Aluno a").getSingleResult());
		
		//Informações de Arquivos
		BigInteger tamanhoArquivosMonografia = (BigInteger) manager.createQuery("SELECT SUM(m.tamanho) FROM Monografia m").getSingleResult();
		BigInteger tamanhoArquivosAnexos     = (BigInteger) manager.createQuery("SELECT SUM(a.tamanho) FROM Anexo a").getSingleResult();
		dashboard.setTamanhoArquivos(FileUtils.byteCountToDisplaySize(tamanhoArquivosMonografia.add(tamanhoArquivosAnexos)));
		
		Long numeroDownloadsMonografia = (Long) manager.createQuery("SELECT SUM(m.numeroDownloads) FROM Monografia m").getSingleResult();
		Long numeroDownloadsAnexo = (Long) manager.createQuery("SELECT SUM(a.numeroDownloads) FROM Anexo a").getSingleResult();
		
		dashboard.setNumeroDownloads(numeroDownloadsMonografia + numeroDownloadsAnexo);
		
		return dashboard;
	}
	
}
