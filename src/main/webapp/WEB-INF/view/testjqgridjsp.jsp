<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Canonical jqGrid example</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/themes/redmond/jquery-ui.min.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/free-jqgrid/4.15.2/css/ui.jqgrid.min.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/free-jqgrid/4.15.2/jquery.jqgrid.min.js"></script>

<script>
jQuery(document).ready(function() {
    $("#formData").jqGrid({
            url : "/mpos-offline-admin/loadjqgrid",
            datatype : "json",
            mtype : 'GET',
            colNames : [ 'DeviceId', 'DeviceToken', 'ClientName' ],
            colModel : [ {
                    name : 'deviceId',
                    index : 'deviceId',
                    width : 100
            }, {
                    name : 'deviceToken',
                    index : 'deviceToken',
                    width : 200,
                    editable : true
            }, {
                name : 'client',
                index : 'client',
                width : 150,
                editable : true                    
            } ],
            pager : '#pager',
            rowNum : 10,
            rowList : [ 10, 20, 30 ],
            sortname : 'invid',
            sortorder : 'desc',
            viewrecords : true,
            gridview : true,
            caption : 'Device Tokens',
            jsonReader : {
                    repeatitems : false,
            },
            editurl : "/mpos-offline-admin/testjqgrid",
            onSelectRow: function(rowId){ 
                var rowData = $('#formData').jqGrid('getRowData', rowId);
                //You can access the desired columns like this --> rowData['col3']
                document.getElementById("token").value = rowData['deviceId'];
                //alert('rowData[deviceId]:'+rowData['deviceId']);
            }
    });
    jQuery("#formData").jqGrid('navGrid', '#pager', {
            edit : true,
            add : true,
            del : true,
            search : true
    });
});

</script>
</head>
<body>

    <form:form action="/mpos-offline-admin/pushkit" method="post" modelAttribute="pushkit">
    
		<h1>Healthcheck</h1>
		<a href="http://kwidev31:8080/mpos-offline-pushkit-0.0.1-SNAPSHOT/restservice/offlinecontroller/healthcheck/IPH-754F9B3E10EA/">HealthCheck</a>
			
		<h1>Pushkit</h1>

		</br>
		
		<table>
			<tr>
				<td><form:label path="pushText">PushText</form:label></td>
				<td><form:input path="pushText"></form:input></td>
			</tr>
			<tr>
				<td><form:label path="token">Token</form:label></td>
				<td><form:input path="token"></form:input></td>
			</tr>
		</table>
			
		</br>	

		<table id="formData">
		<tr>
			<td />
		</tr>
		</table>
		<div id="pager"></div>				

		</br>
		
		<input type="submit" value="Submit" />

    </form:form>

</body>
</html>



