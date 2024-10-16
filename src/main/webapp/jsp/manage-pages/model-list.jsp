<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../head.jsp"/>
    <title>Wascoot Model Page</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">

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
   ***********************************8--%>
    <jsp:include page="../header-menu.jsp"/>

    <!--**********************************
        Content body start
    ***********************************-->
    <div class="content-body">
        <!-- row -->
        <div class="page-titles">
            <ol class="breadcrumb">
                <li><h5 class="bc-title">Models</h5></li>
                <li class="breadcrumb-item"><a href="<c:url value="/dashboard"/>">
                    <svg width="17" height="17" viewBox="0 0 17 17" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M2.125 6.375L8.5 1.41667L14.875 6.375V14.1667C14.875 14.5424 14.7257 14.9027 14.4601 15.1684C14.1944 15.4341 13.8341 15.5833 13.4583 15.5833H3.54167C3.16594 15.5833 2.80561 15.4341 2.53993 15.1684C2.27426 14.9027 2.125 14.5424 2.125 14.1667V6.375Z" stroke="#2C2C2C" stroke-linecap="round" stroke-linejoin="round"/>
                        <path d="M6.375 15.5833V8.5H10.625V15.5833" stroke="#2C2C2C" stroke-linecap="round" stroke-linejoin="round"/>
                    </svg>
                    Home </a>
                </li>
                <li class="breadcrumb-item active"><a href="javascript:void(0)">Model Page</a></li>
            </ol>
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-xl-3 col-xxl-4">
                    <div class="card h-auto">
                        <div class="card-header">
                            <h4 class="heading mb-0">Update Model </h4>
                        </div>
                        <div class="card-body">
                            <form method="POST" action="<c:url value="/model/update/"/>" class="update-model">
                                <div class="form-group mb-3">
                                    <label for="nameID" class="">Model Name<span class="text-danger">*</span>
                                    </label>
                                    <select id="nameID" name="name" type="text" class="form-control"  placeholder="select the name">
                                        <c:forEach var="model" items="${modelList}">
                                            <option><c:out value="${model.name}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group mb-3">
                                    <label for="brandID" class="">Brand<span class="text-danger">*</span>
                                    </label>
                                    <input id="brandID" name="brand" type="text" class="form-control"  placeholder="modify the brand">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="batteryLifeID" class="">Battery Life<span class="text-danger">*</span>
                                    </label>
                                    <input id="batteryLifeID" name="battery_life" type="text" class="form-control batteryLifeID"  placeholder="modify battery life" required>
                                </div>
                                <div class="form-group mb-3">
                                    <label for="pricePerMinID">Price Per Min<span class="text-danger">*</span>
                                    </label>
                                    <div class="input-group">
                                        <div class="input-group-text">€</div>
                                        <input id="pricePerMinID" name="price_per_min" type="number" min=0 step=0.1 class="form-control" placeholder="modify the price">
                                    </div>
                                </div>
                                <div>
                                    <button type="submit" class="btn btn-primary">Update</button>
                                    <button type="reset" class="btn btn-danger light">Clear Form</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-xl-9 col-xxl-8">
                    <div class="card">
                        <div class="card-body p-0">
                            <div class="table-responsive customer-rows">
                                <div class="tbl-caption">
                                    <h4 class="heading mb-0">Models</h4>
                                    <div>
                                        <a class="btn btn-primary btn-sm" data-bs-toggle="offcanvas" href="#offcanvasAddModel" role="button" aria-controls="offcanvasAddModel" style="position: absolute;right: 10px;top: 25px;">+ Add Model</a>
                                    </div>
                                </div>
                                <c:if test='${not empty modelList}'>
                                <table id="" class="table">
                                    <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Brand</th>
                                        <th>Battery Life</th>
                                        <th>Price per min</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="model" items="${modelList}">
                                    <tr>
                                        <td><span><c:out value="${model.name}"/></span></td>
                                        <td><span><c:out value="${model.brand}"/></span></td>
                                        <td><span><c:out value="${model.batteryLife}"/></span></td>
                                        <td><span class="text-primary"><c:out value="${model.pricePerMin}"/></span></td>

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

    <div class="offcanvas offcanvas-end customeoff" tabindex="-1" id="offcanvasAddModel">
        <div class="offcanvas-header">
            <h5 class="modal-title" id="#gridSystemModal">Add Model</h5>
            <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close">
                <i class="fa-solid fa-xmark"></i>
            </button>
        </div>
        <div class="offcanvas-body">
            <div class="container-fluid">
                <form method="POST" action="<c:url value="/model/insert/"/>">
                    <div class="row">
                        <div class="col-xl-6 mb-3">
                            <label for="canvasModelName" class="form-label">Model Name <span class="text-danger">*</span></label>
                            <input name="name" type="text" class="form-control" id="canvasModelName" placeholder="">
                        </div>
                        <div class="col-xl-6 mb-3">
                            <label for="canvasModelBrand" class="form-label">Brand<span class="text-danger">*</span></label>
                            <input name="brand" type="text" class="form-control" id="canvasModelBrand" placeholder="">
                        </div>
                        <div class="col-xl-6 mb-3">
                            <label for="canvasModelBattery" class="form-label">Battery Life<span class="text-danger">*</span></label>
                            <input name="battery_life" type="text" class="form-control" id="canvasModelBattery" placeholder="" class="batteryLifeID">
                        </div>
                        <div class="col-xl-6 mb-3">
                            <label for="canvasModelPrice" class="form-label">Price Per Min<span class="text-danger">*</span></label>
                            <div class="input-group">
                                <div class="input-group-text">€</div>
                                <input name="price_per_min" type="number" min=0 step=0.1 class="form-control" id="canvasModelPrice" placeholder="">
                            </div>
                        </div>
                    </div>
                    <div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                        <button type="reset" class="btn btn-danger light">Clear Form</button>
                    </div>
                </form>
            </div>
        </div>
    </div>



