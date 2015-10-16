<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml" data-ng-app="solarisSite" lang="<spring:message code='site.lang' />" >
	<head>
		<meta charset="UTF-8" />
		<meta http-equiv="Content-Type" content="text/html" />
		<meta name="robots" content="noindex, nofollow" >
		
		<tiles:insertAttribute name="meta" />  
		
		<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="session" /> 
		<script>
		var contextPath = "${contextPath}";
		</script>  
		     
		
		<%-- Font --%>     
		<link type="text/css" rel="stylesheet" href="//fonts.googleapis.com/css?family=Noto+Sans:200,300,400,400italic,700,700italic&amp;subset=latin,latin-ext,cyrillic-ext">
		
		<%-- CSS --%>
		<link rel="stylesheet" type="text/css" href="${contextPath}/static/common/stylesheets/css/bootstrap.min.css" />
		
		<link rel="stylesheet" type="text/css" href="${contextPath}/static/site/stylesheets/css/styles.css" />
		
	</head>
	<body>
		
		<tiles:insertAttribute name="header" />
		
		<tiles:insertAttribute name="body" flush="false" />
		
		<tiles:insertAttribute name="footer" />
		
	
		<%-- Javascript --%>	
		<script src="${contextPath}/static/common/javascript/jquery.min.js" type="text/javascript" ></script>
		
		<script src="${contextPath}/static/common/javascript/bootstrap.min.js"></script>
	
		<%-- AngularJs --%>
		<tiles:insertAttribute name="angularjsLib" />  
		
	</body>
</html>

