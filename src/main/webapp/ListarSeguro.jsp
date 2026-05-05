<%@page import="java.util.ArrayList" %>
<%@page import="dominio.Seguro" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listar Seguros</title>
</head>
<body>
	<%@ include file="includes/nav.jspf" %>
	<hr>
	<h1>Tipo de seguros en la base de datos</h1>
	
	<form action="ListarSegurosServlet" method="POST">
		<label>Busqueda por tipo de seguros:</label>
		<select name="ddlFiltroTipo">
			<option value="1">Seguro de casas</option>
			<option value="2">Seguro de vida</option>
			<option value="3">Seguro de motos</option>
		</select>
		<input type="submit" value="Filtrar">
	</form>
	<br></br>
	
	<table border="1" cellpadding="5" cellspacing="0">
		<thead>
			<tr>
				<th>ID Seguro</th>
				<th>Descripcion Seguro</th>
				<th>Descripcion Tipo Seguro</th>
				<th>Costo Contratacion</th>
				<th>Costo Maximo Asegurado</th>
			</tr>
		</thead>
		<tbody>
			<% 
				ArrayList<Seguro> lista = (ArrayList<Seguro>) request.getAttribute("lista");
				if (lista != null && !lista.isEmpty()) {
					for (Seguro s : lista) {
			%>
			<tr>
				<td style="text-align: center;"><%= s.getIdSeguro() %></td>
				<td><%= s.getDescripcion() %></td>
				<td style="text-align: center;"><%= s.getTipoSeguro().getDescripcion() %></td>
				<td style="text-align: center;"><%= s.getCostoContratacion() %></td>
				<td style="text-align: center;"><%= s.getCostoAsegurado() %></td>
			</tr>
			<% 
				}
					}else {
			%>
			<tr>
				<td colspan="5" style="text-align: center;">No hay seguros para mostrar.</td>
			</tr>
			<% 
					}
			%>
		</tbody>
	</table>
</body>
</html>