<%--
  Created by IntelliJ IDEA.
  User: haiph
  Date: 2/20/2022
  Time: 11:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibAdmin.jsp" %>
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Trang Thống Kê</h1>
        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
    </div>

    <!-- Content Row -->
    <div class="row">

        <!-- Earnings (Monthly) Card Example -->
        <div class="col-xl-3 col-md-6 mb-4">
            <div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                Tổng Doanh Thu
                            </div>
                            <div class="h5 mb-0 font-weight-bold text-gray-800"><fmt:formatNumber type="number" value="${totalRevenue}"/> VNĐ</div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Earnings (Monthly) Card Example -->
        <div class="col-xl-3 col-md-6 mb-4">
            <div class="card border-left-success shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                Đơn Hàng Mới
                            </div>
                            <div class="h5 mb-0 font-weight-bold text-gray-800">${newOrders}</div>
                        </div>
                        <div class="col-auto">
                            <i class="fa-solid fa-comment-plus fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Earnings (Monthly) Card Example -->
        <div class="col-xl-3 col-md-6 mb-4">
            <div class="card border-left-info shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Tổng Đơn Hàng
                            </div>
                            <div class="row no-gutters align-items-center">
                                <div class="col-auto">
                                    <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">${totalOrder}</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Pending Requests Card Example -->
        <div class="col-xl-3 col-md-6 mb-4">
            <div class="card border-left-warning shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                Đơn Hàng Đã Giao
                            </div>
                            <div class="h5 mb-0 font-weight-bold text-gray-800">${orderDelivered}</div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-comments fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Content Row -->
