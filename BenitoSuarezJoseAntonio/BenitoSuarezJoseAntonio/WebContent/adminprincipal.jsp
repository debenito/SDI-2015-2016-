<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="h"%>
<HTML>
<HEAD>
<link rel="stylesheet" href="css/metro-bootstrap.css">
<script src="js/jquery/jquery.min.js"></script>
<script src="js/jquery/jquery.widget.min.js"></script>
<script src="js/metro/metro.min.js"></script>
<TITLE>Página principal del administrador</TITLE>
</HEAD>
<BODY class="metro">
	<header>
		<h1>Menu Principal</h1>
	</header>
	<nav class="navigation-bar dark">
		<nav class="navigation-bar-content">
			<div class="element">
				<a href="listarUsuarios">Listar usuarios</a>
			</div>
			<div class="element">
				<a href="listarContactos">Agenda Compartida</a>
			</div>
			<div class="element">
				<%@ include file="logout.jsp"%>
			</div>
		</nav>
	</nav>
	<div class="container">
		<div class="example">
			<details>
				<jsp:useBean id="user" class="com.sdi.model.Usuario" scope="session">
				</jsp:useBean>
				Id:
				<jsp:getProperty property="id" name="user" /><br> Nombre:
				<jsp:getProperty property="nombre" name="user" /><br>
				Apellidos:
				<jsp:getProperty property="apellidos" name="user" /><br>
				Email:
				<jsp:getProperty property="email" name="user" /><br>

			</details>
			<h1>Bienvenido Administrador</h1>
			<h2>
				<h:if test="${exito ne null}"></h:if>
				<h:out value="${exito}"></h:out>

			</h2>
			<br> <br>
		</div>
	</div>
	<%@ include file="logout.jsp"%>
	<%@ include file="contacto.jsp"%>
</BODY>
</HTML>