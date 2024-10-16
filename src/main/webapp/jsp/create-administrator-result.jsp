

<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>Create Administrator</title>
</head>

<body>
<h1>Create Administrator</h1>
<hr/>

<!-- display the message -->
<c:import url="/jsp/include/show-message.jsp"/>

<!-- display the just created admin, if any and no errors -->
<c:if test="${not empty administrator && !message.error}">
	<ul>
		<li>id: <c:out value="${administrator.id}"/></li>
		<li>email: <c:out value="${administrator.email}"/></li>
		<li>password: <c:out value="${administrator.password}"/></li>


		<c:choose>
			<c:when test="${administrator.hasPhoto()}">

				<li>photo:
					<ul>
						<li>MIME media type: <c:out value="${administrator.photoMediaType}"/> </li>
						<li>size: <c:out value="${administrator.photoSize}"/> </li>
						<li>image: <br/>
							<img src="<c:url value="/load-administrator-photo"><c:param name="id" value="${administrator.id}"/></c:url>"/>
						</li>
					</ul>
				</li>

			</c:when>

			<c:otherwise>
				<li>photo: not available</li>
			</c:otherwise>
		</c:choose>

	</ul>
</c:if>

<input type=button onClick="location.href='./jsp/login-page.jsp'" value='Login'>
<input type=button onClick="location.href='./html/homepage.html'" value='back to home page'>
</body>
</html>
