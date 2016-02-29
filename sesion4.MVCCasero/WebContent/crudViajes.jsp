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
			<th colspan="1">Numero de viaje</th>
			<th colspan="6">Lugar de Salida</th>
			<th colspan="1">Salida</th>
			<th colspan="6">Lugar de Destino</th>
			<th colspan="1">LLegada</th>
			<th colspan="1">Coste Estimado</th>
			<th colspan="1">Comentarios</th>
			<th colspan="2">Plazas</th>
			<th colspan="1">Limite</th>
		</tr>
		<tr>
			<th>ID</th>
			<th>Ciudad</th>
			<th>Provincia</th>
			<th>Pais</th>
			<th>Calle</th>
			<th>Codigo Postal</th>
			<th>GPS</th>
			<th>Fecha y Hora Salida</th>
			<th>Calle</th>
			<th>Ciudad</th>
			<th>Provincia</th>
			<th>Pais</th>
			<th>Codigo Postal</th>
			<th>GPS</th>
			<th>Fecha y Hora de LLegada</th>
			<th>Coste Estimado</th>
			<th>Comentarios</th>
			<th>Plazas Maximas</th>
			<th>Plazas Libres</th>
			<th>Fecha y Hora Limite</th>
		</tr>
		<c:forEach var="entry" items="${viajesPromotor}" varStatus="i">
			<tr id="item_${i.index}">
				<td><a href="moddelViaje?id=${entry.id}">${entry.id}</a></td>
				<td>${entry.departure.city}</td>
				<td>${entry.departure.state}</td>
				<td>${entry.departure.country }</td>
				<td>${entry.departure.address }</td>
				<td>${entry.departure.zipCode}</td>
				<td>${entry.departure.waypoint.lat }
					${entry.departure.waypoint.lon }</td>
				<td>${entry.arrivalDate}</td>
				<td>${entry.destination.city}</td>
				<td>${entry.destination.state}</td>
				<td>${entry.destination.country }</td>
				<td>${entry.destination.address }</td>
				<td>${entry.destination.zipCode}</td>
				<td>${entry.destination.waypoint.lat }
					${entry.destination.waypoint.lon }</td>
				<td>${entry.departureDate}</td>
				<td>${entry.estimatedCost}</td>
				<td>${entry.comments}</td>
				<td>${entry.maxPax}</td>
				<td>${entry.availablePax}</td>
				<td>${entry.closingDate}</td>

			</tr>
		</c:forEach>
	</table>
	<br>
	<form action="registroViajes" method="POST">
		<table>
			<tr>
				<th>Lugar de Salida</th>
			</tr>
			<tr>

				<td>Ciudad:</td>
				<td id="departurecity"><input type="text" name="departurecity"
					required="required"></td>
			</tr>
			<tr>
				<td>Provincia:</td>
				<td id="departurestate"><input type="text"
					name="departurestate" required="required"></td>
			</tr>
			<tr>
				<td>Pais:</td>
				<td id="departurecountry"><input type="text"
					name="departurecountry" required="required"></td>
			</tr>
			<tr>
				<td>Calle:</td>
				<td id="departureaddress"><input type="text"
					name="departureaddress" required="required"></td>

			</tr>
			<tr>
				<td>Codigo Postal:</td>
				<td id="departurezipCode"><input type="text"
					name="departurezipCode" required="required"></td>
			</tr>
			<tr>
				<td>GPS:</td>
				<td id="departurewaypointlat">Latitud:<input type="text"
					name="departurewaypointlat"   pattern="(-)?([0-9]*).([0-9]*)"></td>
				<td id="departurewaypointlon">Longitud:<input type="text"
					name="departurewaypointlon"  pattern="(-)?([0-9]*).([0-9]*)"></td>
			</tr>
			<tr>
				<th>Salida</th>
			</tr>
			<tr>
				<td id="arrivalDatefecha">Fecha de Salida:<input type="text"
					name="arrivalDatefecha" required="required"
					pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])"></td>

				<td id="arrivalDatehora">Hora de Salida:<input type="text"
					name="arrivalDatehora" required="required"
					pattern="([01]?[0-9]{1}|2[0-3]{1}):[0-5]{1}[0-9]{1}:[0-5]{1}[0-9]{1}"></td>
			</tr>
			<tr>
				<th>Lugar de Destino</th>
			</tr>
			<tr>
				<td>Ciudad:</td>
				<td id="destinationcity"><input type="text" name="destinationcity"
					required="required"></td>
			</tr>
			<tr>
				<td>Provincia:</td>
				<td id="destinationstate"><input type="text"
					name="destinationstate" required="required"></td>
			</tr>
			<tr>
				<td>Pais:</td>
				<td id="destinationcountry"><input type="text"
					name="destinationcountry" required="required"></td>
			</tr>
			<tr>
				<td>Calle:</td>
				<td id="destinationaddress"><input type="text"
					name="destinationaddress" required="required"></td>

			</tr>
			<tr>
				<td>Codigo Postal:</td>
				<td id="destinationzipCode"><input type="text"
					name="destinationzipCode" required="required"></td>
			</tr>
			<tr>
				<td>GPS:</td>
				<td id="destinationwaypointlat">Latitud:<input type="text"
					name="destinationwaypointlat"  pattern="(-)?([0-9]*).([0-9]*)" ></td>
				<td id="destinationwaypointlon" >Longitud:<input type="text"
					name="destinationwaypointlon"  pattern="(-)?([0-9]*).([0-9]*)"></td>
			</tr>
			<tr>
				<th>LLegada</th>
			</tr>
			<tr>
				<td id="departureDatefecha">Fecha de LLegada:<input type="text"
					name="departureDatefecha" required="required"
					 pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])"></td>
				<td id="departureDatehora">Hora de LLegada:<input type="text"
					name="departureDatehora" required="required"
					pattern="([01]?[0-9]{1}|2[0-3]{1}):[0-5]{1}[0-9]{1}:[0-5]{1}[0-9]{1}"></td>
			</tr>
			<tr>
				<th>Coste Estimado</th>
			</tr>
			<tr>
				<td>Coste Estimado:</td>
				<td id="estimatedCost"><input type="text" name="estimatedCost"
					required="required" pattern="([0-9]*).([0-9]*)"></td>
			</tr>
			<tr>
				<th>Comentarios</th>
			</tr>
			<tr>
				<td>Comentario:</td>
				<td id="comment"><input type="text" name="comment"></td>
			</tr>
			<tr>
				<th>Plazas</th>
			</tr>
			<tr>
				<td>Numero Maximo de plazas:</td>
				<td id="maxPax"><input type="text" name="maxPax"
					required="required" pattern="[0-9]*"></td>
			</tr>
			<tr>

				<td>Numero de plazas libres:</td>
				<td id="availablePax"><input type="text" name="availablePax"
					required="required" pattern="[0-9]*"></td>
			</tr>
			<tr>
				<th>Limite</th>
			</tr>
			<tr>
				<td id="closingDatefecha">Fecha de Limite:<input type="text"
					name="closingDatefecha" required="required"
					 pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])"></td>
				<td></td>
				<td id="closingDatehora">Hora de Limite:<input type="text"
					name="closingDatehora" required="required"
					pattern="([01]?[0-9]{1}|2[0-3]{1}):[0-5]{1}[0-9]{1}:[0-5]{1}[0-9]{1}"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Registrar"></td>
		</table>
	</form>

	<a href="listarViajes">Atrás</a>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>