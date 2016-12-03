﻿<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
	<c:url value="includes/meta_informations.jsp" var="metainformations"></c:url>
	
	<!-- Informações de Autor do projeto -->
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
							Profile <small>Você no Ctrl+T</small>
						</h1>
						<ol class="breadcrumb">
							<li class="active"><i class="fa fa-user"></i> Profile
							</li>
						</ol>
					</div>
				</div>
				<!-- /.row -->

				<div class="row">
					<!-- Conteúdo futuro -->
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
	
	<script>
		
	</script>

</body>

</html>
