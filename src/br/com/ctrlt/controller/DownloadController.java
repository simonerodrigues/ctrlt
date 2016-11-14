package br.com.ctrlt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ctrlt.dao.TrabalhoDeConclusaoDAO;
import br.com.ctrlt.model.Anexo;
import br.com.ctrlt.model.TrabalhoDeConclusao;

@Controller
public class DownloadController {
	
	@Autowired
	private TrabalhoDeConclusaoDAO trabalhoDeConclusaoDAO;

	@RequestMapping(value = "/download/monografias/{idTcc}")
	public String controleDownloadMonografia(@PathVariable("idTcc") Long idTcc){
		
		TrabalhoDeConclusao trabalhoDeConclusao = trabalhoDeConclusaoDAO.pesquisarPorId(idTcc);
		
		trabalhoDeConclusao.getMonografia().setNumeroDownloads(trabalhoDeConclusao.getMonografia().getNumeroDownloads() + 1);
		
		trabalhoDeConclusaoDAO.alterar(trabalhoDeConclusao);
		
		return "redirect:/monografias/" + trabalhoDeConclusao.getId() + "/" +trabalhoDeConclusao.getMonografia().getNome(); 
	}
	
	@RequestMapping(value = "/download/anexos/{idTcc}/{idAnexo}")
	public String controleDownloadMonografia(@PathVariable("idTcc") Long idTcc, @PathVariable("idAnexo") Long idAnexo){
		
		TrabalhoDeConclusao trabalhoDeConclusao = trabalhoDeConclusaoDAO.pesquisarPorId(idTcc);
		
		Anexo anexo = null;
		
		for(int i = 0; i < trabalhoDeConclusao.getListaAnexos().size(); i++){
			if(trabalhoDeConclusao.getListaAnexos().get(i).getId() == idAnexo){
				anexo = trabalhoDeConclusao.getListaAnexos().get(i);
				trabalhoDeConclusao.getListaAnexos().get(i).setNumeroDownloads(trabalhoDeConclusao.getListaAnexos().get(i).getNumeroDownloads() + 1);
			}
		}
		
		trabalhoDeConclusaoDAO.alterar(trabalhoDeConclusao);
		
		return "redirect:/anexos/" + trabalhoDeConclusao.getId() + "/" + anexo.getId()  + "/" + anexo.getNome(); 
	}
	
}
