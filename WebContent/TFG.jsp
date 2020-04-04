<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>VISTA TFG</h2>

<p><b>Nombre TFG: </b>${tfg.name}</p>

<h2>SUBIR ARCHIVO</h2>

<c:choose>

    <c:when test="${tfg.status=='3'}">

		<form action="Form4TFGServlet" method="post" enctype="multipart/form-data">
			<input type="hidden" name="tfgemail" value="${tfg.email}" /> 
			<input type="file" name="file" />
			<button type="submit">Subir memoria</button>
		</form>

	</c:when>
	
	<c:when test="${tfg.status=='4'}">
	
		<p>Memoria subida con exito</p>
		
	</c:when>
	
	<c:otherwise>
		<p>Necesario Status TFG = 3</p>
        <p>Status TFG actual = ${tfg.status}</p>
    </c:otherwise>

</c:choose>

<c:if test="${tfg.status > 3}">
	<p>
	<b>Memoria: </b>
		<form action="ServeFileServlet" method="get">
		<input type="hidden" name="tfgemail" value="${tfg.email}"/>
		<button type="submit">Descargar</button>
		</form>
	</p>
</c:if>


<h2>Salir de la aplicacion</h2>

<%@ include file = "FormLogout.jsp" %>


</html>