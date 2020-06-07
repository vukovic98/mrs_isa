$( document ).ready(function() {
    console.log( "ready!" );
    
    $.ajax ({
    	type: 'GET',
    	url: 'userApi/getRole',
    	headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
    	statusCode: {
    		200: function(responseObject, textStatus, jqXHR) {
    			console.log("ClinicalCenterAdmins - findAll() - 200 OK");
    			
    			if(responseObject == "MAIN_CLINICAL_CENTER_ADMINISTRATOR")
    				$("#addingAdmin").css('display', 'block');
    		},
    		204: function(responseObject, textStatus, jqXHR) {
    			console.log("ClinicalCenterAdmins - findAll() - 204 No Content");
    			showMessage("Something went wrong!", "antiquewhite");
    		},
    		403: function(responseObject, textStatus, jqXHR) {
    			console.log("403 Unauthorized");
    			unauthorized();
    		}
    	}
    });
    
    $.ajax ({
    	type: 'GET',
    	url: 'centerAdminApi/findAll',
    	headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
    	statusCode: {
    		200: function(responseObject, textStatus, jqXHR) {
    			console.log("ClinicalCenterAdmins - findAll() - 200 OK");
    			centerAdminsAllOK(responseObject);
    		},
    		204: function(responseObject, textStatus, jqXHR) {
    			console.log("ClinicalCenterAdmins - findAll() - 204 No Content");
    			centerAdminsAllNO(responseObject);
    		},
    		403: function(responseObject, textStatus, jqXHR) {
    			console.log("403 Unauthorized");
    			unauthorized();
    		}
    	}
    });
    
    $(document).on('click', '#addAdministrator', function () {
        $.ajax({
          type: 'GET',
          url: 'clincalCenterApi/findAll',
          headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
          statusCode: {
            200: function(responseObject, textStatus, jqXHR) {
              console.log("Clinical center - findAll() - 200 OK");
              $.each(responseObject, function(i, val) {
                  $('#clinicalCenters').append(`<option value="${val.name}">${val.name}</option>`); 
              });

            },
            204: function(responseObject, textStatus, jqXHR) {
              console.log("Clinical center - findAll() - 204 No Content");
            },
            403: function(responseObject, textStatus, jqXHR) {
              console.log("403 Unauthorized");
              unauthorized();
            }
          }
        });
    	$("#exampleModal").modal();
    });

    $(document).on('click', '#submitAdmin', function (e) {
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
      var clinicCenter = $("#clinicalCenters").val();
      
      console.log(email);
      console.log(clinicCenter);

      if(email == null || email == "") {
        $("#email").addClass("is-invalid");
      } else {
        $("#email").removeClass("is-invalid");
      }
      
      if(clinicCenter == null || clinicCenter == "") {
        $("#clinicalCenters").addClass("is-invalid");
      } else {
        $("#clinicalCenters").removeClass("is-invalid");
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
        lastName != null && lastName != "" && clinicCenter != "" && clinicCenter != null) {

        $.ajax({
          type : 'POST',
          url : "centerAdminApi/addAdmin",
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
            "clinicCenter": clinicCenter
          }),
          contentType: "application/json; charset=utf-8",
            dataType: "json",
            statusCode: {
                200: function(responseObject, textStatus, jqXHR) {
                    console.log("usao");
                    showMessage("New clinical center admin successfully added!", "palegreen");
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

function unauthorized(){
	document.write("<html><head></head><body>UNAUTHORIZED</body></html>");
}

function centerAdminsAllOK(clinicAdminsList) {
  var table = $("#centerAdminsTableBody");
  table.empty();

  
  $.each(clinicAdminsList, function(i, val) {
    var row = $("<tr class=\"clinic-table-text\" id=\""+i+"\"></tr>");

    row.append("<td id=\""+val.email+"\"><p>" + val.firstName + " " + val.lastName + "</p>" +
          "<p style=\"font-size: large\"><i>" + val.clinicCenter + "</i></td>");

    table.append(row);
  });
}

function centerAdminsAllNO(responseObject) {
  var table = $("#centerAdminsTableBody");
  table.empty();
  
  var row = $("<tr></tr>");
  row.append("<td class='pl-1'>There are no clinical center admins in system.</td>");
  
  table.append(row);
}