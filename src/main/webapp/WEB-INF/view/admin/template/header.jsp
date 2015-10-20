<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp" %>


<nav class="navbar navbar-default">
	<div class="container">
	
		<div class="row" >
		
			<div class="col-sm-2 nav-left">
				<spring:message code="header.panel" />
			</div>
		
			<div class="col-sm-10 nav-right">
				<span>
	   				You're logged in as <b>Admin</b>. 
	   			</span>
	   			<a href="${contextPath}/" class="" ><spring:message code="header.logout" /></a>
			</div>
		</div>
	
	</div>
</nav>