<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/head.jsp" %>
<div class="shopping-cart section">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="total-amount">
                    <div class="row">
                        <div class="col-lg-9 col-12">
                            <div class="cart-list-head">
                                <div class="cart-list-title">
                                    <div class="row">
                                        <div class="col-lg-1 col-md-2 col-12">
                                        </div>
                                        <div class="col-lg-9 col-md-7 col-12">
                                            <p>Tên sản phẩm</p>
                                        </div>
                                        <div class="col-lg-2 col-md-3 col-12">
                                            <p>Số Lượng</p>
                                        </div>
                                    </div>
                                </div>
                                <c:forEach items="${cartList}" var="cart">
                                    <div class="cart-single-list">
                                        <div class="row align-items-center">
                                            <div class="col-lg-1 col-md-2 col-12">
                                                <a href="product-details.html"><img src="${cart.product.image}"
                                                                                    alt="#"></a>
                                            </div>
                                            <div class="col-lg-9 col-md-7 col-12">
                                                <h5 class="product-name"><a href="product-details.html">
                                                        ${cart.product.name}</a></h5>
                                                <p class="product-des">
                                                    <span><em>Danh mục:</em> ${cart.product.category.name}</span>
                                                    <span><em>Giá tiền:</em> <fmt:formatNumber type="number"
                                                                                               value="${cart.product.unitPrice}"/> VNĐ</span>
                                                </p>
                                            </div>
                                            <div class="col-lg-2 col-md-3 col-12">
                                                <div class="count-input">
                                                    <input class="form-control" type="number" id="quantity1" min="1"
                                                           value="${cart.quantity}">
                                                </div>
                                                <div class="d-inline-flex align-items-center position-relative">
                                                    <a class="remove-item stretched-link m-0"
                                                       href="/user/shopping-cart/remove/${cart.id}"><i
                                                            class="lni lni-close"></i></a>
                                                    <span class="remove-text ps-2">Remove</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="col-lg-3 col-12">
                            <div class="right">
                                <ul>
                                    <li>Tổng Tiền: <span><fmt:formatNumber type="number"
                                                                           value="${totalUnitPrice}"/></span></li>
                                    <li>Shipping<span>Free</span></li>
                                </ul>
                                <div class="button">
                                    <a href="/user/shopping-cart/order" class="btn">Đặt Hàng</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>