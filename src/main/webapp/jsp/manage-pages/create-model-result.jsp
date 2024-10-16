
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Create Models</title>
	</head>

	<body>
		<h1>Create Models</h1>
		<hr/>
		
		<!-- display the message -->
		<c:import url="/jsp/include/show-message.jsp"/>

		<!-- display the just created model, if any and no errors -->
		<c:if test='${not empty newModel && !message.error}'>
			<ul>
				<li>name: <c:out value="${newModel.name}"/></li>
				<li>brand: <c:out value="${newModel.brand}"/></li>
				<li>battery life: <c:out value="${newModel.batteryLife}"/></li>
				<li>price per min: <c:out value="${newModel.pricePerMin}"/></li>
			</ul>
		</c:if>

		<a href="${pageContext.request.contextPath}/jsp/homepage.jsp">go back to the homepage</a>
	</body>
</html>
