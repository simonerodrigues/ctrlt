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
							Cadastro de Trabalho de Conclusão
						</h1>
						<ol class="breadcrumb">
							<li>
								<a href="dashboard"><i class="fa fa-dashboard"></i> Dashboard</a>
							</li>
							
							<li class="active">
								<i class="fa fa-book"></i> Cadastro de TCC
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
								<div class="col-lg-12">
									<div class="form-group">
										<label>Título: (*)</label> <input type="text" id="titulo"
											name="titulo" class="form-control" autocomplete="off">
									</div>
								</div>
							</div>
							
							<div class="col-lg-12">
								<div class="col-lg-12">
									<div class="form-group">
										<label>Descrição:</label> <textarea id="descricao"
											name="descricao" class="form-control"></textarea>
									</div>
								</div>
							</div>
							
							<div class="col-lg-12">
								<div class="col-lg-4">
									<div class="form-group">
										<label>Aluno 1: (*)</label>
										<select class="form-control" id="aluno1" name="aluno1">
											<c:forEach items="${alunos}" var="aluno">
												<option value="${aluno.id}">${aluno.nome}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								
								<div class="col-lg-4">
									<div class="form-group">
										<label>Aluno 2: </label> 
										<select class="form-control" id="aluno2" name="aluno2">
											<option></option>
											<c:forEach items="${alunos}" var="aluno">
												<option value="${aluno.id}">${aluno.nome}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								
								<div class="col-lg-4">
									<div class="form-group">
										<label>Aluno 3: </label>
										<select class="form-control" id="aluno3" name="aluno3">
											<option></option>
											<c:forEach items="${alunos}" var="aluno">
												<option value="${aluno.id}">${aluno.nome}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
							
							<div class="col-lg-12">
								<div class="col-lg-4">
									<div class="form-group">
										<label>Aluno 4: </label>
										<select class="form-control" id="aluno4" name="aluno4">
											<option></option>
											<c:forEach items="${alunos}" var="aluno">
												<option value="${aluno.id}">${aluno.nome}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								
								<div class="col-lg-4">
									<div class="form-group">
										<label>Aluno 5: </label>
										<select class="form-control" id="aluno5" name="aluno5">
											<option></option>
											<c:forEach items="${alunos}" var="aluno">
												<option value="${aluno.id}">${aluno.nome}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								
								<div class="col-lg-4">
									<div class="form-group">
										<label>Monografia: (*)</label> <input type="file" id="monografia"
											name="monografia" class="form-control" autocomplete="off">
									</div>
								</div>
							</div>
							
							<div class="col-lg-12">
								<div class="col-lg-4">
									<div class="form-group">
										<label>Professor 1: (*)</label>
										<select class="form-control" id="professor1" name="professor1">
											<c:forEach items="${professores}" var="professor">
												<option value="${professor.id}">${professor.nome}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								
								<div class="col-lg-4">
									<div class="form-group">
										<label>Professor 2: </label>
										<select class="form-control" id="professor2" name="professor2">
											<option></option>
											<c:forEach items="${professores}" var="professor">
												<option value="${professor.id}">${professor.nome}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								
								<div class="col-lg-4">
									<div class="form-group">
										<label>Professor 3: </label>
										<select class="form-control" id="professor3" name="professor3">
											<option></option>
											<c:forEach items="${professores}" var="professor">
												<option value="${professor.id}">${professor.nome}</option>
											</c:forEach>
										</select>
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
	
	<script type="text/javascript">
		$(document).ready(function(){
			//Marca no menu a opção correta sobre a página
			marcaMenu("#cadastros", "#cadastroTrabalhoDeConclusao");
			
			dataTable(
					"#dataTable",
					"/rest/lista/linha_de_pesquisa",
					[
							{
								"data" : function(o) {
									if(o.ativo){
										return '<center><button onclick="inativar(\'linha_de_pesquisa\', '
											+ o.id
											+ ')" class="btn btn-primary"><i class="fa fa-ban" aria-hidden="true"></i></button></center>';
									}else{
										return '<center><button onclick="inativar(\'linha_de_pesquisa\', '
											+ o.id
											+ ')" class="btn btn-primary"><i class="fa fa-check" aria-hidden="true"></i></button></center>';
									}
								}
							},
							{
								"data" : function(o) {
									return '<center><button onclick="excluir(\'linha_de_pesquisa\', '
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
								"data" : "ativo"
							} ]
				);
		});
	</script>
	
	<script type="text/javascript">
		//Quando todos os dados estiverem validos, cadastra a linha de pesquisa
		$.validator.setDefaults({
			submitHandler : function() {
				if($("#cadastrar").html() == "Cadastrar"){
					manterEntidade(1,"linha_de_pesquisa", {
						"nome" : $("#nome").val()
					});
				}else{
					manterEntidade(2,"linha_de_pesquisa", {
						"id" : $("#id").val(),
						"nome" : $("#nome").val()						
					});
				}
			}
		});
		
		function carregarAlteracao(id) {
			//Função que habilita os campos
			alterar();
			
			$.post("/rest/json/linha_de_pesquisa", {"id" : id}).done(function(response) {
				$(".se-pre-con-dark").hide()
				
				$("#id").val(response.id);
				$("#nome").val(response.nome);
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
				$("#texto-modal").text("Erro ao tentar carregar os dados da linha de pesquisa para serem alterados. Por gentileza tente novamente!");
				$("#modal").modal("show");
			});
		}
	</script>

</body>

</html>
