//Função que valida os os campos do formulário
$(document).ready(function() {
	
	$("#dataNascimento").mask('00/00/0000');
	$("#celular").mask('(00) 00000-0000');
	$("#telefone").mask('(00) 0000-0000');
	$("#cpf").mask('000.000.000-00');
	$("#cep").mask('00000-000');
	
	$("#form").validate({
		rules : {
			ra : {
				required: true,
			},
			nome :  {
				required: true,
				minlength: 3
			},
			titulo :  {
				required: true,
				minlength: 3
			},
			login :  {
				required: true,
				minlength: 4
			},
			senhaAtual :  {
				required: true,
				minlength: 8
			},
			senha :  {
				required: true,
				minlength: 8
			},
			confirmacaoSenha : {
				required: true,
				minlength: 8,
				equalTo: "#senha"
			},
			emailAlternativo : {
				required: true,
				email: true
			},
			emailFatec : {
				email: true
			},
			celular : {
				minlength: 15,
				maxlength: 15
			},
			telefone : {
				minlength: 14,
				maxlength: 14
			},
			descricao : {
				minlength: 4,
				maxlength: 140
			},
			siglaCurso : {
				required: true,
				minlength: 3,
				maxlength: 3
			},
			siglaPeriodo : {
				required: true,
				minlength: 2,
				maxlength: 2
			},
			cursoPeriodo : {
				required: true
			}
		},
		messages : {
			ra : {
				required: "Por favor, digite o RA",
			},
			nome : {
				required: "Por favor, digite o nome",
				minlength: "O nome deve conter no mínimo 3 dígitos"
			},
			titulo : {
				required: "Por favor, digite o titulo",
				minlength: "O titulo deve conter no mínimo 3 dígitos"
			},
			login : {
				required: "Por favor, digite o login",
				minlength: "O login deve conter no mínimo 4 dígitos"
			},
			senhaAtual : {
				required: "Por favor, digite a senha",
				minlength: "A senha deve conter no mínimo 8 dígitos"
			},
			senha : {
				required: "Por favor, digite a senha",
				minlength: "A senha deve conter no mínimo 8 dígitos"
			},
			confirmacaoSenha : {
				required: "Por favor, digite a confirmação de senha",
				minlength: "A senha deve conter no mínimo 8 dígitos",
				equalTo: "Senhas divergentes"
			},
			emailAlternativo : {
				required: "Por favor, digite um e-mail alternativo",
				email: "Por favor, digite um e-mail válido"
			},
			emailFatec :{
				required: "Por favor, digite um e-mail alternativo",
				email: "Por favor, digite um e-mail válido"
			},
			celular : {
				number: "O celular deve conter somente números",
				minlength: "O celular deve conter 15 dígitos",
				maxlength: "O celular deve conter 15 dígitos"
			},
			telefone : {
				number: "O telefone deve conter somente números",
				minlength: "O telefone deve conter 14 dígitos",
				maxlength: "O telefone deve conter 14 dígitos"
			},
			descricao : {
				minlength: "A descrição deve conter no mínimo 4 dígitos",
				maxlength: "A descrição deve conter no máximo 140 dígitos"
			},
			siglaCurso : {
				required: "Por favor, digite a sigla",
				minlength: "A descrição deve conter no mínimo 3 dígitos",
				maxlength: "A descrição deve conter no máximo 3 dígitos"
			},
			siglaPeriodo : {
				required: "Por favor, digite a sigla",
				minlength: "A descrição deve conter no mínimo 2 dígitos",
				maxlength: "A descrição deve conter no máximo 2 dígitos"
			},
			cursoPeriodo : {
				required: "Por favor, digite o RA"
			}
		},
		highlight : function(element) {
			$(element).closest('.form-group').addClass('has-error');
		},
		unhighlight : function(element) {
			$(element).closest('.form-group').removeClass('has-error');
			$(element).closest('.form-group').addClass('has-success');
		},
		errorElement : 'span',
		errorClass : 'help-block',
		errorPlacement : function(error, element) {
			if (element.parent('.input-group').length) {
				error.insertAfter(element.parent());
			} else {
				error.insertAfter(element);
			}
		}
	});
});

