
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Create Subscription Result</title>
</head>

<body>
<h1>Create Subscription Result</h1>
<hr/>

<!-- display the message -->
<c:import url="/jsp/include/show-message.jsp"/>

<!-- display the just created model, if any and no errors -->
<c:if test='${not empty newSubscription && !message.error}'>
    <ul>
        <li>type: <c:out value="${newSubscription.type}"/></li>
        <li>daily unlocks: <c:out value="${newSubscription.dailyUnlocks}"/></li>
        <li>validity per day: <c:out value="${newSubscription.validityPerDay}"/></li>
        <li>fixed price: <c:out value="${newSubscription.fixedPrice}"/></li>
    </ul>
</c:if>

<a href="${pageContext.request.contextPath}/jsp/homepage.jsp">go back to the homepage</a>
</body>
</html>
