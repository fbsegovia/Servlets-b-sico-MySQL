<%@page import="entidades.Persona"%>
<%@page import="negocio.GestorPersonas"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificar Persona</title>
</head>
<body>
	<%
		String sId = request.getParameter("id");
		int id = Integer.parseInt(sId);
		GestorPersonas gp = new GestorPersonas();
		Persona p = gp.obtener(id);
		
	%>
	<h1>Modificar Persona</h1>
	<form action="ModificarPersonaServlet" method="post">
		ID:<input type="text" name="id" value="<%=id%>" readonly="readonly"/><br/>
		Nombre:<input type="text" name="nombre" value="<%=p.getNombre()%>"/><br/>
		Edad:<input type="number" name="edad" value="<%=p.getEdad()%>"/><br/>
		Peso:<input type="number" step="0.1" name="peso" value="<%=p.getPeso()%>"/><br/>
		<input type="submit" value="Modificar Usuario"/>
	</form>
	
	
	
</body>
</html>