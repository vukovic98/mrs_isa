$(document).ready(function () {
	 $.ajax ({
    	type: 'GET',
    	url: 'appointmentApi/findAllMedicalReports',
	    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
    	statusCode: {
    		200: function(responseObject, textStatus, jqXHR) {
    			console.log("MedicalReports - findAllMedicalReports() - 200 OK");
    			reportsAllOK(responseObject);
    		},
    		204: function(responseObject, textStatus, jqXHR) {
    			console.log("MedicalReports - findAllMedicalReports() - 204 No Content");
    			reportsAllNO(responseObject);
    		},
			403: function(responseObject, textStatus, jqXHR) {
				console.log("403 Unauthorized");
				unauthorized();
			}
    	}
    });
	 
	 $(document).on('click', '#modalApproveBtn', function () {
		 var clinic = $("#clinic").val();
		 var patient = $("#patient").val();
		 var doctor = $("#doctor").val();
		 var details = $("#details").val();
		 
		 var diagnosis = $("#diagnosis option").map(function() {return $(this).val();}).get();
		 var medications = $("#medications option").map(function() {return $(this).val();}).get();
		 var id = $("#reportId").text();
		 $.ajax({
			 type: 'POST',
	        	url: 'medicalReportApi/addReport',
	    	    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
	        	dataType: 'json',
	        	data: JSON.stringify({
	        		"id": id,
	        		"clinic": clinic,
	        		"doctor": doctor,
	        		"patient": patient,
	        		"details": details,
	        		"diagnosis": diagnosis,
	        		"medications": medications
	        	}),
	        	contentType: "application/json; charset=utf-8",
			    dataType: "json",
			    statusCode: {
		    		200: function(responseObject, textStatus, jqXHR) {
		    			console.log("MedicalReports - findAllMedicalReports() - 200 OK");
		    			showMessage("Medical report successfully approved!", "palegreen");
		    			window.setTimeout(function(){location.reload()},1500)
		    		},
		    		400: function(responseObject, textStatus, jqXHR) {
		    			console.log("MedicalRecords - findAllMedicalReports() - 204 No Content");
		    			showMessage("Something went wrong!", "antiquewhite");
		    		},
					403: function(responseObject, textStatus, jqXHR) {
						console.log("403 Unauthorized");
						unauthorized();
					}
		    	}
		 });
	 });

	 $(document).on('click', '.info', function () {
        var id = $(this).attr('id');
        console.log("UDJE MODAL");
        $.ajax({
        	type: 'POST',
        	url: 'appointmentApi/findReportById',
    	    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
        	dataType: 'json',
        	data: JSON.stringify({
        		"medicalReportId": id 
        	}),
        	contentType: "application/json; charset=utf-8",
		    dataType: "json",
        	success: function(report) {
        		console.log(report);
        		$("#reportId").text(report.id);
        		$("#clinic").val(report.clinic);
		        $("#patient").val(report.patient);
		        $("#doctor").val(report.doctor);
		        $("#details").val(report.details);
		        
		        $("#diagnosis").empty();
		        $("#medications").empty();
		        
		        
		        if(report.diagnosis.length == 0)
		        	$('#diagnosis').append(`<option value="-1">Nothing diagnosed.</option>`); 
		        
		        $.each(report.diagnosis, function(i, val) {
		        	$('#diagnosis').append(`<option value="${val}">${val}</option>`); 
		        });

		        if(report.medications.length == 0)
		        	$('#medications').append(`<option value="-1">Nothing perscribed.</option>`); 
		        
		        $.each(report.medications, function(i, val) {
		        	$('#medications').append(`<option value="${val}">${val}</option>`); 
		        });

		        $("#exampleModal").modal();
        	}
        });
    });
});

function showMessage(message, color) {
	$("#message_bar").css("background", color);
	$("#message_bar").text(message);
	$("#message_bar").slideDown().delay(1500).slideUp();
}

function reportsAllOK(reportList) {
	var table = $("#reportsBody");
	table.empty();
	
	$.each(reportList, function(i, val) {
		var row = $("<tr id=\""+i+"\"></tr>");
		row.append("<td id=\""+val.id+"\">" + val.id + "</td>");
		row.append("<td id=\""+val.id+"\">" + val.patient + "</td>");
		row.append("<td id=\""+val.id+"\">" + val.doctor + "</td>");
		row.append("<td class=\"info\" id=\""+val.id+"\"></td>");

		table.append(row);
	});
}

function reportsAllNO(responseObject) {
	var table = $("#reportsBody");
	table.empty();
	
	var row = $("<tr></tr>");
	row.append("<td class='pl-1'>There is no pending reports at the moment.</td>");
	
	table.append(row);
}

function unauthorized(){
	document.write("<html><head></head><body>UNAUTHORIZED</body></html>");
}