<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="comprobarNavegacion.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>ShareMyTrip - Página principal del usuario</title>
</head>
<body>

	<jsp:useBean id="user" class="uo.sdi.model.User" scope="session" />

	<form action="modificarDatos" method="post">
		<table>
			<c:if test="${user != null }">

				<tr>
					<td>Login:</td>
					<td id="login"><input type="text"
						value="<c:out
							value="${user.login}"/>" name="login" /></td>
				</tr>
				<tr>
					<td>Nombre:</td>
					<td id="name"><input type="text"
						value="<c:out
							value="${user.name}"/>" name="name" /></td>
				</tr>
				<tr>
					<td>Apellidos:</td>
					<td id="surname"><input type="text"
						value="<c:out
							value="${user.surname}"/>" name="surname" />
					</td>
				</tr>
				<tr>
					<td>Email:</td>
					<td id="email"><input type="email" name="email" size="15"
						value="<jsp:getProperty property="email" name="user"/>"
						pattern="[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{1,5}">
					</td>
				</tr>
				<tr>
					<td>Contraseña nueva:</td>
					<td id="pass"><input type="password" name="pass" size="15" /></td>
				</tr>
				<tr>
					<td>Repita contraseña:</td>
					<td id="pass1"><input type="password" name="pass1" size="15" /></td>
				</tr>
				<tr>
					<td>
				</tr>
				<tr>
				<td>Contraseña vieja</td>
				<td><input type="password" name="passVieja" size="15" /></td>
				</tr>
				<tr>
				<td><input type="submit" value="Confirmar"></td>
				</tr>
			</c:if>

		</table>
	</form>
	<br /> Es Vd el usuario número: ${contador}
	<br />
	<c:if test="${mensajeError != null }">
		<c:out value="${mensajeError }" />
	</c:if>
</body>
</html>
