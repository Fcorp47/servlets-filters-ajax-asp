<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Management System</title>
</head>
<body>
	<c:if test="${employee != null}">
		<form action="Update" method="post">
		<h2>Edit Employee</h2>
		<input type="hidden" name="hdnid" value="${employee.id}">
	</c:if>
	<c:if test="${employee == null}">
		<form action="Insert" method="post">
		<h2>Add Employee</h2>
	</c:if>
	<table>
		<tr>
			<td>First Name:</td>
			<td><input type="text" name="txtFirstName"
				placeHolder="Enter First Name" value="${employee.firstname}" /></td>
		</tr>
		<tr>
			<td>Last Name:</td>
			<td><input type="text" name="txtLastName"
				placeHolder="Enter Last Name" value="${employee.lastname}" /></td>
		</tr>
		<tr>
			<td>User Name:</td>
			<td><input type="text" name="txtUserName"
				placeHolder="Enter User Name" value="${employee.username}" /></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><input type="password" name="txtPassword"
				placeHolder="Enter Password" value="${employee.password}" /></td>
		</tr>
		<tr>
			<td>Contact No:</td>
			<td><input type="text" name="txtContactNo"
				placeHolder="Enter Contact No" value="${employee.contactno}" /></td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="submit">Save</button>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>