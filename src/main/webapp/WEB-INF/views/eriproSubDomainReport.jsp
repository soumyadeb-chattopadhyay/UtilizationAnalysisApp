<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="link.jsp" %>
<fmt:setBundle basename="report" />
<html>
	<head>
		<title><fmt:message key="welcome.header.eripro.sub.domain.report"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<link href="${pageContext.request.contextPath}/resources/css/displaytag.css" media="screen" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/resources/css/layout.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" />
		
	
	
</head>
<body id="top">
	<div class="wrapper">
		<jsp:include page="header.jsp" />
		<div id="breadcrumb">
		    <ul>
		      <li class="first"><fmt:message key="breadcrumb.tag"/></li>
		      <li>&#187;</li>
		      <li><a href='<c:out value="${homeUrl}"/>'><fmt:message key="breadcrumb.tag.home"/></a></li>
		      <li>&#187;</li>
		      <li class="current"><fmt:message key="breadcrumb.tag.eripro.sub.domain.report"/></li>
		    </ul>
		</div>
		<div id="maincontainer">
			<h2><fmt:message key="welcome.header.eripro.sub.domain.report"/></h2>
			<div id="tablediv">
				<display:table name="cdTypeDTOs" class="simple nocol" defaultsort="1" decorator="org.displaytag.decorator.TotalTableDecorator" export="true">
				    <display:column property="subCdType" group="1" />
					<display:column property="subCd" />
					<display:column property="month" sortable="true"/>
				   
				    <display:column property="targetHours" format="{0,number,0.00}" headerClass="r" class="r" total="true" />
				    <display:column property="recordedHours" format="{0,number,0.00}" headerClass="r" class="r" total="true" />
		  		</display:table>
			</div>
			<br class="clear" />
		</div>
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>