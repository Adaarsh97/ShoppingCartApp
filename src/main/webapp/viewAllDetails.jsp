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
<%
List<Product> products = (List<Product>) request.getAttribute("products");
%>
<body>
	<a href="displayitemfromcart">View cart</a>
	<table cellPadding="20px" border="1">
		<th>Brand</th>
		<th>Category</th>
		<th>Price</th>
		<th>Add</th>
		<%
		for (Product product : products) {
		%>
		<tr>
			<td><%=product.getBrand()%></td>
			<td><%=product.getCategory()%></td>
			<td><%=product.getPrice()%></td>
			<td><a href="additem?id=<%=product.getId()%>">Add to Cart</a></td>
		</tr>
		<%
		}
		%>
	</table>

</body>
</html>