<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="comprobarNavegacion.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>ShareMyTrip - Mis viajes</title>
</head>
<body>

	

	<center>
		<h1>ShareMyTrip - Mis viajes</h1>
	</center>
	<table border="1" align="center">
		<tr>
			<th>ID viaje</th>
			<th>Origen</th>
			<th>Destino</th>
			<th>Plazas libres</th>
			<th>Estado</th>
			<th>Acciones</th>
		</tr>
		<c:forEach var="entry" items="${viajesPromotor}" varStatus="i">
			<tr id="item_${i.index}">
				
				<td><a href="mostrarViaje?id=${entry.id}">${entry.id}</a></td>
				<td>${entry.departure.city}</td>
				<td>${entry.destination.city}</td>
				<td>${entry.availablePax}</td>
				<td>PROMOTOR</td>
					<c:if test="${user.id!=null}">
						<td>
						<a href="modificarViaje?id=${entry.id}">Editar</a>
						<a href="EliminarViaje">Eliminar</a></td>
					</c:if>
			
			</tr>
		</c:forEach>
	</table>
	
			<a href="principal.jsp">Atras</a>
		</body>
</html>