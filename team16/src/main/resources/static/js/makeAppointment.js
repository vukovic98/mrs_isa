
$( document ).ready(function() {
console.log( "ready!" );
    var now = currentDate();
    $("#date").attr("min",now);
    $.ajax ({
    	type: 'GET',
    	url: '/pricelistItemApi/findAllAppointmentTypes',
	    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
    	statusCode: {
    		200: function(responseObject, textStatus, jqXHR) {
    			console.log("200 OK");
    			loadAppointmentTypesAllOK(responseObject);
    		},
    		204: function(responseObject, textStatus, jqXHR) {
    			console.log("204 No Content");
	
    		},
			403: function(responseObject, textStatus, jqXHR) {
				console.log("403 Unauthorized");
				unauthorized();
			}
    	}
    });
    
    $.ajax ({
    	type: 'GET',
    	url: '/clinicApi/findAll',
	    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
    	statusCode: {
    		200: function(responseObject, textStatus, jqXHR) {
    			console.log("200 OK");
    			loadLocationsAllOK(responseObject);
    		},
    		204: function(responseObject, textStatus, jqXHR) {
    			console.log("204 No Content");

    			
    		},
			403: function(responseObject, textStatus, jqXHR) {
				console.log("403 Unauthorized");
				unauthorized();
			}
    	}
    });
    
    $(document).on('click', '.selectClinicBtn', function () {
    	var btnID = $(this).attr('id');
    	sessionStorage.setItem("appParam", btnID);
    	console.log("prvo " + btnID);
    	sessionStorage.setItem("filter", "ne");
    	window.location.href = "/clinicPage";
    });
    
    $(document).on('click', '.cLinicPageButton', function () {
    	var clinicID = $(this).attr('id');
    	sessionStorage.setItem("appParam",clinicID);
    	sessionStorage.setItem("filter", "da");
    	window.location.href = "/clinicPage";
    });
});

function loadLocationsAllOK(clinics){
	//tabela klinika
	/*var clinicsTable = $("#clinics");
	if(clinics != null){
		clinicsTable.bootstrapTable(
			  {
				  data: clinics,
				  columns: [{
				    field: 'name',
				    title: 'Name',
				    sortable: true
				  }, {
				    field: 'address',
				    title: 'Address',
				    sortable: true
				  }]
				}
	  
	  );}*/
	var clinicsHead = $("#clinicsHead");
	clinicsHead.empty();
	var row = $("<tr></tr>");
	row.append("<th>Clinics name</th>");
	row.append("<th>Address</th>");
	row.append("<th>Average grade</th>");
	row.append("<th>&nbsp</th>");

	clinicsHead.append(row);
	
	var clinicsBody = $("#clinicsBody");
	clinicsBody.empty();
	
	$.each(clinics,function(i,val){
		var row = $("<tr id=\""+i+"\"></tr>");
		row.append("<td >" + val.name + "</td>");
		row.append("<td >" + val.address + ", " + val.city + "</td>");
		row.append("<td >" + val.averageGrade + "</td>");
		
		var btn = "<td class=\"text-center\" ><button id=\"" + val.clinicID + "\" type=\"button\" class=\"btn btn-primary cLinicPageButton\">Select clinic</button></td>";
		row.append(btn);
		clinicsBody.append(row);	
	  });
	
	
	var select = $("#locations");
	  select.empty();
	  var klinike = [];
	  select.append("	<option disabled selected>Select location...</option>");
	  $.each(clinics,function(i,val){
		 if (!klinike.includes(val.city.toUpperCase())) {
			 klinike.push(val.city.toUpperCase())
		 } 
	  });
	  $.each(klinike, function(i, val) {
		  
		  var option = $("<option>"+val+"</option>");
		   select.append(option);
		  });
	
}

