package br.com.ctrlt.controller;

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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import br.com.ctrlt.dao.AlunoDAO;
import br.com.ctrlt.dao.ProfessorDAO;
import br.com.ctrlt.dao.TrabalhoDeConclusaoDAO;
import br.com.ctrlt.json.ResponseJson;
import br.com.ctrlt.json.TableResponseJson;
import br.com.ctrlt.model.TrabalhoDeConclusao;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class TrabalhoDeConclusaoController implements Control<TrabalhoDeConclusao> {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
    private ServletContext servletContext; 
	
	@Autowired
	private TrabalhoDeConclusaoDAO trabalhoDeConclusaoDAO;

	@Autowired
	private AlunoDAO alunoDAO;
	
	@Autowired
	private ProfessorDAO professorDAO;
	
	@Override
	@RequestMapping(value = "adm/cadastro/trabalho_de_conclusao", method = RequestMethod.GET)
	public String carregarPagina(Model model) {
		model.addAttribute("alunos", alunoDAO.listar(" WHERE a.trabalhoDeConclusao = null AND a.ativo = true"));
		model.addAttribute("professores", professorDAO.listar(" WHERE p.ativo = true"));
		
		return "adm/cadastros/cadastro_trabalho_de_conclusao";
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "rest/cadastra/trabalho_de_conclusao", method = RequestMethod.POST)
	public ResponseJson cadastrar(@Valid TrabalhoDeConclusao entidade, BindingResult result) {
		ResponseJson responseJson = new ResponseJson();

		if (!result.hasErrors()) {
			entidade.setAtivo(true);

			if (trabalhoDeConclusaoDAO.cadastrar(entidade)) {
				responseJson.setStatus("SUCCESS");
				responseJson.setResult("Trabalho de conclusão com sucesso.");
			} else {
				responseJson.setStatus("FAIL");
				responseJson.setResult("Erro ao cadastrar o trabalho de conclusão. Por gentileza contate o administrador do sistema.");
			}
		} else {
			responseJson.setStatus("FAIL");

			String erros = "";

			if (result.getErrorCount() == 1) {
				erros = "O seguinte erro foi apresentado durante a validação dos dados: \n";
			} else {
				erros = "Os seguintes erros foram apresentados durante a validação dos dados: \n";
			}

			for (ObjectError erro : result.getAllErrors()) {
				erros += "\n" + erro.getDefaultMessage();
			}

			responseJson.setResult(erros);
		}

		return responseJson;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "rest/altera/trabalho_de_conclusao", method = RequestMethod.POST)
	public ResponseJson alterar(@Valid TrabalhoDeConclusao entidade, BindingResult result) {
		ResponseJson responseJson = new ResponseJson();

		if (!result.hasErrors()) {
			TrabalhoDeConclusao linhaDePesquisaBanco = trabalhoDeConclusaoDAO.pesquisarPorId(entidade.getId());

			entidade.setAtivo(linhaDePesquisaBanco.isAtivo());

			if (trabalhoDeConclusaoDAO.alterar(entidade)) {
				responseJson.setStatus("SUCCESS");
				responseJson.setResult("Trabalho de conclusão alterado com sucesso.");
			} else {
				responseJson.setStatus("FAIL");
				responseJson.setResult("Erro ao alterar o trabalho de conclusão. Por gentileza contate o administrador do sistema.");
			}
		} else {
			responseJson.setStatus("FAIL");

			String erros = "";

			if (result.getErrorCount() == 1) {
				erros = "O seguinte erro foi apresentado durante a validação dos dados: \n";
			} else {
				erros = "Os seguintes erros foram apresentados durante a validação dos dados: \n";
			}

			for (ObjectError erro : result.getAllErrors()) {
				erros += "\n" + erro.getDefaultMessage();
			}

			responseJson.setResult(erros);
		}

		return responseJson;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "rest/lista/trabalho_de_conclusao", method = RequestMethod.POST)
	public TableResponseJson listar(HttpServletRequest req) {
		// Cria objeto de retorno do JSON
		TableResponseJson res = new TableResponseJson();

		List<TrabalhoDeConclusao> listaTrabalhoDeConclusaos = trabalhoDeConclusaoDAO.listar("");

		res.setData(listaTrabalhoDeConclusaos);

		return res;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "rest/exclui/trabalho_de_conclusao")
	public ResponseJson excluir(HttpServletRequest req) {
		// Cria objeto de retorno do JSON
		ResponseJson response = new ResponseJson();

		// Pega o código do linhaDePesquisa que será excluido
		String id = req.getParameter("id");

		// Pega o objeto de linhaDePesquisa para pesquisar no banco
		TrabalhoDeConclusao linhaDePesquisa = trabalhoDeConclusaoDAO.pesquisarPorId(Integer.parseInt(id));

		// Realiza a exclusão do linhaDePesquisa
		if (trabalhoDeConclusaoDAO.excluir(linhaDePesquisa)) {
			response.setStatus("SUCCESS");
			response.setResult("Trabalho de conclusão excluída com sucesso.");
		} else {
			response.setStatus("FAIL");
			response.setResult("Erro ao excluir a trabalho de conclusão. Por gentileza contate o administrador do sistema.");
		}
		
		return response;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "rest/inativa/trabalho_de_conclusao", method = RequestMethod.POST)
	public ResponseJson inativar(HttpServletRequest req) {
		// Cria objeto de retorno do JSON
		ResponseJson response = new ResponseJson();

		// Pega o código do linhaDePesquisa que será inativado
		String id = req.getParameter("id");

		// Pega o objeto de linhaDePesquisa para alterar o status
		TrabalhoDeConclusao linhaDePesquisa = trabalhoDeConclusaoDAO.pesquisarPorId(Integer.parseInt(id));

		// Altera o status do linhaDePesquisa
		if (linhaDePesquisa.isAtivo()) {
			linhaDePesquisa.setAtivo(false);
		} else {
			linhaDePesquisa.setAtivo(true);
		}

		// Grava as alterações realizadas com o linhaDePesquisa
		if (trabalhoDeConclusaoDAO.alterar(linhaDePesquisa)) {
			response.setStatus("SUCCESS");

			if (linhaDePesquisa.isAtivo()) {
				response.setResult("Trabalho de conclusão ativada com sucesso.");
			} else {
				response.setResult("Trabalho de conclusão inativada com sucesso.");
			}
		} else {
			response.setStatus("FAIL");
			response.setResult("Erro ao ativar/inativar o trabalho de conclusão. Por gentileza contate o administrador do sistema.");
		}
		
		return response;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "rest/json/trabalho_de_conclusao", method = RequestMethod.POST)
	public TrabalhoDeConclusao entidadeJSON(HttpServletRequest req) {
		// Pega o código do TrabalhoDeConclusao que será inativado
		String id = req.getParameter("id");

		// Pega o objeto de linhaDePesquisa para alterar o status
		TrabalhoDeConclusao linhaDePesquisa = trabalhoDeConclusaoDAO.pesquisarPorId(Integer.parseInt(id));

		return linhaDePesquisa;
	}

	@RequestMapping(value = "adm/relatorio/pdf/trabalho_de_conclusao", method = RequestMethod.GET)
	public ModelAndView gerarRelatorio(ModelAndView modelAndView) {
		List<TrabalhoDeConclusao> listaTrabalhoDeConclusao = trabalhoDeConclusaoDAO.listar(" ORDER BY l.nome");

		//Criação da DataSouce do iReport
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(listaTrabalhoDeConclusao, false);
		
		//Map de parâmetros a serem passados para o iReport
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", JRdataSource);
		parameterMap.put("path", servletContext.getRealPath("/images/reports/"));
		
		Map<String, Object> exporterParameters = new HashMap<String, Object>();
		
		//Propriedades do Header da Response
		Properties header = new Properties();
		
		//Nome do arquivo caso o usuário de Ctrl+S (Salvar)
		header.put("Content-Disposition", "inline; filename=Trabalho de Conclusao.pdf");
		
		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setUrl("/WEB-INF/reports/pdf/trabalho_de_conclusao.jrxml");
		view.setReportDataKey("datasource");
		view.setExporterParameters(exporterParameters);
		view.setApplicationContext(applicationContext);
		view.setHeaders(header);
		
		// Gera o relatório de acordo com a extensao enviada por parâmetro de URL
		modelAndView = new ModelAndView(view, parameterMap);
		return modelAndView;
	}

}
