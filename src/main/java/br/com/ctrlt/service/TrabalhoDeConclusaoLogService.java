package br.com.ctrlt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ctrlt.interfaces.TrabalhoDeConclusaoService;
import br.com.ctrlt.model.TrabalhoDeConclusao;
import br.com.ctrlt.repository.TrabalhoDeConclusaoRepository;

@Service
@Transactional
public class TrabalhoDeConclusaoLogService implements TrabalhoDeConclusaoService {
	 private static final int PAGE_SIZE = 10;
	
    @Autowired 
    private TrabalhoDeConclusaoRepository trabalhoDeConclusaoRepository;

    public Page<TrabalhoDeConclusao> obterMonografias(Integer pageNumber) {
        PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, "titulo");
        return trabalhoDeConclusaoRepository.findAll(request);
    }
    
    public Page<TrabalhoDeConclusao> pesquisarMonografia(Integer pageNumber, String titulo) {
        PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, "titulo");
        return trabalhoDeConclusaoRepository.pesquisarPorTitulo(titulo, request);
    }
}