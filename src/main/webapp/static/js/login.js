$(function () {
    $("#submitId").click(function () {
        var user = $("#user").val();
        var pwd = $("#pwd").val();
        var veryfiy = true;
        if (user.length == 0) {
            $("#user_alert").css("visibility", "visible");
            veryfiy = false;
        } else {
            $("#user_alert").css("visibility", "hidden");
        }
        if (pwd.length == 0) {
            $("#pwd_alert").css("visibility", "visible");
            veryfiy = false;
        } else {
            $("#pwd_alert").css("visibility", "hidden");
        }
        if (veryfiy) {
            $.ajax({
                type: "POST",
                url: "/login/check",
                data: {"username": user, "password": pwd},
                success: function (data) {
                    if (data == "login_Success") {
                        console.log("login_success-----------------" + data);
                        window.location.href = "/user/home";
                    } else {
                        console.log("login_error-----------------" + data);
                        $("#login_error").css("visibility", "visible");
                    }
                }
            });
        }
    });
});