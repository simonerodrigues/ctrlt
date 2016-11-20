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
		
		window.location = baseURL + "/logout";
	});
	
	$("#modal").modal("show");
}