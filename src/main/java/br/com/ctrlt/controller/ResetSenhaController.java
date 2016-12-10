package br.com.ctrlt.controller;

import java.math.BigInteger;
import java.security.SecureRandom;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
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

@Controller
public class ResetSenhaController {
	
	@Autowired
	AdministradorDeConteudoDAO administradorDeConteudoDAO;
	
	@Autowired
	AlunoDAO alunoDAO;
	
	@Autowired 
	ProfessorDAO professorDAO;
	
	@Autowired
    private JavaMailSender mailSender;
	
	@RequestMapping(value = "reset_senha", method = RequestMethod.GET)
	public String carregarPagina() {
		return "adm/autenticacao/reset_senha";
	}

	@RequestMapping(value = "enviar_senha", method = RequestMethod.POST)
	public @ResponseBody ResponseJson resetarSenha(HttpServletRequest request) throws MessagingException{
		
		ResponseJson response = new ResponseJson();
		
		String nome = "";
		String email = "";
		
		if(request.getParameter("email").isEmpty() || request.getParameter("tipo").isEmpty()){
			response.setStatus("FAIL");
			response.setResult("Por gentileza digite o e-mail que deseja recuperar a senha");
		}else{
			email = request.getParameter("email").toString();
		}
		
		SecureRandom random = new SecureRandom();
		String novaSenha = new BigInteger(130, random).toString(32);
		
		switch (request.getParameter("tipo")) {
			case "1":
				AdministradorDeConteudo administradorDeConteudo = administradorDeConteudoDAO.pesquisarPorEmail(email);
				
				if(administradorDeConteudo == null){
					response.setStatus("FAIL");
					response.setResult("Não foi encontrado nenhum administrador com este e-mail");
				}else{
					//Realiza o reset de senha do Administrador de Conteúdo
					administradorDeConteudo.setSenha(novaSenha);
					
					//Efetiva o reset de senha
					administradorDeConteudoDAO.alterar(administradorDeConteudo);
					
					nome = administradorDeConteudo.getNome();
				}
				
				break;
			case "2":
				Professor professor = professorDAO.pesquisarPorEmail(email);
				
				if(professor == null){
					response.setStatus("FAIL");
					response.setResult("Não foi encontrado nenhum professor com este e-mail");
				}else{
					//Realiza o reset de senha do Professor
					professor.setSenha(novaSenha);
					
					//Efetiva o reset de senha
					professorDAO.alterar(professor);
					
					nome = professor.getNome();
				}
				break;
			case "3":
				Aluno aluno = alunoDAO.pesquisarPorEmail(email);
				
				if(aluno == null){
					response.setStatus("FAIL");
					response.setResult("Não foi encontrado nenhum aluno com este e-mail");
				}else{
					//Realiza o reset de senha do Aluno
					aluno.setSenha(novaSenha);
					
					//Efetiva o reset de senha
					alunoDAO.alterar(aluno);
					
					nome = aluno.getNome();
				}
				break;
			default:
				response.setStatus("FAIL");
				response.setResult("Erro ao realizar o reset de senha, por gentileza contate o administrador do sistema!");
				break;
			}	
		
		//Envia o e-mail de reset de senha
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
         
        String body = "<p>Prezado(a) " + nome + ", <br /><br />"
        		+ "&emsp;Sua solicita&ccedil;&atilde;o de reset de senha foi atendida. Sua nova senha &eacute;: " + novaSenha + "<br /><br />"
        		+ "Atenciosamente,<br /><br />"
        		+ "Ctrl+T<p>";
        
        mimeMessage.setContent(body, "text/html");
        helper.setSubject("Ctrl+T - Reset de Senha");
        helper.setTo(email);
        
        // sends the e-mail
        mailSender.send(mimeMessage);
        
        response.setStatus("SUCCESS");
        response.setResult("Reset de senha realizado com sucesso. A senha foi enviada para o e-mail informado.");
        
        return response;
	}
	
}
