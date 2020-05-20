$( document ).ready(function() {
    console.log( "ready!" );
    
    $.ajax ({
    	type: 'GET',
    	url: '/pricelistItemApi/findAllAppointmentTypes',
	    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
    	statusCode: {
    		200: function(responseObject, textStatus, jqXHR) {
    			console.log("200 OK");
    			loadAppointmentTypesAllOK(responseObject);
    		},
    		204: function(responseObject, textStatus, jqXHR) {
    			console.log("204 No Content");
    			loadAppointmentTypesNO(responseObject);
    		},
			403: function(responseObject, textStatus, jqXHR) {
				console.log("403 Unauthorized");
				unauthorized();
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

function unauthorized(){
	document.write("<html><head></head><body>UNAUTHORIZED</body></html>");
}