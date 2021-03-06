package br.com.ctrlt.interfaces.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ctrlt.model.TrabalhoDeConclusao;

/**
 * Interface responsável pela consulta presente na index da galeria
 * 
 * @author Simone Santos Rodrigues
 * @version 1.0
 * 
 * Este sistema foi desenvolvido sob a licença GPL/GNU versão 3, onde qualquer pessoa poderá copiar e distribuir cópias sem alterações deste documento de licença.
 * Consulte mais informações sobre essa licença no arquivo gpl.txt que contêm na raiz do projeto.
 * 
 */

@Service
@Transactional
public interface TrabalhoDeConclusaoRepository extends JpaRepository<TrabalhoDeConclusao, Long> {
	@Query("SELECT DISTINCT t FROM TrabalhoDeConclusao t "
			+ "JOIN t.listaProfessores p "
			+ "JOIN t.listaAlunos a "
			+ "WHERE t.ativo = true "
			+ "AND (t.titulo LIKE CONCAT('%', :pesquisa, '%') "
			+ "OR p.nome LIKE CONCAT('%', :pesquisa, '%') "
			+ "OR a.nome LIKE CONCAT('%', :pesquisa, '%') "
			+ "OR t.palavrasChave LIKE CONCAT('%', :pesquisa, '%') "
			+ "OR STR(YEAR(t.dataPublicacao)) = :pesquisa)")
	Page<TrabalhoDeConclusao> pesquisarTcc(@Param("pesquisa") String pesquisa, Pageable pageRequest);
	
	@Query("SELECT DISTINCT t FROM TrabalhoDeConclusao t "
			+ "JOIN t.listaProfessores p "
			+ "JOIN t.listaAlunos a "
			+ "JOIN a.curso c "
			+ "WHERE t.ativo = true "
			+ "AND a.curso.id = :curso")
	Page<TrabalhoDeConclusao> pesquisaPorCurso(@Param("curso") Long curso, Pageable pageRequest);	
	
}
