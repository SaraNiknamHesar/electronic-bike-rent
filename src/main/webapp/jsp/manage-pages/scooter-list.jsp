<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <jsp:include page="../head.jsp"/>
  <title>Wascoot Scooters Page</title>
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
        <li><h5 class="bc-title">Scooters</h5></li>
        <li class="breadcrumb-item"><a href="<c:url value="/dashboard"/>">
          <svg width="17" height="17" viewBox="0 0 17 17" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M2.125 6.375L8.5 1.41667L14.875 6.375V14.1667C14.875 14.5424 14.7257 14.9027 14.4601 15.1684C14.1944 15.4341 13.8341 15.5833 13.4583 15.5833H3.54167C3.16594 15.5833 2.80561 15.4341 2.53993 15.1684C2.27426 14.9027 2.125 14.5424 2.125 14.1667V6.375Z" stroke="#2C2C2C" stroke-linecap="round" stroke-linejoin="round"/>
            <path d="M6.375 15.5833V8.5H10.625V15.5833" stroke="#2C2C2C" stroke-linecap="round" stroke-linejoin="round"/>
          </svg>
          Home </a>
        </li>
        <li class="breadcrumb-item active"><a href="javascript:void(0)">Scooters Page</a></li>
      </ol>
    </div>
    <div class="container-fluid">
      <div class="row">
        <div class="col-xl-3 col-xxl-4">
          <div class="card h-auto">
            <div class="card-header">
              <h4 class="heading mb-0">Update Scooter </h4>
            </div>
            <div class="card-body">
              <form method="POST" action="<c:url value="/scooter/update/"/>" class="update-scooter">
                <div class="form-group mb-3">
                  <label for="nameID" class="">Scooter ID<span class="text-danger">*</span>
                  </label>
                  <select id="nameID" name="id" type="text" class="form-control"  placeholder="select the id">
                    <c:forEach var="scooter" items="${scootersList}">
                      <option><c:out value="${scooter.id}"/></option>
                    </c:forEach>
                  </select>
                </div>
                <div class="form-group mb-3">
                  <label for="dateOfPurchaseID" class="">Date of purchase<span class="text-danger">*</span>
                  </label>
                  <input id="dateOfPurchaseID" name="date_of_purchase" type="date" class="form-control"  placeholder="modify the date of purchase">
                </div>
                <div class="form-group mb-3">
                  <label for="kmTraveledID" class="">Km traveled<span class="text-danger">*</span>
                  </label>
                  <input id="kmTraveledID" name="km_traveled" type="number" min=0 step=0.5 class="form-control kmTraveledID"  placeholder="modify km traveled" required>
                </div>
                <div class="form-group mb-3">
                  <label for="modelID">Scooter's model<span class="text-danger">*</span>
                  </label>
                  <div class="input-group">
                    <input id="modelID" name="model" type="text" class="form-control" placeholder="modify the model">
                  </div>
                </div>
                <div class="form-group mb-3">
                  <label for="remainingBatteryLifeID">Remaining battery life<span class="text-danger">*</span>
                  </label>
                  <div class="input-group">
                    <input id="remainingBatteryLifeID" name="remaining_battery_life" type="number" class="form-control" placeholder="modify the remaining battery life">
                  </div>
                </div>
                <div class="form-group mb-3">
                  <label for="idScooterrackID">Scooter's scooterrack id<span class="text-danger">*</span>
                  </label>
                  <div class="input-group">
                    <input id="idScooterrackID" name="id_scooterrack" type="number" min=0 step=1 class="form-control" placeholder="modify the scooterrack id">
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
                  <h4 class="heading mb-0">Scooters</h4>
                  <div>
                    <a class="btn btn-primary btn-sm" data-bs-toggle="offcanvas" href="#offcanvasAddModel" role="button" aria-controls="offcanvasAddModel" style="position: absolute;right: 10px;top: 25px;">+ Add Scooter</a>
                  </div>
                </div>
                <c:if test='${not empty scootersList}'>
                  <table id="" class="table">
                    <thead>
                    <tr>
                      <th>Id</th>
                      <th>Date of Purchase</th>
                      <th>Km Traveled</th>
                      <th>Model Name</th>
                      <th>Remaining Battery Life</th>
                      <th>Scooterrack id</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="scooter" items="${scootersList}">
                      <tr>
                        <td><span><c:out value="${scooter.id}"/></span></td>
                        <td><span><c:out value="${scooter.dateOfPurchase}"/></span></td>
                        <td><span><c:out value="${scooter.kmTraveled}"/></span></td>
                        <td><span><c:out value="${scooter.model}"/></span></td>
                        <td><span><c:out value="${scooter.remainingBatteryLife}"/></span></td>
                        <td><span><c:out value="${scooter.idScooterrack}"/></span></td>
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
      <h5 class="modal-title" id="#gridSystemModal">Add Scooter</h5>
      <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close">
        <i class="fa-solid fa-xmark"></i>
      </button>
    </div>
    <div class="offcanvas-body">
      <div class="container-fluid">
        <form method="POST" action="<c:url value="/scooter/insert/"/>">
          <div class="row">
            <div class="col-xl-6 mb-3">
              <label for="canvasModelDateofpurchase" class="form-label">Date of Purchase<span class="text-danger">*</span></label>
              <input name="date_of_purchase" type="date" class="form-control" id="canvasModelDateofpurchase" placeholder="">
            </div>
            <div class="col-xl-6 mb-3">
              <label for="canvasModelKmtraveled" class="form-label">Km Traveled<span class="text-danger">*</span></label>
              <input name="km_traveled" type="number" min=0 step=0.1 class="form-control" id="canvasModelKmtraveled" placeholder="" >
            </div>
            <div class="col-xl-6 mb-3">
              <label for="canvasModelModelname" class="form-label">Model Name<span class="text-danger">*</span></label>
              <input name="model" type="text" class="form-control" id="canvasModelModelname" placeholder="">
            </div>
            <div class="col-xl-6 mb-3">
              <label for="canvasModelRemainingbatterylife" class="form-label">Remaining Battery<span class="text-danger">*</span></label>
              <input name="remaining_battery_life" type="number" min=0 max=100 step=0.1 class="form-control" id="canvasModelRemainingbatterylife" placeholder="">
            </div>
            <div class="col-xl-6 mb-3">
              <label for="canvasModelScooterrackid" class="form-label">Scooterrack id<span class="text-danger">*</span></label>
              <input name="id_scooterrack" type="number" min=0 step=1 class="form-control" id="canvasModelScooterrackid" placeholder="">
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
  document.addEventListener('DOMContentLoaded', function() {
    flatpickr("#dateOfPurchaseID", {
      enableTime: false, // Change to true if you want to enable time selection as well
      dateFormat: "Y-m-d", // Change the date format as desired
    });
  });
  document.addEventListener('DOMContentLoaded', function() {
    flatpickr("#canvasModelDateofpurchase", {
      enableTime: false, // Change to true if you want to enable time selection as well
      dateFormat: "Y-m-d", // Change the date format as desired
    });
  });
</script>

</body>
</html>
