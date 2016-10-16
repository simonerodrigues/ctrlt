<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<c:url value="includes/meta_informations.jsp"
	var="metainformations"></c:url>

<!-- Informações de Autor do projeto -->
<c:import url="${metainformations}"></c:import>

<!-- CSS Include -->
<c:url value="includes/css.jsp" var="css"></c:url>
<c:import url="${css}"></c:import>

</head>

<body>
	<div class="modal-loading"></div>

	<div class="se-pre-con"></div>

	<div class="row">
		<div class="col-lg-12">
			<center><i style="font-size:400px;color:#3980b5" class="fa fa-ban" aria-hidden="true"></i></center>
		</div>
		
		<div class="col-lg-12">
			<center>
				<p>Você não possui permissão para acessar a página em questão, caso possua dúvidas, entre em contato com o administrador do Sistema.</p>
				
				<br /><br />
				
				<p><strong>Ctrl+T - Controle de Trabalhos Acadêmicos. Fatec São Caetano do Sul - ${ano}</strong></p>
				
				<br />
				
				<button type="button" class="btn btn-primary" onclick="window.history.back();"><i class="fa fa-arrow-circle-left"></i>&nbsp;Clique aqui para voltar para a página anterior</button>
			</center>
		</div>
	</div>
	<!-- /.row -->

	<!-- Modal Include -->
	<c:url value="includes/modal.jsp" var="modal"></c:url>
	<c:import url="${modal}"></c:import>

	<!-- JavaScript Include -->
	<c:url value="includes/javascript.jsp" var="javascript"></c:url>
	<c:import url="${javascript}"></c:import>
	
	<script>		
	</script>


</body>

</html>
