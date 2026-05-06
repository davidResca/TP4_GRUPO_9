<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList" %>
<%@ page import="dominio.TipoSeguro" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agregar Seguros</title>
<style type="text/css">
form {
	border: 2px solid blue;
	display: inline-block;
	padding: 15px;
}
</style>
</head>
<body>
	<%@ include file="includes/nav.jspf" %>
	<hr>	
	
	<h1>Agregar Seguros</h1><br>
	<form action="AgregarSeguroServlet" method="POST">
		<table>
			<tr>
			    <td>Id Seguro:</td>
			    <td><input type="text" value="${proximoId}" readonly></td>
			</tr>
			<tr>
				<td>Descripcion :</td>
				<td><label><input type="text" name="txtDescripcion"></label></td>
			</tr>
			
			<tr>
				<td>Tipo de Seguro:</td>
				<td>
					<select name="ddlTipoSeguro">
						<%
							ArrayList<TipoSeguro> tipos = (ArrayList<TipoSeguro>) request.getAttribute("tipos");
							if (tipos != null) {
								for (TipoSeguro t : tipos) {
						%>
							<option value="<%= t.getId() %>"><%= t.getDescripcion() %></option>
						<%
								}
							}
						%>
					</select>
				</td>
			</tr>
			<tr>
				<td>Costo contratacion:</td>
				<td><input type="number" step="0.01" name="txtCostoContratacion" required></td>
			</tr>
			<tr>
				<td>Costo Maximo Asegurado:</td>
				<td><input type="number" step="0.01" name="txtCostoMaximo" required></td>
			</tr>
			<tr>
				<td colspan="2">
					<br>
					<input type="submit" value="Aceptar" name="btnAceptarSeguro">
				</td>
			</tr>	
		</table>
	</form>
</body>
</html>