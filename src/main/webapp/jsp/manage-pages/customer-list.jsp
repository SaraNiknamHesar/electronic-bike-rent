<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../head.jsp"/>
    <title>Wascoot Customer Page</title>

</head>
<body>
<!--*******************
    Preloader start
********************-->
<jsp:include page="../preloader.jsp"/>
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
    <jsp:include page="../header-menu.jsp"/>
    <!--**********************************
        Content body start
    ***********************************-->
    <div class="content-body">
        <!-- row -->
        <div class="page-titles">
            <ol class="breadcrumb">
                <li><h5 class="bc-title">Customers</h5></li>
                <li class="breadcrumb-item"><a href="<c:url value="/dashboard"/>">
                    <svg width="17" height="17" viewBox="0 0 17 17" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M2.125 6.375L8.5 1.41667L14.875 6.375V14.1667C14.875 14.5424 14.7257 14.9027 14.4601 15.1684C14.1944 15.4341 13.8341 15.5833 13.4583 15.5833H3.54167C3.16594 15.5833 2.80561 15.4341 2.53993 15.1684C2.27426 14.9027 2.125 14.5424 2.125 14.1667V6.375Z" stroke="#2C2C2C" stroke-linecap="round" stroke-linejoin="round"/>
                        <path d="M6.375 15.5833V8.5H10.625V15.5833" stroke="#2C2C2C" stroke-linecap="round" stroke-linejoin="round"/>
                    </svg>
                    Home </a>
                </li>
                <li class="breadcrumb-item active"><a href="javascript:void(0)">Customers Page</a></li>
            </ol>
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-xl-12">
                    <div class="card">
                        <div class="card-body p-0">
                            <div class="table-responsive customer-rows">
                                <div class="tbl-caption">
                                    <h4 class="heading mb-0">Customers</h4>
                                </div>
                                <%--<c:import url="/jsp/include/show-message.jsp"/>--%>
                                <c:if test='${not empty customerList}'>
                                <table id="" class="table">
                                    <thead>
                                    <tr>
                                        <th>CF</th>
                                        <th>Customer Name</th>
                                        <th>Customer Surname</th>
                                        <th>Email Address</th>
                                        <th>Gender</th>
                                        <th>Birth Date</th>
                                        <th>Postal Code</th>
                                        <th>Payment Method</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="customer" items="${customerList}">
                                        <tr>
                                            <td><span><c:out value="${customer.CF}"/></span></td>
                                            <td><span><c:out value="${customer.name}"/></span></td>
                                            <td><span><c:out value="${customer.surname}"/></span></td>
                                            <td><span class="text-primary"><c:out value="${customer.email}"/></span></td>
                                            <td><span><c:out value="${customer.sex}"/></span></td>
                                            <td><span><c:out value="${customer.birthdate}"/></span></td>
                                            <td><span class = "postalCode"><c:out value="${customer.postalCode}"/></span></td>
                                            <td><span class="badge badge-success light border-0 paymentMethod"><c:out value="${customer.paymentType}"/></span></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>

                                </table>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>

<%--<!-- display the message -->
<c:import url="/jsp/include/show-message.jsp"/>

<!-- display the list of found customer, if any -->
<c:if test='${not empty customerList}'>
    <table>
        <thead>
        <tr>
            <th>CF</th><th>name</th><th>surname</th><th>email</th><th>sex</th><th>birthdate</th><th>postal code</th><th>payment type</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="customer" items="${customerList}">
            <tr>
                <td><c:out value="${customer.CF}"/></td>
                <td><c:out value="${customer.name}"/></td>
                <td><c:out value="${customer.surname}"/></td>
                <td><c:out value="${customer.email}"/></td>
                <td><c:out value="${customer.sex}"/></td>
                <td><c:out value="${customer.birthdate}"/></td>
                <td><c:out value="${customer.postalCode}"/></td>
                <td><c:out value="${customer.paymentType}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>--%>

<%--<a href="${pageContext.request.contextPath}/jsp/homepage.jsp">go back to the homepage</a>--%>
</div>

<!--**********************************
    Content body end
***********************************-->

<!--**********************************
    Footer start
***********************************-->
<jsp:include page="../footer.jsp"/>
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
<!-- Required -->


<!--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>-->
<jsp:include page="../foot.jsp"/>
<script src="js/managePages.js"></script>

</body>
</html>