
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="h"%>
<html>
<head>
<link rel="stylesheet" href="css/metro-bootstrap.css">
<script src="js/jquery/jquery.min.js"></script>
<script src="js/jquery/jquery.widget.min.js"></script>
<script src="js/metro/metro.min.js"></script>
<script type="text/javascript" src="filtro.js"></script>
<title>Bandeja entrada</title>
</head>
<body class="metro">
	<header>
		<h1>Bandeja de Entrada</h1>
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
				<a href="listaSalida">Bandeja de Borradores</a>
			</div>
			<div class="element">
				<a href="papelera">Papelera</a>
			</div>

		</nav>
	</nav>
	<div class="container">
		<div class="example">
			<input type="text" id="searchbar" class="form-control"
				onkeyup="search()" placeholder="Filtre por nombre">

			<ul>
				<h:forEach var="c" items="${listaCorreosEntrada}">
					<div class="program">
						<li><a href="bandejaentrada.jsp?id=${c.id}"> <h:forEach
									var="p" items="${primero}">
									<h:if test="${p.key == c.id }">
					${c.id} ${c.fechahora} ${c.asunto} ${c.cuerpo} ${p.value.email}
						 </h:if>
								</h:forEach> ${c.id }
						</a></li>
					</div>
				</h:forEach>
			</ul>

			<h:if test="${param.id ne null}">
				<h:set var="id" scope="session" value="${param.id}" />
			</h:if>
			<h:if test="${id ne null}">
				<h:forEach var="c" items="${listaCorreosEntrada}">
					<h:if test="${id == c.id}">
						<label>Fecha y hora : </label>
						<div class="input-control text">
							<input type="text" readonly="readonly" value="${c.fechahora}" />
						</div>
						<label>Para :</label>

						<textarea class="nuevo" name="para">
						<h:forEach var="c1" items="${destinatarios}">
						<h:if test="${c1.key == c.id}">${c1.value.toString()}</h:if>
						</h:forEach>
						</textarea>


						<label>Asunto : </label>
						<div class="input-control text">
							<input type="text" readonly="readonly" value="${c.asunto }" />
						</div>
						<label>Cuerpo :</label>
						<div class="input-control text">
							<input type="text" readonly="readonly" value="${c.cuerpo}" />
						</div>
					</h:if>
				</h:forEach>
			</h:if>
		</div>
	</div>
	<%@ include file="logout.jsp"%>
	<%@ include file="contacto.jsp"%>
</body>
</html>
"
