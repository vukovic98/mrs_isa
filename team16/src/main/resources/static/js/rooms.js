$(document).ready(function(){
	
	console.log("ready");

	  $.ajax({
	    type: 'GET',
	    url: 'ordinationApi/findAll',
	    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
	    statusCode: {
	      200: function(responseObject, textStatus, jqXHR) {
	        console.log("Rooms - findAll() - 200 OK");
	        roomsAllOK(responseObject);
	      },
	      204: function(responseObject, textStatus, jqXHR) {
	        console.log("Rooms - findAll() - 204 No Content");
	        roomsAllNO(responseObject);
	      },
			403: function(responseObject, textStatus, jqXHR) {
				console.log("403 Unauthorized");
				unauthorized();
			}
	    }
	  });
	
	  
	  $.ajax({
		    type: 'GET',
		    url: 'appointmentApi/findAllAppointments',
		    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
		    statusCode: {
		      200: function(responseObject, textStatus, jqXHR) {
		        console.log("Appointments - findAll() - 200 OK");
		        appointmentsAllOK(responseObject);
		      },
		      204: function(responseObject, textStatus, jqXHR) {
		        console.log("Appointments - findAll() - 204 No Content");
		        appointmentsAllNO(responseObject);
		      },
				403: function(responseObject, textStatus, jqXHR) {
					console.log("403 Unauthorized");
					unauthorized();
				}
		    }
		  });
	  
	
	$('[data-toggle="tooltip"]').tooltip();
	  var actions = $("table td:last-child").html();
	  // Append table with add row form on add new button click
	    $(".add-new").click(function(){
	      var actions = $("table td:last-child").html();
	    $(this).attr("disabled", "disabled");
	    var index = $("table tbody tr:nth-child(0)").index();
	        var row = '<tr>' +
	            '<td><input type="text" class="form-control" name="name" id="name"></td>' +
	            '<td><select id="type" name="type"><option value="OPERATION">OPERATION</option><option value="EXAM">EXAM</option></select></td>' +
	            '<td>&nbsp</td>' +
	              '<td>' + actions + '</td>' +
	        '</tr>';
	      $("table").prepend(row);    
	    $("table tbody tr").eq(index + 1).find(".add").toggle();
	    });
	// Add row on add button click

	
	
    $(document).on("click", ".add", function(){
        var empty = false;
        var input = $(this).parents("tr").find('input[type="text"]');
        var select = $(this).parents("tr").find('select');
            input.each(function(){
          if(!$(this).val()){
            $(this).addClass("error");
            empty = true;
          } else{
                    $(this).removeClass("error");
                }
        });
        $(this).parents("tr").find(".error").first().focus();
        if(!empty){
          var dataOrdination = [];
          input.each(function(){
        	  dataOrdination.push($(this).val());
          });
          select.each(function(){
        	  dataOrdination.push($(this).val());
          });
          $.ajax({
            type: 'POST',
            url: 'ordinationApi/addOrdination',
            headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
            data : JSON.stringify({
              "name" : dataOrdination[0],
              "type" : dataOrdination[1]
            }),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            statusCode: {
              200: function(responseObject, textStatus, jqXHR) {
                console.log("Ordination - add() - 200 OK");
                showMessage("Ordination successfully added!", "palegreen");
                input.each(function(){
                  $(this).parent("td").html($(this).val());
                }); 
    			select.each(function(){
    				$(this).parent("td").html($(this).val());
    			});	
                $(this).parents("tr").find(".add").toggle();
                $(".add-new").removeAttr("disabled");
                
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
        }   
        });
	
	// Edit row on edit button click
	$(document).on("click", ".edit", function(){		
        $(this).parents("tr").find("td:not(:last-child)").each(function(){
			if($(this).is("td:first-child")){
			$(this).html('<input type="text" class="form-control" value="' + $(this).text() + '">');
			}else if($(this).is(".but")){
			
			}else{
			$(this).html('<select id="type"><option value="OPERATION">OPERATION</option><option value="EXAM">EXAM</option></select>');
			}
		});		
		$(this).parents("tr").find(".add, .edit").toggle();
		$(".add-new").attr("disabled", "disabled");
    });
	// Delete row on delete button click
	$(document).on("click", ".delete", function(){
		var id = $(this).parents("td").attr('id');
		
        $.ajax({
            type: 'DELETE',
            url: 'ordinationApi/deleteOrdination/' + id,
            headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            statusCode: {
              200: function(responseObject, textStatus, jqXHR) {
                console.log("Ordination - delete() - 200 OK");
                showMessage("Ordination successfully deleted!", "palegreen"); 
              },
              400: function(responseObject, textStatus, jqXHR) {
                console.log("Ordination - delete() - 400 Bad request");
                showMessage("Something went wrong!", "antiquewhite");
              },
      		403: function(responseObject, textStatus, jqXHR) {
    			console.log("403 Unauthorized");
    			unauthorized();
    		}
            }
          });
		
        $(this).parents("tr").remove();
		$(".add-new").removeAttr("disabled");
    });
});


function showMessage(message, color) {
	  $("#message_bar").css("background", color);
	  $("#message_bar").text(message);
	  $("#message_bar").slideDown().delay(1500).slideUp();
	}

function roomsAllOK(roomsList) {
	  var table = $("#roomsBody");
	  table.empty();

	  
	  $.each(roomsList, function(i, val) {
	    var row = $("<tr id=\""+i+"\"></tr>");

	    row.append("<td id=\""+val.id+"\">" + val.name + "</td>");
	    row.append("<td id=\""+val.id+"\">" + val.type + "</td>");
	    row.append("<td class=\"but\"><button class=\"btn btn-primary\" type=\"button\" data-toggle=\"modal\" data-target=\"#exampleModal\" aria-expanded=\"false\" aria-controls=\"exampleModal\">Appointments</button></td>");
	    row.append("<td id=\""+val.name+"\">" + "<a class=\"add\" title=\"Add\" data-toggle=\"tooltip\"><i class=\"material-icons\">&#xE03B;</i></a>" +
		                            "<a class=\"edit\" title=\"Edit\" data-toggle=\"tooltip\"><i class=\"material-icons\">&#xE254;</i></a>" +
		                            "<a class=\"delete\" title=\"Delete\" data-toggle=\"tooltip\"><i class=\"material-icons\">&#xE872;</i></a>" + "</td>");
	    table.append(row);
	  });
	}

	function roomsAllNO(responseObject) {
	  var table = $("#roomsBody");
	  table.empty();
	  
	  var row = $("<tr></tr>");
	  row.append("<td class='pl-1'>There are no rooms in the system.</td>");
	  
	  table.append(row);
	}
	
	function appointmentsAllOK(appointmentList) {
		  var table = $("#appointmentsBody");
		  table.empty();

		  
		  $.each(appointmentList, function(i, val) {
		    var row = $("<tr id=\""+i+"\"></tr>");

		    row.append("<td id=\""+val.id+"\">" + val.date + "</td>");
		    row.append("<td id=\""+val.id+"\">" + val.duration + "</td>");
		    row.append("<td id=\""+val.id+"\">" + val.doctor + "</td>");
		    row.append("<td id=\""+val.id+"\">" + val.patient + "</td>");
		    table.append(row);
		  });
		}

		function appointmentsAllNO(responseObject) {
		  var table = $("#appointmentsBody");
		  table.empty();
		  
		  var row = $("<tr></tr>");
		  row.append("<td class='pl-1'>There are no appointments in the system.</td>");
		  
		  table.append(row);
		}
		function unauthorized(){
			document.write("<html><head></head><body>UNAUTHORIZED</body></html>");
		}

function myFunction() {
	  // Declare variables
	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("myInput");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("myTable");
	  tr = table.getElementsByTagName("tr");

	  // Loop through all table rows, and hide those who don't match the search query
	  for (i = 0; i < tr.length; i++) {
	    td = tr[i].getElementsByTagName("td")[0];
	    if (td) {
	      txtValue = td.textContent || td.innerText;
	      if (txtValue.toUpperCase().indexOf(filter) > -1) {
	        tr[i].style.display = "";
	      } else {
	        tr[i].style.display = "none";
	      }
	    }
	  }
	}