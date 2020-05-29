$(document).ready(function(){
	
	console.log("ready");

	  $.ajax({
		    type: 'GET',
		    url: 'appointmentApi/findAllDoc/60',
		    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
		    statusCode: {
		      200: function(responseObject, textStatus, jqXHR) {
		        console.log("Appointments - findAll() - 200 OK");
		        appointmentsAllOK(responseObject);
		      },
		      204: function(responseObject, textStatus, jqXHR) {
		        console.log("Appointments - findAll() - 204 No Content");
		        appointmentsAllNO(responseObject);
		      },
				403: function(responseObject, textStatus, jqXHR) {
					console.log("403 Unauthorized");
					unauthorized();
				}
		    }
		  });
  
});

function appointmentsAllOK(appointmentsList) {
	  var table = $("#appointmentsBody");
	  table.empty();
	  console.log(appointmentsList);
	  
	  $.each(appointmentsList, function(i, val) {
	    var row = $("<tr id=\""+i+"\"></tr>");
	    row.append("<td class=\"w-25\" id=\""+val.id+"\">" + val.datetime + "</td>");
	    row.append("<td class=\"w-25\" id=\""+val.id+"\">" + val.patient +"</td>");
	    row.append("<td class=\"w-25 text-right\" id=\""+val.id+"\"><button type=\"button\" class=\"btn btn-outline-primary\">Start</button><button type=\"button\" onclick=\"modalShow()\" class=\"btn btn-outline-secondary\">Report</button></td>");

	    table.append(row);
	    
	  });
	}

	function appointmentsAllNO(responseObject) {
	  var table = $("#appointmentsBody");
	  table.empty();
	  
	  var row = $("<tr></tr>");
	  row.append("<td class='pl-1'>There are no appointments.</td>");
	  
	  table.append(row);
	}
	function unauthorized(){
		document.write("<html><head></head><body>UNAUTHORIZED</body></html>");
	}