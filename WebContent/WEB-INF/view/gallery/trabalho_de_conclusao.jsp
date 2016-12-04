<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="baseURL" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="pt-br">

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
			
			<h2>Título: ${trabalhoDeConclusao.titulo} <button onclick="voltar()" class="btn btn-primary right"><i class="fa fa-arrow-circle-left"></i> Voltar</button></h2>
			<hr />
			
			<br />
			
			<div class="col-lg-6">
				<h3>Aluno(s):</h3>
				
				<br />
				
				<c:forEach items="${trabalhoDeConclusao.listaAlunos}" var="aluno">
					${aluno.nome} <br />
				</c:forEach>
				
				<br />
				
				<h3>Orientadore(s):</h3>
				
				<br />
				
				<c:forEach items="${trabalhoDeConclusao.listaProfessores}" var="professor">
					${professor.nome} <br />
				</c:forEach>
				
				<br />
				
				<h3>Palavras Chave:</h3>
					
				<br />
				
				<textarea readonly="readonly" cols="65" rows="3">${trabalhoDeConclusao.palavrasChave}</textarea>
				
				<br />
				
				<h3>Resumo:</h3>
					
				<br />
				
				<textarea readonly="readonly" cols="65" rows="20">${trabalhoDeConclusao.resumo}</textarea>
			</div>
			
			<div class="col-lg-6">
				<h3>Capa padrão Ctrl+T:</h3>
				
				<br />
				
				<div class="row text-center">
					<div class="book-cover text-center">
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
				</div>
				
				<br />
				<br />
			
				<h3>Monografia:</h3>
				
				<br />
				
				<div class="row">
					<div class="col-lg-8">
						<strong>Nome:</strong> ${trabalhoDeConclusao.monografia.nome} <br />
						<strong>Número de Downloads:</strong> ${trabalhoDeConclusao.monografia.numeroDownloads}
					</div>
					
					<div class="col-lg-4">
						<a target="_blank" href="${baseURL}/monografias/${trabalhoDeConclusao.id}/${trabalhoDeConclusao.monografia.nome}">
							<button class="btn btn-primary">
								<i class="fa fa-cloud-download" aria-hidden="true"></i> Download
							</button>
						</a>
					</div>
				</div>
				
				<br />
				<br />
				
				<c:if test="${not empty  trabalhoDeConclusao.listaAnexos}">	
					<h3>Anexo(s):</h3>

					<c:forEach items="${trabalhoDeConclusao.listaAnexos}" var="anexo">
						<c:if test="${anexo.visivel == true}">
							<br />
														
							<div class="row">
								<div class="col-lg-8">
									<strong>Nome:</strong> ${anexo.nome} <br />
									<strong>Número de Downloads:</strong> ${anexo.numeroDownloads}
								</div>
								
								<div class="col-lg-4">
									<a target="_blank" href="${baseURL}/anexos/${trabalhoDeConclusao.id}/${anexo.id}/${anexo.nome}">
										<button class="btn btn-primary">
											<i class="fa fa-cloud-download" aria-hidden="true"></i> Download
										</button>
									</a>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</c:if>
			</div>
			
		</div>
	</main>

	<!-- Importando Footer -->
	<c:import url="includes/footer.jsp"></c:import>

	<!-- JavaScript Include -->
	<c:url value="includes/javascript.jsp" var="javascript"></c:url>
	<c:import url="${javascript}"></c:import>
	
	<script>
		function voltar(){
			window.history.back();
		}
	</script>
	
</body>

</html>
