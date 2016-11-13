<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<div class="row">
				<div class="col-lg-12">		
					<center><h2>Trabalhos mais visitados</h2></center>
					<hr />
					
					<!-- Importando Coverflow -->
					<c:url value="includes/coverflow.jsp" var="coverflow"></c:url>
					<c:import url="${coverflow}"></c:import>
				</div>
			</div>

			<br />

			<center><h2>Recém adicionados</h2></center>
			<hr />

			<!-- Demais trabalhos acadêmicos -->
			<div class="row">
				<div class="col-lg-12 line">		
					<c:forEach items="${trabalhosRecemAdicionados}" var="trabalhoDeConclusao">
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
			</div>
		</div>
	</main>

	<footer>
		
	</footer>

	<!-- JavaScript Include -->
	<c:url value="includes/javascript.jsp" var="javascript"></c:url>
	<c:import url="${javascript}"></c:import>

	<!-- Coverflow script -->
	<script>
		$(function() {

			$('#coverflow').coverflow({
				index:			parseInt(${((trabalhosMaisBaixados.size()) / 2)}),
				density:		2,
				innerOffset:	50,
				innerScale:		.7,
				animateStep:	function(event, cover, offset, isVisible, isMiddle, sin, cos) {
					if (isVisible) {
						if (isMiddle) {
							$(cover).css({
								'filter':			'none',
								'-webkit-filter':	'none'
							});
						} else {
							var brightness	= 1 + Math.abs(sin),
								contrast	= 1 - Math.abs(sin),
								filter		= 'contrast('+contrast+') brightness('+brightness+')';
							$(cover).css({
								'filter':			filter,
								'-webkit-filter':	filter
							});
						}
					}
				}
			});
		});
	</script>

</body>

</html>
