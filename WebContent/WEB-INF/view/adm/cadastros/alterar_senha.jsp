<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<c:url value="../includes/meta_informations.jsp"
	var="metainformations"></c:url>

<!-- Informações de Autor do projeto -->
<c:import url="${metainformations}"></c:import>

<!-- CSS Include -->
<c:url value="../includes/css.jsp" var="css"></c:url>
<c:import url="${css}"></c:import>

<!-- CSS DataTable Include -->
<c:url value="../includes/css_datatables.jsp" var="css_datatables"></c:url>
<c:import url="${css_datatables}"></c:import>

</head>

<body>
	<div class="modal-loading"></div>

	<div class="se-pre-con"></div>

	<div id="wrapper">

		<!-- Navigation -->
		<c:url value="../includes/navbar.jsp" var="navbar"></c:url>
		<c:url value="../adm/includes/sidebar_adm.jsp" var="sidebarADM"></c:url>

		<c:import url="${navbar}">
			<c:param name="sidebar" value="../${sidebarADM}" />
		</c:import>

		<div id="page-wrapper">

			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							Alterar Senha
						</h1>
						<ol class="breadcrumb">
							<li>
								<a href="dashboard"><i class="fa fa-dashboard"></i> Dashboard</a>
							</li>
							
							<li class="active">
								<i class="fa fa-key"></i> Alterar Senha
							</li>
						</ol>
					</div>
				</div>
				<!-- /.row -->

				<div class="row">
					<form id="form" role="form" method="post">
						<fieldset>						
							<div class="col-lg-12">
								<div class="col-lg-4">
									<div class="form-group">
										<label>Senha Atual: (*)</label> <input type="password" id="senhaAtual"
											name="senhaAtual" class="form-control" autocomplete="off">
									</div>
								</div>
								
								<div class="col-lg-4">
									<div class="form-group">
										<label>Nova Senha: (*)</label> <input type="password" id="senha"
											name="senha" class="form-control" autocomplete="off">
									</div>
								</div>
								
								<div class="col-lg-4">
									<div class="form-group">
										<label>Confirmação de Senha: (*)</label> <input type="password" id="confirmacaoSenha"
											name="confirmacaoSenha" class="form-control" autocomplete="off">
									</div>
								</div>
							</div>
							
						</fieldset>

						<div class="col-lg-6">
							<p class="help-block">Todos os campos com (*) são de
								preenchimento obrigatório</p>
						</div>

						<div class="col-lg-6">
							<div class="form-group clearfix">
								<button id="cadastrar" type="submit" class="btn btn-primary right">Alterar Senha</button>
							</div>
						</div>

						<div class="col-lg-12">
							<hr />
						</div>
					</form>
				</div>
				<!-- /.row -->
				
			</div>
			<!-- /.container-fluid -->

		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->
	
	<!-- Modal Include -->
	<c:url value="../includes/modal.jsp" var="modal"></c:url>
	<c:import url="${modal}"></c:import>

	<!-- JavaScript Include -->
	<c:url value="../includes/javascript.jsp" var="javascript"></c:url>
	<c:import url="${javascript}"></c:import>
	
	<!-- JavaScript Form Include -->
	<c:url value="../includes/javascript_form.jsp" var="javascript_form"></c:url>
	<c:import url="${javascript_form}"></c:import>
	
	<script type="text/javascript">
		//Quando todos os dados estiverem validos, cadastra a linha de pesquisa
		$.validator.setDefaults({
			submitHandler : function() {
				confirmacaoAlteracaoSenha();
			}
		});
		
		//Função de confirmação da alteração de senha
		function confirmacaoAlteracaoSenha() {
			$("#botao-modal-nao").show();
			$("#botao-modal-sim").text("Sim");
			
			$("#texto-modal").html("Confirma a alteração da senha?")
			
			$("#botao-modal-sim").unbind();
			$("#botao-modal-sim").on("click", function(){
				$("#modal").modal("hide");
				$("body").removeClass("modal-open");
				$(".modal-backdrop").fadeOut("slow");
				$(".modal-backdrop").remove();
				alterarSenha();
			});
			
			$("#modal").modal("show");
		}
		
		function alterarSenha(){
			var senhaAtual = $("#senhaAtual").val();
			var novaSenha = $("#senha").val();
			
			$.post("/rest/altera/senha", {"senhaAtual" : senhaAtual, "novaSenha" : novaSenha}).done(function(response) {
				$(".se-pre-con-dark").fadeIn("slow");
				
				if (response.status == "SUCCESS") {
					$(".se-pre-con-dark").fadeOut("slow");
					
					$("#botao-modal-nao").hide();
					$("#botao-modal-sim").text("Ok");
					$("#botao-modal-sim").unbind();
					$("#botao-modal-sim").on("click", function(){
						$("#modal").modal("hide");
						$("body").removeClass("modal-open");
						$(".modal-backdrop").fadeOut("slow");
						$(".modal-backdrop").remove();
						
						limparCampos();
					});
			
					$("#texto-modal").html(response.result);
					
					$("#modal").modal("show");
				} else {
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
					
					$("#texto-modal").html(response.result);
					$("#modal").modal("show");
				}
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
				
				$("#texto-modal").html("Erro ao alterar a senha, por gentileza contate o administrador do sistema.");
				$("#modal").modal("show");
			});
		}
		
		function limparCampos(){
			$("#senhaAtual").val("");
			$("#senha").val("");
			$("#confirmacaoSenha").val("");
			
			// Limpa os campos (input)
			var $campos = $("input");

			for (var i = 0; i < $campos.length; i++) {
				if($campos[i].type != "checkbox"){
					$campos[i].value = "";
				}else{
					$($campos[i]).removeAttr('checked');
				}
				
				$($campos[i]).closest('.form-group').removeClass('has-error');
				$($campos[i]).closest('.form-group').removeClass('has-success');
				$($campos[i]).attr('disabled', false);
			}
		}
	</script>

</body>

</html>
