<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="comprobarNavegacion.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>ShareMyTrip - Listado de viajes</title>
</head>
<body>
<center><h1>ShareMyTrip - Listado de viajes</h1></center>
	<table id="salida">
			<tr>
				<td>Lugar de salida:</td>
			</tr>

			<tr>
				<td>Calle: ${viaje.departure.address }</td>
				<td>Ciudad: ${viaje.departure.city}</td>
				<td>Provincia: ${viaje.departure.state}</td>
			</tr>
			<tr>
				<td>C.P: ${viaje.departure.zipCode}</td>
				<td>País: ${viaje.departure.country}</td>
			</tr>
			<tr>
				<td>Coordenadas GPS:</td>
				<td>Latitud: ${viaje.departure.waypoint.lat }</td>
				<td>Longitud: ${viaje.departure.waypoint.lon }></td>
			</tr>

			<tr>
				<td>Fecha de salida: ${viaje.departureDate }</td>
				<td>Hora de salida: </td>
			</tr>

		</table>
		<hr>
		<table id="destino">

			<tr>
				<td>Lugar de destino:</td>
			</tr>

			<tr>
				<td>Calle: ${viaje.destination.address}</td>
				<td>Ciudad: ${viaje.destination.city }</td>
				<td>Provincia: ${viaje.destination.state }</td>
			</tr>
			<tr>
				<td>C.P: ${viaje.destination.zipCode }</td>
				<td>País: ${viaje.destination.country }</td>
			</tr>
			<tr>
				<td>Coordenadas GPS:</td>
				<td>Latitud: ${viaje.destination.waypoint.lat }</td>
				<td>Longitud: ${viaje.destination.waypoint.lon }</td>
			</tr>
			<tr>
				<td>Fecha de llegada: ${viaje.arrivalDate }</td>
				<td>Hora de llegada: </td>
			</tr>
		</table>
		<hr>
		<table id="extras">
			<tr>
				<td>Fecha limite para apuntarse: ${viaje.closingDate }</td>
			</tr>
			<tr>
				<td>Coste estimado del viaje: ${viaje.estimatedCost }</td>
			</tr>
			<tr>
				<td>Maximo de pasajeros: ${viaje.maxPax }</td>
			</tr>
			<tr>
				<td>Plazas libres: ${viaje.availablePax }</td>
			</tr>

			<tr>
				<td>Obsevaciones:</td>
			</tr>
			<tr>
				<td>${viaje.comments }</td>
			</tr>
			<tr>
				<td><input type="submit" value="Apuntarse"></td>
			</tr>
		</table>
</body>
</html>