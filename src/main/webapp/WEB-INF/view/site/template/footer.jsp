<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp" %>


<div class="footer" >
	<div class="container">
	
		<div class="sitemap">
			<ul class="nav navbar-nav navbar-right">
				<li ><a href="${contextPath}/" ><spring:message code="site.navigation.home" /></a></li>
				<li><a href="${contextPath}/gallery" ><spring:message code="site.navigation.gallery" /></a></li>
				<li><a href="${contextPath}/links" ><spring:message code="site.navigation.links" /></a></li>
				<li><a href="${contextPath}/contact" ><spring:message code="site.navigation.contact" /></a></li>
			</ul>
		</div>
		
		<div class="info"><spring:message code="site.footer.copyrights" /></div>		
	
	</div>
</div>
	
	