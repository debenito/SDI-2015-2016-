
<HTML>
<head>
<link rel="stylesheet" href="css/metro-bootstrap.css">
<script src="js/jquery/jquery.min.js"></script>
<script src="js/jquery/jquery.widget.min.js"></script>
<script src="js/metro/metro.min.js"></script>
<Title>Registrarse</Title>
</head>
<body class="metro">
	<header>
		<h1>Registrarse</h1>
	</header>
	<nav class="navigation-bar dark">
		<nav class="navigation-bar-content">
			<div class="element">
				<a href="login.jsp">Loguer</a>
			</div>
		</nav>
	</nav>
	<div class="container">
		<div class="example">
			<form action="registrarDatos">
				<label> Login: </label>
				<div class="input-control text">
					<input type="text" name="login" required="required" />
				</div>
				<label> Nombre: </label>
				<div class="input-control text">
					<input type="text" name="nombre" required="required" />
				</div>
				<label> Apellidos:</label>
				<div class="input-control text">
					<input type="text" name="apellidos" required="required" />
				</div>
				<label>Contraseña: </label>
				<div class="input-control password">
					<input type="password" name="password" required="required" />
				</div>
				<label> RepitaContraseña: </label>
				<div class="input-control password">
					<input type="password" name="password2" required="required" />
				</div>
				<INPUT TYPE="Submit" VALUE="Enviar">
			</form>
		</div>
	</div>

</body>
</HTML>