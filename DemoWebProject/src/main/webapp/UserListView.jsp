<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
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
		<h1>User List</h1>
		<%
			List list = (List) request.getAttribute("list");
			String msg = (String) request.getAttribute("msg");
		%>

		<%
			if (msg != null) {
		%>
		<h3 align="center"><%=msg%></h3>
		<%
			}
		%>

		<form action="UserListCtl" method="post">

			<table border="1px" width="100%">

				<tr>
					<th>Select</th>
					<th>Id</th>
					<th>FirstName</th>
					<th>LastName</th>
					<th>Login</th>
					<th>Dob</th>
				</tr>

				<%
					Iterator it = list.iterator();
					while (it.hasNext()) {
						UserBean bean = (UserBean) it.next();
				%>
				<tr align="center">
					<td><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
					<td><%=bean.getId()%></td>
					<td><%=bean.getFirstName()%></td>
					<td><%=bean.getLastName()%></td>
					<td><%=bean.getLogin()%></td>
					<td><%=bean.getDob()%></td>
				</tr>
				<%
					}
				%>

			</table>

			<table align="center">
				<input type="submit" name="operation" value="delete">
			</table>
		</form>
	</div>
</body>
</html>