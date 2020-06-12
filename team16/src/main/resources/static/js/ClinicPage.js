$( document ).ready(function() {
	var filter = sessionStorage.getItem("filter");
	var now = currentDate();
    $("#dateF").attr("min",now);
    $("#datePredef").attr("min",now);
	if (filter == "ne"){
		$("#filterDiv").css("display","none");
	}
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
    				    	  $("#clinicName").text(responseObject.name);
    				    	 clinicOK(responseObject,2);
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
    					    url: 'appointmentApi/findAllPredefinedCurrent',
    					    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
    					    statusCode: {
    					      200: function(responseObject, textStatus, jqXHR) {
    					        console.log("Appointments - findAll() - 200 OK");
    					        predefAppointmentsAllOK(responseObject);
    					      },
    					      204: function(responseObject, textStatus, jqXHR) {
    					        console.log("Appointments - findAll() - 204 No Content");
    					        //appointmentsAllNO(responseObject);
    					        
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
    				
    				  
     				 $.ajax ({
 				    	type: 'GET',
 				    	url: '/pricelistItemApi/findAllAppointmentTypes',
 					    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
 				    	statusCode: {
 				    		200: function(responseObject, textStatus, jqXHR) {
 				    			console.log("200 OK");
 				    			loadAppointmentTypesPredefAllOK(responseObject);
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
    				  
    			} else if(responseObject == "PATIENT"){
    				$("#price").css('display', 'none');
    				$("#rooms").css('display', 'none');
    				//$("#appointments").css('display', 'none');
    				
    				$("#li_price").css('display', 'none');
    				$("#li_rooms").css('display', 'none');
    				//$("#li_appointments").css('display', 'none');
    				
    				var atrs = sessionStorage.getItem('appParam');
    				var clinicId = atrs.split("&")[0];
    				 $.ajax({
 					    type: 'GET',
 					    url: 'appointmentApi/findAllPredefinedForPatient/'+clinicId,
 					    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
 					    statusCode: {
 					      200: function(responseObject, textStatus, jqXHR) {
 					        console.log("Predefined appointments for clinic - 200 OK");
 					        predefAppointmentsAllOK(responseObject,1);
 					      },
 					      204: function(responseObject, textStatus, jqXHR) {
 					        console.log("Predefined appointments for clinic - 204 No Content");
 					        //appointmentsAllNO(responseObject);
 					        $("#carouselExampleControls").css("display","none");
 					        $("#predefAppH1").text("There are no predefined appointments.");
 					        
 					     
 					    		
 					      },
 							403: function(responseObject, textStatus, jqXHR) {
 								console.log("403 Unauthorized");
 								unauthorized();
 							}
 					    }
 					  });
    			//---------------ako filtrira klinike------------------------
    			if (atrs.includes("&")){
    				    var attrs2 = atrs.split("&");
	    				var clinicID = attrs2[0];
	    				var appType = attrs2[1];
	    				var date = attrs2[2];
	    				
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
	    				    	 clinicOK(responseObject,1);
	    				      },
	    				      204: function(responseObject, textStatus, jqXHR) {
	
	    				      },
	    						403: function(responseObject, textStatus, jqXHR) {
	    							console.log("403 Unauthorized");
	    							unauthorized();
	    						}
	    				    }
	    				  });
	    				//!!!mislim da ovaj deo ne treba!!! dole
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
			    		    }); //!!!mislim da ovaj deo ne treba!!! gore
	    				} else {
	    					$.ajax ({
	    					  	type: 'GET',
	    					  	url: 'clinicApi/findAllAppointmentDoctors/' + clinicID + "&" + appType + "&" + date,
	    						headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
	    					  	statusCode: {
	    					  		200: function(responseObject, textStatus, jqXHR) {
	    					  			console.log("200 OK");
	    					  			var odakle = 1;
	    					  			showDoctors(responseObject,odakle);
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
    			} else { //-------------------ako ne filtrira klinike-----------------------
    				var clinicID = atrs;
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
    				    	 clinicOK(responseObject,1);
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
	    		    	url: 'doctorApi/findAllDoctorsDTOByClinic/' + clinicID,
	    			    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
	    		    	statusCode: {
	    		    		200: function(responseObject, textStatus, jqXHR) {
	    		    			console.log("Doctors - findAll() - 200 OK");
	    		    			listDoctorOfClinic(responseObject);
	    		    		},
	    		    		204: function(responseObject, textStatus, jqXHR) {
	    		    			console.log("Doctors - findAll() - 204 No Content");
	    		    			listDoctorOfClinicNoContet(responseObject);
	    		    		},
	    					403: function(responseObject, textStatus, jqXHR) {
	    						console.log("403 Unauthorized");
	    						unauthorized();
	    					}
	    		    	}
	    		    });
    				 $.ajax ({
    				    	type: 'GET',
    				    	url: '/pricelistItemApi/findAllAppointmentTypes',
    					    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
    				    	statusCode: {
    				    		200: function(responseObject, textStatus, jqXHR) {
    				    			console.log("200 OK");
    				    			loadAppointmentTypesAllOK(responseObject);
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
    				
    		}},
    		204: function(responseObject, textStatus, jqXHR) {
    			console.log("Role - findAll() - 204 No Content");
    		     Swal.fire({
   		  		  position: 'center',
   		  		  icon: 'error',
   		  		  title: 'Something went wrong!',
   		  		  showConfirmButton: false,
   		  		  timer: 1500
   		  		});
    			},
    		403: function(responseObject, textStatus, jqXHR) {
    			console.log("403 Unauthorized");
    			unauthorized();
    		}
    	}
    	
    });
	function currentDate() {
	    var d = new Date(),
	        month = '' + (d.getMonth() + 1),
	        day = '' + d.getDate(),
	        year = d.getFullYear();

	    if (month.length < 2) 
	        month = '0' + month;
	    if (day.length < 2) 
	        day = '0' + day;

	    return [year, month, day].join('-');
	}
	
	$(document).on("click", "#addRoomBtn", function(){
		$("#addRoomModal").modal();
	});
	
	$(document).on("click", "#addDoctorBtn", function(){
		$("#addDoctor").modal();
	});
	
	
	$(document).on("click", "#fireButton", function(){
		var id = $(this).parents("tr").attr("id");
        $.ajax({
            type: 'DELETE',
            url: 'doctorApi/deleteDoctor/' + id,
            headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            statusCode: {
              200: function(responseObject, textStatus, jqXHR) {
                console.log("Ordination - delete() - 200 OK");
                
                Swal.fire({
	          		  position: 'center',
	          		  icon: 'success',
	          		  title: 'Doctor deleted successfully!',
	          		  showConfirmButton: false,
	          		  timer: 1500
	          		})
	          		
	          		window.setTimeout(function(){location.reload()},1500);
                
              },
              400: function(responseObject, textStatus, jqXHR) {
                console.log("Ordination - delete() - 400 Bad request");
                Swal.fire({
	          		  position: 'center',
	          		  icon: 'error',
	          		  title: 'Removing a doctor with appointemtns is not allowed!',
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
		
		
	});
	
	
	
	$(document).on("click", "#addDoctorModalBtn", function(){
		var err = false;
		if($("#firstNameSignUp").val() == null || $("#firstNameSignUp").val() == ""){
			err = true;
			$("#firstNameSignUp").addClass("is-invalid");
		}else
			err = false;
		if($("#lastNameSignUp").val() == null || $("#lastNameSignUp").val() == ""){
			err = true;
			$("#lastNameSignUp").addClass("is-invalid");
		}else
			err = false;
		if($("#citySignUp").val() == null || $("#citySignUp").val() == ""){
			err = true;
			$("#citySignUp").addClass("is-invalid");
		}else
			err = false;
		if($("#countrySignUp").val() == null || $("#countrySignUp").val() == ""){
			err = true;
			$("#countrySignUp").addClass("is-invalid");
		}else
			err = false;
		if($("#addressSignUp").val() == null || $("#addressSignUp").val() == ""){
			err = true;
			$("#addressSignUp").addClass("is-invalid");
		}else
			err = false;
		if($("#insNumberSignUp").val() == null || $("#insNumberSignUp").val() == ""){
			err = true;
			$("#insNumberSignUp").addClass("is-invalid");
		}else
			err = false;
		if($("#phoneSignUp").val() == null || $("#phoneSignUp").val() == ""){
			err = true;
			$("#phoneSignUp").addClass("is-invalid");
		}else
			err = false;
		if($("#emailSignUp").val() == null || $("#emailSignUp").val() == ""){
			err = true;
			$("#emailSignUp").addClass("is-invalid");
		}else
			err = false;
		if($("#passSignUp").val() == null || $("#passSignUp").val() == ""){
			err = true;
			$("#passSignUp").addClass("is-invalid");
		}else
			err = false;
		if($("#passRepeatSignUp").val() == null || $("#passRepeatSignUp").val() == ""){
			err = true;
			$("#passRepeatSignUp").addClass("is-invalid");
		}else
			err = false;
		
		if($("#passSignUp").val() != $("#passRepeatSignUp").val()){
			err = true;
            Swal.fire({
        		  position: 'center',
        		  icon: 'error',
        		  title: 'The passwords do not match!',
        		  showConfirmButton: false,
        		  timer: 1500
        		})
        		
		}else
			err = false;
		
		if(err != true){
	        $.ajax({
	            type: 'POST',
	            url: 'doctorApi/addDoctor',
	            headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
	            data : JSON.stringify({
	              "email" : $("#emailSignUp").val(),
	              "password" : $("#passSignUp").val(),
	              "firstName" : $("#firstNameSignUp").val(),
	              "lastName" : $("#lastNameSignUp").val(),
	              "address" : $("#addressSignUp").val(),
	              "city" : $("#citySignUp").val(),
	              "country" : $("#countrySignUp").val(),
	              "phoneNumber" : $("#phoneSignUp").val(),
	              "insuranceNumber" : $("#insNumberSignUp").val()
	            }),
	            contentType: "application/json; charset=utf-8",
	            dataType: "json",
	            statusCode: {
	              200: function(responseObject, textStatus, jqXHR) {
	                console.log("Ordination - add() - 200 OK");
	                Swal.fire({
	          		  position: 'center',
	          		  icon: 'success',
	          		  title: 'New doctor successfully added!',
	          		  showConfirmButton: false,
	          		  timer: 1500
	          		})
	          		
	          		window.setTimeout(function(){location.reload()},1500);
	          		
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
                Swal.fire({
     		  		  position: 'center',
     		  		  icon: 'success',
     		  		  title: 'Ordination successfully added!',
     		  		  showConfirmButton: false,
     		  		  timer: 1500
     		  		});
              
          	  var table = $("#roomsBody");
        	  
        	    var row = $("<tr id=\""+name+"\"></tr>");
        	    row.append("<td class=\"w-25\" id=\""+name+"\">" + name + "</td>");
        	    row.append("<td class=\"w-25\" id=\""+name+"\">" + type +"</td>");

        	    table.append(row);
        	    
        	      
              },
              400: function(responseObject, textStatus, jqXHR) {
                console.log("Ordination - add() - 400 Bad request");
                Swal.fire({
   		  		  position: 'center',
   		  		  icon: 'error',
   		  		  title: 'Ordination with inserted name already exists!',
   		  		  showConfirmButton: false,
   		  		  timer: 1500
   		  		});
                
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
		if ( selTerm == "Choose term"){
			 Swal.fire({
		  		  position: 'center',
		  		  icon: 'error',
		  		  title: 'You must choose exam term!',
		  		  showConfirmButton: false,
		  		  timer: 1500
		  		});
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
	//klinike admin nayad
	$(document).on("click", "#btnBackAdmin", function(){
		window.location.href = "/clinicAdmin";
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
		  			  		 Swal.fire({
		  				  		  position: 'center',
		  				  		  icon: 'success',
		  				  		  title: 'Appointment request successfully submited!',
		  				  		  showConfirmButton: false,
		  				  		  timer: 1500
		  				  		});
		  			  			
		  			  		},
		  			  		204: function(responseObject, textStatus, jqXHR) {
		  			  			console.log("204 No Content");
		  			  		 Swal.fire({
		  				  		  position: 'center',
		  				  		  icon: 'error',
		  				  		  title: 'Something went wrong!',
		  				  		  showConfirmButton: false,
		  				  		  timer: 1500
		  				  		});
		  			  			
		  			  			window.setTimeout(function(){location.reload()},1500);
		  			  		},
		  					403: function(responseObject, textStatus, jqXHR) {
		  						console.log("403 Unauthorized");
		  						unauthorized();
		  					}
		  			  	}
		  			});
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
              Swal.fire({
			  		  position: 'center',
			  		  icon: 'success',
			  		  title: 'Ordination successfully added!',
			  		  showConfirmButton: false,
			  		  timer: 1500
			  		});
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
              Swal.fire({
			  		  position: 'center',
			  		  icon: 'error',
			  		  title: 'Ordination with inserted name already exists!',
			  		  showConfirmButton: false,
			  		  timer: 1500
			  		});
             
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

		var num = $(this).parents("tr").attr("id");
		
		
		
        $.ajax({
            type: 'GET',
            url: 'ordinationApi/findOneByNumber/' + num,
            headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            statusCode: {
              200: function(responseObject, textStatus, jqXHR) {
                console.log("Ordination - add() - 200 OK");
                if(responseObject.hasAppointments == true)
                    Swal.fire({
  	        		  position: 'center',
  	        		  icon: 'error',
  	        		  title: 'Editing rooms with booked appointments is not allowed!',
  	        		  showConfirmButton: false,
  	        		  timer: 1500
  	        		})
                else{
                $("#roomNameInputEdit").val(responseObject.name);
                $("#roomIdEdit").text(num);
                $("#editRoomModal").modal();
                }
                
              },
              400: function(responseObject, textStatus, jqXHR) {
                console.log("Ordination - add() - 400 Bad request");
                Swal.fire({
			  		  position: 'center',
			  		  icon: 'error',
			  		  title: 'Ordination with inserted name already exists!',
			  		  showConfirmButton: false,
			  		  timer: 1500
			  		});
               
              },
    		  403: function(responseObject, textStatus, jqXHR) {
    			console.log("403 Unauthorized");
    			unauthorized();
    		  }
            }
          });
		
		
		
		
		
		
  });
	
	$(document).on("click", "#roomEditModalBtn", function(){
		var id = $(this).parents("tr").attr('id');
        $.ajax({
            type: 'PUT',
            url: 'ordinationApi/editOrdination',
            headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
            data : JSON.stringify({
              "name" : $("#roomNameInputEdit").val(),
              "type" : $("#roomTypeInputEdit").val(),
              "ordId" : $("#roomIdEdit").text()
            }),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            statusCode: {
              200: function(responseObject, textStatus, jqXHR) {
                console.log("PricelistItem - add() - 200 OK");
                Swal.fire({
	        		  position: 'center',
	        		  icon: 'success',
	        		  title: 'Room successfully edited!',
	        		  showConfirmButton: false,
	        		  timer: 1500
	        		})
	        		
	        		window.setTimeout(function(){location.reload()},1500);
                
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
		
		
	});
	
	// Delete row on delete button click
	$(document).on("click", ".delete-room", function(){
		var id = $(this).parents("tr").attr('id');
		
        $.ajax({
            type: 'GET',
            url: 'ordinationApi/findOneByNumber/' + id,
            headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            statusCode: {
              200: function(responseObject, textStatus, jqXHR) {
                console.log("Ordination - add() - 200 OK");
                if(responseObject.hasAppointments == true)
                    Swal.fire({
  			  		  position: 'center',
  			  		  icon: 'error',
  			  		  title: 'Deleting rooms with appointments is not allowed!',
  			  		  showConfirmButton: false,
  			  		  timer: 1500
  			  		});
                	
                else{
                    $.ajax({
                        type: 'DELETE',
                        url: 'ordinationApi/deleteOrdination/' + id,
                        headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
                        contentType: "application/json; charset=utf-8",
                        dataType: "json",
                        statusCode: {
                          200: function(responseObject, textStatus, jqXHR) {
                            console.log("Ordination - delete() - 200 OK");
                            Swal.fire({
            			  		  position: 'center',
            			  		  icon: 'success',
            			  		  title: 'Ordination successfully deleted!',
            			  		  showConfirmButton: false,
            			  		  timer: 1500
            			  		});
                           
                          },
                          400: function(responseObject, textStatus, jqXHR) {
                            console.log("Ordination - delete() - 400 Bad request");
                            Swal.fire({
          			  		  position: 'center',
          			  		  icon: 'error',
          			  		  title: 'Something went wrong!',
          			  		  showConfirmButton: false,
          			  		  timer: 1500
          			  		});
                          
                          },
                  		403: function(responseObject, textStatus, jqXHR) {
                			console.log("403 Unauthorized");
                			unauthorized();
                		}
                        }
                      });
                }
                
              },
              400: function(responseObject, textStatus, jqXHR) {
                console.log("Ordination - add() - 400 Bad request");
                Swal.fire({
			  		  position: 'center',
			  		  icon: 'error',
			  		  title: 'Ordination with inserted name already exists!',
			  		  showConfirmButton: false,
			  		  timer: 1500
			  		});
              },
    		  403: function(responseObject, textStatus, jqXHR) {
    			console.log("403 Unauthorized");
    			unauthorized();
    		  }
            }
          });
		
		
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
	                Swal.fire({
				  		  position: 'center',
				  		  icon: 'success',
				  		  title: 'Pricelist item successfully added!',
				  		  showConfirmButton: false,
				  		  timer: 1500
				  		});
	                input.each(function(){
	                  $(this).parent("td").html($(this).val());
	                }); 
	                $(this).parents("tr").find(".add-pricelist").toggle();
	                $(".add-new-pricelist").removeAttr("disabled");
	                
	              },
	              400: function(responseObject, textStatus, jqXHR) {
	                console.log("PricelistItem - add() - 400 Bad request");
	                Swal.fire({
				  		  position: 'center',
				  		  icon: 'error',
				  		  title: 'Pricelist item with inserted name already exists!',
				  		  showConfirmButton: false,
				  		  timer: 1500
				  		});
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
              Swal.fire({
		  		  position: 'center',
		  		  icon: 'success',
		  		  title: 'Pricelist item successfully deleted!',
		  		  showConfirmButton: false,
		  		  timer: 1500
		  		});
            },
            400: function(responseObject, textStatus, jqXHR) {
              console.log("Pricelist item - delete() - 400 Bad request");
              Swal.fire({
   		  		  position: 'center',
   		  		  icon: 'error',
   		  		  title: 'Something went wrong!',
   		  		  showConfirmButton: false,
   		  		  timer: 1500
   		  		});
                
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
	
});//-----------------------zatvara se .ready()
//---------------------------------------------listanje svih doktora odredjenje klinike---------------------------
function loadAppointmentTypesAllOK(pricelistItem){
	//popuni select za apptype
	 var select = $("#appointmentTypeF");
	 select.empty();
	 select.append("<option disabled selected>Select appointment type...</option>");
	 $.each(pricelistItem, function(i, val) {
		   if(val.appointmentType != "SURGERY"){
		    var option = $("<option>"+val.appointmentType+"</option>");
		    select.append(option);
		   }
		  });
}

function loadAppointmentTypesPredefAllOK(pricelistItem){
	//popuni select za apptype
	 var select = $("#appointmentTypePredef");
	 select.empty();
	 select.append("<option disabled selected>Select appointment type...</option>");
	 $.each(pricelistItem, function(i, val) {
		   if(val.appointmentType != "SURGERY"){
		    var option = $("<option>"+val.appointmentType+"</option>");
		    select.append(option);
		   }
		  });
}

$("#applyFilter").click(function(){
	
	var appType = $("#appointmentTypeF").val();
	var date = document.querySelector('input[type="date"]');
	var clinicID = sessionStorage.getItem('clinicID');
    sessionStorage.setItem("appParam", clinicID+"&"+appType+"&"+date.value);
	if (appType == null || appType == "" ){
		$("#appointmentTypeF").addClass("is-invalid");
	}
	else
		{
		$("#appointmentTypeF").removeClass("is-invalid");
		}
	if (date.value== null || date.value == "" ){
		$("#dateF").addClass("is-invalid");
	}
	else
		{
		$("#dateF").removeClass("is-invalid");
		}
	if(appType != null && appType!="" && date.value!=null && date.value != ""){
		$.ajax ({
		  	type: 'GET',
		  	url: 'clinicApi/findAllAppointmentDoctors/' + clinicID + "&" + appType + "&" + date.value,
			headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
		  	statusCode: {
		  		200: function(responseObject, textStatus, jqXHR) {
		  			console.log("200 OK");
		  			var odakle = 2;
		  			showDoctors(responseObject,odakle);
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
		var attr = 
		sessionStorage.setItem('appDate', date.value);
	
	}
	});

function searchDoctors(){
	// Declare variables
	var input, filter, table, tr, td, i, j, txtValue;
	input = document.getElementById("searchDoc");
	filter = input.value.toUpperCase();
	table = document.getElementById("doctorTable");
	tr = table.getElementsByTagName("tr");


	// Loop through all table rows, and hide those who don't match the search query
	for (i = 0; i < tr.length; i++) {
		let rowTds = tr[i].getElementsByTagName("td")
		for (j = 0; j < 2; j++) {
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

function listDoctorOfClinic(doctors){
	//svi doktori : ime prez prosecna ocena bez list slobodnih termina
	//tek ako pritisne i search dolaze i slobodni termini
	var tableHead = $("#doctorHead");
	tableHead.empty();
	var row = $("<tr></tr>");
	row.append("<th style=\"width: 50%;\">Name</th>");
	row.append("<th style=\"width: 50%;\">Average grade</th>");
	

	tableHead.append(row);	
	
	
	var table = $("#doctorBody");
	table.empty();
	$.each(doctors,function(i,val){
		var row = $("<tr id=\""+i+"\"></tr>");
		row.append("<td >" + val.firstName + " " + val.lastName + "</td>");
		row.append("<td >" + val.averageGrade + "</td>");
	
		table.append(row);	
	  });
	
	var btn = "<button id=\"btnBack\" type=\"button\" title=\"Back\" class=\"btn btn-secondary btn-lg\"><i class=\"fa fa-arrow-left\" aria-hidden=\"true\"></i></button></td>";
	$("#jumbotron").append(btn);
	
}
function listDoctorOfClinicNoContent(doctors){
	//nema doktora
}
function showDoctors(doctors,odakle) {
	
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
		var selectt="<td align=\"center\"><select class=\"select\"><option disabled selected>Choose term</option>";
		
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
	if (odakle == 1){
	var btn = "<button id=\"btnBack\" type=\"button\" title=\"Back\" class=\"btn btn-secondary btn-lg\"><i class=\"fa fa-arrow-left\" aria-hidden=\"true\"></i></button></td>";
	$("#jumbotron").append(btn);
	}
}

function clinicOK(clinic,odakle){
	$("#name").val(clinic.name);
	$("#clinicAddress").val(clinic.address + ", " + clinic.city);
	$("#desc").val(clinic.description);
	var attr = sessionStorage.setItem('clinicID', clinic.clinicID);
	if(odakle == 1){
		$("#predefinedAddDiv").css("display","none");
	}
	$.ajax({
	    type: 'POST',
	    url: 'clinicApi/createMap/' + $("#clinicAddress").val(),
	    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
	    statusCode: {
	      200: function(responseObject, textStatus, jqXHR) {
	        console.log("Clinics - map() - 200 OK");
	        var obj = JSON.parse(responseObject);
	        var latV = obj.results[0].geometry.location.lat;
	        var lonV = obj.results[0].geometry.location.lng;
	        
	        console.log(latV + " " + lonV);
	        
	        initMap(latV, lonV);
	      },
	      204: function(responseObject, textStatus, jqXHR) {
	        console.log("Clinics - map() - 204 No Content");
	        clinicsAllNO(responseObject);
	      },
			403: function(responseObject, textStatus, jqXHR) {
				console.log("403 Unauthorized");
				unauthorized();
			}
	    }
	  });
	if(odakle == 2){
	var btn = "<button id=\"btnBackAdmin\" type=\"button\" title=\"Back\" class=\"btn btn-secondary btn-lg\"><i class=\"fa fa-arrow-left\" aria-hidden=\"true\"></i></button></td>";
	$("#jumbotron").append(btn);}

	
}

function initMap(latV, lon) {
    var myLatLng = {lat: latV, lng: lon};

    var map = new google.maps.Map(document.getElementById('map'), {
      zoom: 15,
      center: myLatLng,
      disableDefaultUI: true
    });

    var marker = new google.maps.Marker({
      position: myLatLng,
      map: map,
      title: 'Hello World!'
    });
  }
function doctorAllOK(doctorList) {
	$("#filterDiv").css("display","none");
	//titleDoctors
	$("#titleDoctors").text("Search doctors");
	//searchDoc
	$("#searchDoc").attr("placeholder","Search doctors by name...");

	var table = $("#doctorBody");
	table.empty();
	console.log(doctorList);
	$.each(doctorList, function(i, val) {
		var row = $("<tr id=\""+val.id+"\"></tr>");

		row.append("<td class=\"w-50 modalDoctor\" id=\""+val.email+"\">" + val.firstName + " " + val.lastName + "</td>");
		row.append("<td class=\"w-50 text-right\"><button type=\"button\" class=\"btn btn-outline-primary mr-3\">Review</button><button type=\"button\" class=\"btn btn-outline-danger\" id=\"fireButton\">Fire</button></td>");

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
	    var row = $("<tr id=\""+val.ordId+"\"></tr>");

	    row.append("<td id=\""+val.ordId+"\">" + val.name + "</td>");
	    row.append("<td id=\""+val.ordId+"\">" + val.type + "</td>");
	    row.append("<td class=\"but\"><button class=\"btn btn-primary\" type=\"button\" data-toggle=\"modal\" data-target=\"#exampleModal\" aria-expanded=\"false\" aria-controls=\"exampleModal\">Appointments</button></td>");
	    row.append("<td id=\""+val.name+"\">" +
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


function predefAppointmentsAllOK(appointmentsList,odakle) {
  var table = $(".carousel-inner");
  table.empty();
  var first = true;
  console.log(appointmentsList);
  
  
  var amount = Math.floor(appointmentsList.length/3) + 1;
  var j;
  for (j = 0; j < amount; j++) {
	  var row = $("<div class=\"carousel-item\" id=\"item"+j+"\"></div>");
	  table.append(row);
	}
  console.log(appointmentsList);
  $.each(appointmentsList, function(i, val) {
	    var rowAdd = $("#item"+Math.floor(i/3)); 
		var card = $("<div class=\"card\" style=\"width: 18rem; float: left;\"></div>");
		var cardBody = $("<div class=\"card-body\"></div>");
		cardBody.append("<h5 class=\"card-title\">"+val.datetime+"h</h5>");
		cardBody.append("<p class=\"card-text\"><b>Doctor: </b>"+val.doctor+"<br><b>Ordination: </b> "+val.roomName+"<br><b>Type: </b>"+val.appointmentType+"<br><b>Price: </b><s>"+val.price+"</s> "+(val.price*val.discount).toFixed(2)+"$</p>");
		//ako je pacijent poziva(odakle=1)
		if(odakle == 1){
			var btn = "<button id=\"btnSchedule\" type=\"button\" title=\"Schedule appointment\" class=\"btn btn-secondary \">Schedule appointment</button></td>";
			cardBody.append(btn);
			//console.log("TOKEN "+sessionStorage.getItem('token')+ "  app id "+val.id);
			sessionStorage.setItem("appId",val.id);
			//sessionStorage.setItem("patient",);
		}
		card.append(cardBody);
	    
		rowAdd.append(card);


  });
  
  $('.carousel-item').first().addClass('active');
  
  var controlLeft = $("<a class=\"carousel-control-prev\" href=\"#carouselExampleControls\" role=\"button\" data-slide=\"prev\"><span class=\"carousel-control-prev-icon\" aria-hidden=\"true\"></span><span class=\"sr-only\">Previous</span></a>");
  var controlRight = $("<a class=\"carousel-control-next\" href=\"#carouselExampleControls\" role=\"button\" data-slide=\"next\"><span class=\"carousel-control-next-icon\" aria-hidden=\"true\"></span><span class=\"sr-only\">Next</span></a>");
  
  var car = $("#carouselExampleControls");
  car.append(controlLeft);
  car.append(controlRight);
  
  
}
$(document).on("click", "#btnSchedule", function(){
	var appointmentId = sessionStorage.getItem("appId");
	$.ajax({
	    type: 'POST',
	    url: 'appointmentApi/schedulePredefinedAppointment/' + appointmentId ,
	    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
	    contentType: "application/json; charset=utf-8",
		dataType: "json",
	    statusCode: {
	      200: function(responseObject, textStatus, jqXHR) {
	        console.log("200 OK - appointment scheduled.");
	        Swal.fire({
	  		  position: 'center',
	  		  icon: 'success',
	  		  title: 'Successfully scheduled appointment!',
	  		  showConfirmButton: false,
	  		  timer: 1500
	  		});
	  	//mozda redirekt na appointment history
	      },
	      204: function(responseObject, textStatus, jqXHR) {
	        console.log("204 - Could not schedule appointment.");
	        Swal.fire({
		  		  position: 'center',
		  		  icon: 'error',
		  		  title: 'Sorry, you can not schedule appointment.',
		  		  showConfirmButton: false,
		  		  timer: 1500
		  		});
		  	
	      },
			403: function(responseObject, textStatus, jqXHR) {
				console.log("403 Unauthorized");
				unauthorized();
			}
	    }
	  });
	
	
});
$("#filterPredef").click(function(){

	var date = $("#datePredef").val();
	var time = $("#timePredef option:selected").text();
	
	var type = $("#appointmentTypePredef").val();
	var disc = $("#discountPredef option:selected").text();
	
	$("#dateModalPredef").val(date + " " + time);
	$("#typeModalPredef").val(type);
	$("#discountModalPredef").val(disc);
	
	
    $.ajax({
    	type: 'GET',
    	url: '/ordinationApi/findAllFreeForDateTime',
	    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
	    data: {
	    	"date" : date+ " "+time
	    },
    	statusCode: {
    		200: function(responseObject, textStatus, jqXHR) {
    			console.log("200 OK");
    			var select = $("#roomPredef");
    			$.each(responseObject, function(i, val) {
    				var option = "<option value=\""+val.ordId+"\">"+val.name+"</option>";
    				select.append(option);
    				
    			});
    			$("#predefinedModal").modal();
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
    
    $.ajax({
    	type: 'GET',
    	url: '/doctorApi/findAllFreeForDateTime',
	    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
	    data: {
	    	"date" : date+ " "+time,
	    	"type" : type
	    },
    	statusCode: {
    		200: function(responseObject, textStatus, jqXHR) {
    			console.log("200 OK");
    			var select = $("#doctorPredef");
    			$.each(responseObject, function(i, val) {
    				var option = "<option value=\""+val.email+"\">"+val.firstName+" " + val.lastName+"</option>";
    				select.append(option);
    				
    			});
    			$("#predefinedModal").modal();
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
    
	
});

$("#modalApprovePredefBtn").click(function(){
	
	var date = $("#datePredef").val();
	var time = $("#timePredef option:selected").text();
	
	var doc = $("#doctorPredef").val();
	
    $.ajax({
    	type: 'POST',
    	url: '/appointmentApi/addPredefinedAppointment',
	    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
	    data: JSON.stringify({
	    	"email" : doc,
	    	"examType" : $("#typeModalPredef").val(),
	    	"dateTime" : date+ " "+time,
	    	"discount" : $("#discountPredef").val(),
	    	"ordId" : $("#roomPredef").val()
	    }),
		contentType: "application/json; charset=utf-8",
		dataType: "json",
    	statusCode: {
    		200: function(responseObject, textStatus, jqXHR) {
    			console.log("200 OK");
                Swal.fire({
	        		  position: 'center',
	        		  icon: 'success',
	        		  title: 'New predefined appointment successfully added!',
	        		  showConfirmButton: false,
	        		  timer: 1500
	        		})
	        		
	       
                window.setTimeout(function(){location.reload()},1500);
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
});


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