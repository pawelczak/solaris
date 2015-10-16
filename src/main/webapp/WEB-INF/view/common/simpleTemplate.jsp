<%@page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>

	<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="session"></c:set>

	<head>
		<meta charset="UTF-8" />
		
	</head>
	<body>
	    
	    <tiles:insertAttribute name="content" flush="false" />
	</body>
</html>

