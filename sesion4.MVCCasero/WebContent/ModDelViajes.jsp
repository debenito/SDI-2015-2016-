<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="comprobarNavegacion.jsp"%>
<html>
<head>
<title>ShareMyTrip -Viajes Registrados</title>
</head>
<body>
	<h1>ShareMyTrip -Viajes Registrados</h1>

		<c:if test="${viajes ne null}">
		<form action="modificarViajes" method="POST">
			<table>
			<tr>
				<td><input type="text" name="id" value="${viajes.id}" readonly="readonly"
				hidden="true">
				<input type="text" name="status" value="${viajes.status}" readonly="readonly"
				hidden="true">
			</td>
			</tr>
				<tr>
					<th>Lugar de Salida</th>
				</tr>
				<tr>

					<td>Ciudad:</td>
					<td id="departurecity"><input type="text" name="departurecity"
						required="required" value="${viajes.departure.city}"></td>
				</tr>
				<tr>
					<td>Provincia:</td>
					<td id="departurestate"><input type="text"
						name="departurestate" required="required"
						value="${viajes.departure.state}"></td>
				</tr>
				<tr>
					<td>Pais:</td>
					<td id="departurecountry"><input type="text"
						name="departurecountry" required="required"
						value="${viajes.departure.country}"></td>
				</tr>
				<tr>
					<td>Calle:</td>
					<td id="departureaddress"><input type="text"
						name="departureaddress" required="required"
						value="${viajes.departure.address}"></td>

				</tr>
				<tr>
					<td>Codigo Postal:</td>
					<td id="departurezipCode"><input type="text"
						name="departurezipCode" required="required"
						value="${viajes.departure.zipCode}"></td>
				</tr>
				<tr>
					<td>GPS:</td>
					<td id="departurewaypointlat">Latitud:<input type="text"
						name="departurewaypointlat"
						value="${viajes.departure.waypoint.lat}"   pattern="(-)?([0-9]*).([0-9]*)"></td>
					<td id="departurewaypointlon">Longitud:<input type="text"
						name="departurewaypointlon"
						value="${viajes.departure.waypoint.lon}"  pattern="(-)?([0-9]*).([0-9]*)"></td>
				</tr>
				<tr>
					<th>Salida</th>
				</tr>
				<tr>
					<td id="arrivalDatefecha">Fecha de Salida:<input type="text"
						name="arrivalDatefecha" required="required"
						value="${viajes.arrivalDate.year + 1900}-${viajes.arrivalDate.month +1}-${viajes.arrivalDate.date}"
						    pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])"
						></td>

					<td id="arrivalDatehora">Hora de Salida:<input type="text"
						name="arrivalDatehora" required="required"
						value="${viajes.arrivalDate.hours}:${viajes.arrivalDate.minutes}:${viajes.arrivalDate.seconds}"
						pattern="([01]?[0-9]{1}|2[0-3]{1}):[0-5]{1}[0-9]{1}:[0-5]{1}[0-9]{1}"></td>
				</tr>
				<tr>
					<th>Lugar de Destino</th>
				</tr>
				<tr>
					<td>Ciudad:</td>
					<td id="destinationcity"><input type="text"
						name="destinationcity" required="required"
						value="${viajes.destination.city}"></td>
				</tr>
				<tr>
					<td>Provincia:</td>
					<td id="destinationstate"><input type="text"
						name="destinationstate" required="required"
						value="${viajes.destination.state}"></td>
				</tr>
				<tr>
					<td>Pais:</td>
					<td id="destinationcountry"><input type="text"
						name="destinationcountry" required="required"
						value="${viajes.destination.country}"></td>
				</tr>
				<tr>
					<td>Calle:</td>
					<td id="destinationaddress"><input type="text"
						name="destinationaddress" required="required"
						value="${viajes.destination.address}"></td>

				</tr>
				<tr>
					<td>Codigo Postal:</td>
					<td id="destinationzipCode"><input type="text"
						name="destinationzipCode" required="required"
						value="${viajes.destination.zipCode}"></td>
				</tr>
				<tr>
					<td>GPS:</td>
					<td id="destinationwaypointlat">Latitud:<input type="text"
						name="destinationwaypointlat"
						value="${viajes.destination.waypoint.lat}"   pattern="(-)?([0-9]*).([0-9]*)"></td>
					<td id="destinationwaypointlon">Longitud:<input type="text"
						name="destinationwaypointlon"
						value="${viajes.destination.waypoint.lon}"  pattern="(-)?([0-9]*).([0-9]*)"></td>
				</tr>
				<tr>
					<th>LLegada</th>
				</tr>
				<tr>
					<td id="departureDatefecha">Fecha de LLegada:<input
						type="text" name="departureDatefecha" required="required"
						value="${viajes.departureDate.year + 1900}-${viajes.departureDate.month +1}-${viajes.departureDate.date}"
						  pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])"></td>
					<td id="departureDatehora">Hora de LLegada:<input type="text"
						name="departureDatehora" required="required"
						value="${viajes.departureDate.hours}:${viajes.departureDate.minutes}:${viajes.departureDate.seconds}"
					pattern="([01]?[0-9]{1}|2[0-3]{1}):[0-5]{1}[0-9]{1}:[0-5]{1}[0-9]{1}"></td>
				</tr>
				<tr>
					<th>Coste Estimado</th>
				</tr>
				<tr>
					<td>Coste Estimado:</td>
					<td id="estimatedCost"><input type="text" name="estimatedCost"
						required="required" value="${viajes.estimatedCost}" pattern="([0-9]*).([0-9]*)"></td>
				</tr>
				<tr>
					<th>Comentarios</th>
				</tr>
				<tr>
					<td>Comentario:</td>
					<td id="comment"><input type="text" name="comment"
						 value="${viajes.comments}"></td>
				</tr>
				<tr>
					<th>Plazas</th>
				</tr>
				<tr>
					<td>Numero Maximo de plazas:</td>
					<td id="maxPax"><input type="text" name="maxPax"
						required="required" value="${viajes.maxPax}" pattern="[0-9]*"></td>
				</tr>
				<tr>

					<td>Numero de plazas libres:</td>
					<td id="availablePax"><input type="text" name="availablePax"
						required="required" value="${viajes.availablePax}" pattern="[0-9]*"></td>
				</tr>
				<tr>
					<th>Limite</th>
				</tr>
				<tr>
					<td id="closingDatefecha">Fecha de Limite:<input type="text"
						name="closingDatefecha" required="required"
						value="${viajes.closingDate.year + 1900}-${viajes.closingDate.month +1}-${viajes.closingDate.date}"
						  pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])"></td>
					<td></td>
					<td id="closingDatehora">Hora de Limite:<input type="text"
						name="closingDatehora" required="required"
						value="${viajes.closingDate.hours}:${viajes.closingDate.minutes}:${viajes.closingDate.seconds}"
						pattern="([01]?[0-9]{1}|2[0-3]{1}):[0-5]{1}[0-9]{1}:[0-5]{1}[0-9]{1}"></td>
				</tr>
				<tr>
					<td><input type="submit" value="Modificar"></td>
			</table>
		</form>
		<form action="borrarViaje" method="POST">
		<input type="text" name="id" value="${viajes.id}" readonly="readonly"
				hidden="true">
		<input type="submit" value="delete">
		</form>
	</c:if>
	<a href="listarViajes">Atrás</a>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>