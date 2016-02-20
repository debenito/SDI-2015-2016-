<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<footer>
	Creado por :
	${copyright}

	| Contacto : <a
		href="mailto:${applicationScope.emailContacto}">
		${applicationScope.emailContacto}</a>
		
		<a href="logout">Cerrar Sesion |</a>

	<c:out value="${user.name}">Session del usuario ${user.login}</c:out>
		
</footer>

