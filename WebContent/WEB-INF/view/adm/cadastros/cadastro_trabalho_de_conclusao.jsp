<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="baseURL" value="${pageContext.request.contextPath}" />

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
										<label>Resumo:</label> <textarea id="resumo"
											name="resumo" class="form-control"></textarea>
									</div>
								</div>
							</div>
							
							<div class="col-lg-12">
								<div class="col-lg-12">
									<div class="form-group">
										<label>Palavras Chave:</label> <input type="text" id="palavrasChave"
											name="palavrasChave" class="form-control" autocomplete="off">
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
										<label>Monografia: </label> <input type="file" id="monografia" name="monografia" class="form-control" accept="application/pdf">
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
						
						<div id="panel-information" class="col-lg-12">
							<div class="col-lg-12">
								<div class="panel panel-success">
									<div id="panel-message" class="panel-heading"></div>
								</div>
							</div>
						</div>
						

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
							<th>Anexos</th>
							<th>Monografia</th>
							<th>Título</th>
							<th>Aluno 1</th>
							<th>Aluno 2</th>
							<th>Aluno 3</th>
							<th>Aluno 4</th>
							<th>Aluno 5</th>
							<th>Professor 1</th>
							<th>Professor 2</th>
							<th>Professor 3</th>
							<th>Data de Publicação</th>
							<th>Palavras Chave</th>
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

	<!-- JavaScript Include -->
	<c:url value="../includes/javascript.jsp" var="javascript"></c:url>
	<c:import url="${javascript}"></c:import>
	
	<!-- JavaScript Form Include -->
	<c:url value="../includes/javascript_form.jsp" var="javascript_form"></c:url>
	<c:import url="${javascript_form}"></c:import>
	
	<!-- Modal-Anexo Include -->
	<c:url value="../includes/modal_anexo.jsp" var="modal_anexo"></c:url>
	<c:import url="${modal_anexo}"></c:import>
	
	<!-- Modal Include -->
	<c:url value="../includes/modal.jsp" var="modal"></c:url>
	<c:import url="${modal}"></c:import>
	
	<!-- DataTable Include -->
	<c:url value="../includes/javascript_datatables.jsp" var="javascript_datatables"></c:url>
	<c:import url="${javascript_datatables}"></c:import>

	<script type="text/javascript">
		$(document).ready(function(){
			//Marca no menu a opção correta sobre a página
			marcaMenu("#cadastros", "#cadastroTrabalhoDeConclusao");
			
			dataTableTCC(
					"#dataTable",
					"${baseURL}/rest/lista/trabalho_de_conclusao",
					[
							{
								"data" : function(o) {
									if(o.ativo){
										return '<center><button onclick="inativar(\'trabalho_de_conclusao\', '
											+ o.id
											+ ')" class="btn btn-primary"><i class="fa fa-ban" aria-hidden="true"></i></button></center>';
									}else{
										return '<center><button onclick="inativar(\'trabalho_de_conclusao\', '
											+ o.id
											+ ')" class="btn btn-primary"><i class="fa fa-check" aria-hidden="true"></i></button></center>';
									}
								}
							}, {
								"data" : function(o) {
									return '<center><button onclick="excluir(\'trabalho_de_conclusao\', '
											+ o.id
											+ ')" class="btn btn-primary"><i class="fa fa-trash" aria-hidden="true"></i></button></center>';
								}
							}, {
								"data" : function(o) {
									return '<center><button onclick="carregarAlteracao('
											+ o.id
											+ ')" class="btn btn-primary"><i class="fa fa-pencil" aria-hidden="true"></i></button></center>';
								}
							},{
								"data" : function(o) {
									return '<center>'
												+'<button onclick="carregarAnexo(' + o.id + ')" class="btn btn-primary"><i class="fa fa-paperclip" aria-hidden="true"></i></button>'
											+'</center>'
								}
							},{
								"data" : function(o) {
									if(o.monografia != null){
										return '<center>'
											+'<a target="_blank" href="${baseURL}/monografias/' + o.id + "/" + o.monografia.nome + '">'
												+'<button class="btn btn-primary"><i class="fa fa-file-pdf-o" aria-hidden="true"></i></button>'
											+'</a>'
										+'</center>';
									}else{
										return "";
									}
								}
							}, {
								"data" : "titulo"
							}, {
								"data" : function(o) {
									if(o.listaAlunos[0] != null){
										return o.listaAlunos[0].nome;
									}else{
										return "";
									}
								}
							}, {
								"data" : function(o) {
									if(o.listaAlunos[1] != null){
										return o.listaAlunos[1].nome;
									}else{
										return "";
									}
								}
							}, {
								"data" : function(o) {
									if(o.listaAlunos[2] != null){
										return o.listaAlunos[2].nome;
									}else{
										return "";
									}
								}
							}, {
								"data" : function(o) {
									if(o.listaAlunos[3] != null){
										return o.listaAlunos[3].nome;
									}else{
										return "";
									}
								}
							}, {
								"data" : function(o) {
									if(o.listaAlunos[4] != null){
										return o.listaAlunos[4].nome;
									}else{
										return "";
									}
								}
							}, {
								"data" : function(o) {
									if(o.listaProfessores[0] != null){
										return o.listaProfessores[0].nome;
									}else{
										return "";
									}
								}
							}, {
								"data" : function(o) {
									if(o.listaProfessores[1] != null){
										return o.listaProfessores[1].nome;
									}else{
										return "";
									}
								}
							}, {
								"data" : function(o) {
									if(o.listaProfessores[2] != null){
										return o.listaProfessores[2].nome;
									}else{
										return "";
									}
								}
							}, {
								"data" : function(o) {
									return moment(new Date(o.dataPublicacao)).lang("pt-br").format('L') + " " + moment(new Date(o.dataPublicacao)).lang("pt-br").format('LTS');
								}
							}, {
								"data" : "palavrasChave"
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
				
				//Mensagem que irá conter a mensagem de erro
				var mensagemAluno = "";
				var mensagemProfessor = "";
				
				//Array que irá conter os campos de alunos e professores
				var listaAlunos = [];
				var listaProfessores = [];
				
				listaAlunos[1] = $("#aluno1");
				listaAlunos[2] = $("#aluno2");
				listaAlunos[3] = $("#aluno3");
				listaAlunos[4] = $("#aluno4");
				listaAlunos[5] = $("#aluno5");
				
				for(var i = 0; i < listaAlunos.length; i++){
					if($(listaAlunos[i]).val() != ""){
						for(var j = i + 1; j < listaAlunos.length; j++){
							if($(listaAlunos[j]).val() != "" && $(listaAlunos[i]).val() == $(listaAlunos[j]).val()){
								if(mensagemAluno == ""){
									mensagemAluno = "Existem alunos repetidos para o mesmo trabalho de conclusão. Os repetidos foram removidos, por gentileza revise os dados.";
								}
								
								$(listaAlunos[j]).val("");
							}
						}
					}
				}
				
				listaProfessores[1] = $("#professor1");
				listaProfessores[2] = $("#professor2");
				listaProfessores[3] = $("#professor3");

				for(var i = 0; i < listaProfessores.length; i++){
					if($(listaProfessores[i]).val() != ""){
						for(var j = i + 1; j < listaProfessores.length; j++){
							if($(listaProfessores[j]).val() != "" && $(listaProfessores[i]).val() == $(listaProfessores[j]).val()){
								if(mensagemProfessor == ""){
									if(mensagemAluno != ""){
										mensagemProfessor = "<br /><br />"
									}
									
									mensagemProfessor += "Existem professores repetidos para o mesmo trabalho de conclusão. Os repetidos foram removidos, por gentileza revise os dados.";
								}
								
								$(listaProfessores[j]).val("");
							}
						}
					}
				}
				
				if( (mensagemAluno + mensagemProfessor) != ""){
					$("#botao-modal-nao").hide();
					$("#botao-modal-sim").text("Ok");
					$("#botao-modal-sim").unbind();
					$("#botao-modal-sim").on("click", function(){
						$("#modal").modal("hide");
						$("body").removeClass("modal-open");
						$(".modal-backdrop").fadeOut("slow");
						$(".modal-backdrop").remove();
					});
					$("#texto-modal").html(mensagemAluno + mensagemProfessor);
					$("#modal").modal("show");
					
					return false;
				}

				if($("#cadastrar").html() == "Cadastrar"){
					moment.locale('pt-BR');
					
					if($("#monografia").val() == ""){
						manterEntidade(1,"trabalho_de_conclusao", {
							"titulo" : $("#titulo").val(),
							"resumo" : $("#resumo").val(),
							"palavrasChave" : $("#palavrasChave").val(),
							"dataPublicao" : moment().format("L") + " " + moment().format("LT"),
							"listaAlunos[0].id" : $("#aluno1").val() == "" ? 0 : $("#aluno1").val(),
							"listaAlunos[1].id" : $("#aluno2").val() == "" ? 0 : $("#aluno2").val(),
							"listaAlunos[2].id" : $("#aluno3").val() == "" ? 0 : $("#aluno3").val(),
							"listaAlunos[3].id" : $("#aluno4").val() == "" ? 0 : $("#aluno4").val(),
							"listaAlunos[4].id" : $("#aluno5").val() == "" ? 0 : $("#aluno5").val(),
							"listaProfessores[0].id" : $("#professor1").val() == "" ? 0 : $("#professor1").val(),
							"listaProfessores[1].id" : $("#professor2").val() == "" ? 0 : $("#professor2").val(),
							"listaProfessores[2].id" : $("#professor3").val() == "" ? 0 : $("#professor3").val()
						});
					}else{
						var formData = new FormData();
						formData.append("monografia", $("#monografia")[0].files[0]);
						
						manterEntidadeComUpload(1,"trabalho_de_conclusao", {
							"titulo" : $("#titulo").val(),
							"resumo" : $("#resumo").val(),
							"palavrasChave" : $("#palavrasChave").val(),
							"dataPublicao" : moment().format("L") + " " + moment().format("LT"),
							"listaAlunos[0].id" : $("#aluno1").val() == "" ? 0 : $("#aluno1").val(),
							"listaAlunos[1].id" : $("#aluno2").val() == "" ? 0 : $("#aluno2").val(),
							"listaAlunos[2].id" : $("#aluno3").val() == "" ? 0 : $("#aluno3").val(),
							"listaAlunos[3].id" : $("#aluno4").val() == "" ? 0 : $("#aluno4").val(),
							"listaAlunos[4].id" : $("#aluno5").val() == "" ? 0 : $("#aluno5").val(),
							"listaProfessores[0].id" : $("#professor1").val() == "" ? 0 : $("#professor1").val(),
							"listaProfessores[1].id" : $("#professor2").val() == "" ? 0 : $("#professor2").val(),
							"listaProfessores[2].id" : $("#professor3").val() == "" ? 0 : $("#professor3").val()
						}, "monografia", formData);
					}
				}else{
					if($("#monografia").val() == ""){
						manterEntidade(2,"trabalho_de_conclusao", {
							"id" : $("#id").val(),
							"titulo" : $("#titulo").val(),
							"resumo" : $("#resumo").val(),
							"palavrasChave" : $("#palavrasChave").val(),
							"listaAlunos[0].id" : $("#aluno1").val() == "" ? 0 : $("#aluno1").val(),
							"listaAlunos[1].id" : $("#aluno2").val() == "" ? 0 : $("#aluno2").val(),
							"listaAlunos[2].id" : $("#aluno3").val() == "" ? 0 : $("#aluno3").val(),
							"listaAlunos[3].id" : $("#aluno4").val() == "" ? 0 : $("#aluno4").val(),
							"listaAlunos[4].id" : $("#aluno5").val() == "" ? 0 : $("#aluno5").val(),
							"listaProfessores[0].id" : $("#professor1").val() == "" ? 0 : $("#professor1").val(),
							"listaProfessores[1].id" : $("#professor2").val() == "" ? 0 : $("#professor2").val(),
							"listaProfessores[2].id" : $("#professor3").val() == "" ? 0 : $("#professor3").val()
						});
					}else{
						var formData = new FormData();
						formData.append("monografia", $("#monografia")[0].files[0]);
						
						manterEntidadeComUpload(2,"trabalho_de_conclusao", {
							"id" : $("#id").val(),
							"titulo" : $("#titulo").val(),
							"resumo" : $("#resumo").val(),
							"palavrasChave" : $("#palavrasChave").val(),
							"listaAlunos[0].id" : $("#aluno1").val() == "" ? 0 : $("#aluno1").val(),
							"listaAlunos[1].id" : $("#aluno2").val() == "" ? 0 : $("#aluno2").val(),
							"listaAlunos[2].id" : $("#aluno3").val() == "" ? 0 : $("#aluno3").val(),
							"listaAlunos[3].id" : $("#aluno4").val() == "" ? 0 : $("#aluno4").val(),
							"listaAlunos[4].id" : $("#aluno5").val() == "" ? 0 : $("#aluno5").val(),
							"listaProfessores[0].id" : $("#professor1").val() == "" ? 0 : $("#professor1").val(),
							"listaProfessores[1].id" : $("#professor2").val() == "" ? 0 : $("#professor2").val(),
							"listaProfessores[2].id" : $("#professor3").val() == "" ? 0 : $("#professor3").val()
						}, "monografia", formData);
					}
				}
			}
		});
		
		function carregarAlteracao(id) {
			//Função que habilita os campos
			alterar();
			
			$.post("${baseURL}/rest/json/trabalho_de_conclusao", {"id" : id}).done(function(response) {
				$(".se-pre-con-dark").hide()
				
				$("#id").val(response.id);
				$("#titulo").val(response.titulo);
				$("#resumo").val(response.resumo);
				$("#palavrasChave").val(response.palavrasChave);
				$("#aluno1").val(response.listaAlunos[0].id);
				
				if(response.listaAlunos[1] != null){
					$("#aluno2").val(response.listaAlunos[1].id);	
				}
				
				if(response.listaAlunos[2] != null){
					$("#aluno3").val(response.listaAlunos[2].id);
				}
				
				if(response.listaAlunos[3] != null){
					$("#aluno4").val(response.listaAlunos[3].id);
				}
				
				if(response.listaAlunos[4] != null){
					$("#aluno5").val(response.listaAlunos[4].id);
				}
				
				$("#professor1").val(response.listaProfessores[0].id);
				
				if(response.listaAlunos[1] != null){
					$("#professor2").val(response.listaProfessores[1].id);
				}
				
				if(response.listaAlunos[2] != null){
					$("#professor3").val(response.listaProfessores[2].id);
				}
				
				$("#panel-information").show();
				
				if(response.monografia != null){
					$("#panel-message").html('O trabalho de conclusão em alteração possui o arquivo <a target="_blank" href="${baseURL}/monografias/'+response.id+'/'+response.monografia.nome+'">'+response.monografia.nome+'</a> associado. '
							+ 'Caso não inclua um novo arquivo na alteração, o arquivo será mantido. Caso um novo arquivo seja associado, o atual será sobreposto.' );
				}else{
					$("#panel-message").html('O trabalho de conclusão em alteração não possui uma monografia associada.');
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
				$("#texto-modal").html("Erro ao tentar carregar os dados da linha de pesquisa para serem alterados. Por gentileza tente novamente!");
				$("#modal").modal("show");
			});
		}
		
		function carregarAnexo(id){
			abrirModalAnexo(id);
			
			$("#modal-anexo").modal("show");
		}
		
		$('#modal-anexo').on('shown.bs.modal', function (e) {
			//Ajusta a coluna da DataTable
		    $.fn.dataTable.tables( {visible: true, api: true} ).columns.adjust();
			
			//Caso exista, a mensagem "Nenhum registro encontrad" ficará com o espaçamento de 6 colunas
		    $("#dataTableAnexo td.dataTables_empty").attr("colspan",6);
		});
		
		$('#modal-anexo').on('hide.bs.modal', function (e) {
			//Destroy a DataTable
			$('#dataTableAnexo').DataTable().destroy();
		});
		
		//Ao teclar o enter, clica no botão sim do modal
		$("#modal").keyup(function(event){
		    if(event.keyCode == 13){
		        $("#botao-modal-sim").click();
		    }
		});
	</script>
	
	

</body>

</html>
