
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Create Scooterrack Form</title>
</head>

<body>
<h1>Create Scooterrack Form</h1>

<form method="POST" action="<c:url value="/scooterrack/insert/"/>">
  <label for="totalParkingSpotsID">total parking spots:</label>
  <input id="totalParkingSpotsID" name="total_parking_spots" type="text"/><br/>

  <label for="availableParkingSpotsID">available parking spots:</label>
  <input id="availableParkingSpotsID" name="available_parking_spots" type="text"/><br/>

  <label for="postalCodeID">postal code:</label>
  <input id="postalCodeID" name="postalcode" type="text"/><br/><br/>

  <label for="addressID">address:</label>
  <input id="addressID" name="address" type="text"/><br/><br/>

  <button type="submit">Create</button><br/>
  <button type="reset">Reset the form</button>
</form>
</body>
</html>
