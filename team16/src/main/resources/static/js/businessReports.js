$(document).ready(function(){
	 var idAdmin;
	 
	  $.ajax({
		    type: 'GET',
		    url: 'clinicApi/getCurrentClinic',
		    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
		    statusCode: {
		      200: function(responseObject, textStatus, jqXHR) {
		    	  $.ajax({
		  		    type: 'GET',
		  		    url: 'clinicApi/getClinicGrade/' + responseObject,
		  		    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
		  		    statusCode: {
		  		      200: function(responseObject, textStatus, jqXHR) {
		  		        console.log("Rooms - findAll() - 200 OK");
		  		        businessAllOK(responseObject);
		  		      },
		  		      204: function(responseObject, textStatus, jqXHR) {
		  		        console.log("Rooms - findAll() - 204 No Content");
		  		        businessAllNO(responseObject);
		  		      },
		  				403: function(responseObject, textStatus, jqXHR) {
		  					console.log("403 Unauthorized");
		  					unauthorized();
		  				}
		  		    }
		  		  });
		      },
		      204: function(responseObject, textStatus, jqXHR) {

		      },
				403: function(responseObject, textStatus, jqXHR) {
					console.log("403 Unauthorized");
					unauthorized();
				}
		    }
		  });
	

	  
	  

	  
});

function businessAllOK(clinic){
	  
	 $("#averageClinic").text('The average grade of the clinic is '+clinic.averageGrade+'.')
	  
}