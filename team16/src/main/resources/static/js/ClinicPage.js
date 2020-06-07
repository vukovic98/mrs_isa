$( document ).ready(function() {
	
	  $.ajax({
		    type: 'GET',
		    url: 'clinicApi/getCurrentClinicInfo',
		    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
		    statusCode: {
		      200: function(responseObject, textStatus, jqXHR) {
		    	 clinicOK(responseObject);
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

function clinicOK(clinic){
	$("#name").val(clinic.name);
	$("#clinicAddress").val(clinic.address);
	$("#desc").val(clinic.description);
	
	
}