</div>
<!-- /.container-fluid -->
<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="card">
    <div class="card-header">
        <h2 class="card-title">Danh sách sản phẩm</h2>
        <c:if test="${not empty message}">
            <div class="alert alert-success" role="alert">
                    ${message}
            </div>
        </c:if>
    </div>
    <!-- /.card-header -->
    <div class="card-body">
        <div class="container-fluid">
            <!-- Small boxes (Stat box) -->

            <!-- /.row -->
            <!-- Main row -->
            <div class="row">
                <div class="col-md-6">
                    <div class="card card-gray">
                        <div class="card-header">
                            <h3 class="card-title">Doanh thu theo loại</h3>
                        </div>
                        <!-- /.card-header -->
                        <div class="card-body">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th style="width: 10px">STT</th>
                                    <th>Danh mục</th>
                                    <th>Phần trăm</th>
                                    <th style="width: 40px">Tổng tiền</th>
                                </tr>
                                </thead>
                                <tbody>

                                <tr>
                                    <td>1.</td>
                                    <td>Điện thoại</td>
                                    <td>
                                        <div class="progress progress-xs">
                                            <div class="progress-bar progress-bar-danger" style="width: ${phamtram0}%"></div>
                                        </div>
                                        <span>${phamtram0}%</span>
                                    </td>
                                    <td><span class="badge bg-danger"><fmt:formatNumber value="${totaldetail}" type="number"/></span></td>
                                </tr>
                                <tr>
                                    <td>2.</td>
                                    <td>Đồng hồ  thông minh</td>
                                    <td>
                                        <div class="progress progress-xs">
                                            <div class="progress-bar bg-warning" style="width: ${phamtram3}%"></div>
                                        </div>
                                        <span>${phamtram3}%</span>
                                    </td>
                                    <td><span class="badge bg-warning"><fmt:formatNumber value="${totaldetail3}" type="number"/></span></td>
                                </tr>
                                <tr>
                                    <td>3.</td>
                                    <td>	Máy tính bảng</td>
                                    <td>
                                        <div class="progress progress-xs progress-striped active">
                                            <div class="progress-bar bg-primary" style="width: ${phamtram1}%"></div>
                                        </div>
                                        <span>${phamtram1}%</span>
                                    </td>
                                    <td><span class="badge bg-primary"><fmt:formatNumber value="${totaldetail1}" type="number"/></span></td>
                                </tr>
                                <tr>
                                    <td>4.</td>
                                    <td>Laptop</td>
                                    <td>
                                        <div class="progress progress-xs progress-striped active">

                                            <div class="progress-bar bg-success" style="width: ${phamtram2}%"></div>

                                        </div>
                                        <span>${phamtram2}%</span>
                                    </td>
                                    <td><span class="badge bg-success"><fmt:formatNumber value="${totaldetail2}" type="number"/></span></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.card-body -->
                        <div class="card-footer clearfix">
                            <ul class="pagination pagination-sm m-0 float-right">
                                <li class="page-item"><a class="page-link" href="#">&laquo;</a></li>
                                <li class="page-item"><a class="page-link" href="#">1</a></li>
                                <li class="page-item"><a class="page-link" href="#">2</a></li>
                                <li class="page-item"><a class="page-link" href="#">3</a></li>
                                <li class="page-item"><a class="page-link" href="#">&raquo;</a></li>
                            </ul>
                        </div>
                    </div>
                    <!-- /.card -->


                    <!-- /.card -->
                </div>
                <div class="col-md-6">
                    <div class="card card-danger">
                        <div class="card-header">
                            <h3 class="card-title">Doanh thu theo loại</h3>
                        </div>
                        <!-- /.card-header -->
                        <div class="card-body">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th style="width: 10px">STT</th>
                                    <th >Danh mục</th>
                                    <th>Phần trăm</th>
                                    <th style="width: 40px">Tổng tiền</th>
                                </tr>
                                </thead>
                                <tbody>

                                <tr>
                                    <td>1.</td>
                                    <td>Điện thoại</td>
                                    <td>
                                        <div class="progress progress-xs">
                                            <div class="progress-bar progress-bar-danger" style="width: ${phamtram0}%"></div>
                                        </div>
                                        <span>${phamtram0}%</span>
                                    </td>
                                    <td><span class="badge bg-danger"><fmt:formatNumber value="${totaldetail}" type="number"/></span></td>
                                </tr>
                                <tr>
                                    <td>2.</td>
                                    <td>Đồng hồ  thông minh</td>
                                    <td>
                                        <div class="progress progress-xs">
                                            <div class="progress-bar bg-warning" style="width: ${phamtram3}%"></div>
                                        </div>
                                        <span>${phamtram3}%</span>
                                    </td>
                                    <td><span class="badge bg-warning"><fmt:formatNumber value="${totaldetail3}" type="number"/></span></td>
                                </tr>
                                <tr>
                                    <td>3.</td>
                                    <td>	Máy tính bảng</td>
                                    <td>
                                        <div class="progress progress-xs progress-striped active">
                                            <div class="progress-bar bg-primary" style="width: ${phamtram1}%"></div>
                                        </div>
                                        <span>${phamtram1}%</span>
                                    </td>
                                    <td><span class="badge bg-primary"><fmt:formatNumber value="${totaldetail1}" type="number"/></span></td>
                                </tr>
                                <tr>
                                    <td>4.</td>
                                    <td>Laptop</td>
                                    <td>
                                        <div class="progress progress-xs progress-striped active">

                                            <div class="progress-bar bg-success" style="width: ${phamtram2}%"></div>

                                        </div>
                                        <span>${phamtram2}%</span>
                                    </td>
                                    <td><span class="badge bg-success"><fmt:formatNumber value="${totaldetail2}" type="number"/></span></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.card-body -->
                        <div class="card-footer clearfix">
                            <ul class="pagination pagination-sm m-0 float-right">
                                <li class="page-item"><a class="page-link" href="#">&laquo;</a></li>
                                <li class="page-item"><a class="page-link" href="#">1</a></li>
                                <li class="page-item"><a class="page-link" href="#">2</a></li>
                                <li class="page-item"><a class="page-link" href="#">3</a></li>
                                <li class="page-item"><a class="page-link" href="#">&raquo;</a></li>
                            </ul>
                        </div>
                    </div>
                    <!-- /.card -->


                    <!-- /.card -->
                </div>
                <!-- /.col -->

                <div class="col-md-12">
                    <div class="card card-blue">
                        <div class="card-header">
                            <h3 class="card-title">Trạng thái đơn hàng</h3>
                        </div>
                        <!-- /.card-header -->
                        <div class="card-body">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th style="width: 10px">STT</th>
                                    <th style="width: 10px">Order Code</th>
                                    <th  style="width: 10px">Họ và tên</th>

                                    <th style="width: 40px">Trạng thái</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="items" items="${ListOrders}">
                                    <tr>
                                        <td>${items.id}</td>
                                        <td>${items.orderCode}</td>
                                        <td>${items.user.fullname}</td>

                                        <td>${items.status==0?"Đang giao":"Đã giao"}</td>
                                    </tr>
                                </c:forEach>


                                </tbody>
                            </table>
                        </div>
                        <!-- /.card-body -->
                        <div class="card-footer clearfix">
                            <ul class="pagination pagination-sm m-0 float-right">
                                <li class="page-item"><a class="page-link" href="#">&laquo;</a></li>
                                <li class="page-item"><a class="page-link" href="#">1</a></li>
                                <li class="page-item"><a class="page-link" href="#">2</a></li>
                                <li class="page-item"><a class="page-link" href="#">3</a></li>
                                <li class="page-item"><a class="page-link" href="#">&raquo;</a></li>
                            </ul>
                        </div>
                    </div>
                    <!-- /.card -->


                    <!-- /.card -->
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row (main row) -->
        </div>
    </div>
    <!-- /.card-body -->
</div>


