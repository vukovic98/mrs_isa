$( document ).ready(function() {
    console.log( "ready!" );
    
    $.ajax ({
    	type: 'GET',
    	url: '/pricelistItemApi/findAllAppointmentTypes',
    	statusCode: {
    		200: function(responseObject, textStatus, jqXHR) {
    			console.log("200 OK");
    			loadAppointmentTypesAllOK(responseObject);
    		},
    		204: function(responseObject, textStatus, jqXHR) {
    			console.log("204 No Content");
    			
    		}
    	}
    });
    $.ajax ({
    	type: 'GET',
    	url: '/clinicApi/findAll',
    	statusCode: {
    		200: function(responseObject, textStatus, jqXHR) {
    			console.log("200 OK");
    			loadClinicsAllOK(responseObject);
    		},
    		204: function(responseObject, textStatus, jqXHR) {
    			console.log("204 No Content");
    	
    		}
    	}
    });
  
});

function loadAppointmentTypesAllOK(pricelistItems){
	 var select = $("#appointmentTypes");
	  select.empty();
	 
	  $.each(pricelistItems, function(i, val) {
		   
		    var option = $("<option>"+val.appointmentType+"</option>");
		    select.append(option);
		  });
}
function loadClinicsAllOK(clinics){
	 var select = $("#locations");
	  select.empty();
	 
	  $.each(clinics, function(i, val) {
		   
		    var option = $("<option>"+val.address+"</option>");
		    select.append(option);
		  });
}