$( document ).ready(function() {
    console.log( "ready!" );
    
    $(".modalCA").click(function(e) {

        var email = $(this).attr('id');

        $.ajax({
        	type: 'GET',
        	url: 'clinicAdminApi/findOneByEmail',
        	dataType: 'json',
        	data: {
        		"email": email 
        	},
        	success: function(patient) {
        		$("#modalName").text(patient.firstName);
		        $("#modalEmail").text(patient.email);
		        $("#modalAddress").text(patient.Address);
		        $("#modalPhone").text(patient.phoneNumber);
		        $("#modalCity").text(patient.city);
		        $("#modalCountry").text(patient.country);
		        $("#modalInsNumber").text(patient.insuranceNumber);
		        
		        $("#exampleModal").show();
        	}
        });
    });
});