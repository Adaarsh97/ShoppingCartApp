<%@page import="com.jsp.shoppingcart_application.dto.Item"%>
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
	List<Item> items = (List<Item>) request.getAttribute("itemslist");
	double totalPrice = (Double) request.getAttribute("totalprice");
	%>

	<table cellpadding="10px" border="1" cellspacing="0">
		<th>Brand</th>
		<th>Category</th>
		<th>Quantity</th>
		<th>Price</th>
		<%
		for (Item item : items) {
		%>
		<tr>
			<td><%=item.getName()%></td>
			<td><%=item.getCategory()%></td>
			<td><%=item.getQuantity()%></td>
			<td><%=item.getPrice()%></td>
		</tr>
		<%
		}
		%>
	</table>
	<h1>
		totalPrice:
		<%=totalPrice%>
	</h1>
	<button>
		<a href="addorder">Buy Now</a>
	</button>

</body>
</html>