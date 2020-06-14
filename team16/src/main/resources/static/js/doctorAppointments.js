$(document).ready(function(){
	
	console.log("ready");
	var cur = currentDate();
	$("#appNextDate").attr('min', cur);
	  $.ajax({
		    type: 'GET',
		    url: 'appointmentApi/findAllCurrDoctorAppointments',
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
	  
	  
	  $(document).on('click', '#closeEditModal', function () {
		  $("#allMedEditModal").modal('hide');
	  });
	  
	  $(document).on('click', '#editModalAllergies', function () {
		  var patient = sessionStorage.getItem('appPatientEmail').trim();
		  
		  $.ajax ({
		    	type: 'GET',
		    	url: '/patientApi/medicalRecordForPatient/' + patient,
			    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
		    	statusCode: {
		    		200: function(responseObject, textStatus, jqXHR) {
		    			console.log("200 OK");
		    			var select = $("#patientsMedAll");
		    			select.empty();
				        $.each(responseObject.allergies, function(i, val) {  
				  		  var option = $("<option>"+val+"</option>");
				  		   select.append(option);
				  		  });

						  $.ajax({
							    type: 'GET',
							    url: 'diagnosisApi/findAllAllergies',
							    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
							    statusCode: {
							      200: function(responseObject2, textStatus, jqXHR) {
							        console.log("Diagnosis - findAll() - 200 OK");
							        var select = $("#newMedAll");
							        select.empty();
							        console.log(responseObject2);
							        $.each(responseObject2, function(i, val) {  
							        	console.log(val);
							        	if(!responseObject.allergies.includes(val)) {
								  		   var option = $("<option>"+val+"</option>");
								  		   select.append(option);
							        	}
							  		  });
							      },
							      204: function(responseObject2, textStatus, jqXHR) {
							        console.log("Diagnosis - findAll() - 204 No Content");
							        Swal.fire({
						        		  position: 'center',
						        		  icon: 'error',
						        		  title: 'Something went wrong!',
						        		  showConfirmButton: false,
						        		  timer: 1500
						        		})
							      },
									403: function(responseObject2, textStatus, jqXHR) {
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
		  
		  
		  
		  $("#nameSPAN").empty();
		  $("#nameAddSPAN").empty();
		  $("#nameSPAN").append("Allergies");
		  $("#nameAddSPAN").append("Add allergie");
		  $("#allMedEditModal").modal();
		  
	  });
	  
	  
	  $(document).on('click', '#saveChangesBtn', function () {
		  var patient = sessionStorage.getItem('appPatientEmail').trim();
		  
		  const selected = document.querySelectorAll('#patientsMedAll option:checked');
		  const forRemoving = Array.from(selected).map(el => el.value);
		  
		  const selectedM = document.querySelectorAll('#newMedAll option:checked');
		  const forAdding = Array.from(selectedM).map(el => el.value);
		  
		  var type = document.getElementById("nameSPAN").innerText;
		  
		  if(type == "Allergies") {
			  $.ajax ({
			    	type: 'PUT',
			    	url: '/patientApi/editAllergiesInRecord/' + patient,
			    	data : JSON.stringify({
						"allergiesAdd" : forAdding,
						"allergiesRemove": forRemoving
					}),
					contentType: "application/json; charset=utf-8",
				    dataType: "json",
				    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
			    	statusCode: {
			    		200: function(responseObject, textStatus, jqXHR) {
			    			console.log("200 OK");
			    			$.ajax ({
			    		    	type: 'GET',
			    		    	url: '/patientApi/medicalRecordForPatient/' + patient,
			    			    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
			    		    	statusCode: {
			    		    		200: function(responseObject, textStatus, jqXHR) {
			    		    			console.log("200 OK");
			    		    			loadPatientInfoAllOK(responseObject);
			    		    		},
			    		    		204: function(responseObject, textStatus, jqXHR) {
			    		    			console.log("204 No Content");
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
		  } else {
			  $.ajax ({
			    	type: 'PUT',
			    	url: '/patientApi/editMedicationInRecord/' + patient,
			    	data : JSON.stringify({
						"medicationsAdd" : forAdding,
						"medicationsRemove": forRemoving
					}),
					contentType: "application/json; charset=utf-8",
				    dataType: "json",
				    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
			    	statusCode: {
			    		200: function(responseObject, textStatus, jqXHR) {
			    			console.log("200 OK");
			    			$.ajax ({
			    		    	type: 'GET',
			    		    	url: '/patientApi/medicalRecordForPatient/' + patient,
			    			    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
			    		    	statusCode: {
			    		    		200: function(responseObject, textStatus, jqXHR) {
			    		    			console.log("200 OK");
			    		    			loadPatientInfoAllOK(responseObject);
			    		    		},
			    		    		204: function(responseObject, textStatus, jqXHR) {
			    		    			console.log("204 No Content");
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
		  
		  $("#allMedEditModal").modal('hide');
		  
	  });
	  
	  $(document).on('click', '#editModalMedications', function () {
		  var patient = sessionStorage.getItem('appPatientEmail').trim();
		  
		  $.ajax ({
		    	type: 'GET',
		    	url: '/patientApi/medicalRecordForPatient/' + patient,
			    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
		    	statusCode: {
		    		200: function(responseObject, textStatus, jqXHR) {
		    			console.log("200 OK");
		    			var select = $("#patientsMedAll");
		    			select.empty();
				        $.each(responseObject.perscription, function(i, val) {  
				  		  var option = $("<option>"+val.name+"</option>");
				  		   select.append(option);
				  		  });
				        
				        var options = $('#patientsMedAll option');

				        var values = $.map(options ,function(option) {
				            return option.value;
				        });
				        
				        $.ajax({
						    type: 'GET',
						    url: 'medicationApi/findAll',
						    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
						    statusCode: {
						      200: function(responseObject2, textStatus, jqXHR) {
						        console.log("Medications - findAll() - 200 OK");
						        var select = $("#newMedAll");
						        select.empty();
						        console.log(responseObject.perscription);
						        $.each(responseObject2, function(i, val) {  
						        	console.log(val.name);
						        	if(!values.includes(val.name)){
							  		   var option = $("<option>"+val.name+"</option>");
							  		   select.append(option);
						        	}
						  		  });
						      },
						      204: function(responseObject2, textStatus, jqXHR) {
						        console.log("Medications - findAll() - 204 No Content");
						        Swal.fire({
					        		  position: 'center',
					        		  icon: 'error',
					        		  title: 'Something went wrong!',
					        		  showConfirmButton: false,
					        		  timer: 1500
					        		})
						      },
								403: function(responseObject2, textStatus, jqXHR) {
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
		  
		  
		  
		  $("#nameSPAN").empty();
		  $("#nameAddSPAN").empty();
		  $("#nameSPAN").append("Medications");
		  $("#nameAddSPAN").append("Add medication");
		  $("#allMedEditModal").modal();
		  
	  });
	  
	  $(document).on('click', '#profile-tab', function () {
		  var patient = sessionStorage.getItem('appPatientEmail').trim();
		  $.ajax ({
		    	type: 'GET',
		    	url: '/patientApi/medicalRecordForPatient/' + patient,
			    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
		    	statusCode: {
		    		200: function(responseObject, textStatus, jqXHR) {
		    			console.log("200 OK");
		    			loadPatientInfoAllOK(responseObject);
		    		},
		    		204: function(responseObject, textStatus, jqXHR) {
		    			console.log("204 No Content");
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
	  });
	  
	  $(document).on('change', '#appNextDate', function () {
		  var date = this.value;
		  console.log(date);
		  $.ajax({
			    type: 'POST',
			    url: 'doctorApi/getFreeTermsForCurrent',
			    data: {
			    	"date": date
			    },
			    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
			    statusCode: {
			      200: function(responseObject, textStatus, jqXHR) {
			        console.log("Appointments - findAll() - 200 OK");
			        var select = $("#appNextTime");
			        $("#appNextTime").removeAttr('disabled');
			        $.each(responseObject, function(i, val) {  
			  		  var option = $("<option>"+val.substring(11, 16)+"</option>");
			  		   select.append(option);
			  		  });
			      },
			      204: function(responseObject, textStatus, jqXHR) {
			        console.log("Appointments - findAll() - 204 No Content");
			        $("#appNextTime").attr('disabled', 'disabled');
			        Swal.fire({
		        		  position: 'center',
		        		  icon: 'error',
		        		  title: "You don' have free terms for that date!",
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
	  
	  $(document).on('click', '#appSchedule', function () {
		  var date = $("#appNextDate").val();
		  var type = $("#appNextType").val();
		  var time = $("#appNextTime").val();
		  
		  var dateTime = date + " " + time;
		  var doctor = sessionStorage.getItem('appDoctorEmail').trim();
		  var patient = sessionStorage.getItem('appPatientEmail').trim();
		  console.log(dateTime + " " + doctor + " " + patient);
		  $.ajax ({
			  	type: 'POST',
			  	url: 'appointmentApi/addAppointment',
			  	data : JSON.stringify({
					"email" : patient,
					"doctor" : doctor,
					"dateTime" : dateTime,
					"examType": type
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
			        		  title: "Appointment successfully scheduled!",
			        		  showConfirmButton: false,
			        		  timer: 1500
			        		})
			  		},
			  		204: function(responseObject, textStatus, jqXHR) {
			  			console.log("204 No Content");
			  			Swal.fire({
			        		  position: 'center',
			        		  icon: 'error',
			        		  title: "Something went wrong!",
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
	  
	  $(document).on('click', '#startBtn', function () {
		  var appId = $(this).parent().attr('id');
		  
		  $.ajax({
			    type: 'GET',
			    url: 'appointmentApi/findAppointmentById/' + appId,
			    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
			    statusCode: {
			      200: function(responseObject, textStatus, jqXHR) {
			        console.log("Appointments - findAll() - 200 OK");
			        startModal(responseObject);
			      },
			      204: function(responseObject, textStatus, jqXHR) {
			        console.log("Appointments - findAll() - 204 No Content");
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
	  });
	  
	  $(document).on('click', '#saveWeight', function () {
		  var patient = sessionStorage.getItem('appPatientEmail').trim();
		  var weight = $("#weight").val();
		  
		  if(weight == null || weight == "" || weight <= 0) {
				$("#weight").addClass("is-invalid");
			} else {
				$("#weight").removeClass("is-invalid");
			}
		  
		  if(weight != null && weight != "" && weight > 0) {
			  $.ajax({
				    type: 'PUT',
				    url: 'patientApi/changeWeight/' + patient + "&" + weight,
				    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
				    statusCode: {
				      200: function(responseObject, textStatus, jqXHR) {
				        console.log("Appointments - findAll() - 200 OK");
				        Swal.fire({
			        		  position: 'center',
			        		  icon: 'success',
			        		  title: 'Successfully changed!',
			        		  showConfirmButton: false,
			        		  timer: 1500
			        		})
				      },
				      204: function(responseObject, textStatus, jqXHR) {
				        console.log("Appointments - findAll() - 204 No Content");
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
	  
	  $(document).on('click', '#saveHeight', function () {
		  var patient = sessionStorage.getItem('appPatientEmail').trim();
		  var height = $("#height").val();
		  
		  if(height == null || height == "" || height <= 0) {
				$("#height").addClass("is-invalid");
			} else {
				$("#height").removeClass("is-invalid");
			}
		  
		  if(height != null && height != "" && height >0) {
			  $.ajax({
				    type: 'PUT',
				    url: 'patientApi/changeHeight/' + patient + "&" + height,
				    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
				    statusCode: {
				      200: function(responseObject, textStatus, jqXHR) {
				        console.log("Appointments - findAll() - 200 OK");
				        Swal.fire({
			        		  position: 'center',
			        		  icon: 'success',
			        		  title: 'Successfully changed!',
			        		  showConfirmButton: false,
			        		  timer: 1500
			        		})
				      },
				      204: function(responseObject, textStatus, jqXHR) {
				        console.log("Appointments - findAll() - 204 No Content");
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
	  
	  
	  $(document).on('click', '#seeReportBtn', function () {
		  var appId = $(this).parent().attr('id');
		  
		  $.ajax({
			    type: 'GET',
			    url: 'medicalReportApi/getReportForAppointment/' + appId,
			    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
			    statusCode: {
			      200: function(responseObject, textStatus, jqXHR) {
			        console.log("MedReport - findAll() - 200 OK");
			        console.log(responseObject);
			        seeReportModal(responseObject);
			      },
			      204: function(responseObject, textStatus, jqXHR) {
			        console.log("MedReport - findAll() - 204 No Content");
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
	  });
	  
	  $(document).on('click', '#appFinishBtn', function () {
		  var doctor = sessionStorage.getItem('appDoctorEmail');
		  var patient = sessionStorage.getItem('appPatientEmail');
		  var appID = sessionStorage.getItem('appID');
		  
		  var min = parseInt(document.getElementById("min").innerText);
		  var sec = parseInt(document.getElementById("sec").innerText)/60.0;
		  var duration = min + sec;
		  
		  const selected = document.querySelectorAll('#appDiagnosis option:checked');
		  const diagnosis = Array.from(selected).map(el => el.value);
		  
		  const selectedM = document.querySelectorAll('#appMedication option:checked');
		  const medications = Array.from(selectedM).map(el => el.value);
		  
		  var nurse = $("#appNurse").find('option:selected').attr('id');
		  
		  var details = $("#appDetails").val();
		  
		  $.ajax({
				 type: 'POST',
		        	url: 'medicalReportApi/createReport',
		    	    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
		        	dataType: 'json',
		        	data: JSON.stringify({
		        		"id": appID,
		        		"clinic": nurse+","+duration,
		        		"doctor": doctor,
		        		"patient": patient,
		        		"details": details,
		        		"diagnosis": diagnosis,
		        		"medications": medications
		        	}),
		        	contentType: "application/json; charset=utf-8",
				    dataType: "json",
				    statusCode: {
			    		200: function(responseObject, textStatus, jqXHR) {
			    			console.log("MedicalReports - findAllMedicalReports() - 200 OK");
			    			Swal.fire({
			    				  position: 'center',
			    				  icon: 'success',
			    				  title: 'Exam successfully finished!',
			    				  showConfirmButton: false,
			    				  timer: 1500
			    				})
			    			$("#"+appID)
			    			window.setTimeout(function(){location.reload()},1500)
			    		},
			    		400: function(responseObject, textStatus, jqXHR) {
			    			console.log("MedicalRecords - findAllMedicalReports() - 204 No Content");
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
	  });
});

function seeReportModal(report) {
	
	$("#modalReportID").val(report.id);
	$("#modalReportPatient").val(report.patient);
	$("#modalReportDetails").val(report.details);
	
	var selectMed = $("#modalReportMedications");
	selectMed.empty();
    $.each(report.medications, function(i, val) {  
		  var option = $("<option>"+val+"</option>");
		  selectMed.append(option);
	});
    
    var selectDiag = $("#modalReportDiagnosis");
    selectDiag.empty();
    $.each(report.medications, function(i, val) {  
		  var option = $("<option>"+val+"</option>");
		  selectDiag.append(option);
    });
	
	
	$("#modalReport").modal();
}

function loadPatientInfoAllOK(patient) {
	console.log(patient.fullName);
	var birthday = patient.birthday;
	var date = new Date(birthday);
	var day = date.getDay()-1;
	var month = date.getMonth()+1;
	var year = date.getFullYear();
	
	$("#fullName").val(patient.fullName);
	$("#birthday").val(month+'-'+day+'-'+year);
	$("#gender").val(patient.gender);
	$("#weight").val(patient.weight);
	$("#height").val(patient.height);
	$("#bloodType").val(patient.bloodType);
	console.log(patient.allergies.length);
	var allergiesTable = $("#allergiesBody");
	allergiesTable.empty();
	
	if(patient.allergies.length != 0){
		var row = $("<tr></tr>");
    	$.each(patient.allergies,function(i,val){	
    		row.append("<td>"+val+"</td>");
    	});
    	allergiesTable.append(row);
	}
	else {
		var row = $("<tr></tr>");
		row.append("<td><h6>No allergies</h6></td>")
		allergiesTable.append(row);
	}
	
	var medicationsTable = $("#medicationsBody");
	medicationsTable.empty();
	
	if(patient.perscription.length != 0){
		var row = $("<tr></tr>");
		$.each(patient.perscription,function(i,val){
    		row.append("<td>"+val.name+"</td>");
    	});
		medicationsTable.append(row);
	}
	else{
		var row = $("<tr></tr>");
		row.append("<td><h6>No medications</h6></td>")
		medicationsTable.append(row);
	}
}

function startModal(appointment) {
	start();
	console.log(appointment);
	$("#appPatient").val(appointment.patient.split(",")[0]);
	$("#appDoctor").val(appointment.doctor.split(",")[0]);
	$("#appDate").val(appointment.datetime);
	
	sessionStorage.setItem("appID", appointment.id);
	sessionStorage.setItem("appPatientEmail", appointment.patient.split(",")[1]);
	sessionStorage.setItem("appDoctorEmail", appointment.doctor.split(",")[1]);
	
	$.ajax({
	    type: 'GET',
	    url: 'diagnosisApi/findAll',
	    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
	    statusCode: {
	      200: function(responseObject, textStatus, jqXHR) {
	        console.log("Diagnosis - findAll() - 200 OK");
	        var select = $("#appDiagnosis");
	        $.each(responseObject, function(i, val) {  
	  		  var option = $("<option>"+val.name+"</option>");
	  		   select.append(option);
	  		  });
	      },
	      204: function(responseObject, textStatus, jqXHR) {
	        console.log("Diagnosis - findAll() - 204 No Content");
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
	
	$.ajax({
	    type: 'GET',
	    url: 'medicationApi/findAll',
	    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
	    statusCode: {
	      200: function(responseObject, textStatus, jqXHR) {
	        console.log("Medications - findAll() - 200 OK");
	        var select = $("#appMedication");
	        $.each(responseObject, function(i, val) { 
	  		  var option = $("<option>"+val.name+"</option>");
	  		   select.append(option);
	  		  });
	      },
	      204: function(responseObject, textStatus, jqXHR) {
	        console.log("Medications - findAll() - 204 No Content");
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
	
	
	$.ajax({
	    type: 'GET',
	    url: 'nurseApi/findAllByClinicForCurrentDoctor',
	    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
	    statusCode: {
	      200: function(responseObject, textStatus, jqXHR) {
	        console.log("Medications - findAll() - 200 OK");
	        var select = $("#appNurse");
	        $.each(responseObject, function(i, val) { 
	  		  var option = $("<option id=\"" + val.email + "\">"+val.name+"</option>");
	  		   select.append(option);
	  		  });
	      },
	      204: function(responseObject, textStatus, jqXHR) {
	        console.log("Medications - findAll() - 204 No Content");
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
	
	$("#startAppModal").modal({
		backdrop: 'static'
	});
}

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

function appointmentsAllOK(appointmentsList) {
	  var table = $("#appointmentsBody");
	  table.empty();
	  console.log(appointmentsList);
	  var curr = currentDate();
	  
	  $.each(appointmentsList, function(i, val) {
	    var row = $("<tr id=\""+i+"\"></tr>");
	    row.append("<td class=\"w-25\" id=\""+val.id+"\">" + val.datetime + "</td>");
	    row.append("<td class=\"w-25\" id=\""+val.id+"\">" + val.patient +"</td>");
	   
	    
	    if(!val.held && val.datetime.substring(0,10) == curr) {
	    	row.append("<td class=\"w-25 text-right\" id=\""+val.id+"\"><button type=\"button\" id=\"startBtn\" class=\"btn btn-primary\">Start appointment</button></td>");
	    	row.append("<td class=\"w-25 text-right\" id=\""+val.id+"\"><button type=\"button\" id=\"seeReportBtn\" class=\"btn btn-primary\" disabled>See report</button></td>");
	    	
	    }else {
	    	row.append("<td class=\"w-25 text-right\" id=\""+val.id+"\"><button type=\"button\" id=\"startBtn\" class=\"btn btn-primary\" disabled>Start appointment</button></td>");
	    	row.append("<td class=\"w-25 text-right\" id=\""+val.id+"\"><button type=\"button\" id=\"seeReportBtn\" class=\"btn btn-primary\">See report</button></td>");
	    }
	    	table.append(row);
	    
	  });
	}

	function appointmentsAllNO(responseObject) {
	  var table = $("#appointmentsBody");
	  table.empty();
	  
	  var row = $("<tr></tr>");
	  row.append("<td class='pl-1'>There are no appointments.</td>");
	  
	  table.append(row);
	}
	function unauthorized(){
		document.write("<html><head></head><body>UNAUTHORIZED</body></html>");
	}