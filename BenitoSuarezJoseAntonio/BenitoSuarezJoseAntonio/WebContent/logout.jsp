<%@ page import="com.sdi.model.Usuario"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="l"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logout</title>
</head>
<body>

	<a href="logout">Cerrar Sesion |</a>

	<l:out value="${user.nombre}">Session del usuario ${user.nombre}</l:out>

</body>
</html>