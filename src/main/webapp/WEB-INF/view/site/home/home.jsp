<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp" %>


<div class="container showcase" >

	<img src="${contextPath}/static/site/images/home/showcase.jpg" />
</div>


<div class="container" >
	<div class="sh-header">
		<div><spring:message code="site.home.aboutMe" /></div>
	</div>	
	<div class="sh-desc">
	
		<div class="home-text" >
		
			<p><spring:message code="site.home.welcomeText.partOne" /></p>
		
			<p><spring:message code="site.home.welcomeText.partTwo" /></p>
		
		</div>
	
		<div class="home-images row" >
			<div class="col-xs-4" >
				<img src="${contextPath}/static/site/images/home/home-image-1.jpg" />
			</div>
			<div class="col-xs-4" >
				<img src="${contextPath}/static/site/images/home/home-image-2.jpg" />
			</div>
			<div class="col-xs-4" >
				<img src="${contextPath}/static/site/images/home/home-image-3.jpg" />
			</div>
		</div>
	
	</div>

</div>


<div class="sh-full" >
	<div class="container" >
		<div class="home-bottom row " >
			<div class="col-xs-4" >
				<div class="teaser" >
					<a href="${contextPath}/featured">
						<div class="block featured"></div>
					</a>
					
					<p><spring:message code="site.home.featured" /></p>
					<p><spring:message code="site.home.featured.desc" /></p>
				</div>
			</div>
			<div class="col-xs-4" >
				<div class="teaser" >
					<a href="${contextPath}/photo">
						<div class="block photo"></div>
					</a>
				
					<p><spring:message code="site.home.photo" /></p>
					<p><spring:message code="site.home.photo.desc" /></p>
				</div>
			</div>
			<div class="col-xs-4" >
				<div class="teaser" >
					<a href="${contextPath}/contact">
						<div class="block contact"></div>
					</a>
					
					<p><spring:message code="site.home.contact" /></p>
					<p><spring:message code="site.home.contact.desc" /></p>
				</div>
			</div>
	
		</div>
	</div>

</div>


<%--
<gallery:section headerLabel="home.gallery.mostPopular" galleries="${mostPopularGalleries}" ></gallery:section>
<gallery:section headerLabel="home.gallery.lastAdded" galleries="${lastAddedGalleries}" ></gallery:section>
  --%>
  
  
 

 
