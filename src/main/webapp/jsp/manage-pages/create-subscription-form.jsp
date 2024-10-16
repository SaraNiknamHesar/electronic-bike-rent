
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create Subscription Form</title>
</head>

<body>
<h1>Create Subscription Form</h1>

<form method="POST" action="<c:url value="/subscription/insert/"/>">

    <label for="typeID">type:</label>
    <input id="typeID" name="type" type="text"/><br/>

    <label for="dailyUnlocksID">daily unlocks:</label>
    <input id="dailyUnlocksID" name="daily_unlocks" type="text"/><br/>

    <label for="validityPerDayID">validity per day:</label>
    <input id="validityPerDayID" name="validity_per_day" type="text"/><br/>

    <label for="fixedPriceID">fixed price:</label>
    <input id="fixedPriceID" name="fixed_price" type="text"/><br/><br/>

    <button type="submit">Submit</button><br/>
    <button type="reset">Reset the form</button>
</form>
</body>
</html>
