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
   <form:form action = "saveitem" modelAttribute="itemobj">
    Enter brand:  <form:input path="name" readonly = "true" /> <br>
    Enter category: <form:input path="category" readonly = "true"/> <br>
    Enter price:<form:input path="price" readonly = "true"/> <br>
    Enter pId:<form:input path="pId" readonly = "true"/> <br>
    Enter quantity:<form:input path="quantity"/> <br>
    <input type="submit">  
      </form:form>
</body>
</html>