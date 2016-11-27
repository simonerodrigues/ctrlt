package br.com.ctrlt.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ctrlt.dao.AdministradorDeConteudoDAO;
import br.com.ctrlt.dao.AlunoDAO;
import br.com.ctrlt.dao.ProfessorDAO;
import br.com.ctrlt.json.ResponseJson;
import br.com.ctrlt.model.AdministradorDeConteudo;
import br.com.ctrlt.model.Aluno;
import br.com.ctrlt.model.Professor;

@Controller
public class SenhaController {
	
	@Autowired
	private AdministradorDeConteudoDAO administradorDeConteudoDAO;
	
	@Autowired
	private ProfessorDAO professorDAO;
	
	@Autowired
	private AlunoDAO alunoDAO;
	
	@ResponseBody
	@RequestMapping(value = "rest/altera/senha", method = RequestMethod.POST)
	public ResponseJson alterarSenha(@RequestParam("senhaAtual") String senhaAtual, @RequestParam("novaSenha") String novaSenha, HttpServletRequest request){
		ResponseJson response = new ResponseJson();
		
		senhaAtual = new String(Base64.encodeBase64(senhaAtual.getBytes()));
		
		//É um administrador que está logado
		if(request.getSession().getAttribute("administradorLogado") != null){
			AdministradorDeConteudo administradorDeConteudo = administradorDeConteudoDAO.pesquisarPorId(((AdministradorDeConteudo) request.getSession().getAttribute("administradorLogado")).getId());
			
			//Verifica a senha atual
			if(administradorDeConteudo.getSenha().equals(senhaAtual)){
				
				//Atribui a nova senha
				administradorDeConteudo.setSenha(novaSenha);
				
				if(administradorDeConteudoDAO.alterar(administradorDeConteudo)){
					response.setStatus("SUCCESS");
					response.setResult("Senha alterada com sucesso!");
				}else{
					response.setStatus("FAIL");
					response.setResult("Ocorreu um erro ao alterar a senha, por gentileza contate o administrador do sistema.");
				}
			}else{
				response.setStatus("FAIL");
				response.setResult("Senha atual digitada é inválida! A senha não foi alterada.");
			}
		}else if(request.getSession().getAttribute("professorLogado") != null){ //É um professor logado
			Professor professor = professorDAO.pesquisarPorId(((Professor) request.getSession().getAttribute("professorLogado")).getId());
			
			//Verifica a senha atual
			if(professor.getSenha().equals(senhaAtual)){
				
				//Atribui a nova senha
				professor.setSenha(novaSenha);
				
				if(professorDAO.alterar(professor)){
					response.setStatus("SUCCESS");
					response.setResult("Senha alterada com sucesso!");
				}else{
					response.setStatus("FAIL");
					response.setResult("Ocorreu um erro ao alterar a senha, por gentileza contate o administrador do sistema.");
				}
			}else{
				response.setStatus("FAIL");
				response.setResult("Senha atual digitada é inválida! A senha não foi alterada.");
			}
		}else{ //É um aluno logado
			Aluno aluno = alunoDAO.pesquisarPorId(((Aluno) request.getSession().getAttribute("alunoLogado")).getId());
			
			//Verifica a senha atual
			if(aluno.getSenha().equals(senhaAtual)){
				
				//Atribui a nova senha
				aluno.setSenha(novaSenha);
				
				if(alunoDAO.alterar(aluno)){
					response.setStatus("SUCCESS");
					response.setResult("Senha alterada com sucesso!");
				}else{
					response.setStatus("FAIL");
					response.setResult("Ocorreu um erro ao alterar a senha, por gentileza contate o administrador do sistema.");
				}
			}else{
				response.setStatus("FAIL");
				response.setResult("Senha atual digitada é inválida! A senha não foi alterada.");
			}
		}
		
		return response;
	}
	
}
