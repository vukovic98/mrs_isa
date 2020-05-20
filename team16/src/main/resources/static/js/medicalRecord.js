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
    	
    	
    	$("#fullName").val(patient.firstName+" "+patient.lastName);
    	$("#birthday").val(patient.medicalRecord.birthday);
    	$("#gender").val(patient.medicalRecord.gender);
    	$("#weight").val(patient.medicalRecord.weight);
    	$("#height").val(patient.medicalRecord.height);
    	$("#bloodType").val(patient.medicalRecord.bloodType);
    	var allergiesList = $("#allergies");
    	allergiesList.empty();
    	if(patient.medicalRecord.allergies != null){
    		
        	$.each(patient.medicalRecord.allergies,function(i,val){
        		allergiesList.append("<li id=\""+i+"\">"+val+"</li>");
        	});
    	}
    	else{
    		allergiesList.append("<li>There are no allergies found</li>");
    	}
    	
    	var medicationsList = $("#medications");
    	medicationsList.empty();
    	if(patient.medicalRecord.perscriptions != null){
    		$.each(patient.medicalRecord.allergies,function(i,val){
        		medicationsList.append("<li id=\""+i+"\">"+val.name+"</li>");
    		});
    	}
    		else{
    			medicationsList.append("<li>There are no perscriptions found</li>");
    		}
    	
    	}
    	

    	
    	
    	
    
    function loadPatientInfoNO(patient){
    	$("#notFoundLabel").text("Medical Record Not Found");	
    }
    
    function unauthorized(){
    	document.write("<html><head></head><body>UNAUTHORIZED</body></html>");
    }