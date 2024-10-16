
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Update Scooterrack</title>
</head>

<body>
<h1>Update Scooterrack</h1>
<hr/>

<!-- display the message -->
<c:import url="/jsp/include/show-message.jsp"/>

<!-- display the just created scooterrack, if any and no errors -->
<c:if test='${not empty updateScooterrack && !message.error}'>
  <ul>
    <li>name: <c:out value="${updateScooterrack.id}"/></li>
    <li>total parking spots: <c:out value="${updateScooterrack.totalParkingSpots}"/></li>
    <li>available parking spots: <c:out value="${updateScooterrack.availableParkingSpots}"/></li>
    <li>postal code: <c:out value="${updateScooterrack.postalCode}"/></li>
    <li>address: <c:out value="${updateScooterrack.address}"/></li>
  </ul>
</c:if>

<a href="${pageContext.request.contextPath}/jsp/homepage.jsp">go back to the homepage</a>
</body>
</html>
