
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Create Scooterracks</title>
</head>

<body>
<h1>Create Scooterracks</h1>
<hr/>

<!-- display the message -->
<c:import url="/jsp/include/show-message.jsp"/>

<!-- display the just created model, if any and no errors -->
<c:if test='${not empty newScooterrack && !message.error}'>
  <ul>
    <li>id: <c:out value="${newScooterrack.id}"/></li>
    <li>total parking spots: <c:out value="${newScooterrack.totalParkingSpots}"/></li>
    <li>available parking spots: <c:out value="${newScooterrack.availableParkingSpots}"/></li>
    <li>postal code: <c:out value="${newScooterrack.postalCode}"/></li>
    <li>address: <c:out value="${newScooterrack.address}"/></li>
  </ul>
</c:if>

<a href="${pageContext.request.contextPath}/jsp/homepage.jsp">go back to the homepage</a>
</body>
</html>
