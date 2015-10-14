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
  
  	<%--
  	<div class="navbar-brand" >
	  	<a href="#" >Pawelczak.pl Admin panel</a>
	  	<a href="${contextPath}/" class="" >View website</a>
	   	<span>
	   		You're logged in as <b>Admin</b>. 
	   	</span>
	   	<a href="${contextPath}/" class="" >Log out</a>
   	</div>
   	 --%>
  
  <%--
    <div class="navbar-header">
      <button aria-controls="navbar" aria-expanded="false" data-target="#navbar" data-toggle="collapse" class="navbar-toggle collapsed" type="button">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a href="#" class="navbar-brand">Pawelczak.pl Admin panel</a>
    </div>
    <div class="navbar-collapse collapse" id="navbar">
    	<a href="${contextPath}/" class="">View website</a>
    	<span>
    		You're logged in as <b>Admin</b>. 
    	</span>
    	<a href="${contextPath}/" class="">Log out</a>
    </div><!--/.nav-collapse -->
    
    
     --%>
  </div>
</nav>