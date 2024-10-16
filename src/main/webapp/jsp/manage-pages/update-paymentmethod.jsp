

<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Update Payment Method</title>
</head>

<body>
<h1>Update Payment Method</h1>
<hr/>

<!-- display the message -->
<c:import url="/jsp/include/show-message.jsp"/>

<!-- display the just updated payment method, if any and no errors -->
<c:if test='${not empty updatePaymentMethod && !message.error}'>
    <c:redirect url="/paymentmethod/list/"/>
    <ul>
        <li>type: <c:out value="${updatePaymantMethod.type}"/></li>
        <li>activation: <c:out value="${updatePaymentMethod.activation}"/></li>
    </ul>
</c:if>

<a href="${pageContext.request.contextPath}/jsp/homepage.jsp">go back to the homepage</a>
</body>
</html>
