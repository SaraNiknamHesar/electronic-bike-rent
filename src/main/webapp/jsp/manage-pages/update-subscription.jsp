

<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Update Subscription</title>
</head>

<body>
<h1>Update Subscription</h1>
<hr/>

<!-- display the message -->
<c:import url="/jsp/include/show-message.jsp"/>

<!-- display the just created employee, if any and no errors -->
<c:if test='${not empty updateSubscription && !message.error}'>
    <ul>
        <li>id: <c:out value="${updateSubscription.id}"/></li>
        <li>type: <c:out value="${updateSubscription.type}"/></li>
        <li>daily unlocks: <c:out value="${updateSubscription.dailyUnlocks}"/></li>
        <li>validity per day: <c:out value="${updateSubscription.validityPerDay}"/></li>
        <li>fixed price: <c:out value="${updateSubscription.fixedPrice}"/></li>
    </ul>
</c:if>

<a href="${pageContext.request.contextPath}/jsp/homepage.jsp">go back to the homepage</a>
</body>
</html>
