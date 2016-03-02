<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="comprobarNavegacion.jsp"%>

<html>
<head>
<title>ShareMyTrip - Perfil de usuario</title>
<body>
<h1>ShareMyTrip - Perfil de usuario</h1>
	<table id="datos">
		<tr>
			<td>${usuario.name}</td>
			<td>${usuario.surname}</td>
		</tr>
		<tr>
			<td>${usuario.email}</td>
		</tr>
	</table>

<c:choose>

<c:when test="${comenta.id ne usuario.id and comenta.id ne promotor.id}">
	<h3>Comentario sobre el usuario</h3>

	<form action="comentar?id=${viaje.id}&userId=${usuario.id}" method="post">
		<table id=comments>
			<tr>
				<td><textarea id="comentarios" name= "comentarios" rows="10" cols="60"></textarea>
				<td>
			</tr>
			<tr>
				<c:forEach var="i" begin="0" end="10">
					<td><input id="puntuacion" name="puntuacion" type="radio" value="${i}">${i}</td>
				</c:forEach>
			</tr>
			<tr>
				<td><input type="submit" value="Comentar"></td>
			</tr>
		</table>
	</form>
	</c:when>
	<c:otherwise>
	<td>El usuario no puede comentarse a si mismo</td>
	</c:otherwise>
	</c:choose>
	
</body>
</head>
</html>