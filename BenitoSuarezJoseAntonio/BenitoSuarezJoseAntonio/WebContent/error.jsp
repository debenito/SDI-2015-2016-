<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="e"%>
<HTML>
<HEAD>
<link rel="stylesheet" href="css/metro-bootstrap.css">
<script src="js/jquery/jquery.min.js"></script>
<script src="js/jquery/jquery.widget.min.js"></script>
<script src="js/metro/metro.min.js"></script>
</HEAD>
<TITLE>Error</TITLE>
<BODY class="metro">
	<header>
		<h1>Se ha producido algun error</h1>

	</header>
	<div class="container">
		<div class="example">
			<h2>
				<e:out value="${error }">${error}</e:out>
			</h2>
			<a href="login.jsp">Iniciar sesion</a>
		</div>
	</div>
</BODY>
</HTML>