$( document ).ready(function() {
    console.log( "ready!" );
    
    $.ajax ({
    	type: 'GET',
    	url: 'appointmentRequestApi/findAllUnapproved',
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
		row.append("<td style=\"text-align:center;\" id=\"" + val.requestId + "\"><button id=\"btnRooms\" type=\"button\" class=\"btn btn-primary\">Assign Room</button></td>")
		table.append(row);
	    

	    

	  });
	  
	  

	  
	  $(document).on('click', '#btnRooms', function () {
	    	var id = $(this).parent().parent().attr('id');
	    	$('#requestId').text(id);
	    	$('#roomSelectModal')
	        .find('option')
	        .remove()
	        .end()
	        .append('<option value="NoRoom">No room selected...</option>')
	        .val('NoRoom')
	    	
	        $('#termModal')
	        .find('option')
	        .remove()
	        .end()
	        
		    $.ajax ({
		    	type: 'GET',
		    	url: 'appointmentRequestApi/findInfoForRequest/' + id,
		        headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
	            contentType: "application/json; charset=utf-8",
	            dataType: "json",
		    	statusCode: {
		    		200: function(responseObject, textStatus, jqXHR) {
		    			console.log("200 OK");
		    			$("#doctorModal").val(responseObject.doctor);
		    			$("#dateModal").val(responseObject.dateTime);
		    			$("#termModal").val(responseObject.onlyTime);
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
	    	
		    $.ajax ({
		    	type: 'GET',
		    	url: 'ordinationApi/findAllFreeForRequest/' + id,
		        headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
	            contentType: "application/json; charset=utf-8",
	            dataType: "json",
		    	statusCode: {
		    		200: function(responseObject, textStatus, jqXHR) {
		    			console.log("200 OK");
		    			var selectRoom = $("#roomSelectModal");
		    			
		    			
		    			
		    			 $.each(responseObject, function(i, val) {
		    				 selectRoom.append("<option value=\""+val.ordId+"\">"+val.name+"</option>");
		    			 });
		    			 
		    		},
		    		202: function(responseObject, textStatus, jqXHR) {
		    			console.log("204 No Content");
		                Swal.fire({
			          		  position: 'center',
			          		  icon: 'error',
			          		  title: 'There are no free rooms for this time. Please select a room to view the next available time.',
			          		  showConfirmButton: true,
			          		  timer: 1500
			          		})
			          		var selectRoom = $("#roomSelectModal");
			    			 $.each(responseObject, function(i, val) {
			    				 selectRoom.append("<option id=\""+val.nextTerm+"\" value=\""+val.ordId+"\">"+val.name+"</option>");
			    			 });
		                
		                	
			          		
			          		
			          		
		    		},
		    		403: function(responseObject, textStatus, jqXHR) {
		    			console.log("403 Unauthorized");
		    			unauthorized();
		    		}
		    	}
		    });
		    
		    
	    	
	    	$("#ordinationModal").modal();
		  
	  });
	  

	  $(document).on('click', '#modalApproveBtn', function () {
		  	var idReq = $('#requestId').text();
		  	var idRoom = $("#roomSelectModal").val();
		  
	       $.ajax({
	            type: 'PUT',
	            url: 'appointmentRequestApi/approveRequest',
	            headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
	            data : JSON.stringify({
	              "ordId" : idRoom,
	              "requestId" : idReq
	            }),
	            contentType: "application/json; charset=utf-8",
	            dataType: "json",
	            statusCode: {
	              200: function(responseObject, textStatus, jqXHR) {
	                console.log("Ordination - add() - 200 OK");
	                //showMessage("Ordination successfully added!", "palegreen");
	        	    
	                Swal.fire({
		          		  position: 'center',
		          		  icon: 'success',
		          		  title: 'Request successfully approved!',
		          		  showConfirmButton: true,
		          		  timer: 1500
		          		})
	                
	        	      
	              },
	              400: function(responseObject, textStatus, jqXHR) {
	                console.log("Ordination - add() - 400 Bad request");
	                showMessage("Ordination with inserted name already exists!", "antiquewhite");
	              },
	    		  403: function(responseObject, textStatus, jqXHR) {
	    			console.log("403 Unauthorized");
	    			unauthorized();
	    		  }
	            }
	          });
		});
	  
	  
	  
	  $("#roomSelectModal").on('change', function() {
		  console.log($("#roomSelectModal option:selected").attr('id'));
		  
		  var dateTime = $("#roomSelectModal option:selected").attr('id').split(" ");
		  
			$("#dateModal").val(dateTime[0]);
			$("#termModal").val(dateTime[1]);

		  
		  
		});
}


function ordModal(){
	
	
}

function loadRequestsNO(requests){
	
	
}