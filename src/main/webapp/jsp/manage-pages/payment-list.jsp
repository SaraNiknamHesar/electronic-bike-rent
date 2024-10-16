<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../head.jsp"/>
    <title>Payment Method Page</title>

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
                <li><h5 class="bc-title">Payment Methods</h5></li>
                <li class="breadcrumb-item"><a href="<c:url value="/dashboard"/>">
                    <svg width="17" height="17" viewBox="0 0 17 17" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M2.125 6.375L8.5 1.41667L14.875 6.375V14.1667C14.875 14.5424 14.7257 14.9027 14.4601 15.1684C14.1944 15.4341 13.8341 15.5833 13.4583 15.5833H3.54167C3.16594 15.5833 2.80561 15.4341 2.53993 15.1684C2.27426 14.9027 2.125 14.5424 2.125 14.1667V6.375Z" stroke="#2C2C2C" stroke-linecap="round" stroke-linejoin="round"/>
                        <path d="M6.375 15.5833V8.5H10.625V15.5833" stroke="#2C2C2C" stroke-linecap="round" stroke-linejoin="round"/>
                    </svg>
                    Home </a>
                </li>
                <li class="breadcrumb-item active"><a href="javascript:void(0)">Payment Method Page</a></li>
            </ol>
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-xl-3 col-xxl-4">
                    <div class="card h-auto">
                        <div class="card-header">
                            <h4 class="heading mb-0">Update Payment Method </h4>
                        </div>
                        <div class="card-body">
                            <form method="POST" action="<c:url value="/paymentmethod/update/"/>" class="update-model">

                                <div class="form-group mb-3">
                                    <label for="type" class="">Type<span class="text-danger">*</span></label>
                                    <select id="type" name="type" type="text" class="form-control"  placeholder="select the type">
                                        <c:forEach var="paymentmethod" items="${paymentList}">
                                            <option><c:out value="${paymentmethod.type}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group mb-3">
                                    <label for="activation" class="">Activation<span class="text-danger">*</span></label>
                                    <select id="activation" name="activation" type="text" class="form-control" placeholder="select">
                                        <option>Active</option>
                                        <option>Inactive</option>
                                    </select>

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
                                    <h4 class="heading mb-0">Payment Methods</h4>
                                </div>
                                <c:if test='${not empty paymentList}'>
                                    <table id="" class="table">
                                        <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Type</th>
                                            <th>Activation</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="paymentMethod" items="${paymentList}">
                                            <tr>

                                                <td><c:out value="${paymentMethod.id}"/></td>
                                                <td><c:out value="${paymentMethod.type}"/></td>
                                                <td><span class="badge badge-success light border-0 ActiveInactive"><c:out value="${paymentMethod.activation}"/></span></td>

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




</div>

<!--**********************************
    Main wrapper end
***********************************-->

<!--**********************************
    Scripts
***********************************-->

<jsp:include page="../foot.jsp"/>
<script src="../../js/managePages.js"></script>
</body>
</html>


<!-- display the message -->
<%--<c:import url="/jsp/include/show-message.jsp"/>--%>

<!-- display the list of found payment method, if any -->
<%--
<c:if test='${not empty paymentList}'>
    <table>
        <thead>
        <tr>
            <th>Id</th><th>type</th><th>activation</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="paymentMethod" items="${paymentList}">
            <tr>

                <td><c:out value="${paymentMethod.id}"/></td>
                <td><c:out value="${paymentMethod.type}"/></td>
                <td><c:out value="${paymentMethod.activation}"/></td>
                <td bgcolor="yellow">Credit Card</td>
                <td bgcolor="green">Visa Debit</td>
                <td bgcolor="blue">Paypal</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<h1>Update Payment Method</h1>
<form method="POST" action="<c:url value="/paymentmethod/update/"/>">
    <label for="typeID">type:</label>
    &lt;%&ndash;<select name="type" id="typeID">
        <option value="CreditCard">Credit Card</option>
        <option value="VisaDebit">Visa Debit</option>
        <option value="Paypal">Paypal</option>
    </select>&ndash;%&gt;
    <input id="typeID" name="type" type="text"/><br/>

    <label for="activationID">activation:</label>
    &lt;%&ndash;<select name="activation" id="activationID">
        <option value="Inactive">Inactive</option>
        <option value="Active">Active</option>
    </select>&ndash;%&gt;
    <input id="activationID" name="activation" type="text"/><br/>

    <button type="submit">Update</button><br/>
    <button type="reset">Reset the form</button>
</form>

<a href="${pageContext.request.contextPath}/jsp/homepage.jsp">go back to the homepage</a>
</body>
</html>--%>
