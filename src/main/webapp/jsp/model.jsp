
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>get models</title>
	</head>

	<body>
		<h1>get models</h1>
		<hr/>

		<!-- display the message -->
		<c:import url="/jsp/include/show-message.jsp"/>

		<!-- display the list of found model, if any -->
		<c:if test='${not empty modelList}'>
			<table>
				<thead>
					<tr>
						<th>name</th><th>brand</th><th>battery life</th><th>weight</th><th>height</th><th>length</th><th>depth</th><th>rate p/min</th><th>rate p/model</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="model" items="${modelList}">
						<tr>

							<td><c:out value="${model.name}"/></td>
							<td><c:out value="${model.brand}"/></td>
							<td><c:out value="${model.batteryLife}"/></td>
							<td><c:out value="${model.weight}"/></td>
							<td><c:out value="${model.height}"/></td>
                            <td><c:out value="${model.length}"/></td>
                            <td><c:out value="${model.depth}"/></td>
                            <td><c:out value="${model.ratePerMin}"/></td>
                            <td><c:out value="${model.ratePerModel}"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</body>
</html>