</div>
<!--**********************************
    Main wrapper end
***********************************-->

<!--**********************************
    Scripts
***********************************-->

<jsp:include page="../foot.jsp"/>

<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<script>
    $(document).ready(function() {
        $('#batteryLifeID').flatpickr({
            enableTime: true,
            noCalendar: true,
            dateFormat: 'H:i:S',
            time_24hr: true,
            enableSeconds: true
        });
    });
    $(document).ready(function (){
        $('#canvasModelBattery').flatpickr({
            enableTime: true,
            noCalendar: true,
            dateFormat: 'H:i:S',
            time_24hr: true,
            enableSeconds: true
        });
    });
</script>

</body>
</html>

<%--
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
            <th>name</th><th>brand</th><th>battery life</th><th>price p/min</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="model" items="${modelList}">
            <tr>

                <td><c:out value="${model.name}"/></td>
                <td><c:out value="${model.brand}"/></td>
                <td><c:out value="${model.batteryLife}"/></td>
                <td><c:out value="${model.pricePerMin}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<h1>Update Employee</h1>
<form method="POST" action="<c:url value="/model/update/"/>">
    <label for="nameID">name:</label>
    <input id="nameID" name="name" type="text"/><br/>

    <label for="brandID">brand:</label>
    <input id="brandID" name="brand" type="text"/><br/>

    <label for="batteryLifeID">battery life:</label>
    <input id="batteryLifeID" name="battery_life" type="text"/><br/>

    <label for="pricePerMinID">price per min:</label>
    <input id="pricePerMinID" name="price_per_min" type="text"/><br/><br/>

    <button type="submit">Update</button><br/>
    <button type="reset">Reset the form</button>
</form>

<button type="button" onclick="window.location.href='create-model-form.jsp';">Create</button>

<a href="${pageContext.request.contextPath}/jsp/homepage.jsp">go back to the homepage</a>
</body>
</html>--%>
