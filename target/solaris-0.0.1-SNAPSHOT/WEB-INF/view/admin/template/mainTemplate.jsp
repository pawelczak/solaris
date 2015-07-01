<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml" data-ng-app="solarisAdmin" >
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><tiles:insertAttribute name="title" /> - Canary project</title>
	
	
	
	<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="session" ></c:set> 
	<script>
		var contextPath = "${contextPath}";
	</script>
	
	 
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" />
	
	<link rel="stylesheet" type="text/css" href="${contextPath}/static/stylesheets/css/admin-styles.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/static/stylesheets/css/common.css" />
	
	<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	
	<!-- Angularjs -->
	<script src="${contextPath}/static/angularjs/angular.min.js" type="text/javascript" ></script>
	<script src="${contextPath}/static/angularjs/angular-route.min.js" type="text/javascript" ></script>
	
	
	<!-- Angularjs APP-->
	<script src="${contextPath}/static/angularjs/admin-app.js" type="text/javascript" ></script>
	
	<!-- Angularjs Controllers -->
	<script src="${contextPath}/static/angularjs/controller/solarisAdminCtrl.js" type="text/javascript" ></script>
	<script src="${contextPath}/static/angularjs/controller/solarisAdminGalleryCtrl.js" type="text/javascript" ></script>
	<script src="${contextPath}/static/angularjs/controller/solarisAdminPhotoCtrl.js" type="text/javascript" ></script>
	<script src="${contextPath}/static/angularjs/controller/solarisAdminArticleCtrl.js" type="text/javascript" ></script>

	
</head>
<body data-ng-controller="solarisAdminCtrl">
	
	<!-- Header -->
	<tiles:insertAttribute name="header" />

	<div class="container admin-panel" >

		<div class="row row-eq-height">
	
			<div class="col-sm-2 navigation" >
			
				<div class="top-block">Main navigation</div>
				
				<ul>
					<li><a href="#/" >Dashboard</a></li>
					<li><a href="#/gallery" >Galleries</a></li>
					<li><a href="#/photo" >Photos</a></li>
					<li><a href="#/article" >Articles</a></li>	
					
				
				</ul>
			
			</div>
			<div class="col-sm-10 content" >
				
				
				<!-- Body Page -->
				<div >
					<%--
					<tiles:insertAttribute name="body" />
					 --%>
					
					<ng-view />
				</div>
				
			</div>
	
		</div>
		
	</div>
	
	<!-- Footer Page -->
	<tiles:insertAttribute name="footer" />
</body>
</html>