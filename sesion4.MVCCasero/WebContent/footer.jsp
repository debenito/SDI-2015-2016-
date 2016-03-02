<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<footer>
	Creado por :
	${applicationScope.copy}

	| Contacto : <a
		href="mailto:${applicationScope.uo213487}">
		${applicationScope.uo213487}</a>
		-<a href="mailto:${applicationScope.uo214459 }">${applicationScope.uo214459}</a>
		<c:if test="${user !=null }">
		<a href="logout">Cerrar Sesion |</a>

	<c:out value="${user.name}">Session del usuario ${user.login}</c:out>
		</c:if>
</footer>

