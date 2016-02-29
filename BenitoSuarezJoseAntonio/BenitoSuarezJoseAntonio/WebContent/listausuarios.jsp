<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.sdi.model.Usuario" import="java.util.List"%>
<HTML>
<HEAD>
<link rel="stylesheet" href="css/metro-bootstrap.css">
<script src="js/jquery/jquery.min.js"></script>
<script src="js/jquery/jquery.widget.min.js"></script>
<script src="js/metro/metro.min.js"></script>
<script type="text/javascript" src="filtro.js"></script>
<TITLE>Listado de usuarios</TITLE>
</HEAD>
<BODY class="metro">
	<header>
		<h1>Listado de Usuarios</h1>
	</header>
	<nav class="navigation-bar dark">
		<nav class="navigation-bar-content">
			<div class="element">
				<a href="adminprincipal.jsp">Menu principal</a>
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
			<input type="text" id="searchbar" class="form-control" onkeyup="search()" placeholder="Filtre por nombre">
			<ul>
			<c:forEach var="entry" items="${listaUsuarios}">
			<div class="program">
				<li><a href="listausuarios.jsp?login=${entry.login}">${entry.nombre}
					${entry.apellidos}</a></li>
				</div>
			</c:forEach>
			</ul>
			<c:if test="${param.login ne null}">
				<c:forEach var="u" items="${listaUsuarios}">
					<c:if test="${param.login == u.login }">
						<form action="cambiarDatos">
							<label> Id:</label>
							<div class="input-control text">
								<input type="text" value="${u.id}" readonly="readonly" name="id" />
							</div>
							<label>Nombre: </label>
							<div class="input-control text">
								<input type="text" value="${u.nombre}" name="nombre" />
							</div>
							<label> Apellidos:</label>
							<div class="input-control text">
								<input type="text" value="${u.apellidos}" name="apellidos" /><br>
							</div>
							<label> Email: </label>
							<div class="input-control text">
								<input type="text" value="${u.email}" name="email" /><br>
							</div>
							<label>Contraseña:</label>
							<div class="input-control text">
								<input type="password" value="${u.passwd}" name="password" /><br>
							</div>
							<label> Repetir: </label>
							<div class="input-control text">
								<input type="password" value="${u.passwd}" name="password2" /><br>
							</div>
							<label>Rol:</label>
							<div class="input-control text">
								<input type="text" value="${u.rol}" name="rol" /><br>
							</div>
							<label> Login:</label>
							<div class="input-control text">
								<input type="text" value="${u.login}" name="login" /><br>
							</div>
							<label>Activo: <input type="checkbox"
								<c:if test="${u.activo == 'true' }">checked="checked"</c:if>
								name="activo" /></label> <label> <input type="submit"
								value="modificar">
							</label>
						</form>
					</c:if>
				</c:forEach>
			</c:if>
		</div>
	</div>

	<%@ include file="logout.jsp"%>
	<%@ include file="contacto.jsp"%>


</BODY>
</HTML>