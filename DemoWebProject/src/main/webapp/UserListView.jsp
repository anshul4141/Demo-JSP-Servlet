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

	<h1 align="center">User List</h1>
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

	<form action="UserListCtl.do" method="post">
		<div align="center">
			<table border="1px" width="100%">

				<tr>
					<th>Select</th>
					<th>S.No</th>
					<th>FirstName</th>
					<th>LastName</th>
					<th>Login</th>
					<th>Dob</th>
					<th>Edit</th>
				</tr>

				<%
					Iterator it = list.iterator();
					int index = 1;
					while (it.hasNext()) {
						UserBean bean = (UserBean) it.next();
				%>
				<tr align="center">
					<td><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
					<td><%=index++%></td>
					<td><%=bean.getFirstName()%></td>
					<td><%=bean.getLastName()%></td>
					<td><%=bean.getLogin()%></td>
					<td><%=bean.getDob()%></td>
					<td><a href="UserCtl.do?id=<%=bean.getId()%>">Edit</a></td>
				</tr>
				<%
					}
				%>

			</table>
		</div>
		<table width="100%">
			<tr>
				<td align="center"><input type="submit" name="operation"
					value="delete"></td>
			</tr>
		</table>
	</form>


</body>
</html>