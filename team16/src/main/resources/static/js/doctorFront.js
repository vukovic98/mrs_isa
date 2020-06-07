$(document).ready(function(){
	
	console.log("ready");

  $.ajax({
    type: 'GET',
    url: 'patientApi/findAllForList',
    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
    statusCode: {
      200: function(responseObject, textStatus, jqXHR) {
        console.log("Patients - findAll() - 200 OK");
        patientsAllOK(responseObject);
      },
      204: function(responseObject, textStatus, jqXHR) {
        console.log("Patients - findAll() - 204 No Content");
        patientsAllNO(responseObject);
      },
      403: function(responseObject, textStatus, jqXHR) {
          console.log("Patients - findAll() - 403 Unauthorized");
          unauthorized();
        }
    }
  });
});

function patientsAllOK(medicationsList) {
  var table = $("#usersTable");
  table.empty();

  console.log(medicationsList);
  /*$.each(medicationsList, function(i, val) {
    var row = $("<tr id=\""+i+"\"></tr>");
    row.append("<td id=\"id\">" + val.insurance + "</td>");
    row.append("<td id=\"name\">" + val.name + "</td>");
    row.append("<td id=\""+val.id+"\">" + val.phone + "</td>");
    table.append(row);
  });*/
  
  table.bootstrapTable({
	  data: medicationsList,
	  pagination: true,
	  search: true,
	  columns: [{
	    field: 'insurance',
	    title: 'Insurance number',
	    sortable: true
	  }, {
	    field: 'name',
	    title: 'Name',
	    sortable: true
	  }, {
	    field: 'phone',
	    title: 'Phone number'
	  }]
	})
}

function patientsAllNO(responseObject) {
  var table = $("#patientBody");
  table.empty();
  
  var row = $("<tr></tr>");
  row.append("<td class='pl-1'>There are no patients in system.</td>");
  
  table.append(row);
}

function unauthorized(){
	document.write("<html><head></head><body>UNAUTHORIZED</body></html>");
}
