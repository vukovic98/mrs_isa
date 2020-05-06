$(document).ready(function(){
	
	console.log("ready");

	  $.ajax({
	    type: 'GET',
	    url: 'appointmentApi/findAll',
	    statusCode: {
	      200: function(responseObject, textStatus, jqXHR) {
	        console.log("Appointments - findAll() - 200 OK");
	        appointmentsAllOK(responseObject);
	      },
	      204: function(responseObject, textStatus, jqXHR) {
	        console.log("Appointments - findAll() - 204 No Content");
	        appointmentsAllNO(responseObject);
	      }
	    }
	  });
	
	
	$('[data-toggle="tooltip"]').tooltip();
	var actions = $("table td:last-child").html();
	// Append table with add row form on add new button click
    $(".add-new").click(function(){
		$(this).attr("disabled", "disabled");
		var index = $("table tbody tr:last-child").index();
        var row = '<tr>' +
            '<td><input type="datetime-local" class="form-control"></td>' +
            '<td><select class="form-control"><option value="Basic Examination">Basic Examination</option><option value="Biopsy">Biopsy</option></td>' +
			'<td><select class="form-control"><option value="101">101</option><option value="102">102</option></td>' +
			'<td><select class="form-control"><option value="John Doe">John Doe</option><option value="Doctor House">Doctor House</option></td>' +
			'<td><select class="form-control"><option value="25 min.">25 min.</option><option value="35 min.">35 min.</option></td>' +
			'<td>' + actions + '</td>' +
        '</tr>';
    	$("table").append(row);		
		$("table tbody tr").eq(index + 1).find(".add, .edit").toggle();
        $('[data-toggle="tooltip"]').tooltip();
    });
	// Add row on add button click
	$(document).on("click", ".add", function(){
		var empty = false;
		var input = $(this).parents("tr").find('input');
		var select = $(this).parents("tr").find('select');
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
			select.each(function(){
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


function appointmentsAllOK(appointmentsList) {
	  var table = $("#appointmentsBody");
	  table.empty();

	  console.log(appointmentsList);
	  $.each(appointmentsList, function(i, val) {
	    var row = $("<tr id=\""+i+"\"></tr>");
	    if(val.pricelistItems != undefined) {
	    row.append("<td id=\""+val.id+"\">" + val.dateTime + "</td>");
	    row.append("<td id=\""+val.id+"\">" + val.pricelistItems.name + "</td>");
	    row.append("<td id=\""+val.id+"\">" + val.ordination.number + "</td>");
	    row.append("<td id=\""+val.id+"\">" + val.patient.name + "</td>");
	    row.append("<td id=\""+val.id+"\">" + val.duration + " min</td>");
	    row.append("<td id=\""+val.id+"\">" + val.pricelistItems.price + "$</td>");
	    table.append(row);
	    }
	  });
	}

	function appointmentsAllNO(responseObject) {
	  var table = $("#appointmentsBody");
	  table.empty();
	  
	  var row = $("<tr></tr>");
	  row.append("<td class='pl-1'>There are no predefined appointments in system.</td>");
	  
	  table.append(row);
	}


function modalExamShow(){
$("#examModal").modal();
}