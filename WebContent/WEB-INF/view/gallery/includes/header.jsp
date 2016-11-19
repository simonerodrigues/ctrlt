<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="baseURL" value="${pageContext.request.contextPath}" />

<header>
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container container-navbar">
	
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
						<span class="sr-only"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
					</button>
					<a href="${baseURL}/galeria"><img src="${baseURL}/images/logo/gallery/logo.png"/></a>	
				</div>
			</div>
	
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="${baseURL}/galeria">Home <span class="sr-only">(current)</span></a></li>
					<li><a href="#">Monografias</a></li>
					<li class="dropdown">
					 	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Cursos <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<c:forEach items="${cursos}" var="curso">
								<li><a href="#">${curso.nome}</a></li>
							</c:forEach>
						</ul>
					</li>
					<li class="dropdown">
					 	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Informações <span class="caret"></span></a>
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