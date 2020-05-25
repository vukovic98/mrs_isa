$( document ).ready(function() {
    console.log( "ready!" );
    
    $.ajax ({
    	type: 'GET',

    	url: '/patientApi/medicalRecord',

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
    	console.log(patient.firstName);
    	var birthday = patient.medicalRecord.birthday;
    	var date = new Date(birthday);
    	var day = date.getDay()-1;
    	var month = date.getMonth()+1;
    	var year = date.getFullYear();
    	
    	$("#fullName").val(patient.firstName + " "+ patient.lastName);
    	$("#birthday").val(day+'-'+month+'-'+year);
    	$("#gender").val(patient.medicalRecord.gender);
    	$("#weight").val(patient.medicalRecord.weight + " kg");
    	$("#height").val(patient.medicalRecord.height+ " cm");
    	$("#bloodType").val(patient.medicalRecord.bloodType);
    	var allergiesTable = $("#allergiesBody");
    	allergiesTable.empty();
    	
    	if(patient.medicalRecord.allergies != null){
    		
        	$.each(patient.medicalRecord.allergies,function(i,val){
        		var row = $("<tr></tr>");
        		row.append("<td>"+val+"</td>");
        		allergiesTable.append(row);
        	
        	});
    	}
    	else{
    		allergiesList.append("<h6>There are no allergies found</h6>");
    	}
    	
    	var medicationsTable = $("#medicationsBody");
    	medicationsTable.empty();
    	
    	if(patient.medicalRecord.perscriptions != null){
    		$.each(patient.medicalRecord.perscriptions,function(i,val){
    			 var row = $("<tr></tr>");

     		    row.append("<td>" + val.name + "</td>");
     		    row.append("<td>" + val.code + "</td>");

     		    medicationsTable.append(row);
     		
    	});}

    		else{
    			medicationsTable.append("<tr><td colspan=\"2\">There are no perscriptions found</td></tr>");
    		}

    	}
    	 
    function loadPatientInfoNO(patient){
    	$("#notFoundLabel").text("Medical Record Not Found");	
    }
    
    function unauthorized(){
    	document.write("<html><head></head><body>UNAUTHORIZED</body></html>");
    }