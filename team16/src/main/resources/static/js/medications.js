$(document).ready(function(){
	
	console.log("ready");

  $.ajax({
    type: 'GET',
    url: 'medicationApi/findAll',
    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
    statusCode: {
      200: function(responseObject, textStatus, jqXHR) {
        console.log("Medications - findAll() - 200 OK");
        medicationsAllOK(responseObject);
      },
      204: function(responseObject, textStatus, jqXHR) {
        console.log("Medications - findAll() - 204 No Content");
        medicationsAllNO(responseObject);
      },
		403: function(responseObject, textStatus, jqXHR) {
			console.log("403 Unauthorized");
			unauthorized();
		}
    }
  });

  

  var actions = $("table td:last-child").html();
  // Append table with add row form on add new button click
    $(".add-new").click(function(){
      var actions = $("table td:last-child").html();
    $(this).attr("disabled", "disabled");
    var index = $("table tbody tr:nth-child(0)").index();
        var row = '<tr>' +
            '<td><input type="text" class="form-control" name="name" id="name"></td>' +
            '<td><input type="text" class="form-control" name="code" id="code"></td>' +
              '<td>' + actions + '</td>' +
        '</tr>';
      $("table").prepend(row);    
    $("table tbody tr").eq(index + 1).find(".add").toggle();
    });
  // Add row on add button click
  $(document).on("click", ".add", function(){
    var empty = false;
    var input = $(this).parents("tr").find('input[type="text"]');
        input.each(function(){
      if(!$(this).val()){
        $(this).addClass("error");
        empty = true;
      } else{
                $(this).removeClass("error");
            }
    });
    $(this).parents("tr").find(".error").first().focus();
    if(!empty){
      var dataMed = [];
      input.each(function(){
        dataMed.push($(this).val());
      });     
      $.ajax({
        type: 'POST',
        url: 'medicationApi/addMedication',
        headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
        data : JSON.stringify({
          "name" : dataMed[0],
          "code" : dataMed[1]
        }),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        statusCode: {
          200: function(responseObject, textStatus, jqXHR) {
            console.log("Medications - add() - 200 OK");
            Swal.fire({
      		  position: 'center',
      		  icon: 'success',
      		  title: 'Medication successfully added!',
      		  showConfirmButton: false,
      		  timer: 1500
      		})
            input.each(function(){
              $(this).parent("td").html($(this).val());
            }); 
            $(this).parents("tr").find(".add").toggle();
            $(".add-new").removeAttr("disabled");
            
          },
          400: function(responseObject, textStatus, jqXHR) {
            console.log("Medications - add() - 400 Bad request");
            Swal.fire({
      		  position: 'center',
      		  icon: 'error',
      		  title: 'Medication with inserted code or name already exists!',
      		  showConfirmButton: false,
      		  timer: 1500
      		})
          },
		  403: function(responseObject, textStatus, jqXHR) {
			console.log("403 Unauthorized");
			unauthorized();
		  }
        }
      });
    }   
    });
  
  
  // Delete row on delete button click
  $(document).on("click", ".delete", function(){
        var code = $(this).parents("td").attr('id');
        
        $.ajax({
            type: 'DELETE',
            url: 'medicationApi/deleteMedication/' + code,
            headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            statusCode: {
              200: function(responseObject, textStatus, jqXHR) {
                console.log("Medications - delete() - 200 OK");
                Swal.fire({
          		  position: 'center',
          		  icon: 'success',
          		  title: 'Medication successfully deleted!',
          		  showConfirmButton: false,
          		  timer: 1500
          		})
              },
              400: function(responseObject, textStatus, jqXHR) {
                console.log("Medications - delete() - 400 Bad request");
                Swal.fire({
          		  position: 'center',
          		  icon: 'error',
          		  title: 'Something went wrong!',
          		  showConfirmButton: false,
          		  timer: 1500
          		})
              },
      		403: function(responseObject, textStatus, jqXHR) {
    			console.log("403 Unauthorized");
    			unauthorized();
    		}
            }
          });
        
        $(this).parents("tr").remove();
        $(".add-new").removeAttr("disabled");
    });
});

function medicationsAllOK(medicationsList) {
  var table = $("#medicationsBody");
  table.empty();

  
  $.each(medicationsList, function(i, val) {
    var row = $("<tr id=\""+val.code+"\"></tr>");

    row.append("<td class=\"name\" id=\""+val.id+"\">" + val.name + "</td>");
    row.append("<td class=\"code\" id=\""+val.id+"\">" + val.code + "</td>");
    row.append("<td id=\""+val.code+"\">" + "<a class=\"add\" title=\"Add\" data-toggle=\"tooltip\"><i class=\"material-icons\">&#xE03B;</i></a>" +
                              "<a class=\"delete\" title=\"Delete\" data-toggle=\"tooltip\"><i class=\"material-icons\">&#xE872;</i></a>" + "</td>");
    table.append(row);
  });
}

function medicationsAllNO(responseObject) {
  var table = $("#medicationsBody");
  table.empty();
  
  var row = $("<tr></tr>");
  row.append("<td class='pl-1'>There are no medications in system.</td>");
  
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