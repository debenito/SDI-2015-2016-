<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="comprobarNavegacion.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>ShareMyTrip - Registro de nuevos viajes</title>
</head>
<body>
	<h1>ShareMyTrip - Registro de nuevos viajes</h1>
	<form action="nuevoViaje" method="post">
		<table id="salida">
			<tr>
				<td>Lugar de salida:</td>
			</tr>

			<tr>
				<td>*Calle: <input id="calle" required="required" type="text"></td>
				<td>*Ciudad: <input id="ciudad" required="required" type="text"></td>
				<td>*Provincia: <input id="provincia" required="required"
					type="text"></td>
			</tr>
			<tr>
				<td>*C.P: <input id="cp" required="required" type="text"></td>
				<td>*País: <input id="pais" required="required" type="text"></td>
			</tr>
			<tr>
				<td>Coordenadas GPS:</td>
				<td>Latitud: <input id="lat" type="text"></td>
				<td>Longitud: <input id="lon" type="text"></td>
			</tr>

			<tr>
				<td>*Fecha de salida: <input id="fechaSalida" type="text"
					required="required"></td>
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
					type="text"></td>
				<td>*Ciudad: <input id="ciudadDestino" required="required"
					type="text"></td>
				<td>*Provincia: <input id="provinciaDestino"
					required="required" type="text"></td>
			</tr>
			<tr>
				<td>*C.P: <input id="cpDestino" required="required" type="text"></td>
				<td>*País: <input id="paisDestino" required="required"
					type="text"></td>
			</tr>
			<tr>
				<td>Coordenadas GPS:</td>
				<td>Latitud: <input id="latestino" type="text"></td>
				<td>Longitud: <input id="lonDestino" type="text"></td>
			</tr>
			<tr>
				<td>*Fecha de llegada: <input id="fechaLlegada" type="text"
					required="required"></td>
				<td>*Hora de llegada: <input id="horaLlegada" type="text"
					required="required"></td>
			</tr>
		</table>
		<hr>
		<table id="extras">
			<tr>
				<td>*Fecha limite para apuntarse: <input id="fechaLimite"
					type="text" required="required"></td>
			</tr>
			<tr>
				<td>*Coste estimado del viaje: <input id="coste" type="text"
					required="required"></td>
			</tr>
			<tr>
				<td>*Maximo de pasajeros: <input id="maximo" type="number"
					required="required"></td>
			</tr>
			<tr>
				<td>*Plazas libres: <input id="plazas" type="number"
					required="required"></td>
			</tr>

			<tr>
				<td>Obsevaciones:</td>
			</tr>
			<tr>
				<td><textarea id="observaciones" rows="10" cols="60"
						required="required"></textarea></td>
			</tr>
			<tr>
				<td>Los campos marcados con * son obligatorios</td>
			</tr>
		</table>
		<input id=enviar" type="submit" value="Enviar">
	</form>
	
</body>
</html>