$( document ).ready(function() {
  console.log("ready");
  var atrs = sessionStorage.getItem('appParam').split("&");;
  console.log(atrs);
  var clinicID = atrs[0];
  var appType = atrs[1];
  var date = atrs[2];
  
  $.ajax ({
  	type: 'GET',
  	url: 'clinicApi/findAllAppointmentDoctors/' + clinicID + "&" + appType + "&" + date,
	headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
  	statusCode: {
  		200: function(responseObject, textStatus, jqXHR) {
  			console.log("200 OK");
  			sessionStorage.setItem('appParam', "");
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
});

function showDoctors(doctors) {
	var table = $("#doctorsTableBody");
	table.empty();
	$.each(doctors,function(i,val){
		var row = $("<tr></tr>");
		row.append("<td >" + val.firstName + " " + val.lastName + "</td>");
		row.append("<td >" + val.averageGrade + "</td>");
		var selectt="<td><select><option  disabled selected>Select term</option><option>15:20</option></select></td>";
		row.append(selectt);
		table.append(row);	
	  });
}

function searchDoctors() {
	// Declare variables
	var input, filter, table, tr, td, i, j, txtValue;
	input = document.getElementById("myInput");
	filter = input.value.toUpperCase();
	table = document.getElementById("doctorsTable");
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

 