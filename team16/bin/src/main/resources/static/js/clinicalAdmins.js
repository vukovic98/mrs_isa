$( document ).ready(function() {
  console.log("ready");

  $.ajax({
    type: 'GET',
    url: 'clinicAdminApi/findAll',
    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
    statusCode: {
      200: function(responseObject, textStatus, jqXHR) {
        console.log("ClinicAdmins - findAll() - 200 OK");
        clinicAdminsAllOK(responseObject);
      },
      204: function(responseObject, textStatus, jqXHR) {
        console.log("ClinicAdmins - findAll() - 204 No Content");
        clinicAdminsAllNO(responseObject);
      },
		403: function(responseObject, textStatus, jqXHR) {
			console.log("403 Unauthorized");
			unauthorized();
		}
    }
  });


  $(document).on('click', '#addClinicalAdmin', function () {
    $.ajax({
      type: 'GET',
      url: 'clinicApi/findAll',
      headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
      statusCode: {
        200: function(responseObject, textStatus, jqXHR) {
          console.log("Clinics - findAll() - 200 OK");
          $.each(responseObject, function(i, val) {
              $('#clinics').append(`<option value="${val.name}">${val.name}</option>`); 
          });

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
      $("#exampleModal").modal();
  });

  $(document).on('click', '#addAdmin', function (e) {
      e.preventDefault();
    
      var email = $("#email").val();
      var pass = $("#pass").val();
      var city = $("#city").val();
      var country = $("#country").val();
      var firstName = $("#firstName").val();
      var lastName = $("#lastName").val();
      var insNumber = $("#insNumber").val();
      var phone = $("#phone").val();
      var address = $("#address").val();
      var clinic = $("#clinics").val();
      
      console.log(email);
      console.log(clinic);

      if(email == null || email == "") {
        $("#email").addClass("is-invalid");
      } else {
        $("#email").removeClass("is-invalid");
      }
      
      if(clinic == null || clinic == "") {
        $("#clinics").addClass("is-invalid");
      } else {
        $("#clinics").removeClass("is-invalid");
      }

      if(pass == null || pass == "") {
        $("#pass").addClass("is-invalid");
      } else {
        $("#pass").removeClass("is-invalid");
      }

      if(city == null || city == "") {
        $("#city").addClass("is-invalid");
      } else {
        $("#city").removeClass("is-invalid");
      }

      if(country == null || country == "") {
        $("#country").addClass("is-invalid");
      } else {
        $("#country").removeClass("is-invalid");
      }

      if(firstName == null || firstName == "") {
        $("#firstName").addClass("is-invalid");
      } else {
        $("#firstName").removeClass("is-invalid");
      }

      if(lastName == null || lastName == "") {
        $("#lastName").addClass("is-invalid");
      } else {
        $("#lastName").removeClass("is-invalid");
      }

      if(insNumber == null || insNumber == "") {
        $("#insNumber").addClass("is-invalid");
      } else {
        $("#insNumber").removeClass("is-invalid");
      }

      if(phone == null || phone == "") {
        $("#phone").addClass("is-invalid");
      } else {
        $("#phone").removeClass("is-invalid");
      }

      if(address == null || address == "") {
        $("#address").addClass("is-invalid");
      } else {
        $("#address").removeClass("is-invalid");
      }

      if(email != null && email != "" && pass != null && pass != "" && address != null && address != "" && 
        city != null && city != "" && country != "" && country != null && phone != null &&
        phone != "" && insNumber != null && insNumber != "" && firstName != null && firstName != "" &&
        lastName != null && lastName != "" && clinic != "" && clinic != null) {

        $.ajax({
          type : 'POST',
          url : "clinicAdminApi/addAdmin",
          headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
          data : JSON.stringify({
            "email" : email,
            "password" : pass,
            "address" : address,
            "phoneNumber": phone,
            "city": city,
            "country": country,
            "insuranceNumber": insNumber,
            "firstName": firstName,
            "lastName": lastName,
            "clinic": clinic
          }),
          contentType: "application/json; charset=utf-8",
            dataType: "json",
            statusCode: {
                200: function(responseObject, textStatus, jqXHR) {
                    console.log("usao");
                    showMessage("New clinical Admin successfully added!", "palegreen");
                    window.setTimeout(function(){location.reload()},1500);
                },
                400: function(responseObject, textStatus, errorThrown) {
                    showMessage("User with given email already exists!", "antiquewhite");
                },         
            }
        });

      } else {
        showMessage("All inputs are mandatory!", "antiquewhite");
      }
  });
});

function showMessage(message, color) {
  $("#message_bar").css("background", color);
  $("#message_bar").text(message);
  $("#message_bar").slideDown().delay(1500).slideUp();
}

function clinicAdminsAllOK(clinicAdminsList) {
  var table = $("#clinicAdminsBody");
  table.empty();

  
  $.each(clinicAdminsList, function(i, val) {
    var row = $("<tr class=\"clinic-table-text\" title=\"Click for more information\" id=\""+i+"\"></tr>");

    row.append("<td id=\""+val.id+"\"><p>" + val.name + "</p>" +
          "<p style=\"font-size: large\"><i>" + val.clinicName + "</i></td>");

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

function unauthorized(){
	document.write("<html><head></head><body>UNAUTHORIZED</body></html>");
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