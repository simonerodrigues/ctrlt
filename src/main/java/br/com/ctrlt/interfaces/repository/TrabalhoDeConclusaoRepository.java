package br.com.ctrlt.interfaces.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ctrlt.model.TrabalhoDeConclusao;

@Service
@Transactional
public interface TrabalhoDeConclusaoRepository extends JpaRepository<TrabalhoDeConclusao, Long> {
	@Query("SELECT t FROM TrabalhoDeConclusao t "
			+ "JOIN t.listaProfessores p "
			+ "JOIN t.listaAlunos a "
			+ "WHERE t.ativo = true "
			+ "AND (lower(t.titulo) LIKE (lower(:pesquisa) || '%') "
			+ "OR lower(p.nome) LIKE (lower(:pesquisa) || '%') "
			+ "OR lower(a.nome) LIKE (lower(:pesquisa) || '%') "
			+ "OR lower(t.palavrasChave) LIKE ('%' || lower(:pesquisa) || '%'))")
	Page<TrabalhoDeConclusao> pesquisarTcc(@Param("pesquisa") String pesquisa, Pageable pageRequest);
	
	@Query("SELECT t FROM TrabalhoDeConclusao t "
			+ "JOIN t.listaProfessores p "
			+ "JOIN t.listaAlunos a "
			+ "JOIN a.curso c "
			+ "WHERE t.ativo = true "
			+ "AND a.curso.id = :curso")
	Page<TrabalhoDeConclusao> pesquisaPorCurso(@Param("curso") Long curso, Pageable pageRequest);	
	
}
