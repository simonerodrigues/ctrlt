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

	<header>
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="container container-navbar">

				<div class="container">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
							<span class="sr-only"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
						</button>
						<img src="images/logo/library/logo.png"/>	
					</div>
				</div>

				<div class="collapse navbar-collapse navbar-ex1-collapse">
					<ul class="nav navbar-nav">
						<li class="active"><a href="#">Home <span class="sr-only">(current)</span></a></li>
						<li><a href="#">Monografias</a></li>
						<li><a href="#">Orientadores</a></li>
						<li class="dropdown">
						 	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Ano Letivo <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="#">2014</a></li>
								<li><a href="#">2015</a></li>
								<li><a href="#">2016</a></li>
							</ul>
						</li>
						<li class="dropdown">
						 	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Categoria(s) <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="#">Banco de Dados</a></li>
								<li><a href="#">Mobile</a></li>
								<li><a href="#">Mainframe</a></li>
								<li><a href="#">Sistemas Distribuídos</a></li>
								<li><a href="#">Web</a></li>
							</ul>
						</li>
						<li><a href="#">Prêmios</a></li>
						<li class="dropdown">
						 	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Ajuda <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="#">FATEC São Caetano - Antonio Russo</a></li>
								<li><a href="#">Sobre</a></li>
							</ul>
						</li>
					</ul>
				</div><!-- /.navbar-collapse -->

				<ul class="nav navbar-nav navbar-right search-form">
					<form class="navbar-form" role="search">
						<div class="form-group">
							<input type="text" class="form-control" id="campo-pesquisa" placeholder="Digite sua pesquisa">
						
							<button type="submit" class="btn btn-default" id="botao-pesquisa">Pesquisar</button>
						</div>
					</form>
				</ul>
			</div>
			
		</nav>
	</header>

	<main>
		<!-- Trabalhos mais procurados -->
		<div class="container page">
			<div class="row">
				<div class="col-lg-12">		
					<center><h2>Trabalhos mais visitados</h2></center>
					<hr />
					<div id="coverflow">
						<img class="cover" src="images/demo/1.jpg"/>
						<img class="cover" src="images/demo/2.jpg"/>
						<img class="cover" src="images/demo/3.jpg"/>
						<img class="cover" src="images/demo/4.jpg"/>
						<img class="cover" src="images/demo/5.jpg"/>
						<img class="cover" src="images/demo/6.jpg"/>
						<img class="cover" src="images/demo/7.jpg"/>
						<img class="cover" src="images/demo/8.jpg"/>
						<img class="cover" src="images/demo/9.jpg"/>
						<img class="cover" src="images/demo/10.jpg"/>
						<img class="cover" src="images/demo/11.jpg"/>
						<img class="cover" src="images/demo/12.jpg"/>
						<img class="cover" src="images/demo/13.jpg"/>
					</div>
				</div>
			</div>

			<br />

			<center><h2>Recém adicionados</h2></center>
			<hr />

			<!-- Demais trabalhos acadêmicos -->
			<div class="row">
				<div class="col-lg-12 line">		
					<div class="col-lg-3 ">
						<div class="book-cover">
						</div>
					</div>
					<div class="col-lg-3 ">
						<div class="book-cover">
						</div>
					</div>
					<div class="col-lg-3 ">
						<div class="book-cover">
						</div>
					</div>
					<div class="col-lg-3 ">
						<div class="book-cover">
						</div>
					</div>
	
					<div class="col-lg-3 ">
						<div class="book-cover">
						</div>
					</div>
					<div class="col-lg-3 ">
						<div class="book-cover">
						</div>
					</div>
					<div class="col-lg-3 ">
						<div class="book-cover">
						</div>
					</div>
					<div class="col-lg-3 ">
						<div class="book-cover">
						</div>
					</div>

					<div class="col-lg-3 ">
						<div class="book-cover">
						</div>
					</div>
					<div class="col-lg-3 ">
						<div class="book-cover">
						</div>
					</div>
					<div class="col-lg-3 ">
						<div class="book-cover">
						</div>
					</div>
					<div class="col-lg-3 ">
						<div class="book-cover">
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>

	<footer>
		
	</footer>

	<!-- JavaScript Include -->
	<c:url value="includes/javascript.jsp" var="javascript"></c:url>
	<c:import url="${javascript}"></c:import>

	<!-- Coverflow script -->
	<script>
		$(function() {

			$('#coverflow').coverflow({
				index:			6,
				density:		2,
				innerOffset:	50,
				innerScale:		.7,
				animateStep:	function(event, cover, offset, isVisible, isMiddle, sin, cos) {
					if (isVisible) {
						if (isMiddle) {
							$(cover).css({
								'filter':			'none',
								'-webkit-filter':	'none'
							});
						} else {
							var brightness	= 1 + Math.abs(sin),
								contrast	= 1 - Math.abs(sin),
								filter		= 'contrast('+contrast+') brightness('+brightness+')';
							$(cover).css({
								'filter':			filter,
								'-webkit-filter':	filter
							});
						}
					}
				}
			});
		});
	</script>

</body>

</html>
