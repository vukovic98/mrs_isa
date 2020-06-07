$( document ).ready(function() {
    console.log( "ready!" );
    
    $.ajax ({
    	type: 'GET',

    	url: '/patientApi/medicalRecord',

	    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },

    	statusCode: {
    		200: function(responseObject, textStatus, jqXHR) {
    			console.log("200 OK");
    			loadPatientInfoAllOK(responseObject);
    		},
    		204: function(responseObject, textStatus, jqXHR) {
    			console.log("204 No Content");
    			loadPatientInfoNO(responseObject);
    		},
			403: function(responseObject, textStatus, jqXHR) {
				console.log("403 Unauthorized");
				unauthorized();
			}
    	}
    });
  
});
    function loadPatientInfoAllOK(patient) {
    	console.log(patient.fullName);
    	var birthday = patient.birthday;
    	var date = new Date(birthday);
    	var day = date.getDay()-1;
    	var month = date.getMonth()+1;
    	var year = date.getFullYear();
    	
    	$("#fullName").val(patient.fullName);
    	$("#birthday").val(day+'-'+month+'-'+year);
    	$("#gender").val(patient.gender);
    	$("#weight").val(patient.weight + " kg");
    	$("#height").val(patient.height+ " cm");
    	$("#bloodType").val(patient.bloodType);
    	var allergiesTable = $("#allergiesBody");
    	allergiesTable.empty();
    	
    	if(patient.allergies != null){
    		
        	$.each(patient.allergies,function(i,val){
        		var row = $("<tr></tr>");
        		row.append("<td>"+val+"</td>");
        		allergiesTable.append(row);
        	
        	});
    	}
    	else{
    		allergiesList.append("<h6>There are no allergies found</h6>");
    	}
    	
    	var medicationsTable = $("#medicationsTable");
    	if(patient.perscription != null){
    		medicationsTable.bootstrapTable(
    			  {
    				  data: patient.perscription,
    				  columns: [{
    				    field: 'name',
    				    title: 'Name',
    				    sortable: true
    				  }, {
    				    field: 'code',
    				    title: 'Code',
    				    sortable: true
    				  }]
    				}
    	  
    	  );}
    	else{
			medicationsTable.append("<tr><td colspan=\"2\">There are no perscriptions found</td></tr>");
		}
    	
    	

    		

    	}
    	 
    function loadPatientInfoNO(patient){
    	$("#notFoundLabel").text("Medical Record Not Found");	
    }
    
    function unauthorized(){
    	document.write("<html><head></head><body>UNAUTHORIZED</body></html>");
    }