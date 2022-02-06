<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" media="screen" href="/css/style.css"/>
<title>Inicio de sesión</title>
</head>
<body>
	<form action="login" method="post" name="formLogin">
	<fieldset>
		<legend>Inicio de sesión</legend>
		<div class="grid">
		
		<label for="cuenta">Cuenta </label>
		<input type="text" name="cuenta" id="cuenta" placeholder="Número de cuenta"/>
				
		</div>
		
	<input class="send-button" type="submit" value="Operar con la cuenta" />
		
	</fieldset>
	
	</form>
	<p>${mensajeLogin}</p>
	<p>Cuenta 1: <b>1000</b></p>
	<p>Cuenta 2: <b>2000</b></p>
</body>
</html>