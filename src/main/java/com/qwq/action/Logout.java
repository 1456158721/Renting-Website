package com.qwq.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

/**
 * 注销
 * @Author: QWQ
 * @Date: 2019.12.08 10:52
 * @Version: 1.0
 */
@Controller("logout")
@Scope("prototype")
@ParentPackage(value = "struts-default")
public class Logout extends ActionSupport {
    @Override
    @Action(value = "logout")
    public String execute() {
        HttpSession session = ServletActionContext.getRequest().getSession( false );
        if( session != null ) {
            session.removeAttribute( "userName" );
            session.invalidate();
        }
        return "success";
    }
}
