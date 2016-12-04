package br.com.ctrlt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import br.com.ctrlt.json.ResponseJson;
import br.com.ctrlt.json.TableResponseJson;

public interface Control<Entidade> {
	//Método que devolve o nome da página JSP que irá ser aberta pelo @RequestMapping
	public String carregarPagina(Model model);
	
	//Método de controle para cadastrar as entidades
	public ResponseJson cadastrar(Entidade entidade, BindingResult result);

	//Método de controle para alterar as entidades
	public ResponseJson alterar(Entidade entidade, BindingResult result);
	
	//Método de controle para listar as entidades
	public TableResponseJson listar(HttpServletRequest req);
	
	//Método de controle para excluir as entidades
	public ResponseJson excluir(HttpServletRequest req);
	
	//Método de controle para inativar as entidades
	public ResponseJson inativar(HttpServletRequest req);
	
	//Método de controle para retornar a entidade em JSON
	public Entidade entidadeJSON(HttpServletRequest req);
	
	//Método de controle para gerar os relatórios
	public ModelAndView gerarRelatorio(ModelAndView modelAndView);
	
}
