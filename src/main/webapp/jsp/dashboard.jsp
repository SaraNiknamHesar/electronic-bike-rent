 <%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Dashboard</title>
		<jsp:include page="head.jsp"/>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/utils.js"></script>
        <script src="${pageContext.request.contextPath}/js/dashboard.js"></script>

        <!-- Charts -->
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">

        <!--- Map representation with Leaflet -->
        <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" integrity="sha256-p4NxAoJBhIIN+hmNHrzRCf9tD/miZyoHS5obTRR9BMY=" crossorigin="" />
        <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js" integrity="sha256-20nQCchB9co0qIjJZRGuk2/Z9VM+kNiyxNV1lvTlZBo=" crossorigin=""></script>

	</head>

	<body>

	<!--*******************
        Preloader start
    ********************-->
	<jsp:include page="preloader.jsp"/>
	<!--*******************
        Preloader end
    ********************-->

	<!--**********************************
        Main wrapper start
    ***********************************-->

	<div id="main-wrapper">
		<%--**********************************
            Header and Sidebar
       ***********************************--%>
		<jsp:include page="header-menu.jsp"/>

		<!--**********************************
            Content body start
        ***********************************-->
		<div class="content-body">
			<!-- row -->
			<div class="page-titles">
				<ol class="breadcrumb">
					<li><h5 class="bc-title">Home Page</h5></li>
					<li class="breadcrumb-item"><a href="<c:url value="/dashboard"/>">
						<svg width="17" height="17" viewBox="0 0 17 17" fill="none" xmlns="http://www.w3.org/2000/svg">
							<path d="M2.125 6.375L8.5 1.41667L14.875 6.375V14.1667C14.875 14.5424 14.7257 14.9027 14.4601 15.1684C14.1944 15.4341 13.8341 15.5833 13.4583 15.5833H3.54167C3.16594 15.5833 2.80561 15.4341 2.53993 15.1684C2.27426 14.9027 2.125 14.5424 2.125 14.1667V6.375Z" stroke="#2C2C2C" stroke-linecap="round" stroke-linejoin="round"/>
							<path d="M6.375 15.5833V8.5H10.625V15.5833" stroke="#2C2C2C" stroke-linecap="round" stroke-linejoin="round"/>
						</svg>
						Home </a>
					</li>
					<li class="breadcrumb-item active"><a href="javascript:void(0)">Dashboard</a></li>
				</ol>
			</div>
            <div class="container">
                <div id="top-elements">

                    <div class= "non-chart-container" id="map-container">
                        <h4 class="chart-text" >Scooterracks available information with geographic representation :
                            <button id="button-padova">Padova</button>
                            <button id="button-rome">Rome</button>
                        </h4>
                        <div id="map"></div>
                    </div>

                    <div class= "non-chart-container" id="topLocationTable">
                        <h4 class="chart-text" >The most visited places for rent a bike</h4>
                        <c:if test='${not empty topLocation}'>
                            <c:set var="count" value="0" scope="page" />
                        <table class="table">
                            <thead>
                            <tr>
                                <th>Rank</th>
                                <th>Id of the scooterrack</th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach var="topLocation" items="${topLocation}">
                                <c:set var="count" value="${count + 1}" scope="page"/>
                                <tr>
                                    <td><c:out value="${count}"/></td>
                                    <td><c:out value="${topLocation}"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        </c:if>
                    </div>

                    <div class="chart-container" id="age-chart-container">
                        <h4 class="chart-text">Age distribution by postal code</h4>
                        <canvas id="age-chart"></canvas>
                    </div>
                </div>

                <div id="bottom-elements" class="row">
                    <div class="chart-container" id="revenue-chart-container">
                        <h4 class="chart-text">The amount of bicycle rental income per month</h4>
                        <canvas id="revenue-chart"></canvas>
                    </div>

                    <div class="chart-container" id="gender-chart-container">
                        <h4 class="chart-text">Gender distribution among customers</h4>
                        <canvas id="gender-chart"></canvas>
                    </div>
                </div>

            </div>
		</div>
			<!--**********************************
                Content body end
            ***********************************-->

			<!--**********************************
                Footer start
            ***********************************-->
			<jsp:include page="footer.jsp"/>
			<!--**********************************
                Footer end
            ***********************************-->
	</div>

	<!--**********************************
        Main wrapper end
    ***********************************-->

	<!--**********************************
        Scripts
    ***********************************-->

	<jsp:include page="foot.jsp"/>

	</body>
</html>
