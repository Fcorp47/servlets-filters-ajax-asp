<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee List</title>
</head>
<body>
	<h3>List of Employees</h3>
	<br>
	<a href="<%= request.getContextPath() %>/new">Add New Employee</a>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>User Name</th>
				<th>Contact No</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="emp" items="${listEmployee}">
				<tr>
					<td><c:out value="${emp.id}"></c:out></td>
					<td><c:out value="${emp.firstname}"></c:out></td>
					<td><c:out value="${emp.lastname}"></c:out></td>
					<td><c:out value="${emp.username}"></c:out></td>
					<td><c:out value="${emp.contactno}"></c:out></td>
					<td>
						<a href="edit?id=<c:out value='${emp.id}'/>">Edit</a>
						&nbsp;&nbsp;&nbsp;
						<a href="delete?id=<c:out value='${emp.id}'/>">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>