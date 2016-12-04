package br.com.ctrlt.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import br.com.ctrlt.dao.LinhaDePesquisaDAO;
import br.com.ctrlt.dao.ProfessorDAO;
import br.com.ctrlt.json.ResponseJson;
import br.com.ctrlt.json.TableResponseJson;
import br.com.ctrlt.model.LinhaDePesquisa;
import br.com.ctrlt.model.Professor;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class ProfessorController implements Control<Professor> {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
    private ServletContext servletContext; 
	
	@Autowired
	private ProfessorDAO professorDAO;
	
	@Autowired
	private LinhaDePesquisaDAO linhaDePesquisaDAO;
	
	@Override
	@Transactional
	@RequestMapping(value = "adm/cadastro/professor", method = RequestMethod.GET)
	public String carregarPagina(Model model) {
		model.addAttribute("linhasDePesquisa", linhaDePesquisaDAO.listar(" WHERE l.ativo = true ORDER BY nome ASC"));
		
		return "adm/cadastros/cadastro_professor";
	}

	@Override
	public ResponseJson cadastrar(@Valid Professor entidade, BindingResult result) {
		return null;
	}
	
	@ResponseBody
	@Transactional
	@RequestMapping(value = "rest/cadastra/professor", method = RequestMethod.POST)
	public ResponseJson cadastrar(@Valid Professor entidade, Long[] linhasDePesquisa, BindingResult result) {
		ResponseJson responseJson = new ResponseJson();

		String erros = "";
		
		if (!result.hasErrors()) {
			//Valida dados que não podem ser repetidos na base
			boolean loginExistente = professorDAO.verificarLoginExistente(entidade);
			boolean emailAlternativoExistente = professorDAO.verificarEmailAlternativoExistente(entidade);
			boolean emailFatecExistente = professorDAO.verificarEmailFatecExistente(entidade);
			
			int numeroErros = 0;
			
			if(loginExistente){
				erros += "<br />" + "Login de professor já cadastrado no sistema";
				numeroErros++;
			}
			
			if(emailAlternativoExistente){
				erros += "<br />" + "E-mail alternativo de professor já cadastrado no sistema";
				numeroErros++;
			}
			
			if(emailFatecExistente){
				erros += "<br />" + "E-mail Fatec de professor já cadastrado no sistema";
				numeroErros++;
			}
			
			if(numeroErros > 0){
				if(numeroErros == 1){
					erros = "O seguinte erro foi apresentado durante a validação dos dados: <br />" + erros;
				}else{
					erros = "Os seguintes erros foram apresentados durante a validação dos dados: <br />" + erros;
				}
				
				responseJson.setStatus("FAIL");
				responseJson.setResult(erros);
				
				return responseJson;
			}
			
			entidade.setAtivo(true);
			
			//Preenche as linhas de Pesquisa
			if(linhasDePesquisa.length > 0){
				List<LinhaDePesquisa> listaLinhaDePesqusia = new ArrayList<>();
				
				for(int i = 0; i < linhasDePesquisa.length; i++){
					LinhaDePesquisa linhaDePesquisa = linhaDePesquisaDAO.pesquisarPorId(linhasDePesquisa[i]);
					listaLinhaDePesqusia.add(linhaDePesquisa);
				}
				
				entidade.setListaLinhaDePesquisa(listaLinhaDePesqusia);
			}
			
			if (professorDAO.cadastrar(entidade)) {
				responseJson.setStatus("SUCCESS");
				responseJson.setResult("Professor cadastrado com sucesso.");
			} else {
				responseJson.setStatus("FAIL");
				responseJson.setResult("Erro ao cadastrar o professor. Por gentileza contate o administrador do sistema.");
			}
		} else {
			responseJson.setStatus("FAIL");

			if (result.getErrorCount() == 1) {
				erros = "O seguinte erro foi apresentado durante a validação dos dados: <br />";
			} else {
				erros = "Os seguintes erros foram apresentados durante a validação dos dados: <br />";
			}

			for (ObjectError erro : result.getAllErrors()) {
				erros += "<br />" + erro.getDefaultMessage();
			}

			responseJson.setResult(erros);
		}

		return responseJson;
	}

	@Override
	public ResponseJson alterar(@Valid Professor entidade, BindingResult result) {
		return null;
	}
	
	@ResponseBody
	@Transactional
	@RequestMapping(value = "rest/altera/professor", method = RequestMethod.POST)
	public ResponseJson alterarCustom(@Valid Professor entidade, Long[] linhasDePesquisa, BindingResult result) {
		ResponseJson responseJson = new ResponseJson();

		String erros = "";
		
		if (!result.hasErrors()) {			
			//Valida dados que não podem ser repetidos na base
			boolean loginExistente = professorDAO.verificarLoginExistente(entidade);
			boolean emailAlternativoExistente = professorDAO.verificarEmailAlternativoExistente(entidade);
			boolean emailFatecExistente = professorDAO.verificarEmailFatecExistente(entidade);
			
			int numeroErros = 0;
			
			if(loginExistente){
				erros += "<br />" + "Login de professor já cadastrado no sistema";
				numeroErros++;
			}
			
			if(emailAlternativoExistente){
				erros += "<br />" + "E-mail alternativo de professor já cadastrado no sistema";
				numeroErros++;
			}
			
			if(emailFatecExistente){
				erros += "<br />" + "E-mail Fatec de professor já cadastrado no sistema";
				numeroErros++;
			}
			
			if(numeroErros > 0){
				if(numeroErros == 1){
					erros = "O seguinte erro foi apresentado durante a validação dos dados: <br />" + erros;
				}else{
					erros = "Os seguintes erros foram apresentados durante a validação dos dados: <br />" + erros;
				}
				
				responseJson.setStatus("FAIL");
				responseJson.setResult(erros);
				
				return responseJson;
			}
			
			Professor professorBanco = professorDAO.pesquisarPorId(entidade.getId());
			
			//Seta a senha já codificada
			entidade.setSenhaCoded(professorBanco.getSenha());
			entidade.setAtivo(professorBanco.isAtivo());
			
			//Preenche as linhas de Pesquisa
			if(linhasDePesquisa.length > 0){
				List<LinhaDePesquisa> listaLinhaDePesqusia = new ArrayList<>();
				
				for(int i = 0; i < linhasDePesquisa.length; i++){
					LinhaDePesquisa linhaDePesquisa = linhaDePesquisaDAO.pesquisarPorId(linhasDePesquisa[i]);
					listaLinhaDePesqusia.add(linhaDePesquisa);
				}
				
				entidade.setListaLinhaDePesquisa(listaLinhaDePesqusia);
			}
			
			if (professorDAO.alterar(entidade)) {
				responseJson.setStatus("SUCCESS");
				responseJson.setResult("Professor alterado com sucesso.");
			} else {
				responseJson.setStatus("FAIL");
				responseJson.setResult("Erro ao alterar o professor. Por gentileza contate o administrador do sistema.");
			}
		} else {
			responseJson.setStatus("FAIL");

			if (result.getErrorCount() == 1) {
				erros = "O seguinte erro foi apresentado durante a validação dos dados: <br />";
			} else {
				erros = "Os seguintes erros foram apresentados durante a validação dos dados: <br />";
			}

			for (ObjectError erro : result.getAllErrors()) {
				erros += "<br />" + erro.getDefaultMessage();
			}

			responseJson.setResult(erros);
		}

		return responseJson;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "rest/lista/professor", method = RequestMethod.POST)
	public TableResponseJson listar(HttpServletRequest req) {
		// Cria objeto de retorno do JSON
		TableResponseJson res = new TableResponseJson();

		List<Professor> listaProfessores = professorDAO.listar("");

		res.setData(listaProfessores);

		return res;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "rest/exclui/professor", method = RequestMethod.POST)
	public ResponseJson excluir(HttpServletRequest req) {
		// Cria objeto de retorno do JSON
		ResponseJson responseJson = new ResponseJson();

		// Pega o código do professor que será excluido
		String id = req.getParameter("id");

		// Pega o objeto de professor para pesquisar no banco
		Professor professor = professorDAO.pesquisarPorId(Integer.parseInt(id));

		if(professor.getListaTrabalhoDeConclusao().size() == 0){
			// Realiza a exclusão do professor
			if (professorDAO.excluir(professor)) {
				responseJson.setStatus("SUCCESS");
				responseJson.setResult("Professor excluído com sucesso.");
			} else {
				responseJson.setStatus("FAIL");
				responseJson.setResult("Erro ao excluir o professor. Por gentileza contate o administrador do sistema.");
			}
		}else{
			responseJson.setStatus("FAIL");
			responseJson.setResult("Não é possível exluir o professor, o mesmo está vinculado a um ou mais trabalho(s) de conclusão. Por gentileza realize a desassociação antes de excluí-lo.");
		}
		
		return responseJson;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "rest/inativa/professor", method = RequestMethod.POST)
	public ResponseJson inativar(HttpServletRequest req) {
		// Cria objeto de retorno do JSON
		ResponseJson responseJson = new ResponseJson();

		// Pega o código do professor que será inativado
		String id = req.getParameter("id");

		// Pega o objeto de professor para alterar o status
		Professor professor = professorDAO.pesquisarPorId(Integer.parseInt(id));

		// Altera o status do professor
		if (professor.isAtivo()) {
			professor.setAtivo(false);
		} else {
			professor.setAtivo(true);
		}

		// Grava as alterações realizadas com o professor
		if (professorDAO.alterar(professor)) {
			responseJson.setStatus("SUCCESS");

			if (professor.isAtivo()) {
				responseJson.setResult("Professor ativado com sucesso.");
			} else {
				responseJson.setResult("Professor inativado com sucesso.");
			}
		} else {
			responseJson.setStatus("FAIL");
			responseJson.setResult("Erro ao ativar/inativar o professor. Por gentileza contate o administrador do sistema.");
		}
		
		return responseJson;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "rest/json/professor", method = RequestMethod.POST)
	public Professor entidadeJSON(HttpServletRequest req) {
		// Pega o código do Professor que será inativado
		String id = req.getParameter("id");

		// Pega o objeto de professor para alterar o status
		Professor professor = professorDAO.pesquisarPorId(Integer.parseInt(id));

		return professor;
	}

	@Transactional
	@RequestMapping(value = "adm/relatorio/pdf/professor", method = RequestMethod.GET)
	public ModelAndView gerarRelatorio(ModelAndView modelAndView) {
		List<Professor> listaProfessores = professorDAO.listar(" ORDER BY p.nome");

		//Criação da DataSouce do iReport
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(listaProfessores, false);
		
		//Map de parâmetros a serem passados para o iReport
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", JRdataSource);
		parameterMap.put("path", servletContext.getRealPath("/images/reports/"));
		
		Map<String, Object> exporterParameters = new HashMap<String, Object>();
		
		//Propriedades do Header da Response
		Properties header = new Properties();
		
		//Nome do arquivo caso o usuário de Ctrl+S (Salvar)
		header.put("Content-Disposition", "inline; filename=Professores.pdf");
		
		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setUrl("/WEB-INF/reports/pdf/professor.jrxml");
		view.setReportDataKey("datasource");
		view.setExporterParameters(exporterParameters);
		view.setApplicationContext(applicationContext);
		view.setHeaders(header);
		
		// Gera o relatório de acordo com a extensao enviada por parâmetro de URL
		modelAndView = new ModelAndView(view, parameterMap);
		return modelAndView;
	}

}
