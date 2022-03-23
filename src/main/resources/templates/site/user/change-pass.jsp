<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/head.jsp" %>
<div class="account-login section">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 offset-lg-3 col-md-10 offset-md-1 col-12">
                <div class="register-form">
                    <div class="title">
                        <h3>Đổi mật khẩu</h3>
                    </div>
                    <form class="row" method="post" action="/user/change-pass">
                        <div class="col-sm-12">
                            <div class="form-group">
                                <label class="form-label">Mật khẩu cũ</label>
                                <input class="form-control" type="password" name="oldPassword" required="">
                            </div>
                        </div>
                        <div class="col-sm-12">
                            <div class="form-group">
                                <label class="form-label">Mật khẩu mới</label>
                                <input class="form-control" type="password" name="newPassword" required="">
                            </div>
                        </div>
                        <div class="col-sm-12">
                            <div class="form-group">
                                <label class="form-label">Xác nhận mật khẩu mới</label>
                                <input class="form-control" type="password" name="confirmPassword" required="">
                            </div>
                        </div>
                        <div class="button">
                            <button class="btn" type="submit">Cập nhật</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>