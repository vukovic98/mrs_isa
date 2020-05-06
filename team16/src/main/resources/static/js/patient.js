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
    	
    	$("#patientFullName").text(patient.firstName + " " + patient.lastName);
    	
    	
    	$("#fullName").val(patient.firstName+" "+patient.lastName);
    	$("#country").val(patient.country);
    	$("#city").val(patient.city);
    	$("#address").val(patient.address);
    	$("#phoneNumber").val(patient.phoneNumber);
    	$("#insNumber").val(patient.insuranceNumber);
    	
    	$("#editFirstName").val(patient.firstName);
    	$("#editLastName").val(patient.lastName);
    	$("#editCountry").val(patient.country);
    	$("#editCity").val(patient.city);
    	$("#editAddress").val(patient.address);
    	$("#editPhoneNumber").val(patient.phoneNumber);
    	$("#editInsNumber").val(patient.insuranceNumber);
    	$("#editEmail").val(patient.email);
    }
    	
    	
    	
    
    function loadPatientInfoNO(patient){
    	$("#patientFullName").text("Patient Not Found");	
    }