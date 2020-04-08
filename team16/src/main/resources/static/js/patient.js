$(document).ready(function () {

    $("#patientAddForm").submit(function (event) {
        event.preventDefault();

        fire_ajax_submit();

    });

});

function fire_ajax_submit() {

    var add = {}
    add["email"] = $("#inputEmail").val();
    add["password"] = $("#inputPassword").val();
    add["firstName"] = $("#inputFirstName").val();
    add["lastName"] = $("#inputLastName").val();
    add["address"] = $("#inputAddress").val();
    add["city"] = $("#inputCity").val();
    add["country"] = $("#inputPhoneNumber").val();
    add["insuranceNumber"] = $("#inputInsuranceNumber").val();
    

    

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/patientApi",
        data: JSON.stringify(add),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            console.log("SUCCESS : ", data);
         
        },
        error: function (e) {

            console.log("ERROR : ", e);
           

        }
    });

}