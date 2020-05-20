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
    

   
    $("#myTab").click(function(e){
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
		 $("#oldPassword").attr("placeholder", "Wrong password.");
	 }
	 else{
		 $("#oldPassword").removeClass("is-invalid");
		 $("#oldPassword").attr("placeholder", "");
	 }
    	 
    	 if(newPass != confirmedPass){
     		$("#newPassword").addClass("is-invalid");
     		$("#confirmedPassword").addClass("is-invalid");
     		$("#confirmedPassword").attr("placeholder", "Please make sure your passwords match.");
     	}
     	else{
     		$("#newPassword").removeClass("is-invalid");
     		$("#confirmedPassword").removeClass("is-invalid");
    		$("#confirmedPassword").attr("placeholder", "");
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
   			    success:function(data) {
   			        alert('Password changed!');},
   			   error : function(e) {
   			            alert("Error!")
   			            console.log("ERROR: ", e);
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
     		$("#editFirstName").attr("placeholder", "First Name can not be empty!");
     	}else{
     		$("#editFirstName").removeClass("is-invalid");
     		$("#editFirstName").attr("placeholder", "");
     	}
     	if (lastName == null || lastName == ""){
     		$("#editLastName").addClass("is-invalid");
     		$("#editLastName").attr("placeholder", "Last Name can not be empty!");
     	}else{
     		$("#editLastName").removeClass("is-invalid");
     	}
     	if (country == null || country == ""){
     		$("#editCountry").addClass("is-invalid");
     		$("#editCountry").attr("placeholder", "Country can not be empty!");
     	}else{
     		$("#editCountry").removeClass("is-invalid");
     	}
     	if (city == null || country == ""){
     		$("#editCity").addClass("is-invalid");
     		$("#editCity").attr("placeholder", "City can not be empty!");
     	}else{
     		$("#editCity").removeClass("is-invalid");
     	}
    	if (phoneNumber == null || phoneNumber == ""){
     		$("#editPhoneNumber").addClass("is-invalid");
     		$("#editPhoneNumber").attr("placeholder", "Phone number can not be empty!");
     	}else{
     		$("#editPhoneNumber").removeClass("is-invalid");
     	}
    	if (address == null || address == ""){
     		$("#editAddress").addClass("is-invalid");
     		$("#editAddress").attr("placeholder", "Address can not be empty!");
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
		if (firstName != "" && lastName != "" && country !="" && city!="" && address!="" && phoneNumber != ""){
		 $.ajax({
	        	type: 'PUT',
	        	url: 'patientApi/updatePatient',
	        	data: JSON.stringify(formData),
	        	dataType: 'json',
	        	contentType: "application/json",
			    dataType: "json",
			    success:function(data) {
			        alert('Patient saved!');},
			   error : function(e) {
			            alert("Error!")
			            console.log("ERROR: ", e);
			          }
	        });
		}
		
    });
		
		

    function unauthorized(){
    	document.write("<html><head></head><body>UNAUTHORIZED</body></html>");
    }

