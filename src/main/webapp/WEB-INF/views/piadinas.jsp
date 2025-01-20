<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Piadinas menù</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stylesPiadinas.css">
</head>
<body>
    <h1>Available Piadinas</h1>
    <c:if test="${not empty piadinas}">
        <ul>
            <c:forEach var="piadina" items="${piadinas}">
                <li>${piadina.name}</li>
            </c:forEach>
        </ul>
    </c:if>
    <c:if test="${empty piadinas}">
        <p>No piadinas avaiable.</p>
    </c:if>
    <a href="${pageContext.request.contextPath}/home">Back to Home</a>
</body>
</html>