

<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Create Administrator</title>
	</head>

  <body>
	<h1>Create Administrator</h1>
	
	<form method="POST" enctype="multipart/form-data" action="<c:url value="/create-administrator-photo"/>">
		<label for="id">Enter your ID:</label>
		<input id="id" name="id" type="text"/><br/>

		<label for="email">Enter your email:</label>
		<input id="email" name="email" type="text"/><br/><br/>

		<label for="password">Enter your password:</label>
		<input id="password" name="password" type="text"/><br/><br/>

		<label for="photoID">Photo:</label>
		<input id="photoID" name="photo" type="file" accept="image/png, image/jpeg, .jpg, .jpeg, .png"/><br/><br/>

		<button type="submit">Submit</button>
		<button type="reset">Reset</button>
	</form>

	<br/><br/>
	<input type=button onClick="location.href='../jsp/login-page.jsp'" value='Login'>
	<input type=button onClick="location.href='../html/homepage.html'" value='back to home page'>
  </body>
</html>
