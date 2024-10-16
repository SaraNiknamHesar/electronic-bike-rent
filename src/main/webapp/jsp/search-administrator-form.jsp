
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Search administrator</title>
	</head>

  <body>
	<h1>Search administrator </h1>
	
	<form method="POST" action="<c:url value="/search-administrator-by-email"/>">
		<label for="email">Email:</label>
		<input id="email" name="email" type="text"/><br/><br/>
		
		<button type="submit">Submit</button>
		<button type="reset">Clear</button>
	</form>

	<br/><br/>
	<input type=button onClick="location.href='../jsp/homepage.jsp'" value='back to home page'>
	</body>
</html>
