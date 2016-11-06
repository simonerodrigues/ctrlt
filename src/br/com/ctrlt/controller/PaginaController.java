package br.com.ctrlt.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PaginaController {

	@RequestMapping(value = "adm/dashboard")
	public String indexADM(Model model){
		return "adm/index";
	}
	
	@RequestMapping(value = "adm/profile")
	public String profile(Model model){
		return "adm/profile";
	}
	
	@RequestMapping(value = "galeria")
	public String indexGallery(Model model){
		return "gallery/index";
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
	public String sobre(Model model){
		model.addAttribute("ano", new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime()));
		
		return "adm/sobre";
	}	
	
	@RequestMapping(value="alterar_senha")
	public String alterarSenha(Model model){		
		return "adm/cadastros/alterar_senha";
	}	
	
}
