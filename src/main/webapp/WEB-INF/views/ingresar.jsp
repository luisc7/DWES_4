<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" media="screen" href="/css/style.css"/>
<title>Ingreso de efectivo</title>
</head>
<body>
	<h1>Cuenta ${cuentaActiva.idCuenta}</h1>
	<a href="/operar">Volver a lista de operaciones</a>
	<form action="ingresar" method="post" name="formIngresar">
	<fieldset>
		<legend>Ingreso</legend>
		<div class="grid">
		
		<label for="cantidad">Cantidad (En Euros) </label>
		<input type="number" min="0" max="100000" step="0.01" value="0" name="cantidad" id="cantidad" placeholder="En Euros"/>
				
		</div>
		
	<input class="send-button" type="submit" value="Realizar ingreso" />
		
	</fieldset>
	
	</form>
</body>
</html>