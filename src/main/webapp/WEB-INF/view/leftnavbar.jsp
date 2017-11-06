<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<nav class="w3-sidebar w3-bar-block w3-collapse w3-large w3-theme-l5 w3-animate-left" id="mySidebar">
  <a href="javascript:void(0)" onclick="w3_close()" class="w3-right w3-xlarge w3-padding-large w3-hover-black w3-hide-large" title="Close Menu">
    <i class="fa fa-remove"></i>
  </a>
  <h4 class="w3-bar-item"><b>Offline Menu</b></h4>
  
	<c:url value="/cloudDevicePush" var="messageUrl" />
  <a class="w3-bar-item w3-button w3-hover-black" href="${messageUrl}">Cloud Device Push</a>
  
	<c:url value="/offlineDeviceReports" var="messageUrl" />  
  <a style="color:teal" class="w3-bar-item w3-button w3-hover-black" href="${messageUrl}">Offline Device Reports</a>
  
  	<c:url value="/offlineDownloadSchedule" var="messageUrl" />
  <a class="w3-bar-item w3-button w3-hover-black" href="${messageUrl}">Offline Download Schedule</a>

</nav>

</body>
</html>