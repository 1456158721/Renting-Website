<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>控制面板</title>
    <c:if test="${empty sessionScope.user}">
        <script type="text/javascript">location.href = "index.jsp"</script>
    </c:if>
    <link rel="stylesheet" type="text/css" href="https://www.bootcss.com/p/buttons/css/buttons.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/console.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/JQuery-EasyUi/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/JQuery-EasyUi/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/JQuery-EasyUi/color.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/JQuery-EasyUi/demo.css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/JQuery-EasyUi/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/sha1.js"></script>
    <script type="text/javascript" src="js/console.js"></script>
</head>
<body>
<%--侧栏--%>
<div id="left">
    <p id="hello" class="hello"></p>
    <p class="hello" style="display: none">你好</p>
    <button id="0" class="leftButton leftButtonHover button button-3d button-primary button-rounded">主页</button>
    <button id="1" class="leftButton leftButtonHover button button-3d button-action button-rounded">租房</button>
    <c:if test="${sessionScope.userId != '游客'}">
        <button id="2" class="leftButton leftButtonHover button button-3d button-action button-rounded">出租</button>
    </c:if>
    <button id="3" class="leftButton leftButtonHover button button-3d button-royal button-rounded">关于</button>
</div>
<%--顶栏--%>
<div id="right">
    <div id="top">
        <button id="isHide" class="button button-3d button-box button-jumbo">三</button>
        <div id="information">
            <p>用户中心</p>
        </div>
        <div id="topUserMenu">
            <p id="topUser" class="${sessionScope.user.userId}">欢迎您，${sessionScope.user.userName}<img src="img/menu.png" alt=""></p>
            <div id="headSuspensionWindow">
                <p id="logout"><img src="img/logout.png" alt="">注销</p>
            </div>
        </div>
    </div>
    <%--网页主体div--%>
    <div id="main"></div>
</div>
</body>
</html>
