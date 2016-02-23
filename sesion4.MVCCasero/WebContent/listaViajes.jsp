<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="comprobarNavegacion.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>ShareMyTrip - Listado de viajes</title>
</head>
<body>
	<center>
		<h1>ShareMyTrip - Listado de viajes</h1>
	</center>
	<table border="1" align="center">
		<tr>
			<th>ID viaje</th>
			<th>Origen</th>
			<th>Destino</th>
			<th>Plazas libres</th>
			<c:if test="${user!=null  }">
				<th>Fecha de Salida</th>
				<th>Fecha de LLegada</th>
				<th>Maximo de Plazas</th>
				<th>Precio</th>
				<th>Comentario</th>
				<th>Identificador Promotor</th>
			</c:if>
		</tr>
		<c:forEach var="entry" items="${listaViajes}" varStatus="i">
			<tr id="item_${i.index}">
			<c:choose>
			<c:when test="${user != null }">
				<td><a href="mostrarViaje?id=${entry.id}">${entry.id}</a></td>
				</c:when>
				<c:otherwise>
				<td>${entry.id}</td>
				</c:otherwise>
				</c:choose>
				<td>${entry.departure.city}</td>
				<td>${entry.destination.city}</td>
				<td>${entry.availablePax}</td>
				<c:if test="${user!=null  }">
					<td>${entry.arrivalDate}</td>
					<td>${entry.departureDate}</td>
					<td>${entry.maxPax}</td>
					<td>${entry.estimatedCost}</td>
					<td>${entry.comments}</td>
					<td>${entry.promoterId }</td>
					
				</c:if>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${user !=null }">
	<table border="1" align="center">
		<tr>
			<th> Viaje </th>
			<th>Acerca de Usuario</th>
			<th>Para el Usuario</th>
			<th> Comentario</th>
			<th>Puntuacion</th>
			</tr>
		<c:forEach var="rating" items="${listaViajesRegistrado}" varStatus="i">
			<tr id="item_${i.index}"></tr>
			<td>${rating.seatAboutTripId}</td>
				<td>${rating.seatAboutUserId}</td>
				<td>${rating.seatFromUserId}</td>
				<td>${rating.comment}</td>
				<td>${rating.value}</td>
			</c:forEach>
			</table>
	</c:if>
	<a href="login.jsp">Atrás</a>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>