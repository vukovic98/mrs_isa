$(document).ready(function () {
    
    $.ajax ({
    	type: 'GET',
    	url: 'nurseApi/findOneByEmail',
    	statusCode: {
    		200: function(responseObject, textStatus, jqXHR) {
    			console.log("Nurse - findByEmail() - 200 OK");
    			nurseAllOK(responseObject);
    		},
    		204: function(responseObject, textStatus, jqXHR) {
    			console.log("Nurse - findByEmail() - 204 No Content");
    			nurseAllNO(responseObject);
    		}
    	}
    });

});

function nurseAllOK(nurse) {
	$("#name").val(nurse.name);
    $("#email").val(nurse.email);
    $("#phone").val(nurse.phone);
    $("#clinic").val(nurse.clinic);
}

function nurseAllNO(responseObject) {
	alert("ERROR!");
}