<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="coverflow">
	<c:forEach items="${trabalhosMaisBaixados}" var="trabalhoDeConclusao">
		<div class="book-cover left">
			<img src="/images/logo/library/book-header.png" width="100%">
			
			<div class="book-student-names">
				<c:forEach items="${trabalhoDeConclusao.listaAlunos}" var="aluno">
					${aluno.nome} <br />
				</c:forEach>
			</div>
			
			<div class="book-title">
				${trabalhoDeConclusao.titulo}
			</div>
			
			<div class="book-footer">
				São Caetano do Sul - <fmt:formatDate pattern="yyyy" value="${trabalhoDeConclusao.monografia.dataUpload.time}" />
			</div>
		</div>
	</c:forEach>
</div>