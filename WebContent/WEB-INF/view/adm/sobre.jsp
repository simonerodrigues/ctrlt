<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
	<c:url value="includes/meta_informations.jsp"
		var="metainformations"></c:url>
	
	<!-- Informa��es de Autor do projeto -->
	<c:import url="${metainformations}"></c:import>
	
	<!-- CSS Include -->
	<c:url value="includes/css.jsp" var="css"></c:url>
	<c:import url="${css}"></c:import>
</head>

<body>
	<div class="modal-loading"></div>

	<div class="se-pre-con"></div>

	<div id="wrapper">

		<!-- Navigation -->
		<c:url value="includes/navbar.jsp" var="navbar"></c:url>
		<c:url value="includes/sidebar_adm.jsp" var="sidebar"></c:url>

		<c:import url="${navbar}">
			<c:param name="sidebar" value="${sidebar}" />
		</c:import>

		<div id="page-wrapper">

			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							Sobre <small>Ctrl+T</small>
						</h1>
						<ol class="breadcrumb">
							<li class="active"><i class="fa fa-info-circle"></i> Sobre
							</li>
						</ol>
					</div>
				</div>
				<!-- /.row -->

				<div class="row">
					<div class="col-lg-12">
						<center><i style="font-size:400px;color:#3980b5" class="fa fa-book fa-6" aria-hidden="true"></i></center>
					</div>
					
					<div class="col-lg-12 text-center">
						<p>Ctrl+T � um software com a licen�a GPL/GNU desenvolvido como trabalho de conclus�o de curso para a Fatec S�o Caetano do Sul - Antonio Russo com o 
							intuito de auxiliar a faculdade a dar maior publicidade aos trabalhos realizados, assim como garantir maior seguran�a em rela��o ao armazenamento destes.</p>
						
						<br />
						
						<p><strong>Desenvolvido por:</strong> Simone Santos Rodrigues</p>
						<p><strong>Professor Orientador:</strong> Ricardo Baitz</p>
						
						<br /><br />
						
						<p><strong>Ctrl+T - Controle de Trabalhos Acad�micos. Fatec S�o Caetano do Sul - ${ano}</strong></p>
					</div>
				</div>
				<!-- /.row -->

			</div>
			<!-- /.container-fluid -->

		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->
	
	<!-- Modal Include -->
	<c:url value="includes/modal.jsp" var="modal"></c:url>
	<c:import url="${modal}"></c:import>

	<!-- JavaScript Include -->
	<c:url value="includes/javascript.jsp" var="javascript"></c:url>
	<c:import url="${javascript}"></c:import>

</body>

</html>
