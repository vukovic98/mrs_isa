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
    			loadAppointmentTypesNO(responseObject);
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