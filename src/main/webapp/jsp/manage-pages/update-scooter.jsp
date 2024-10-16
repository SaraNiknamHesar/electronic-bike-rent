
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Update Scooter</title>
</head>

<body>
<h1>Update Scooter</h1>
<hr/>

<!-- display the message -->
<c:import url="/jsp/include/show-message.jsp"/>

<!-- display the just created scooter, if any and no errors -->
<c:if test='${not empty updateScooter && !message.error}'>
  <ul>
    <li>id: <c:out value="${updateScooter.id}"/></li>
    <li>date of purchase: <c:out value="${updateScooter.dateOfPurchase}"/></li>
    <li>km traveled: <c:out value="${updateScooter.kmTraveled}"/></li>
    <li>model: <c:out value="${updateScooter.model}"/></li>
    <li>remaining battery life: <c:out value="${updateScooter.remainingBatteryLife}"/></li>
    <li>id scooterrack: <c:out value="${updateScooter.idScooterrack}"/></li>
  </ul>
</c:if>

<a href="${pageContext.request.contextPath}/jsp/homepage.jsp">go back to the homepage</a>
</body>
</html>
