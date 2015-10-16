<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp" %>


 
<header class="navbar navbar-fixed-top" >
	<div class="container">
		<div class="navbar-header">
			<button aria-controls="navbar" aria-expanded="false" data-target="#navbar" data-toggle="collapse" class="navbar-toggle collapsed" type="button">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			
			<div class="logo navbar-header" ><a class="navbar-brand" href="${contextPath}/" ><spring:message code="project.name" /></a></div>
		</div>
		<div class="navbar-collapse collapse" id="navbar" aria-expanded="false" style="height: 1px;">
			<ul class="nav navbar-nav navbar-right">
				<li ><a href="${contextPath}/" ><spring:message code="site.navigation.main" /></a></li>
				<li><a href="${contextPath}/photo/" ><spring:message code="site.navigation.photos" /></a></li>
				<li><a href="${contextPath}/contact" ><spring:message code="site.navigation.contact" /></a></li>
			</ul>
		</div>
	</div>
</header>
 
