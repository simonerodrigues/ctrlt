package br.com.ctrlt.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.ctrlt.dao.AdministradorDeConteudoDAO;
import br.com.ctrlt.model.AdministradorDeConteudo;

/**
 * Classe de controle de permissões de usuário Administrador
 * 
 * @author Simone Santos Rodrigues
 * @version 1.0
 */

public class AdmInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	AdministradorDeConteudoDAO administradorDeConteudoDAO;

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

		// Veridica se está acessando uma página que somente o administrador possui acesso
		if ((uri.contains("/adm/cadastro/") || uri.contains("adm/relatorio/") || uri.endsWith("dashboard")) && 
			request.getSession().getAttribute("administradorLogado") != null) {
			
			//Atualiza o administrador de conteúdo para atualizar os privilégios
			AdministradorDeConteudo administradorDeConteudo = administradorDeConteudoDAO.
					pesquisarPorId(((AdministradorDeConteudo) request.getSession().getAttribute("administradorLogado")).getId());
			
			//Verifica as permissões de cadastro
			if(uri.contains("/adm/cadastro/")){
				if(uri.endsWith("administrador_de_conteudo") && administradorDeConteudo.getPermissao().isManterAluno()){
					return true;
				}else if(uri.endsWith("aluno") && administradorDeConteudo.getPermissao().isManterAluno()){
					return true;
				}else if(uri.endsWith("curso") && administradorDeConteudo.getPermissao().isManterCurso()){
					return true;
				}else if(uri.endsWith("linha_de_pesquisa") && administradorDeConteudo.getPermissao().isManterLinhaDePesquisa()){
					return true;
				}else if(uri.endsWith("periodo") && administradorDeConteudo.getPermissao().isManterPeriodo()){
					return true;
				}else if(uri.endsWith("professor") && administradorDeConteudo.getPermissao().isManterProfessor()){
					return true;
				}else if(uri.endsWith("trabalho_de_conclusao") && administradorDeConteudo.getPermissao().isPublicarTCC()){
					return true;
				}else{
					response.sendRedirect(request.getContextPath() + "/acesso_negado");
					return false;
				}
			}
			
			if(uri.contains("adm/relatorio/")){
				if(! administradorDeConteudo.getPermissao().isExtrairRelatorio()){
					response.sendRedirect(request.getContextPath() + "/acesso_negado");
					return false;
				}
			}
			
			return true;
		}
		
		//Página de alterar senha
		if(uri.contains("alterar_senha") && (request.getSession().getAttribute("alunoLogado") != null ||
				request.getSession().getAttribute("professorLogado") != null ||
				request.getSession().getAttribute("administradorLogado") != null)){
			return true;
		}
		
		if((! (uri.contains("/adm/cadastro/") || uri.contains("adm/relatorio/") || uri.endsWith("dashboard"))) && 
				(request.getSession().getAttribute("alunoLogado") != null ||
				request.getSession().getAttribute("professorLogado") != null)){
			return true;
		}

		response.sendRedirect(request.getContextPath() + "/acesso_negado");
		return false;
	}
}