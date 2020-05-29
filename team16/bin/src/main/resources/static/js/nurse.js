$(document).ready(function () {
    
    $.ajax ({
    	type: 'GET',
    	url: 'patientApi/findAll',
        headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
    	statusCode: {
    		200: function(responseObject, textStatus, jqXHR) {
    			console.log("RegistrationRequests - findAll() - 200 OK");
    			patientsAllOK(responseObject);
    		},
    		204: function(responseObject, textStatus, jqXHR) {
    			console.log("RegistrationRequests - findAll() - 204 No Content");
    			patientsAllNO(responseObject);
    		},
    		403: function(responseObject, textStatus, jqXHR) {
    			console.log("403 Unauthorized");
    			unauthorized();
    		}
    	}
    });
    
    $(document).on('click', '.modalTD', function () {

        var email = $(this).attr('id');
        console.log("UDJE MODAL");
        $.ajax({
        	type: 'POST',
        	url: 'patientApi/findModalByEmail',
    	    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
        	dataType: 'json',
        	data: JSON.stringify({
        		"email": email 
        	}),
        	contentType: "application/json; charset=utf-8",
		    dataType: "json",
        	success: function(patient) {
        		$("#modalName").text(patient.name);
		        $("#modalEmail").text(patient.email);
		        $("#modalAddress").text(patient.address);
		        $("#modalPhone").text(patient.phone);
		        $("#modalCity").text(patient.city);
		        $("#modalCountry").text(patient.country);
		        $("#modalInsNumber").text(patient.insurance);
		        
		        $("#exampleModal").modal();
        	}
        });
    });

});

function myFunction() {
  // Declare variables
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("patientsTable");
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

function patientsAllOK(patientList) {
	var table = $("#patientsBody");
	table.empty();
	
	$.each(patientList, function(i, val) {
		var row = $("<tr id=\""+i+"\"></tr>");
		console.log(val.patient);
		row.append("<td class=\"w-50 modalTD\" id=\""+val.email+"\">" + val.name + "</td>");

		table.append(row);
	});
}

function patientsAllNO(responseObject) {
	var table = $("#patientsBody");
	table.empty();
	
	var row = $("<tr></tr>");
	row.append("<td class='pl-1'>There is no patients in your clinic.</td>");
	
	table.append(row);
}

function unauthorized(){
	document.write("<html><head></head><body>UNAUTHORIZED</body></html>");
}

function fire_ajax_submit() {

    var create = {}
    create["email"] = $("#email").val();
    create["password"] = $("#password").val();
    create["firstName"] = $("#firstName").val();
    create["lastName"] = $("#lastName").val();
    create["address"] = $("#address").val();
    create["phoneNumber"] = $("#phone").val();
    create["city"] = $("#city").val();
    create["country"] = $("#state").val();
    create["insuranceNumber"] = $("#insurance").val();


    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/nurseApi",
	    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
        data: JSON.stringify(create),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            console.log("SUCCESS : ", data);
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });

}