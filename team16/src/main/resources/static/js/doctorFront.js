$(document).ready(function(){
	
	console.log("ready");

  $.ajax({
    type: 'GET',
    url: 'patientApi/findAll',
    statusCode: {
      200: function(responseObject, textStatus, jqXHR) {
        console.log("Patients - findAll() - 200 OK");
        patientsAllOK(responseObject);
      },
      204: function(responseObject, textStatus, jqXHR) {
        console.log("Patients - findAll() - 204 No Content");
        patientsAllNO(responseObject);
      }
    }
  });
});

function patientsAllOK(medicationsList) {
  var table = $("#patientBody");
  table.empty();

  
  $.each(medicationsList, function(i, val) {
    var row = $("<tr id=\""+i+"\"></tr>");
    row.append("<td id=\""+val.id+"\">" + val.insuranceNumber "</td>");
    row.append("<td id=\""+val.id+"\">" + val.firstName + " " + val.lastName "</td>");
    row.append("<td id=\""+val.id+"\">" + val.phoneNumber + "</td>");
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
