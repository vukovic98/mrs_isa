const loginBtn = document.getElementById('login');
const signupBtn = document.getElementById('signup');


$(document).ready(function(e) {
	$("#loginBtn").click(function(e) {
		e.preventDefault();
		
		var email = $("#emailInput").val();
		var pass = $("#passInput").val();
		
		if(email != '' && pass != '') {
			$.ajax({
				type : 'POST',
				url : "userApi/validateUser",
				data : JSON.stringify({
					"email" : email,
					"password" : pass
				}),
				contentType: "application/json; charset=utf-8",
			    dataType: "json",
			    statusCode: {
			        200: function(responseObject, textStatus, jqXHR) {
			            console.log("usao");
			            whereToGo(responseObject);
			        },
			        400: function(responseObject, textStatus, errorThrown) {
			            concole.log("ERROR");
			        }           
			    }
			});
		}
	});
	
	$("#login").click(function (e){
		let parent = e.target.parentNode.parentNode;
		Array.from(e.target.parentNode.parentNode.classList).find((element) => {
			if(element !== "slide-up") {
				parent.classList.add('slide-up')
			}else{
				signupBtn.parentNode.classList.add('slide-up')
				parent.classList.remove('slide-up')
			}
		});
	});

	$("#signup").click(function (e){
		let parent = e.target.parentNode;
		Array.from(e.target.parentNode.classList).find((element) => {
			if(element !== "slide-up") {
				parent.classList.add('slide-up')
			}else{
				loginBtn.parentNode.parentNode.classList.add('slide-up')
				parent.classList.remove('slide-up')
			}
		});
	});
});

function whereToGo(user) {
		if(user.email == "a@a") {
			console.log("USAO");
			window.location.href = "/clinicalCenterAdmin";
		}
	
}


