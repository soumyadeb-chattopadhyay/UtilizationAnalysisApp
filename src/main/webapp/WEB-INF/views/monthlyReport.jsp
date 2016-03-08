<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="link.jsp" %>
<fmt:setBundle basename="report" />
<html>
	<head>
		<title><fmt:message key="welcome.header.monthly.report"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<link href="${pageContext.request.contextPath}/resources/css/layout.css" rel="stylesheet"  type="text/css" />
		<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" />
	
	 	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	
	
	 <script type="text/javascript">
	 
	 //alert("Hi");
	 
      google.charts.load('current', {'packages':['corechart']});
      
      google.charts.setOnLoadCallback(drawChartSDP);
      
      google.charts.setOnLoadCallback(drawChartTV);
      
      google.charts.setOnLoadCallback(drawChartECENGIN);
      
      google.charts.setOnLoadCallback(drawChartMSP);
      
      google.charts.setOnLoadCallback(drawChartRNAM);
      
      google.charts.setOnLoadCallback(drawChartMA);
      
      function drawChartSDP() {
     
    	  // Specify the data source URL.
      	  var query = new google.visualization.Query('subCdColumnChartChart?subCdType=SDP');
      	  // Send the query with a callback function.
      	  query.send(handleSimpleDsResponseSDP);
      }
      
      
   	  // Handle the query response.
      function handleSimpleDsResponseSDP(response) {
      if (response.isError()) {
      alert('Error in query: ' + response.getMessage() + ' ' + response.getDetailedMessage());
      return;
      }
      // Draw the visualization.
      var data = response.getDataTable();
      		
      		var options = {
      		      title : 'Utilization Analysis For SUB-CdType SDP',
      		      vAxis: {title: 'Hours'},
      		      hAxis: {title: 'Month'},
      		      seriesType: 'bars',
      		      series: {5: {type: 'line'}}
      		    };


     
	      var chart = new google.visualization.ComboChart(document.getElementById('columnchart_material_SDP'));
	
	      chart.draw(data, options);
      }
   	  
   	  
   	  
   	  
      function drawChartTV() {
    	     
    	  // Specify the data source URL.
      	  var query = new google.visualization.Query('subCdColumnChartChart?subCdType=TV');
      	  // Send the query with a callback function.
      	  query.send(handleSimpleDsResponseTV);
      }
      
      
   	  // Handle the query response.
      function handleSimpleDsResponseTV(response) {
      if (response.isError()) {
      alert('Error in query: ' + response.getMessage() + ' ' + response.getDetailedMessage());
      return;
      }
      // Draw the visualization.
      var data = response.getDataTable();
      		
      		var options = {
      		      title : 'Utilization Analysis For SUB-CdType TV',
      		      vAxis: {title: 'Hours'},
      		      hAxis: {title: 'Month'},
      		      seriesType: 'bars',
      		      series: {5: {type: 'line'}}
      		    };


     
	      var chart = new google.visualization.ComboChart(document.getElementById('columnchart_material_TV'));
	
	      chart.draw(data, options);
      }
   	  
   	  
   	  
      function drawChartECENGIN() {
    	     
    	  // Specify the data source URL.
      	  var query = new google.visualization.Query('subCdColumnChartChart?subCdType=ECE-NGIN');
      	  // Send the query with a callback function.
      	  query.send(handleSimpleDsResponseECENGIN);
      }
      
      
   	  // Handle the query response.
      function handleSimpleDsResponseECENGIN(response) {
      if (response.isError()) {
      alert('Error in query: ' + response.getMessage() + ' ' + response.getDetailedMessage());
      return;
      }
      // Draw the visualization.
      var data = response.getDataTable();
      		
      		var options = {
      		      title : 'Utilization Analysis For SUB-CdType ECE-NGIN',
      		      vAxis: {title: 'Hours'},
      		      hAxis: {title: 'Month'},
      		      seriesType: 'bars',
      		      series: {5: {type: 'line'}}
      		    };


     
	      var chart = new google.visualization.ComboChart(document.getElementById('columnchart_material_ECENGIN'));
	
	      chart.draw(data, options);
      }
   	  
      function drawChartMSP() {
    	     
    	  // Specify the data source URL.
      	  var query = new google.visualization.Query('subCdColumnChartChart?subCdType=MIO/MSP/MPS');
      	  // Send the query with a callback function.
      	  query.send(handleSimpleDsResponseMSP);
      }
      
      
   	  // Handle the query response.
      function handleSimpleDsResponseMSP(response) {
      if (response.isError()) {
      alert('Error in query: ' + response.getMessage() + ' ' + response.getDetailedMessage());
      return;
      }
      // Draw the visualization.
      var data = response.getDataTable();
      		
      		var options = {
      		      title : 'Utilization Analysis For SUB-CdType MIO/MSP/MPS',
      		      vAxis: {title: 'Hours'},
      		      hAxis: {title: 'Month'},
      		      seriesType: 'bars',
      		      series: {5: {type: 'line'}}
      		    };


     
	      var chart = new google.visualization.ComboChart(document.getElementById('columnchart_material_MSP'));
	
	      chart.draw(data, options);
      }
   	  
      function drawChartRNAM() {
    	     
    	  // Specify the data source URL.
      	  var query = new google.visualization.Query('subCdColumnChartChart?subCdType=RNAM');
      	  // Send the query with a callback function.
      	  query.send(handleSimpleDsResponseRNAM);
      }
      
      
   	  // Handle the query response.
      function handleSimpleDsResponseRNAM(response) {
      if (response.isError()) {
      alert('Error in query: ' + response.getMessage() + ' ' + response.getDetailedMessage());
      return;
      }
      // Draw the visualization.
      var data = response.getDataTable();
      		
      		var options = {
      		      title : 'Utilization Analysis For SUB-CdType RNAM',
      		      vAxis: {title: 'Hours'},
      		      hAxis: {title: 'Month'},
      		      seriesType: 'bars',
      		      series: {5: {type: 'line'}}
      		    };


     
	      var chart = new google.visualization.ComboChart(document.getElementById('columnchart_material_RNAM'));
	
	      chart.draw(data, options);
      }
   	  
   	  
      function drawChartMA() {
    	     
    	  // Specify the data source URL.
      	  var query = new google.visualization.Query('subCdColumnChartChart?subCdType=MA');
      	  // Send the query with a callback function.
      	  query.send(handleSimpleDsResponseMA);
      }
      
      
   	  // Handle the query response.
      function handleSimpleDsResponseMA(response) {
      if (response.isError()) {
      alert('Error in query: ' + response.getMessage() + ' ' + response.getDetailedMessage());
      return;
      }
      // Draw the visualization.
      var data = response.getDataTable();
      		
      		var options = {
      		      title : 'Utilization Analysis For SUB-CdType M&A',
      		      vAxis: {title: 'Hours'},
      		      hAxis: {title: 'Month'},
      		      seriesType: 'bars',
      		      series: {5: {type: 'line'}}
      		    };


     
	      var chart = new google.visualization.ComboChart(document.getElementById('columnchart_material_MA'));
	
	      chart.draw(data, options);
      }
      
    </script>
	
	
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
		      <li class="current"><fmt:message key="breadcrumb.tag.monthly.report"/></li>
		    </ul>
		</div>
		<div id="maincontainer">
			<h2><fmt:message key="welcome.header.monthly.report"/></h2>
			<div id="columnchart_material_SDP"></div>
			<div id="columnchart_material_ECENGIN"></div>
			<div id="columnchart_material_TV"></div>
			<div id="columnchart_material_MSP"></div>
			<div id="columnchart_material_RNAM"></div>
			<div id="columnchart_material_MA"></div>
			<br class="clear" />
		</div>
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>