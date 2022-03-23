<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/head.jsp" %>
<div class="account-login section">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 offset-lg-3 col-md-10 offset-md-1 col-12">
                <div class="register-form">
                    <div class="title">
                        <h3>No Account? Register</h3>
                        <p>Registration takes less than a minute but gives you full control over your orders.</p>
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
                    <form:form cssClass="row" action="/user/register" method="post" modelAttribute="register">
                        <div class="col-sm-6">
                            <div class="form-group">
                                <form:label cssClass="form-label" path="username">Username</form:label>
                                <form:input cssClass="form-control" path="username"/>
                                <form:errors path="username" cssClass="text-danger"/>
                            </div>
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
                                <form:label cssClass="form-label" path="password">Password</form:label>
                                <form:password cssClass="form-control" path="password"/>
                                <form:errors path="password" cssClass="text-danger"/>

                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <form:label cssClass="form-label" path="email">Email</form:label>
                                <form:input cssClass="form-control" path="email"/>
                                <form:errors path="email" cssClass="text-danger"/>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <form:label cssClass="form-label" path="confirmPassword">Confirm Password</form:label>
                                <form:password cssClass="form-control" path="confirmPassword"/>
                                <form:errors path="confirmPassword" cssClass="text-danger"/>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <form:label cssClass="form-label" path="phoneNumber">Phone Number</form:label>
                                <form:input cssClass="form-control" path="phoneNumber"/>
                                <form:errors path="phoneNumber" cssClass="text-danger"/>
                            </div>
                        </div>
                        <div class="button">
                            <button class="btn" type=   "submit">Register</button>
                        </div>
                        <p class="outer-link">Already have an account? <a href="/user/login">Login Now</a>
                        </p>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>