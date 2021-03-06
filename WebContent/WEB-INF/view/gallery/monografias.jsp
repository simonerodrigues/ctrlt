<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="baseURL" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="pt-br">

<!--
Este sistema foi desenvolvido sob a licença GPL/GNU versão 3.
Consulte mais informações sobre essa licença no arquivo gpl.txt que se encontra na raiz do projeto.
-->

<head>
	<!-- Informações de Autor do projeto --> 
	<c:url value="includes/meta_informations.jsp" var="metainformations"></c:url>
	<c:import url="${metainformations}"></c:import>
	
	<!-- CSS Include -->
	<c:url value="includes/css.jsp" var="css"></c:url>
	<c:import url="${css}"></c:import>
</head>

<body>
	<div class="modal-loading"></div>

	<div class="se-pre-con"></div>
	
	<!-- Importando Header -->
	<c:import url="includes/header.jsp"></c:import>

	<main>
		<!-- Trabalhos mais procurados -->
		<div class="container page">
		
			<h2 class="row text-center">Monografias do Ctrl+T</h2>
			<hr />

			<!-- Trabalhos acadêmicos -->
			<c:if test="${not empty trabalhosDeConclusao}">
				<div class="row">			
					<div class="col-lg-12 line">		
						<c:forEach items="${trabalhosDeConclusao}" var="trabalhoDeConclusao">
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
										São Caetano do Sul - <fmt:formatDate pattern="yyyy" value="${trabalhoDeConclusao.monografia.dataUpload.time}" />
									</div>
								</div>
							</a>
						</c:forEach>
					</div>
				</div>
				
				<div class="col-lg-12 line">
					<c:if test="${empty param.s}">
						<c:url var="firstUrl" value="/galeria/monografias/1" />
						<c:url var="lastUrl" value="/galeria/monografias/${deploymentLog.totalPages}" />
						<c:url var="prevUrl" value="/galeria/monografias/${currentIndex - 1}" />
						<c:url var="nextUrl" value="/galeria/monografias/${currentIndex + 1}" />
					</c:if>
					
					<c:if test="${not empty param.s}">
						<c:url var="firstUrl" value="/galeria/monografias/1?s=${param.s}" />
						<c:url var="lastUrl" value="/galeria/monografias/${deploymentLog.totalPages}?s=${param.s}" />
						<c:url var="prevUrl" value="/galeria/monografias/${currentIndex - 1}?s=${param.s}" />
						<c:url var="nextUrl" value="/galeria/monografias/${currentIndex + 1}?s=${param.s}" />
					</c:if>
					
					<div class="row text-center">
						<ul class="pagination">
					        <c:choose>
					            <c:when test="${currentIndex == 1}">
					                <li class="disabled"><a href="#">&lt;&lt;</a></li>
					                <li class="disabled"><a href="#">&lt;</a></li>
					            </c:when>
					            <c:otherwise>
					                <li><a href="${firstUrl}">&lt;&lt;</a></li>
					                <li><a href="${prevUrl}">&lt;</a></li>
					            </c:otherwise>
					        </c:choose>
					        <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
					        	<c:if test="${empty param.s}">
									<c:url var="pageUrl" value="/galeria/monografias/${i}" />
								</c:if>
								
								<c:if test="${not empty param.s}">
									<c:url var="pageUrl" value="/galeria/monografias/${i}" />
								</c:if>
								
					            <c:choose>
					                <c:when test="${i == currentIndex}">
					                    <li class="active"><a href="${pageUrl}?s=${param.s}"><c:out value="${i}" /></a></li>
					                </c:when>
					                <c:otherwise>
					                    <li><a href="${pageUrl}?s=${param.s}"><c:out value="${i}" /></a></li>
					                </c:otherwise>
					            </c:choose>
					        </c:forEach>
					        <c:choose>
					            <c:when test="${currentIndex == deploymentLog.totalPages}">
					                <li class="disabled"><a href="#">&gt;</a></li>
					                <li class="disabled"><a href="#">&gt;&gt;</a></li>
					            </c:when>
					            <c:otherwise>
					                <li><a href="${nextUrl}">&gt;</a></li>
					                <li><a href="${lastUrl}">&gt;&gt;</a></li>
					            </c:otherwise>
					        </c:choose>
					    </ul>
					</div>
				</div>
			</c:if>
			
			<!-- Nenhum trabalho encontrado para a pesquisa digitada -->
			<c:if test="${empty trabalhosDeConclusao and not empty param.s}">
				<div class="row text-center">
					<img class="row text-center not-found" src="${baseURL}/images/others/not-found.png" />
					<h3 class="row text-center not-found">Nenhum resultado encontrado para a pesquisa: "${param.s}"</h3>
				</div>
			</c:if>
			
			<!-- Nenhum trabalho encontrado para a o curso selecionado -->
			<c:if test="${empty trabalhosDeConclusao and not empty param.c}">
				<div class="row text-center">
					<img class="row text-center not-found" src="${baseURL}/images/others/not-found.png" />
					<h3 class="row text-center not-found">Nenhum resultado encontrado para o curso selecionado</h3>
				</div>
			</c:if>
		</div>
	</main>

	<!-- Importando Footer -->
	<c:import url="includes/footer.jsp"></c:import>

	<!-- JavaScript Include -->
	<c:url value="includes/javascript.jsp" var="javascript"></c:url>
	<c:import url="${javascript}"></c:import>
	
	<script>
		<c:if test="${empty param.c}">
			$("li#menuMonografias").attr('class', 'active');
		</c:if>
		
		<c:if test="${not empty param.c}">
		$("li#menuCursos").attr('class', 'active');
	</c:if>
	</script>
</body>

</html>
