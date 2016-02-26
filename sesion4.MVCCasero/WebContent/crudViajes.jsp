<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="comprobarNavegacion.jsp"%>
<html>
<head>
<title>ShareMyTrip -Viajes Registrados</title>
</head>
<body>
	<h1>ShareMyTrip -Viajes Registrados</h1>

	<table border="1">
		<tr>
			<th colspan="6">Lugar de Salida</th>
			<th colspan="2">Salida</th>
			<th colspan="6">Lugar de Destino</th>
			<th colspan="2">Fecha Limite</th>
			<th colspan="1">Coste Estimado</th>
			<th colspan="1">Comentarios</th>
			<th colspan="2">Plazas</th>
		</tr>
		<tr>
			<th>Calle</th>
			<th>Ciudad</th>
			<th>Provincia</th>
			<th>Pais</th>
			<th>Codigo Postal</th>
			<th>GPS</th>
			<th>Fecha de Salida</th>
			<th>Hora de Salida</th>
			<th>Calle</th>
			<th>Ciudad</th>
			<th>Provincia</th>
			<th>Pais</th>
			<th>Codigo Postal</th>
			<th>GPS</th>
			<th>Fecha Limite</th>
			<th>Hora Limite</th>
			<th>Coste Estimado</th>
			<th>Comentarios</th>
			<th>Plazas Maximas</th>
			<th>Plazas ocupadas</th>
		</tr>
		<c:forEach var="entry" items="${viajesPromotor}" varStatus="i">
			<tr id="item_${i.index}">
				<td><a href="mostrarViaje?id=${entry.id}">${entry.id}</a></td>
				<td>${entry.departure.city}</td>
				<td>${entry.departure.city}</td>
				<td>${entry.departure.country }</td>
				<td>${entry.departure.zipCode }</td>
				<td>${entry.departure.waypoint }</td>
				<td>${entry.arrivalDate.date}</td>
				<td>${entry.arrivalDate.time}</td>
				<td>${entry.destination.city}</td>
				<td>${entry.destination.country }</td>
				<td>${entry.destination.zipCode }</td>
				<td>${entry.destination.waypoint }</td>
				<td>${entry.departureDate}</td>
				<td>${entry.availablePax}</td>
				<td>${entry.maxPax}</td>
				<td>${entry.estimatedCost}</td>
				<td>${entry.comments}</td>
				<td>${entry.promoterId }</td>

			</tr>
		</c:forEach>
	</table>
	<a href="listarViajes">Atrás</a>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>