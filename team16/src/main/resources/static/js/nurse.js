$(document).ready(function () {

    $("#nurse_add_form").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

    });

});

function fire_ajax_submit() {

    var create = {}
    create["email"] = $("#email").val();
    create["password"] = $("#password").val();
    create["firstName"] = $("#firstName").val();
    create["lastName"] = $("#lastName").val();
    create["address"] = $("#address").val();
    create["phoneNumber"] = $("#phone").val();
    create["city"] = $("#city").val();
    create["country"] = $("#state").val();
    create["insuranceNumber"] = $("#insurance").val();


    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/nurseApi",
        data: JSON.stringify(create),
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