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
							Cadastro de Professor
						</h1>
						<ol class="breadcrumb">
							<li>
								<a href="dashboard"><i class="fa fa-dashboard"></i> Dashboard</a>
							</li>
							
							<li class="active">
								<i class="fa fa-user-plus"></i> Cadastro de Professor
							</li>
						</ol>
					</div>
				</div>
				<!-- /.row -->

				<div class="row">
					<form id="form" role="form" method="post">
						<fieldset disabled="disabled">
							<input type="hidden" id="id" name="id" class="form-control" autocomplete="off">
						
							<div class="col-lg-12">
								<div class="col-lg-4">
									<div class="form-group">
										<label>Nome: (*)</label> <input type="text" id="nome"
											name="nome" class="form-control" autocomplete="off">
									</div>
								</div>
								<div class="col-lg-4">
									<div class="form-group">
										<label>E-mail Alternativo: (*)</label> <input type="email" id="emailAlternativo"
											name="emailAlternativo" class="form-control" autocomplete="off">
									</div>
								</div>
								<div class="col-lg-4">
									<div class="form-group">
										<label>E-mail Fatec: </label> <input type="email" id="emailFatec"
											name="emailFatec" class="form-control" autocomplete="off">
									</div>
								</div>
							</div>
							
							<div class="col-lg-12">
								<div class="col-lg-4">
									<div class="form-group">
										<label>Login: (*)</label> <input type="text" id="login"
											name="login" class="form-control" />
									</div>
								</div>
								
								<div class="col-lg-4">
									<div class="form-group">
										<label>Senha: (*)</label> <input type="password" id="senha"
											name="senha" class="form-control" />
									</div>
								</div>
								
								<div class="col-lg-4">
									<div class="form-group">
										<label>Confirmação Senha: (*)</label> <input type="password" id="confirmacaoSenha"
											name="confirmacaoSenha" class="form-control" />
									</div>
								</div>
							</div>
							
							<div class="col-lg-12">
								<div class="col-lg-4">
									<div class="form-group">
										<label>Telefone:</label> <input type="text" id="telefone"
											name="telefone" class="form-control" placeholder="(00) 0000-0000" />
									</div>
								</div>
								
								<div class="col-lg-4">
									<div class="form-group">
										<label>Celular:</label> <input type="text" id="celular"
											name="celular" class="form-control" placeholder="(00) 00000-0000" />
									</div>
								</div>
								
								<div class="col-lg-4">
									<div class="form-group">
										<label>Coordenador? </label> 
											<select id="coordenador" name="coordenador" class="form-control" />
												<option value="1">Sim</option>
												<option value="0" selected="selected">Não</option>
											</select>
									</div>
								</div>
								
							</div>
							
							<div class="col-lg-12">
								<div class="col-lg-12">
									<label>Linhas de Pesquisa:</label>
								</div> 
								<div class="col-lg-12">
									<div class="panel panel-default">
										<div class="panel-body">
											<c:forEach var="linhaDePesquisa" items="${linhasDePesquisa}" varStatus="linha">
												<div class="col-lg-3">
													<div class="checkbox">
													    <label>
													      <input type="checkbox" class="linhaDePesquisa" value="${linhaDePesquisa.id}"> ${linhaDePesquisa.nome}
													    </label>
													</div>
												</div>
											</c:forEach>
										</div>
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
								<button id="cadastrar" type="submit" class="btn btn-primary right" disabled="disabled">Cadastrar</button>
								<button id="novo" type="button" class="btn btn-default right">Novo</button>
								<button id="reset" type="reset" class="btn btn-default right" disabled="disabled">Limpar</button>
								<button id="cancelar" type="button" class="btn btn-default right" disabled="disabled">Cancelar</button>
							</div>
						</div>

						<div class="col-lg-12">
							<hr />
						</div>
					</form>
				</div>
				<!-- /.row -->
				
				<table id="dataTable" class="table table-hover table-striped table-bordered dataTable nowrap" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th>Inativar</th>
							<th>Excluir</th>
							<th>Alterar</th>
							<th>Nome</th>
							<th>Login</th>
							<th>Coordenador?</th>
							<th>E-mail Alternativo</th>
							<th>E-mail Fatec</th>
							<th>Telefone</th>
							<th>Celular</th>
							<th class="status">Status</th>
						</tr>
					</thead>
				</table>
				
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
	
	<!-- DataTable Include -->
	<c:url value="../includes/javascript_datatables.jsp" var="javascript_datatables"></c:url>
	<c:import url="${javascript_datatables}"></c:import>
	
	<!-- JSON JS -->
	<script src="/js/vendor/jquery_json/jquery.json.min.js" type="text/javascript"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
			//Marca no menu a opção correta sobre a página
			marcaMenu("#cadastros", "#cadastroProfessor");
			
			dataTable(
					"#dataTable",
					"/rest/lista/professor",
					[
							{
								"data" : function(o) {
									if(o.ativo){
										return '<center><button onclick="inativar(\'professor\', '
											+ o.id
											+ ')" class="btn btn-primary"><i class="fa fa-ban" aria-hidden="true"></i></button></center>';
									}else{
										return '<center><button onclick="inativar(\'professor\', '
											+ o.id
											+ ')" class="btn btn-primary"><i class="fa fa-check" aria-hidden="true"></i></button></center>';
									}
								}
							},
							{
								"data" : function(o) {
									return '<center><button onclick="excluir(\'professor\', '
										+ o.id
										+ ')" class="btn btn-primary"><i class="fa fa-trash" aria-hidden="true"></i></button></center>';
								}
							},
							{
								"data" : function(o) {
									return '<center><button onclick="carregarAlteracao('
										+ o.id
										+ ')" class="btn btn-primary"><i class="fa fa-pencil" aria-hidden="true"></i></button></center>';
								}
							}, {
								"data" : "nome"
							}, {
								"data" : "login"
							}, {
								"data" : function(o) {
									if(o.coordenador){
										return "Sim";
									}else{
										return "Não";
									}
								}
							}, {
								"data" : "emailAlternativo"
							}, {
								"data" : "emailFatec"
							}, {
								"data" : "telefone"
							}, {
								"data" : "celular"
							}, {
								"data" : "ativo"
							} ]
				);
		});
	</script>
	
	<script type="text/javascript">
		//Quando todos os dados estiverem validos, cadastra o periodo
		$.validator.setDefaults({
			submitHandler : function() {
				
				var listaLinhaDePesquisaAll = $(".linhaDePesquisa");
				var listaLinhaDePesquisa = "";
				
				for(var i = 0; i < listaLinhaDePesquisaAll.length; i++) {
					if($(listaLinhaDePesquisaAll[i]).is(":checked")){
						listaLinhaDePesquisa += (listaLinhaDePesquisa == "" ? "" : ",") + $(listaLinhaDePesquisaAll[i]).val();
					}
				}
				
				if($("#cadastrar").html() == "Cadastrar"){
					manterEntidade(1,"professor", {
						"nome" : $("#nome").val(),
						"login" : $("#login").val(),
						"coordenador" : $("#coordenador").val(),
						"senha" : $("#senha").val(),
						"emailAlternativo" : $("#emailAlternativo").val(),
						"emailFatec" : $("#emailFatec").val(),
						"telefone" : $("#telefone").val(),
						"celular" : $("#celular").val(),
						"linhasDePesquisa" : listaLinhaDePesquisa
					});
				}else{
					manterEntidade(2,"professor", {
						"id" : $("#id").val(),
						"nome" : $("#nome").val(),
						"login" : $("#login").val(),
						"coordenador" : $("#coordenador").val(),
						"senha" : $("#senha").val(),
						"emailAlternativo" : $("#emailAlternativo").val(),
						"emailFatec" : $("#emailFatec").val(),
						"telefone" : $("#telefone").val(),
						"celular" : $("#celular").val(),
						"linhasDePesquisa" : listaLinhaDePesquisa
					});
				}
			}
		});
		
		function carregarAlteracao(id) {
			//Função que habilita os campos
			alterar();
			
			$.post("/rest/json/professor", {"id" : id}).done(function(response) {
				$(".se-pre-con-dark").hide();
				
				$("#id").val(response.id);
				$("#nome").val(response.nome);
				$("#login").val(response.login);
				$("#emailAlternativo").val(response.emailAlternativo);
				$("#emailFatec").val(response.emailFatec);
				$("#telefone").val(response.telefone);
				$("#celular").val(response.celular);
				
				$("#coordenador").val(response.coordenador == true ? "1" : "0");
				
				// Limpa os campos (input)
				var $campos = $(".linhaDePesquisa");
				
				for(var i = 0; i < response.listaLinhaDePesquisa.length; i++){
					for(var j = 0; j < $campos.length; j++){
						if($campos[i].type == "checkbox"){
							if($campos[j].value == response.listaLinhaDePesquisa[i].id){
								$($campos[j]).prop("checked", true);
							}
						}
					}
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
				$("#texto-modal").text("Erro ao tentar carregar os dados do professor para serem alterados. Por gentileza tente novamente!");
				$("#modal").modal("show");
			});
		}
	</script>

</body>

</html>
