<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="baseURL" value="${pageContext.request.contextPath}" />

<footer>
	<img src="${baseURL}/images/icons/favicon.png" /> <p>Ctrl+T - Sistema de Controle de Trabalhos Acad�micos - FATEC S�o Caetano do Sul</p>
</footer>

<!-- Modal Include -->
<c:url value="includes/modal.jsp" var="modal"></c:url>
<c:import url="${modal}"></c:import>