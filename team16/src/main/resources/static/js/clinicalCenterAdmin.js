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
    
    $(document).on('click', '#acBtn', function () {
    	console.log($(this).parent().parent().attr('id'));
    	
    	var email = $(this).parent().parent().attr('id');
    	
    	$.ajax({
    		type: 'POST',
    		url: 'registrationRequestApi/acceptUser',
    		dataType: 'json',
    		data: JSON.stringify({
    			"email": email
    		}),
    		contentType: "application/json; charset=utf-8",
    		statusCode: {
        		200: function(responseObject, textStatus, jqXHR) {
        			console.log("RegistrationRequests - acceptPatient() - 200 OK");
        			acceptAllOK();
        		},
        		400: function(responseObject, textStatus, jqXHR) {
        			console.log("RegistrationRequests - acceptPatient() - 400 Bad request");
        			acceptNO();
        		}
        	}
    	});
    });
    
    $(document).on('click', '#decBtn', function () {
    	console.log($(this).parent().parent().attr('id'));
    	
    	var email = $(this).parent().parent().attr('id');
    	
        $("#modalDecEmail").text(email);
        $("#modalDecName").text($(this).parent().parent().find("td:first").text());

        $("#declineModal").modal();

    });

     $(document).on('click', '#declineFinal', function () {
        var email = $("#modalDecEmail").text();
        var reason = $("#reasonArea").val();
        $.ajax({
            type: 'POST',
            url: 'registrationRequestApi/declineUser',
            dataType: 'json',
            data: JSON.stringify({
                "email": email,
                "address": reason
            }),
            contentType: "application/json; charset=utf-8",
            statusCode: {
                200: function(responseObject, textStatus, jqXHR) {
                    console.log("RegistrationRequests - declinePatient() - 200 OK");
                    declineAllOK();
                },
                400: function(responseObject, textStatus, jqXHR) {
                    console.log("RegistrationRequests - declinePatient() - 400 Bad request");
                    declineNO();
                }
            }
        });
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
        		$("#modalName").text(patient.name);
		        $("#modalEmail").text(patient.email);
		        $("#modalAddress").text(patient.address);
		        $("#modalPhone").text(patient.phone);
		        $("#modalCity").text(patient.city);
		        $("#modalCountry").text(patient.country);
		        $("#modalInsNumber").text(patient.insurance);
		        
		        $("#exampleModal").modal();
        	}
        });
    });
});

function declineAllOK() {
	location.reload();
}

function declineNO() {
	alert("Something went wrong!");
}

function acceptAllOK() {
	location.reload();
}

function acceptNO() {
	alert("Something went wrong!");
}

function regReqAllOK(regReqList) {
	var table = $("#regReqBody");
	table.empty();
	
	$.each(regReqList, function(i, val) {
		var row = $("<tr id=\""+val.patientEmail+"\"></tr>");
		row.append("<td class=\"w-50 modalTD cursor_pointer\" title=\"Click for more information\" id=\""+val.patientEmail+"\">" + val.patientName + "</td>");
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