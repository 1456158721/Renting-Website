package com.qwq.action;


import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


@Controller
@ParentPackage(value = "struts-default")
@Namespace("/")
@Scope("prototype")
public class HelloAction  extends ActionSupport {

       @Action(value = "hello")
        public String  hello(){
           System.out.print("\n=================================");
              return "hello";
        }

}
