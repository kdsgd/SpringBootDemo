
$(function() {
    validateRule();
	$('.imgcode').click(function() {
		var url = ctx + "captcha/captchaImage?type=" + captchaType + "&s=" + Math.random();
		$(".imgcode").attr("src", url);
	});
});

$.validator.setDefaults({
    submitHandler: function() {
        $.modal.loading($("#btnSubmit").data("loading"));
        var username = $.common.trim($("input[name='username']").val());
        var password = $.common.trim($("input[name='password']").val());

        $.ajax({
            type: "post",
            url: "/login",
            dataType:"html",
            data: {
                "username": username,
                "password": password,
            },
            success: function() {
                    location.href =  '/index';
                },
        })
    }
});

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules: {
            username: {
                required: true
            },
            password: {
                required: true
            }
        },
        messages: {
            username: {
                required: icon + "请输入您的用户名",
            },
            password: {
                required: icon + "请输入您的密码",
            }
        }
    })
}
