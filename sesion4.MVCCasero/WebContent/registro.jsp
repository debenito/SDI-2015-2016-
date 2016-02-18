<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>ShareMyTrip - Registro de nuevos usuarios</title>
</head>
<body>
<h1>ShareMyTrip - Registro de nuevos usuarios</h1>
	<c:if test="${user == null}">
		<form action="registro" method="POST">
			<table>

				<tr>
					<td>Login:</td>
					<td id="login"><input type="text" name="login" required="required"></td>
				</tr>
				<tr>
					<td>Nombre:</td>
					<td id="name"><input type="text" name="nombre" required="required"></td>
				</tr>
				<tr>
					<td>Apellidos:</td>
					<td id="apellidos"><input type="text" name="apellidos" required="required"></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td id="email"><input type="email" name="email" 
						pattern="[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{1,5}"
						required="required">
						</td>
				</tr>
				<tr>
					<td>Password:</td>
					<td id="pass1"><input type="password" name="pass1" required="required"></td>
				</tr>
				<tr>
					<td>Repite Password:</td>
					<td id="pass2"><input type="password" name="pass2" required="required"></td>
				</tr>
				<tr>
					<td><input type="submit" value="Registrar"></td>
			</table>
		</form>
	</c:if>
	 <c:if test= "${ mensajeError != null }">
   <c:out value="${mensajeError}"/>
   </c:if>
   <a href="login.jsp">Atrás</a>
</body>
</html>