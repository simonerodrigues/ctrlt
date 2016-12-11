<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
			<div class="row">
				<h2 class="row text-center">Sobre</h2>
				<hr />
				
				<div class="col-lg-12">
					<center><i style="font-size:400px;color:#3980b5" class="fa fa-book fa-6" aria-hidden="true"></i></center>
				</div>
				
				<div class="col-lg-12 text-center">
					<p>Ctrl+T é um software com a licença GPL/GNU desenvolvido como trabalho de conclusão de curso para a Fatec São Caetano do Sul - Antonio Russo com o 
						intuito de auxiliar a faculdade a dar maior publicidade aos trabalhos realizados, assim como garantir maior segurança em relação ao armazenamento destes.</p>
					
					<br />
					
					<p><strong>Desenvolvido por:</strong> Simone Santos Rodrigues</p>
					<p><strong>Professor Orientador:</strong> Ricardo Baitz</p>
					
					<br /><br />
				</div>
			</div>
			<!-- /.row -->
		</div>
	</main>

	<!-- Importando Footer -->
	<c:import url="includes/footer.jsp"></c:import>

	<!-- JavaScript Include -->
	<c:url value="includes/javascript.jsp" var="javascript"></c:url>
	<c:import url="${javascript}"></c:import>

	<!-- Coverflow script -->
	<script>
		$("li#menuInformacoes").attr('class', 'active');
	</script>

</body>

</html>
