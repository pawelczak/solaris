<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp" %>


<div class="showcase" >
	 
	 <%--
	 <gallery:jssorCarousel photos="${carouselPhotos}"></gallery:jssorCarousel>
	 --%>

</div>


<div class="container" >
	<div class="sh-header">
		<div><spring:message code="site.home.aboutMe" /></div>
	</div>	
	<div class="sh-desc">
	
		<div class="sh-canvas" >
		
			<p>Fotografia stała się moją pasją, odtąd wykonałem samodzielnie zdjęcia pierwszego syna w 1986 roku. Chociaż już wcześniej ojciec wprowadził mnie w magiczny świat fotografii, to dopiero wtedy zdałem sobie sprawę, że odtąd będę już zawsze dźwigał ciężki sprzęt na kolejnych wyprawach w góry. Fascynuje mnie krajobraz. Lubię spojrzenie teleobiektywów i obiektywów szerokątnych. W moich fotografiach pragnę uchwycić to wrażnie potęgi przyrody jakie odczuwa się będąc gdzieś wysoko na górskiej ścieżce. </p>
		
			<p>W dążeniach do doskonałości kieruję się klasycznymi zasadami kompozycji obrazu i wierności oddania barw. Chciałbym kiedyś osiągnąć poziom niedoścignionych perfekcjonistów takich jak David Noton i Jeremy Walker. Mam nadzieję, że kiedyś go osiągnę. </p>
		
		</div>
	
		<div class="sh-images row" >
			<div class="col-md-4" >
				<img src="${contextPath}/static/site/images/home/home-image-1.jpg" />
			</div>
			<div class="col-md-4" >
				<img src="${contextPath}/static/site/images/home/home-image-2.jpg" />
			</div>
			<div class="col-md-4" >
				<img src="${contextPath}/static/site/images/home/home-image-3.jpg" />
			</div>
		</div>
	
	</div>

</div>

<!-- 
<div class="sh-header">
	<div>Polecane</div>
</div>
 -->

<div class="sh-full" >
	<div class="container" >
		<div class="sh-bottom row " >
			<div class="col-md-4" >
				<div class="teaser" >
					<a href="${contextPath}/featured">
						<div class="block featured"></div>
					</a>
					
					<p><spring:message code="site.home.featured" /></p>
					<p>Lorem ipsum dolor sit amet, conse ctetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis </p>
				</div>
			</div>
			<div class="col-md-4" >
				<div class="teaser" >
					<a href="${contextPath}/gallery">
						<div class="block gallery"></div>
					</a>
				
					<p><spring:message code="site.home.gallery" /></p>
					<p>Lorem ipsum dolor sit amet, conse ctetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis </p>
				</div>
			</div>
			<div class="col-md-4" >
				<div class="teaser" >
					<a href="${contextPath}/contact">
						<div class="block contact"></div>
					</a>
					
					<p><spring:message code="site.home.contact" /></p>
					<p>Lorem ipsum dolor sit amet, cons ectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis </p>
				</div>
			</div>
	
		</div>
	</div>

</div>


<%--
<gallery:section headerLabel="home.gallery.mostPopular" galleries="${mostPopularGalleries}" ></gallery:section>
<gallery:section headerLabel="home.gallery.lastAdded" galleries="${lastAddedGalleries}" ></gallery:section>
  --%>
  
  
 

 
