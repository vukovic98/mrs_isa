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
 function formatDate(d) {
     month = '' + (d.getMonth() + 1),
     day = '' + d.getDate(),
     year = d.getFullYear();

	 if (month.length < 2) 
	     month = '0' + month;
	 if (day.length < 2) 
	     day = '0' + day;
	 var hrs = (d.getHours()<=9) ? "0"+d.getHours() : d.getHours() ;
	 var mins = (d.getMinutes()=="0") ? "00" : d.getMinutes() ;
	 return [year, month, day].join('-') +" "+ hrs+":"+mins+"h";
}
    function loadAppHistoryAllOK(appointments) {
    	
    	var table = $("#appointmentHistoryBody");
    	table.empty();
    	
    	if(appointments.length != 0){
	    	$.each(appointments, function(i, val) {
	    		
	    		var row = $("<tr id=\""+val.appointmentID+"\"></tr>");
	    		var d = new Date(val.datetime);
	    		var date = formatDate(d);
	    		//za approved i held
	    		var now = new Date();
	    		now = Date.now();
	    		var inDate = new Date(val.datetime);
	    		
	    		row.append("<td >" + date + "</td>");
	    		row.append("<td >" + val.appointmentType + "</td>");
	    		row.append("<td class=\"clinicTD\" id=\""+val.clinicID+"\">" + val.clinic + "</td>");
	    		row.append("<td class=\" doctorTD\"id=\""+val.doctorID+"\" >" + val.doctor + "</td>");
	    		if (val.approved){
	    			row.append("<td >" + "Yes" + "</td>");}
	    		else {
	    			row.append("<td >" + "No" + "</td>");
	    		}
	    		
	    		if (val.approved && inDate < now){
	    			row.append("<td >" + "Yes" + "</td>");
		    		row.append("<td class=\" modalTD\" id="+val.appointmentID+" align=\"center\"><button type=\"button\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"Click to rate clinic and doctor\" class=\"btn btn-indigo btn-sm m-0\"><i class=\"fa fa-star\"></i></button></td>");}
	    		else {
	    			row.append("<td >" + "No" + "</td>");
	    			row.append("<td >" + "" + "</td>");
	    		}
	    		table.append(row);
	    	});
    	}
    	else{
    		var row = $("<tr></tr>");

    		row.append("<td class=\"w-50 text-center\" colspan=\"7\"> There are no appointments to show.</td>");
    		table.append(row);
    	}
    }
    	
    function unauthorized(){
    	document.write("<html><head></head><body><h1>UNAUTHORIZED</h1></body></html>");
    }
    	
    
    function loadPatientInfoNO(patient){
    	Swal.fire({
  		  position: 'center',
  		  icon: 'error',
  		  title: 'Patient not found!',
  		  showConfirmButton: false,
  		  timer: 1500
  		})	
    }
    

    $(document).on('click', '.modalTD', function () {

        var apId = $(this).attr('id');
       
        $.ajax({
        	type: 'POST',
        	url: 'patientApi/findModalByID',
    	    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
        	dataType: 'json',
        	data: JSON.stringify({
        		appointmentId: apId 
        	}),
        	contentType: "application/json; charset=utf-8",
		
        	success: function(app) {
        		console.log(app);
        		$("#rateDoctor").text("Rate dr "+app.doctor);
		        $("#rateClinic").text("Rate clinic \""+app.clinic+"\"");
		        
		       
		        //console.log(clinicID);
		        
		        
		        $("#exampleModal").modal();
        	}
        });
    });
    var data = {
    		grade:$("#clinicGrade").val(),
    		ID: $('.clinicTD').attr('id')
    }
    $("#rateClinicBtn").click(function(){
    	var grade = 
    	console.log("clinic id: "+$('.clinicTD').attr('id'));
    	$.ajax({
        	type: 'PUT',
        	url: 'clinicApi/rateClinic/' + $('.clinicTD').attr('id')+"&"+$("#clinicGrade").val(),
        	headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
        	
        	contentType: "application/json; charset=utf-8",
		   
		    statusCode :{
		    	200:function(){
		    		console.log("200 OK");
		    		Swal.fire({
		        		  position: 'center',
		        		  icon: 'success',
		        		  title: 'Rated!',
		        		  showConfirmButton: false,
		        		  timer: 1500
		        		})
		    		

		    	},
		    	204:function(){
		    		console.log("204 No Content");
		    		Swal.fire({
		        		  position: 'center',
		        		  icon: 'error',
		        		  title: 'Something went wrong, please try again!',
		        		  showConfirmButton: false,
		        		  timer: 1500
		        		})
		    	}
		    } 
			    	
			    
        });
    });
    $("#rateDoctorBtn").click(function(){
    	var grade = $("#doctorGrade").val();
    	console.log($('.doctorTD').attr('id'));
    	$.ajax({
        	type: 'PUT',
        	url: 'doctorApi/rateDoctor/'+$('.doctorTD').attr('id')+"&"+grade,
        	headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
        	contentType: "application/json; charset=utf-8",

		    statusCode :{
		    	200:function(){
		    		console.log("200 OK");
		    		Swal.fire({
		        		  position: 'center',
		        		  icon: 'success',
		        		  title: 'Rated!',
		        		  showConfirmButton: false,
		        		  timer: 1500
		        		})
		    		

		    	},
		    	400:function(){
		    		console.log("400 No Content");
		    		Swal.fire({
		        		  position: 'center',
		        		  icon: 'error',
		        		  title: 'Something went wrong, please try again!',
		        		  showConfirmButton: false,
		        		  timer: 1500
		        		})
		    	}
		    } 
			    	
			    
        });
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