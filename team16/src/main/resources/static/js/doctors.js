$( document ).ready(function() {
    console.log( "ready!" );
    
    $.ajax({
	    type: 'GET',
	    url: 'userApi/getRole' ,
	    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
	    statusCode: {
	      200: function(responseObject, textStatus, jqXHR) {
	    	  if(responseObject == "CLINIC_ADMINISTRATOR") {
	    		  $.ajax ({
	    		    	type: 'GET',
	    		    	url: 'doctorApi/findAll',
	    			    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
	    		    	statusCode: {
	    		    		200: function(responseObject, textStatus, jqXHR) {
	    		    			console.log("Doctors - findAll() - 200 OK");
	    		    			doctorAllOK(responseObject);
	    		    		},
	    		    		204: function(responseObject, textStatus, jqXHR) {
	    		    			console.log("Doctors - findAll() - 204 No Content");
	    		    			doctorAllNO(responseObject);
	    		    		},
	    					403: function(responseObject, textStatus, jqXHR) {
	    						console.log("403 Unauthorized");
	    						unauthorized();
	    					}
	    		    	}
	    		    });
	    	  } else if(responseObject == "PATIENT") {
	    		  $.ajax ({
	    		    	type: 'GET',
	    		    	url: 'doctorApi/findAllDoctorsDTO',
	    			    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
	    		    	statusCode: {
	    		    		200: function(responseObject, textStatus, jqXHR) {
	    		    			console.log("Doctors - findAll() - 200 OK");
	    		    			doctorAllOK(responseObject);
	    		    		},
	    		    		204: function(responseObject, textStatus, jqXHR) {
	    		    			console.log("Doctors - findAll() - 204 No Content");
	    		    			doctorAllNO(responseObject);
	    		    		},
	    					403: function(responseObject, textStatus, jqXHR) {
	    						console.log("403 Unauthorized");
	    						unauthorized();
	    					}
	    		    	}
	    		    });
	    	  }
	      },
	      204: function(responseObject, textStatus, jqXHR) {
	    	  console.log("ERROR");
	      },
			403: function(responseObject, textStatus, jqXHR) {
				console.log("403 Unauthorized");
				unauthorized();
			}
	    }
	  });
    
    $(".modalDoctor").click(function(e) {

        var email = $(this).attr('id');

        $.ajax({
        	type: 'GET',
        	url: 'doctorApi/findOneByEmail',
        	dataType: 'json',
        	data: {
        		"email": email 
        	},
        	success: function(doctor) {
        		$("#modalName").text(doctor.firstName);
		        $("#modalEmail").text(doctor.email);
		        $("#modalAddress").text(doctor.Address);
		        $("#modalPhone").text(doctor.phoneNumber);
		        $("#modalCity").text(doctor.city);
		        $("#modalCountry").text(doctor.country);
		        $("#modalInsNumber").text(doctor.insuranceNumber);
		        
		        $("#userModal").show();
        	}
        });
    });
});

