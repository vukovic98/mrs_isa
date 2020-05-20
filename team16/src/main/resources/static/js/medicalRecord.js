$( document ).ready(function() {
    console.log( "ready!" );
    
    $.ajax ({
    	type: 'GET',

    	url: '/patientApi/medicalRecord',

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
    	
    	
    	$("#fullName").val(patient.fullName);
    	$("#birthday").val(patient.medicalRecord.birthday);
    	$("#gender").val(patient.medicalRecord.gender);
    	$("#weight").val(patient.medicalRecord.weight);
    	$("#height").val(patient.medicalRecord.height);
    	$("#bloodType").val(patient.medicalRecord.bloodType);
    	var allergiesTable = $("#allergiesBody");
    	allergiesTable.empty();
    	console.log(patient.medicalRecord.allergies);
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
<<<<<<< HEAD
    		$.each(patient.medicalRecord.allergies,function(i,val){
        		medicationsList.append("<li id=\""+i+"\">"+val.name+"</li>");
    		});
    	}
=======
    		
    		$.each(patient.medicalRecord.perscriptions,function(i,val){
    			 var row = $("<tr></tr>");

     		    row.append("<td>" + val.name + "</td>");
     		    row.append("<td>" + val.code + "</td>");

     		    medicationsTable.append(row);
     		
    	});}
>>>>>>> a4a21ebc4b43fbbb2f9fee7340d048268c904577
    		else{
    			medicationsTable.append("<tr><td colspan=\"2\">There are no perscriptions found</td></tr>");
    		}
    	
<<<<<<< HEAD
    	}
    	

=======
    	};
    	
    
>>>>>>> a4a21ebc4b43fbbb2f9fee7340d048268c904577
    	
    	
    	
    
    function loadPatientInfoNO(patient){
    	$("#notFoundLabel").text("Medical Record Not Found");	
    }
    
    function unauthorized(){
    	document.write("<html><head></head><body>UNAUTHORIZED</body></html>");
    }