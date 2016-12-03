<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="baseURL" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="pt-br" class="login-html">

<head>
	<c:url value="../includes/meta_informations.jsp"
		var="metainformations"></c:url>
		
	<!-- Informaçõe de Autor do projeto -->
	<c:import url="${metainformations}"></c:import>
	
	<!-- CSS Include -->
	<c:url value="../includes/css.jsp" var="css"></c:url>
	<c:import url="${css}"></c:import>
	
	<style type="text/css">
		@media ( max-width :768px) {
			#modal-login {
				position: initial;
			}
		}
	</style>
</head>

<body class="login-body">

	<div class="se-pre-con"></div>

	<div class="se-pre-con-dark"></div>

	<div class="container container-login">
		<div class="row">
			<div class="modal" id="modal-login" tabindex="-1" role="dialog"
				aria-labelledby="modalCancelar" data-backdrop="static"
				data-keyboard="false">
				<div class="modal-dialog" role="document">
					<form id="form" role="form" method="post">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title text-center">
									<i class="fa fa-book"></i>&nbsp;Ctrl+T - Controle de Controle de Trabalhos Acadêmicos
								</h4>
							</div>
							<div class="modal-body">
								<fieldset>
									<div class="form-group">
										<label><i class="fa fa-user"></i>&nbsp;Email: </label>
										<input class="form-control" placeholder="E-mail" id="email" name=""email""
											type="email" autofocus>
									</div>
									
									<div class="form-group">
										<label><i class="fa fa-tag"></i>&nbsp;Tipo de acesso: </label>
										<select class="form-control" name="tipo" id="tipo" >
											<option value="1">Administrador de Conteúdo</option>
											<option value="2">Professor</option>
											<option value="3">Aluno</option>
										</select>
									</div>
								</fieldset>								
							</div>
							<div class="modal-footer">
								<button id="botao-enviar" type="submit" class="btn btn-primary">Enviar Senha&nbsp;<i class="fa fa-paper-plane-o"></i></button>
							</div>	
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
		
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
	
		$("#modal-login").modal("show");
		
		$("body").removeClass("modal-open");
		$(".modal-backdrop").fadeOut("slow");
		$(".modal-backdrop").remove();
		
		$.validator.setDefaults({
			submitHandler :
				//Função para cadastrar a entidade
				function realizarLogin(acao,entidade, data) {	
					$(".se-pre-con-dark").fadeIn("slow");
				
					$.post("${baseURL}/enviar_senha", {
						"email" : $("#email").val(),
						"tipo" : $("#tipo").val()
					}).done(function(response) {
						$(".se-pre-con-dark").fadeOut("slow");
						
						if (response.status == "SUCCESS") {
							$("body").removeClass("modal-open");
							$(".modal-backdrop").fadeOut("slow");
							$(".modal-backdrop").remove();
							
							$("#botao-modal-nao").hide();
							$("#botao-modal-sim").text("Ok");
							$("#texto-modal").html(response.result);
							$("#botao-modal-sim").unbind();
							$("#botao-modal-sim").on("click", function(){
								window.location = "${baseURL}/login";
							});
							$("#modal").modal("show");
						} else {
							$("#botao-modal-nao").hide();
							$("#botao-modal-sim").text("Ok");
							$("#texto-modal").html(response.result);
							$("#botao-modal-sim").unbind();
							$("#botao-modal-sim").on("click", function(){
								$("#modal").modal("hide");
								$("body").removeClass("modal-open");
								$(".modal-backdrop").fadeOut("slow");
								$(".modal-backdrop").remove();
							});
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
						$("#texto-modal").html("Erro ao tentar realizar o reset de senha, por gentileza tente novamente!");
						$("#modal").modal("show");
					});
				}	
			});
	</script>
</body>

</html>


