<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibAdmin.jsp" %>
<div class="card w-75 mt-4">
    <div class="card-header bg-primary">
        <h3 class="text-white p-0 m-0">QUẢN LÝ NGƯỜI DÙNG</h3>
    </div>
    <c:if test="${not empty messageSuccess}">
        <div class="alert alert-success" role="alert">
                ${messageSuccess}
        </div>
    </c:if>
    <c:if test="${not empty messageFailed}">
        <div class="alert alert-danger" role="alert">
                ${messageFailed}
        </div>
    </c:if>
    <form class="row form-floating p-3" action="/admin/user/save" method="post" id="saveUserForm"
          enctype="multipart/form-data">
        <div class="col-sm-4 mb-2">
            <div class="form-floating">
                <input type="text" class="form-control" id="floatingUsername" placeholder="Username" name="username"
                       value="${username}" ${existUsername ? "readonly" : ""}>
                <label for="floatingUsername">Username</label>
            </div>
        </div>
        <div class="col-sm-4 mb-2">
            <div class="form-floating">
                <input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="password"
                       value="${password}">
                <label for="floatingPassword">Password</label>
            </div>
        </div>
        <div class="col-sm-4 mb-2">
            <div class="form-floating">
                <input type="text" class="form-control" id="floatingFullname" placeholder="Fullname" name="fullname"
                       value="${fullname}">
                <label for="floatingFullname">Fullname</label>
            </div>
        </div>
        <div class="col-sm-4 mb-2">
            <div class="form-floating">
                <input type="text" class="form-control" id="floatingEmail" placeholder="Email" name="email"
                       value="${email}">
                <label for="floatingEmail">Email</label>
            </div>
        </div>
        <div class="col-sm-4 mb-2">
            <div class="form-floating">
                <input type="number" class="form-control" id="floatingPhoneNumber" placeholder="Phone Number"
                       name="phoneNumber" value="${phoneNumber}">
                <label for="floatingPhoneNumber">Phone Number</label>
            </div>
        </div>
        <div class="col-sm-4 mb-2">
            <div class="form-floating">
                <input type="text" class="form-control" id="floatingAddress" placeholder="Address" name="address"
                       value="${address}">
                <label for="floatingAddress">Address</label>
            </div>
        </div>
        <div class="col-sm-12 mb-2">
            <div class="form-check">
                <input class="form-check-input" type="checkbox" id="isAdmin"
                       name="isAdmin" ${isAdmin == true ? "checked" : ""}>
                <label class="form-check-label" for="isAdmin">
                    Admin
                </label>
            </div>
        </div>
        <div class="col-sm-auto mb-2">
            <div class="mb-3">
                <c:if test="${not empty photo}">
                    <img src="/assets/images/avatar/${photo}" width="80" height="100" style="border: 2px solid black">
                </c:if>
                <c:if test="${empty photo}">
                    <img src="/assets/images/avatar/default.png" width="80" height="100"
                         style="border: 2px solid black">
                </c:if>
            </div>
        </div>
        <div class="col-sm-6 mb-2">
            <div class="mb-3">
                <label for="formFile" class="form-label">Hình Ảnh</label>
                <input class="form-control" type="file" id="formFile" name="photo" value="${photo}">
            </div>
        </div>
        <div class="button mt-3">
            <button type="submit" class="btn btn-success">Save</button>
            <button type="reset" class="btn btn-warning">Reset</button>
        </div>
    </form>
</div>