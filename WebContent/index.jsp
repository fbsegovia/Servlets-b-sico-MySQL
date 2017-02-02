<%@page import="entidades.Persona"%>
<%@page import="java.util.List"%>
<%@page import="negocio.GestorPersonas"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alta Usuarios</title>
</head>
<body>

<%
	GestorPersonas gestorpersonas = new GestorPersonas();
	List<Persona> listaPersonas = gestorpersonas.listar();
	
%>

	<table border="3">
		<tr>
			<th>ID</th>
			<th>Nombre</th>
			<th>Edad</th>
			<th>Peso</th>
			<th>Borrar</th>
			<th>Modificar</th>
		</tr>
		<%
			for(Persona p: listaPersonas){
				
		%>
		<tr>
			<td><%=p.getId() %></td>
			<td><%=p.getNombre() %></td>
			<td><%=p.getEdad() %></td>
			<td><%=p.getPeso() %></td>
			<td>
				<a href="BajaPersonaServlet?id=<%=p.getId() %>">
					<img src="iconos/iconoborrarpersona.png"/>
				</a>
			</td>
			<td>
			<a href="modificarPersona.jsp?id=<%=p.getId() %>">
					<img src="iconos/iconomodificarpersona.png"/>
				</a>
			</td>
		</tr>
		<%
		}
		%>
	</table>
	<form action="AltaPersonaServlet" method="post"><br/>
		Nombre:<input type="text" name="nombre"/><br/>
		Edad:<input type="number" name="edad"/><br/>
		Peso:<input type="number" step="0.1" name="peso"/><br/>
		<input type="submit" value="Alta Usuario"/>
	</form>
</body>
</html>