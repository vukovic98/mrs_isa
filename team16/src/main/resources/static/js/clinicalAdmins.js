$( document ).ready(function() {
  console.log("ready");

  $.ajax({
    type: 'GET',
    url: 'clinicAdminApi/findAll',
    statusCode: {
      200: function(responseObject, textStatus, jqXHR) {
        console.log("ClinicAdmins - findAll() - 200 OK");
        clinicAdminsAllOK(responseObject);
      },
      204: function(responseObject, textStatus, jqXHR) {
        console.log("ClinicAdmins - findAll() - 204 No Content");
        clinicAdminsAllNO(responseObject);
      }
    }
  });
});

function clinicAdminsAllOK(clinicAdminsList) {
  var table = $("#clinicAdminsBody");
  table.empty();

  
  $.each(clinicAdminsList, function(i, val) {
    var row = $("<tr class=\"clinic-table-text\" title=\"Click for more information\" id=\""+i+"\"></tr>");

    row.append("<td id=\""+val.id+"\"><p>" + val.firstName + " " + val.lastName + "</p>" +
          "<p style=\"font-size: large\"><i>" + val.clinic.name + "</i></td>");
    row.append("<span style=\"width: 30px; height: 30px;\" class=\"btn btn-sm mt-4 fl-right delete_btn\" type=\"button\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"Delete\"><i class=\"fa fa-trash\"></i></span>");

    table.append(row);
  });
}

function clinicAdminsAllNO(responseObject) {
  var table = $("#clinicAdminsBody");
  table.empty();
  
  var row = $("<tr></tr>");
  row.append("<td class='pl-1'>There are no clinical admins in system.</td>");
  
  table.append(row);
}

function myFunction() {
  // Declare variables
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("clinicsTable");
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

 function searchClinics() {
  // Declare variables
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("myTable");
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