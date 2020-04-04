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
<h2>VISTA PROFESOR</h2>

<p><b>Nombre profesor: </b>${profesor.name}</p>

<p><b>Numero de TFG supervisados: </b>${fn:length(advisedTFGs)}</p>

<h2>AdvisedTFGs</h2>
<table border="1">
<tr>
<th>email</th>
<th>name</th>
<th>title</th>
<th>status</th>
</tr>
<c:forEach items="${advisedTFGs}" var="advisedTFGsi">
<tr>
<td>${advisedTFGsi.email}</td>
<td>${advisedTFGsi.name}</td>
<td>${advisedTFGsi.title}</td>
<td>${advisedTFGsi.status}</td>

<td>

<c:choose>
    <c:when test="${advisedTFGsi.status=='1'}">

		<form action="Form2ProfesorServlet">
			<input type="hidden" name="tfgemail" value="${advisedTFGsi.email}" />
			<input type="hidden" name="profesor" value="${profesor.email}" />
			<button type="submit">Aceptar Tutela</button>
		</form>

	</c:when>    

</c:choose>

</td>

</tr>
</c:forEach>
</table>

<h2>Salir de la aplicacion</h2>

<%@ include file = "FormLogout.jsp" %>


</html>