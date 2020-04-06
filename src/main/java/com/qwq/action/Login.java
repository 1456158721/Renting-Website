package com.qwq.action;

import com.opensymphony.xwork2.ActionSupport;
/*import com.qwq.dao.Account;*/
import com.qwq.entity.User;
import com.qwq.service.AccountOperations;
import org.apache.struts2.ServletActionContext;
/*import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;*/
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import java.io.IOException;
import java.util.Objects;

/**
 * 登录
 * @Author: QWQ
 * @Date: 2019.12.13 14:07
 * @Version: 2.5
 */
@ParentPackage(value = "struts-default")
@Namespace("/")
@Controller("login")
/*  表示该类为多例 */
@Scope("prototype")
public class Login extends ActionSupport {
    public String errorMess;
    public String userId;
    public String userName;
    public String userPwd;
    @Autowired
    private AccountOperations accountOperations;

    public String getErrorMess() {
        return errorMess;
    }

    public void setErrorMess( String errorMess ) {
        this.errorMess = errorMess;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId( String userId ) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName( String userName ) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd( String userPwd ) {
        this.userPwd = userPwd;
    }

    @Action(value = "login",results = {@Result(name = "console",location = "/console.jsp") })
    public String execute() {
        System.out.println("????????????????????");
        if (getUserName() != null){
            return "console";
        }
        boolean flag = false;
        User user = null;
        if ( Objects.equals( userId,"游客" ) ){
            flag = true;
        } else if (getUserId() == null || getUserPwd() == null){
            setErrorMess( "用户名或密码不能为空" );
        } else {
            user = accountOperations.userLogin( getUserId(),getUserPwd() );
            //User user = accountOperations.userLogin( "admin","7c4a8d09ca3762af61e59520943dc26494f8941b" );
        }
        if( user != null || flag ){
            if (ServletActionContext.getRequest().getSession( false ) == null){
                Cookie cookie_id = new Cookie("userId",userId);
                Cookie cookie_pwd = new Cookie("userPwd",userPwd);
                cookie_id.setMaxAge( 60*60 );
                cookie_pwd.setMaxAge( 60*60 );
                ServletActionContext.getResponse().addCookie( cookie_id );
                ServletActionContext.getResponse().addCookie( cookie_pwd );
            }
            ServletActionContext.getRequest().getSession().setAttribute( "userId",userId );
            ServletActionContext.getRequest().getSession().setAttribute( "user",user );
            setErrorMess( "登录成功" );
        } else if (getErrorMess() == null) {
            setErrorMess( "用户名或密码错误，请重新输入" );
        }
        /*ServletActionContext.getRequest().getSession().setAttribute( "errorMess",getErrorMess() );*/
        try {
            ServletActionContext.getResponse().setContentType( "text/html;charset=utf-8" );
            ServletActionContext.getResponse().getWriter().write( getErrorMess() );
        } catch( IOException e ) {
            e.printStackTrace();
        }
        return null;
    }
}
