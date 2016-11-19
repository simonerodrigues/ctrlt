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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import br.com.ctrlt.dao.AlunoDAO;
import br.com.ctrlt.dao.CursoDAO;
import br.com.ctrlt.dao.PeriodoDAO;
import br.com.ctrlt.json.ResponseJson;
import br.com.ctrlt.json.TableResponseJson;
import br.com.ctrlt.model.Aluno;
import br.com.ctrlt.model.Curso;
import br.com.ctrlt.model.Periodo;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class CursoController implements Control<Curso> {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
    private ServletContext servletContext; 
	
	@Autowired
	private CursoDAO cursoDAO;
	
	@Autowired
	private PeriodoDAO periodoDAO;
	
	@Autowired
	private AlunoDAO alunoDAO;
	
	@Override
	@RequestMapping(value = "adm/cadastro/curso", method = RequestMethod.GET)
	public String carregarPagina(Model model) {		
		model.addAttribute("periodos", periodoDAO.listar(" WHERE p.ativo = 1 ORDER BY p.id ASC"));
		
		return "adm/cadastros/cadastro_curso";
	}

	@Override
	public ResponseJson cadastrar(@Valid Curso entidade, BindingResult result) {
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "rest/cadastra/curso", method = RequestMethod.POST)
	public ResponseJson cadastrar(@Valid Curso entidade, Long[] periodos, BindingResult result) {
		ResponseJson responseJson = new ResponseJson();

		if (!result.hasErrors()) {
			entidade.setAtivo(true);
			
			//Preenche as linhas de Pesquisa
			if(periodos.length > 0){
				List<Periodo> listaPeriodo = new ArrayList<>();
				
				for(int i = 0; i < periodos.length; i++){
					Periodo periodo = periodoDAO.pesquisarPorId(periodos[i]);
					listaPeriodo.add(periodo);
				}
				
				entidade.setListaPeriodo(listaPeriodo);
			}
			
			if (cursoDAO.cadastrar(entidade)) {
				responseJson.setStatus("SUCCESS");
				responseJson.setResult("Curso cadastrado com sucesso.");
			} else {
				responseJson.setStatus("FAIL");
				responseJson.setResult("Erro ao cadastrar o curso. Por gentileza contate o administrador do sistema.");
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
	public ResponseJson alterar(@Valid Curso entidade, BindingResult result) {
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "rest/altera/curso", method = RequestMethod.POST)
	public ResponseJson alterar(@Valid Curso entidade, Long[] periodos, BindingResult result) {
		ResponseJson responseJson = new ResponseJson();

		if (!result.hasErrors()) {
			Curso cursoBanco = cursoDAO.pesquisarPorId(entidade.getId());
			
			entidade.setAtivo(cursoBanco.isAtivo());
			
			//Preenche as linhas de Pesquisa
			if(periodos.length > 0){
				List<Periodo> listaPeriodo = new ArrayList<>();
				
				for(int i = 0; i < periodos.length; i++){
					Periodo periodo = periodoDAO.pesquisarPorId(periodos[i]);
					listaPeriodo.add(periodo);
				}
				
				entidade.setListaPeriodo(listaPeriodo);
			}
			
			if (cursoDAO.alterar(entidade)) {
				responseJson.setStatus("SUCCESS");
				responseJson.setResult("Curso alterado com sucesso.");
			} else {
				responseJson.setStatus("FAIL");
				responseJson.setResult("Erro ao alterar o curso. Por gentileza contate o administrador do sistema.");
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
	@RequestMapping(value = "rest/lista/curso", method = RequestMethod.POST)
	public TableResponseJson listar(HttpServletRequest req) {
		// Cria objeto de retorno do JSON
		TableResponseJson res = new TableResponseJson();

		List<Curso> listaCursos = cursoDAO.listar("");

		res.setData(listaCursos);

		return res;
	}

	@Override
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "rest/exclui/curso", method = RequestMethod.POST)
	public ResponseJson excluir(HttpServletRequest req) {
		// Cria objeto de retorno do JSON
		ResponseJson responseJson = new ResponseJson();

		// Pega o código do curso que será excluido
		String id = req.getParameter("id");

		// Pega o objeto de curso para pesquisar no banco
		Curso curso = cursoDAO.pesquisarPorId(Integer.parseInt(id));
		
		// Query para verificar se existe algum Aluno associado com o Curso selecionado
		List<Aluno> listaAlunos = alunoDAO
				.getEntityManager().createQuery("SELECT DISTINCT a FROM Aluno a "
						+ " JOIN FETCH a.curso c " 
						+ " WHERE c = :curso")
				.setParameter("curso", curso).getResultList();

		if (listaAlunos.size() > 0) {
			responseJson.setStatus("FAIL");
			responseJson.setResult("Não é possível excluir o curso selecionado porque existe(m) alunos(s) "
					+ "associado(s) a este curso. <br /><br />"
					+ "Por gentileza realize as desassociações antes de excluir o curso.");
		} else {
			// Realiza a exclusão do curso
			if (cursoDAO.excluir(curso)) {
				responseJson.setStatus("SUCCESS");
				responseJson.setResult("Curso excluído com sucesso!");
			} else {
				responseJson.setStatus("FAIL");
				responseJson.setResult("Erro ao excluir o curso. Por gentileza contate o administrador do sistema.");
			}
		}

		return responseJson;
	}

	@Override
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "rest/inativa/curso", method = RequestMethod.POST)
	public ResponseJson inativar(HttpServletRequest req) {
		// Cria objeto de retorno do JSON
		ResponseJson responseJson = new ResponseJson();

		// Pega o código do curso que será inativado
		String id = req.getParameter("id");
		
		// Pega o objeto de curso para alterar o status
		Curso curso = cursoDAO.pesquisarPorId(Integer.parseInt(id));

		// Query para verificar se existe algum Professor associado com a linhaDePesquisa selecionada
		List<Aluno> listaAlunos = alunoDAO
				.getEntityManager().createQuery("SELECT DISTINCT a FROM Aluno a "
						+ " JOIN FETCH a.curso c " 
						+ " WHERE c = :curso AND "
						+ " a.ativo = :ativo")
				.setParameter("curso", curso)
				.setParameter("ativo", true).getResultList();

		if (listaAlunos.size() > 0) {
			responseJson.setStatus("FAIL");
			responseJson.setResult("Não é possível inativar este curso seleciono porque existe(m) "
					+ "alunos(s) ativo(s) associado(s) a este curso. <br /><br />"
					+ "Por gentileza realize as desassociações antes de inativar o curso.");
		}else{
			// Altera o status do curso
			if (curso.isAtivo()) {
				curso.setAtivo(false);
			} else {
				curso.setAtivo(true);
			}
	
			// Grava as alterações realizadas com o curso
			if (cursoDAO.alterar(curso)) {
				responseJson.setStatus("SUCCESS");
	
				if (curso.isAtivo()) {
					responseJson.setResult("Curso ativado com sucesso.");
				} else {
					responseJson.setResult("Curso inativado com sucesso.");
				}
			} else {
				responseJson.setStatus("FAIL");
				responseJson.setResult("Erro ao ativar/inativar o curso. Por gentileza contate o administrador do sistema.");
			}
		}

		return responseJson;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "rest/json/curso", method = RequestMethod.POST)
	public Curso entidadeJSON(HttpServletRequest req) {
		// Pega o código do Curso que será inativado
		String id = req.getParameter("id");

		// Pega o objeto de curso para alterar o status
		Curso curso = cursoDAO.pesquisarPorId(Integer.parseInt(id));

		return curso;
	}

	@RequestMapping(value = "adm/relatorio/pdf/curso", method = RequestMethod.GET)
	public ModelAndView gerarRelatorio(ModelAndView modelAndView) {
		List<Curso> listaCurso = cursoDAO.listar(" ORDER BY c.nome");

		//Criação da DataSouce do iReport
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(listaCurso, false);
		
		//Map de parâmetros a serem passados para o iReport
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", JRdataSource);
		parameterMap.put("path", servletContext.getRealPath("/images/reports/"));
		
		Map<String, Object> exporterParameters = new HashMap<String, Object>();
		
		//Propriedades do Header da Response
		Properties header = new Properties();
		
		//Nome do arquivo caso o usuário de Ctrl+S (Salvar)
		header.put("Content-Disposition", "inline; filename=Cursos.pdf");
		
		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setUrl("/WEB-INF/reports/pdf/curso.jrxml");
		view.setReportDataKey("datasource");
		view.setExporterParameters(exporterParameters);
		view.setApplicationContext(applicationContext);
		view.setHeaders(header);
		
		// Gera o relatório de acordo com a extensao enviada por parâmetro de URL
		modelAndView = new ModelAndView(view, parameterMap);
		return modelAndView;
	}

}
