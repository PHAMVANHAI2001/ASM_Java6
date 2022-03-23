<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibAdmin.jsp" %>
<div class="table-title text-primary">
    <h3 class="text-uppercase">Danh sách người dùng</h3>
</div>
<table id="example" class="table table-striped table-bordered table-hover" style="width: 100%">

    <thead class="table-dark text-center">
    <tr>
        <th scope="col">Photo</th>
        <th scope="col">Username</th>
        <th scope="col">Password</th>
        <th scope="col">Fullname</th>
        <th scope="col">Email</th>
        <th scope="col">Address</th>
        <th scope="col">Phone</th>
        <th scope="col">isAdmin</th>
        <th scope="col">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:if test="${not empty user.photo}">
                <img src="/assets/images/avatar/${user.photo}" width="80" height="100">
            </c:if>
                <c:if test="${empty user.photo}">
                    <img src="/assets/images/avatar/default.png" width="80" height="100">
                </c:if>
            </td>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td class="w-100">${user.fullname}</td>
            <td>${user.email}</td>
            <td>${user.address}</td>
            <td>${user.phoneNumber}</td>
            <td>${user.isAdmin}</td>
            <td class="button w-100">
                <a href="/admin/edit-user/${user.id}" class="btn btn-primary w-100 mb-1"><i
                        class="fa-solid fa-pen-to-square"></i></a>
                <a href="/admin/list-user/delete/${user.id}" class="btn btn-danger w-100"><i
                        class="fa-solid fa-trash-can"></i></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>