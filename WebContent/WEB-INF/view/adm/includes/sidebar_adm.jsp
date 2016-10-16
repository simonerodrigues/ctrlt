<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="collapse navbar-collapse navbar-ex1-collapse">
	<ul class="nav navbar-nav side-nav">
		<c:if test="${not empty sessionScope.alunoLogado}">
			<li class="active"><a href="/adm/profile"><i class="fa fa-user"></i> Profile</a></li>
		</c:if>
		
		<c:if test="${not empty sessionScope.professorLogado}">
			<li class="active"><a href="/adm/profile"><i class="fa fa-user"></i> Profile</a></li>
		</c:if>
		
		<c:if test="${not empty sessionScope.administradorLogado}">
			<li class="active"><a href="/adm/dashboard"><i class=" fa fa-dashboard"></i> Dashboard</a></li>
			<li><a href="javascript:;" data-toggle="collapse" data-target="#cadastros"><i class=" fa fa-plus"></i> Cadastros <i class=" fa fa-caret-down"></i></a>
				<ul id="cadastros" class="collapse">
					<li id="cadastroADM"><a href="/adm/cadastro/administrador_de_conteudo"><i class="fa fa-user-plus"></i> Adm de Conteúdo</a></li>
					<li id="cadastroAluno"><a href="/adm/cadastro/aluno"><i class="fa fa-user-plus"></i> Aluno</a></li>
					<li id="cadastroCurso"><a href="/adm/cadastro/curso"><i class="fa fa-puzzle-piece"></i> Curso</a></li>
					<li id="cadastroLinhaDePesquisa"><a href="/adm/cadastro/linha_de_pesquisa"><i class="fa fa-tag"></i> Linha de Pesquisa</a></li>
					<li id="cadastroPeriodo"><a href="/adm/cadastro/periodo"><i class="fa fa-sun-o"></i> Período</a></li>
					<li id="cadastroProfessor"><a href="/adm/cadastro/professor"><i class="fa fa-user-plus"></i> Professor</a></li>
					<li id="cadastroTrabalhoDeConclusao"><a href="/adm/cadastro/trabalho_de_conclusao"><i class="fa fa-book"></i> TCC</a></li>
				</ul>
			</li>
			<li><a href="javascript:;" data-toggle="collapse"data-target="#relatorios"><i class="fa fa-file-text"></i> Relatórios <i class="fa fa-caret-down"></i></a>
				<ul id="relatorios" class="collapse">
					<li id="relatorioADMPDF"><a target="_blank" href="/adm/relatorio/pdf/administrador_de_conteudo"><i class="fa fa-file-pdf-o"></i> ADM de Conteúdo</a></li>
					<li id="relatorioAlunoPDF"><a target="_blank" href="/adm/relatorio/pdf/aluno"><i class="fa fa-file-pdf-o"></i> Aluno</a></li>
					<li id="relatorioCursoPDF"><a target="_blank" href="/adm/relatorio/pdf/curso"><i class="fa fa-file-pdf-o"></i> Curso</a></li>
					<li id="relatorioLinhaDePesquisaPDF"><a target="_blank" href="/adm/relatorio/pdf/linha_de_pesquisa"><i class="fa fa-file-pdf-o"></i> Linha de Pesquisa</a></li>
					<li id="relatorioPeriodoPDF"><a target="_blank" href="/adm/relatorio/pdf/periodo"><i class="fa fa-file-pdf-o"></i> Período</a></li>
					<li id="relatorioProfessorPDF"><a target="_blank" href="/adm/relatorio/pdf/professor"><i class="fa fa-file-pdf-o"></i> Professor</a></li>
					<li id="relatorioProfessorPDF"><a target="_blank" href="/adm/relatorio/pdf/trabalho_de_conclusao"><i class="fa fa-file-pdf-o"></i> TCC</a></li>
				</ul>
			</li>
		</c:if>
		<li><a href="javascript:;" data-toggle="collapse" data-target="#ajuda"><i class="fa fa-question-circle"></i> Ajuda <i class="fa fa-caret-down"></i></a>
			<ul id="ajuda" class="collapse">
				<li id="ajudaFAQ"><a target="_blank" href="doc/"><i class="fa fa-code"></i> Documentação</a></li>
				<li id="ajudaFAQ"><a href="/manual"><i class="fa fa-life-ring"></i> Manual</a></li>
				<li id="ajudaSobre"><a href="/sobre"><i class="fa fa-info-circle"></i> Sobre</a></li>
			</ul>
		</li>
	</ul>
</div>
<!-- /.navbar-collapse -->