//Função com ação de novo cadastro
function novo() {
	cancelar();
	
	$("fieldset").attr('disabled', false);
	$("#cancelar").attr('disabled', false);
	$("#reset").attr('disabled', false);
	$("#cadastrar").attr('disabled', false);
	$("#novo").attr('disabled', true);
	$("#filtro").attr('disabled', true);
	$("#pesquisa").attr('disabled', true);
	$("#btnPesquisa").attr('disabled', true);
}

//Função com ação de alterar cadastro
function alterar() {
	$("fieldset").attr('disabled', false);
	$("#cancelar").attr('disabled', false);
	$("#reset").attr('disabled', false);
	$("#cadastrar").attr('disabled', false);
	$("#cadastrar").html('Alterar');
	$("#novo").attr('disabled', true);
	$("#filtro").attr('disabled', true);
	$("#pesquisa").attr('disabled', true);
	$("#btnPesquisa").attr('disabled', true);
	
	$("#senha").attr('disabled', true);
	$("#confirmacaoSenha").attr('disabled', true);
	
	$("#senha").val("123456789");
	$("#confirmacaoSenha").val("123456789");
}

//Função com ação de inativar cadastro
function inativar(entidade, id) {
	$("#botao-modal-nao").show();
	$("#botao-modal-sim").text("Sim");
	
	$("#texto-modal").html("Deseja alterar o status do(a) " + entidadeMensagem(entidade) + " selecionado(a)?")
	
	$("#botao-modal-sim").unbind();
	$("#botao-modal-sim").on("click", function(){
		$("#modal").modal("hide");
		$("body").removeClass("modal-open");
		$(".modal-backdrop").fadeOut("slow");
		$(".modal-backdrop").remove();
		inativarEntidade(entidade, id);
	});
	
	$("#modal").modal("show");
}

//Função com ação de excluir cadastro
function excluir(entidade, id) {
	$("#botao-modal-nao").show();
	$("#botao-modal-sim").text("Sim");
	
	$("#texto-modal").html("Deseja excluir o(a) " + entidadeMensagem(entidade) + " selecionado(a)?")
	
	$("#botao-modal-sim").unbind();
	$("#botao-modal-sim").on("click", function(){
		$("#modal").modal("hide");
		$("body").removeClass("modal-open");
		$(".modal-backdrop").fadeOut("slow");
		$(".modal-backdrop").remove();
		excluirEntidade(entidade, id);
	});
	
	$("#modal").modal("show");
}

//Função com ação de cancelar
function cancelar() {
	$("fieldset").attr('disabled', true);
	$("#cancelar").attr('disabled', true);
	$("#reset").attr('disabled', true);
	$("#cadastrar").attr('disabled', true);
	$("#cadastrar").html('Cadastrar');
	$("#novo").attr('disabled', false);
	$("#filtro").attr('disabled', false);
	$("#pesquisa").attr('disabled', false);
	$("#btnPesquisa").attr('disabled', false);
	$("fieldset.alterar").attr('disabled', false);

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
	
	// Limpa as textareas
	var $campos = $("textarea");

	for (var i = 0; i < $campos.length; i++) {
		$campos[i].value = "";
		
		$($campos[i]).closest('.form-group').removeClass('has-error');
		$($campos[i]).closest('.form-group').removeClass('has-success');
	}
	
	// Limpa os campos (select)
	var $campos = $("select");
	
	for (var i = 0; i < $campos.length; i++) {		
		$($campos[i]).closest('.form-group').removeClass('has-error');
		$($campos[i]).closest('.form-group').removeClass('has-success');
		$($campos[i]).attr('disabled', false);
	}
	
	var $helps = $("span.help-block");
	
	for (var i = 0; i < $helps.length; i++) {
		$($helps[i]).text("");
	}
	
	$("#panel-information").hide();
	
	$('#modal').modal('hide')
}

