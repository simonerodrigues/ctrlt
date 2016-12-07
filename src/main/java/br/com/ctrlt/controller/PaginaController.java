package br.com.ctrlt.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ctrlt.dao.CursoDAO;
import br.com.ctrlt.dao.TrabalhoDeConclusaoDAO;
import br.com.ctrlt.interfaces.service.TrabalhoDeConclusaoService;
import br.com.ctrlt.model.Curso;
import br.com.ctrlt.model.TrabalhoDeConclusao;


@Controller
public class PaginaController {

	@Autowired
	private CursoDAO cursoDAO;
	
	@Autowired
	private TrabalhoDeConclusaoDAO trabalhoDeConclusaoDAO;
	
	@Autowired
	TrabalhoDeConclusaoService trabalhoDeConclusaoService;
	
	@RequestMapping(value = "adm/dashboard")
	public String indexADM(Model model){
		return "adm/index";
	}
	
	@RequestMapping(value = "adm/profile")
	public String profile(Model model){
		return "adm/profile";
	}
	
	@Transactional
	@RequestMapping(value = "galeria")
	public String indexGallery(Model model){
		
		model.addAttribute("ano", new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime()));
		
		List<Curso> listaCursos = cursoDAO.listar(" WHERE c.ativo = true ORDER BY c.nome");
		model.addAttribute("cursos", listaCursos);
		
		List<TrabalhoDeConclusao> trabalhosMaisBaixados = trabalhoDeConclusaoDAO.listarOsMaisBaixados(11);
		model.addAttribute("trabalhosMaisBaixados", trabalhosMaisBaixados);
		
		List<TrabalhoDeConclusao> trabalhosRecemAdicionados = trabalhoDeConclusaoDAO.listarOsRecemAdicionados(8);
		model.addAttribute("trabalhosRecemAdicionados", trabalhosRecemAdicionados);
		
		return "gallery/index";
	}	
	
	@Transactional
	@RequestMapping(value = "galeria/monografias/{pageNumber}")
	public String monografias(@PathVariable Integer pageNumber, HttpServletRequest request, Model model){
		
		model.addAttribute("ano", new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime()));
		
		List<Curso> listaCursos = cursoDAO.listar(" WHERE c.ativo = true ORDER BY c.nome");
		model.addAttribute("cursos", listaCursos);
		
		Page<TrabalhoDeConclusao> page = null;
		
		if(request.getParameter("s") == null && request.getParameter("c") == null){
			page = trabalhoDeConclusaoService.obterMonografias(pageNumber);
		}else if(request.getParameter("s") != null){
			page = trabalhoDeConclusaoService.pesquisarMonografia(pageNumber, request.getParameter("s").toString());
		}else{
			page = trabalhoDeConclusaoService.obeterMonografiasPorCurso(pageNumber, request.getParameter("c"));
		}
		
		int current = page.getNumber() + 1;
	    int begin = Math.max(1, current - 5);
	    int end = Math.min(begin + 10, page.getTotalPages());

	    model.addAttribute("deploymentLog", page);
	    model.addAttribute("beginIndex", begin);
	    model.addAttribute("endIndex", end);
	    model.addAttribute("currentIndex", current);
	    model.addAttribute("trabalhosDeConclusao", page.getContent());
		
		return "gallery/monografias";
	}	
	
	@RequestMapping(value = "acesso_negado")
	public String acessoNegado(Model model){
		model.addAttribute("ano", new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime()));
		
		return "adm/acesso_negado";
	}	
	
	@RequestMapping(value="login")
	public String loginForm(Model model){
		return "adm/autenticacao/login";
	}	
	
	@RequestMapping(value="sobre")
	public String sobreAdm(Model model){
		model.addAttribute("ano", new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime()));
		
		return "adm/sobre";
	}	
	
	@Transactional
	@RequestMapping(value="galeria/sobre")
	public String sobreGaleria(Model model){
		model.addAttribute("ano", new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime()));
		
		List<Curso> listaCursos = cursoDAO.listar(" WHERE c.ativo = true ORDER BY c.nome");
		model.addAttribute("cursos", listaCursos);
		
		return "gallery/sobre";
	}
	
	@RequestMapping(value="alterar_senha")
	public String alterarSenha(Model model){		
		return "adm/cadastros/alterar_senha";
	}	
	
}
