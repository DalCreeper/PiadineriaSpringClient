<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PiadineriaAdvancia Client</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stylesHome.css">
</head>
<body>
	<header>
	    <h1>Welcome to Piadineria Advancia's external client platform</h1>
    </header>
    <main>
    	<p>Click the button below to view the menù list of piadinas.</p>
	    <form id="form" action="${pageContext.request.contextPath}/piadinas" method="POST">
	   		<div id="overlay" style="display: none;">
				<div class="mover"></div>
			</div>
	        <button id="view-btn" type="submit">View Piadinas</button>
	    </form>
    </main>
    <script src="${pageContext.request.contextPath}/js/scriptHome.js"></script>
</body>
</html>