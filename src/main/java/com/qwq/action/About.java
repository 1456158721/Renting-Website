package com.qwq.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * 关于页面
 * @Author: QWQ
 * @Date: 2019.11.03 18:14:09
 * @Version: 1.0
 */
@Controller("about")
@ParentPackage(value = "struts-default")
public class About extends ActionSupport {

    @Action(value = "about")
    public void about(){
        ServletActionContext.getResponse().setContentType( "text/html;charset=utf-8" );
        try {
            ServletActionContext.getResponse().getWriter().write( "<div id='about' class='button button-3d button-primary button-rounded'>\n" +
                    "<p>QWQ倾情制作</p>\n" +
                    "<p>项目版本：2.0</p>\n" +
                    "<p>编写日期：2019.12.10</p>\n" +
                    "</div>" );
        } catch( IOException e ) {
            e.printStackTrace();
        }
    }
}
