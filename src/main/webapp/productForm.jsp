<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <form:form action = "saveproduct" modelAttribute="productobj">
    Enter Brand:<form:input path="brand" /> <br>
    Enter Category: <form:input path="category" /> <br>
    Enter Price: <form:input path="price" /> <br>
    Enter Stock: <form:input path="stock"/>  <br>
    <input type="submit">
      </form:form>
</body>
</html>