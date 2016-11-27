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

import br.com.ctrlt.dao.AdministradorDeConteudoDAO;
import br.com.ctrlt.json.ResponseJson;
import br.com.ctrlt.json.TableResponseJson;
import br.com.ctrlt.model.AdministradorDeConteudo;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class AdministradorDeConteudoController implements Control<AdministradorDeConteudo> {

	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
    ServletContext servletContext; 
	
	@Autowired
	private AdministradorDeConteudoDAO administradorDeConteudoDAO;
	
	@Override
	@RequestMapping(value = "adm/cadastro/administrador_de_conteudo", method = RequestMethod.GET)
	public String carregarPagina(Model model) {
		return "adm/cadastros/cadastro_administrador_de_conteudo";
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "rest/cadastra/administrador_de_conteudo", method = RequestMethod.POST)
	public ResponseJson cadastrar(@Valid AdministradorDeConteudo entidade, BindingResult result) {
		ResponseJson responseJson = new ResponseJson();
		
		String erros = "";

		if (!result.hasErrors()) {		
			
			//Valida dados que n�o podem ser repetidos na base
			boolean loginExistente = administradorDeConteudoDAO.verificarLoginExistente(entidade);
			boolean emailAlternativoExistente = administradorDeConteudoDAO.verificarEmailAlternativoExistente(entidade);
			boolean emailFatecExistente = administradorDeConteudoDAO.verificarEmailFatecExistente(entidade);
			
			int numeroErros = 0;
			
			if(loginExistente){
				erros += "<br />" + "Login de administrador j� cadastrado no sistema";
				numeroErros++;
			}
			
			if(emailAlternativoExistente){
				erros += "<br />" + "E-mail alternativo de administrador j� cadastrado no sistema";
				numeroErros++;
			}
			
			if(emailFatecExistente){
				erros += "<br />" + "E-mail Fatec de administrador j� cadastrado no sistema";
				numeroErros++;
			}
			
			if(numeroErros > 0){
				if(numeroErros == 1){
					erros = "O seguinte erro foi apresentado durante a valida��o dos dados: <br />" + erros;
				}else{
					erros = "Os seguintes erros foram apresentados durante a valida��o dos dados: <br />" + erros;
				}
				
				responseJson.setStatus("FAIL");
				responseJson.setResult(erros);
				
				return responseJson;
			}
			
			entidade.setAtivo(true);
			
			if (administradorDeConteudoDAO.cadastrar(entidade)) {
				responseJson.setStatus("SUCCESS");
				responseJson.setResult("Administrador de conte�do cadastrado com sucesso.");
			} else {
				responseJson.setStatus("FAIL");
				responseJson.setResult("Erro ao cadastrar o administrador de conte�do. Por gentileza contate o administrador do sistema.");
			}			
		} else {
			responseJson.setStatus("FAIL");

			if (result.getErrorCount() == 1) {
				erros = "O seguinte erro foi apresentado durante a valida��o dos dados: <br />";
			} else {
				erros = "Os seguintes erros foram apresentados durante a valida��o dos dados: <br />";
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
	@RequestMapping(value = "rest/altera/administrador_de_conteudo", method = RequestMethod.POST)
	public ResponseJson alterar(@Valid AdministradorDeConteudo entidade, BindingResult result) {
		ResponseJson responseJson = new ResponseJson();
		
		String erros = "";

		if (!result.hasErrors()) {			
			
			//Valida dados que n�o podem ser repetidos na base
			boolean loginExistente = administradorDeConteudoDAO.verificarLoginExistente(entidade);
			boolean emailAlternativoExistente = administradorDeConteudoDAO.verificarEmailAlternativoExistente(entidade);
			boolean emailFatecExistente = administradorDeConteudoDAO.verificarEmailFatecExistente(entidade);
			
			int numeroErros = 0;
			
			if(loginExistente){
				erros += "<br />" + "Login de administrador j� cadastrado no sistema";
				numeroErros++;
			}
			
			if(emailAlternativoExistente){
				erros += "<br />" + "E-mail alternativo de administrador j� cadastrado no sistema";
				numeroErros++;
			}
			
			if(emailFatecExistente){
				erros += "<br />" + "E-mail Fatec de administrador j� cadastrado no sistema";
				numeroErros++;
			}
			
			if(numeroErros > 0){
				if(numeroErros == 1){
					erros = "O seguinte erro foi apresentado durante a valida��o dos dados: <br />" + erros;
				}else{
					erros = "Os seguintes erros foram apresentados durante a valida��o dos dados: <br />" + erros;
				}
				
				responseJson.setStatus("FAIL");
				responseJson.setResult(erros);
				
				return responseJson;
			}
			
			AdministradorDeConteudo administradorDeConteudoBanco = administradorDeConteudoDAO.pesquisarPorId(entidade.getId());
			
			entidade.setSenha(administradorDeConteudoBanco.getSenha());
			entidade.setAtivo(administradorDeConteudoBanco.isAtivo());
			
			if (administradorDeConteudoDAO.alterar(entidade)) {
				responseJson.setStatus("SUCCESS");
				responseJson.setResult("Administrador de conte�do alterado com sucesso.");
			} else {
				responseJson.setStatus("FAIL");
				responseJson.setResult("Erro ao alterar o administrador de conte�do. Por gentileza contate o administrador do sistema.");
			}
		} else {
			responseJson.setStatus("FAIL");

			if (result.getErrorCount() == 1) {
				erros = "O seguinte erro foi apresentado durante a valida��o dos dados: <br />";
			} else {
				erros = "Os seguintes erros foram apresentados durante a valida��o dos dados: <br />";
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
	@RequestMapping(value = "rest/lista/administrador_de_conteudo")
	public TableResponseJson listar(HttpServletRequest req) {
		// Cria objeto de retorno do JSON
		TableResponseJson res = new TableResponseJson();

		List<AdministradorDeConteudo> listaAdministradorDeConteudos = administradorDeConteudoDAO.listar("");

		res.setData(listaAdministradorDeConteudos);

		return res;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "rest/exclui/administrador_de_conteudo", method = RequestMethod.POST)
	public ResponseJson excluir(HttpServletRequest req) {
		// Cria objeto de retorno do JSON
		ResponseJson responseJson = new ResponseJson();

		// Pega o c�digo do administradorDeConteudo que ser� excluido
		String id = req.getParameter("id");

		// Pega o objeto de administradorDeConteudo para pesquisar no banco
		AdministradorDeConteudo administradorDeConteudo = administradorDeConteudoDAO.pesquisarPorId(Integer.parseInt(id));

		// Realiza a exclus�o do administradorDeConteudo
		if (administradorDeConteudoDAO.excluir(administradorDeConteudo)) {
			responseJson.setStatus("SUCCESS");
			responseJson.setResult("Administrador de conte�do exclu�do com sucesso.");
		} else {
			responseJson.setStatus("FAIL");
			responseJson.setResult("Erro ao excluir o administrador de conte�do. Por gentileza contate o administrador do sistema.");
		}

		return responseJson;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "rest/inativa/administrador_de_conteudo", method = RequestMethod.POST)
	public ResponseJson inativar(HttpServletRequest req) {
		// Cria objeto de retorno do JSON
		ResponseJson responseJson = new ResponseJson();

		// Pega o c�digo do administradorDeConteudo que ser� inativado
		String id = req.getParameter("id");

		// Pega o objeto de administradorDeConteudo para alterar o status
		AdministradorDeConteudo administradorDeConteudo = administradorDeConteudoDAO.pesquisarPorId(Integer.parseInt(id));

		// Altera o status do administradorDeConteudo
		if (administradorDeConteudo.isAtivo()) {
			administradorDeConteudo.setAtivo(false);
		} else {
			administradorDeConteudo.setAtivo(true);
		}

		// Grava as altera��es realizadas com o administradorDeConteudo
		if (administradorDeConteudoDAO.alterar(administradorDeConteudo)) {
			responseJson.setStatus("SUCCESS");

			if (administradorDeConteudo.isAtivo()) {
				responseJson.setResult("Administrador de conte�do ativado com sucesso.");
			} else {
				responseJson.setResult("Administrador de conte�do inativado com sucesso.");
			}
		} else {
			responseJson.setStatus("FAIL");
			responseJson.setResult("Erro ao ativar/inativar o administrador de conte�do. Por gentileza contate o administrador do sistema.");
		}
		
		return responseJson;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "rest/json/administrador_de_conteudo", method = RequestMethod.POST)
	public AdministradorDeConteudo entidadeJSON(HttpServletRequest req) {
		// Pega o c�digo do AdministradorDeConteudo que ser� inativado
		String id = req.getParameter("id");

		// Pega o objeto de administradorDeConteudo para alterar o status
		AdministradorDeConteudo administradorDeConteudo = administradorDeConteudoDAO.pesquisarPorId(Integer.parseInt(id));

		return administradorDeConteudo;
	}

	@RequestMapping(value = "adm/relatorio/pdf/administrador_de_conteudo", method = RequestMethod.GET)
	public ModelAndView gerarRelatorio(ModelAndView modelAndView) {
		List<AdministradorDeConteudo> listaAdministradorDeConteudo = administradorDeConteudoDAO.listar(" ORDER BY a.nome");

		//Cria��o da DataSouce do iReport
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(listaAdministradorDeConteudo, false);
		
		//Map de par�metros a serem passados para o iReport
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", JRdataSource);
		parameterMap.put("path", servletContext.getRealPath("/images/reports/"));
		
		Map<String, Object> exporterParameters = new HashMap<String, Object>();
		
		//Propriedades do Header da Response
		Properties header = new Properties();
		
		//Nome do arquivo caso o usu�rio de Ctrl+S (Salvar)
		header.put("Content-Disposition", "inline; filename=Administradores de Conte�do.pdf");
		
		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setUrl("/WEB-INF/reports/pdf/administrador_de_conteudo.jrxml");
		view.setReportDataKey("datasource");
		view.setExporterParameters(exporterParameters);
		view.setApplicationContext(applicationContext);
		view.setHeaders(header);
		
		// Gera o relat�rio de acordo com a extensao enviada por par�metro de URL
		modelAndView = new ModelAndView(view, parameterMap);
		return modelAndView;
	}

}
