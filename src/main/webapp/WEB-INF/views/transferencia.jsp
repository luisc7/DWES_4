<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" media="screen" href="/css/style.css"/>
<title>Transferencia</title>
</head>
<body>
	<h1>Cuenta ${cuentaActiva.idCuenta}</h1>
	<a href="/operar">Volver a lista de operaciones</a>
	<form action="transferencia" method="post" name="formTransf">
	<fieldset>
		<legend>Transferencia</legend>
		<div class="grid">
		
		<label for="cantidad">Cantidad (En Euros) </label>
		<input type="number" min="0" max="100000" step="0.01" value="0" name="cantidad" id="cantidad" placeholder="En Euros"/>
		
		<label for="destino">Cuenta de destino </label>
		<input type="number" min="0" step="1"  value="0" name="destino" id="destino" placeholder="Número de cuenta"/>
				
		</div>
		
	<input class="send-button" type="submit" value="Realizar transferencia" />
		
	</fieldset>
	
	</form>
	
	<p>${mensajeOperar}</p>
</body>
</html>