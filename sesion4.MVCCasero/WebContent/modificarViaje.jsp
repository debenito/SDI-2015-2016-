<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="comprobarNavegacion.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>ShareMyTrip - Modificar datos del viaje</title>
</head>

<body>
	<h1>ShareMyTrip - Modificar datos del viaje</h1>
	<form action="nuevoViaje" method="post">
		<table id="salida">
			<tr>
				<td>Lugar de salida:</td>
			</tr>

			<tr>
				<td>*Calle: <input id="calle" required="required" type="text" value="${viaje.departure.address}"></td>
				<td>*Ciudad: <input id="ciudad" required="required" type="text" value="${viaje.departure.city }"></td>
				<td>*Provincia: <input id="provincia" required="required"
					type="text" value="${viaje.departure.state}"></td>
			</tr>
			<tr>
				<td>*C.P: <input id="cp" required="required" type="text"value="${viaje.departure.zipCode }" ></td>
				<td>*País: <input id="pais" required="required" type="text" value="${viaje.departure.country}"></td>
			</tr>
			<tr>
				<td>Coordenadas GPS:</td>
				<td>Latitud: <input id="lat" type="text" value="${viaje.departure.waypoint.lat}"></td>
				<td>Longitud: <input id="lon" type="text" value="${viaje.departure.waypoint.lon}"></td>
			</tr>

			<tr>
				<td>*Fecha de salida: <input id="fechaSalida" type="text"
					required="required" value="${viaje.departureDate}"></td>
				<td>*Hora de salida: <input id="horaSalida" type="text"
					required="required"></td>
			</tr>

		</table>
		<hr>
		<table id="destino">

			<tr>
				<td>Lugar de destino:</td>
			</tr>

			<tr>
				<td>*Calle: <input id="calleDestino" required="required"
					type="text" value="${viaje.destination.address}"></td>
				<td>*Ciudad: <input id="ciudadDestino" required="required"
					type="text" value="${viaje.destination.city }"></td>
				<td>*Provincia: <input id="provinciaDestino"
					required="required" type="text" value="${viaje.destination.state}"></td>
			</tr>
			<tr>
				<td>*C.P: <input id="cpDestino" required="required" type="text" value="${viaje.destination.zipCode }"></td>
				<td>*País: <input id="paisDestino" required="required"
					type="text" value="${viaje.destination.country }"></td>
			</tr>
			<tr>
				<td>Coordenadas GPS:</td>
				<td>Latitud: <input id="latDestino" type="text" value="${viaje.destination.waypoint.lat }"></td>
				<td>Longitud: <input id="lonDestino" type="text" value="${viaje.destination.waypoint.lon }"></td>
			</tr>
			<tr>
				<td>*Fecha de llegada: <input id="fechaLlegada" type="text"
					required="required" value="${viaje.arrivalDate}"></td>
				<td>*Hora de llegada: <input id="horaLlegada" type="text"
					required="required"></td>
			</tr>
		</table>
		<hr>
		<table id="extras">
			<tr>
				<td>*Fecha limite para apuntarse: <input id="fechaLimite"
					type="text" required="required" value="${viaje.closingDate}"></td>
			</tr>
			<tr>
				<td>*Coste estimado del viaje: <input id="coste" type="text"
					required="required" value="${viaje.estimatedCost}"></td>
			</tr>
			<tr>
				<td>*Maximo de pasajeros: <input id="maximo" type="number"
					required="required" value="${viaje.maxPax }"></td>
			</tr>
			<tr>
				<td>*Plazas libres: <input id="plazas" type="number"
					required="required" value="${viaje.availablePax }"></td>
			</tr>

			<tr>
				<td>Obsevaciones:</td>
			</tr>
			<tr>
				<td><textarea id="observaciones" rows="10" cols="60"
						required="required">${viaje.comments}</textarea></td>
			</tr>
			<tr>
				<td>Los campos marcados con * son obligatorios</td>
			</tr>
		</table>
		<input id=enviar" type="submit" value="Enviar">
	</form>
	
</body>
</html>