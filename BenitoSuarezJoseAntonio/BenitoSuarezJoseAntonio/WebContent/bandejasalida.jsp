<%@ page import="com.sdi.model.Correo" import="java.util.List"
	import="com.sdi.model.*"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="h"%>
<html>
<head>
<link rel="stylesheet" href="css/metro-bootstrap.css">
<script src="js/jquery/jquery.min.js"></script>
<script src="js/jquery/jquery.widget.min.js"></script>
<script src="js/metro/metro.min.js"></script>
<title>Bandeja salida</title>
</head>
<body class="metro">

	<header>
		<h1>Bandeja de Salida</h1>
	</header>
	<nav class="navigation-bar dark">
		<nav class="navigation-bar-content">
			<div class="element">
				<a href="principal.jsp">Principal</a>
			</div>
			<div class="element">
				<a href="listarContactos">Alta usuario/Enviar Mensaje</a>
			</div>
			<div class="element">
				<a href="listaEntrada">Bandeja de Enviados</a>
			</div>
			<div class="element">
				<a href="papelera">Papelera</a>
			</div>

		</nav>
	</nav>
	<div class="container">
		<div class="example">
			<h:forEach var="c" items="${listaCorreosSalida}">
				<ul>
					<li><a href="bandejasalida.jsp?id=${c.id}"> <h:forEach
								var="p" items="${primero}">
								<h:if test="${p.key == c.id }">
					${c.id} ${c.fechahora} ${c.asunto} ${c.cuerpo} ${p.value.email}
						 </h:if>
							</h:forEach>
					</a></li>
				</ul>
			</h:forEach>

			<h:if test="${param.id ne null}">
				<h:set var="id" scope="session" value="${param.id}" />
			</h:if>
			<h:if test="${id ne null }">
				<h:if test="${param.lista1 == null }">
					<h:forEach var="c" items="${listaCorreosSalida}">
						<h:if test="${id == c.id}">
							<form action="guardar">

								<label><h:out value="Lista Contactos Agenda Compartida"></h:out>
								</label>
								<div class="input-control select">
									<select name="lista1">
										<option value="vacio1">---</option>
										<h:forEach var="entry" items="${listaContactos}">
											<option value="${entry.email}">${entry.id}${entry.nombre}
												${entry.apellidos}</option>
										</h:forEach>
									</select>
								</div>
								<label> <h:out value="Lista Contactos Agenda Privada"></h:out>
								</label>
								<div class="input-control select">
									<select name="lista2">
										<option value="vacio2">---</option>
										<h:forEach var="entry" items="${listaPrivada}">
											<option value="${entry.email}">${entry.id}${entry.nombre}
												${entry.apellidos}</option>
										</h:forEach>
									</select> <input type="submit" value="EnviarContacto">
								</div>
								<label> Fecha y hora : </label>
								<div class="input-control text">
									<input type="text" name="fecha" value="${c.fechahora}" />
								</div>
								<label> Para : </label>
								<textarea class="nuevo" name="para">
						<h:forEach var="c1" items="${destinatarios}">
						<h:if test="${c1.key == c.id}">${c1.value.toString()}</h:if>
						</h:forEach>
						</textarea>
								<label>Asunto :</label>
								<div class="input-control text">
									<input type="text" name="asunto" value="${c.asunto}" />
								</div>

								<label>Cuerpo :</label>
								<div class="input-control text">
									<input type="text" name="cuerpo" value="${c.cuerpo }" />
								</div>

							</form>
						</h:if>
					</h:forEach>
				</h:if>
			</h:if>
			<h:if test="${param.lista1 ne null}">

				<h2>Datos introducidos en el correo</h2>
				<h:out value="${exito}">${exito}</h:out>

				<a href="listaSalida">¿Si quieres volver a modificar el correo?
					Pincha aqui y luego en el enlace del correo que estabas modificando</a>
				<form action="nuevoCorreo">
					<label> Fecha y hora : </label>
					<div class="input-control text">
						<input type="text" name="fecha" value="${param.fecha}"
							readonly="readonly" />
					</div>

					<label>Para :</label>

					<textarea class="nuevo" name="para" readonly="readonly">
						<h:if test="${para ne null}">
						${para}</h:if>
						</textarea>
					<label> Asunto : </label>
					<div class="input-control text">
						<input type="text" name="asunto"
							value="<h:if test="${param.asunto ne null }">
						${param.asunto}
						</h:if>"
							readonly="readonly" />
					</div>
					<label> Cuerpo :</label>
					<div class="input-control text">
						<input type="text" name="cuerpo"
							value="<h:if test="${param.cuerpo ne null }">
						${param.cuerpo}
						</h:if>"
							readonly="readonly" />
					</div>

					<input type="submit" value="EnviarCorreo">


				</form>
			</h:if>
		</div>
	</div>
	<%@ include file="logout.jsp"%>
	<%@ include file="contacto.jsp"%>
</body>
</html>