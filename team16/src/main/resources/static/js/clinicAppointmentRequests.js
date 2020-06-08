$( document ).ready(function() {
    console.log( "ready!" );
    
    $.ajax ({
    	type: 'GET',
    	url: 'appointmentRequestApi/findAll',
        headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
    	statusCode: {
    		200: function(responseObject, textStatus, jqXHR) {
    			console.log("200 OK");
    			loadRequestsOK(responseObject);
    		},
    		204: function(responseObject, textStatus, jqXHR) {
    			console.log("204 No Content");
    			loadRequestsNO(responseObject);
    		},
    		403: function(responseObject, textStatus, jqXHR) {
    			console.log("403 Unauthorized");
    			unauthorized();
    		}
    	}
    });
   
    
    
  
});


function loadRequestsOK(requests){
	  var table = $("#requestsBody");
	  table.empty();

	  
	  $.each(requests, function(i, val) {
	    var row = $("<tr id=\""+val.requestId+"\"></tr>");

	    row.append("<td id=\""+val.requestId+"\">" + val.patientName + "</td>");
	    row.append("<td id=\""+val.requestId+"\">" + val.dateTime + "</td>");
	    row.append("<td id=\""+val.requestId+"\">" + val.doctorName + "</td>");
		row.append("<td id=\"" + val.requestId + "\"><button id=\"btnRooms\" type=\"button\" class=\"btn btn-primary\">Rooms</button></td>")
		table.append(row);
	    

	    

	  });
	  $(document).on('click', '#btnRooms', function () {
	    	console.log($(this).parent().parent().attr('id'));
		    $.ajax ({
		    	type: 'POST',
		    	url: 'ordinationApi/findAllFree',
			  	data : JSON.stringify({
					"dateTime" : val.dateTime,
				}),
		        headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
	            contentType: "application/json; charset=utf-8",
	            dataType: "json",
		    	statusCode: {
		    		200: function(responseObject, textStatus, jqXHR) {
		    			console.log("200 OK");
		    			var select = "<select id=\"ordSelect\">";
		    			$.each(responseObject, function(i, val) {
		    			    select = select + "<option value=\""+ val.ordId + "\">"+val.name+"</option>";
		    			});
		    		    
		    		},
		    		204: function(responseObject, textStatus, jqXHR) {
		    			console.log("204 No Content");
		    			loadRequestsNO(responseObject);
		    		},
		    		403: function(responseObject, textStatus, jqXHR) {
		    			console.log("403 Unauthorized");
		    			unauthorized();
		    		}
		    	}
		    });
	    	var roomID = $(this).parent().parent().attr('id');
	    	
	    	$("#ordinationModal").modal();
		  
	  });
}


function ordModal(){
	
	
}

function loadRequestsNO(requests){
	
	
}