

<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Search administrator</title>
	</head>

	<body>
		<h1>Search administrator</h1>
		<hr/>
		
		<!-- display the message -->
		<c:import url="/jsp/include/show-message.jsp"/>

		<!-- display the list of found administrator, if any -->
		<c:if test='${not empty administratorList}'>
			<table>
				<thead>
					<tr>
						<th>Email</th><th>Password</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="administrator" items="${administratorList}">
						<tr>
							<td><c:out value="${administrator.email}"/></td>
							<td><c:out value="${administrator.password}"/></td>
							<td><c:out value="${administrator.name}"/></td>

						</tr>
						<li>image: <br/>
							<img src="<c:url value="/load-administrator-photo"><c:param name="id" value="${administrator.id}"/></c:url>"/>
						</li>

					</c:forEach>
				</tbody>
			</table>
		</c:if>



		<input type=button onClick="location.href='./jsp/homepage.jsp'" value='back to home page'>
	</body>
</html>
