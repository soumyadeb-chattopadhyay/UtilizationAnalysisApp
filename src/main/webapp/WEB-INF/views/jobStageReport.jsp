<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="link.jsp" %>
<fmt:setBundle basename="report" />
<html>
	<head>
		<title><fmt:message key="welcome.header.jobStage.report"/></title>
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
		      <li class="current"><fmt:message key="breadcrumb.tag.jobStage.report"/></li>
		    </ul>
		</div>
		<div id="maincontainer">
			<h2><fmt:message key="welcome.header.jobStage.report"/></h2>
			<div id="tablediv">
				<display:table name="jobStageDTOs" class="report" decorator="org.displaytag.decorator.TotalTableDecorator" export="true" requestURI="${jobStageReportUrl}">
				    <display:column property="cd" title="Sub Cd" group="1" />
					<display:column property="month" title="Month"/>
					<display:column property="targetHours" title="Target Hours"/>
					<display:column property="recordedHours" title="Recorded Hours"/>
					<display:column property="percentage" format="{0,number,0.00} %"/>
		  		</display:table>
			</div>
			<br class="clear" />
		</div>
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>