<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Sidenav Light - SB Admin</title>
<%@include file="/common/cssAdmin.jsp"%>
</head>
<body class="sb-nav-fixed">
	<tiles:insertAttribute name="navbarAdmin" />
	<div id="layoutSidenav">
		<tiles:insertAttribute name="sidebarAdmin" />
		<div id="layoutSidenav_content">
			<main
				class="d-flex flex-column align-items-center justify-content-center container"
				style="min-height: 75vh">
				<tiles:insertAttribute name="bodyAdmin" />
			</main>
			<tiles:insertAttribute name="footerAdmin" />
		</div>
	</div>
	<%@include file="/common/jsAdmin.jsp"%>
</body>
</html>
