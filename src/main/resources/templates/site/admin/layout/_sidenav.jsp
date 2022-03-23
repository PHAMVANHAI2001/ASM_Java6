<%--
  Created by IntelliJ IDEA.
  User: haiph
  Date: 2/20/2022
  Time: 11:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="layoutSidenav_nav">
    <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
        <div class="sb-sidenav-menu">
            <div class="nav">
                <div class="sb-sidenav-menu-heading">Core</div>
                <a class="nav-link" href="/admin">
                    <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                    Dashboard
                </a>
                <div class="sb-sidenav-menu-heading">Interface</div>
                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts"
                   aria-expanded="false" aria-controls="collapseLayouts">
                    <div class="sb-nav-link-icon"><i class="fa-solid fa-user-pen"></i></div>
                    Quản Lý người dùng
                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne"
                     data-bs-parent="#sidenavAccordion">
                    <nav class="sb-sidenav-menu-nested nav">
                        <a class="nav-link" href="/admin/list-user">Danh sách người dùng</a>
                        <a class="nav-link" href="/admin/edit-user">Cập nhật người dùng</a>
                    </nav>
                </div>
                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts2"
                   aria-expanded="false" aria-controls="collapseLayouts2">
                    <div class="sb-nav-link-icon"><i class="fa-brands fa-apple"></i></div>
                    Quản Lý Sản Phẩm
                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse" id="collapseLayouts2" aria-labelledby="headingOne"
                     data-bs-parent="#sidenavAccordion">
                    <nav class="sb-sidenav-menu-nested nav">
                        <a class="nav-link" href="/admin/list-product">Danh sách sản phẩm</a>
                        <a class="nav-link" href="/admin/edit-product">Cập nhật sản phẩm</a>
                    </nav>
                </div>
                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts3"
                   aria-expanded="false" aria-controls="collapseLayouts3">
                    <div class="sb-nav-link-icon"><i class="fa-solid fa-list"></i></div>
                    Quản Lý Danh Mục
                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse" id="collapseLayouts3" aria-labelledby="headingOne"
                     data-bs-parent="#sidenavAccordion">
                    <nav class="sb-sidenav-menu-nested nav">
                        <a class="nav-link" href="/admin/list-categories">Danh sách danh mục</a>
                        <a class="nav-link" href="/admin/edit-categories">Cập nhật danh mục</a>
                    </nav>
                </div>
                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts4"
                   aria-expanded="false" aria-controls="collapseLayouts4">
                    <div class="sb-nav-link-icon"><i class="fa-solid fa-tags"></i></div>
                    Quản Lý Giảm Giá
                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse" id="collapseLayouts4" aria-labelledby="headingOne"
                     data-bs-parent="#sidenavAccordion">
                    <nav class="sb-sidenav-menu-nested nav">
                        <a class="nav-link" href="/admin/list-saleoff">Danh sách giảm giá</a>
                        <a class="nav-link" href="/admin/edit-saleoff">Cập nhật giảm giá</a>
                    </nav>
                </div>
                <%--                <div class="sb-sidenav-menu-heading">Addons</div>--%>
                <%--                <a class="nav-link" href="charts.html">--%>
                <%--                    <div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></div>--%>
                <%--                    Charts--%>
                <%--                </a>--%>
                <%--                <a class="nav-link" href="tables.html">--%>
                <%--                    <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>--%>
                <%--                    Tables--%>
                <%--                </a>--%>
            </div>
        </div>
        <%--        <div class="sb-sidenav-footer">--%>
        <%--            <div class="small">Logged in as:</div>--%>
        <%--            Start Bootstrap--%>
        <%--        </div>--%>
    </nav>
</div>
