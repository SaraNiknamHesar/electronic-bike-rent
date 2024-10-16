<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <jsp:include page="../head.jsp"/>
  <title>Wascoot Scooterracks Page</title>
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
        <li><h5 class="bc-title">Scooterracks</h5></li>
        <li class="breadcrumb-item"><a href="<c:url value="/dashboard"/>">
          <svg width="17" height="17" viewBox="0 0 17 17" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M2.125 6.375L8.5 1.41667L14.875 6.375V14.1667C14.875 14.5424 14.7257 14.9027 14.4601 15.1684C14.1944 15.4341 13.8341 15.5833 13.4583 15.5833H3.54167C3.16594 15.5833 2.80561 15.4341 2.53993 15.1684C2.27426 14.9027 2.125 14.5424 2.125 14.1667V6.375Z" stroke="#2C2C2C" stroke-linecap="round" stroke-linejoin="round"/>
            <path d="M6.375 15.5833V8.5H10.625V15.5833" stroke="#2C2C2C" stroke-linecap="round" stroke-linejoin="round"/>
          </svg>
          Home </a>
        </li>
        <li class="breadcrumb-item active"><a href="javascript:void(0)">Scooterracks Page</a></li>
      </ol>
    </div>
    <div class="container-fluid">
      <div class="row">
        <div class="col-xl-3 col-xxl-4">
          <div class="card h-auto">
            <div class="card-header">
              <h4 class="heading mb-0">Update Scooterrack </h4>
            </div>
            <div class="card-body">
              <form method="POST" action="<c:url value="/scooterrack/update/"/>" class="update-scooterrack">
                <div class="form-group mb-3">
                  <label for="nameID" class="">Scooterrack ID<span class="text-danger">*</span>
                  </label>
                  <select id="nameID" name="id" type="text" class="form-control"  placeholder="select the id">
                    <c:forEach var="scooterrack" items="${scooterrackList}">
                      <option><c:out value="${scooterrack.id}"/></option>
                    </c:forEach>
                  </select>
                </div>
                <div class="form-group mb-3">
                  <label for="totalParkingSpotsID" class="">Total Parking Spots<span class="text-danger">*</span>
                  </label>
                  <input id="totalParkingSpotsID" name="total_parking_spots" type="number" min=0 step=1 class="form-control"  placeholder="modify the total parking spots">
                </div>
                <div class="form-group mb-3">
                  <label for="availableParkingSpotsID" class="">Available Parking Spots<span class="text-danger">*</span>
                  </label>
                  <input id="availableParkingSpotsID" name="available_parking_spots" type="number" min=0 step=1 class="form-control kmTraveledID"  placeholder="modify available parking spots" required>
                </div>
                <div class="form-group mb-3">
                  <label for="postalCodeID">Postal Code<span class="text-danger">*</span>
                  </label>
                  <div class="input-group">
                    <input id="postalCodeID" name="postalcode" type="number" min=0 step=1 class="form-control" placeholder="modify the postalcode">
                  </div>
                </div>
                <div class="form-group mb-3">
                  <label for="addressID">Address<span class="text-danger">*</span>
                  </label>
                  <div class="input-group">
                    <input id="addressID" name="address" type="text" class="form-control" placeholder="modify the address">
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
                  <h4 class="heading mb-0">Scooterracks</h4>
                  <div>
                    <a class="btn btn-primary btn-sm" data-bs-toggle="offcanvas" href="#offcanvasAddModel" role="button" aria-controls="offcanvasAddModel" style="position: absolute;right: 10px;top: 25px;">+ Add Scooterracks</a>
                  </div>
                </div>
                <c:if test='${not empty scooterrackList}'>
                  <table id="" class="table">
                    <thead>
                    <tr>
                      <th>Id</th>
                      <th>Total Parking Spots</th>
                      <th>Available Parking Spots</th>
                      <th>Postal Code</th>
                      <th>Address</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="scooterrack" items="${scooterrackList}">
                      <tr>
                        <td><span><c:out value="${scooterrack.id}"/></span></td>
                        <td><span><c:out value="${scooterrack.totalParkingSpots}"/></span></td>
                        <td><span><c:out value="${scooterrack.availableParkingSpots}"/></span></td>
                        <td><span><c:out value="${scooterrack.postalCode}"/></span></td>
                        <td><span><c:out value="${scooterrack.address}"/></span></td>
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
        <form method="POST" action="<c:url value="/scooterrack/insert/"/>">
          <div class="row">
            <div class="col-xl-6 mb-3">
              <label for="canvasScooterrackTPS" class="form-label">Total Parking Spots<span class="text-danger">*</span></label>
              <input name="total_parking_spots" type="number" min=0 step=1 class="form-control" id="canvasScooterrackTPS" placeholder="">
            </div>
            <div class="col-xl-6 mb-3">
              <label for="canvasScooterrackAPS" class="form-label">Avail. Parking Spots<span class="text-danger">*</span></label>
              <input name="available_parking_spots" type="number" min=0 step=1 class="form-control" id="canvasScooterrackAPS" placeholder="" >
            </div>
            <div class="col-xl-6 mb-3">
              <label for="canvasScooterrackPostalcode" class="form-label">Postal Code<span class="text-danger">*</span></label>
              <input name="postalcode" type="number" min=0 step=1 class="form-control" id="canvasScooterrackPostalcode" placeholder="">
            </div>
            <div class="col-xl-6 mb-3">
              <label for="canvasScooterrackAddress" class="form-label">Address<span class="text-danger">*</span></label>
              <input name="address" type="text" class="form-control" id="canvasScooterrackAddress" placeholder="">
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