//Função que reseta o formulário
function resetar() {
	// Limpa os campos
	var $campos = $("input");

	for (var i = 0; i < $campos.length; i++) {
		if($campos[i].type != "checkbox"){
			$campos[i].value = "";
		}else{
			$($campos[i]).removeAttr('checked');
		}
		
		$($campos[i]).closest('.form-group').removeClass('has-error');
		$($campos[i]).closest('.form-group').removeClass('has-success');
	}
	
	// Limpa as textareas
	var $campos = $("textarea");

	for (var i = 0; i < $campos.length; i++) {
		$campos[i].value = "";
		
		$($campos[i]).closest('.form-group').removeClass('has-error');
		$($campos[i]).closest('.form-group').removeClass('has-success');
	}
	
	// Limpa os campos (select)
	var $campos = $("select");
	
	for (var i = 0; i < $campos.length; i++) {		
		$($campos[i]).closest('.form-group').removeClass('has-error');
		$($campos[i]).closest('.form-group').removeClass('has-success');
	}
	
	var $helps = $("span.help-block");
	
	for (var i = 0; i < $helps.length; i++) {
		$($helps[i]).text("");
	}
}

//Inicia a tabela de acordo com o Id passado por parâmetro, ou realiza a atualização da tabela
function dataTable(id, url, data) {		
	if($.fn.dataTable.isDataTable(id)){
		$(id).DataTable().ajax.reload();
	}else{
		var table = $(id).DataTable({
			"processing": true,
			"responsive": true,
	        "serverSide": false,
	        "ajax": {
	            "url": url,
	            "type": "POST"
	        },
	        "columns": data,
			"scrollX": true,
			"language" : {
				"url" : "/json/Portuguese-Brasil.json"
			},
			"aoColumnDefs": [ {
				"aTargets": [0,1,2],
				"sWidth": "50px",
				"bSortable" : false
			},{
			   "aTargets": ['status'],
			   "sWidth": "30px",
			   "mRender": function (data) {
				    if(data == true){
				    	return "Ativo";	 
				    }else{
				    	return "Inativo";
				    }
			  	}
			},{
				"aTargets": ['Sigla'],
				"sWidth": "30px"
			}],
			"order": [[3, "asc" ]]
		});
	
		$(id + ' tbody').on('click', 'tr', function() {
			if ($(this).hasClass('info')) {
				$(this).removeClass('info');
			} else {
				table.$('tr.info').removeClass('info');
				$(this).addClass('info');
			}
		});
	}
}

//Inicia a tabela de acordo com o Id passado por parâmetro, ou realiza a atualização da tabela
function dataTableTCC(id, url, data) {		
	if($.fn.dataTable.isDataTable(id)){
		$(id).DataTable().ajax.reload();
	}else{
		var table = $(id).DataTable({
			"processing": true,
			"responsive": true,
	        "serverSide": false,
	        "ajax": {
	            "url": url,
	            "type": "POST"
	        },
	        "columns": data,
			"scrollX": true,
			"language" : {
				"url" : "/json/Portuguese-Brasil.json"
			},
			"aoColumnDefs": [ {
				"aTargets": [0,1,2,3,4],
				"sWidth": "50px",
				"bSortable" : false
			},{
			   "aTargets": ['status'],
			   "sWidth": "30px",
			   "mRender": function (data) {
				    if(data == true){
				    	return "Ativo";	 
				    }else{
				    	return "Inativo";
				    }
			  	}
			},{
				"aTargets": ['Sigla'],
				"sWidth": "30px"
			}],
			"order": [[5, "asc" ]]
		});
	
		$(id + ' tbody').on('click', 'tr', function() {
			if ($(this).hasClass('info')) {
				$(this).removeClass('info');
			} else {
				table.$('tr.info').removeClass('info');
				$(this).addClass('info');
			}
		});
	}
}

