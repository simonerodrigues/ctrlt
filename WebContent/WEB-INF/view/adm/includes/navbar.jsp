<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="baseURL" value="${pageContext.request.contextPath}" />

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<!-- Brand and toggle get grouped for better mobile display -->
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-ex1-collapse">
			<span class="sr-only"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="${baseURL}/adm/dashboard"><i class="fa fa-book"></i>&nbsp;Ctrl+T</a>
	</div>
	<!-- Top Menu Items -->
	<ul class="nav navbar-right top-nav">
		<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">
			<i class="fa fa-user"></i> 
			
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
				<li><a onclick="logout()" href="#"><i
						class="fa fa-power-off"></i> Log Out</a></li>
			</ul></li>
	</ul>

	<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
	<c:import url="${param.sidebar}"></c:import>
</nav>
