$(document).ready(function () {
    
    $.ajax ({
    	type: 'GET',
    	url: 'nurseApi/findOneByEmail',
	    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
    	statusCode: {
    		200: function(responseObject, textStatus, jqXHR) {
    			console.log("Nurse - findByEmail() - 200 OK");
    			nurseAllOK(responseObject);
    		},
    		204: function(responseObject, textStatus, jqXHR) {
    			console.log("Nurse - findByEmail() - 204 No Content");
    			nurseAllNO(responseObject);
    		},
			403: function(responseObject, textStatus, jqXHR) {
				console.log("403 Unauthorized");
				unauthorized();
			}
    	}
    });
    
    $("#addRequestForm").submit(function (event) {
        event.preventDefault();

    	var email = $("#email").val();
    	var dateFrom = $("#dateFrom").val();
    	var dateTo = $("#dateTo").val();
    	
    	if(dateFrom == null || dateFrom == "") {
			$("#dateFrom").addClass("is-invalid");
		} else {
			$("#dateFrom").removeClass("is-invalid");
		}
    	
    	if(dateTo == null || dateTo == "") {
			$("#dateTo").addClass("is-invalid");
		} else {
			$("#dateTo").removeClass("is-invalid");
		}
    	
    	if(dateFrom != null && dateFrom != "" && dateFrom != null && dateFrom != "") {
    		$.ajax ({
    	    	type: 'PUT',
    	    	url: 'leaveRequestApi/addLeaveRequest/' + email,
    		    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
    		    data : JSON.stringify({
					"dateFrom" : dateFrom,
					"dateTo": dateTo
				}),
				contentType: "application/json; charset=utf-8",
			    dataType: "json",
    	    	statusCode: {
    	    		200: function(responseObject, textStatus, jqXHR) {
    	    			console.log("LeaveRequest - addLeaveRequest() - 200 OK");
    	    			Swal.fire({
			        		  position: 'center',
			        		  icon: 'error',
			        		  title: 'Leave request successfully submited!',
			        		  showConfirmButton: false,
			        		  timer: 1500
			        		})
    	    		},
    	    		400: function(responseObject, textStatus, jqXHR) {
    	    			console.log("LeaveRequest - addLeaveRequest() - 400 Bad request");
    	    			Swal.fire({
			        		  position: 'center',
			        		  icon: 'error',
			        		  title: 'Something went wrong!',
			        		  showConfirmButton: false,
			        		  timer: 1500
			        		})
    	    		},
    				403: function(responseObject, textStatus, jqXHR) {
    					console.log("403 Unauthorized");
    					unauthorized();
    				}
    	    	}
    	    });
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
	Swal.fire({
		  position: 'center',
		  icon: 'error',
		  title: 'Something went wrong!',
		  showConfirmButton: false,
		  timer: 1500
		})
}

function unauthorized(){
	document.write("<html><head></head><body>UNAUTHORIZED</body></html>");
}