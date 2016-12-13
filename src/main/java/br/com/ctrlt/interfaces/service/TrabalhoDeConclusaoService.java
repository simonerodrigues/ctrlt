package br.com.ctrlt.interfaces.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.ctrlt.model.TrabalhoDeConclusao;

/**
 * Interface responsável pela paginação da galeria
 * 
 * @author Simone Santos Rodrigues
 * @version 1.0
 * 
 * Este sistema foi desenvolvido sob a licença GPL/GNU versão 3, onde qualquer pessoa poderá copiar e distribuir cópias sem alterações deste documento de licença.
 * Consulte mais informações sobre essa licença no arquivo gpl.txt que contêm na raiz do projeto.
 * 
 */

@Service
public interface TrabalhoDeConclusaoService {
	
	Page<TrabalhoDeConclusao> obterMonografias(Integer pageNumber);
	
	Page<TrabalhoDeConclusao> pesquisarMonografia(Integer pageNumber, String titulo);
	
	Page<TrabalhoDeConclusao> obeterMonografiasPorCurso(Integer pageNumber, String curso);

}
