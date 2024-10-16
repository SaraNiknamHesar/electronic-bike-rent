
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Create Scooter Form</title>
</head>

<body>
<h1>Create Scooter Form</h1>
<form method="POST" action="<c:url value="/scooter/insert/"/>">
  <label for="dateOfPurchaseID">date of purchase:</label>
  <input id="dateOfPurchaseID" name="date_of_purchase" type="text"/><br/>

  <label for="kmTraveledID">km traveled:</label>
  <input id="kmTraveledID" name="km_traveled" type="text"/><br/>

  <label for="modelID">model:</label>
  <input id="modelID" name="model" type="text"/><br/><br/>

  <label for="remainingBatteryLifeID">remaining battery life:</label>
  <input id="remainingBatteryLifeID" name="remaining_battery_life" type="text"/><br/><br/>

  <label for="idScooterrackID">id scooterrack:</label>
  <input id="idScooterrackID" name="id_scooterrack" type="text"/><br/><br/>

  <button type="submit">Create</button><br/>
  <button type="reset">Reset the form</button>
</form>

</body>
</html>
