<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml" data-ng-app="solarisSite"  >
<head>
	<meta charset="UTF-8" />
	<meta http-equiv="Content-Type" content="text/html" />
	<meta name="robots" content="noindex, nofollow" >
	
	<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="session" /> 
	<script>
	var contextPath = "${contextPath}";
	</script>  
	     
	
	<!-- Font -->     
	<link type="text/css" rel="stylesheet" href="http://fonts.googleapis.com/css?family=Noto+Sans:200,300,400,400italic,700,700italic&amp;subset=latin,latin-ext,cyrillic-ext">
	
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" type="text/css" href="${contextPath}/static/common/stylesheets/css/bootstrap.min.css" />
	
	<link rel="stylesheet" type="text/css" href="${contextPath}/static/site/stylesheets/css/styles.css" />
	
	
	<script src="${contextPath}/static/common/javascript/jquery.min.js" type="text/javascript" ></script>
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="${contextPath}/static/common/javascript/bootstrap.min.js"></script>

	<tiles:insertAttribute name="angularjsLib" />  
	
</head>
<body>
	
	<tiles:insertAttribute name="header" />
	
	<tiles:insertAttribute name="body" flush="false" />
	
	<tiles:insertAttribute name="footer" />
	
</body>
</html>