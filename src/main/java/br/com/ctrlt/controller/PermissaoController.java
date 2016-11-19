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

import br.com.ctrlt.dao.PermissaoDAO;
import br.com.ctrlt.json.ResponseJson;
import br.com.ctrlt.json.TableResponseJson;
import br.com.ctrlt.model.Permissao;

@Controller
public class PermissaoController implements Control<Permissao> {

	@Autowired
	private PermissaoDAO permissaoDAO;
	
	@Override
	public String carregarPagina(Model model) {
		return "";
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "rest/cadastra/permissao", method = RequestMethod.POST)
	public ResponseJson cadastrar(@Valid Permissao entidade, BindingResult result) {
		ResponseJson responseJson = new ResponseJson();

		if (!result.hasErrors()) {			
			if (permissaoDAO.cadastrar(entidade)) {
				responseJson.setStatus("SUCCESS");
				responseJson.setResult("Permiss�o cadastrada com sucesso.");
			} else {
				responseJson.setStatus("FAIL");
				responseJson.setResult("Erro ao cadastrar a permiss�o. Por gentileza contate o administrador do sistema.");
			}
		} else {
			responseJson.setStatus("FAIL");

			String erros = "";

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
	@RequestMapping(value = "rest/altera/permissao", method = RequestMethod.POST)
	public ResponseJson alterar(@Valid Permissao entidade, BindingResult result) {
		ResponseJson responseJson = new ResponseJson();

		if (!result.hasErrors()) {
			if (permissaoDAO.alterar(entidade)) {
				responseJson.setStatus("SUCCESS");
				responseJson.setResult("Permiss�o alterada com sucesso.");
			} else {
				responseJson.setStatus("FAIL");
				responseJson.setResult("Erro ao alterar o permiss�o. Por gentileza contate o administrador do sistema.");
			}
		} else {
			responseJson.setStatus("FAIL");

			String erros = "";

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
	@RequestMapping(value = "rest/lista/permissaoe", method = RequestMethod.POST)
	public TableResponseJson listar(HttpServletRequest req) {
		// Cria objeto de retorno do JSON
		TableResponseJson res = new TableResponseJson();

		List<Permissao> listaPermissaoes = permissaoDAO.listar("");

		res.setData(listaPermissaoes);

		return res;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "rest/exclui/permissaoes")
	public ResponseJson excluir(HttpServletRequest req) {
		// Cria objeto de retorno do JSON
		ResponseJson responseJson = new ResponseJson();

		// Pega o c�digo do permissao que ser� excluido
		String id = req.getParameter("id");

		// Pega o objeto de permissao para pesquisar no banco
		Permissao permissao = permissaoDAO.pesquisarPorId(Integer.parseInt(id));

		// Realiza a exclus�o do permissao
		if (permissaoDAO.excluir(permissao)) {
			responseJson.setStatus("SUCCESS");
			responseJson.setResult("Permiss�o exclu�da com sucesso.");
		} else {
			responseJson.setStatus("FAIL");
			responseJson.setResult("Erro ao excluir a permiss�o. Por gentileza contate o administrador do sistema.");
		}

		return responseJson;
	}

	@Override
	public ResponseJson inativar(HttpServletRequest req) {
		// N�O UTILIZAMOS NO MOMENTO
		return null;
	}
	
	@Override
	@ResponseBody
	@RequestMapping(value = "rest/json/permissao", method = RequestMethod.POST)
	public Permissao entidadeJSON(HttpServletRequest req) {
		// Pega o c�digo do Permissao que ser� inativado
		String id = req.getParameter("id");

		// Pega o objeto de permissao para alterar o status
		Permissao permissao = permissaoDAO.pesquisarPorId(Integer.parseInt(id));

		return permissao;
	}

	@Override
	public ModelAndView gerarRelatorio(ModelAndView modelAndView) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
