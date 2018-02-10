<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>Congratulations your form processed successfully!!</h1>

	<h2>${message}</h2>

	<table>

		<tr>
			<td>Student Name :</td>
			<td>${student.name}</td>
		</tr>
<%-- 
		<tr>
			<td>Student Hobby :</td>
			<td>${student1.studentHobby}</td>
		</tr> --%>

		<tr>
			<td>Student Mobile :</td>
			<td>${student.mobile}</td>
		</tr>
<%-- 
		<tr>
			<td>Student DOB :</td>
			<td>${student1.studentDOB}</td>
		</tr> 

		<tr>
			<td>Student Skills :</td>
			<td>${student1.studentSkills}</td>
		</tr> --%>

        <tr>
			<td>Email Address :</td>
			<td>${student.email}</td>
		</tr>

		<tr>
			<td>Student Address :</td>
			<td>${student.address.country}
				${student.address.city} ${student.address.street}
				${student.address.pincode}</td>
		</tr>
		
		<tr>
			<td><a href="login.html">Login</a></td>
			
		</tr>
	</table>

</body>
</html>