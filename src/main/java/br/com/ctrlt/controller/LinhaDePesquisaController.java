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

import br.com.ctrlt.dao.LinhaDePesquisaDAO;
import br.com.ctrlt.json.ResponseJson;
import br.com.ctrlt.json.TableResponseJson;
import br.com.ctrlt.model.LinhaDePesquisa;
import br.com.ctrlt.model.Professor;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class LinhaDePesquisaController implements Control<LinhaDePesquisa> {

	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
    ServletContext servletContext; 
	
	@Autowired
	private LinhaDePesquisaDAO linhaDePesquisaDAO;

	@Override
	@RequestMapping(value = "adm/cadastro/linha_de_pesquisa", method = RequestMethod.GET)
	public String carregarPagina(Model model) {
		return "adm/cadastros/cadastro_linhadepesquisa";
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "rest/cadastra/linha_de_pesquisa", method = RequestMethod.POST)
	public ResponseJson cadastrar(@Valid LinhaDePesquisa entidade, BindingResult result) {
		ResponseJson responseJson = new ResponseJson();

		if (!result.hasErrors()) {
			entidade.setAtivo(true);

			if (linhaDePesquisaDAO.cadastrar(entidade)) {
				responseJson.setStatus("SUCCESS");
				responseJson.setResult("Linha de Pesquisa cadastrada com sucesso.");
			} else {
				responseJson.setStatus("FAIL");
				responseJson.setResult("Erro ao cadastrar o linha de pesquisa. Por gentileza contate o administrador do sistema.");
			}
		} else {
			responseJson.setStatus("FAIL");

			String erros = "";

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
	@RequestMapping(value = "rest/altera/linha_de_pesquisa", method = RequestMethod.POST)
	public ResponseJson alterar(@Valid LinhaDePesquisa entidade, BindingResult result) {
		ResponseJson responseJson = new ResponseJson();

		if (!result.hasErrors()) {
			LinhaDePesquisa linhaDePesquisaBanco = linhaDePesquisaDAO.pesquisarPorId(entidade.getId());

			entidade.setAtivo(linhaDePesquisaBanco.isAtivo());

			if (linhaDePesquisaDAO.alterar(entidade)) {
				responseJson.setStatus("SUCCESS");
				responseJson.setResult("Linha de Pesquisa alterado com sucesso.");
			} else {
				responseJson.setStatus("FAIL");
				responseJson.setResult("Erro ao alterar o linha de pesquisa. Por gentileza contate o administrador do sistema.");
			}
		} else {
			responseJson.setStatus("FAIL");

			String erros = "";

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
	@RequestMapping(value = "rest/lista/linha_de_pesquisa", method = RequestMethod.POST)
	public TableResponseJson listar(HttpServletRequest req) {
		// Cria objeto de retorno do JSON
		TableResponseJson res = new TableResponseJson();

		List<LinhaDePesquisa> listaLinhaDePesquisas = linhaDePesquisaDAO.listar("");

		res.setData(listaLinhaDePesquisas);

		return res;
	}

	@Override
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "rest/exclui/linha_de_pesquisa")
	public ResponseJson excluir(HttpServletRequest req) {
		// Cria objeto de retorno do JSON
		ResponseJson response = new ResponseJson();

		// Pega o código do linhaDePesquisa que será excluido
		String id = req.getParameter("id");

		// Pega o objeto de linhaDePesquisa para pesquisar no banco
		LinhaDePesquisa linhaDePesquisa = linhaDePesquisaDAO.pesquisarPorId(Integer.parseInt(id));

		// Query para verificar se existe algum Professor associado com a linhaDePesquisa selecionada
		List<Professor> listaProfessores = linhaDePesquisaDAO
				.getEntityManager().createQuery("SELECT DISTINCT p FROM Professor p "
						+ " JOIN FETCH p.listaLinhaDePesquisa l " 
						+ " WHERE l = :linhaDePesquisa")
				.setParameter("linhaDePesquisa", linhaDePesquisa).getResultList();

		if (listaProfessores.size() > 0) {
			response.setStatus("FAIL");
			response.setResult("Não é possível excluir a linha de pesquisa selecionada porque existe(m) "
					+ "professor(es) associado(s) a esta linha de pesquisa. <br /><br />"
					+ "Por gentileza realize as desassociações antes de excluir a linha de pesquisa.");
		} else {

			// Realiza a exclusão do linhaDePesquisa
			if (linhaDePesquisaDAO.excluir(linhaDePesquisa)) {
				response.setStatus("SUCCESS");
				response.setResult("Linha de pesquisa excluída com sucesso.");
			} else {
				response.setStatus("FAIL");
				response.setResult("Erro ao excluir a linha de pesquisa. Por gentileza contate o administrador do sistema.");
			}
		}

		return response;
	}

	@Override
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "rest/inativa/linha_de_pesquisa", method = RequestMethod.POST)
	public ResponseJson inativar(HttpServletRequest req) {
		// Cria objeto de retorno do JSON
		ResponseJson response = new ResponseJson();

		// Pega o código do linhaDePesquisa que será inativado
		String id = req.getParameter("id");

		// Pega o objeto de linhaDePesquisa para alterar o status
		LinhaDePesquisa linhaDePesquisa = linhaDePesquisaDAO.pesquisarPorId(Integer.parseInt(id));

		// Query para verificar se existe algum Professor associado com a linhaDePesquisa selecionada
		List<Professor> listaProfessores = linhaDePesquisaDAO
				.getEntityManager().createQuery("SELECT DISTINCT p FROM Professor p "
						+ " JOIN FETCH p.listaLinhaDePesquisa l " 
						+ " WHERE l = :linhaDePesquisa AND "
						+ " p.ativo = :ativo")
				.setParameter("linhaDePesquisa", linhaDePesquisa)
				.setParameter("ativo", true).getResultList();

		if (listaProfessores.size() > 0) {
			response.setStatus("FAIL");
			response.setResult("Não é possível inativar essa linha de pesquisa selecionada porque existe(m) "
					+ "professor(es) ativo(s) associado(s) a esta linha de pesquisa. <br /><br />"
					+ "Por gentileza realize as desassociações antes de inativar a linha de pesquisa.");
		} else {

			// Altera o status do linhaDePesquisa
			if (linhaDePesquisa.isAtivo()) {
				linhaDePesquisa.setAtivo(false);
			} else {
				linhaDePesquisa.setAtivo(true);
			}

			// Grava as alterações realizadas com o linhaDePesquisa
			if (linhaDePesquisaDAO.alterar(linhaDePesquisa)) {
				response.setStatus("SUCCESS");

				if (linhaDePesquisa.isAtivo()) {
					response.setResult("Linha de pesquisa ativada com sucesso.");
				} else {
					response.setResult("Linha de pesquisa inativada com sucesso.");
				}
			} else {
				response.setStatus("FAIL");
				response.setResult("Erro ao ativar/inativar a linha de pesquisa. Por gentileza contate o administrador do sistema.");
			}
		}

		return response;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "rest/json/linha_de_pesquisa", method = RequestMethod.POST)
	public LinhaDePesquisa entidadeJSON(HttpServletRequest req) {
		// Pega o código do LinhaDePesquisa que será inativado
		String id = req.getParameter("id");

		// Pega o objeto de linhaDePesquisa para alterar o status
		LinhaDePesquisa linhaDePesquisa = linhaDePesquisaDAO.pesquisarPorId(Integer.parseInt(id));

		return linhaDePesquisa;
	}

	@RequestMapping(value = "adm/relatorio/pdf/linha_de_pesquisa", method = RequestMethod.GET)
	public ModelAndView gerarRelatorio(ModelAndView modelAndView) {
		List<LinhaDePesquisa> listaLinhaDePesquisa = linhaDePesquisaDAO.listar(" ORDER BY l.nome");

		//Criação da DataSouce do iReport
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(listaLinhaDePesquisa, false);
		
		//Map de parâmetros a serem passados para o iReport
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", JRdataSource);
		parameterMap.put("path", servletContext.getRealPath("/images/reports/"));
		
		Map<String, Object> exporterParameters = new HashMap<String, Object>();
		
		//Propriedades do Header da Response
		Properties header = new Properties();
		
		//Nome do arquivo caso o usuário de Ctrl+S (Salvar)
		header.put("Content-Disposition", "inline; filename=Linha De Pesquisa.pdf");
		
		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setUrl("/WEB-INF/reports/pdf/linha_de_pesquisa.jrxml");
		view.setReportDataKey("datasource");
		view.setExporterParameters(exporterParameters);
		view.setApplicationContext(applicationContext);
		view.setHeaders(header);
		
		// Gera o relatório de acordo com a extensao enviada por parâmetro de URL
		modelAndView = new ModelAndView(view, parameterMap);
		return modelAndView;
	}

}
