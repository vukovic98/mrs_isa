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

   $(document).on('click', '#addClinic', function () {

      $.ajax({
        type: 'GET',
        url: 'pricelistApi/getNames',
        headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
        statusCode: {
          200: function(responseObject, textStatus, jqXHR) {
            console.log("Pricelists - getNames() - 200 OK");
            
            $.each(responseObject, function(i, val) {
              $('#clinicPricelist').append(`<option value="${val}">${val}</option>`); 
            });

          },
          204: function(responseObject, textStatus, jqXHR) {
            console.log("Pricelists - getNames() - 204 No Content");
          },
          403: function(responseObject, textStatus, jqXHR) {
            console.log("403 Unauthorized");
            unauthorized();
          }
        }
      });

      $("#exampleModal").modal();
   });

   $(document).on('click', '#submitClinic', function () {
      var name = $("#clinicName").val();
      var address = $("#clinicAddress").val();
      var desc = $("#clinicDescription").val();
      var pricelist = $("#clinicPricelist").val();
      var city = $("#clinicCity").val();

      if(name == null || name == "") {
        $("#clinicName").addClass("is-invalid");
      } else {
        $("#clinicName").removeClass("is-invalid");
      }

      if(address == null || address == "") {
        $("#clinicAddress").addClass("is-invalid");
      } else {
        $("#clinicAddress").removeClass("is-invalid");
      }
      
      if(city == null || city == "") {
          $("#clinicCity").addClass("is-invalid");
        } else {
          $("#clinicCity").removeClass("is-invalid");
        }

      if(desc == null) {
        desc = "";
      }

      if(pricelist == null || pricelist == "") {
        $("#clinicPricelist").addClass("is-invalid");
      } else {
        $("#clinicPricelist").removeClass("is-invalid");
      }

      if(name != null && name != "" && address != "" && address != null &&
    		  city != null && city != "" && desc != null && pricelist != null &&
        pricelist != "") {
        $.ajax({
          type : 'POST',
          url : "clinicApi/addClinic",
          headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
          data : JSON.stringify({
            "name": name,
            "address": address,
            "description": desc,
            "pricelist": pricelist,
            "city": city
          }),
          contentType: "application/json; charset=utf-8",
            dataType: "json",
            statusCode: {
                200: function(responseObject, textStatus, jqXHR) {
                    console.log("usao");
                    Swal.fire({
		        		  position: 'center',
		        		  icon: 'success',
		        		  title: 'New clinic successfully added!',
		        		  showConfirmButton: false,
		        		  timer: 1500
		        		})
                    window.setTimeout(function(){location.reload()},1500);
                },
                400: function(responseObject, textStatus, errorThrown) {
                	Swal.fire({
		        		  position: 'center',
		        		  icon: 'error',
		        		  title: 'Clinic with inserted name already exists!',
		        		  showConfirmButton: false,
		        		  timer: 1500
		        		})
		        		window.setTimeout(function(){location.reload()},1500);
                },         
            }
        });

      } else {
    	  Swal.fire({
    		  position: 'center',
    		  icon: 'error',
    		  title: 'All inputs are mandatory!',
    		  showConfirmButton: false,
    		  timer: 1500
    		})
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