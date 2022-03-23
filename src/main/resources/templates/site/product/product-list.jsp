<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/head.jsp" %>
<section class="product-grids section">
    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-12">
                <div class="product-sidebar">

                    <div class="single-widget search">
                        <h3>Search Product</h3>
                        <form action="#">
                            <input type="text" placeholder="Search Here...">
                            <button type="submit">
                                <i class="lni lni-search-alt"></i>
                            </button>
                        </form>
                    </div>

                    <div class="single-widget">
                        <h3>All Categories</h3>
                        <ul class="list">
                            <c:forEach items="${categories}" var="category">
                                <li>
                                    <a href="/product/product-list/${category.slug}">${category.name}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>

                </div>

            </div>
            <div class="col-lg-9 col-12">
                <div class="product-grids-head">
                    <div class="product-grid-topbar">
                        <div class="row align-items-center">
                            <div class="col-lg-7 col-md-8 col-12">
                                <div class="product-sorting">
                                    <label for="sorting">Sort by:</label> <select
                                        class="form-control" id="sorting">
                                    <option>Khuyến mãi hot</option>
                                    <option>Giá thấp đến cao</option>
                                    <option>Giá Cao đến thấp</option>
                                </select>
                                </div>
                            </div>
                            <div class="col-lg-5 col-md-4 col-12">
                                <nav>
                                    <div class="nav nav-tabs" id="nav-tab" role="tablist">
                                        <button class="nav-link active" id="nav-grid-tab"
                                                data-bs-toggle="tab" data-bs-target="#nav-grid" type="button"
                                                role="tab" aria-controls="nav-grid" aria-selected="true">
                                            <i class="lni lni-grid-alt"></i>
                                        </button>
                                        <button class="nav-link" id="nav-list-tab"
                                                data-bs-toggle="tab" data-bs-target="#nav-list" type="button"
                                                role="tab" aria-controls="nav-list" aria-selected="false">
                                            <i class="lni lni-list"></i>
                                        </button>
                                    </div>
                                </nav>
                            </div>
                        </div>
                    </div>
                    <div class="tab-content" id="nav-tabContent">
                        <div class="tab-pane fade show active" id="nav-grid"
                             role="tabpanel" aria-labelledby="nav-grid-tab">
                            <div class="row">
                                <c:forEach items="${pageProducts.content}" var="product">
                                    <div class="col-lg-4 col-md-6 col-12">
                                        <div class="single-product">
                                            <div class="product-image">
                                                <img src="${product.image}" alt="#">
                                                <c:if test="${product.discount.saleOff != null}">
                                                    <span class="sale-tag">-${product.discount.saleOff}%</span>
                                                </c:if>
                                                <div class="button">
                                                    <a href="/user/shopping-cart/add/${product.slug}" class="btn"><i
                                                            class="lni lni-cart"></i> Add to Cart</a>
                                                </div>
                                            </div>
                                            <div class="product-info">
                                                <span class="category">${product.category.name}</span>
                                                <h4 class="title">
                                                    <a href="/product/product-detail/${product.slug}">${product.name}</a>
                                                </h4>
                                                <ul class="review">
                                                    <li><i class="lni lni-star-filled"></i></li>
                                                    <li><i class="lni lni-star-filled"></i></li>
                                                    <li><i class="lni lni-star-filled"></i></li>
                                                    <li><i class="lni lni-star-filled"></i></li>
                                                    <li><i class="lni lni-star-filled"></i></li>
                                                    <li><span>5.0 Review(s)</span></li>
                                                </ul>
                                                <div class="price">
                                                    <c:if test="${not empty product.discount}">
                                                        <span class="discount-price"><fmt:formatNumber type="number"
                                                                                                       value="${product.unitPrice}"/> VNĐ</span>
                                                    </c:if>
                                                    <span><fmt:formatNumber type="number"
                                                                            value="${product.unitPrice - (product.unitPrice*product.discount.saleOff)/100}"/> VNĐ</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="single-product">
                                            <div class="product-image">
                                                <img class="p-4" src="${product.image}" alt="#">
                                                <div class="button">
                                                    <a href="/user/shopping-cart/add/${product.slug}" class="btn">
                                                        <i class="lni lni-cart"></i>Thêm vào giỏ</a>
                                                        <%--                                <a href="javascript:void(0)" onclick="addToCart(${product.id})"--%>
                                                        <%--                                   class="btn"><i--%>
                                                        <%--                                        class="lni lni-cart"></i>Thêm vào giỏ</a>--%>
                                                </div>
                                            </div>
                                            <div class="product-info">
                                                <span class="category">${product.category.name}</span>
                                                <h4 class="title">
                                                    <a href="/product/product-detail/${product.slug}">${product.name}</a>
                                                </h4>
                                                <div class="price">

                                                    <span><fmt:formatNumber type="number" value="${product.unitPrice}"/> VNĐ</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                            <div class="row">
                                <div class="col-12">
                                    <div class="pagination center">
                                        <ul class="pagination-list">
                                            <li><a href="/product/product-list?p=0"><i
                                                    class="lni lni-chevron-left"></i></a></li>
                                            <li><a href="/product/product-list?p=${pageProducts.number-1}"><i
                                                    class="fa-solid fa-chevrons-left"></i></a></li>
                                            <li><a href="/product/product-list?p=${pageProducts.number+1}"><i
                                                    class="fa-solid fa-chevrons-right"></i></a></li>
                                            <li><a href="/product/product-list?p=${pageProducts.totalPages-1}"><i
                                                    class="lni lni-chevron-right"></i></a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%--                        <div class="tab-pane fade" id="nav-list" role="tabpanel"--%>
                        <%--                             aria-labelledby="nav-list-tab">--%>
                        <%--                            <div class="row">--%>
                        <%--                                <div class="col-lg-12 col-md-12 col-12">--%>
                        <%--                                    <c:forEach items="${page.content}" var="product">--%>
                        <%--                                        <div class="single-product">--%>
                        <%--                                            <div class="row align-items-center">--%>
                        <%--                                                <div class="col-lg-4 col-md-4 col-12">--%>
                        <%--                                                    <div class="product-image">--%>
                        <%--                                                        <img src="${product.image}"--%>
                        <%--                                                             alt="#"> <c:if test="${product.discount.saleOff != null}">--%>
                        <%--                                                        <span class="sale-tag">-${product.discount.saleOff}%</span>--%>
                        <%--                                                    </c:if>--%>
                        <%--                                                        <div class="button">--%>
                        <%--                                                            <a href="/user/shopping-cart/add/${product.slug}"--%>
                        <%--                                                               class="btn"><i--%>
                        <%--                                                                    class="lni lni-cart"></i> Add to Cart</a>--%>
                        <%--                                                        </div>--%>
                        <%--                                                    </div>--%>
                        <%--                                                </div>--%>
                        <%--                                                <div class="col-lg-8 col-md-8 col-12">--%>
                        <%--                                                    <div class="product-info">--%>
                        <%--                                                        <span class="category">${product.category.name}</span>--%>
                        <%--                                                        <h4 class="title">--%>
                        <%--                                                            <a href="/product/product-detail/${product.slug}">${product.name}</a>--%>
                        <%--                                                        </h4>--%>
                        <%--                                                        <ul class="review">--%>
                        <%--                                                            <li><i class="lni lni-star-filled"></i></li>--%>
                        <%--                                                            <li><i class="lni lni-star-filled"></i></li>--%>
                        <%--                                                            <li><i class="lni lni-star-filled"></i></li>--%>
                        <%--                                                            <li><i class="lni lni-star-filled"></i></li>--%>
                        <%--                                                            <li><i class="lni lni-star-filled"></i></li>--%>
                        <%--                                                            <li><span>5.0 Review(s)</span></li>--%>
                        <%--                                                        </ul>--%>
                        <%--                                                        <div class="price">--%>
                        <%--                                                            <c:if test="${not empty product.discount}">--%>
                        <%--                                                        <span class="discount-price"><fmt:formatNumber type="number"--%>
                        <%--                                                                                                       value="${product.unitPrice}"/> VNĐ</span><br>--%>
                        <%--                                                            </c:if>--%>
                        <%--                                                            <span><fmt:formatNumber type="number"--%>
                        <%--                                                                                    value="${product.unitPrice - (product.unitPrice*product.discount.saleOff)/100}"/> VNĐ</span>--%>
                        <%--                                                        </div>--%>
                        <%--                                                    </div>--%>
                        <%--                                                </div>--%>
                        <%--                                            </div>--%>
                        <%--                                        </div>--%>
                        <%--                                    </c:forEach>--%>
                        <%--                                </div>--%>
                        <%--                            </div>--%>
                        <%--                            <div class="row">--%>
                        <%--                                <div class="col-12">--%>
                        <%--                                    <div class="pagination center">--%>
                        <%--                                        <ul class="pagination-list">--%>
                        <%--                                            <li><a href="/product/product-list?p=0"><i--%>
                        <%--                                                    class="lni lni-chevron-left"></i></a></li>--%>
                        <%--                                            <li><a href="/product/product-list?p=${page.number-1}"><i class="fa-solid fa-chevrons-left"></i></a></li>--%>
                        <%--                                            <li><a href="/product/product-list?p=${page.number+1}"><i class="fa-solid fa-chevrons-right"></i></a></li>--%>
                        <%--                                            <li><a href="/product/product-list?p=${page.totalPages-1}"><i--%>
                        <%--                                                    class="lni lni-chevron-right"></i></a></li>--%>
                        <%--                                        </ul>--%>
                        <%--                                    </div>--%>
                        <%--                                </div>--%>
                        <%--                            </div>--%>

                        <%--                        </div>--%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>