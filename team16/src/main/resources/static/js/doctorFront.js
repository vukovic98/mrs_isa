$(document).ready(function(){
	
	console.log("ready");

  $.ajax({
    type: 'GET',
    url: 'patientApi/findAll',
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
  var table = $("#patientBody");
  table.empty();

  console.log(medicationsList);
  $.each(medicationsList, function(i, val) {
    var row = $("<tr id=\""+i+"\"></tr>");
    row.append("<td id=\""+val.id+"\">" + val.insurance + "</td>");
    row.append("<td id=\""+val.id+"\">" + val.name + "</td>");
    row.append("<td id=\""+val.id+"\">" + val.phone + "</td>");
    table.append(row);
  });
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
