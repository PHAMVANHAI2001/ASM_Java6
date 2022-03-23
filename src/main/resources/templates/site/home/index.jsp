<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/head.jsp" %>
<!-- Start Hero Area -->
<section class="hero-area">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 col-12 custom-padding-right">
                <div class="slider-head">
                    <!-- Start Hero Slider -->
                    <div class="hero-slider">
                        <!-- Start Single Slider -->
                        <div class="single-slider"
                             style="background-image: url(/assets/user/images/hero/slider-bg1.jpg);">
                            <div class="content">
                                <h2>
                                    <span>No restocking fee ($35 savings)</span> M75 Sport Watch
                                </h2>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit,
                                    sed do eiusmod tempor incididunt ut labore et dolore magna
                                    aliqua.</p>
                                <h3>
                                    <span>Now Only</span> $320.99
                                </h3>
                                <div class="button">
                                    <a href="product-grids.html" class="btn">Shop Now</a>
                                </div>
                            </div>
                        </div>
                        <!-- End Single Slider -->
                        <!-- Start Single Slider -->
                        <div class="single-slider"
                             style="background-image: url(/assets/user/images/hero/slider-bg2.jpg);">
                            <div class="content">
                                <h2>
                                    <span>Big Sale Offer</span> Get the Best Deal on CCTV Camera
                                </h2>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit,
                                    sed do eiusmod tempor incididunt ut labore et dolore magna
                                    aliqua.</p>
                                <h3>
                                    <span>Combo Only:</span> $590.00
                                </h3>
                                <div class="button">
                                    <a href="product-grids.html" class="btn">Shop Now</a>
                                </div>
                            </div>
                        </div>
                        <!-- End Single Slider -->
                    </div>
                    <!-- End Hero Slider -->
                </div>
            </div>
        </div>
    </div>
</section>
<!-- End Hero Area -->

<!-- Start Trending Product Area -->
<section class="trending-product section" style="margin-top: 12px;">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="section-title">
                    <h2>Sản phẩm bán chạy</h2>
                    <!--      <p>There are many variations of passages of Lorem Ipsum available, but the majority have
                        suffered alteration in some form.</p> -->
                </div>
            </div>
        </div>
        <div class="row">
            <c:forEach var="product" items="${page.content}">
                <div class="col-lg-3 col-md-6 col-12 d-flex">
                    <!-- Start Single Product -->
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
                    <!-- End Single Product -->
                </div>
            </c:forEach>

        </div>
        <div class="row">
            <div class="col-12">
                <div class="pagination center">
                    <ul class="pagination-list">
                        <li><a href="/home?p=0"><i
                                class="lni lni-chevron-left"></i></a></li>
                        <li><a href="/home?p=${page.number-1}"><i class="fa-solid fa-chevrons-left"></i></a></li>
                        <li><a href="/home?p=${page.number+1}"><i class="fa-solid fa-chevrons-right"></i></a></li>
                        <li><a href="/home?p=${page.totalPages-1}"><i
                                class="lni lni-chevron-right"></i></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- End Trending Product Area -->

<!-- Start Shipping Info -->
<section class="shipping-info">
    <div class="container">
        <ul>
            <!-- Free Shipping -->
            <li>
                <div class="media-icon">
                    <i class="lni lni-delivery"></i>
                </div>
                <div class="media-body">
                    <h5>Miễn phí vận chuyển</h5>
                </div>
            </li>
            <!-- Money Return -->
            <li>
                <div class="media-icon">
                    <i class="lni lni-support"></i>
                </div>
                <div class="media-body">
                    <h5>Hỗ trợ 24/7.</h5>
                </div>
            </li>
            <!-- Support 24/7 -->
            <li>
                <div class="media-icon">
                    <i class="lni lni-credit-cards"></i>
                </div>
                <div class="media-body">
                    <h5>Thanh toán trực tuyến.</h5>
                </div>
            </li>
            <!-- Safe Payment -->
            <li>
                <div class="media-icon">
                    <i class="lni lni-reload"></i>
                </div>
                <div class="media-body">
                    <h5>Đổi trả dễ dàng.</h5>
                </div>
            </li>
        </ul>
    </div>
</section>
<!-- End Shipping Info -->
