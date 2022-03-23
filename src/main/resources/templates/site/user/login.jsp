<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/head.jsp" %>
<div class="account-login section">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 offset-lg-3 col-md-10 offset-md-1 col-12">
                <form class="card login-form" action="/user/login" method="post">
                    <div class="card-body">
                        <div class="title">
                            <h3>Login Now</h3>
                            <p>You can login using your social media account or email
                                address.</p>
                        </div>
                        <div class="social-login">
                            <a class="btn google-btn" href="javascript:void(0)"> <i
                                    class="lni lni-google"></i> Google Login
                            </a>
                        </div>
                        <div class="alt-option">
                            <span>Or</span>
                        </div>
                        <c:if test="${not empty messageFailed}">
                            <div class="alert alert-danger" role="alert">
                                    ${messageFailed}
                            </div>
                        </c:if>
                        <div class="form-group input-group">
                            <label class="form-label">Username</label> <input class="form-control"
                                                                              type="text" required="" name="username"
                                                                              value="${username}">
                        </div>
                        <div class="form-group input-group">
                            <label class="form-label">Password</label> <input class="form-control"
                                                                              type="password" required=""
                                                                              name="password" value="${password}">
                        </div>
                        <div
                                class="d-flex flex-wrap justify-content-between bottom-content">
                            <div class="form-check">
                                <input type="checkbox" class="form-check-input width-auto"
                                       id="remember-me" name="isRemember" value="true" ${isRemember}> <label
                                    for="remember-me" class="form-check-label">Remember me</label>
                            </div>
                            <a class="lost-pass" href="/user/forgot-pass">Forgot
                                password?</a>
                        </div>
                        <div class="button">
                            <button class="btn" type="submit">Login</button>
                        </div>
                        <p class="outer-link">
                            Don't have an account? <a href="/user/register">Register here
                        </a>
                        </p>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>