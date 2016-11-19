<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="baseURL" value="${pageContext.request.contextPath}" />

<div id="coverflow">
	<c:forEach items="${trabalhosMaisBaixados}" var="trabalhoDeConclusao">
		<a class="book-link" href="${baseURL}/trabalho_de_conclusao/${trabalhoDeConclusao.id}">
			<div class="book-cover left">
				<img src="${baseURL}/images/logo/gallery/book-header.png" width="100%">
				
				<div class="book-student-names">
					<c:forEach items="${trabalhoDeConclusao.listaAlunos}" var="aluno">
						${aluno.nome} <br />
					</c:forEach>
				</div>
				
				<div class="book-title">
					${trabalhoDeConclusao.titulo}
				</div>
				
				<div class="book-footer">
					S�o Caetano do Sul - <fmt:formatDate pattern="yyyy" value="${trabalhoDeConclusao.monografia.dataUpload.time}" />
				</div>
			</div>
		</a>
	</c:forEach>
</div>