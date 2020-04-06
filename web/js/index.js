$(function () {
    $(".container").hide();
    if($("#userId").val()){
        location.href = "/console.jsp";
    }
    $("#retrievePassword").click(function () {
        alert("目前暂无找回密码功能，请联系管理员找回密码")
    });
    const pwd = $("#pwd");
    pwd.textbox('textbox' ).focus(function () {
        pwd.textbox('setValue','');
    });
});
let border = $("#border");
let tbody;
let loginHtml;
let flag = false;
function setInput() {
    flag = true;
    tbody = $("#input").children();
    border.css("height","400px");
    border.children().attr("action","registered.action");
    loginHtml = border.html();
    $("#welcome").text("欢迎注册");
    $(".required").text("*");
    $(".jump").parent().hide();
    tbody.append("<tr>\n" +
        "<td>电话</td>\n" +
        "<td>\n" +
        "<label>\n" +
        "<input id='userTel' class='addIng input button-rounded' type='tel' name='userTel'>\n" +
        "</label>\n" +
        "</td>\n" +
        "</tr>" +
        "<tr>\n" +
        "<td>姓名</td>\n" +
        "<td>\n" +
        "<label>\n" +
        "<input id='userName' class='addIng input button-rounded' type='text' name='userRealName'>\n" +
        "</label>\n" +
        "</td>\n" +
        "</tr>" +
        "<tr>\n" +
        "<td>验证</td>\n" +
        "<td>\n" +
        "<label>\n" +
        "<input id='verificationCode' class='addIng input button-rounded' type='text' name='verificationCode'>\n" +
        "</label>\n" +
        "<img id='img' style='position: absolute;right: 45px;bottom: 115px;' src='getGenerateVerificationCode.action' alt='验证码加载失败，请刷新重试' title='看不清楚？点击刷新'>\n" +
        "</td>\n" +
        "</tr>");
    $("#login").text("注册");
    const addIng = $(".addIng");
    addIng.eq(0).textbox({prompt:"请输入您的联系电话" });
    addIng.eq(1).textbox({prompt:"请输入您的真实姓名" });
    addIng.eq(2).textbox({prompt:"请输入验证码" });
    const img = $("#img");
    img.click(function () {
        img.attr("src","getGenerateVerificationCode.action?time=" + new Date().getMilliseconds());
    });
    const tourist = $("#tourist");
    tourist.text("返回");
    tourist.attr("href","javascript:void(0)");
    tourist.click(function () {
        flag = false;
        border.html(loginHtml);
        border.css("height","300px");
    });
}
border.children().submit(function () {
    const userId = $("#inputUserName");
    const userPwd = $("#pwd");
    const loginDiv = $("#border");
    const html = $("html");
    const container = $(".container");
    loginDiv.hide();
    container.show();
    html.addClass("loading");
    if (flag) {
        $.ajax({
            "url": "userCenter_register.action",
            "type": "post",
            "data": {userId:userId.textbox( 'getValue' ),userPwd:hex_sha1(userPwd.textbox('getValue')),
                userTel:$("#userTel").textbox( 'getValue' ),userRealName:$("#userName").textbox( 'getValue' ),
                verificationCode:$("#verificationCode").textbox( 'getValue' )},
            "success": function (re) {
                alert(re);
                if (re === "注册成功！确定后将返回登录页面") {
                    location.href = "/index.jsp";
                    flag = false;
                } else if (re === "验证码错误"){
                    $("#img").attr("src","getGenerateVerificationCode.action?time="+new Date().getMilliseconds());
                }
                html.removeClass("loading");
                container.hide();
                loginDiv.show();
            }
        });
        return false;
    } else {
        const userID = userId.textbox( 'getValue' );
        let userPWD = userPwd.textbox('getValue');
        if (userPWD.length !== 40){
            userPWD = hex_sha1(userPWD);
        }
        $.ajax({
            "url": "login.action",
            "type": "post",
            "data": {userId:userID,userPwd:userPWD},
            "success": function (re) {
                if (re === '用户名或密码不能为空' || re === '用户名或密码错误，请重新输入'){
                    alert(re);
                    html.removeClass("loading");
                    container.hide();
                    loginDiv.show();
                } else if (re === "登录成功"){
                    location.href = "/console.jsp";
                }
            }
        });
        return false;
    }
});