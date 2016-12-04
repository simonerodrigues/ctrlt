<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="baseURL" value="${pageContext.request.contextPath}" />

<header>
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container container-navbar">

			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-ex1-collapse">
						<span class="sr-only"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span> <span class="icon-bar"></span>
					</button>
					<a href="${baseURL}/galeria"><img
						src="${baseURL}/images/logo/gallery/logo.png" /></a>
				</div>
			</div>

			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav">
					<li id="menuHome"><a href="${baseURL}/galeria">Home</a></li>
					<li id="menuMonografias"><a href="${baseURL}/galeria/monografias/1">Monografias</a></li>
					<li id="menuCursos" class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Cursos <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<c:forEach items="${cursos}" var="curso">
								<li><a href="${baseURL}/galeria/monografias/1?c=${curso.id}">${curso.nome}</a></li>
							</c:forEach>
						</ul></li>
					<li id="menuInformacoes" class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Informações <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a target="_blank"
								href="http://www.fatecsaocaetano.edu.br/">FATEC São Caetano
									- Antonio Russo</a></li>
							<li><a href="${baseURL}/galeria/sobre">Sobre</a></li>
						</ul>
					</li>
				</ul>
				
				<c:if test="${not empty sessionScope.administradorLogado 
					or not empty sessionScope.professorLogado 
					or not empty sessionScope.alunoLogado}">
					<ul id="user-bar" class="nav top-nav">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> <i class="fa fa-user"></i> 
								<c:if test="${not empty sessionScope.administradorLogado}">
									<c:out value="${sessionScope.administradorLogado.nome}" />
								</c:if>
								<c:if test="${not empty sessionScope.professorLogado}">
									<c:out value="${sessionScope.professorLogado.nome}" />
								</c:if> 
								<c:if test="${not empty sessionScope.alunoLogado}">
									<c:out value="${sessionScope.alunoLogado.nome}" />
								</c:if> 
								<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="${baseURL}/alterar_senha"><i class="fa fa-key"></i> Alterar Senha</a></li>
								<li class="divider"></li>
								<c:if test="${not empty sessionScope.administradorLogado}">
									<li><a href="${baseURL}/dashboard"><i class="fa fa-dashboard"></i> Dashboard</a></li>
									<li class="divider"></li>
								</c:if>
								<li><a onclick="logout()" href="#"><i
										class="fa fa-power-off"></i> Log Out</a></li>
							</ul>
						</li>
					</ul>
				</c:if>
				
				<c:if test="${empty sessionScope.administradorLogado 
					and empty sessionScope.professorLogado 
					and empty sessionScope.alunoLogado}">
					<ul id="user-bar" class="nav top-nav">
						<li class="dropdown">
							<a href="${baseURL}/login?redirect=galeria"> 
								<i class="fa fa-user"></i> Clique aqui para logar
							</a>
						</li>
					</ul> 
				</c:if>
			</div>
			<!-- /.navbar-collapse -->

			<ul class="nav navbar-nav navbar-right search-form">

				<form class="navbar-form" role="search" action="${baseURL}/galeria/monografias/1" accept-charset="UTF-8">
					<div id="search-form" class="form-group">
						<input type="text" class="form-control" name="s" id="campo-pesquisa" value="${param.s}"
							placeholder="Digite sua pesquisa">

						<button type="submit" class="btn btn-default" id="botao-pesquisa">Pesquisar</button>
					</div>
				</form>
			</ul>
		</div>
	</nav>
</header>

<!-- Modal Include -->
<c:url value="includes/modal.jsp" var="modal"></c:url>
<c:import url="${modal}"></c:import>