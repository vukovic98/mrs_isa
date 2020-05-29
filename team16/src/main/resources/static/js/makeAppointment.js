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

    			
    		},
			403: function(responseObject, textStatus, jqXHR) {
				console.log("403 Unauthorized");
				unauthorized();
			}
    	}
    });
    $.ajax ({
    	type: 'GET',
    	url: '/clinicApi/findAll',
    	headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
    	statusCode: {
    		200: function(responseObject, textStatus, jqXHR) {
    			console.log("200 OK");
    			loadClinicsAllOK(responseObject);
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

$("#searchButton").click(function(){
	var appType = $("#appointmentTypes").val();
	var avgGrade = $("#avgGrade").val();
	var date = $("date").val();
	console.log(appType+" "+ avgGrade+" "+date);
	
});


function unauthorized(){
	document.write("<html><head></head><body>UNAUTHORIZED</body></html>");

}