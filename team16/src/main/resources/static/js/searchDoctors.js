$( document ).ready(function() {
  console.log("ready");

  $.ajax({
    type: 'GET',
    url: 'clinicApi/findAll',
    statusCode: {
      200: function(responseObject, textStatus, jqXHR) {
        console.log("Clinics - findAll() - 200 OK");
        clinicsAllOK(responseObject);
      },
      204: function(responseObject, textStatus, jqXHR) {
        console.log("Clinics - findAll() - 204 No Content");
        clinicsAllNO(responseObject);
      }
    }
  });
});

function clinicsAllOK(clinicsList) {
 
  var table = $("#doctorsTableBody");
  table.empty();
  
  $.each(doctorsList, function(i, val) {
    var row = $("<tr  title=\"Click for more information\" id=\""+i+"\"></tr>");

    row.append("<td>" + val.name + "</td>");
    row.append("<td>" + "10" + "</td>");
    row.append("<td> 10:30,15:30 </td>");


    table.append(row);
  });
  
}

function clinicsAllNO(responseObject) {
  var table = $("#allClinicsTableBody");
  table.empty();
  
  var row = $("<tr></tr>");
  row.append("<td class='pl-1'>There is no clinics in the system</td>");
  
  table.append(row);
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

 