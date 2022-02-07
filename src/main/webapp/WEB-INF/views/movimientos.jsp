<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" media="screen" href="css/style.css"/>
<title>Movimentos</title>
</head>
<body>
	<h1>Cuenta ${cuentaActiva.idCuenta}</h1>
	<a href="/operar">Volver a lista de operaciones</a>
	
	<h2>Saldo <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${saldo}"/></h2>
	
	<table>		 
		 <tr>
			<th class="col1">Operacion</th>
			<th class="col2">Fecha</th>
			<th class="col3">Importe</th>
		</tr>
		<c:forEach var="eleMovimiento" items="${listaMovimientosCuenta}">
			<tr>
				<td class="col1 filled-col">${eleMovimiento.operacion}</td>
				<td class="col2 filled-col">Día: <fmt:formatDate pattern = "dd-MM-yyyy" value = "${eleMovimiento.fecha}"/> Hora: <fmt:formatDate pattern = "HH:mm:ss" value = "${eleMovimiento.fecha}"/></td>
				<td class="col3 filled-col"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${eleMovimiento.cantidad}"/></td>
			</tr>
		</c:forEach>
		
	</table>

</body>
</html>