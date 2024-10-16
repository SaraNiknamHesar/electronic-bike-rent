<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" class="h-100">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- PAGE TITLE -->
    <title>Wascoot Login Page</title>

    <!-- FAVICONS ICON -->
    <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/media/logo-pre.png">
    <link href="https://fonts.googleapis.com/css2?family=Material+Icons" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <link href="./vendor/bootstrap-select/dist/css/bootstrap-select.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/metismenu/dist/metisMenu.min.css">

</head>

<body class="vh-100">
<div class="">

    <!-- Content -->
    <div class="">

        <div class="overflow-hidden" >
            <div class="row gx-0">
                <div class="col-xl-4 col-lg-5 col-md-6 col-sm-12 vh-100 bg-white ">
                    <div id="mCSB_1" class="mCustomScrollBox mCS-light mCSB_vertical mCSB_inside" style="max-height: 653px;" tabindex="0">
                        <div id="mCSB_1_container" class="mCSB_container" style="position:relative; top:0; left:0;" dir="ltr">
                            <div class="login-form style-2">


                                <div class="card-body">
                                    <div class="logo-header" >
                                        <a href="login-page.jsp" class="logo"><img src="${pageContext.request.contextPath}/media/logo-pre.png" width="200" alt="" class="width-230 light-logo center"></a>
                                    </div>

                                    <nav>
                                        <div class="nav nav-tabs border-bottom-0" id="nav-tab" role="tablist">
                                            <div class="tab-content w-100" id="nav-tabContent">
                                                <div class="tab-pane fade show active" id="nav-personal" role="tabpanel" aria-labelledby="nav-personal-tab">
                                                    <form method="POST" action="<c:url value="/admin/login/"/>" class=" dz-form pb-3">
                                                        <h3 style="text-align: center" class="form-title m-t0">Login Information</h3>
                                                        <div class="dz-separator-outer m-b5">
                                                            <div class="dz-separator bg-primary style-liner"></div>
                                                        </div>
                                                        <p style="text-align: center">Enter your e-mail address and your password. </p>
                                                        <div class="form-group mb-3">
                                                            <input id="email" name="email" type="email" class="form-control required" required placeholder="admin@wascoot.com">
                                                        </div>
                                                        <div class="form-group mb-3">
                                                            <input id="password" name="password" type="password" class="form-control required" required placeholder="Password">
                                                        </div>
                                                        <div class="form-group text-left mb-5 forget-main">
                                                            <button type="submit" class="btn btn-primary center">Sign In</button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </nav>
                                </div>
                                <div class="card-footer">
                                    <div class=" bottom-footer clearfix m-t10 m-b20 row text-center">
                                        <div class="col-lg-12 text-center">
                                            <a href="javascript:void(0);">WASCOOT </a> All rights reserved.</span>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>

                    </div>
                </div>
                <div class="col-xl-8 col-lg-5 col-md-6 col-sm-12 vh-100 bg-white ">
                    <img src="${pageContext.request.contextPath}/media/background/scooters-login.jpeg">
                </div>
            </div>
        </div>
        <!-- Full Blog Page Contant -->
    </div>
    <!-- Content END-->
</div>

<!--**********************************
	Scripts
***********************************-->
<!-- Required vendors -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
<script src="js/jquery-3.7.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery"></script>
<script src="https://cdn.jsdelivr.net/npm/metismenu"></script>
<script src="https://unpkg.com/@popperjs/core@2"></script>
<script src="js/wascoot.js"></script>



</body>
</html>