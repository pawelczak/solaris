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
	   			<a href="javascript:formSubmit()" class="" ><spring:message code="header.logout" /></a>
	   			
	   			<script>
					function formSubmit() {
						document.getElementById("logoutForm").submit();
					}
				</script>
	   			
   				<form action="${contextPath}/admin/login?logout" method="post" id="logoutForm">
					<input type="hidden" 
						name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
			</div>
		</div>
	
	</div>
</nav>