<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/head.jsp" %>
<div class="account-login section">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 offset-lg-3 col-md-10 offset-md-1 col-12">
                <div class="register-form">
                    <div class="title">
                        <h3>Cập nhật tài khoản</h3>
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
                    <form:form cssClass="row" action="/user/profile" method="post" modelAttribute="editProfile"
                               enctype="multipart/form-data">
                        <div class="col-sm-12">
                            <div class="form-group">
                                <label for="formFile" class="form-label">Hình ảnh</label>
                                <img class="border" src="/assets/images/avatar/${editProfile.photo}" width="90" height="100">
                            </div>
                            <input class="form-control" type="file" name="filePhoto" id="formFile">
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <form:label cssClass="form-label" path="fullname">Fullname</form:label>
                                <form:input cssClass="form-control" path="fullname"/>
                                <form:errors path="fullname" cssClass="text-danger"/>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <form:label cssClass="form-label" path="email">Email Address</form:label>
                                <form:input cssClass="form-control" path="email"/>
                                <form:errors path="email" cssClass="text-danger"/>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <form:label cssClass="form-label" path="phoneNumber">Phone Number</form:label>
                                <form:input cssClass="form-control" path="phoneNumber"/>
                                <form:errors path="phoneNumber" cssClass="text-danger"/>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <form:label cssClass="form-label" path="address">Address</form:label>
                                <form:input cssClass="form-control" path="address"/>
                                <form:errors path="address" cssClass="text-danger"/>
                            </div>
                        </div>
                        <div class="button">
                            <button class="btn" type="submit">Cập nhật</button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>