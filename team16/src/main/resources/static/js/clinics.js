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
  var table = $("#clinicsTableBody");
  table.empty();
  
  $.each(clinicsList, function(i, val) {
    var row = $("<tr class=\"clinic-table-text\" title=\"Click for more information\" id=\""+i+"\"></tr>");

    row.append("<td id=\""+val.clinicID+"\">" + val.name + "</td>");

    table.append(row);
  });
}

function clinicsAllNO(responseObject) {
  var table = $("#clinicsTableBody");
  table.empty();
  
  var row = $("<tr></tr>");
  row.append("<td class='pl-1'>There is clinics in the system</td>");
  
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