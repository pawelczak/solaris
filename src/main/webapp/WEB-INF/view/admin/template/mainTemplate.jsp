<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml" data-ng-app="solarisAdmin" >
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><tiles:insertAttribute name="title" /> - Canary project</title>
	
	
	
	<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="session" /> 
	<script>
		var contextPath = "${contextPath}";
	</script>
	
	 
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" type="text/css" href="${contextPath}/static/stylesheets/css/bootstrap.min.css" />
	
	<link rel="stylesheet" type="text/css" href="${contextPath}/static/stylesheets/css/admin-styles.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/static/stylesheets/css/common.css" />
	
	<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="${contextPath}/static/javascript/bootstrap.min.js"></script>
	
	<jsp:include page="angularJsLibs.jsp" />

</head>
<body data-ng-controller="solarisAdminCtrl">
	
	<!-- Header -->
	<tiles:insertAttribute name="header" />

	<div class="container admin-panel" >

		<div class="row row-eq-height">
	
			<div class="col-sm-2 navigation" >
			
				<div class="top-block"><spring:message code="navigation.header" /></div>
				
				<ul>
					<li><a href="#/" ><spring:message code="navigation.dashboard" /></a></li>
					<li><a href="#/gallery" ><spring:message code="navigation.galleries" /></a></li>
					<li><a href="#/photo" ><spring:message code="navigation.photos" /></a></li>
					<li><a href="#/article" ><spring:message code="navigation.articles" /></a></li>	
					
				
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