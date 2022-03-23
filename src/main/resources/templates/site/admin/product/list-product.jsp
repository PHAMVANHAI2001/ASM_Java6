<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglibAdmin.jsp" %>
<div class="table-title text-primary">
    <h3 class="text-uppercase">Danh sách sản phẩm</h3>
</div>
<table id="example" class="table table-striped table-bordered table-hover" style="width: 100%">

    <thead class="table-dark text-center">
    <tr>
        <th>Image</th>
        <th class="w-100">Name</th>
        <th>Quantity</th>
        <th class="w-100">UnitPrice</th>
        <th>Available</th>
        <th>Category</th>
        <th>Discount</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${products}" var="product">
        <tr>
            <td><c:if test="${not empty product.image}">
                <img src="${product.image}" width="90" height="100">
            </c:if>
            </td>
            <td >${product.name}</td>
            <td>${product.quantity}</td>
            <td><fmt:formatNumber type="number" value="${product.unitPrice}"/> VNĐ</td>
            <td>${product.available}</td>
            <td>${product.category.name}</td>
            <td>${product.discount.name}</td>
            <td class="button">
                <a href="/admin/edit-product/${product.slug}" class="btn btn-primary w-100 mb-1"><i
                        class="fa-solid fa-pen-to-square"></i></a>
                <a href="/admin/list-product/delete/${product.id}" class="btn btn-danger w-100"><i
                        class="fa-solid fa-trash-can"></i></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
