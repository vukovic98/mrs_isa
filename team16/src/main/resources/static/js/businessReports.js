$(document).ready(function(){
	 var idAdmin;
	 
	  $.ajax({
		    type: 'GET',
		    url: 'clinicApi/getCurrentClinic',
		    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
		    statusCode: {
		      200: function(responseObject, textStatus, jqXHR) {
		    	  $.ajax({
		  		    type: 'GET',
		  		    url: 'clinicApi/getClinicGrade/' + responseObject,
		  		    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
		  		    statusCode: {
		  		      200: function(responseObject, textStatus, jqXHR) {
		  		        console.log("Rooms - findAll() - 200 OK");
		  		        businessAllOK(responseObject);
		  		      },
		  		      204: function(responseObject, textStatus, jqXHR) {
		  		        console.log("Rooms - findAll() - 204 No Content");
		  		        businessAllNO(responseObject);
		  		      },
		  				403: function(responseObject, textStatus, jqXHR) {
		  					console.log("403 Unauthorized");
		  					unauthorized();
		  				}
		  		    }
		  		  });
		    	  
		    	  $.ajax({
			  		    type: 'GET',
			  		    url: 'clinicApi/getDoctorsGrade/' + responseObject,
			  		    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
			  		    statusCode: {
			  		      200: function(responseObject, textStatus, jqXHR) {
			  		        console.log("Rooms - findAll() - 200 OK");
			  		        var table = $("#averageDoctorBody");
			  		        $.each(responseObject, function(i, val) {
			  		        	var row = $("<tr id=\""+val.name+"\"></tr>")
			  		        	
			  		        	row.append($("<td>"+val.name+"</td>"));
			  		        	row.append($("<td>"+val.average+"</td>"));
			  		        	
			  		        	table.append(row);
			  			   });
			  		        
			  		      },
			  		      204: function(responseObject, textStatus, jqXHR) {
			  		        console.log("Rooms - findAll() - 204 No Content");
			  		        
			  		      },
			  				403: function(responseObject, textStatus, jqXHR) {
			  					console.log("403 Unauthorized");
			  					unauthorized();
			  				}
			  		    }
			  		  });
		    	  
		    	  $.ajax({
		  		    type: 'GET',
		  		    url: 'clinicApi/getDailyAppointments/' + responseObject +'/' + 0,
		  		    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
		  		    statusCode: {
		  		      200: function(responseObject, textStatus, jqXHR) {
		  		        console.log("Rooms - findAll() - 200 OK");
		  			  var options = {
		  					title: {
		  						text: "Held appointments for this week"
		  					},
		  					animationEnabled: true,
		  					exportEnabled: false,
		  					data: [
		  					{
		  						type: "column", //change it to line, area, column, pie, etc
		  						dataPoints: responseObject
		  					}
		  					],
		  					 axisY:{
		  						  title:"Number of appointments",
		  						  },
		  				};
		  			  
		  			  
		  				$("#chartContainer1").CanvasJSChart(options);
		  				$("#chartContainer1").render();
		  		      },
		  		      204: function(responseObject, textStatus, jqXHR) {
		  		        console.log("Rooms - findAll() - 204 No Content");
		  		        
		  		      },
		  				403: function(responseObject, textStatus, jqXHR) {
		  					console.log("403 Unauthorized");
		  					unauthorized();
		  				}
		  		    }
		  		  });
		    	  
		    	  
		    	  $.ajax({
			  		    type: 'GET',
			  		    url: 'clinicApi/getMonthlyAppointments/' + responseObject +'/' + 0,
			  		    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
			  		    statusCode: {
			  		      200: function(responseObject, textStatus, jqXHR) {
			  		        console.log("Rooms - findAll() - 200 OK");
			  			  var options = {
			  					title: {
			  						text: "Held appointments for this month"
			  					},
			  					animationEnabled: true,
			  					exportEnabled: false,
			  					data: [
			  					{
			  						type: "column", //change it to line, area, column, pie, etc
			  						dataPoints: responseObject
			  					}
			  					],
			  					 axisY:{
			  						  title:"Number of appointments",
			  						  },
			  				};
			  			  
			  			  
			  				$("#chartContainer2").CanvasJSChart(options);
			  		        
			  		      },
			  		      204: function(responseObject, textStatus, jqXHR) {
			  		        console.log("Rooms - findAll() - 204 No Content");
			  		        
			  		      },
			  				403: function(responseObject, textStatus, jqXHR) {
			  					console.log("403 Unauthorized");
			  					unauthorized();
			  				}
			  		    }
			  		  });
		    	  
		    	  
		    	  $.ajax({
			  		    type: 'GET',
			  		    url: 'clinicApi/getYearlyAppointments/' + responseObject +'/' + 0,
			  		    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
			  		    statusCode: {
			  		      200: function(responseObject, textStatus, jqXHR) {
			  		        console.log("Rooms - findAll() - 200 OK");
			  			  var options = {
			  					title: {
			  						text: "Held appointments for this year"
			  					},
			  					animationEnabled: true,
			  					exportEnabled: false,
			  					data: [
			  					{
			  						type: "column", //change it to line, area, column, pie, etc
			  						dataPoints: responseObject
			  					}
			  					],
			  					 axisY:{
			  						  title:"Number of appointments",
			  						  },
			  				};
			  			  
			  			  
			  				$("#chartContainer3").CanvasJSChart(options);
			  		        
			  		      },
			  		      204: function(responseObject, textStatus, jqXHR) {
			  		        console.log("Rooms - findAll() - 204 No Content");
			  		        
			  		      },
			  				403: function(responseObject, textStatus, jqXHR) {
			  					console.log("403 Unauthorized");
			  					unauthorized();
			  				}
			  		    }
			  		  });
		    	  
		    	  
		    	  $.ajax({
			  		    type: 'GET',
			  		    url: 'clinicApi/getDailyAppointments/' + responseObject +'/' + 1,
			  		    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
			  		    statusCode: {
			  		      200: function(responseObject, textStatus, jqXHR) {
			  		        console.log("Rooms - findAll() - 200 OK");
			  			  var options = {
			  					title: {
			  						text: "Appointment profit for this week"
			  					},
			  					animationEnabled: true,
			  					exportEnabled: false,
			  					data: [
			  					{
			  						type: "column", //change it to line, area, column, pie, etc
			  						dataPoints: responseObject
			  					}
			  					],
			  					 axisY:{
			  						  title:"Value in $",
			  						  },
			  				};
			  			  
			  			  
			  				$("#chartContainer11").CanvasJSChart(options);
			  		        
			  		      },
			  		      204: function(responseObject, textStatus, jqXHR) {
			  		        console.log("Rooms - findAll() - 204 No Content");
			  		        
			  		      },
			  				403: function(responseObject, textStatus, jqXHR) {
			  					console.log("403 Unauthorized");
			  					unauthorized();
			  				}
			  		    }
			  		  });
			    	  
			    	  
			    	  $.ajax({
				  		    type: 'GET',
				  		    url: 'clinicApi/getMonthlyAppointments/' + responseObject +'/' + 1,
				  		    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
				  		    statusCode: {
				  		      200: function(responseObject, textStatus, jqXHR) {
				  		        console.log("Rooms - findAll() - 200 OK");
				  			  var options = {
				  					title: {
				  						text: "Appointment profit for this month"
				  					},
				  					animationEnabled: true,
				  					exportEnabled: false,
				  					data: [
				  					{
				  						type: "column", //change it to line, area, column, pie, etc
				  						dataPoints: responseObject
				  					}
				  					],
				  					 axisY:{
				  						  title:"Value in $",
				  						  },
				  				};
				  			  
				  			  
				  				$("#chartContainer22").CanvasJSChart(options);
				  		        
				  		      },
				  		      204: function(responseObject, textStatus, jqXHR) {
				  		        console.log("Rooms - findAll() - 204 No Content");
				  		        
				  		      },
				  				403: function(responseObject, textStatus, jqXHR) {
				  					console.log("403 Unauthorized");
				  					unauthorized();
				  				}
				  		    }
				  		  });
			    	  
			    	  
			    	  $.ajax({
				  		    type: 'GET',
				  		    url: 'clinicApi/getYearlyAppointments/' + responseObject +'/' + 1,
				  		    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
				  		    statusCode: {
				  		      200: function(responseObject, textStatus, jqXHR) {
				  		        console.log("Rooms - findAll() - 200 OK");
				  			  var options = {
				  					title: {
				  						text: "Appointment profit for this year"
				  					},
				  					animationEnabled: true,
				  					exportEnabled: false,
				  					data: [
				  					{
				  						type: "column", //change it to line, area, column, pie, etc
				  						dataPoints: responseObject
				  					}
				  					],
				  					 axisY:{
				  						  title:"Value in $",
				  						  },
				  				};
				  			  
				  			  
				  				$("#chartContainer33").CanvasJSChart(options);
				  		        
				  		      },
				  		      204: function(responseObject, textStatus, jqXHR) {
				  		        console.log("Rooms - findAll() - 204 No Content");
				  		        
				  		      },
				  				403: function(responseObject, textStatus, jqXHR) {
				  					console.log("403 Unauthorized");
				  					unauthorized();
				  				}
				  		    }
				  		  });
		      },
		      204: function(responseObject, textStatus, jqXHR) {

		      },
				403: function(responseObject, textStatus, jqXHR) {
					console.log("403 Unauthorized");
					unauthorized();
				}
		    }
		  });
	

	  

	  

});

function businessAllOK(clinic){
	  
	 $("#averageClinic").text('The average grade of the clinic is '+clinic.averageGrade+'.')
	  
}