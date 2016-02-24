<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>ShareMyTrip - Inicie sesión</title>
<body>
	<form action="validarse" method="post">

			<h1>Inicie sesión</h1>
		<hr>
		<br>
		<table >
			<tr>
				<td align="right">Su identificador de usuario</td>
				<td><input type="text" name="nombreUsuario" required="required"></td>

			</tr>
			<tr>
				<td align="right">Su password</td>
				<td><input type="password" name="pass" required="required"></td>
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