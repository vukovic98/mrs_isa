 $( document ).ready(function() {
    console.log( "ready!" );
    
    $.ajax ({
    	type: 'GET',
    	url: '/patientApi/findOneByEmail',
    	headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
    	statusCode: {
    		200: function(responseObject, textStatus, jqXHR) {
    			console.log("200 OK");
    			loadPatientInfoAllOK(responseObject);
    		},
    		204: function(responseObject, textStatus, jqXHR) {
    			console.log("204 No Content");
    			loadPatientInfoNO(responseObject);
    		},
    		403: function(responseObject, textStatus, jqXHR) {
    			console.log("403 Unauthorized");
    			unauthorized();
    		}
    	}
    });
  
});
    function loadPatientInfoAllOK(patient) {
    	
    	var table = $("#appointmentHistoryBody");
    	table.empty();
    	var appointments = patient.appointments;
    	console.log(appointments.length);
    	if(appointments.length != 0){
	    	$.each(appointments, function(i, val) {
	    		console.log(val);
	    		var row = $("<tr id=\""+i+"\"></tr>");
	
	    		row.append("<td class=\"w-50\">" + val.dateTime + "</td>");
	    		row.append("<td class=\"w-50\">" + val.ordination.type + "</td>");
	    		row.append("<td class=\"w-50\">" + val.doctor.clinic.name + "</td>");
	    		table.append(row);
	    	});
    	}
    	else{
    		var row = $("<tr></tr>");

    		row.append("<td class=\"w-50 text-center\" colspan=\"4\"> There are no appointments to show.</td>");
    		table.append(row);
    	}
    }
    	
    function unauthorized(){
    	document.write("<html><head></head><body><h1>UNAUTHORIZED</h1></body></html>");
    }
    	
    
    function loadPatientInfoNO(patient){
    	$("#patientFullName").text("Patient Not Found");	
    }
    
    function searchAppointments() {
		// Declare variables
		var input, filter, table, tr, td, i, j, txtValue;
		input = document.getElementById("myInput");
		filter = input.value.toUpperCase();
		table = document.getElementById("myTable");
		tr = table.getElementsByTagName("tr");


		// Loop through all table rows, and hide those who don't match the search query
		for (i = 0; i < tr.length; i++) {
			let rowTds = tr[i].getElementsByTagName("td")
			for (j = 0; j < rowTds.length; j++) {
				td = tr[i].getElementsByTagName("td")[j];
				if (td) {
					if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
						tr[i].style.display = "";
						break; // this will break the row looping on j after making the row visible.
					} else {
						tr[i].style.display = "none";
					}
				}
			}

		}

	}