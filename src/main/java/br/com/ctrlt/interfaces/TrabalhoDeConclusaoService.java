package br.com.ctrlt.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.ctrlt.model.TrabalhoDeConclusao;

@Service
public interface TrabalhoDeConclusaoService {
	
	Page<TrabalhoDeConclusao> obterMonografias(Integer pageNumber);
	
	Page<TrabalhoDeConclusao> pesquisarMonografia(Integer pageNumber, String titulo);

}
