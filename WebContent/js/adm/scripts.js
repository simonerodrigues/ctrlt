//Marca o item que deve estar em destaque no menu lateral
function marcaMenu(item, subItem){
	if (item == "" && subItem == ""){
		return;
	}
	
	if (item != "" && subItem != ""){
		$(item).removeClass('collapse');
		$(item).addClass('collapsing');
		$(item).removeClass('collapsing');
		$(item).addClass('collapse in');
	}
	
	var $listaItens = $(".navbar-nav>li");
	var $listaSubItens = $(".side-nav li ul li");
	
	for(var i = 0; i < $listaItens.length; i++){
		$($listaItens[i]).removeClass('active');
	}
	
	for(var i = 0; i < $listaSubItens.length; i++){
		$($listaSubItens[i]).removeClass('active');
	}
	
	if (subItem != ""){
		$(subItem).css("background-color", "#000");
		$(subItem + ">a").css("color", "#FFF");
	}else{
		$(item).css("background-color", "#000");
	}
}


$(window).load(function() {
	// Animate loader off screen
	$(".se-pre-con").fadeOut("slow");;
});

//Realiza o logout
function logout() {
	$("#botao-modal-nao").show();
	$("#botao-modal-sim").text("Sim");
	$("#texto-modal").html("Deseja realizar o logout do Ctrl+T?")
	
	$("#botao-modal-sim").unbind();
	$("#botao-modal-sim").on("click", function(){
		$("#modal").modal("hide");
		$("body").removeClass("modal-open");
		$(".modal-backdrop").fadeOut("slow");
		$(".modal-backdrop").remove();
		
		window.location="/logout";
	});
	
	$("#modal").modal("show");
}