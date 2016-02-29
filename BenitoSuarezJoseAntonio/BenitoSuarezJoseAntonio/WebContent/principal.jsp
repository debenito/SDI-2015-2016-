<%@page import="com.sdi.infrastructure.Factories"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.sdi.model.*" import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="h"%>
<HTML>
<HEAD>
<link rel="stylesheet" href="css/metro-bootstrap.css">
<script src="js/jquery/jquery.min.js"></script>
<script src="js/jquery/jquery.widget.min.js"></script>
<script src="js/metro/metro.min.js"></script>
<TITLE>Página principal del usuario</TITLE>
</HEAD>
<body class="metro">
	<header> <h1>Página principal del usuario</h1></header>
	<nav class="navigation-bar dark">
		<nav class="navigation-bar-content">
			<div class="element">
				<a href="listarContactos">Agenda de <jsp:getProperty
						property="login" name="user" /></a>
			</div>
			<div class="element">
				<a href="listaEntrada">Bandeja de Enviados</a>
			</div>
			<div class="element">
				<a href="listaSalida">Bandeja de Borradores</a>
			</div>
			<div class="element">
				<a href="papelera">Papelera</a>
			</div>
		</nav>
	</nav>
	<div class="container">
		<div class="example">
			<aside>
				<details>
					<jsp:useBean id="user" class="com.sdi.model.Usuario"
						scope="session">
					</jsp:useBean>
					Id :<jsp:getProperty property="id" name="user" /><br> Nombre:
					<jsp:getProperty property="nombre" name="user" /><br>
					Apellidos:
					<jsp:getProperty property="apellidos" name="user" /><br> Es
					Vd el usuario número: ${contador}
					<label>El numero de caracteres de su nombre es :
					<h:out value="${user.getNameLength()}">${user.getNameLength()}</h:out>	</label>
				</details>
			</aside>
			<FORM ACTION="modificarDatos" METHOD="POST">
				<fieldset>

					<label>Nombre: </label>
					<div class="input-control text">
						<INPUT TYPE="TEXT" NAME="nombre"
							VALUE="<jsp:getProperty 
  property="nombre" name="user"/>">

					</div>

					<label>Apellidos: </label>
					<div class="input-control text">
						<INPUT TYPE="TEXT" NAME="apellidos"
							VALUE="<jsp:getProperty 
  property="apellidos" name="user"/>">
					</div>

					<label>Email: </label>
					<div class="input-control text">
						<INPUT TYPE="TEXT" NAME="email"
							VALUE="<jsp:getProperty 
  property="email" name="user"/>">
					</div>



					<label>Contraseña Antigua: </label>
					<div class="input-control password">
						<INPUT TYPE="password" NAME="password" />
					</div>

					<label> Contraseña Nueva : </label>
					<div class="input-control password">
						<INPUT TYPE="password" NAME="password2" />

					</div>

					<label>Repita la contraseña Nueva : </label>
					<div class="input-control password size">
						<INPUT TYPE="password" NAME="password3" />
					</div>
					<INPUT TYPE="Submit" VALUE="Modificar">
				</fieldset>
			</FORM>




			<br>
			<%@ include file="logout.jsp"%>
			<h2>
				<h:if test="${exito!= null}">${exito}</h:if>

			</h2>

		</div>
	</div>
	<%@ include file="logout.jsp"%>
	<%@ include file="contacto.jsp"%>

</BODY>
</HTML>
