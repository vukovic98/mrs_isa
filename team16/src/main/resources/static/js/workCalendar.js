$( document ).ready(function() {
    console.log( "ready!" );
    
    $.ajax ({
      type: 'GET',
      url: '/leaveRequestApi/findAllApprovedLeavesForUser',
	  headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
      statusCode: {
        200: function(responseObject, textStatus, jqXHR) {
          console.log("Calendar events - 200 OK");
          calendarEventsAllOK(responseObject);
        },
        204: function(responseObject, textStatus, jqXHR) {
          console.log("Calendar events - 204 No Content");
          calendarEventsNO(responseObject);
        },
		403: function(responseObject, textStatus, jqXHR) {
			console.log("403 Unauthorized");
			unauthorized();
		}
      }
    });
  
});

function calendarEventsAllOK(events) {
	console.log(events);
  var eventList = [];
  $.each(events, function(i, val) {
    var text = "Date from: " + val.dateFrom + ";\nDate to: " + val.dateTo;
	  var ev = {
		    start: val.dateFrom,
	        end: val.dateTo,
	        title: "Leave",
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

function unauthorized(){
	document.write("<html><head></head><body>UNAUTHORIZED</body></html>");
}