$( document ).ready(function() {
	
	$.ajax ({
    	type: 'GET',
    	url: 'userApi/getRole',
    	headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
    	statusCode: {
    		200: function(responseObject, textStatus, jqXHR) {
    			console.log("ClinicalCenterAdmins - findAll() - 200 OK");
    			
    			if(responseObject == "CLINIC_ADMINISTRATOR"){
    				$.ajax({
    				    type: 'GET',
    				    url: 'clinicApi/getCurrentClinicInfo',
    				    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
    				    statusCode: {
    				      200: function(responseObject, textStatus, jqXHR) {
    				    	 clinicOK(responseObject);
    				      },
    				      204: function(responseObject, textStatus, jqXHR) {

    				      },
    						403: function(responseObject, textStatus, jqXHR) {
    							console.log("403 Unauthorized");
    							unauthorized();
    						}
    				    }
    				  });
    				
    				$.ajax ({
	    		    	type: 'GET',
	    		    	url: 'doctorApi/findAllDoctorsDTOCurrentClinic',
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
    				
    				$.ajax({
    				    type: 'GET',
    				    url: 'ordinationApi/findAllCurrentClinicRooms',
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
    					    url: 'appointmentApi/findAll',
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
    				  
    				  $.ajax({
    					    type: 'GET',
    					    url: 'pricelistItemApi/findAllAppointmentTypes',
    					    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
    					    statusCode: {
    					      200: function(responseObject, textStatus, jqXHR) {
    					        console.log("Pricelist - findAll() - 200 OK");
    					        pricelistAllOK(responseObject);
    					      },
    					      204: function(responseObject, textStatus, jqXHR) {
    					        console.log("Pricelist - findAll() - 204 No Content");
    					        pricelistAllNO(responseObject);
    					      },
    							403: function(responseObject, textStatus, jqXHR) {
    								console.log("403 Unauthorized");
    								unauthorized();
    							}
    					    }
    					  });
    				
    			} else if(responseObject == "PATIENT"){
    				$("#price").css('display', 'none');
    				$("#rooms").css('display', 'none');
    				$("#appointments").css('display', 'none');
    				
    				$("#li_price").css('display', 'none');
    				$("#li_rooms").css('display', 'none');
    				$("#li_appointments").css('display', 'none');
    				
    				var atrs = sessionStorage.getItem('appParam');
    				var clinicID = undefined;
    				var appType = undefined;
    				var date = undefined;
    				
    				if(atrs.includes("&")) {
    					var attrs2 = atrs.split("&");
    					clinicID = attrs2[0];
    					appType = attrs2[1];
        				date = attrs2[2];
    				} else {
    					clinicID = atrs;
    				}
	
    				$.ajax({
    				    type: 'GET',
    				    url: 'clinicApi/getClinicInfo/' + clinicID,
    				    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
    				    statusCode: {
    				      200: function(responseObject, textStatus, jqXHR) {
    				    	 $("#clinicName").text(responseObject.name);
    				    	 $("#profileInfo").removeClass("show");
    				    	 $("#profileInfo").removeClass("active");
    				    	 $("#profile-tab").removeClass("active");
    				    	 
    				    	 $("#doctor").addClass("show");
    				    	 $("#doctor").addClass("active");
    				    	 $("#doctor-tab").addClass("active");
    				    	 clinicOK(responseObject);
    				      },
    				      204: function(responseObject, textStatus, jqXHR) {

    				      },
    						403: function(responseObject, textStatus, jqXHR) {
    							console.log("403 Unauthorized");
    							unauthorized();
    						}
    				    }
    				  });
    				
    				if(appType == undefined) {
	    				$.ajax ({
		    		    	type: 'GET',
		    		    	url: 'doctorApi/findAllDoctorsDTOByClinic/' + clinicID,
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
    				} else {
    					$.ajax ({
    					  	type: 'GET',
    					  	url: 'clinicApi/findAllAppointmentDoctors/' + clinicID + "&" + appType + "&" + date,
    						headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
    					  	statusCode: {
    					  		200: function(responseObject, textStatus, jqXHR) {
    					  			console.log("200 OK");
    					  			showDoctors(responseObject);
    					  		},
    					  		204: function(responseObject, textStatus, jqXHR) {
    					  			console.log("204 No Content");
    					  		},
    							403: function(responseObject, textStatus, jqXHR) {
    								console.log("403 Unauthorized");
    								unauthorized();
    							}
    					  	}
    					  });
    				}
    			}
    				
    		},
    		204: function(responseObject, textStatus, jqXHR) {
    			console.log("ClinicalCenterAdmins - findAll() - 204 No Content");
    			showMessage("Something went wrong!", "antiquewhite");
    		},
    		403: function(responseObject, textStatus, jqXHR) {
    			console.log("403 Unauthorized");
    			unauthorized();
    		}
    	}
    });
	
	$(document).on("click", "#addRoomBtn", function(){
		$("#addRoomModal").modal();
	});
	
	$(document).on("click", "#roomCreateModalBtn", function(){
		var name = $("#roomNameInput").val();
		var type = $("#roomTypeInput").val();
		
        $.ajax({
            type: 'POST',
            url: 'ordinationApi/addOrdination',
            headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
            data : JSON.stringify({
              "name" : name,
              "type" : type
            }),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            statusCode: {
              200: function(responseObject, textStatus, jqXHR) {
                console.log("Ordination - add() - 200 OK");
                showMessage("Ordination successfully added!", "palegreen");
          	  var table = $("#roomsBody");
        	  
        	    var row = $("<tr id=\""+name+"\"></tr>");
        	    row.append("<td class=\"w-25\" id=\""+name+"\">" + name + "</td>");
        	    row.append("<td class=\"w-25\" id=\""+name+"\">" + type +"</td>");

        	    table.append(row);
        	    
        	      
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
	
	$(document).on("click", "#btnMakeApp", function(){
		var selTerm = $(this).closest("tr").find("select option:selected").val();
		console.log("sel term: "+selTerm);
		if ( selTerm == "Choose term"){
			 showMessage("You must choose exam term!", "antiquewhite");
		}
		else{
			var atrs = sessionStorage.getItem('appParam').split("&");
			var doctorAttrs = $(this).parent().attr('id').split("&");
			
			$("#doctorSPAN").attr('name', doctorAttrs[0]);
			
			var dcName = doctorAttrs[1];
			var dcLast = doctorAttrs[2];
	
			var clinicID = atrs[0];
			var appType = atrs[1];
			var date = atrs[2];
			
			console.log(atrs);
			console.log(appType);
			console.log(date);
		
			$("#clinicModal").val($("#name").val());
			$("#doctorModal").val(dcName + " " + dcLast);
			$("#dateTime").val(date + " " + selTerm + "h");
			$("#examType").val(appType);
			
			$("#exampleModal").modal();
		}
	});
	
	$(document).on("click", "#btnBack", function(){
		window.location.href = "/makeAppointment";
	});
	
	$(document).on("click", "#modalApproveBtn", function(){
		var user = undefined;
		$.ajax ({
		  	type: 'GET',
		  	url: 'userApi/getCurrentUser',
			headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
		  	statusCode: {
		  		200: function(responseObject, textStatus, jqXHR) {
		  			console.log("200 OK");
		  			user = responseObject;
		  		},
		  		204: function(responseObject, textStatus, jqXHR) {
		  			console.log("204 No Content");
		  		},
				403: function(responseObject, textStatus, jqXHR) {
					console.log("403 Unauthorized");
					unauthorized();
				}
		  	}
		});
		

	
		
		var doctor = $("#doctorSPAN").attr('name');
		var clinic = $("#clinicModal").val();
		var dateTime = $("#dateTime").val();
		var examType = $("#examType").val();
		
		$.ajax ({
		  	type: 'POST',
		  	url: 'appointmentApi/addAppointment',
		  	data : JSON.stringify({
				"email" : user,
				"doctor" : doctor,
				"dateTime" : dateTime,
				"examType": examType
			}),
			contentType: "application/json; charset=utf-8",
		    dataType: "json",
			headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
		  	statusCode: {
		  		200: function(responseObject, textStatus, jqXHR) {
		  			console.log("200 OK");
		  			showMessage("Appointment request successfully submited!", "palegreen");
		  		},
		  		204: function(responseObject, textStatus, jqXHR) {
		  			console.log("204 No Content");
		  			showMessage("Something went wrong!", "antiquewhite");
		  		},
				403: function(responseObject, textStatus, jqXHR) {
					console.log("403 Unauthorized");
					unauthorized();
				}
		  	}
		});
	});
	
	//----------------------------------ROOMS TABLE
	
	$('[data-toggle="tooltip"]').tooltip();
	  var actions = $("#roomTable td:last-child").html();
	  // Append table with add row form on add new button click
	  /*  $(".add-new-rooms").click(function(){
	      var actions = $("#roomTable td:last-child").html();
	    $(this).attr("disabled", "disabled");
	    var index = $("#roomTable tbody tr:nth-child(0)").index();
	        var row = '<tr>' +
	            '<td><input type="text" class="form-control" name="name" id="name"></td>' +
	            '<td><select id="type" name="type"><option value="OPERATION">OPERATION</option><option value="EXAM">EXAM</option></select></td>' +
	            '<td>&nbsp</td>' +
	              '<td>' + actions + '</td>' +
	        '</tr>';
	      $("#roomTable").prepend(row);    
	    $("#roomTable tbody tr").eq(index + 1).find(".add-room").toggle();
	    });*/
	// Add row on add button click

	
	
  $(document).on("click", ".add-room", function(){
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
              $(".add-new-rooms").removeAttr("disabled");
              
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
	$(document).on("click", ".edit-room", function(){		
      $(this).parents("tr").find("td:not(:last-child)").each(function(){
			if($(this).is("td:first-child")){
			$(this).html('<input type="text" class="form-control" value="' + $(this).text() + '">');
			}else if($(this).is(".but")){
			
			}else{
			$(this).html('<select id="type"><option value="OPERATION">OPERATION</option><option value="EXAM">EXAM</option></select>');
			}
		});		
		$(this).parents("tr").find(".add-room, .edit-room").toggle();
		$(".add-new-rooms").attr("disabled", "disabled");
  });
	// Delete row on delete button click
	$(document).on("click", ".delete-room", function(){
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
		$(".add-new-rooms").removeAttr("disabled");
  });
	
	//----------------------------------ROOMS TABLE
	
	//----------------------------------PRICELIST TABLE
	
	var actions_pricelist = $("pricelistTable td:last-child").html();
	  // Append table with add row form on add new button click
	    $(".add-new-pricelist").click(function(){
	      var actions_pricelist = $("pricelistTable td:last-child").html();
	    $(this).attr("disabled", "disabled");
	    var index = $("pricelistTable tbody tr:nth-child(0)").index();
	        var row = '<tr>' +
	            '<td><input type="text" class="form-control" name="name" id="type"></td>' +
	            '<td><input type="text" class="form-control" name="price" id="price"></td>' +
	              '<td>' + actions_pricelist + '</td>' +
	        '</tr>';
	      $("pricelistTable").prepend(row);    
	    $("pricelistTable tbody tr").eq(index + 1).find(".add-pricelist").toggle();
	    });
  
  
	// Add row on add button click
	    $(document).on("click", ".add-pricelist", function(){
	        var empty = false;
	        var input = $(this).parents("tr").find('input[type="text"]');
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
	          var dataPrice = [];
	          input.each(function(){
	            dataPrice.push($(this).val());
	          });     
	          $.ajax({
	            type: 'POST',
	            url: 'pricelistItemApi/addPricelistItem',
	            headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
	            data : JSON.stringify({
	              "name" : dataPrice[0],
	              "price" : dataPrice[1]
	            }),
	            contentType: "application/json; charset=utf-8",
	            dataType: "json",
	            statusCode: {
	              200: function(responseObject, textStatus, jqXHR) {
	                console.log("PricelistItem - add() - 200 OK");
	                showMessage("Pricelist item successfully added!", "palegreen");
	                input.each(function(){
	                  $(this).parent("td").html($(this).val());
	                }); 
	                $(this).parents("tr").find(".add-pricelist").toggle();
	                $(".add-new-pricelist").removeAttr("disabled");
	                
	              },
	              400: function(responseObject, textStatus, jqXHR) {
	                console.log("PricelistItem - add() - 400 Bad request");
	                showMessage("Pricelist item with inserted name already exists!", "antiquewhite");
	              },
	    		  403: function(responseObject, textStatus, jqXHR) {
	    			console.log("403 Unauthorized");
	    			unauthorized();
	    		  }
	            }
	          });
	        }   
	        });
	

	// Delete row on delete button click
	$(document).on("click", ".delete-pricelist", function(){
		var id = $(this).parents("td").attr('id');
		
      
      $.ajax({
          type: 'DELETE',
          url: 'pricelistItemApi/deletePricelistItem/' + id,
          headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
          contentType: "application/json; charset=utf-8",
          dataType: "json",
          statusCode: {
            200: function(responseObject, textStatus, jqXHR) {
              console.log("Pricelist item - delete() - 200 OK");
              showMessage("Pricelist item successfully deleted!", "palegreen"); 
            },
            400: function(responseObject, textStatus, jqXHR) {
              console.log("Pricelist item - delete() - 400 Bad request");
              showMessage("Something went wrong!", "antiquewhite");
            },
    		403: function(responseObject, textStatus, jqXHR) {
  			console.log("403 Unauthorized");
  			unauthorized();
  		}
          }
        });
      $(this).parents("tr").remove();
		$(".add-new-pricelist").removeAttr("disabled");
  });
	
	//----------------------------------PRICELIST TABLE
	
});

function showDoctors(doctors) {
	var tableHead = $("#doctorHead");
	tableHead.empty();
	var row = $("<tr></tr>");
	row.append("<th style=\"width: 25%;\">Name</th>");
	row.append("<th style=\"width: 30%;\">Average grade</th>");
	row.append("<th style=\"width: 30%;\">Free terms</th>");
	row.append("<th style=\"width: 15%;\">&nbsp</th>");

	tableHead.append(row);	
	
	
	var table = $("#doctorBody");
	table.empty();
	$.each(doctors,function(i,val){
		var row = $("<tr id=\""+i+"\"></tr>");
		row.append("<td >" + val.firstName + " " + val.lastName + "</td>");
		row.append("<td >" + val.averageGrade + "</td>");
		var selectt="<td><select><option disabled selected>Choose term</option>";
		
		  $.each(val.terms, function(i, val) {
			  selectt = selectt + "<option>" + val.substring(11, 16) + "</option>"
		  });
		  
		selectt = selectt + "</select></td>";
		console.log(val.terms);
		row.append(selectt);
		var btn = "<td id=\"" + val.email + "&" + val.firstName + "&" + val.lastName + "\"><button id=\"btnMakeApp\" type=\"button\" class=\"btn btn-primary\">Select doctor</button></td>";
		row.append(btn);
		table.append(row);	
	  });
	
	var btn = "<button id=\"btnBack\" type=\"button\" title=\"Back\" class=\"btn btn-secondary btn-lg\"><i class=\"fa fa-arrow-left\" aria-hidden=\"true\"></i></button></td>";
	$("#jumbotron").append(btn);
}

function clinicOK(clinic){
	$("#name").val(clinic.name);
	$("#clinicAddress").val(clinic.address + ", " + clinic.city);
	$("#desc").val(clinic.description);
}
function doctorAllOK(doctorList) {
	var table = $("#doctorBody");
	table.empty();
	console.log(doctorList);
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

	    row.append("<td id=\""+val.ordId+"\">" + val.name + "</td>");
	    row.append("<td id=\""+val.ordId+"\">" + val.type + "</td>");
	    row.append("<td class=\"but\"><button class=\"btn btn-primary\" type=\"button\" data-toggle=\"modal\" data-target=\"#exampleModal\" aria-expanded=\"false\" aria-controls=\"exampleModal\">Appointments</button></td>");
	    row.append("<td id=\""+val.name+"\">" + "<a class=\"add-room\" title=\"Add\" data-toggle=\"tooltip\"><i class=\"material-icons\">&#xE03B;</i></a>" +
		                            "<a class=\"edit-room\" title=\"Edit\" data-toggle=\"tooltip\"><i class=\"material-icons\">&#xE254;</i></a>" +
		                            "<a class=\"delete-room\" title=\"Delete\" data-toggle=\"tooltip\"><i class=\"material-icons\">&#xE872;</i></a>" + "</td>");
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
	  var table = $("#modalAppointmentsBody");
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


function appointmentsAllOK(appointmentsList) {
  var table = $("#appointmentsBody");
  table.empty();

  console.log(appointmentsList);
  $.each(appointmentsList, function(i, val) {
    var row = $("<tr id=\""+i+"\"></tr>");
    if(val.appointmentType != undefined) {
    row.append("<td id=\""+val.id+"\">" + val.datetime + "</td>");
    row.append("<td id=\""+val.id+"\">" + val.appointmentType + "</td>");
    row.append("<td id=\""+val.id+"\">" + val.roomNo + "</td>");
    row.append("<td id=\""+val.id+"\">" + val.doctor + "</td>");
    row.append("<td id=\""+val.id+"\">" + val.duration + " min</td>");
    row.append("<td id=\""+val.id+"\">" + val.price + "$</td>");
    table.append(row);
    }
  });
}

function pricelistAllOK(pricelistItemList) {
	  var table = $("#pricelistBody");
	  table.empty();

	  console.log(pricelistItemList);
	  $.each(pricelistItemList, function(i, val) {
	    var row = $("<tr id=\""+i+"\"></tr>");
	    row.append("<td class=\"appointmentType\" id=\""+val.id+"\">" + val.appointmentType + "</td>");
	    row.append("<td class=\"price\" id=\""+val.id+"\">" + val.price + "$</td>");
	    row.append("<td id=\""+val.id+"\">" + "<a class=\"add-priceclist\" title=\"Add\" data-toggle=\"tooltip\"><i class=\"material-icons\">&#xE03B;</i></a>" +
              "<a class=\"delete-pricelist\" title=\"Delete\" data-toggle=\"tooltip\"><i class=\"material-icons\">&#xE872;</i></a>" + "</td>");
table.append(row);

	  });
}

	function pricelistAllNO(responseObject) {
	  var table = $("#pricelistBody");
	  table.empty();
	  
	  var row = $("<tr></tr>");
	  row.append("<td class='pl-1'>There are no pricelist items in system.</td>");
	  
	  table.append(row);
	}
	function unauthorized(){
		document.write("<html><head></head><body>UNAUTHORIZED</body></html>");
	}

function pricelistFunction() {
	  // Declare variables
	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("pricelistInput");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("pricelistTable");
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

function appointmentsAllNO(responseObject) {
  var table = $("#appointmentsBody");
  table.empty();
  
  var row = $("<tr></tr>");
  row.append("<td class='pl-1'>There are no predefined appointments in system.</td>");
  
  table.append(row);
}
function unauthorized(){
	document.write("<html><head></head><body>UNAUTHORIZED</body></html>");
}


function modalExamShow(){
$("#examModal").modal();
}
function roomFunction() {
	  // Declare variables
	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("roomInput");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("roomTable");
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
function unauthorized(){
	document.write("<html><head></head><body>UNAUTHORIZED</body></html>");
}