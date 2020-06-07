$( document ).ready(function() {
    console.log( "ready!" );
    
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

function doctorAllOK(doctorList) {
	var table = $("#doctorBody");
	table.empty();
	
	$.each(doctorList, function(i, val) {
		var row = $("<tr id=\""+i+"\"></tr>");

		row.append("<td class=\"w-50 modalDoctor\" id=\""+val.email+"\">" + val.firstName + " " + val.lastName + "</td>");
		row.append("<td class=\"w-50 text-right\"><button type=\"button\" class=\"btn btn-outline-primary mr-3\">Review</button><button type=\"button\" class=\"btn btn-outline-danger\">Fire</button></td>");

		table.append(row);
	});
}

function doctorAllNO(responseObject) {
	var table = $("#doctorBody");
	table.empty();
	
	var row = $("<tr></tr>");
	row.append("<td class='pl-1'>There are no doctors in this clinic.</td>");
	
	table.append(row);
}
function unauthorized(){
	document.write("<html><head></head><body>UNAUTHORIZED</body></html>");
}