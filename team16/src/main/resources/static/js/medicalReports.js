$(document).ready(function () {
	 $.ajax ({
    	type: 'GET',
    	url: 'appointmentApi/findAllMedicalReports',
    	statusCode: {
    		200: function(responseObject, textStatus, jqXHR) {
    			console.log("MedicalRecords - findAllMedicalReports() - 200 OK");
    			reportsAllOK(responseObject);
    		},
    		204: function(responseObject, textStatus, jqXHR) {
    			console.log("MedicalRecords - findAllMedicalReports() - 204 No Content");
    			reportsAllNO(responseObject);
    		}
    	}
    });

	 $(document).on('click', '.info', function () {

        var id = $(this).attr('id');
        console.log("UDJE MODAL");
        $.ajax({
        	type: 'POST',
        	url: 'appointmentApi/findReportById',
        	dataType: 'json',
        	data: JSON.stringify({
        		"medicalReportId": id 
        	}),
        	contentType: "application/json; charset=utf-8",
		    dataType: "json",
        	success: function(report) {
        		console.log(report);
        		
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