<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="baseURL" value="${pageContext.request.contextPath}" />

<!-- JQuery Validate -->
<script src="${baseURL}/js/vendor/jquery_validate/jquery.validate.min.js" charset="utf-8"></script>
<script src="${baseURL}/js/vendor/jquery_validate/additional-methods.min.js" charset="utf-8"></script>

<!-- JQuery Mask -->
<script src="${baseURL}/js/vendor/jquery_mask/jquery.mask.min.js" charset="utf-8"></script>

<!-- Moments JavaScript -->
<script src="${baseURL}/js/vendor/moment/moment-with-locales.js" charset="utf-8"></script>

<script src="${baseURL}/js/adm/form_scripts.js" charset="utf-8"></script>

<!-- Função para tratar o contexto da aplicação EX: 127.0.0.1:8080/ctrlt/ -->
<script>
	var baseURL = '${baseURL}';
</script>