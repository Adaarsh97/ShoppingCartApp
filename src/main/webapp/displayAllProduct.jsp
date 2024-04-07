<%@page import="com.jsp.shoppingcart_application.dto.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	List<Product> products = (List<Product>) request.getAttribute("productlist");
	%>
	<table cellPadding="20px" border="1">
		<th>Id</th>
		<th>Brand</th>
		<th>Category</th>
		<th>Price</th>
		<th>Stock</th>
		<th>Update</th>
		<th>Delete</th>

		<%
		for (Product p : products) {
		%>
		<tr>
			<td><%=p.getId()%></td>
			<td><%=p.getBrand()%></td>
			<td><%=p.getCategory()%></td>
			<td><%=p.getPrice()%></td>
			<td><%=p.getStock()%></td>
			<td><a href="updateproduct?id=<%=p.getId()%>">Update</a></td>
			<td><a href="deleteproduct?id=<%=p.getId()%>">delete</a></td>
		</tr>
		<%
		}
		%>
	</table>
</body>
</html>