$( document ).ready(function() {
  console.log("ready");
  var address = "Masarikova 45, Sabac";
  console.log($("#clinicAddress").val());
  
  $.ajax({
	    type: 'POST',
	    url: 'clinicApi/createMap/' + address,
	    headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
	    statusCode: {
	      200: function(responseObject, textStatus, jqXHR) {
	        console.log("Clinics - map() - 200 OK");
	        //console.log(responseObject);
	        var obj = JSON.parse(responseObject);
	       // console.log(obj.results);
	        var latV = obj.results[0].geometry.location.lat;
	        var lonV = obj.results[0].geometry.location.lng;
	        
	        //console.log(latV + " " + lonV);
	        
	        initMap(latV, lonV);
	      },
	      204: function(responseObject, textStatus, jqXHR) {
	        console.log("Clinics - map() - 204 No Content");
	        clinicsAllNO(responseObject);
	      },
			403: function(responseObject, textStatus, jqXHR) {
				console.log("403 Unauthorized");
				unauthorized();
			}
	    }
	  });


});

function initMap(latV, lon) {
    var myLatLng = {lat: latV, lng: lon};

    var map = new google.maps.Map(document.getElementById('map'), {
      zoom: 15,
      center: myLatLng,
      disableDefaultUI: true
    });

    var marker = new google.maps.Marker({
      position: myLatLng,
      map: map,
      title: 'Hello World!'
    });
  }