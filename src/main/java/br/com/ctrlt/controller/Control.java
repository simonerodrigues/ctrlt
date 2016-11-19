package br.com.ctrlt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import br.com.ctrlt.json.ResponseJson;
import br.com.ctrlt.json.TableResponseJson;

public interface Control<Entidade> {
	//M�todo que devolve o nome da p�gina JSP que ir� ser aberta pelo @RequestMapping
	public String carregarPagina(Model model);
	
	//M�todo de controle para cadastrar as entidades
	public ResponseJson cadastrar(Entidade entidade, BindingResult result);

	//M�todo de controle para alterar as entidades
	public ResponseJson alterar(Entidade entidade, BindingResult result);
	
	//M�todo de controle para listar as entidades
	public TableResponseJson listar(HttpServletRequest req);
	
	//M�todo de controle para excluir as entidades
	public ResponseJson excluir(HttpServletRequest req);
	
	//M�todo de controle para inativar as entidades
	public ResponseJson inativar(HttpServletRequest req);
	
	//M�todo de controle para retornar a entidade em JSON
	public Entidade entidadeJSON(HttpServletRequest req);
	
	//M�todo de controle para gerar os relat�rios
	public ModelAndView gerarRelatorio(ModelAndView modelAndView);
	
}
