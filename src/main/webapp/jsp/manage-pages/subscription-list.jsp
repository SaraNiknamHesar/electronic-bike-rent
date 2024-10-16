<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../head.jsp"/>
    <title>Wascoot Subscriptions Page</title>
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
                <li><h5 class="bc-title">Subscriptions</h5></li>
                <li class="breadcrumb-item"><a href="<c:url value="/dashboard"/>">
                    <svg width="17" height="17" viewBox="0 0 17 17" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M2.125 6.375L8.5 1.41667L14.875 6.375V14.1667C14.875 14.5424 14.7257 14.9027 14.4601 15.1684C14.1944 15.4341 13.8341 15.5833 13.4583 15.5833H3.54167C3.16594 15.5833 2.80561 15.4341 2.53993 15.1684C2.27426 14.9027 2.125 14.5424 2.125 14.1667V6.375Z" stroke="#2C2C2C" stroke-linecap="round" stroke-linejoin="round"/>
                        <path d="M6.375 15.5833V8.5H10.625V15.5833" stroke="#2C2C2C" stroke-linecap="round" stroke-linejoin="round"/>
                    </svg>
                    Home </a>
                </li>
                <li class="breadcrumb-item active"><a href="javascript:void(0)">Subscriptions Page</a></li>
            </ol>
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-xl-3 col-xxl-4">
                    <div class="card h-auto">
                        <div class="card-header">
                            <h4 class="heading mb-0">Update Subscription </h4>
                        </div>
                        <div class="card-body">
                            <form method="POST" action="<c:url value="/subscription/update/"/>" class="update-subscription">
                                <div class="form-group mb-3">
                                    <label for="idID" class="">Subscription ID<span class="text-danger">*</span>
                                    </label>
                                    <select id="idID" name="id" type="text" class="form-control"  placeholder="select the id">
                                        <c:forEach var="subscription" items="${subscriptionList}">
                                            <option><c:out value="${subscription.id}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group mb-3">
                                    <label for="typeID" class="">Subscription Type<span class="text-danger">*</span>
                                    </label>
                                    <input id="typeID" name="type" type="text" class="form-control" placeholder="modify the subscription type">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="dailyUnlocksID" class="">Daily Unlocks<span class="text-danger">*</span>
                                    </label>
                                    <input id="dailyUnlocksID" name="daily_unlocks" type="number" min=0 step=1 class="form-control"  placeholder="modify daily unlocks" required>
                                </div>
                                <div class="form-group mb-3">
                                    <label for="validityPerDayID">Hours of Validity, per day<span class="text-danger">*</span>
                                    </label>
                                    <div class="input-group">
                                        <input id="validityPerDayID" name="validity_per_day" type="time" class="form-control" placeholder="modify the validity per day">
                                    </div>
                                </div>
                                <div class="form-group mb-3">
                                    <label for="fixedPriceID">Price<span class="text-danger">*</span>
                                    </label>
                                    <div class="input-group">
                                        <div class="input-group">
                                            <div class="input-group-text">€</div>
                                            <input id="fixedPriceID" name="fixed_price" type="number" min=0 step=0.1 class="form-control" placeholder="modify the fixed price">
                                        </div>
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
                                    <h4 class="heading mb-0">Subscriptions</h4>
                                    <div>
                                        <a class="btn btn-primary btn-sm" data-bs-toggle="offcanvas" href="#offcanvasAddModel" role="button" aria-controls="offcanvasAddModel" style="position: absolute;right: 10px;top: 25px;">+ Add Subscription</a>
                                    </div>
                                </div>
                                <c:if test='${not empty subscriptionList}'>
                                    <table id="" class="table">
                                        <thead>
                                        <tr>
                                            <th>Id</th>
                                            <th>Type</th>
                                            <th>Daily Unlocks</th>
                                            <th>Validity Per Day</th>
                                            <th>Fixed Price</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="subscription" items="${subscriptionList}">
                                            <tr>
                                                <td><span><c:out value="${subscription.id}"/></span></td>
                                                <td><span><c:out value="${subscription.type}"/></span></td>
                                                <td><span><c:out value="${subscription.dailyUnlocks}"/></span></td>
                                                <td><span><c:out value="${subscription.validityPerDay}"/></span></td>
                                                <td><span><c:out value="${subscription.fixedPrice}"/></span></td>
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
            <h5 class="modal-title" id="#gridSystemModal">Add Subscription</h5>
            <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close">
                <i class="fa-solid fa-xmark"></i>
            </button>
        </div>
        <div class="offcanvas-body">
            <div class="container-fluid">
                <form method="POST" action="<c:url value="/subscription/insert/"/>">
                    <div class="row">
                        <div class="col-xl-6 mb-3">
                            <label for="canvasSubType" class="form-label">Type<span class="text-danger">*</span></label>
                            <input name="type" type="text" class="form-control" id="canvasSubType" placeholder="">
                        </div>
                        <div class="col-xl-6 mb-3">
                            <label for="canvasSubDailyUnlocks" class="form-label">Daily Unlocks<span class="text-danger">*</span></label>
                            <input name="daily_unlocks" type="number" min=0 step=1 class="form-control" id="canvasSubDailyUnlocks" placeholder="" >
                        </div>
                        <div class="col-xl-6 mb-3">
                            <label for="canvasSubValidityPerDay" class="form-label">Validity per day<span class="text-danger">*</span></label>
                            <input name="validity_per_day" type="time" class="form-control" id="canvasSubValidityPerDay" placeholder="">
                        </div>
                        <div class="col-xl-6 mb-3">
                            <label for="canvasSubPrice" class="form-label">Price<span class="text-danger">*</span></label>
                            <div class="input-group">
                                <div class="input-group-text">€</div>
                                <input name="fixed_price" type="number" min=0 step=0.1 class="form-control" id="canvasSubPrice" placeholder="">
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
        $('#validityPerDayID').flatpickr({
            enableTime: true,
            noCalendar: true,
            dateFormat: 'H:i:S',
            time_24hr: true,
            enableSeconds: true
        });
    });
    $(document).ready(function (){
        $('#canvasSubValidityPerDay').flatpickr({
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
