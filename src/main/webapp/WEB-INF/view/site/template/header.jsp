<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp" %>


<header class="navbar navbar-fixed-top" >
	<div class="container" >
		<nav>
			<div class="logo navbar-header" ><a class="navbar-brand" href="${contextPath}/" >Waldemar Pawełczak</a></div>
		
			<ul class="nav navbar-nav navbar-right" >
				<li ><a href="${contextPath}/" ><spring:message code="site.navigation.main" /></a></li>
				<li><a href="${contextPath}/photo/" ><spring:message code="site.navigation.photos" /></a></li>
				<li><a href="${contextPath}/contact" ><spring:message code="site.navigation.contact" /></a></li>
			</ul>
		
		</nav>
	
	</div>
</header>


