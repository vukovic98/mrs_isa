$( document ).ready(function() {
    console.log( "ready!" );
    
    $("#btnSubmit").click(function(e) {

        var pass = $("#password").val();
        var repeat = $("#repeatPassword").val();

          if(pass == null || pass == "") {
            $("#password").addClass("is-invalid");
          } else {
            $("#password").removeClass("is-invalid");
          }

          if(repeat == null || repeat == "") {
            $("#repeatPassword").addClass("is-invalid");
          } else {
            $("#repeatPassword").removeClass("is-invalid");
          }

          if(repeat != null && repeat != "" && pass != null && pass != "") {
            if(pass != repeat){
              $("#repeatPassword").addClass("is-invalid");
              Swal.fire({
        		  position: 'center',
        		  icon: 'error',
        		  title: "Passwords don't match",
        		  showConfirmButton: false,
        		  timer: 1500
        		})
            }
            else{
              $("#repeatPassword").removeClass("is-invalid");
            }
          } else {
            $("#repeatPassword").addClass("is-invalid");
          }

          if(pass != null && pass != "" && repeat != null && repeat != "" && pass == repeat) {
             $.ajax({
            type: 'PUT',
            url: 'clinicAdminApi/changePassword',
            headers: { "Authorization": 'Bearer ' + sessionStorage.getItem('token') },
            data: JSON.stringify(pass),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            statusCode: {
              200: function(responseObject, textStatus, jqXHR) {
                console.log("ChangePassword - 200 OK");
                window.location.href = "/";
              },
              204: function(responseObject, textStatus, jqXHR) {
                console.log("ChangePassword - 204 No Content");
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
          }
       
    });
});

function unauthorized(){
  document.write("<html><head></head><body>UNAUTHORIZED</body></html>");
}