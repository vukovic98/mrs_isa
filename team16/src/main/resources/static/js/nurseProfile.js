$(document).ready(function () {
    
    $.ajax ({
    	type: 'GET',
    	url: 'nurseApi/findOneByEmail',
        headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
    	statusCode: {
    		200: function(responseObject, textStatus, jqXHR) {
    			console.log("Nurse - findOneByEmail() - 200 OK");
    			nurseAllOK(responseObject);
    		},
    		204: function(responseObject, textStatus, jqXHR) {
    			console.log("Nurse - findOneByEmail() - 204 No Content");
    			nurseAllNO(responseObject);
    		},
    		403: function(responseObject, textStatus, jqXHR) {
    			console.log("403 Unauthorized");
    			unauthorized();
    		}
    	}
    });

});


function unauthorized(){
	document.write("<html><head></head><body>UNAUTHORIZED</body></html>");
}

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
	Swal.fire({
		  position: 'center',
		  icon: 'error',
		  title: 'Something went wrong!',
		  showConfirmButton: false,
		  timer: 1500
		})
}