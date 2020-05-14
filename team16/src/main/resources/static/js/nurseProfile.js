$(document).ready(function () {
    
    $.ajax ({
    	type: 'GET',
    	url: 'nurseApi/findOneByEmail',
    	statusCode: {
    		200: function(responseObject, textStatus, jqXHR) {
    			console.log("Nurse - findOneByEmail() - 200 OK");
    			nurseAllOK(responseObject);
    		},
    		204: function(responseObject, textStatus, jqXHR) {
    			console.log("Nurse - findOneByEmail() - 204 No Content");
    			nurseAllNO(responseObject);
    		}
    	}
    });

});


function nurseAllOK(nurse) {
	$("#name").val(nurse.name);
    $("#email").val(nurse.email);
    $("#city").val(nurse.city);
    $("#country").val(nurse.country);
    $("#phone").val(nurse.phone);
    $("#clinic").val(nurse.clinic);
    $("#address").val(nurse.address);
}

function nurseAllNO(responseObject) {
	alert("ERROR!");
}