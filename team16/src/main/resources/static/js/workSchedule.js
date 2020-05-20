$( document ).ready(function() {
    console.log( "ready!" );
    
    $.ajax ({
      type: 'GET',
      url: '/appointmentApi/findAllAppointments',
      statusCode: {
        200: function(responseObject, textStatus, jqXHR) {
          console.log("Calendar events - 200 OK");
          calendarEventsAllOK(responseObject);
        },
        204: function(responseObject, textStatus, jqXHR) {
          console.log("Calendar events - 204 No Content");
          calendarEventsNO(responseObject);
        }
      }
    });
  
});

function calendarEventsAllOK(events) {
	console.log(events);
  var eventList = [];
  $.each(events, function(i, val) {
    var text = "Patient: " + val.patient + ";\nStart: " + val.date.substring(10, 16) + "h;\nDate: " + val.date.substring(0, 10)
    + ";\nType: " + val.appointmentType;
	  var ev = {
		      start: val.date,
	        end: val.date,
	        title: val.appointmentType,
	        color: '#eee',
	        data: {data: text}
	  }
	  eventList.push(ev);
  });
  console.log(eventList);
   $('.event-calendar').equinox({
      events: eventList,
    });
 
}

$(document).on('click', '.modalCalendar', function () {

        var data = $(this).attr('title');
        console.log("UDJE MODAL");
        
        var list = data.split(";");

        var patient = list[0].split(":")[1];
        var time = list[1].split(":")[1] + ":" + list[1].split(":")[2];
        var date = list[2].split(":")[1];
        var type = list[3].split(":")[1];


        $("#modalType").text(type);
        $("#modalPatient").text(patient);
        $("#modalDate").text(date);
        $("#modalTime").text(time);

        $("#exampleModal").modal();

    });