//Função para cadastrar a entidade
function manterEntidade(acao,entidade, data) {
	
	$(".se-pre-con-dark").fadeIn("slow");
	
	/*
	 * acao = 1 (Inclusão)
	 * acao = 1 (Alteração)
	 */
	
	if(acao == 1){
		var operacao = "/rest/cadastra/";
	}else if(acao == 2){
		var operacao = "/rest/altera/"
	}

	$.post(operacao + entidade, data).done(function(response) {
		$(".se-pre-con-dark").hide();
		
		if (response.status == "SUCCESS") {
			//Exibe mensagem de aluno cadastrado com sucesso
			$("#botao-modal-nao").hide();
			$("#botao-modal-sim").text("Ok");
			$("#texto-modal").html(response.result);
			$("#botao-modal-sim").unbind();
			$("#botao-modal-sim").on("click", function(){
				cancelar();
				$("#modal").modal("hide");
				$("body").removeClass("modal-open");
				$(".modal-backdrop").fadeOut("slow");
				$(".modal-backdrop").remove();
			});
			$("#modal").modal("show");
			
			dataTable("#dataTable");
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
		if(acao == 1){
			$("#texto-modal").html("Erro ao cadastrar " + entidadeMensagem(entidade) + ", por gentileza tente novamente!");
		}else{
			$("#texto-modal").html("Erro ao alterar " + entidadeMensagem(entidade) + ", por gentileza tente novamente!");
		}
		$("#modal").modal("show");
	});
}

//Função para cadastrar a entidade
function manterEntidadeComUpload(acao, entidade, data, entidadeUpload, uploadData) {
	
	$(".se-pre-con-dark").fadeIn("slow");
	
	/*
	 * acao = 1 (Inclusão)
	 * acao = 1 (Alteração)
	 */
	
	if(acao == 1){
		var operacao = "/rest/cadastra/";
	}else if(acao == 2){
		var operacao = "/rest/altera/"
	}

	$.post(operacao + entidade, data).done(function(response) {
		$(".se-pre-con-dark").hide();
		
		if (response.status == "SUCCESS") {
			
			uploadData.append("id", response.id);
			
			mostrarBarraDeProgresso();
			
			uploadArquivos(entidadeUpload, uploadData);
		} else {
			$("#botao-modal-nao").hide();
			$("#botao-modal-sim").show();
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
		$("#botao-modal-sim").show();
		$("#botao-modal-sim").text("Ok");
		$("#botao-modal-sim").unbind();
		$("#botao-modal-sim").on("click", function(){
			$("#modal").modal("hide");
			$("body").removeClass("modal-open");
			$(".modal-backdrop").fadeOut("slow");
			$(".modal-backdrop").remove();
		});
		if(acao == 1){
			$("#texto-modal").html("Erro ao cadastrar " + entidadeMensagem(entidade) + ", por gentileza tente novamente!");
		}else{
			$("#texto-modal").html("Erro ao alterar " + entidadeMensagem(entidade) + ", por gentileza tente novamente!");
		}
		$("#modal").modal("show");
	});
}

//Função que realiza o upload do(s) arquivo(s)	
function uploadArquivos(entidadeUpload, uploadData){
	$.ajax({
        url: "/rest/cadastra/upload_" + entidadeUpload,
        type: 'POST',
        data: uploadData,
        xhr: function() {
            var myXhr = $.ajaxSettings.xhr();
            if(myXhr.upload){
                myXhr.upload.addEventListener('progress',carregarProgresso, false);
            }
            return myXhr;
        },
        success: function(response2) {
        	//Exibe mensagem de aluno cadastrado com sucesso
			$("#botao-modal-nao").hide();
			$("#botao-modal-sim").show();
			$("#botao-modal-sim").text("Ok");
			$("#texto-modal").html(response2.result);
			$("#botao-modal-sim").unbind();
			$("#botao-modal-sim").on("click", function(){
				cancelar();
				$("#modal").modal("hide");
				$("body").removeClass("modal-open");
				$(".modal-backdrop").fadeOut("slow");
				$(".modal-backdrop").remove();
			});
			$("#modal").modal("show");
			
			dataTable("#dataTable");
        },
        fail: function(response2) {
        	$("#botao-modal-nao").hide();
        	$("#botao-modal-sim").show();
			$("#botao-modal-sim").text("Ok");
			$("#texto-modal").html(response2.result);
			$("#botao-modal-sim").unbind();
			$("#botao-modal-sim").on("click", function(){
				cancelar();
				$("#modal").modal("hide");
				$("body").removeClass("modal-open");
				$(".modal-backdrop").fadeOut("slow");
				$(".modal-backdrop").remove();
			});
			$("#modal").modal("show");
        },
        cache: false,
        contentType: false,
        processData: false
    });
}

//Função que exibe a barra de progresso
function mostrarBarraDeProgresso(){
	$("#botao-modal-nao").hide();
	$("#botao-modal-sim").hide();
	$("#texto-modal").html(' '+ 
			'<div class="progress">' +
				'<div id="progressBar" class="progress-bar progress-bar-striped active" role="progressbar' +
			          ' aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width:100%">' +
					'0% Completado' +
				'</div>' + 
			'</div>');
	$("#modal").modal("show");
}

//Função que exibe a porcentagem do upload do(s) arquivo(s)
function carregarProgresso(e){

    if(e.lengthComputable){
        var max = e.total;
        var current = e.loaded;

        var percentVal = ((current * 100)/max).toFixed(2) + '%';

        $("#progressBar").width(percentVal);
        $("#progressBar").html(percentVal);
    }  
 }

//Função para inativar a entidade
function inativarEntidade(entidade, id) {
	
	$.post("/rest/inativa/" + entidade, {"id" : id}).done(function(response) {
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
			
			dataTable("#dataTable");
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

//Função para excluir a entidade
function excluirEntidade(entidade, id) {
	
	$.post("/rest/exclui/" + entidade, {"id" : id}).done(function(response) {
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
			
			dataTable("#dataTable");
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
		
		$("#texto-modal").html("Erro ao excluir o(a) " + entidadeMensagem(entidade) + ", por gentileza tente novamente!");
		$("#modal").modal("show");
	});
}

//Função que preenche o valor do produto
function valorProduto(){
	$.post("servico_json", {"id" : $("#servico").val()}).done(function(response) {
		$(".se-pre-con-dark").hide();
		
		$("#preco").val(response.preco);
		$("#preco").val(($("#preco").val()).replace(".",","));
	}).fail(function(e) {
		$(".se-pre-con-dark").fadeOut("slow");
		$("#botao-modal-nao").hide();
		$("#botao-modal-sim").text("Ok");
		$("#texto-modal").html("Erro ao tentar carregar preço do serviço. Por gentileza tente novamente!");
		$("#modal").modal("show");
	});
}

//Clique no botão novo na tela de cadastro
$("#novo").on("click", novo);

//Ao teclar o enter, clica no botão sim do modal
$("#modal").keyup(function(event){
    if(event.keyCode == 13){
        $("#botao-modal-sim").click();
    }
});

//botão que chama o modal para cancelar o registro
$("#cancelar").on("click", function(){
	if($("#cadastrar").html() == "Cadastrar"){
		$('#texto-modal').text("Deseja cancelar o cadastro?");
	}else{
		$('#texto-modal').text("Deseja cancelar a alteração?");
	}
	
	$("#botao-modal-nao").show();
	$("#botao-modal-sim").text("Sim");
	$("#botao-modal-sim").unbind();
	
	// Clique no botão cancelar na tela de cadastro
	$("#botao-modal-sim").on("click", cancelar);
	
	$('#modal').modal('show');
});

//Clique no botão limpar na tela de cadastro
$("#reset").on("click", resetar);

//Nunca deixa o form realiza o submit, quem realiza o submit é o JQuery(Ajax)
$("#form").on("submit", function() {
	return false;
});

function entidadeMensagem(entidade){
	var nomeEntidadeMensagem = "";
	
	if(entidade == "linha_de_pesquisa"){
		nomeEntidadeMensagem = "linha de pesquisa";
	}else if(entidade == "administrador_de_conteudo"){
		nomeEntidadeMensagem = "administrador de conteúdo";
	}else if(entidade == "trabalhoDeConclusao"){
		nomeEntidadeMensagem = "trabalho de conclusão de curso";
	}else if(entidade == "periodo"){
		nomeEntidadeMensagem = "período";
	}else if(entidade == "trabalho_de_conclusao"){
		nomeEntidadeMensagem = "trabalho de conclusão";
	}else{
		nomeEntidadeMensagem = entidade;
	}
	
	return nomeEntidadeMensagem;
}