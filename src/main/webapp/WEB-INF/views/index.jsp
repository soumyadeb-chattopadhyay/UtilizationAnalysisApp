<html>

<head>
	<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
	<link href="resources/css/displaytag.css" media="screen" rel="stylesheet" type="text/css" />

    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
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


<body>
	<form method="POST" enctype="multipart/form-data"
		action="upload">
		File to upload: <input type="file" name="file"><br /> Name: <input
			type="text" name="name"><br /> <br /> <input type="submit"
			value="Upload"> Press here to upload the file!
	</form>
	
	
	<div id="tablediv">
		<display:table name="cdTypeDTOs" class="simple nocol" defaultsort="1" decorator="org.displaytag.decorator.TotalTableDecorator" export="true">
		    <display:column property="subCdType" group="1" />
			<display:column property="subCd" />
			<display:column property="month" sortable="true"/>
		   
		    <display:column property="targetHours" format="{0,number,0.00}" headerClass="r" class="r" total="true" />
		    <display:column property="recordedHours" format="{0,number,0.00}" headerClass="r" class="r" total="true" />
  		</display:table>
	</div>
	
	Eripro SubCdType percentage wise : 
	<div id="tablediv">
		<display:table name="cdTypeMonthPercentageDTOs" class="simple nocol" defaultsort="1" decorator="org.displaytag.decorator.TotalTableDecorator" export="true">
		    <display:column property="subCdType" group="1" />
			<display:column property="month"/>
			<display:column property="percentage" format="{0,number,0.00}" headerClass="r" class="r" total="true" />
  		</display:table>
	</div>
	
	<div id="columnchart_material_SDP"></div>
	<div id="columnchart_material_ECENGIN"></div>
	<div id="columnchart_material_TV"></div>
	<div id="columnchart_material_MSP"></div>
	<div id="columnchart_material_RNAM"></div>
	<div id="columnchart_material_MA"></div>
	
	
</body>
</html>