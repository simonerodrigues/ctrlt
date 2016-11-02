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
							<div class="form-group clearfix">
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
							<th>Inativar</th>
							<th>Excluir</th>
							<th>Visível</th>
							<th>Nome</th>
							<th>Data de Upload</th>
							<th class="status">Status</th>
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
								+ '<label>Visível: </label>'
								+ '<select name="visivel[]" class="form-control">'
									+ '<option>Sim</option>'
									+ '<option>Não</option>'
								+ '</select>'
							+ '</div>'
						+ '</div>'
					+ '</div>'
				+ '</div>'
		);
		
		$("#fieldset-anexo").attr('disabled', true);
		$("#cadastrar-anexo").attr('disabled', true);
		$("#novo-anexo").attr('disabled', false);
		$("#adicionar-anexo").attr('disabled', true);
		
		//Carrega a tabela de acordo com os anexos do TCC
		dataTable("#dataTableAnexo", "/rest/lista/anexo/" + id, 
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
					}, {
						"data" : "nome"
					}, {
						"data" : function(o) {
							return moment(new Date(o.dataUpload)).lang("pt-br").format('L') + " " + moment(new Date(o.dataUpload)).lang("pt-br").format('LTS');
						}
					}, {
						"data" : "ativo"
					} ]
		);
	}
	
	//Função com ação de novo cadastro
	$("#novo-anexo").on("click", function novoAnexo() {		
		$("#fieldset-anexo").attr('disabled', false);
		$("#cadastrar-anexo").attr('disabled', false);
		$("#novo-anexo").attr('disabled', true);
		$("#adicionar-anexo").attr('disabled', false);
	});
	
	$("#adicionar-anexo").on("click", function adicionarAnexo(){
		$("#fieldset-anexo").html($("#fieldset-anexo").html()
				+ '<div class="col-lg-12">'
					+ '<div class="col-lg-6">'
						+ '<div class="form-group">'
							+ '<label>Arquivo: </label> <input type="file" ' 
								+ 'name="anexo[]" class="form-control anexo" autocomplete="off">'
						+ '</div>'
					+ ' </div>'
	
					+ '<div class="col-lg-6">'
						+ '<div class="form-group">'
							+ '<label>Visível: </label>'
							+ '<select name="visivel[]" class="form-control">'
								+ '<option>Sim</option>'
								+ '<option>Não</option>'
							+ '</select>'
						+ '</div>'
					+ '</div>'
				+ '</div>'
		);
	});

</script>