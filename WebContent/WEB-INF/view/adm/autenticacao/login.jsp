<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="baseURL" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="pt-br" class="login-html">

<head>
	<c:url value="../includes/meta_informations.jsp"
		var="metainformations"></c:url>
	
	<!-- Informações de Autor do projeto -->
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
								<h4 class="modal-title">
									<center><i class="fa fa-book"></i>&nbsp;Ctrl+T - Controle de Trabalhos Acadêmicos</center>
								</h4>
							</div>
							<div class="modal-body">
								
									<fieldset>
										<div class="form-group">
											<label><i class="fa fa-user"></i>&nbsp;Login: </label>
											<input class="form-control" placeholder="Login" id="login" name="login"
												type="login" autofocus>
										</div>
										
										<div class="form-group">
											<label><i class="fa fa-key"></i>&nbsp;Senha: </label>
											<input class="form-control" placeholder="Senha" id="senha"
												name="password" type="password" value="">
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
								<button id=botao-logar" type="button" class="btn btn-default left"><i class="fa fa-question-circle"></i>&nbsp;Esqueceu a senha?</button>
								<button id=botao-logar" type="submit" class="btn btn-primary">Logar&nbsp;<i class="fa fa-arrow-circle-right"></i></button>
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

		<c:if test="${not empty param.redirect}">
			<script type="text/javascript">
				var redirect = '${baseURL}/${param.redirect}';
			</script>
		</c:if>
		
		<c:if test="${empty param.redirect}">
			<script type="text/javascript">
				var redirect = '';
			</script>
		</c:if>

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
					
						$.post("${baseURL}/efetua_login", {
							"login" : $("#login").val(), 
							"senha" : $("#senha").val(),
							"tipo" : $("#tipo").val()
						}).done(function(response) {
							$(".se-pre-con-dark").fadeOut("slow");
							
							if (response.status == "SUCCESS") {
								$("body").removeClass("modal-open");
								$(".modal-backdrop").fadeOut("slow");
								$(".modal-backdrop").remove();
								
								if(redirect == ""){
									if($("#tipo").val() == "1"){
										window.location = "${baseURL}/adm/dashboard";
									}else{
										window.location = "${baseURL}/adm/profile";
									}
								}else{
									window.location = redirect; 
								}
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
							$("#texto-modal").html("Erro ao tentar realizar o login, por gentileza tente novamente!");
							$("#modal").modal("show");
						});
					}	
				});
		</script>
</body>

</html>


