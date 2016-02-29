<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.sdi.model.Contacto" import="java.util.List"
	import="com.sdi.model.Correo" import="com.sdi.model.Usuario"%>

<HTML>
<HEAD>
<link rel="stylesheet" href="css/metro-bootstrap.css">
<script src="js/jquery/jquery.min.js"></script>
<script src="js/jquery/jquery.widget.min.js"></script>
<script src="js/metro/metro.min.js"></script>
<TITLE>Enviar Correo</TITLE>
</HEAD>
<BODY class="metro">
	<header>
		<h1>Enviar Mensaje/Alta usuario</h1>
	</header>
	<c:if test="${user.rol == 'Administrador' }">
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
	</c:if>
	<c:if test="${user.rol == 'usuario' || user.rol == 'Cliente'}">
		<nav class="navigation-bar dark">
			<nav class="navigation-bar-content">
				<div class="element">
					<a href="principal.jsp">Menu principal</a>
				</div>
				<div class="element">
					<a href="listaEntrada">Bandeja de Enviados</a>
				</div>
				<div class="element">
					<a href="listaSalida">Bandeja de Borradores</a>
				</div>
				<div class="element">
					<a href="papelera">Papelera</a>
				</div>
			</nav>
		</nav>
	</c:if>
	<div class="container">
		<div class="example">
			<c:if test="${user.rol == 'Administrador' }">
				<h2>Lista de Agenda Compartida</h2>
				<c:out value="Lista Contactos Agenda Compartida"></c:out>
				<select name="lista1">
					<option value="vacio1">---</option>
					<c:forEach var="entry" items="${listaContactos}">

						<option value="${entry.email}">${entry.id}${entry.nombre}
							${entry.apellidos}</option>
					</c:forEach>
				</select>
			</c:if>
			<c:if test="${param.lista1 == null  && user.rol != 'Administrador' }">
				<h2>Enviar Mensaje</h2>
				<form action="anadir">
					<c:out value="Lista Contactos Agenda Compartida"></c:out>
					<select name="lista1">
						<option value="vacio1">---</option>
						<c:forEach var="entry" items="${listaContactos}">

							<option value="${entry.email}">${entry.id}${entry.nombre}
								${entry.apellidos}</option>
						</c:forEach>
					</select>
					<c:if test="${user.rol == 'usuario' || user.rol == 'Cliente'}">
						<c:out value="Lista Contactos Agenda Privada"></c:out>
						<select name="lista2">
							<option value="vacio2">---</option>
							<c:forEach var="entry" items="${listaPrivada}">
								<option value="${entry.email}">${entry.id}${entry.nombre}
									${entry.apellidos}</option>

							</c:forEach>
						</select>
					</c:if>

					<input type="submit" value="EnviarContacto"> <label>
						Fecha y hora : </label>
					<div class="input-control text">
						<input type="text" name="fecha" />
					</div>
					<label> Para :</label>
					<textarea class="nuevo" name="para">
							<c:if test="${cadena != null}">
					${cadena}
					</c:if>
					</textarea>
					<label> Asunto :</label>
					<div class="input-control text">
						<input type="text" name="asunto"
							value="<c:if test="${asunto != null }">
					${asunto }
					</c:if>" />
					</div>
					<label> Cuerpo : </label>
					<div class="input-control text">
						<input type="text" name="cuerpo"
							value="<c:if test="${cuerpo ne null }">
					${cuerpo}
					</c:if>" />
					</div>

				</form>
			</c:if>
			<c:if test="${param.lista1 ne null }">
				<h2>Datos introducidos en el correo</h2>
				<c:out value="${exito}">${exito}</c:out>
				<a href="listaSalida">¿Si quieres seguir añadiendo usuarios?
					Pincha aqui</a>
				<form action="nuevoCorreo">
					<label> Fecha y hora : </label>
					<div class="input-control text">
						<input type="text" name="fecha" value="${fecha}"
							readonly="readonly" />
					</div>
					<label> Para :</label>

					<textarea class="nuevo" name="para" readonly="readonly">
							<c:if test="${ cadena ne null}">
									${cadena}</c:if>'
							
					</textarea>

					<label> Asunto : </label>
					<div class="input-control text">
						<input type="text" name="asunto"
							value='<c:if test="${asunto ne null }">
									${asunto}</c:if>'
							readonly="readonly" />
					</div>
					<label> Cuerpo : </label>
					<div class="input-control text">
						<input type="text" name="cuerpo"
							value='<c:if test="${cuerpo ne null }">
					${cuerpo}</c:if>'
							readonly="readonly" />
					</div>
					<input type="submit" value="EnviarCorreo">


				</form>
			</c:if>

			<h2>ALTA DE USUARIO</h2>
			<form action="altaContacto">
				<label> Nombre *:</label>
				<div class="input-control text">
					<input type="text" name="nombre" /><br>
				</div>
				<label>Apellidos *:</label>
				<div class="input-control text">
					<input type="text" name="apellidos" /><br>
				</div>
				<label> Email :</label>
				<div class="input-control text">
					<input type="text" name="email" required="required" /><br>
				</div>
				<label>Direccion *:</label>
				<div class="input-control text">
					<input type="text" name="direccion" /><br>
				</div>
				<label>Ciudad *:</label>
				<div class="input-control text">
					<input type="text" name="ciudad" /><br>
				</div>
				<input type="submit" value="Alta">

			</form>
		</div>
	</div>
	<%@ include file="logout.jsp"%>
	<%@ include file="contacto.jsp"%>
</BODY>
</HTML>