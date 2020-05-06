$( document ).ready(function() {
    console.log( "ready!" );
    
    $.ajax ({
    	type: 'GET',
    	url: 'registrationRequestApi/findAll',
    	statusCode: {
    		200: function(responseObject, textStatus, jqXHR) {
    			console.log("RegistrationRequests - findAll() - 200 OK");
    			regReqAllOK(responseObject);
    		},
    		204: function(responseObject, textStatus, jqXHR) {
    			console.log("RegistrationRequests - findAll() - 204 No Content");
    			regReqAllNO(responseObject);
    		}
    	}
    });
    
    $(document).on('click', '.modalTD', function () {

        var email = $(this).attr('id');
        console.log("UDJE MODAL");
        $.ajax({
        	type: 'POST',
        	url: 'patientApi/findModalByEmail',
        	dataType: 'json',
        	data: JSON.stringify({
        		"email": email 
        	}),
        	contentType: "application/json; charset=utf-8",
		    dataType: "json",
        	success: function(patient) {
        		$("#modalName").text(patient.firstName + " " + patient.lastName);
		        $("#modalEmail").text(patient.email);
		        $("#modalAddress").text(patient.address);
		        $("#modalPhone").text(patient.phoneNumber);
		        $("#modalCity").text(patient.city);
		        $("#modalCountry").text(patient.country);
		        $("#modalInsNumber").text(patient.insuranceNumber);
		        
		        $("#exampleModal").modal();
        	}
        });
    });
});

function regReqAllOK(regReqList) {
	var table = $("#regReqBody");
	table.empty();
	
	$.each(regReqList, function(i, val) {
		var row = $("<tr id=\""+i+"\"></tr>");

		row.append("<td class=\"w-50 modalTD\" id=\""+val.patient.email+"\">" + val.patient.firstName + " " + val.patient.lastName + "</td>");
		row.append("<td class=\"w-50 text-right\"><button type=\"button\" class=\"btn btn-outline-primary mr-1\" id=\"acBtn\">Accept</button>" +
			"<button type=\"button\" class=\"btn btn-outline-secondary\" id=\"decBtn\">Decline</button></td>");

		table.append(row);
	});
}

function regReqAllNO(responseObject) {
	var table = $("#regReqBody");
	table.empty();
	
	var row = $("<tr></tr>");
	row.append("<td class='pl-1'>There is no pending registration requests</td>");
	
	table.append(row);
}