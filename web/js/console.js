$(function () {
    /*侧栏开始*/
    const left = $("#left");
    //距离顶部的间距
    const marginTop = 0;
    left.css('top', marginTop);
    $(window).scroll(function () {
        const scrollTop = $('body').scrollTop();
        if (left.css('position') === 'fixed') {
            left.css('position', 'absolute').css('top', scrollTop + marginTop);
        } else if (left.css('position') === 'absolute') {
            left.css('position', 'fixed').css('top', marginTop);
        }
    });
    /*更新时间*/
    function getNow(s) {
        return s < 10 ? '0' + s : s;
    }
    function getTime() {
        const getTime = new Date();
        const h = getTime.getHours();
        const m = getTime.getMinutes();
        return getNow(h) + ":" + getNow(m);
    }
    function updateTime() {
        const thisTime = getTime().split(":")[0];
        console.info("现在是" + thisTime + "点哦~");
        if (thisTime >= 0 && thisTime < 6) {
            return "都凌晨了，还不睡觉吗\t_(:з」∠)_";
        } else if (thisTime >= 6 && thisTime < 11) {
            return "早上好鸭~又是新的一天了呢\t(〃'▽'〃)";
        } else if (thisTime >= 11 && thisTime < 14) {
            return "中午了，吃饭了吗\t(≧ڡ≦*)";
        } else if (thisTime >= 14 && thisTime < 19) {
            return "下午也要加油喔\t┗(•ω•;)┛";
        } else if (thisTime >= 19 && thisTime < 0) {
            return "晚上好鸭\t(*´ﾟ∀ﾟ｀)ﾉ ";
        }
    }
    /*问候语标签*/
    const timeShow = $("#hello");
    timeShow.text(updateTime());
    setInterval(function () {
        timeShow.text(updateTime());
    }, 1800000);
    /*设置长度*/
    const leftButton = $(".leftButton");
    const rightTopText = $("#information").children();
    const rightTopTextStr = ["用户中心", "租房服务", "出租房屋", "关于页面"];
    let leftButtonNum = 0;
    let setLong = true;
    $(".leftButton:eq(0)").animate({"padding-left": 120 + "px"}, 408);
    leftButton.click(function () {
        if (setLong) {
            rightTopText.text(rightTopTextStr[$(this).attr("id")]);
            leftButton.animate({"padding-left": 40 + "px"}, 60);
            $(this).animate({"padding-left": 120 + "px"}, 408);
            leftButtonNum = $(this).attr("id");
            setMainAjax(leftButtonNum);
        }
    });
    /*隐藏按钮*/
    const hideButton = $("#isHide");
    let flag = 0;
    hideButton.click(function () {
        const userListDiv = $("#userList").children();
        if (flag === 0) {
            flag = 3;
            setLong = false;
            timeShow.hide();
            timeShow.next().show();
            leftButton.css({"padding-left": 10 + "px", "padding-right": 10 + "px"});
            leftButton.removeClass("leftButtonHover");
            left.animate({"width": 5 + "%"}, 900, "swing", function () {
                flag = 1;
            });
            $("#right").animate({"width": 95 + "%"}, 900);
            if (userListDiv) {
                //userListDiv.resize("width", 1400);
            }
        } else if (flag === 1) {
            flag = 3;
            setLong = true;
            left.animate({"width": 20 + "%"}, 900, "swing", function () {
                timeShow.next().hide();
                timeShow.show();
                leftButton.css({"padding-left": 40 + "px", "padding-right": 40 + "px"});
                leftButton.addClass("leftButtonHover");
                $(".leftButton:eq(" + leftButtonNum + ")").animate({"padding-left": 120 + "px"}, 408);
                flag = 0;
            });
            $("#right").animate({"width": 80 + "%"}, 900);
            if (userListDiv) {
                //userListDiv.resize("width", 1200);
            }
        }
    });
    /*顶栏用户*/
    let i = 0;
    const topUser = $("#topUser");
    const topUserDiv = $("#topUserMenu");
    const headSuspensionWindow = $("#headSuspensionWindow");
    let timer1;
    let timer2;
    topUser.mouseover(function () {
        headSuspensionWindow.slideDown(900);
        clearInterval(timer1);
        clearInterval(timer2);
        timer1 = setInterval(() => {
            i++;
            topUser.children().css("transform", "rotate(" + i + "deg)");
            if (i >= 180) {
                clearInterval(timer1);
                clearInterval(timer2);
            }
        }, 1);
    });
    topUserDiv.mouseleave(function () {
        headSuspensionWindow.slideUp(900);
        clearInterval(timer1);
        clearInterval(timer2);
        timer2 = setInterval(() => {
            i--;
            topUser.children().css("transform", "rotate(" + i + "deg)");
            if (i <= 0) {
                clearInterval(timer1);
                clearInterval(timer2);
            }
        }, 1);
    });
    /*注销按钮*/
    $("#logout").click(function () {
        location.replace("logout.action");
    });
    /*<!--主体显示-->*/
    const main = $("#main");
    one();

    function setMainAjax(id) {
        $("#userList").remove();
        switch (id) {
            case "0":
                one();
                break;
            case "1":
                two();
                break;
            case  "2":
                three();
                break;
            case "3":
                four();
                break;
        }
    }

    /*主页*/
    let thisUserId = null;

    function one() {
        main.html("<div>昵称<p id='name'></p>\n" +
            "<button class='userSet button button-pill button-raised button-primary'>更改昵称</button>\n" +
            "</div>\n" +
            "<div>密码<p id='pwd'></p>\n" +
            "<button class='userSet button button-pill button-raised button-primary'>更改密码</button>\n" +
            "</div>\n" +
            "<div>手机<p id='tel'></p>\n" +
            "<button class='userSet button button-pill button-raised button-primary'>更改号码</button>\n" +
            "</div>\n" +
            "<div>组别<p id='isAdmin'></p>\n" +
            "<button id='updateButton' class='userSet button button-pill'>更改组别</button>\n" +
            "</div>\n" +
            "<div id='other'>其它\n" +
            "<button class='userSet button button-pill button-raised button-primary'>注销账号</button>\n" +
            "<button class='userSet button button-pill button-raised button-primary'>删除账号</button>\n" +
            "</div><div id='thisUserEdit' style='display: none'><form id='editUser' class='selectClass' method='post' style='display: none'>" +
            "<div><h3 style='text-align: center;font-size: 25px;'>更改信息</h3></div>" +
            "<div><input class='thisUserId' type='hidden' name='thisUserId'/></div></form></div>");
        updateThisUser();
    }

    let userSet = $(".userSet");

    let isAdmin = $("#isAdmin");

    function updateThisUser() {
        $.ajax({
            "url": "userCenter_getThisUser.action",
            "type": "get",
            data: {"userId": topUser.attr("class")},
            "success": function (re) {
                let getRe = $.parseJSON(re);
                const nameIsP = $("#name");
                let flag;
                nameIsP.text(getRe.userName);
                $("#pwd").text("········");
                $("#tel").text(getRe.userTel);
                isAdmin = $("#isAdmin");
                if (getRe.userIsAdmin === "true") {
                    isAdmin.text("管理组");
                    $("#updateButton").attr("class", "button button-pill button-raised button-primary");
                    flag = true;
                } else {
                    isAdmin.text("用户组");
                    $("#updateButton").attr("class", "userSet button button-pill");
                    flag = false;
                }
                userSet.attr("userId", getRe.userId);
                if (flag) {
                    thisUserId = getRe.userId;
                    addUserList();
                }
            }
        });
    }

    const editUser = $("#editUser");
    const fromHtml = editUser.html();
    userSet.eq(0).click(function () {
        editUser.html(fromHtml);
        editUser.append("<div><label>原昵称</label>\n" +
            "<input id='oldThisUserName' type='text' name='oldThisUserName'/></div>" +
            "<div><label for='name'>新昵称</label>\n" +
            "<input id='newThisUserName' type='text' name='newThisUserName'/></div>");
        addUserId();
        $("#oldThisUserName").textbox({
            readonly: true,
            editable: false,
            disabled: true,
            value: $("#name").text()
        });
        $("#newThisUserName").textbox({
            prompt: '请在此输入新用户名',
            iconCls: 'icon-man',
            iconAlign: 'right',
            iconWidth: '32',
            required: true
        });
        newDialog('更改昵称', 'userCenter_setThisUserName.action')

    });
    userSet.eq(1).click(function () {
        editUser.html(fromHtml);
        editUser.append("<div><label>原密码</label>\n" +
            "<input id='oldThisUserPwd' type='text' name='oldThisUserPwd'/></div>" +
            "<div><label for='name'>新密码</label>\n" +
            "<input id='newThisUserPwd' type='text' name='newThisUserPwd'/></div>" +
            "<div><label for='name'>重新输</label>\n" +
            "<input id='reNewThisUserPwd' type='text' name='reNewThisUserPwd'/></div>");
        addUserId();
        $("#oldThisUserPwd").textbox({
            prompt: '请输入原密码',
            showEye: false,
            required: true,
            checkInterval: 0
        });
        $("#newThisUserPwd").textbox({
            prompt: '请在此输入新密码',
            showEye: false,
            required: true,
            checkInterval: 0
        });
        $("#reNewThisUserPwd").textbox({
            prompt: '请重新输入新密码',
            showEye: false,
            required: true,
            checkInterval: 0
        });
        newDialog('更改密码', 'userCenter_setThisUserPwd.action')
    });
    userSet.eq(2).click(function () {
        editUser.html(fromHtml);
        editUser.append("<div><label>原手机号</label>\n" +
            "<input id='oldThisUserTel' type='text' name='oldThisUserTel'/></div>" +
            "<div><label for='name'>新手机号</label>\n" +
            "<input id='newThisUserTel' type='text' name='newThisUserTel'/></div>");
        addUserId();
        $("#oldThisUserTel").textbox({
            readonly: true,
            editable: false,
            disabled: true,
            value: $("#tel").text()
        });
        $("#newThisUserTel").textbox({
            prompt: '请在此输入新手机号',
            iconCls: 'icon-tel',
            iconAlign: 'right',
            iconWidth: '23'
        });
        newDialog('更改号码', 'userCenter_setThisUserTel.action')
    });
    userSet.eq(3).click(function () {
        if (isAdmin.text() === '管理组') {
            $.messager.confirm('系统提示', '您已是管理组，确定要降回用户组吗?', function (r) {
                isAdmin = $("#isAdmin");
                if (isAdmin.text() === "管理组" && r) {
                    editUser.form('submit', {
                        url: 'userCenter_setThisUserPower.action',
                        onSubmit: function (param) {
                            userSet = $(".userSet");
                            addUserId();
                            param.operating = 'setUserPower';
                            param.newThisUserPower = false;
                        },
                        success: function (result) {
                            $.messager.alert({
                                title: '系统提示',
                                msg: '操作成功',
                                fn: function () {
                                    if (result === "修改成功！") {
                                        updateThisUser();
                                        $("#userList").remove();
                                    }
                                }
                            });
                        }
                    });
                }
            });
        }
    });
    userSet.eq(4).click(function () {
        $.messager.confirm('系统提示', '你真的要注销吗?', function (r) {
            if (r) {
                location.replace("logout.action");
            }
        });
    });
    userSet.eq(5).click(function () {
        $.messager.confirm('系统提示', '你真的要删除该账号吗?', function (r) {
            if (r) {
                editUser.html(fromHtml);
                editUser.append("<div><label>为确保安全，请输入一次该账户密码：</label><br>\n" +
                    "<input id='inputThisUserPwd' type='text' name='inputThisUserPwd'/></div>");
                addUserId();
                $("#inputThisUserPwd").textbox({
                    prompt: '请输入密码',
                    showEye: false,
                    required: true,
                    checkInterval: 0
                });
                newDialog('删除账号', 'userCenter_delThisUser.action')
            }
        });
    });

    function addUserId() {
        const thisUserId = $(".thisUserId");
        thisUserId.textbox({
            readonly: true,
            editable: false
        });
        thisUserId.textbox("setValue", userSet.eq(0).attr("userId"));
    }

    function newDialog(title, url) {
        editUser.show();
        const show = $("#thisUserEdit");
        show.dialog({
            title: title,
            width: 400,
            height: 400,
            cache: false,
            modal: true,
            closed: true,
            buttons: [{
                text: '提交',
                handler: function () {
                    saveEditUser(url);
                }
            }, {
                text: '取消',
                handler: closeThisUserEdit
            }]
        });
        show.dialog('open').dialog('center');
        /*$(".panel-htop").css("top", 20 + "%" );
        $(".window-shadow").css("top", 20 + "%" );*/
    }

    function saveEditUser(url) {
        const text = editUser.children(":eq(2)").children().text();
        if (text === "为确保安全，请输入一次该账户密码：") {
            $.messager.confirm('系统提示', '你真的确认要删除该账号吗?', function (r) {
                if (!r) {
                    return false;
                } else {
                    const inputThisUserPwd = $("#inputThisUserPwd");
                    inputThisUserPwd.textbox("setValue", hex_sha1(inputThisUserPwd.val()));
                    submitUserList(url);
                }
            });
        } else if (text === "原密码") {
            const oldThisUserPwd = $("#oldThisUserPwd");
            oldThisUserPwd.textbox("setValue", hex_sha1(oldThisUserPwd.val()));
            const newThisUserPwd = $("#newThisUserPwd");
            newThisUserPwd.textbox("setValue", hex_sha1(newThisUserPwd.val()));
            const reNewThisUserPwd = $("#reNewThisUserPwd");
            reNewThisUserPwd.textbox("setValue", hex_sha1(reNewThisUserPwd.val()));
            submitUserList(url);
        } else {
            submitUserList(url);
        }
    }

    function submitUserList(url) {
        editUser.form('submit', {
            url: url,
            onSubmit: function () {

            },
            success: function (result) {
                $.messager.alert({
                    title: '系统提示',
                    msg: result,
                    fn: function () {
                        if (result === "修改成功！") {
                            $("#thisUserEdit").dialog('close');
                            updateThisUser();
                        } else if (result === "删除成功！确定后将自动返回主页") {
                            location.replace("/index.jsp")
                        } else if (result === "原密码错误，请重新输入" || result === "两次输入的密码不一致，请重新输入") {
                            editUser.form('clear');
                            addUserId();
                        }
                    }
                });
            }
        });
    }

    function closeThisUserEdit() {
        $("#thisUserEdit").dialog('close');
    }

    function addUserList() {
        main.after("<div id='userList'><table id='dg'></table><div id='dd'>" +
            "<form id='select' class='selectClass' method='post'><div><h3 style='text-align: center;font-size: 25px;'>用户信息</h3>\n" +
            "<div><input id='thisUserId' type='hidden' name='userId'/></div>" +
            "<div><label for='name'>昵称</label>\n" +
            "<input id='newUserName' type='text' name='userName'/></div>" +
            "<div><label>密码</label>\n" +
            "<input id='newUserPwd' type='text' name='userPwd'/></div>" +
            "<div><label>手机</label>\n" +
            "<input id='newUserTel' type='text' name='userTel'/></div>" +
            "<div><label>姓名</label>\n" +
            "<input id='newUserRealName' type='text' name='userRealName'/></div>" +
            "<div><label>组别</label>\n" +
            "<input id='newUserPower' type='text' name='userIsAdmin'/></div></div></form></div>");
        $("#thisUserId").textbox({
            readonly: true,
            editable: false
        });
        $("#newUserName").textbox({
            prompt: '请输入用户名',
            iconCls: 'icon-man',
            iconAlign: 'right',
            iconWidth: '23'
        });
        $("#newUserPwd").passwordbox({
            prompt: '········',
            showEye: true
        });
        $("#newUserTel").textbox({
            prompt: '请输入联系电话',
            iconCls: 'icon-tel',
            iconAlign: 'right',
            iconWidth: '23',
        });
        $("#newUserRealName").textbox({
            prompt: '请输入真实姓名',
            iconCls: 'icon-name',
            iconAlign: 'right',
            iconWidth: '23',
        });
        const newUserPower = $("#newUserPower");
        newUserPower.combobox({
            valueField: 'value',
            textField: 'lable',
            panelHeight: "auto",
            editable: false,
            data: [{
                lable: '用户组',
                value: 'false',
                selected: true
            }, {
                lable: '管理组',
                value: 'true'
            }]
        });
        const dg = $('#dg');
        dg.datagrid({
            //fit: true,
            title: '用户列表',
            width: 1200,
            toolbar: [{
                iconCls: 'icon-add',
                text: '添加',
                handler: newUser,
            }, '-', {
                iconCls: 'icon-edit',
                text: '编辑',
                handler: editUser,
            }, '-', {
                iconCls: 'icon-remove',
                text: '删除',
                handler: destroyUser,
            }],
            url: 'userCenter_getAllUsers.action?thisUserId=' + thisUserId,
            columns: [[
                {
                    field: 'userId', title: 'ID', width: 100, sortable: true, sorter: function (a, b) {
                        return (a > b ? 1 : -1)
                    }
                },
                {
                    field: 'userName', title: '昵称', width: 100, sortable: true, sorter: function (a, b) {
                        return (a > b ? 1 : -1)
                    }
                },
                {
                    field: 'userTel', title: '电话', width: 100, sortable: true, sorter: function (a, b) {
                        return (a > b ? 1 : -1)
                    }
                },
                {
                    field: 'userRealName', title: '姓名', width: 100, sortable: true, sorter: function (a, b) {
                        return (a > b ? 1 : -1)
                    }
                },
                {
                    field: 'isAdmin', title: '组别', width: 100,
                    formatter: function (value, row) {
                        if (row.userIsAdmin === "true") {
                            return "管理组";
                        } else {
                            return "用户组";
                        }
                    },
                    sortable: true,
                    sorter: function (a, b) {
                        return (a > b ? 1 : -1)
                    }
                }
            ]],
            remoteSort: false,
            sortName: 'userId',
            sortOrder: 'asc',
            pagePosition: 'bottom',
            pagination: true,
            fitColumns: true,
            singleSelect: true,
            //每页显示的记录条数
            pageSize: 10,
            //可以设置每页记录条数的列表
            pageList: [10, 25, 50, 75, 100],
            beforePageText: '第',
            afterPageText: '页    共 {pages} 页',
            displayMsg: '第{from}到{to}条，共{total}条',
        });
        const dd = $('#dd');
        dd.dialog({
            title: '添加用户',
            width: 400,
            height: 425,
            cache: false,
            modal: true,
            closed: true,
            buttons: [{
                text: '保存',
                handler: saveUser
            }, {
                text: '取消',
                handler: closeDD
            }]
        });

        function closeDD() {
            dd.dialog('close');
        }

        dg.datagrid('reload');

        function newUser() {
            dd.dialog('open').dialog('center').dialog('setTitle', '添加用户');
            $('#select').form('clear');
            newUserPower.combobox('setValue', 'false');
        }

        function editUser() {
            let row = dg.datagrid('getSelected');
            if (row) {
                dd.dialog('open').dialog('center').dialog('setTitle', '编辑用户');
                $('#select').form('load', row);
                const value = newUserPower.combobox('getValue');
                if (value !== 'true' && value !== 'false') {
                    newUserPower.combobox('setValue', 'false');
                }
            } else {
                alertMess('请选择需要修改的行');
            }
        }

        function saveUser() {
            const select = $('#select');
            select.form({
                url: 'userCenter_setSomeUser.action',
                onSubmit: function () {
                    const newUserPwd = $("#newUserPwd");
                    newUserPwd.passwordbox("setValue", hex_sha1(newUserPwd.val()));
                    closeDD();
                    return true;
                },
                success: function (result) {
                    alertMess(result);
                }
            });
            select.submit();
        }

        function destroyUser() {
            let row = dg.datagrid('getSelected');
            if (row) {
                $.messager.confirm('系统提示', '你真的要删除吗?', function (r) {
                    if (r) {
                        $.ajax({
                            "url": "userCenter_delSomeUser.action",
                            "type": "post",
                            "data": {"userId": row.userId},
                            "success": function (re) {
                                console.info(re);
                                alertMess(re);
                            }
                        });
                    }
                });
            }
        }

        function alertMess(str) {
            $.messager.alert({
                title: '系统提示',
                msg: str,
                fn: function () {
                    dg.datagrid('reload');
                }
            });
        }
    }

    /*租房*/
    function two() {
        $.ajax({
            "url": "rentingHouse.action",
            "type": "get",
            "success": function (re) {
                let getRe = $.parseJSON(re);
                main.html();
            }
        });
    }

    /*出租*/
    function three() {
        $.ajax({
            "url": "rent.action",
            "type": "get",
            "success": function (re) {
                let getRe = $.parseJSON(re);
                main.html();
            }
        });
    }

    /*关于*/
    function four() {
        $.ajax({
            "url": "about.action",
            "type": "get",
            "success": function (re) {
                main.html(re);
            }
        });
    }
});