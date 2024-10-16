<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width">
    <title>Wascoot Home Page</title>
    <!-- FAVICONS ICON -->
    <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/media/logo-pre.png">
    <link href="https://fonts.googleapis.com/css2?family=Material+Icons" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cssstyle.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/metismenu/dist/metisMenu.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">



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
        <a href="../dashboard" class="brand-logo"><img class="logo-abbr" width="40" height="40" viewBox="0 0 50 50" src="${pageContext.request.contextPath}/media/logo/logo-orange-white.png"><span class="brand-title" style="font-size: medium;color: white;">WASCOOT</span></a>
    </div>
    <!--**********************************
        Nav header end
    ***********************************-->

    <!--**********************************
        Header start
    ***********************************-->
    <jsp:include page="header-menu.jsp"/>

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
                <li><h5 class="bc-title">Admin page</h5></li>
                <li class="breadcrumb-item"><a href="<c:url value="/dashboard"/>">
                    <svg width="17" height="17" viewBox="0 0 17 17" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M2.125 6.375L8.5 1.41667L14.875 6.375V14.1667C14.875 14.5424 14.7257 14.9027 14.4601 15.1684C14.1944 15.4341 13.8341 15.5833 13.4583 15.5833H3.54167C3.16594 15.5833 2.80561 15.4341 2.53993 15.1684C2.27426 14.9027 2.125 14.5424 2.125 14.1667V6.375Z" stroke="#2C2C2C" stroke-linecap="round" stroke-linejoin="round"/>
                        <path d="M6.375 15.5833V8.5H10.625V15.5833" stroke="#2C2C2C" stroke-linecap="round" stroke-linejoin="round"/>
                    </svg>
                    Home </a>
                </li>
                <li class="breadcrumb-item active"><a href="javascript:void(0)">Admin Page</a></li>
            </ol>
        </div>

        <div class="container">
            <div>
                <h1>Administrators</h1>
                <div id="admin_container" class="admin_container"></div>
            </div>
        </div>



        <div class="container">
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <div class="searchbar">
                        <div id="form_cont">
                            <select
                                    class="form-select select"
                                    id="tv_select"
                                    aria-label="Default select example"
                            >
                                <option value="" selected>Select Administrator</option>
                                <!-- We will fill the options with javascript. The list is filtered according to the value of the text area above-->
                            </select>
                            <div class="buttonform">
                                <button
                                        type="button"
                                        id="confirm"
                                        class="select btn btn-primary btn-sm"
                                >
                                    Confirm
                                </button>
                                <button
                                        type="button"
                                        id="reset"
                                        class="select btn btn-danger btn-sm"
                                >
                                    Remove
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3"></div>
            </div>
            <div id="film_container" class="film_container">
            </div>

        </div>
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
<!--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.7.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery"></script>
<script src="https://cdn.jsdelivr.net/npm/metismenu"></script>
<script src="https://unpkg.com/@popperjs/core@2"></script>
<script src="${pageContext.request.contextPath}/js/wascoot.js"></script>
<script src="${pageContext.request.contextPath}/js/js_script.js"></script>


</body>
</html>