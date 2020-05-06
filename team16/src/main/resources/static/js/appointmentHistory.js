 $( document ).ready(function() {
    console.log( "ready!" );
    
    $.ajax ({
    	type: 'GET',
    	url: '/patientApi/findOneByEmail',
    	statusCode: {
    		200: function(responseObject, textStatus, jqXHR) {
    			console.log("200 OK");
    			loadPatientInfoAllOK(responseObject);
    		},
    		204: function(responseObject, textStatus, jqXHR) {
    			console.log("204 No Content");
    			loadPatientInfoNO(responseObject);
    		}
    	}
    });
  
});
    function loadPatientInfoAllOK(patient) {
    	
    	var table = $("#appointmentHistoryBody");
    	table.empty();
    	var appointments = patient.appointments;
    	if(appointments != null){
    	$.each(appointments, function(i, val) {
    		var row = $("<tr id=\""+i+"\"></tr>");

    		row.append("<td class=\"w-50>" + val.dateTime + "</td>");
    		row.append("<td class=\"w-50>" + val.pricelistItems[0].appointmentType + "</td>");
    		row.append("<td class=\"w-50>" + val.doctor.clinic.name + "</td>");
    		row.append("<td class=\"w-50>" + val.appointmentRequest.approved + "</td>");
    		table.append(row);
    	});
    	}
    	else{
    		var row = $("<tr id=\""+i+"\"></tr>");

    		row.append("<td class=\"w-50 colspan="4"> There are no appointments to show.</td>");
    		
    	}
    }
    	
    	
    	
    
    function loadPatientInfoNO(patient){
    	$("#patientFullName").text("Patient Not Found");	
    }