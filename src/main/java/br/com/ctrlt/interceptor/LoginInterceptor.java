package br.com.ctrlt.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Classe de controle de autenticação no sistema
 * 
 * @author Simone Santos Rodrigues
 * @version 1.0
 * 
 * Este sistema foi desenvolvido sob a licença GPL/GNU versão 3, onde qualquer pessoa poderá copiar e distribuir cópias sem alterações deste documento de licença.
 * Consulte mais informações sobre essa licença no arquivo gpl.txt que contêm na raiz do projeto.
 * 
 */

public class LoginInterceptor extends HandlerInterceptorAdapter {

	/**
	 * Método que faz a verificação de autenticação do sistema e direciona para as devidas páginas caso este ou não autenticado
	 * 
	 * @param request Request da página
	 * @param response Response da página
	 * @param controller Controlador
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
			throws Exception {

		String uri = request.getRequestURI();

		if ((uri.endsWith("login") || uri.endsWith("efetua_login") || uri.endsWith("galeria") ||  
			 uri.endsWith("reset_senha") || uri.endsWith("enviar_senha")) && 
			(request.getSession().getAttribute("administradorLogado") == null && 
			 request.getSession().getAttribute("professorLogado") == null &&
			 request.getSession().getAttribute("alunoLogado") == null
			)) {
			return true;
		}

		// Verifica se não está na tela de login e está logado
		if (!(uri.endsWith("login") || uri.endsWith("efetua_login")) && 
			(request.getSession().getAttribute("administradorLogado") != null ||
			 request.getSession().getAttribute("professorLogado") != null ||
			 request.getSession().getAttribute("alunoLogado") != null
			)) {
			return true;
		}

		// Verifica se está logado, porém está tentando acessar a tela de login
		if ((uri.endsWith("login") || uri.endsWith("efetua_login")) && 
			(request.getSession().getAttribute("administradorLogado") != null ||
			 request.getSession().getAttribute("professorLogado") != null ||
			 request.getSession().getAttribute("alunoLogado") != null
			)) {
			if(request.getSession().getAttribute("administradorLogado") != null){
				response.sendRedirect(request.getContextPath() + "/adm/dashboard");
			}else{
				response.sendRedirect(request.getContextPath() + "/adm/profile");
			}
			
			return false;
		}

		response.sendRedirect(request.getContextPath() + "/login");
		return false;
	}
}