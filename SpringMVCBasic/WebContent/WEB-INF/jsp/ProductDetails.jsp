<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Details</title>
</head>
<body>

<c:forEach items="${productdetails}" var="product">
<table>
      <tr>
        <td><img src="${product.getImageurl()}" style = "max-height:200px; max-width:350px; vertical-align:top;"/></td>
         <td><h2 style =" padding:0; line-height:1.4; margin-right:5px;"><c:out value="${product.getProdName()}"/></h2></td>
         
         <td><div style = "background-color:#388e3c; line-height:normal; color: #fff; padding:2px 4px 2px 4px; border-radius:3px"><span><c:out value="${product.getAvg_rating()}"/></span></div></td>
         <td> <a href ="/SpringMVCBasic/rating/${product.getProductid()}">View & Write Review</a>
      </tr>
      </table>
    </c:forEach>

</body>
</html>