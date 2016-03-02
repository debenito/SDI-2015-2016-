<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="comprobarNavegacion.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>ShareMyTrip - Página principal del usuario</title>
</head>
<body>

	<jsp:useBean id="user" class="uo.sdi.model.User" scope="session" />
	
	<div id="navSuperior">
		<ul>
		<li><a href="">Desconectarse</a></li>
		</ul>
	</div>
	<center>
		<h1>Bienvenido ${user.name} ${user.surname}</h1>
	</center>

	<div id="menu">
	<h2>Menu</h2>
		<ul>
			<li><a href="modificarDatos">Modificar mis datos</a></li>
			<li><a href="listarViajes">Listar viajes</a></li>
			<li><a href="registroViajes">Registrar nuevo viaje</a></li>
			<li><a href="viajesPromotor">Mis viajes</a>
		</ul>
	</div>
</body>
</html>