<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


</head>
<body>

	<c:forEach items="${productRatings.getProdRevList()}" var="rating">
<table>
      <tr>
         <td><div style = "background-color:#388e3c; line-height:normal; color: #fff; padding:2px 4px 2px 4px; border-radius:3px"><span><c:out value="${rating.getRating()}"/></span></div></td>
         <td><h2 style =" padding:0; line-height:1.4; margin-right:5px;"><c:out value="${rating.getComment()}"/></h2></td>
         
      </tr>
      </table>
    </c:forEach>
	
	<form action="/SpringMVCBasic/submitratingsForm.html" method="post">
	
    <div class="stars">
        <input type="radio" name="star" class="star-1" id="star-1" onclick="javascript:writeRatingValue('child-star-1');"/>
        <label class="star-1" id="child-star-1" for="star-1">1</label>
        <input type="radio" name="star" class="star-2" id="star-2" onclick="javascript:writeRatingValue('child-star-2');"/>
        <label class="star-2" id="child-star-2" for="star-2">2</label>
        <input type="radio" name="star" class="star-3" id="star-3" onclick="javascript:writeRatingValue('child-star-3');"/>
        <label class="star-3" id="child-star-3" for="star-3">3</label>
        <input type="radio" name="star" class="star-4" id="star-4" onclick="javascript:writeRatingValue('child-star-4');"/>
        <label class="star-4" id="child-star-4" for="star-4">4</label>
        <input type="radio" name="star" class="star-5" id="star-5" onclick="javascript:writeRatingValue('child-star-5');"/>
        <label class="star-5" id="child-star-5" for="star-5">5</label>
        <span></span>
    </div>
    <input type="hidden" id="ratingId" name="rating" value=""/>
    <input type="hidden" name="productId" value="${productRatings.productId}"/>
	<c:set var="userIdFromCookie" value="${cookie['userId'].value}"/>
    <input type="hidden" name="userId" value="${userIdFromCookie}"/>
    <textarea rows="4" cols="50" name="comment">
Enter text here...</textarea>
<br>
<input type="submit" name="submit"/>
    
</form>
</body>
<script type="text/javascript">
function writeRatingValue(id)
{
var rating = document.getElementById(id).innerHTML;
document.getElementById("ratingId").value=rating;
}
</script>
</html>