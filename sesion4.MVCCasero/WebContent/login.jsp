<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>ShareMyTrip - Inicie sesión</title>
<body>
	<form action="validarse" method="post">

		<center>
			<h1>Inicie sesión</h1>
		</center>
		<hr>
		<br>
		<table align="center">
			<tr>
				<td align="right">Su identificador de usuario</td>
				<td><input type="text" name="nombreUsuario"></td>

			</tr>
			<tr>
				<td align="right">Su password</td>
				<td><input type="password" name="pass"></td>
			</tr>

			<tr>
				<td><input type="submit" value="Enviar" /></td>
			</tr>
		</table>
	</form>
	<a id="listarViajes" href="listarViajes">Lista de viajes</a>
	<a href="registro.jsp">Registro</a>
	<br/>
	<c:if test="${baseDatos != 'Abierta'}">
	<c:out value="${baseDatos}"></c:out>
	</c:if>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>