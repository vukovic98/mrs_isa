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
		$(this).attr("disabled", "disabled");
		var index = $("table tbody tr:nth-child(0)").index();
        var row = '<tr>' +
            '<td><input type="text" class="form-control" name="name" id="name"></td>' +
            '<td><input type="text" class="form-control" name="code" id="code"></td>' +
			'<td>' + actions + '</td>' +
        '</tr>';
    	$("table").prepend(row);		
		$("table tbody tr").eq(index + 1).find(".add, .edit").toggle();
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
			input.each(function(){
				$(this).parent("td").html($(this).val());
			});			
			$(this).parents("tr").find(".add, .edit").toggle();
			$(".add-new").removeAttr("disabled");
		}		
    });
	// Edit row on edit button click
	$(document).on("click", ".edit", function(){		
        $(this).parents("tr").find("td:not(:last-child)").each(function(){
			$(this).html('<input type="text" class="form-control" value="' + $(this).text() + '">');
		});		
		$(this).parents("tr").find(".add, .edit").toggle();
		$(".add-new").attr("disabled", "disabled");
    });
	// Delete row on delete button click
	$(document).on("click", ".delete", function(){
        $(this).parents("tr").remove();
		$(".add-new").removeAttr("disabled");
    });
});

function medicationsAllOK(medicationsList) {
  var table = $("#medicationsBody");
  table.empty();

  
  $.each(medicationsList, function(i, val) {
    var row = $("<tr id=\""+i+"\"></tr>");

    row.append("<td id=\""+val.id+"\">" + val.name + "</td>");
    row.append("<td id=\""+val.id+"\">" + val.code + "</td>");
    row.append("<td id=\""+val.id+"\">" + "<a class=\"add\" title=\"Add\" data-toggle=\"tooltip\"><i class=\"material-icons\">&#xE03B;</i></a>" +
	                            "<a class=\"edit\" title=\"Edit\" data-toggle=\"tooltip\"><i class=\"material-icons\">&#xE254;</i></a>" +
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