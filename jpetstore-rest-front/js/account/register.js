$(document).ready(function () {
    $('#newAccount').click(function () {
        const pwd = $('#password').val();
        const rePwd = $('#repeatedPassword').val();
        if (pwd != rePwd) {
            $('#newAccountMessage').text("password and repeatedPassword is not equal");
            return;
        }
        if (!usernameIsExist()) {
            const sendData = JSON.stringify({
                username: $('#username').val(),
                password: $('#password').val(),
            });
            console.log($('#userInfo').serialize());
            $.ajax({
                url: "http://localhost:8080/account/",
                type: "POST",
                contentType: 'application/json',
                dataType: 'json',
                data: sendData,
                success: function (res) {
                    if (res.code == 1) {
                        $('#redirect').attr('href', '../catalog/Main.html');
                        document.getElementById('redirect').click();
                    } else {
                        $('#newAccountMessage').text(res.message);
                    }
                },
                error: function (res) {
                    $('#newAccountMessage').text(res.message);
                }
            })
        }

    })
});

function usernameIsExist() {
    const username = $('#username').val();
    $.ajax({
        url: "http://localhost:8080/account/" + username + "/status",
        type: 'get',
        success: function (res) {
            if (res.code == 1) {
                return false;
            } else {
                $('#newAccountMessage').text(res.message);
                return true
            }
        },
        error: function (res) {
            $('#newAccountMessage').text(res.message);
            return true
        }
    })
}