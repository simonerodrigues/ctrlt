<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="baseURL" value="${pageContext.request.contextPath}" />

<!-- Datatables -->
<script src="${baseURL}/js/vendor/datatables/datatables.min.js" charset="utf-8"></script>

<!-- Bootstrap Datatables -->
<script src="${baseURL}/js/vendor/datatables/dataTables.bootstrap.min.js" charset="utf-8"></script>

<!-- Função para tratar o contexto da aplicação EX: 127.0.0.1:8080/ctrlt/ -->
<script>
	var baseURL = '${baseURL}';
</script>