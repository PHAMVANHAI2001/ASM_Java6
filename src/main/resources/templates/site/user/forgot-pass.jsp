<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/head.jsp" %>
<div class="account-login section">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 offset-lg-3 col-md-10 offset-md-1 col-12">
                <div class="register-form">
                    <div class="title">
                        <h3>Quên Mật Khẩu</h3>
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
                    <form class="row" action="/user/forgot-pass" method="post">
                        <div class="col-sm-12">
                            <div class="form-group">
                                <label class="form-label">Email address</label>
                                <input type="email" class="form-control" name="email">
                            </div>
                        </div>
                        <div class="button">
                            <button class="btn" type="submit">Lấy lại mật khẩu mới</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

