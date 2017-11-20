<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<title>W3.CSS Template</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
html,body,h1,h2,h3,h4,h5,h6 {font-family: "Roboto", sans-serif;}
.w3-sidebar {
  z-index: 3;
  width: 250px;
  top: 43px;
  bottom: 0;
  height: inherit;
}
</style>

  <script>
  $( function() {
    $( ".widget input[type=submit], .widget a, .widget button" ).button();
    $( "button, input, a" ).click( function( event ) {
      event.preventDefault();
    } );
  } );
  </script>
<script>
  $( function() {
    $( "#speed" ).selectmenu();
 
    $( "#files" ).selectmenu();
 
    $( "#number" )
      .selectmenu()
      .selectmenu( "menuWidget" )
        .addClass( "overflow" );
 
    $( "#salutation" ).selectmenu();
  } );
  </script>

<body>

<!-- Navbar -->
<div class="w3-top">
  <div class="w3-bar w3-theme w3-top w3-left-align w3-large">
    <a class="w3-bar-item w3-button w3-right w3-hide-large w3-hover-white w3-large w3-theme-l1" href="javascript:void(0)" onclick="w3_open()"><i class="fa fa-bars"></i></a>
    <a href="#" class="w3-bar-item w3-button w3-theme-l1">Authenticate</a>
    <a href="#" class="w3-bar-item w3-button w3-hide-small w3-hover-white">Clients</a>
   	<a href="#" class="w3-bar-item w3-button w3-hide-small w3-hover-white">Alerts</a>    
    <a href="#" class="w3-bar-item w3-button w3-hide-small w3-hover-white">Docs</a>
    <a href="#" class="w3-bar-item w3-button w3-hide-small w3-hover-white">Feedback</a>
    <a href="#" class="w3-bar-item w3-button w3-hide-small w3-hover-white">About</a>
  </div>
</div>

<!-- Sidebar -->

<!-- beg:define form for cloudDevicePush -->
	<jsp:include page="leftnavbar.jsp"/>	
<!-- end:define form for cloudDevicePush -->	

<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- Main content: shift it to the right by 250 pixels when the sidebar is visible -->
<div class="w3-main" style="margin-left:250px">

  <div class="w3-row w3-padding-64">
    <div class="w3-twothird w3-container">

	<!-- beg:define form for cloudDevicePush -->
	<div class="demo">
	
    <form:form method="POST" commandName="gbDevice">
    
		<form:select path="deviceId" items="${gbdevices}"/>  
    
		<!-- 	
		<c:if test="${coloursList}">
    		<!-- Display this only when myObject has the atttribute "myAttribute" -->
    		<!-- Now I can access safely to "myAttribute" -->

		</c:if>	
  			<fieldset>
    			<label for="client">Select a Client</label>
    			<select name="client" id="client">
    				<option>aero</option>
      				<option>brighton</option>
      				<option selected="selected">pandora</option>
    			</select>
			</fieldset>	
		-->
			
			<h1>Healthcheck</h1>
<a href="http://kwidev31:8080/mpos-offline-pushkit-0.0.1-SNAPSHOT/restservice/offlinecontroller/healthcheck/IPH-754F9B3E10EA/">HealthCheck</a>
			
			<h1>Pushkit</h1>

<a href="http://kwidev31:8080/mpos-offline-pushkit-0.0.1-SNAPSHOT/restservice/offlinecontroller/startpushtest">Pushkit</a>

			<!-- 
			<button class="ui-button ui-widget ui-corner-all">A button element</button>
 
			<input class="ui-button ui-widget ui-corner-all" type="submit" value="A submit button">
 
			<a class="ui-button ui-widget ui-corner-all" href="#">An anchor</a>
			-->
			
    </form:form>
	</div>
	<!-- end:define form for cloudDevicePush -->	

    </div>
    <div class="w3-third w3-container">
    </div>
  </div>

 
  <footer id="myFooter">
      <h4>Footer</h4>
    </div>
  </footer>

<!-- END MAIN -->
</div>

<script>
// Get the Sidebar
var mySidebar = document.getElementById("mySidebar");

// Get the DIV with overlay effect
var overlayBg = document.getElementById("myOverlay");

// Toggle between showing and hiding the sidebar, and add overlay effect
function w3_open() {
    if (mySidebar.style.display === 'block') {
        mySidebar.style.display = 'none';
        overlayBg.style.display = "none";
    } else {
        mySidebar.style.display = 'block';
        overlayBg.style.display = "block";
    }
}

// Close the sidebar with the close button
function w3_close() {
    mySidebar.style.display = "none";
    overlayBg.style.display = "none";
}
</script>

</body>
</html>