function loadAppointmentTypesAllOK(pricelistItems){
	 var select = $("#appointmentTypes");
	  select.empty();
	 select.append("<option disabled selected>Select appointment type...</option>");
	 $.each(pricelistItems, function(i, val) {
		   if(val.appointmentType != "SURGERY"){
		    var option = $("<option>"+val.appointmentType+"</option>");
		    select.append(option);
		   }
		  });
}



$("#searchButton").click(function(){

	popuniTabelu();
	
	
});
function popuniTabelu(){
	var appType = $("#appointmentTypes").val();
	var avgGrade = $("#avgGrade").val();
	var dateControl = document.querySelector('input[type="date"]');
	console.log("datum: "+dateControl.value);
	var location =  $("#locations").val(); 
	if (appType == null || appType == "" ){
		$("#appointmentTypes").addClass("is-invalid");
	}
	else
		{
		$("#appointmentTypes").removeClass("is-invalid");
		}
	if (dateControl.value== null || dateControl.value == "" ){
		$("#date").addClass("is-invalid");
	}
	else
		{
		$("#date").removeClass("is-invalid");
		}
	if(appType != null && appType!="" && dateControl.value!=null && dateControl.value != ""){
		$.ajax ({
	    	type: 'GET',
	    	url: '/clinicApi/findAppointments/'+appType+"&"+dateControl.value+"&"+avgGrade+"&"+location,
	    	headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
	    	statusCode: {
	    		200: function(responseObject, textStatus, jqXHR) {
	    			console.log("200 OK "+responseObject);
	    			sessionStorage.setItem('appDate', dateControl.value);
	    			loadAvailableClinicsAllOK(responseObject);
	    		},
	    		204: function(responseObject, textStatus, jqXHR) {
	    			console.log("204 No Content");
	    			loadAvailableClinicsNO();
	    		},
	    		403: function(responseObject, textStatus, jqXHR) {
	    			console.log("403 Unauthorized");
	    			unauthorized();
	    		}
	    	}
	    });
		
	
	}
	console.log(avgGrade);
	console.log(location);
	}
function loadAvailableClinicsAllOK(clinics){

	var clinicsHead = $("#clinicsHead");
	clinicsHead.empty();
	var row = $("<tr></tr>");
	row.append("<th>Clinics name</th>");
	row.append("<th>Address</th>");
	row.append("<th>Average grade</th>");
	row.append("<th>Appointment type</th>");
	row.append("<th>Price</th>");
	row.append("<th>&nbsp</th>");

	clinicsHead.append(row);
	
	
	var table = $("#clinicsBody");
	table.empty();
	if(clinics.length != 0){
		$.each(clinics,function(i,val){
			var row = $("<tr id=\""+val.clinicID+"\"></tr>");
			row.append("<td>"+val.name+"</td>");
			row.append("<td>"+val.address+ ", " + val.city+"</td>");
			row.append("<td>"+val.averageGrade+"</td>");
			row.append("<td>"+val.appointmentType+"</td>");
			row.append("<td>"+val.price+"$</td>");
			var date = sessionStorage.getItem('appDate');
			var id = val.clinicID + "&" + val.appointmentType + "&" + date;
			row.append("<td class=\"text-center\"><button type=\"button\" class=\"btn btn-primary selectClinicBtn\" id=\"" + id + "\" >Select clinic</button></td>");
			table.append(row);
			//sessionStorage.setItem('appDate', "");
		});
	}
	else{
		var row = $("<tr></tr>");

		row.append("<td class=\"w-50 text-center\" colspan=\"6\"> There are no available clinics.</td>");
		table.append(row);	
	}
}
function currentDate() {
    var d = new Date(),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) 
        month = '0' + month;
    if (day.length < 2) 
        day = '0' + day;

    return [year, month, day].join('-');
}
function loadAvailableClinicsNO(){
	var table = $("#clinicsBody");
	table.empty();
	var row = $("<tr></tr>");
	row.append("<td class=\"text-center\" colspan=\"5\"> Could not load clinics.</td>");
	table.append(row);	
}
function unauthorized(){
	document.write("<html><head></head><body>UNAUTHORIZED</body></html>");

}