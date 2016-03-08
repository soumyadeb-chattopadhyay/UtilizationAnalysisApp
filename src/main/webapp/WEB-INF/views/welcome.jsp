<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="link.jsp" %>
<fmt:setBundle basename="report" />
<html>
<head profile="http://gmpg.org/xfn/11">
<title>Welcome</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/layout.css" type="text/css" />
</head>
<body id="top">
	<div class="wrapper">
		<jsp:include page="header.jsp"/>
		<div id="breadcrumb">
		    <ul>
		      <li class="first"><fmt:message key="breadcrumb.tag"/></li>
		      <li>&#187;</li>
		      <li class="current"><fmt:message key="breadcrumb.tag.home"/></li>
		    </ul>
		</div>
		<div id="maincontainer">
			<h2>&nbsp;&nbsp;&nbsp;&nbsp;Available Reports</h2>
			<ul>
				<li style="font-size: 18px"><a href='<c:out value="${monthlyReportUrl}"/>'><fmt:message key="welcome.header.monthly.report"/></a></li>
				<li style="font-size: 18px"><a href='<c:out value="${eriproSubDomainReportUrl}"/>'><fmt:message key="welcome.header.eripro.sub.domain.report"/></a></li>
				<li style="font-size: 18px"><a href='<c:out value="${eriproSubCdTypePercentageReportUrl}"/>'><fmt:message key="welcome.header.eripro.subCdType.percentage.report"/></a></li>
				<li style="font-size: 18px"><a href='<c:out value="${jobStageReportUrl}"/>'><fmt:message key="welcome.header.jobStage.report"/></a></li>
			</ul>
			<br class="clear" />
		</div>
		<jsp:include page="footer.jsp"/>
	</div>
</body>
</html>

