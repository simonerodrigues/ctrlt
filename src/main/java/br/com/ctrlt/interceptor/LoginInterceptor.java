package br.com.ctrlt.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Classe de controle de autentica��o no sistema
 * 
 * @author Simone Santos Rodrigues
 * @version 1.0
 */

public class LoginInterceptor extends HandlerInterceptorAdapter {

	/**
	 * M�todo que faz a verifica��o de autentica��o do sistema e direciona para as devidas p�ginas caso este ou n�o autenticado
	 * 
	 * @param request Request da p�gina
	 * @param response Response da p�gina
	 * @param controller Controlador
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
			throws Exception {

		String uri = request.getRequestURI();

		// Verifica se est� na tela de login e n�o est� logado
		if ((uri.endsWith("login") || uri.endsWith("efetua_login") || uri.endsWith("galeria")) && 
			(request.getSession().getAttribute("administradorLogado") == null && 
			 request.getSession().getAttribute("professorLogado") == null &&
			 request.getSession().getAttribute("alunoLogado") == null
			)) {
			return true;
		}

		// Verifica se n�o est� na tela de login e est� logado
		if (!(uri.endsWith("login") || uri.endsWith("efetua_login")) && 
			(request.getSession().getAttribute("administradorLogado") != null ||
			 request.getSession().getAttribute("professorLogado") != null ||
			 request.getSession().getAttribute("alunoLogado") != null
			)) {
			return true;
		}

		// Verifica se est� logado, por�m est� tentando acessar a tela de login
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