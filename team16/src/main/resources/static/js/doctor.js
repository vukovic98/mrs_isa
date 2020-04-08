$(document).ready(function () {

    $("#doctorAddForm").submit(function (event) {
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
    add["country"] = $("#inputState").val();
    add["phoneNumber"] = $("#inputPhoneNo").val();
    add["personalInsuranceNumber"] = $("#inputInsurance").val();
    
    


    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/doctorApi",
        data: JSON.stringify(add),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            console.log("SUCCESS : ", data);

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                + e.responseText + "&lt;/pre&gt;";
            $('#feedback').html(json);

            console.log("ERROR : ", e);

        }
    });

}