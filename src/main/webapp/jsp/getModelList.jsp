
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Get models Form</title>
	</head>

  <body>
	<h1>Get models Form</h1>

	<form method="GET" action="<c:url value="/models"/>">

		<button type="submit">Submit</button><br/>
		<button type="reset">Reset the form</button>
	</form>
	</body>
</html>
