$(document).ready(function(){
	
	console.log("ready");

	  $.ajax({
	    type: 'GET',
	    url: 'pricelistItemApi/findAllAppointmentTypes',
	    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
	    statusCode: {
	      200: function(responseObject, textStatus, jqXHR) {
	        console.log("Pricelist - findAll() - 200 OK");
	        pricelistAllOK(responseObject);
	      },
	      204: function(responseObject, textStatus, jqXHR) {
	        console.log("Pricelist - findAll() - 204 No Content");
	        pricelistAllNO(responseObject);
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
	            '<td><input type="text" class="form-control" name="name" id="type"></td>' +
	            '<td><input type="text" class="form-control" name="price" id="price"></td>' +
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
	          var dataPrice = [];
	          input.each(function(){
	            dataPrice.push($(this).val());
	          });     
	          $.ajax({
	            type: 'POST',
	            url: 'pricelistItemApi/addPricelistItem',
	            headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
	            data : JSON.stringify({
	              "name" : dataPrice[0],
	              "price" : dataPrice[1]
	            }),
	            contentType: "application/json; charset=utf-8",
	            dataType: "json",
	            statusCode: {
	              200: function(responseObject, textStatus, jqXHR) {
	                console.log("PricelistItem - add() - 200 OK");
	                Swal.fire({
	          		  position: 'center',
	          		  icon: 'success',
	          		  title: 'Pricelist item successfully added!',
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
	                console.log("PricelistItem - add() - 400 Bad request");
	                Swal.fire({
	          		  position: 'center',
	          		  icon: 'error',
	          		  title: 'Pricelist item with inserted name already exist!',
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
		var id = $(this).parents("td").attr('id');
		
        
        $.ajax({
            type: 'DELETE',
            url: 'pricelistItemApi/deletePricelistItem/' + id,
            headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            statusCode: {
              200: function(responseObject, textStatus, jqXHR) {
                console.log("Pricelist item - delete() - 200 OK");
                Swal.fire({
          		  position: 'center',
          		  icon: 'success',
          		  title: 'Pricelist item successfully deleted!',
          		  showConfirmButton: false,
          		  timer: 1500
          		})
              },
              400: function(responseObject, textStatus, jqXHR) {
                console.log("Pricelist item - delete() - 400 Bad request");
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


function pricelistAllOK(pricelistItemList) {
	  var table = $("#pricelistBody");
	  table.empty();

	  console.log(pricelistItemList);
	  $.each(pricelistItemList, function(i, val) {
	    var row = $("<tr id=\""+i+"\"></tr>");
	    row.append("<td class=\"appointmentType\" id=\""+val.id+"\">" + val.appointmentType + "</td>");
	    row.append("<td class=\"price\" id=\""+val.id+"\">" + val.price + "$</td>");
	    row.append("<td id=\""+val.id+"\">" + "<a class=\"add\" title=\"Add\" data-toggle=\"tooltip\"><i class=\"material-icons\">&#xE03B;</i></a>" +
                "<a class=\"delete\" title=\"Delete\" data-toggle=\"tooltip\"><i class=\"material-icons\">&#xE872;</i></a>" + "</td>");
table.append(row);

	  });
}

	function pricelistAllNO(responseObject) {
	  var table = $("#pricelistBody");
	  table.empty();
	  
	  var row = $("<tr></tr>");
	  row.append("<td class='pl-1'>There are no pricelist items in system.</td>");
	  
	  table.append(row);
	}
	function unauthorized(){
		document.write("<html><head></head><body>UNAUTHORIZED</body></html>");
	}

function pricelistFunction() {
	  // Declare variables
	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("pricelistInput");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("pricelistTable");
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