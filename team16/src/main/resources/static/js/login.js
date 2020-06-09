const loginBtn = document.getElementById('login');
const signupBtn = document.getElementById('signup');
const doc = document;

$(document).ready(function(e) {
	sessionStorage.clear();
	
	$("#loginBtn").click(function(e) {
		e.preventDefault();
		
		var email = $("#emailInput").val();
		var pass = $("#passInput").val();

		if(email == null || email == "") {
			$("#emailInput").addClass("is-invalid");
		} else {
			$("#emailInput").removeClass("is-invalid");
		}

		if(pass == null || pass == "") {
			$("#passInput").addClass("is-invalid");
		} else {
			$("#passInput").removeClass("is-invalid");
		}
		
		if(email != '' && pass != '' && email != null && pass != null) {
			console.log("Udje");
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
			        204: function(responseObject, textStatus, errorThrown) {
			        	Swal.fire({
			        		  position: 'center',
			        		  icon: 'error',
			        		  title: 'There is no user with these credentials!',
			        		  showConfirmButton: false,
			        		  timer: 1500
			        		})
			        },
			        403: function(responseObject, textStatus, errorThrown) {
			        	Swal.fire({
			        		  position: 'center',
			        		  icon: 'error',
			        		  title: 'There is no user with these credentials!',
			        		  showConfirmButton: false,
			        		  timer: 1500
			        		})
			        }           
			    }
			});
		}
	});

	$("#signUpBtn").click(function(e) {
		e.preventDefault();
		
		var email = $("#emailSignUp").val();
		var pass = $("#passSignUp").val();
		var repeatPass = $("#passRepeatSignUp").val();
		var city = $("#citySignUp").val();
		var country = $("#countrySignUp").val();
		var firstName = $("#firstNameSignUp").val();
		var lastName = $("#lastNameSignUp").val();
		var insNumber = $("#insNumberSignUp").val();
		var phone = $("#phoneSignUp").val();
		var address = $("#addressSignUp").val();
		console.log(email);

		if(email == null || email == "") {
			$("#emailSignUp").addClass("is-invalid");
		} else {
			$("#emailSignUp").removeClass("is-invalid");
		}

		if(pass == null || pass == "") {
			$("#passSignUp").addClass("is-invalid");
		} else {
			$("#passSignUp").removeClass("is-invalid");
		}

		if(city == null || city == "") {
			$("#citySignUp").addClass("is-invalid");
		} else {
			$("#citySignUp").removeClass("is-invalid");
		}

		if(country == null || country == "") {
			$("#countrySignUp").addClass("is-invalid");
		} else {
			$("#countrySignUp").removeClass("is-invalid");
		}

		if(firstName == null || firstName == "") {
			$("#firstNameSignUp").addClass("is-invalid");
		} else {
			$("#firstNameSignUp").removeClass("is-invalid");
		}

		if(lastName == null || lastName == "") {
			$("#lastNameSignUp").addClass("is-invalid");
		} else {
			$("#lastNameSignUp").removeClass("is-invalid");
		}

		if(insNumber == null || insNumber == "") {
			$("#insNumberSignUp").addClass("is-invalid");
		} else {
			$("#insNumberSignUp").removeClass("is-invalid");
		}

		if(phone == null || phone == "") {
			$("#phoneSignUp").addClass("is-invalid");
		} else {
			$("#phoneSignUp").removeClass("is-invalid");
		}

		if(address == null || address == "") {
			$("#addressSignUp").addClass("is-invalid");
		} else {
			$("#addressSignUp").removeClass("is-invalid");
		}

		if(repeatPass == null || repeatPass == "") {
			$("#passRepeatSignUp").addClass("is-invalid");
		} else {
			$("#passRepeatSignUp").removeClass("is-invalid");
		}

		if(repeatPass != null && repeatPass != "" && pass != null && pass != "") {
			if(pass != repeatPass){
				$("#passRepeatSignUp").addClass("is-invalid");
				Swal.fire({
	        		  position: 'center',
	        		  icon: 'error',
	        		  title: "Passwords don't match!",
	        		  showConfirmButton: false,
	        		  timer: 1500
	        		})
			}
			else{
				$("#passRepeatSignUp").removeClass("is-invalid");
			}
		} else {
			$("#passRepeatSignUp").addClass("is-invalid");
		}

		if(email != null && email != "" && repeatPass != null && repeatPass != "" && pass != null && pass != "" &&
			address != null && address != "" && city != null && city != "" && country != "" && country != null && phone != null &&
			phone != "" && insNumber != null && insNumber != "" && firstName != null && firstName != "" &&
			lastName != null && lastName != "") {

			$.ajax({
				type : 'POST',
				url : "patientApi/signUpUser",
				data : JSON.stringify({
					"email" : email,
					"password" : pass,
					"address" : address,
					"phoneNumber": phone,
					"city": city,
					"country": country,
					"insuranceNumber": insNumber,
					"firstName": firstName,
					"lastName": lastName
				}),
				contentType: "application/json; charset=utf-8",
			    dataType: "json",
			    statusCode: {
			        200: function(responseObject, textStatus, jqXHR) {
			            console.log("usao");
			            Swal.fire({
			        		  position: 'center',
			        		  icon: 'success',
			        		  title: 'Registration request successfully submited!',
			        		  showConfirmButton: false,
			        		  timer: 1500
			        		})
			        },
			        400: function(responseObject, textStatus, errorThrown) {
			            Swal.fire({
			        		  position: 'center',
			        		  icon: 'error',
			        		  title: 'User with given email already exists!',
			        		  showConfirmButton: false,
			        		  timer: 1500
			        		})
			        },         
			    }
			});

		} else {
			Swal.fire({
      		  position: 'center',
      		  icon: 'error',
      		  title: 'All inputs are mandatory!',
      		  showConfirmButton: false,
      		  timer: 1500
      		})
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
	var jwt = user.jwt;
	sessionStorage.setItem('token', jwt);
		if(user.role == "USER") {
			console.log("USAO");
			window.location.href = "/clinicalCenterAdminInitial";
		}
	
		if(user.role == "CLINICAL_CENTER_ADMINISTRATOR" || user.role == "MAIN_CLINICAL_CENTER_ADMINISTRATOR") {
			console.log("USAO");
			window.location.href = "/clinicalCenterAdmin";
		}

		if(user.role == "CLINIC_ADMINISTRATOR") {
			console.log("USAO");
			window.location.href = "/clinicAdmin";
		}
		
		if(user.role == "DOCTOR") {
			console.log("USAO");
			window.location.href = "/doctor";
			
		}

		if(user.role == "PATIENT") {
			window.location.href = "/patient";
		}

		if(user.role == "NURSE") {
			window.location.href = "/nurse";
		}
	
}


