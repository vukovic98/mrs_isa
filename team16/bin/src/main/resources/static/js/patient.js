$( document ).ready(function() {
    console.log( "ready!" );
    
    $.ajax ({
    	type: 'GET',
    	url: '/patientApi/patientInfo',
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
    	console.log(patient);
    	$("#patientFullName").text(patient.firstName + " " + patient.lastName);
    	
    	
    	$("#fullName").val(patient.firstName+" "+patient.lastName);
    	$("#country").val(patient.country);
    	$("#city").val(patient.city);
    	$("#address").val(patient.address);
    	$("#phoneNumber").val(patient.phone);
    	$("#insNumber").val(patient.insurance);
    	
    	$("#editFirstName").val(patient.firstName);
    	$("#editLastName").val(patient.lastName);
    	$("#editCountry").val(patient.country);
    	$("#editCity").val(patient.city);
    	$("#editAddress").val(patient.address);
    	$("#editPhoneNumber").val(patient.phone);
    	$("#editInsNumber").val(patient.insurance);
    	$("#editEmail").val(patient.email); 	
    }
    
    function loadPatientInfoNO(patient){
    	$("#patientFullName").text("Patient Not Found");	
    }
    

   
    $("#myTab").click(function(e){
   	 $.ajax ({
   	    	type: 'GET',
   	    	url: '/patientApi/patientInfo',
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
    $("#changePassword").click(function(e){
    	e.preventDefault();
    	
    	 $.ajax ({
    	    	type: 'GET',
    	    	url: '/patientApi/findPatientsPassword',
    	    	headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
    	    	statusCode: {
    	    		200: function(responseObject, textStatus, jqXHR) {
    	    			console.log("200 OK");
    	    			passValidation(responseObject);
    	    			
    	    		},
    	    		204: function(responseObject, textStatus, jqXHR) {
    	    			console.log("204 No Content");
    	    			
    	    		},
    	    		403: function(responseObject, textStatus, jqXHR) {
    	    			console.log("403 Unauthorized");
    	    			unauthorized();
    	    		}
    	    	}
    	    });
    });
    
function passValidation(patientsPassword){
	var pass = $("#oldPassword").val();
	var newPass = $("#newPassword").val();
	var confirmedPass = $("#confirmedPassword").val();
	
	if(patientsPassword != pass){
		 $("#oldPassword").addClass("is-invalid");

	 }
	 else{
		 $("#oldPassword").removeClass("is-invalid");
	
	 }
    	 
    	 if(newPass != confirmedPass){
     		$("#confirmedPassword").addClass("is-invalid");
     		
     	}
     	else{
     		$("#confirmedPassword").removeClass("is-invalid");
    		
     	}
     	
    	 var formData ={
 				password : $("#newPassword").val()
 	
 		}
    	 console.log($("#oldPassword").hasClass("is-invalid"));
    	if (pass != "" && newPass != "" && confirmedPass !=""  && $("#oldPassword").hasClass("is-invalid")==false && $("#newPassword").hasClass("is-invalid")==false  && $("#confirmedPassword").hasClass("is-invalid")==false ){
    	
    		$.ajax({
   	        	type: 'PUT',
   	        	url: 'patientApi/changePassword',
   	        	headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
   	        	data: JSON.stringify(newPass),
   	        	dataType: 'json',
   	        	contentType: "application/json",
   			    dataType: "json",
   			    statusCode: {
   			    	200: function(){showMessage("Password changed!", "palegreen");
   	        			console.log("200 OK");
   	    		},
   	    		204:function(){
   	    			console.log("204 No Content");
   	    			showMessage("Couldn't change password!", "antiquewhite");
   	    			
   	    		}
   			    }
   			  
   	        });}
   		
    	 $("#oldPassword").val('');
    	 $("#newPassword").val('');
    	 $("#confirmedPassword").val('');
    	
}


    $("#savePatient").click(function(e){
		e.preventDefault();
		
		var firstName = $("#editFirstName").val();
		var lastName = $("#editLastName").val();
		var country = $("#editCountry").val();
		var city = $("#editCity").val();
	    var address = $("#editAddress").val();
     	var	phoneNumber = $("#editPhoneNumber").val();
     	
     	//VALIDATION
     	if (firstName == null || firstName == ""){
     		$("#editFirstName").addClass("is-invalid");
     
     	}else{
     		$("#editFirstName").removeClass("is-invalid");
     	
     	}
     	if (lastName == null || lastName == ""){
     		$("#editLastName").addClass("is-invalid");
     		
     	}else{
     		$("#editLastName").removeClass("is-invalid");
     	}
     	if (country == null || country == ""){
     		$("#editCountry").addClass("is-invalid");
     		
     	}else{
     		$("#editCountry").removeClass("is-invalid");
     	}
     	if (city == null || country == ""){
     		$("#editCity").addClass("is-invalid");
   
     	}else{
     		$("#editCity").removeClass("is-invalid");
     	}
    	if (phoneNumber == null || phoneNumber == ""){
     		$("#editPhoneNumber").addClass("is-invalid");
     	
     	}else{
     		$("#editPhoneNumber").removeClass("is-invalid");
     	}
    	if (address == null || address == ""){
     		$("#editAddress").addClass("is-invalid");
  
     	}else{
     		$("#editAddress").removeClass("is-invalid");
     	}
    	
		var formData ={
				firstName : $("#editFirstName").val(),
				lastName : $("#editLastName").val(),
				country : $("#editCountry").val(),
				city : $("#editCity").val(),
			    address : $("#editAddress").val(),
				phoneNumber : $("#editPhoneNumber").val()
			
		}
		console.log(formData)
		if (firstName != "" && lastName != "" && country !="" && city!="" && address!="" && phoneNumber != ""){
		 $.ajax({
	        	type: 'PUT',
	        	url: 'patientApi/updatePatient',
	        	headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
	        	data: JSON.stringify(formData),
	        	dataType: 'json',
	        	contentType: "application/json",
			    dataType: "json",
			    statusCode: {
   			    	200: function(){showMessage("Saved!", "palegreen");
   	        			console.log("200 OK");
   	    		},
   	    		204:function(){
   	    			console.log("204 No Content");
   	    			showMessage("Something went wrong. Try again.", "antiquewhite");
   	    			
   	    		}
   			    }
			    
	        });
		}
		
    });
		
		
    function showMessage(message, color) {
    	$("#message_bar").css("background", color);
    	$("#message_bar").text(message);
    	$("#message_bar").slideDown().delay(1500).slideUp();
    }
    function unauthorized(){
    	document.write("<html><head></head><body>UNAUTHORIZED</body></html>");
    }

