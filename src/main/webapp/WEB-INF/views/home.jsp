<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home - PiadineriaAdvancia</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stylesHome.css">
</head>
<body>
    <h1>Welcome to the Piadineria Advancia</h1>
    <p>Click the button below to view the menù list of piadinas.</p>
    <form action="${pageContext.request.contextPath}/piadinas" method="POST">
        <button type="submit">View Piadinas</button>
    </form>
</body>
</html>