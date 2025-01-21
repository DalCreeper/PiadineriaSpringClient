<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Piadinas Menu</title>
    <link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
		rel="stylesheet"
		integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
		crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stylesPiadinas.css">
</head>
<body>
	<header class="d-flex align-items-center justify-content-between">
		<a href="${pageContext.request.contextPath}/home" class="me-auto"><button id="backHomeBtn">Back</button></a>
		<h1 class="text-center flex-grow-1 mb-0 prevent-select">Available Piadinas</h1>
	</header>
	<main>
	    <c:if test="${not empty piadinas}">
	        <table class="menu-table table table-hover">
	            <thead>
	                <tr>
	                    <th class="align-middle prevent-select">Name</th>
	                    <th class="align-middle prevent-select">Dough</th>
	                    <th class="align-middle prevent-select">Sauces</th>
	                    <th class="align-middle prevent-select">Meat Base</th>
	                    <th class="align-middle prevent-select">Optional Elements</th>
	                    <th class="align-middle prevent-select">Price</th>
	                    <th class="align-middle prevent-select">Employee</th>
	                </tr>
	            </thead>
	            <tbody>
	                <c:forEach var="piadina" items="${piadinas}">
	                    <tr>
	                        <td class="col align-middle">${piadina.name}</td>
	                        <td class="col align-middle">${piadina.dough.type}</td>
	                        <td class="col align-middle">
	                            <c:forEach var="meat" items="${piadina.meatBase}" varStatus="status">
	                                ${meat.type}<c:if test="${!status.last}">, </c:if>
	                            </c:forEach>
	                        </td>
	                        <td class="col align-middle">
	                            <c:forEach var="sauce" items="${piadina.sauces}" varStatus="status">
	                                ${sauce.type}<c:if test="${!status.last}">, </c:if>
	                            </c:forEach>
	                        </td>
	                        <td class="col align-middle">
	                            <c:forEach var="element" items="${piadina.optionalElements}" varStatus="status">
	                                ${element.type}<c:if test="${!status.last}">, </c:if>
	                            </c:forEach>
	                        </td>
	                        <td class="col align-middle text-nowrap">${piadina.price} €</td>
	                        <td class="col align-middle">${piadina.employee.name} ${piadina.employee.surname}</td>
	                    </tr>
	                </c:forEach>
	            </tbody>
	        </table>
	        <form action="${pageContext.request.contextPath}/generatePdf" method="POST" style="text-align: center; margin-top: 20px;">
	            <button type="submit">Download as PDF</button>
	        </form>
	    </c:if>
	    <c:if test="${empty piadinas}">
	        <p>No piadinas available.</p>
	    </c:if>
    </main>
    <script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous">
	</script>
</body>
</html>