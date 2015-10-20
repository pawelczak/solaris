<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml" data-ng-app="solarisAdmin" >
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><tiles:insertAttribute name="title" /> - Solaris project</title>
	

	<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="session" /> 
	<script>
		var contextPath = "${contextPath}";
	</script>
	
	 
	<link type="text/css" rel="stylesheet" href="//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,700,700italic&amp;subset=latin,cyrillic-ext,greek-ext,latin-ext,cyrillic">
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" type="text/css" href="${contextPath}/static/common/stylesheets/css/bootstrap.min.css" />
	
	<link rel="stylesheet" type="text/css" href="${contextPath}/static/admin/stylesheets/css/admin-styles.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/static/admin/stylesheets/css/common.css" />
	
	<script src="${contextPath}/static/common/javascript/jquery.min.js" type="text/javascript" ></script>
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="${contextPath}/static/common/javascript/bootstrap.min.js"></script>
	
	<jsp:include page="angularJsLibs.jsp" />

</head>
<body data-ng-controller="adminController" >
	
	<!-- Header -->
	<tiles:insertAttribute name="header" />

	<div class="container admin-panel" >

		<div class="row row-eq-height">
	
			<div class="col-sm-2 navigation" >
			
				<div class="top-block"><spring:message code="navigation.header" /></div>
				
				<ul>
					<li data-ng-class="getSelecteViewClassName('dashboard')" ><a href="${contextPath}/admin/#/" ><spring:message code="navigation.dashboard" /></a></li>
					<li data-ng-class="getSelecteViewClassName('gallery')" ><a href="${contextPath}/admin/#/gallery" ><spring:message code="navigation.galleries" /></a></li>
					<li data-ng-class="getSelecteViewClassName('photo')" ><a href="${contextPath}/admin/#/photo" ><spring:message code="navigation.photos" /></a></li>
					<li data-ng-class="getSelecteViewClassName('article')" ><a href="${contextPath}/admin/#/article" ><spring:message code="navigation.articles" /></a></li>	
					<li><a href="${contextPath}/" target="_blank" ><spring:message code="navigation.website" /></a></li>
				</ul>
			
			</div>
			<div class="col-sm-10 content" >
				
				<!-- Body Page -->
				<div >
				
					<ng-view />
						
				</div>
				
			</div>
	
		</div>
		
	</div>
	
	<!-- Footer Page -->
	<tiles:insertAttribute name="footer" />
</body>
</html>