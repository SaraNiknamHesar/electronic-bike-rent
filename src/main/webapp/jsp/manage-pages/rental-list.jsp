<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width">
    <title>Wascoot Customer Page</title>
    <!-- FAVICONS ICON -->
    <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/media/logo-pre.png">
    <link href="https://fonts.googleapis.com/css2?family=Material+Icons" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/metismenu/dist/metisMenu.min.css">

</head>
<body>
<!--*******************
    Preloader start
********************-->
<div id="preloader">
    <div class="lds-ripple">
        <div></div>
        <div></div>
    </div>
</div>
<!--*******************
Preloader end
********************-->
<!--**********************************
    Main wrapper start
***********************************-->
<div id="main-wrapper">
    <!--**********************************
        Nav header start
    ***********************************-->
    <div class="nav-header">
        <a href="${pageContext.request.contextPath}/jsp/homepage.jsp" class="brand-logo"><img class="logo-abbr" width="40" height="40" viewBox="0 0 50 50" src="${pageContext.request.contextPath}/media/logo/logo-orange-white.png"><span class="brand-title" style="font-size: medium;color: white;">WASCOOT</span></a>
    </div>
    <!--**********************************
        Nav header end
    ***********************************-->

    <!--**********************************
        Header start
    ***********************************-->
    <div class="header">
        <div class="header-content">
            <nav class="navbar navbar-expand">
                <div class="collapse navbar-collapse justify-content-between">
                    <div class="header-left">

                    </div>
                    <ul class="navbar-nav header-right">
                        <li class="nav-item align-items-center header-border"><a href="page-login.html" class="btn btn-primary btn-sm">Logout</a></li>
                        <li class="nav-item ps-3">
                            <a class="nav-link" href="javascript:void(0);" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <div class="header-info2 d-flex align-items-center">
                                    <div class="header-media">
                                        <img src="${pageContext.request.contextPath}/media/avatar/admin_girl.png" alt="">
                                    </div>
                                    <div class="header-info">
                                        <h6>WASCOOT Admin</h6>
                                        <p>admin@wascoot.com</p>
                                    </div>

                                </div>
                            </a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </div>
    <!--**********************************
        Header end
    ***********************************-->

    <!--**********************************
        Sidebar start
    ***********************************-->
    <div class="wasnav">
        <div class="wasnav-scroll">
            <ul class="metismenu" id="menu">
                <li class="menu-title">Sidebar Menu</li>
                <li><a href="${pageContext.request.contextPath}/dashboard" class=""  aria-expanded="false">
                    <div class="menu-icon">
                        <svg width="20" height="20" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path d="M2.5 7.49999L10 1.66666L17.5 7.49999V16.6667C17.5 17.1087 17.3244 17.5326 17.0118 17.8452C16.6993 18.1577 16.2754 18.3333 15.8333 18.3333H4.16667C3.72464 18.3333 3.30072 18.1577 2.98816 17.8452C2.67559 17.5326 2.5 17.1087 2.5 16.6667V7.49999Z" stroke="#888888" stroke-linecap="round" stroke-linejoin="round"/>
                            <path d="M7.5 18.3333V10H12.5V18.3333" stroke="#888888" stroke-linecap="round" stroke-linejoin="round"/>
                        </svg>
                    </div>
                    <span class="nav-text">Dashboard</span>
                </a>
                </li>
                <li><a href="<c:url value="/list-customer"/>" class="" aria-expanded="false">
                    <div class="menu-icon">
                        <svg width="22" height="22" viewBox="0 0 22 22" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" clip-rule="evenodd" d="M8.79222 13.9396C12.1738 13.9396 15.0641 14.452 15.0641 16.4989C15.0641 18.5458 12.1931 19.0729 8.79222 19.0729C5.40972 19.0729 2.52039 18.5651 2.52039 16.5172C2.52039 14.4694 5.39047 13.9396 8.79222 13.9396Z" stroke="#888888" stroke-linecap="round" stroke-linejoin="round"/>
                            <path fill-rule="evenodd" clip-rule="evenodd" d="M8.79223 11.0182C6.57206 11.0182 4.77173 9.21874 4.77173 6.99857C4.77173 4.7784 6.57206 2.97898 8.79223 2.97898C11.0115 2.97898 12.8118 4.7784 12.8118 6.99857C12.8201 9.21049 11.0326 11.0099 8.82064 11.0182H8.79223Z" stroke="#888888" stroke-linecap="round" stroke-linejoin="round"/>
                            <path d="M15.1095 9.9748C16.5771 9.76855 17.7073 8.50905 17.7101 6.98464C17.7101 5.48222 16.6147 4.23555 15.1782 3.99997" stroke="#888888" stroke-linecap="round" stroke-linejoin="round"/>
                            <path d="M17.0458 13.5045C18.4675 13.7163 19.4603 14.2149 19.4603 15.2416C19.4603 15.9483 18.9928 16.4067 18.2374 16.6936" stroke="#888888" stroke-linecap="round" stroke-linejoin="round"/>
                        </svg>
                    </div>
                    <span class="nav-text">Customers</span>
                </a>
                </li>
                <li><a href="<c:url value="/scooter/list/"/>" class="" aria-expanded="false">
                    <div class="menu-icon">
                        <svg width="22" height="22" fill="none" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                            <path d="M7.82,16H15v-1c0-2.21,1.79-4,4-4h0.74l-1.9-8.44C17.63,1.65,16.82,1,15.89,1H12v2h3.89l1.4,6.25c0,0-0.01,0-0.01,0 c-2.16,0.65-3.81,2.48-4.19,4.75H7.82c-0.48-1.34-1.86-2.24-3.42-1.94c-1.18,0.23-2.13,1.2-2.35,2.38C1.7,16.34,3.16,18,5,18 C6.3,18,7.4,17.16,7.82,16z M5,16c-0.55,0-1-0.45-1-1s0.45-1,1-1s1,0.45,1,1S5.55,16,5,16z" stroke="#888888" stroke-linecap="round" stroke-linejoin="round"/>
                            <path d="M19,12c-1.66,0-3,1.34-3,3s1.34,3,3,3s3-1.34,3-3S20.66,12,19,12z M19,16c-0.55,0-1-0.45-1-1s0.45-1,1-1s1,0.45,1,1 S19.55,16,19,16z" stroke="#888888" stroke-linecap="round" stroke-linejoin="round"/>
                            <polygon points="11,20 7,20 13,23 13,21 17,21 11,18"/>
                        </svg>
                    </div>
                    <span class="nav-text">Scooters</span>
                </a>

                </li>

                <li><a href="<c:url value="/model/list/"/>" class="" aria-expanded="false">
                    <div class="menu-icon">
                        <svg width="22" height="22" fill="none" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 22 22">
                            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="none" class="bi bi-collection" viewBox="0 0 16 16">
                                <path d="M2.5 3.5a.5.5 0 0 1 0-1h11a.5.5 0 0 1 0 1h-11zm2-2a.5.5 0 0 1 0-1h7a.5.5 0 0 1 0 1h-7zM0 13a1.5 1.5 0 0 0 1.5 1.5h13A1.5 1.5 0 0 0 16 13V6a1.5 1.5 0 0 0-1.5-1.5h-13A1.5 1.5 0 0 0 0 6v7zm1.5.5A.5.5 0 0 1 1 13V6a.5.5 0 0 1 .5-.5h13a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-.5.5h-13z" fill="#888888" stroke-linecap="round" stroke-linejoin="round"/>
                            </svg>
                        </svg>
                    </div>
                    <span class="nav-text">Models</span>
                </a>

                </li>

                <li><a href="<c:url value="/paymentmethod/list/"/>" class="" aria-expanded="false">
                    <div class="menu-icon">
                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="none" class="bi bi-credit-card" viewBox="0 0 16 16">
                            <path d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V4zm2-1a1 1 0 0 0-1 1v1h14V4a1 1 0 0 0-1-1H2zm13 4H1v5a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V7z" fill="#888888"/>
                            <path d="M2 10a1 1 0 0 1 1-1h1a1 1 0 0 1 1 1v1a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1v-1z" fill="#888888"/>
                        </svg>
                    </div>
                    <span class="nav-text">Payment Methods</span>
                </a>
                </li>

                <li><a href="<c:url value="/subscription/list/"/>" class="" aria-expanded="false">
                    <div class="menu-icon">
                        <svg width="22" height="22" viewBox="0 0 22 22" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path d="M10.5346 2.55658H7.1072C4.28845 2.55658 2.52112 4.55216 2.52112 7.37733V14.9985C2.52112 17.8237 4.2802 19.8192 7.1072 19.8192H15.1959C18.0238 19.8192 19.7829 17.8237 19.7829 14.9985V11.3062" stroke="#888888" stroke-linecap="round" stroke-linejoin="round"/>
                            <path fill-rule="evenodd" clip-rule="evenodd" d="M8.09214 10.0108L14.9424 3.16057C15.7958 2.30807 17.1791 2.30807 18.0325 3.16057L19.1481 4.27615C20.0015 5.12957 20.0015 6.51374 19.1481 7.36624L12.2648 14.2495C11.8917 14.6226 11.3857 14.8325 10.8577 14.8325H7.42389L7.51006 11.3675C7.52289 10.8578 7.73097 10.372 8.09214 10.0108Z" stroke="#888888" stroke-linecap="round" stroke-linejoin="round"/>
                            <path d="M13.9014 4.21895L18.0869 8.40445" stroke="#888888" stroke-linecap="round" stroke-linejoin="round"/>
                        </svg>
                    </div>
                    <span class="nav-text">Subscriptions</span>
                </a>
                </li>

                <li><a href="<c:url value="/rentals"/>" class="" aria-expanded="false">
                    <div class="menu-icon">
                        <svg width="20" height="20" viewBox="0 0 15 15" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" clip-rule="evenodd" d="M5.49998 0.5C5.49998 0.223858 5.72383 0 5.99998 0H7.49998H8.99998C9.27612 0 9.49998 0.223858 9.49998 0.5C9.49998 0.776142 9.27612 1 8.99998 1H7.99998V2.11922C9.09832 2.20409 10.119 2.56622 10.992 3.13572C11.0116 3.10851 11.0336 3.08252 11.058 3.05806L12.058 2.05806C12.3021 1.81398 12.6978 1.81398 12.9419 2.05806C13.186 2.30214 13.186 2.69786 12.9419 2.94194L11.967 3.91682C13.1595 5.07925 13.9 6.70314 13.9 8.49998C13.9 12.0346 11.0346 14.9 7.49998 14.9C3.96535 14.9 1.09998 12.0346 1.09998 8.49998C1.09998 5.13361 3.69904 2.3743 6.99998 2.11922V1H5.99998C5.72383 1 5.49998 0.776142 5.49998 0.5ZM2.09998 8.49998C2.09998 5.51764 4.51764 3.09998 7.49998 3.09998C10.4823 3.09998 12.9 5.51764 12.9 8.49998C12.9 11.4823 10.4823 13.9 7.49998 13.9C4.51764 13.9 2.09998 11.4823 2.09998 8.49998ZM7.49998 8.49998V4.09998C5.06992 4.09998 3.09998 6.06992 3.09998 8.49998C3.09998 10.93 5.06992 12.9 7.49998 12.9C8.715 12.9 9.815 12.4075 10.6112 11.6112L7.49998 8.49998Z" fill="#888888"   />
                        </svg>
                    </div>
                    <span class="nav-text">Rental</span>
                </a>
                </li>

                <li><a href="<c:url value="/scooterrack/list/"/>" class="" aria-expanded="false">
                    <div class="menu-icon">
                        <svg width="24" height="24" stroke-width="1.5" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path d="M4 16.01L4.01 15.9989" stroke="#888888" stroke-linecap="round" stroke-linejoin="round"/>
                            <path d="M4 20.01L4.01 19.9989" stroke="#888888" stroke-linecap="round" stroke-linejoin="round"/>
                            <path d="M4 8.01L4.01 7.99889" stroke="#888888" stroke-linecap="round" stroke-linejoin="round"/>
                            <path d="M4 4.01L4.01 3.99889" stroke="#888888" stroke-linecap="round" stroke-linejoin="round"/>
                            <path d="M4 12.01L4.01 11.9989" stroke="#888888" stroke-linecap="round" stroke-linejoin="round"/>
                            <path d="M8 20.01L8.01 19.9989" stroke="#888888" stroke-linecap="round" stroke-linejoin="round"/>
                            <path d="M12 20.01L12.01 19.9989" stroke="#888888" stroke-linecap="round" stroke-linejoin="round"/>
                            <path d="M16 20.01L16.01 19.9989" stroke="#888888" stroke-linecap="round" stroke-linejoin="round"/>
                            <path d="M20 20.01L20.01 19.9989" stroke="#888888" stroke-linecap="round" stroke-linejoin="round"/>
                            <path d="M20 16.01L20.01 15.9989" stroke="#888888" stroke-linecap="round" stroke-linejoin="round"/>
                            <path d="M20 12.01L20.01 11.9989" stroke="#888888" stroke-linecap="round" stroke-linejoin="round"/>
                            <path d="M20 8.01L20.01 7.99889" stroke="#888888" stroke-linecap="round" stroke-linejoin="round"/>
                            <path d="M20 4.01L20.01 3.99889" stroke="#888888" stroke-linecap="round" stroke-linejoin="round"/>
                            <path d="M16 4.01L16.01 3.99889" stroke="#888888" stroke-linecap="round" stroke-linejoin="round"/>
                            <path d="M12 4.01L12.01 3.99889" stroke="#888888" stroke-linecap="round" stroke-linejoin="round"/>
                            <path d="M8 4.01L8.01 3.99889" stroke="#888888" stroke-linecap="round" stroke-linejoin="round"/>
                            <path d="M8 16V8H16V16H8Z" stroke="#888888" stroke-linecap="round" stroke-linejoin="round"/>
                        </svg>
                    </div>
                    <span class="nav-text">Scooter Racks</span>
                </a>
                </li>
            </ul>
        </div>
    </div>

    <!--**********************************
        Sidebar end
    ***********************************-->

    <!--**********************************
        Content body start
    ***********************************-->
    <div class="content-body">
        <!-- row -->
        <div class="page-titles">
            <ol class="breadcrumb">
                <li><h5 class="bc-title">Rentals</h5></li>
                <li class="breadcrumb-item"><a href="javascript:void(0)">
                    <svg width="17" height="17" viewBox="0 0 17 17" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M2.125 6.375L8.5 1.41667L14.875 6.375V14.1667C14.875 14.5424 14.7257 14.9027 14.4601 15.1684C14.1944 15.4341 13.8341 15.5833 13.4583 15.5833H3.54167C3.16594 15.5833 2.80561 15.4341 2.53993 15.1684C2.27426 14.9027 2.125 14.5424 2.125 14.1667V6.375Z" stroke="#2C2C2C" stroke-linecap="round" stroke-linejoin="round"/>
                        <path d="M6.375 15.5833V8.5H10.625V15.5833" stroke="#2C2C2C" stroke-linecap="round" stroke-linejoin="round"/>
                    </svg>
                    Home </a>
                </li>
                <li class="breadcrumb-item active"><a href="javascript:void(0)">Rental Page</a></li>
            </ol>
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-xl-12">
                    <div class="card">
                        <div class="card-body p-0">
                            <div class="table-responsive customer-rows">
                                <div class="tbl-caption">
                                    <h4 class="heading mb-0">Rentals</h4>
                                </div>
                                <%--<c:import url="/jsp/include/show-message.jsp"/>--%>
                                <c:if test='${not empty rentalsList}'>
                                    <table id="" class="table">
                                        <thead>
                                        <tr>
                                            <th>id</th>
                                            <th>date hour delivery</th>
                                            <th>date hour collection</th>
                                            <th>id scooter</th>
                                            <th>scooterrack delivery</th>
                                            <th>scooterrack collection</th>
                                            <th>customer</th>
                                            <th>km traveled</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="rental" items="${rentalsList}">
                                            <tr>

                                                <td><span><c:out value="${rental.id}"/></span></td>
                                                <td><span class="rental-delivery"><c:out value="${rental.dateHourDelivery}"/></span></td>
                                                <td><span><c:out value="${rental.dateHourCollection}"/></span></td>
                                                <td><span><c:out value="${rental.idScooter}"/></span></td>
                                                <td><span><c:out value="${rental.scooterrackDelivery}"/></span></td>
                                                <td><span><c:out value="${rental.scooterrackCollection}"/></span></td>
                                                <td><span><c:out value="${rental.customer}"/></span></td>
                                                <td><span><c:out value="${rental.kmTraveled}"/></span></td>
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


    <%--<a href="${pageContext.request.contextPath}/jsp/homepage.jsp">go back to the homepage</a>--%>
</div>

<!--**********************************
    Content body end
***********************************-->

<!--**********************************
    Footer start
***********************************-->
<div class="footer">
    <div class="copyright">
        <p>Developed by <a href="${pageContext.request.contextPath}/jsp/homepage.jsp" target="_blank">WASCOOT</a> 2023</p>
    </div>
</div>
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
<script src="js/jquery-3.7.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery"></script>
<script src="https://cdn.jsdelivr.net/npm/metismenu"></script>
<script src="https://unpkg.com/@popperjs/core@2"></script>
<script src="js/wascoot.js"></script>
<script src="js/managePages.js"></script>

</body>
</html>
