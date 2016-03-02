<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="comprobarNavegacion.jsp"%>

<html>
<head>
<title>ShareMyTrip - Listado de viajes Participados</title>
</head>
<body>
	<h1>ShareMyTrip - Listado de viajes Participados</h1>
	<table border="1">
		<!-- Viajes Como Promotor -->
		<c:forEach var="entry" items="${viajesPromotor}" varStatus="i">
			<tr>
				<th>ID viaje</th>
				<th>Origen</th>
				<th>Destino</th>
				<th>Fecha de LLegada</th>
				<th>Estado</th>

			</tr>
			<tr>
				<td><a href="usuariosPendientes?id=${entry.id}">${entry.id}</a></td>
				<td>${entry.departure.city}</td>
				<td>${entry.destination.city}</td>
				<td>${entry.arrivalDate}</td>
				<td>PROMOTOR</td>
			</tr>
		</c:forEach>
	</table>
	<!-- Viajes Pendiente  -->
	<form action="cancelarPlaza" method="post">
		<table border="1">

			<c:forEach var="pendientes" items="${viajesPendientes}" varStatus="i">
				<tr>
					<th>ID viaje</th>
					<th>Origen</th>
					<th>Destino</th>
					<th>Fecha de LLegada</th>
					<th>Estado</th>

				</tr>
				<tr id="item_${i.index}">

					<td>${pendientes.id}</td>
					<td>${pendientes.departure.city}</td>
					<td>${pendientes.destination.city}</td>
					<td>${pendientes.arrivalDate}</td>
					<td>PENDIENTE</td>
					<td><input type="submit" value="Cancelar" /></td>
				</tr>
				<input type="text" name="id" hidden="true" value="${pendientes.id}">
			</c:forEach>

		</table>
	</form>
	<!-- Viajes Sin Plaza  -->
	<table border="1">
		<c:forEach var="sinplaza" items="${viajesSinPlaza}" varStatus="i">
			<tr>
				<th>ID viaje</th>
				<th>Origen</th>
				<th>Destino</th>
				<th>Fecha de LLegada</th>
				<th>Estado</th>

			</tr>
			<tr id="item_${i.index}">
				<td>${sinplaza.id}</td>
				<td>${sinplaza.departure.city}</td>
				<td>${sinplaza.destination.city}</td>
				<td>${sinplaza.arrivalDate}</td>
				<td>SIN PLAZA</td>
			</tr>
		</c:forEach>
	</table>
	<!-- Viajes ADMITIDO  -->
	<table border="1">
		<c:forEach var="admitido" items="${viajesAdmitido}" varStatus="i">
			<tr>
				<th>ID viaje</th>
				<th>Origen</th>
				<th>Destino</th>
				<th>Fecha de LLegada</th>
				<th>Estado</th>

			</tr>
			<tr id="item_${i.index}">
				<td>${admitido.id}</td>
				<td>${admitido.departure.city}</td>
				<td>${admitido.destination.city}</td>
				<td>${admitido.arrivalDate}</td>
				<td>ADMITIDO</td>
			</tr>
		</c:forEach>
	</table>
	<!-- Viajes Excluido  -->

	<table border="1">
		<c:forEach var="excluido" items="${viajesExcluido}" varStatus="i">
			<tr>
				<th>ID viaje</th>
				<th>Origen</th>
				<th>Destino</th>
				<th>Fecha de LLegada</th>
				<th>Estado</th>

			</tr>
			<tr id="item_${i.index}">
				<td>${excluido.id}</td>
				<td>${excluido.departure.city}</td>
				<td>${excluido.destination.city}</td>
				<td>${excluido.arrivalDate}</td>
				<td>EXCLUIDO</td>
			</tr>
		</c:forEach>
	</table>
	<!-- Usuarios pendientes de confirmacion o cancelacion -->
	<c:if test="${ listausuariosPendientes ne null}">
		<form action="confirmarYborrar" method="post">
			<table border="1">
				<c:forEach var="usuario" items="${listausuariosPendientes}"
					varStatus="i">
					<tr>
						<th>ID viaje</th>
						<th>ID usuario</th>


					</tr>
					
					<tr>
					<td><input type="text" name="idUser" readonly="readonly"
						value="${usuario.userId}"></td>
					<td><input type="text" name="idTrip" readonly="readonly"
						value="${usuario.tripId}"></td>
					<td><input type="submit" value="Confirmar" name="confirmar"/></td>
					<td><input type="submit" value="Eliminar" name="eliminar"/></td>
					</tr>
					
					<tr>
					<c:if test="${cupo ne null }">
					<td> ${cupo}</td>
					</c:if>
					</tr>
				</c:forEach>
			</table>
			</form>
	</c:if>
	<!-- Acordarse de quitar el atras que solo vale para usuarios Publicos -->
	<a href="login.jsp">Atrás</a>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>