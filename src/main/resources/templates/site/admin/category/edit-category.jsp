<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibAdmin.jsp" %>
<form action="/admin/edit-category" method="post">
    <div class="mb-3">
        <label class="form-label">Name Category</label>
        <input type="text" class="form-control" name="name" value="${name}">
    </div>
    <div class="mb-3">
        <label class="form-label">Slug</label>
        <input type="text" class="form-control" name="slug" value="${slug}">
    </div>
    <div class="button-group mb-3">
        <button type="submit" class="btn btn-primary" formaction="/admin/category/add">Thêm</button>
        <button class="btn btn-primary" formaction="/admin/category/edit/${id}">Sửa</button>
        <button class="btn btn-primary" type="reset">Reset</button>
    </div>
</form>

<table id="example" class="table table-striped table-bordered table-hover" style="width: 100%">
    <thead class="table-dark text-center">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Slug</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${categories}" var="category">
        <tr>
            <td >${category.id}</td>
            <td>${category.name}</td>
            <td>${category.slug}</td>
            <td class="button">
                <a href="/admin/category/edit/${category.id}" class="btn btn-primary w-100 mb-1"><i
                        class="fa-solid fa-pen-to-square"></i></a>
                <a href="/admin/category/delete/${category.id}" class="btn btn-danger w-100"><i
                        class="fa-solid fa-trash-can"></i></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
