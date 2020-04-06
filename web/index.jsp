<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>租房系统</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css" />
    <link type="text/css" rel="stylesheet" href="https://www.bootcss.com/p/buttons/css/buttons.css" />
    <link rel="stylesheet" type="text/css" href="css/JQuery-EasyUi/easyui.css">
    <link rel="stylesheet" type="text/css" href="css/JQuery-EasyUi/icon.css">
    <link rel="stylesheet" href="css/style.css">
    <%
        Cookie[] cookies = request.getCookies();
        String userId = "";
        String userPwd = "";
        if (cookies != null){
            for (Cookie i : cookies ){
                if (i.getName().equals( "userId" )){
                    userId = i.getValue();
                } else if (i.getName().equals( "userPwd" )){
                    userPwd = i.getValue();
                }
            }
        }
    %>
</head>
<body>
<input id="userId" class="button-glow" type="hidden" value="${sessionScope.userId}" />
<div id="border" class="button-glow">
    <%--登录--%>
    <form action="<s:url action='login'/>" method="post">
        <table>
            <tr>
                <td>
                    <p id="welcome">欢迎登录</p>
                    <table id="input">
                        <tbody>
                        <tr>
                            <td>昵称<span class="required"></span></td>
                            <td>
                                <label>
                                    <input id="inputUserName" class="input button-rounded easyui-textbox" prompt="请输入用户名"
                                           iconCls="man" iconWidth="28" type="text" name="User.userName" value="<%=(userId!=null)?userId:""%>">
                                </label>
                            </td>
                            <td><a id="registered" class="jump button button-large button-border button-circle" href="javascript:void(0)"
                                   onclick="setInput()">注册</a></td>
                        </tr>
                        <tr>
                            <td>密码<span class="required"></span></td>
                            <td>
                                <label>
                                    <input id="pwd" class="input button-rounded easyui-passwordbox" prompt="请输入密码" checkInterval="300"
                                           iconWidth="28" name="User.userPwd" value="<%=(userId!=null)?userPwd:""%>">
                                </label>
                            </td>
                            <td><a id="retrievePassword" class="jump button button-large button-border button-circle" href="javascript:void(0)">找回</a></td>
                        </tr>
                        </tbody>
                    </table>
                </td>
            </tr>
        </table>
        <button id="login" class="button button-glow button-rounded button-raised button-primary"
                type="submit">登录</button>
        <a id="tourist" class="button button-glow button-border button-rounded button-primary"
           href="login.action?userId=游客">游客</a>
    </form>
</div>
<%-- 加载动画--%>
<div class='container'>
    <div class='loading-overlay'></div>
    <div class='loading-anim'>
        <div class='border out'></div>
        <div class='border in'></div>
        <div class='border mid'></div>
        <div class='circle'>
            <span class='dot'></span>
            <span class='dot'></span>
            <span class='dot'></span>
            <span class='dot'></span>
            <span class='dot'></span>
            <span class='dot'></span>
            <span class='dot'></span>
            <span class='dot'></span>
            <span class='dot'></span>
            <span class='dot'></span>
            <span class='dot'></span>
            <span class='dot'></span>
        </div>
    </div>
</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script type="text/javascript" src="js/JQuery-EasyUi/jquery.min.js"></script>
<script type="text/javascript" src="js/JQuery-EasyUi/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/sha1.js"></script>
<script type="text/javascript" src="js/index.js"></script>
</body>
</html>
