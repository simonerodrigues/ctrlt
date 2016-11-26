package br.com.ctrlt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ctrlt.model.TrabalhoDeConclusao;

@Transactional
public interface TrabalhoDeConclusaoRepository extends JpaRepository<TrabalhoDeConclusao, Long> {
	@Query("SELECT t FROM TrabalhoDeConclusao t WHERE t.ativo = true AND t.titulo LIKE CONCAT(:titulo, '%')")
	Page<TrabalhoDeConclusao> pesquisarPorTitulo(@Param("titulo") String titulo, Pageable pageRequest);
}
