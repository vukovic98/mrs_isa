 $( document ).ready(function() {
    console.log( "ready!" );
    
    $.ajax ({
    	type: 'GET',
    	url: '/patientApi/appointmentHistory',
    	headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
    	statusCode: {
    		200: function(responseObject, textStatus, jqXHR) {
    			console.log("200 OK");
    			loadAppHistoryAllOK(responseObject);
    		},
    		204: function(responseObject, textStatus, jqXHR) {
    			console.log("204 No Content");
    			loadAppHistoryNO(responseObject);
    		},
    		403: function(responseObject, textStatus, jqXHR) {
    			console.log("403 Unauthorized");
    			unauthorized();
    		}
    	}
    });
  
});
 function formatDate(date) {
	  var hours = date.getHours();
	  var minutes = date.getMinutes();
	  var ampm = hours >= 12 ? 'PM' : 'AM';
	  hours = hours % 12;
	  hours = hours ? hours : 12; // the hour '0' should be '12'
	  minutes = minutes < 10 ? '0'+minutes : minutes;
	  var strTime = hours + ':' + minutes + ' ' + ampm;
	  return date.getDate() + "-" + (date.getMonth()+1) + "-" + date.getFullYear() + "  " + strTime;
	}
    function loadAppHistoryAllOK(appointments) {
    	
    	var table = $("#appointmentHistoryBody");
    	table.empty();
    	
    	if(appointments.length != 0){
	    	$.each(appointments, function(i, val) {
	    		console.log(val);
	    		var row = $("<tr id=\""+i+"\"></tr>");
	    		var d = new Date(val.datetime);
	    		var now = new Date();
	    		now = Date.now();
	    		console.log(now);
	    		var date = formatDate(d);
	    		row.append("<td class=\"w-50\">" + date + "</td>");
	    		row.append("<td class=\"w-50\">" + val.appointmentType + "</td>");
	    		row.append("<td class=\"w-50\">" + val.clinic + "</td>");
	    		row.append("<td class=\"w-50\">" + val.doctor + "</td>");
	    		row.append("<td class=\"w-50\">" + "No" + "</td>");
	    		row.append("<td class=\"w-50 modalTD\" id=\"1\" align=\"center\"><button type=\"button\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"Click to rate clinic and doctor\" class=\"btn btn-indigo btn-sm m-0\"><i class=\"fa fa-star\"></i></button></td>");
	    		table.append(row);
	    	});
    	}
    	else{
    		var row = $("<tr></tr>");

    		row.append("<td class=\"w-50 text-center\" colspan=\"4\"> There are no appointments to show.</td>");
    		table.append(row);
    	}
    }
    	
    function unauthorized(){
    	document.write("<html><head></head><body><h1>UNAUTHORIZED</h1></body></html>");
    }
    	
    
    function loadPatientInfoNO(patient){
    	showMessage("Patient Not Found","antiquewhite");	
    }
    function showMessage(message, color) {
    	$("#message_bar").css("background", color);
    	$("#message_bar").text(message);
    	$("#message_bar").slideDown().delay(1500).slideUp();
    }
    
    $(document).on('click', '.modalTD', function () {
    	$("#exampleModal").modal();
    	
    });
    
    function searchAppointments() {
		// Declare variables
		var input, filter, table, tr, td, i, j, txtValue;
		input = document.getElementById("myInput");
		filter = input.value.toUpperCase();
		table = document.getElementById("myTable");
		tr = table.getElementsByTagName("tr");


		// Loop through all table rows, and hide those who don't match the search query
		for (i = 0; i < tr.length; i++) {
			let rowTds = tr[i].getElementsByTagName("td")
			for (j = 0; j < rowTds.length; j++) {
				td = tr[i].getElementsByTagName("td")[j];
				if (td) {
					if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
						tr[i].style.display = "";
						break; // this will break the row looping on j after making the row visible.
					} else {
						tr[i].style.display = "none";
					}
				}
			}

		}

	}