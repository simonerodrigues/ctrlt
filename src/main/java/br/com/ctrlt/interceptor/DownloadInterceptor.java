package br.com.ctrlt.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.ctrlt.dao.AnexoDAO;
import br.com.ctrlt.dao.TrabalhoDeConclusaoDAO;
import br.com.ctrlt.model.Anexo;
import br.com.ctrlt.model.TrabalhoDeConclusao;

/**
 * Classe de controle de permissão de download
 * 
 * @author Simone Santos Rodrigues
 * @version 1.0
 */

public class DownloadInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	TrabalhoDeConclusaoDAO trabalhoDeConclusaoDAO;
	
	@Autowired 
	AnexoDAO anexoDAO;

	/**
	 * Método que faz a verificação de acesso direto ao link do arquivo (Realiza o bloqueio)
	 * 
	 * @param request Request da página
	 * @param response Response da página
	 * @param controller Controlador
	 */
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
			throws Exception {

		String uri = request.getRequestURI();

		if(uri.contains("/monografias/")){
			Long idTcc = 0l;
			
			try{
				if(request.getContextPath().isEmpty()){
					idTcc   = Long.parseLong(uri.split("/")[2]);
				}else{
					idTcc   = Long.parseLong(uri.split("/")[3]);
				}
			}catch(Exception e){
				 e.printStackTrace();
			}
			
			if(request.getSession().getAttribute("administradorLogado") != null){
				return true;
			}
			
			TrabalhoDeConclusao trabalhoDeConclusao = trabalhoDeConclusaoDAO.pesquisarPorId(idTcc);
			
			if(trabalhoDeConclusao != null && trabalhoDeConclusao.getMonografia() != null && trabalhoDeConclusao.getMonografia().getId() > 0){
				if(trabalhoDeConclusao.isAtivo() && trabalhoDeConclusao.getMonografia().isAtivo()){
					trabalhoDeConclusao.getMonografia().setNumeroDownloads(trabalhoDeConclusao.getMonografia().getNumeroDownloads() + 1);
					
					trabalhoDeConclusaoDAO.alterar(trabalhoDeConclusao);
					
					return true;
				}else{
					request.getRequestDispatcher("/WEB-INF/view/gallery/download_bloqueado.jsp").forward(request, response);
				}
			}else{
				request.getRequestDispatcher("/WEB-INF/view/gallery/arquivo_nao_encontrado.jsp").forward(request, response);
			}	
		}else if(uri.contains("/anexos/")){
			
			Long idTcc = 0l;
			Long idAnexo = 0l;
			
			try{
				idTcc   = Long.parseLong(uri.split("/")[2]);
				idAnexo = Long.parseLong(uri.split("/")[3]);
			}catch(Exception e){
				 e.printStackTrace();
			}
			
			TrabalhoDeConclusao trabalhoDeConclusao = trabalhoDeConclusaoDAO.pesquisarPorId(idTcc);
			Anexo anexo = anexoDAO.pesquisarPorId(idAnexo);
			
			if(request.getSession().getAttribute("administradorLogado") != null){
				return true;
			}
			
			
			
			if(trabalhoDeConclusao != null && anexo != null){
				if(trabalhoDeConclusao.isAtivo() && anexo.isAtivo()){
					
					for(int i = 0; i < trabalhoDeConclusao.getListaAlunos().size(); i++){
						if(trabalhoDeConclusao.getListaAnexos().get(i).getId() == anexo.getId()){
							trabalhoDeConclusao.getListaAnexos().get(i).setNumeroDownloads(trabalhoDeConclusao.getListaAnexos().get(i).getNumeroDownloads() + 1);
							
							trabalhoDeConclusaoDAO.alterar(trabalhoDeConclusao);
							
							break;
						}
					}
					
					return true;
				}else{
					request.getRequestDispatcher("/WEB-INF/view/gallery/download_bloqueado.jsp").forward(request, response);
				}
			}else{
				request.getRequestDispatcher("/WEB-INF/view/gallery/arquivo_nao_encontrado.jsp").forward(request, response);
			}	
		}
		
		return false; 
		
	}
	
}