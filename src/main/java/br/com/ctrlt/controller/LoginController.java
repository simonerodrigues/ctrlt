package br.com.ctrlt.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ctrlt.dao.AdministradorDeConteudoDAO;
import br.com.ctrlt.dao.AlunoDAO;
import br.com.ctrlt.dao.ProfessorDAO;
import br.com.ctrlt.json.ResponseJson;
import br.com.ctrlt.model.AdministradorDeConteudo;
import br.com.ctrlt.model.Aluno;
import br.com.ctrlt.model.Professor;

/**
 * Controller do Login
 * 
 * @author Simone Santos Rodrigues
 * @version 1.0
 * 
 * Este sistema foi desenvolvido sob a licença GPL/GNU versão 3, onde qualquer pessoa poderá copiar e distribuir cópias sem alterações deste documento de licença.
 * Consulte mais informações sobre essa licença no arquivo gpl.txt que contêm na raiz do projeto.
 * 
 */

@Controller
public class LoginController {
	
	@PersistenceContext
	EntityManager manager;
	
	@Autowired
	AdministradorDeConteudoDAO administradorDeConteudoDAO;
	
	@Autowired
	ProfessorDAO ProfessorDAO;
	
	@Autowired
	AlunoDAO alunoDAO;
	
	@Transactional
	@RequestMapping(value = "efetua_login", method = RequestMethod.POST)
	public @ResponseBody ResponseJson efetuaLogin(HttpServletRequest req, HttpSession session) {
		// Cria objeto de retorno do JSON
		ResponseJson res = new ResponseJson();
		
		switch (req.getParameter("tipo")) {
		case "1":
			AdministradorDeConteudo administradorDeConteudo = administradorDeConteudoDAO.logar(req.getParameter("login"), req.getParameter("senha"));
			
			if(administradorDeConteudo != null){
				res.setStatus("SUCCESS");
				res.setResult("Logado com sucesso!");
				session.setAttribute("administradorLogado", administradorDeConteudo);
			}else{
				res.setStatus("FAIL");
				res.setResult("Usuário e/ou senha incorretos! Por gentileza tente novamente!");
			}
			break;

		case "2":
			Professor professor = ProfessorDAO.logar(req.getParameter("login"), req.getParameter("senha"));
			
			if(professor != null){
				res.setStatus("SUCCESS");
				res.setResult("Logado com sucesso!");
				session.setAttribute("professorLogado", professor);
			}else{
				res.setStatus("FAIL");
				res.setResult("Usuário e/ou senha incorretos! Por gentileza tente novamente!");
			}
			break;
			
		case "3":
			Aluno aluno = alunoDAO.logar(req.getParameter("login"), req.getParameter("senha"));
			
			if(aluno != null){
				res.setStatus("SUCCESS");
				res.setResult("Logado com sucesso!");
				session.setAttribute("alunoLogado", aluno);
			}else{
				res.setStatus("FAIL");
				res.setResult("Usuário e/ou senha incorretos! Por gentileza tente novamente!");
			}
			break;
			
		default:
			res.setStatus("FAIL");
			res.setResult("Erro ao realizar o login, por gentileza, tente novamente!");
			break;
		}
		
		return res;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
	  session.invalidate();
	  return "redirect:/login";
	}
}
