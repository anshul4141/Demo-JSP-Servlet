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
		<form action="UserCtl.do" method="post">
			<%
				String successMsg = (String) request.getAttribute("successMsg");
				String errorMsg = (String) request.getAttribute("errorMsg");
				UserBean bean = (UserBean) request.getAttribute("bean");
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

			<%
				if (bean != null) {
			%>
			<h1>Update User</h1>
			<%
				} else {
			%>
			<h1>Add User</h1>
			<%
				}
			%>
			<input type="hidden" name="id"
				value="<%=bean != null ? bean.getId() : ""%>">
			<table>
				<tr>
					<th>First Name:</th>
					<td><input type="text" name="firstName"
						value="<%=bean != null ? bean.getFirstName() : ""%>"
						placeholder="enter your first name"></td>
				</tr>

				<tr>
					<th>Last Name:</th>
					<td><input type="text" name="lastName"
						value="<%=bean != null ? bean.getLastName() : ""%>"
						placeholder="enter you last name"></td>
				</tr>

				<tr>
					<th>Login:</th>
					<td><input type="email" name="login"
						value="<%=bean != null ? bean.getLogin() : ""%>"
						placeholder="enter your login"></td>
				</tr>

				<tr>
					<th>Password:</th>
					<td><input type="password" name="password"
						value="<%=bean != null ? bean.getPassword() : ""%>"
						placeholder="enter you password"></td>
				</tr>

				<tr>
					<th>Dob:</th>
					<td><input type="date" name="dob"
						value="<%=bean != null ? bean.getDob() : ""%>"></td>
				</tr>

				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=bean != null ? "update" : "save"%>"></td>
				</tr>

			</table>
		</form>
	</div>
</body>
</html>