<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="pt-br">

<head>
	<c:url value="includes/meta_informations.jsp"
		var="metainformations"></c:url>
	
	<!-- Informa��es de Autor do projeto -->
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
				<p>Voc� n�o possui permiss�o para acessar a p�gina em quest�o, caso possua d�vidas, entre em contato com o administrador do Sistema.</p>
				
				<br /><br />
				
				<p><strong>Ctrl+T - Controle de Trabalhos Acad�micos. Fatec S�o Caetano do Sul - ${ano}</strong></p>
			</center>
		</div>
	</div>
	<!-- /.row -->

	<!-- JavaScript Include -->
	<c:url value="includes/javascript.jsp" var="javascript"></c:url>
	<c:import url="${javascript}"></c:import>
</body>

</html>
