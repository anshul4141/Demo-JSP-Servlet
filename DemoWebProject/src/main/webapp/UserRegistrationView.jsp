<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<div align="center">
		<form action="UserRegistrationCtl" method="post">

			<h1>User Registration</h1>

			<%
				String successMsg = (String) request.getAttribute("successMsg");
				String errorMsg = (String) request.getAttribute("errorMsg");
			%>

			<%
				if (successMsg != null) {
			%>
			<h3 style="color: green"><%=successMsg%></h3>
			<%
				}
			%>

			<%
				if (errorMsg != null) {
			%>
			<h3 style="color: red"><%=errorMsg%></h3>
			<%
				}
			%>

			<table>

				<tr>
					<th>First Name:</th>
					<td><input type="text" name="firstName" value=""
						placeholder="enter your first name"> <span
						style="color: red"><%=request.getAttribute("firstName") != null ? request.getAttribute("firstName") : ""%></span>
					</td>
				</tr>

				<tr>
					<th>Last Name:</th>
					<td><input type="text" name="lastName" value=""
						placeholder="enter you last name"> <span
						style="color: red"><%=request.getAttribute("lastName") != null ? request.getAttribute("lastName") : ""%></span>
					</td>
				</tr>

				<tr>
					<th>Login:</th>
					<td><input type="email" name="login" value=""
						placeholder="enter your login"> <span style="color: red"><%=request.getAttribute("login") != null ? request.getAttribute("login") : ""%></span>
					</td>
				</tr>

				<tr>
					<th>Password:</th>
					<td><input type="password" name="password" value=""
						placeholder="enter you password"> <span style="color: red"><%=request.getAttribute("password") != null ? request.getAttribute("password") : ""%></span>
					</td>
				</tr>

				<tr>
					<th>Dob:</th>
					<td><input type="date" name="dob" value=""> <span
						style="color: red"><%=request.getAttribute("dob") != null ? request.getAttribute("dob") : ""%></span>
					</td>
				</tr>

				<tr>
					<th></th>
					<td><input type="submit" name="operation" value="SingUp"></td>
				</tr>

			</table>
		</form>
	</div>
</body>
</html>