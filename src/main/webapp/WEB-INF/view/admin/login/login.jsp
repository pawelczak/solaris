<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html >
	<head>
	  	
		<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="session"></c:set>  
		   
		
		 
		<link type="text/css" rel="stylesheet" href="//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,700,700italic&amp;subset=latin,cyrillic-ext,greek-ext,latin-ext,cyrillic">
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" type="text/css" href="${contextPath}/static/common/stylesheets/css/bootstrap.min.css" />
		
		<link rel="stylesheet" type="text/css" href="${contextPath}/static/admin/stylesheets/css/admin-styles.css" />
		<link rel="stylesheet" type="text/css" href="${contextPath}/static/admin/stylesheets/css/common.css" />
		
		<script src="${contextPath}/static/common/javascript/jquery.min.js" type="text/javascript" ></script>
		
		<!-- Latest compiled and minified JavaScript -->
		<script src="${contextPath}/static/common/javascript/bootstrap.min.js"></script>
		
		
		
		<title><spring:message code="login.title" /></title>
	
	</head>

	<body>
		<div class="container" >
		
			<form role="form" class="form-login" name="loginForm" action="${contextPath}/admin/login" method="POST">
			
				<h1 class="form-login-heading"><spring:message code="login.header" /></h1>
				
				<input type="text" name="username" autofocus="" placeholder="<spring:message code='login.name' />" class="form-control">
				<input type="password" name="password" placeholder="<spring:message code='login.passw' />" class="form-control">
				
				<div class="checkbox">
					<label>
						<input type="checkbox" value="remember-me"> <spring:message code="login.remember" />
					</label>
				</div>
				
				<input class="btn btn-lg btn-primary btn-block" name="submit" type="submit" value="<spring:message code="login.button.text" />" />
				 
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
			
		</div>
	</body>
</html>

