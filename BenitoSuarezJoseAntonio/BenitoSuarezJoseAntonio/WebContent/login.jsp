<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="h"%>
<HTML>
<HEAD>
<link rel="stylesheet" href="css/metro-bootstrap.css">
<script src="js/jquery/jquery.min.js"></script>
<script src="js/jquery/jquery.widget.min.js"></script>
<script src="js/metro/metro.min.js"></script>
<link href="cookies.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="fichero.js"></script>

<TITLE>Inicie sesion</TITLE>
<BODY class="metro">
	<!--Código HTML de la política de cookies -->

	<!--La URL incluida es la parte que se ha de modificar -->

	<div class="cookiesms" id="cookie1">
		Esta web utiliza cookies, puedes ver nuestra <a
			href="http://politicadecookies.com/cookies.php">la política de
			cookies, aquí</a> Si continuas navegando estás aceptándola
		<button onclick="controlcookies()">Aceptar</button>
		<div class="cookies2"
			onmouseover="document.getElementById('cookie1').style.bottom = '0px';">Política
			de cookies +</div>
	</div>
	<script type="text/javascript">
		if (localStorage.controlcookie > 0) {
			document.getElementById('cookie1').style.bottom = '-50px';
		}
	</script>

	<!-- Fin del código de cookies --->

	<header>
		<H1>Inicie sesion</H1>


	</header>
	<div class="container">
		<div class="example">
			<FORM ACTION="validarse" METHOD="POST">
				<fieldset>
					<label>Su identificador de usuario</label> <INPUT TYPE="TEXT"
						NAME="nombreUsuario" required="required"> <label>Contraseña</label>
					<INPUT TYPE="password" NAME="password" required="required">

					<INPUT TYPE="Submit">

				</fieldset>
			</FORM>
			<a href="registrarse.jsp">Registrarse</a>
			<h2>
				<h:if test="${exito!= null}">${exito}</h:if>

			</h2>
		</div>
	</div>
	<footer>
		<%@ include file="contacto.jsp"%>
	</footer>
</BODY>
</HTML>
