<%
    String pageAAfficher = (String) request.getAttribute("pageAAfficher");
%>
<!DOCTYPE html>
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
    <meta charset="utf-8">
    <title>ENI Enchères</title>
    <c:set var="context" value="${pageContext.request.contextPath}"/>

    <!-- Bootstrap core CSS -->
    <link href="${context}/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <link href="${context}/css/global.css" rel="stylesheet">
</head>
<body>
<%@include file="entete.jsp" %>
<div class="container">
    <jsp:include page='<%=pageAAfficher%>'>
        <jsp:param name="sessionScope" value="${sessionScope}"/>
    </jsp:include>
</div>

<!-- Bootstrap core JavaScript -->
<script src="${context}/vendor/jquery/jquery.min.js"></script>
<script src="${context}/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
