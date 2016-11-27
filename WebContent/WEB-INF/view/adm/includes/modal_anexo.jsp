<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="baseURL" value="${pageContext.request.contextPath}" />

<div class="modal fade" id="modal-anexo" tabindex="-1" role="dialog"
	aria-labelledby="modalCancelar" data-backdrop="static"
	data-keyboard="false">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">
					<i class="fa fa-book"></i> Ctrl-T - Anexos
				</h4>
			</div>
			<div  id="anexo-page" class="modal-body">
				<form id="form-anexo" role="form" method="post">
					<fieldset disabled="disabled" id="fieldset-anexo">
						<!-- Os campos serão carregados quando o modal for aberto -->						
					</fieldset>
					
					<div class="col-lg-12">
						<div class="col-lg-6">
						</div>
						<div class="col-lg-6">
							<div id="botoes-anexo" class="form-group clearfix">
								<button id="cadastrar-anexo" type="submit" class="btn btn-primary right" disabled="disabled">Cadastrar</button>
								<button id="adicionar-anexo" type="button" class="btn btn-default right" disabled="disabled">Adicionar</button>
								<button id="novo-anexo" type="button" class="btn btn-default right">Novo</button>
							</div>
						</div>
					</div>
				</form>
				
				<table id="dataTableAnexo" class="table table-hover table-striped table-bordered dataTable nowrap" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th>Excluir</th>
							<th>Download?</th>
							<th>Nome</th>
							<th>Data de Upload</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	//Nunca deixa o form realiza o submit, quem realiza o submit é o JQuery(Ajax)
	$("#form-anexo").on("submit", function() {
		return false;
	});
	
	function abrirModalAnexo(id){
		$("#fieldset-anexo").html('<input type="hidden" id="id-tcc" name="id-tcc" class="form-control" value="' +  + '" autocomplete="off">'
				+ '<div class="col-lg-12">'
					+ '<div class="col-lg-12">'
						+ '<div class="col-lg-6">'
							+ '<div class="form-group">'
								+ '<label>Arquivo: </label> <input type="file" ' 
									+ 'name="anexo[]" class="form-control anexo" autocomplete="off">'
							+ '</div>'
						+ ' </div>'
		
						+ '<div class="col-lg-6">'
							+ '<div class="form-group">'
								+ '<label>Disponível para Download?: </label>'
								+ '<select name="visivel[]" class="form-control download">'
									+ '<option value="1">Sim</option>'
									+ '<option value="2">Não</option>'
								+ '</select>'
							+ '</div>'
						+ '</div>'
					+ '</div>'
				+ '</div>'
		);
		
		$("#fieldset-anexo").attr('disabled', true);
		$("#cadastrar-anexo").attr('disabled', true);
		$("#cadastrar-anexo").attr('onclick', "cadastrarAnexo("+id+")");
		$("#novo-anexo").attr('disabled', false);
		$("#adicionar-anexo").attr('disabled', true);
		
		//Carrega a tabela de acordo com os anexos do TCC
		dataTable("#dataTableAnexo", "${baseURL}/rest/lista/anexo/" + id, 
				[
					{
						"data" : function(o) {
							return '<center><button onclick="excluir(\'anexo\', '
									+ o.id
									+ ')" class="btn btn-primary"><i class="fa fa-trash" aria-hidden="true"></i></button></center>';
						}
					}, {
						"data" : function(o) {
							if(o.visivel){
								return '<center><button onclick="perguntaBloquearDownload(false,'
									+ o.id
									+ ')" class="btn btn-primary"><i class="fa fa-ban" aria-hidden="true"></i></button></center>';
							}else{
								return '<center><button onclick="perguntaBloquearDownload(true,'
									+ o.id
									+ ')" class="btn btn-primary"><i class="fa fa-cloud-download" aria-hidden="true"></i></button></center>';
							}
						}
					}, {
						"data" : "nome"
					}, {
						"data" : function(o) {
							return moment(new Date(o.dataUpload)).lang("pt-br").format('L') + " " + moment(new Date(o.dataUpload)).lang("pt-br").format('LTS');
						}
					} ]
		);
		
	}
	
	//Função que realiza o cadastro dos anexos
	function cadastrarAnexo(id){
		var anexos = $(".anexo");
		
		var formData = new FormData();
		
		//Carrega os anexos
		for(var i = 0; i < $(".anexo").length; i++){
			formData.append("anexo", $(".anexo")[i].files[0]);	
			formData.append("download", $(".download").eq(i).val() == "1" ? true : false);
		}
		
		//Adiciona o ID do trabalho de conclusão a qual os Anexos pertencem
		formData.append("id", id);
		
		//Exibe o progresso do upload
		mostrarBarraDeProgresso();
		
		//Realiza o upload dos arquivos
		uploadArquivos("anexo", formData);
	}
	
	//Função com ação de inativar cadastro
	function perguntaBloquearDownload(status,id) {
		$("#botao-modal-nao").show();
		$("#botao-modal-sim").text("Sim");
		
		if(status == true){
			$("#texto-modal").html("Deseja habilitar o download do anexo selecionado?")
		}else{
			$("#texto-modal").html("Deseja desabilitar o download do anexo selecionado?")
		}
		
		$("#botao-modal-sim").unbind();
		$("#botao-modal-sim").on("click", function(){
			$("#modal").modal("hide");
			$("body").removeClass("modal-open");
			$(".modal-backdrop").fadeOut("slow");
			$(".modal-backdrop").remove();
			bloquearDownload("anexo", id);
		});
		
		$("#modal").modal("show");
	}
	
	//Função para bloquear o download da entidade
	function bloquearDownload(entidade, id) {
		
		$.post("${baseURL}/rest/status_download/" + entidade, {"id" : id}).done(function(response) {
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
				});
		
				$("#texto-modal").html(response.result);
				
				$("#modal").modal("show");
				
				dataTable(dataTableName(entidade));
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
			
			$("#texto-modal").html("Erro ao ativar/inativar " + entidadeMensagem(entidade) + ", por gentileza tente novamente!");
			$("#modal").modal("show");
		});
	}
	
	//Função com ação de novo cadastro
	$("#novo-anexo").on("click", function novoAnexo() {		
		$("#fieldset-anexo").attr('disabled', false);
		$("#cadastrar-anexo").attr('disabled', false);
		$("#novo-anexo").attr('disabled', true);
		$("#adicionar-anexo").attr('disabled', false);
	});
	
	$("#adicionar-anexo").on("click", function adicionarAnexo(){
		$("#fieldset-anexo").append('<div class="col-lg-12">'
					+ '<div class="col-lg-6">'
						+ '<div class="form-group">'
							+ '<label>Arquivo: </label> <input type="file" ' 
								+ 'name="anexo[]" class="form-control anexo" autocomplete="off">'
						+ '</div>'
					+ ' </div>'
	
					+ '<div class="col-lg-6">'
						+ '<div class="form-group">'
							+ '<label>Disponível para Download? </label>'
							+ '<select name="visivel[]" class="form-control download">'
								+ '<option value="1">Sim</option>'
								+ '<option value="2">Não</option>'
							+ '</select>'
						+ '</div>'
					+ '</div>'
				+ '</div>'
		);
	});

</script>