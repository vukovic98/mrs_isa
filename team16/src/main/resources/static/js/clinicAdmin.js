$( document ).ready(function() {
    console.log( "ready!" );
    
    $.ajax ({
    	type: 'GET',
    	url: 'clinicAdminApi/findOneByEmail',
        headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
    	statusCode: {
    		200: function(responseObject, textStatus, jqXHR) {
    			console.log("200 OK");
    			loadAdminInfoAllOK(responseObject);
    		},
    		204: function(responseObject, textStatus, jqXHR) {
    			console.log("204 No Content");
    			loadAdminInfoAllNO(responseObject);
    		},
    		403: function(responseObject, textStatus, jqXHR) {
    			console.log("403 Unauthorized");
    			unauthorized();
    		}
    	}
    });
   
  
});


    function loadAdminInfoAllOK(admin) {
    	
    	$("#patientFullName").text(admin.firstName + " " + admin.lastName);
    	
    	
    	$("#fullName").val(admin.firstName+" "+admin.lastName);
    	$("#country").val(admin.country);
    	$("#city").val(admin.city);
    	$("#address").val(admin.address);
    	$("#phoneNumber").val(admin.phoneNumber);
    	$("#insNumber").val(admin.insuranceNumber);
    	
    	$("#editFirstName").val(admin.firstName);
    	$("#editLastName").val(admin.lastName);
    	$("#editCountry").val(admin.country);
    	$("#editCity").val(admin.city);
    	$("#editAddress").val(admin.address);
    	$("#editPhoneNumber").val(admin.phoneNumber);
    	$("#editInsNumber").val(admin.insuranceNumber);
    	$("#editEmail").val(admin.email); 	
    }
    
    function loadAdminInfoAllNO(admin){
    	$("#patientFullName").text("Clinic admin Not Found");	
    }
    

   
    $("#myTab").click(function(e){
   	 $.ajax ({
   	    	type: 'GET',
   	    	url: 'clinicAdminApi/findOneByEmail',
   	    	headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
   	    	statusCode: {
   	    		200: function(responseObject, textStatus, jqXHR) {
   	    			console.log("200 OK");
   	    			loadAdminInfoAllOK(responseObject);
   	    		},
   	    		204: function(responseObject, textStatus, jqXHR) {
   	    			console.log("204 No Content");
   	    			loadAdminInfoAllNO(responseObject);
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
    	    	url: 'clinicAdminApi/findPassword',
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
function passValidation(Password){
	var pass = $("#oldPassword").val();
	var newPass = $("#newPassword").val();
	var confirmedPass = $("#confirmedPassword").val();
	
	if(Password != pass){
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
   	        	url: 'clinicAdminApi/changePassword',
   	        	headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
   	        	data: JSON.stringify(newPass),
   	        	dataType: 'json',
   	        	contentType: "application/json",
   			    success: function(data){
   			    	Swal.fire({
		        		  position: 'center',
		        		  icon: 'success',
		        		  title: 'Password changed!',
		        		  showConfirmButton: false,
		        		  timer: 1500
		        		})
   			    },
   			   error : function(e) {
   				Swal.fire({
	        		  position: 'center',
	        		  icon: 'error',
	        		  title: "Couldn't change password!",
	        		  showConfirmButton: false,
	        		  timer: 1500
	        		})
   			            console.log("ERROR: ", e);
   			          }
   	        });}
   		
    	 $("#oldPassword").val('');
    	 $("#newPassword").val('');
    	 $("#confirmedPassword").val('');
    	
}


    $("#saveAdmin").click(function(e){
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
		if (firstName != "" && lastName != "" && country !="" && city!="" && address!="" && phoneNumber != ""){
		 $.ajax({
	        	type: 'PUT',
	        	url: 'clinicAdminApi/updateAdmin',
	        	headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
	        	data: JSON.stringify(formData),
	        	dataType: 'json',
	        	contentType: "application/json",
			    success : 
			    	Swal.fire({
		        		  position: 'center',
		        		  icon: 'success',
		        		  title: 'Saved!',
		        		  showConfirmButton: false,
		        		  timer: 1500
		        		})
   			    ,
			   error : function(e) {
				   Swal.fire({
		        		  position: 'center',
		        		  icon: 'error',
		        		  title: 'Something went wrong, please try again!',
		        		  showConfirmButton: false,
		        		  timer: 1500
		        		})
			            console.log("ERROR: ", e);
			          }
	        });
		}
		
    });

    function unauthorized(){
    	document.write("<html><head></head><body>UNAUTHORIZED</body></html>");
    }

