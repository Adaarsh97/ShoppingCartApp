<%@page import="com.jsp.shoppingcart_application.dto.Merchant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	${msg}
	<%
	Merchant m = (Merchant) session.getAttribute("merchantInfo");
	if (m != null) {
	%>
	<h1>
		<a href="addproduct">Add Product</a>
	</h1>
	<h1>
		<a href="viewallproducts">View All Products</a>
	</h1>
	<%
	} else {
	%>
	<a href="merchantLoginForm.jsp">Login first</a>
	<%
	}
	%>
</body>
</html>