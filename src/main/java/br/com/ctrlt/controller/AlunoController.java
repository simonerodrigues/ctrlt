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
import br.com.ctrlt.dao.CursoDAO;
import br.com.ctrlt.dao.PeriodoDAO;
import br.com.ctrlt.json.ResponseJson;
import br.com.ctrlt.json.TableResponseJson;
import br.com.ctrlt.model.Aluno;
import br.com.ctrlt.model.Curso;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class AlunoController implements Control<Aluno> {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
    private ServletContext servletContext; 
	
	@Autowired
	private AlunoDAO alunoDAO;
	
	@Autowired
	private CursoDAO cursoDAO;
	
	@Autowired
	private PeriodoDAO periodoDAO;
	
	@Override
	@RequestMapping(value = "adm/cadastro/aluno", method = RequestMethod.GET)
	public String carregarPagina(Model model) {
		List<Curso> listaCurso = cursoDAO.listar(" WHERE c.ativo = 1 ORDER BY c.nome ASC");
		
		model.addAttribute("listaCurso", listaCurso);
		
		return "adm/cadastros/cadastro_aluno";
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "rest/cadastra/aluno", method = RequestMethod.POST)
	public ResponseJson cadastrar(@Valid Aluno entidade, BindingResult result) {
		ResponseJson responseJson = new ResponseJson();

		entidade.setCurso(cursoDAO.pesquisarPorId(entidade.getCurso().getId()));
		entidade.setPeriodo(periodoDAO.pesquisarPorId(entidade.getPeriodo().getId()));
		
		String erros = "";
		
		if (!result.hasErrors()) {
			
			//Valida dados que não podem ser repetidos na base
			boolean raExistente = alunoDAO.verificarRaExistente(entidade);
			boolean loginExistente = alunoDAO.verificarLoginExistente(entidade);
			boolean emailAlternativoExistente = alunoDAO.verificarEmailAlternativoExistente(entidade);
			boolean emailFatecExistente = alunoDAO.verificarEmailFatecExistente(entidade);
			
			int numeroErros = 0;
			
			if(raExistente){
				erros += "<br />" + "RA de aluno já cadastrado no sistema";
				numeroErros++;
			}
			
			if(loginExistente){
				erros += "<br />" + "Login de aluno já cadastrado no sistema";
				numeroErros++;
			}
			
			if(emailAlternativoExistente){
				erros += "<br />" + "E-mail alternativo de aluno já cadastrado no sistema";
				numeroErros++;
			}
			
			if(emailFatecExistente){
				erros += "<br />" + "E-mail Fatec de aluno já cadastrado no sistema";
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
			
			if (alunoDAO.cadastrar(entidade)) {
				responseJson.setStatus("SUCCESS");
				responseJson.setResult("Aluno cadastrado com sucesso.");
			} else {
				responseJson.setStatus("FAIL");
				responseJson.setResult("Erro ao cadastrar o aluno. Por gentileza contate o administrador do sistema.");
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
	@RequestMapping(value = "rest/altera/aluno", method = RequestMethod.POST)
	public ResponseJson alterar(@Valid Aluno entidade, BindingResult result) {
		ResponseJson responseJson = new ResponseJson();

		String erros = "";
		
		if (!result.hasErrors()) {
			
			//Valida dados que não podem ser repetidos na base
			boolean raExistente = alunoDAO.verificarRaExistente(entidade);
			boolean loginExistente = alunoDAO.verificarLoginExistente(entidade);
			boolean emailAlternativoExistente = alunoDAO.verificarEmailAlternativoExistente(entidade);
			boolean emailFatecExistente = alunoDAO.verificarEmailFatecExistente(entidade);
			
			int numeroErros = 0;
			
			if(raExistente){
				erros += "<br />" + "RA de aluno já cadastrado no sistema";
				numeroErros++;
			}
			
			if(loginExistente){
				erros += "<br />" + "Login de aluno já cadastrado no sistema";
				numeroErros++;
			}
			
			if(emailAlternativoExistente){
				erros += "<br />" + "E-mail alternativo de aluno já cadastrado no sistema";
				numeroErros++;
			}
			
			if(emailFatecExistente){
				erros += "<br />" + "E-mail Fatec de aluno já cadastrado no sistema";
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
			
			Aluno alunoBanco = alunoDAO.pesquisarPorId(entidade.getId());
			
			entidade.setSenha(alunoBanco.getSenha());
			entidade.setAtivo(alunoBanco.isAtivo());
			entidade.setTrabalhoDeConclusao(alunoBanco.getTrabalhoDeConclusao());
			
			if (alunoDAO.alterar(entidade)) {
				responseJson.setStatus("SUCCESS");
				responseJson.setResult("Aluno alterado com sucesso.");
			} else {
				responseJson.setStatus("FAIL");
				responseJson.setResult("Erro ao alterar o aluno. Por gentileza contate o administrador do sistema.");
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
	@RequestMapping(value = "rest/lista/aluno", method = RequestMethod.POST)
	public TableResponseJson listar(HttpServletRequest req) {
		// Cria objeto de retorno do JSON
		TableResponseJson res = new TableResponseJson();

		List<Aluno> listaAlunos = alunoDAO.listar("");

		res.setData(listaAlunos);

		return res;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "rest/exclui/aluno", method = RequestMethod.POST)
	public ResponseJson excluir(HttpServletRequest req) {
		// Cria objeto de retorno do JSON
		ResponseJson responseJson = new ResponseJson();

		// Pega o código do aluno que será excluido
		String id = req.getParameter("id");

		// Pega o objeto de aluno para pesquisar no banco
		Aluno aluno = alunoDAO.pesquisarPorId(Integer.parseInt(id));

		if(aluno.getTrabalhoDeConclusao() == null){
			// Realiza a exclusão do aluno
			if (alunoDAO.excluir(aluno)) {
				responseJson.setStatus("SUCCESS");
				responseJson.setResult("Aluno excluído com sucesso.");
			} else {
				responseJson.setStatus("FAIL");
				responseJson.setResult("Erro ao excluir o aluno. Por gentileza contate o administrador do sistema.");
			}
		}else{
			responseJson.setStatus("FAIL");
			responseJson.setResult("Não é possíve exluir o aluno, o memso está vinculado a um trabalho de conclusão. Por gentileza realize a desassociação antes de excluí-lo.");
		}

		return responseJson;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "rest/inativa/aluno", method = RequestMethod.POST)
	public ResponseJson inativar(HttpServletRequest req) {
		// Cria objeto de retorno do JSON
		ResponseJson responseJson = new ResponseJson();

		// Pega o código do aluno que será inativado
		String id = req.getParameter("id");

		// Pega o objeto de aluno para alterar o status
		Aluno aluno = alunoDAO.pesquisarPorId(Integer.parseInt(id));

		// Altera o status do aluno
		if (aluno.isAtivo()) {
			aluno.setAtivo(false);
		} else {
			aluno.setAtivo(true);
		}

		// Grava as alterações realizadas com o aluno
		if (alunoDAO.alterar(aluno)) {
			responseJson.setStatus("SUCCESS");

			if (aluno.isAtivo()) {
				responseJson.setResult("Aluno ativado com sucesso.");
			} else {
				responseJson.setResult("Aluno inativado com sucesso.");
			}
		} else {
			responseJson.setStatus("FAIL");
			responseJson.setResult("Erro ao ativar/inativar o aluno. Por gentileza contate o administrador do sistema.");
		}
		
		return responseJson;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "rest/json/aluno", method = RequestMethod.POST)
	public Aluno entidadeJSON(HttpServletRequest req) {
		// Pega o código do Aluno que será inativado
		String id = req.getParameter("id");

		// Pega o objeto de aluno para alterar o status
		Aluno aluno = alunoDAO.pesquisarPorId(Integer.parseInt(id));

		return aluno;
	}

	@RequestMapping(value = "adm/relatorio/pdf/aluno", method = RequestMethod.GET)
	public ModelAndView gerarRelatorio(ModelAndView modelAndView) {
		List<Aluno> listaAluno = alunoDAO.listar(" ORDER BY a.nome");

		//Criação da DataSouce do iReport
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(listaAluno, false);
		
		//Map de parâmetros a serem passados para o iReport
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", JRdataSource);
		parameterMap.put("path", servletContext.getRealPath("/images/reports/"));
		
		Map<String, Object> exporterParameters = new HashMap<String, Object>();
		
		//Propriedades do Header da Response
		Properties header = new Properties();
		
		//Nome do arquivo caso o usuário de Ctrl+S (Salvar)
		header.put("Content-Disposition", "inline; filename=Alunos.pdf");
		
		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setUrl("/WEB-INF/reports/pdf/aluno.jrxml");
		view.setReportDataKey("datasource");
		view.setExporterParameters(exporterParameters);
		view.setApplicationContext(applicationContext);
		view.setHeaders(header);
		
		// Gera o relatório de acordo com a extensao enviada por parâmetro de URL
		modelAndView = new ModelAndView(view, parameterMap);
		return modelAndView;
	}
}
