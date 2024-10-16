
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Create Model Form</title>
	</head>

  <body>
	<h1>Create Model Form</h1>
	
	<form method="POST" action="<c:url value="/model/insert/"/>">
		<label for="nameID">name:</label>
		<input id="nameID" name="name" type="text"/><br/>
		
		<label for="brandID">brand:</label>
		<input id="brandID" name="brand" type="text"/><br/>
		
		<label for="batteryLifeID">battery life:</label>
		<input id="batteryLifeID" name="battery_life" type="text"/><br/>
		
		<label for="pricePerMinID">price per min:</label>
		<input id="pricePerMinID" name="price_per_min" type="text"/><br/><br/>

		<button type="submit">Submit</button><br/>
		<button type="reset">Reset the form</button>
	</form>
	</body>
</html>
