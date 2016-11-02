package br.com.ctrlt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.ctrlt.dao.AnexoDAO;
import br.com.ctrlt.json.ResponseJson;
import br.com.ctrlt.json.TableResponseJson;
import br.com.ctrlt.model.Anexo;

@Controller
public class AnexoController implements Control<Anexo> {

	@Autowired
	private AnexoDAO anexoDAO;
	
	@Override
	public String carregarPagina(Model model) {
		return "";
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "rest/cadastra/anexo", method = RequestMethod.POST)
	public ResponseJson cadastrar(@Valid Anexo entidade, BindingResult result) {
		ResponseJson responseJson = new ResponseJson();

		if (!result.hasErrors()) {
			entidade.setAtivo(true);
			
			if (anexoDAO.cadastrar(entidade)) {
				responseJson.setStatus("SUCCESS");
				responseJson.setResult("Anexo cadastrado com sucesso.");
			} else {
				responseJson.setStatus("FAIL");
				responseJson.setResult("Erro ao cadastrar o anexo. Por gentileza contate o administrador do sistema.");
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
	@RequestMapping(value = "rest/altera/anexo", method = RequestMethod.POST)
	public ResponseJson alterar(@Valid Anexo entidade, BindingResult result) {
		ResponseJson responseJson = new ResponseJson();

		if (!result.hasErrors()) {
			if (anexoDAO.alterar(entidade)) {
				responseJson.setStatus("SUCCESS");
				responseJson.setResult("Anexo alterado com sucesso.");
			} else {
				responseJson.setStatus("FAIL");
				responseJson.setResult("Erro ao alterar o anexo. Por gentileza contate o administrador do sistema.");
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
	public TableResponseJson listar(HttpServletRequest req) {
		return null;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "excluir_anexo", method = RequestMethod.POST)
	public ResponseJson excluir(HttpServletRequest req) {
		// Cria objeto de retorno do JSON
		ResponseJson responseJson = new ResponseJson();

		// Pega o código do anexo que será excluido
		String id = req.getParameter("id");

		// Pega o objeto de anexo para pesquisar no banco
		Anexo anexo = anexoDAO.pesquisarPorId(Integer.parseInt(id));

		// Realiza a exclusão do anexo
		if (anexoDAO.excluir(anexo)) {
			responseJson.setStatus("SUCCESS");
			responseJson.setResult("Anexo excluído com sucesso.");
		} else {
			responseJson.setStatus("FAIL");
			responseJson.setResult("Erro ao excluir o anexo. Por gentileza contate o administrador do sistema.");
		}
		
		return responseJson;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "rest/inativa/anexo", method = RequestMethod.POST)
	public ResponseJson inativar(HttpServletRequest req) {
		// Cria objeto de retorno do JSON
		ResponseJson responseJson = new ResponseJson();

		// Pega o código do anexo que será inativado
		String id = req.getParameter("id");

		// Pega o objeto de anexo para alterar o status
		Anexo anexo = anexoDAO.pesquisarPorId(Integer.parseInt(id));

		// Altera o status do anexo
		if (anexo.isAtivo()) {
			anexo.setAtivo(false);
		} else {
			anexo.setAtivo(true);
		}

		// Grava as alterações realizadas com o anexo
		if (anexoDAO.alterar(anexo)) {
			responseJson.setStatus("SUCCESS");

			if (anexo.isAtivo()) {
				responseJson.setResult("Anexo ativado com sucesso.");
			} else {
				responseJson.setResult("Anexo inativado com sucesso.");
			}
		} else {
			responseJson.setStatus("FAIL");
			responseJson.setResult("Erro ao ativar/inativar o anexo. Por gentileza contate o administrador do sistema.");
		}

		return responseJson;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "rest/json/anexo", method = RequestMethod.POST)
	public Anexo entidadeJSON(HttpServletRequest req) {
		// Pega o código da Anexo que será inativado
		String id = req.getParameter("id");

		// Pega o objeto de anexo para alterar o status
		Anexo anexo = anexoDAO.pesquisarPorId(Integer.parseInt(id));

		return anexo;
	}

	@Override
	public ModelAndView gerarRelatorio(ModelAndView modelAndView) {
		// TODO Auto-generated method stub
		return null;
	}
}
