<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" media="screen" href="css/style.css"/>
<title>Operar</title>
</head>
<body>

	<h1>Cuenta ${cuentaActiva.idCuenta}</h1>
	<h3><a href=logout>Salir</a></h3>
	<nav>
		<ul>
			<li><a href=ingresar>Ingresar</a></li>
			<li><a href=extraer>Extraer</a></li>
			<li><a href=movimientos>Ver movimientos</a></li>
			<li><a href=transferencia>Transferencia</a></li>
		</ul>
	</nav>
	<p>${mensajeOperar}</p>

</body>
</html>