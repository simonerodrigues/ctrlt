<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<c:url value="includes/meta_informations.jsp"
	var="metainformations"></c:url>

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
							Dashboard <small>Estatísticas do Ctrl+T</small>
						</h1>
						<ol class="breadcrumb">
							<li class="active"><i class="fa fa-dashboard"></i> Dashboard
							</li>
						</ol>
					</div>
				</div>
				<!-- /.row -->
				
				

				<div class="row">
					<div class="col-lg-12">
						<h3>Usuários do Sistema:</h3><br />
					</div>
		
					<div class="col-lg-4">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-user fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge" id="numADM">0</div>
										<div>Número de Administradores</div>
									</div>
								</div>
							</div>
						</div>
					</div>
						
					<div class="col-lg-4">
						<div class="panel panel-green">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-user fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge" id="numProfessores">0</div>
										<div>Número de Professores</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-lg-4">
						<div class="panel panel-red">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-user fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge" id="numAlunos">0</div>
										<div>Número de Alunos</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				
					<div class="col-lg-12">
						<h3>Trabalhos de Conclusão:</h3><br />
					</div>
				
					<div class="col-lg-4">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-book fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge" id="numTCC">0</div>
										<div>Número de Trabalhos de Conclusão</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-lg-4">
						<div class="panel panel-green">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-file-pdf-o fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge" id="numMonografias">0</div>
										<div>Número de Monografias</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-lg-4">
						<div class="panel panel-red">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-paperclip fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge" id="numAnexos">0</div>
										<div>Número de Anexos</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-lg-12">
						<h3>Informações de Arquivos:</h3><br />
					</div>
					
					<div class="col-lg-4">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-file fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge" id="numArquivos">0</div>
										<div>Número de Arquivos</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-lg-4">
						<div class="panel panel-green">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-hdd-o fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge" id="numTamanhoArquivos">0</div>
										<div>Tamanho dos Arquivos</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-lg-4">
						<div class="panel panel-red">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-cloud-download fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge" id="numDownloads">0</div>
										<div>Número de Downloads</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

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
		function atualizaValores(){
			$.post("/rest/dashboard", {}).done(function(response) {
				$("#numTCC").html(response.numeroTrabalhosDeConclusao);
				$("#numMonografias").html(response.numeroMonografias);
				$("#numAnexos").html(response.numeroAnexos);
				$("#numADM").html(response.numeroAdministradoresDeConteudo);
				$("#numProfessores").html(response.numeroProfessores);
				$("#numAlunos").html(response.numeroAlunos);
				$("#numArquivos").html(response.numeroMonografias + response.numeroAnexos);
				$("#numTamanhoArquivos").html(response.tamanhoArquivos);
				$("#numDownloads").html(response.numeroDownloads);
			}).fail(function(e) {
				$(".se-pre-con-dark").fadeOut("slow");
				$("#botao-modal-nao").hide();
				$("#botao-modal-sim").text("Ok");
				$("#botao-modal-sim").unbind();
				$("#botao-modal-sim").on("click", function(){
					$("#modal").modal("hide");
					$("body").removeClass("modal-open");
					$(".modal-backdrop").fadeOut("slow");
					$(".modal-backdrop").remove();
				});
				$("#texto-modal").html("Ocorreu um erro ao tentar puxar as informações da dashboard. Por gentileza contate o administrador do sistema.");
				$("#modal").modal("show");
			});
		}
		
		$(document).on("load", atualizaValores());
	
		setInterval(atualizaValores, 10000);
	</script>

</body>

</html>
