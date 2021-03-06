﻿<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="baseURL" value="${pageContext.request.contextPath}" />

<!-- jQuery -->
<script src="${baseURL}/js/vendor/jquery/jquery.min.js" charset="utf-8"></script>

<!-- jQuery UI -->
<script src="${baseURL}/js/vendor/jquery/jquery-ui.js" charset="utf-8"></script>

<!-- Bootstrap Core JavaScript -->
<script src="${baseURL}/js/vendor/bootstrap/bootstrap.min.js" charset="utf-8"></script>

<!-- Coverflow -->
<script src="${baseURL}/js/vendor/coverflow/jquery.coverflow.js" charset="utf-8"></script>

<!-- Personal Scripts -->
<script src="${baseURL}/js/gallery/scripts.js"></script>

<!-- Função para tratar o contexto da aplicação EX: 127.0.0.1:8080/ctrlt/ -->
<script>
	var baseURL = '${baseURL}';
</script>