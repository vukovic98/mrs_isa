$( document ).ready(function() {
    console.log( "ready!" );
    
    $.ajax ({
    	type: 'GET',
    	url: 'leaveRequestApi/findAll',
    	headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
    	statusCode: {
    		200: function(responseObject, textStatus, jqXHR) {
    			console.log("RegistrationRequests - findAll() - 200 OK");
    			leaveReqAllOK(responseObject);
    		},
    		204: function(responseObject, textStatus, jqXHR) {
    			console.log("RegistrationRequests - findAll() - 204 No Content");
    			leaveReqAllNO(responseObject);
    		},
    		403: function(responseObject, textStatus, jqXHR) {
    			console.log("403 Unauthorized");
    			unauthorized();
    		}
    	}
    });
    
    $(document).on('click', '#acBtn', function () {
    	console.log($(this).parent().parent().attr('id'));
    	
    	var leaveId = $(this).parent().parent().attr('id');
    	
    	$.ajax({
    		type: 'POST',
    		url: 'leaveRequestApi/acceptRequest',
    		headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
    		dataType: 'json',
    		data: JSON.stringify({
    			"id": leaveId
    		}),
    		contentType: "application/json; charset=utf-8",
    		statusCode: {
        		200: function(responseObject, textStatus, jqXHR) {
        			console.log("LeaveRequests - acceptRequest() - 200 OK");
        			acceptAllOK();
        		},
        		400: function(responseObject, textStatus, jqXHR) {
        			console.log("LeaveRequests - acceptRequest() - 400 Bad request");
        			acceptNO();
        		},
        		403: function(responseObject, textStatus, jqXHR) {
        			console.log("403 Unauthorized");
        			unauthorized();
        		}
        	}
    	});
    });
    
    $(document).on('click', '#decBtn', function () {
    	console.log($(this).parent().parent().attr('id'));
    	
    	var leaveId = $(this).parent().parent().attr('id');
    	
        $.ajax ({
        	type: 'GET',
        	url: 'leaveRequestApi/findOneById/' + leaveId,
        	headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
        	statusCode: {
        		200: function(responseObject, textStatus, jqXHR) {
        			console.log("RegistrationRequests - findAll() - 200 OK");
        			declineModalOK(responseObject);
        		},
        		204: function(responseObject, textStatus, jqXHR) {
        			console.log("RegistrationRequests - findAll() - 204 No Content");
        			declineModalNO(responseObject);
        		},
        		403: function(responseObject, textStatus, jqXHR) {
        			console.log("403 Unauthorized");
        			unauthorized();
        		}
        	}
        });
    	
    	


    });

     $(document).on('click', '#declineFinal', function () {
        var email = $("#modalDecEmail").text();
        var reason = $("#reasonArea").val();
    	$.ajax({
    		type: 'POST',
    		url: 'leaveRequestApi/declineRequest',
    		headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
    		dataType: 'json',
    		data: JSON.stringify({
    			"id": leaveId,
    			"reason" : reason
    		}),
    		contentType: "application/json; charset=utf-8",
    		statusCode: {
        		200: function(responseObject, textStatus, jqXHR) {
        			console.log("LeaveRequests - acceptRequest() - 200 OK");
        			declineAllOK();
        		},
        		400: function(responseObject, textStatus, jqXHR) {
        			console.log("LeaveRequests - acceptRequest() - 400 Bad request");
        			declineAllNO();
        		},
        		403: function(responseObject, textStatus, jqXHR) {
        			console.log("403 Unauthorized");
        			unauthorized();
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
        	headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
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

function declineModalOK(response){
    $("#modalDecName").text($(this).parent().parent().find("td:first").text());
    $("#modalDecFrom").text(response.dateFrom);
    $("#modalDecTo").text(response.dateTo);
    $("#declineModal").modal();
}


function declineAllOK() {
	location.reload();
}

function unauthorized(){
	document.write("<html><head></head><body>UNAUTHORIZED</body></html>");
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

function leaveReqAllOK(leaveReqList) {
	var table = $("#leaveReqBody");
	table.empty();
	
	$.each(leaveReqList, function(i, val) {
		var row = $("<tr id=\""+val.requestID+"\"></tr>");
		row.append("<td class=\"w-50 modalTD cursor_pointer\" title=\"Click for more information\" id=\""+val.requestID+"\">" + val.name + "</td>");
		row.append("<td class=\"w-50 text-right\"><button type=\"button\" class=\"btn btn-outline-primary mr-1\" id=\"acBtn\">Accept</button>" +
			"<button type=\"button\" class=\"btn btn-outline-secondary\" id=\"decBtn\">Decline</button></td>");

		table.append(row);
	});
}

function leaveReqAllNO(responseObject) {
	var table = $("#leaveReqBody");
	table.empty();
	
	var row = $("<tr></tr>");
	row.append("<td class='pl-1'>There is no pending registration requests</td>");
	
	table.append(row);
}