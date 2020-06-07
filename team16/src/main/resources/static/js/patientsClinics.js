$( document ).ready(function() {
  console.log("ready");

  $.ajax({
    type: 'GET',
    url: 'clinicApi/findAll',
    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
    statusCode: {
      200: function(responseObject, textStatus, jqXHR) {
        console.log("Clinics - findAll() - 200 OK");
        clinicsAllOK(responseObject);
      },
      204: function(responseObject, textStatus, jqXHR) {
        console.log("Clinics - findAll() - 204 No Content");
        clinicsAllNO(responseObject);
      },
		403: function(responseObject, textStatus, jqXHR) {
			console.log("403 Unauthorized");
			unauthorized();
		}
    }
  });
});

function clinicsAllOK(clinicsList) {
  var table = $("#allClinicsTable");
  table.bootstrapTable(
		  {
			  data: clinicsList,
			  pagination: true,
			  columns: [{
			    field: 'name',
			    title: 'Clinic',
			    sortable: true
			  }, {
			    field: 'address',
			    title: 'Address',
			    sortable: true
			  }, {
				    field: 'city',
				    title: 'City',
				    sortable: true
				  }, 
			  {
			    field: 'averageGrade',
			    title: 'Average grade',
			    sortable: true
			  }]
			}
  
  );

 /* $.each(clinicsList, function(i, val) {
	  
	    var row = $("<tr title=\"Click for more information\" id=\""+i+"\"></tr>");

	    row.append("<td style=\"font-size:20px\">" + val.name + "</td>");
	    row.append("<td style=\"font-size:20px\">"+ val.address + "</td>");
	    row.append("<td style=\"font-size:20px\">"+ val.averageGrade +"</td>");


	    table.append(row);
	  });*/

}

function clinicsAllNO(responseObject) {
  var table = $("#allClinicsTableBody");
  table.empty();
  
  var row = $("<tr></tr>");
  row.append("<td class='pl-1'>There is no clinics in the system</td>");
  
  table.append(row);
}

function unauthorized(){
	document.write("<html><head></head><body>UNAUTHORIZED</body></html>");
}

function searchClinics() {
	// Declare variables
	var input, filter, table, tr, td, i, j, txtValue;
	input = document.getElementById("myInput");
	filter = input.value.toUpperCase();
	table = document.getElementById("allClinicsTable");
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

 