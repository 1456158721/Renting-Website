package com.qwq.action;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.qwq.entity.User;
import com.qwq.service.AccountOperations;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import java.io.IOException;

/**
 * 表现层——账户操作相应
 * @Author: QWQ
 * @Date: 2019.11.07 16:57:11
 * @Version: 2.0
 */
@Controller("userCenter")
@Scope("prototype")
@ParentPackage(value = "struts-default")
public class UserCenter extends ActionSupport {
    Integer userId;
    String userName;
    String userPwd;
    Integer page;
    Integer rows;
    Integer thisUserId;
    Integer userTel;
    String userRealName;
    String verificationCode;
    String realVerificationCode;
    String userIsAdmin;
    String newThisUserName;
    String oldThisUserPwd;
    String newThisUserPwd;
    String reNewThisUserPwd;
    Integer newThisUserTel;
    String newThisUserPower;
    String inputThisUserPwd;
    @Autowired
    private AccountOperations accountOperations;

    public String getNewThisUserName() {
        return newThisUserName;
    }

    public String getOldThisUserPwd() {
        return oldThisUserPwd;
    }

    public String getNewThisUserPwd() {
        return newThisUserPwd;
    }

    public String getReNewThisUserPwd() {
        return reNewThisUserPwd;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getRows() {
        return rows;
    }

    public Integer getThisUserId() {
        return thisUserId;
    }

    public Integer getUserTel() {
        return userTel;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public String getRealVerificationCode() {
        return realVerificationCode;
    }

    public String getUserIsAdmin() {
        return userIsAdmin;
    }

    public Integer getNewThisUserTel() {
        return newThisUserTel;
    }

    public String getNewThisUserPower() {
        return newThisUserPower;
    }

    public String getInputThisUserPwd() {
        return inputThisUserPwd;
    }

    public void setUserId( Integer userId ) {
        this.userId = userId;
    }

    public void setUserName( String userName ) {
        this.userName = userName;
    }

    public void setUserPwd( String userPwd ) {
        this.userPwd = userPwd;
    }

    public void setPage( Integer page ) {
        this.page = page;
    }

    public void setRows( Integer rows ) {
        this.rows = rows;
    }

    public void setThisUserId( Integer thisUserId ) {
        this.thisUserId = thisUserId;
    }

    public void setUserTel( Integer userTel ) {
        this.userTel = userTel;
    }

    public void setUserRealName( String userRealName ) {
        this.userRealName = userRealName;
    }

    public void setVerificationCode( String verificationCode ) {
        this.verificationCode = verificationCode;
    }

    public void setRealVerificationCode( String realVerificationCode ) {
        this.realVerificationCode = realVerificationCode;
    }

    public void setUserIsAdmin( String userIsAdmin ) {
        this.userIsAdmin = userIsAdmin;
    }

    public void setNewThisUserName( String newThisUserName ) {
        this.newThisUserName = newThisUserName;
    }

    public void setOldThisUserPwd( String oldThisUserPwd ) {
        this.oldThisUserPwd = oldThisUserPwd;
    }

    public void setNewThisUserPwd( String newThisUserPwd ) {
        this.newThisUserPwd = newThisUserPwd;
    }

    public void setReNewThisUserPwd( String reNewThisUserPwd ) {
        this.reNewThisUserPwd = reNewThisUserPwd;
    }

    public void setNewThisUserTel( Integer newThisUserTel ) {
        this.newThisUserTel = newThisUserTel;
    }

    public void setNewThisUserPower( String newThisUserPower ) {
        this.newThisUserPower = newThisUserPower;
    }

    public void setInputThisUserPwd( String inputThisUserPwd ) {
        this.inputThisUserPwd = inputThisUserPwd;
    }

    /* Ajax 输出 */
    private void write( String str ) {
        ServletActionContext.getResponse().setContentType( "text/html;charset=utf-8" );
        try {
            ServletActionContext.getResponse().getWriter().write( str );
        } catch( IOException e ) {
            e.printStackTrace();
        }
    }
    /* 获取指定用户 */
    private User getUser(Integer userId){
        return accountOperations.getUser( userId );
    }

    /*用户注册*/
    @Action(value = "register")
    public void register() {
        String flag;
        if (getUserId() == null || getUserPwd() == null){
            flag = "用户名或密码不能为空";
        } else if (!getVerificationCode().toUpperCase().equals( getRealVerificationCode() )){
            flag = "验证码错误";
        } else {
            accountOperations.update( new User( getUserName(),getUserPwd(),getUserTel(),getUserRealName() ) );
            flag = "注册成功！确定后将返回登录页面";
        }
        write( flag );
    }

    /*获取当前用户*/
    @Action(value = "getThisUser")
    public void getThisUser(){
        write( JSON.toJSONString( getUser( getThisUserId() ) ) );
    }

    /*获取所有用户*/
    @Action(value = "getAllUsers")
    public void getAllUsers() {
        if (getThisUserId() == null){
            write( "操作非法" );
        } else if( getPage() == null ) {
            getThisUser();
        } else if( getPage() != null) {
            write( accountOperations.getAllUsers( getThisUserId(),getPage(),getRows() ) );
        }
    }

    /*修改用户名*/
    @Action(value = "setThisUserName")
    public void setThisUserName() {
        User user = getUser(getThisUserId());
        if( user == null ) {
            write( "用户获取失败" );
        } else if( getNewThisUserName() == null || getNewThisUserName().length() < 1 ) {
            write( "新用户名不能为空" );
        } else {
            user.setUserName( getNewThisUserName() );
            setSuccess(user);
        }
    }

    /*修改用户密码*/
    @Action(value = "setThisUserPwd")
    public void setThisUserPwd() {
        User user = getUser(getThisUserId());
        if( user == null ) {
            write( "用户获取失败" );
        } else if( !user.getUserPwd().equals( getOldThisUserPwd() ) ) {
            write( "原密码错误，请重新输入" );
        } else if( !getNewThisUserPwd().equals( getReNewThisUserPwd() ) ) {
            write( "两次输入的密码不一致，请重新输入" );
        } else {
            user.setUserPwd( getNewThisUserPwd() );
            setSuccess(user);
        }
    }

    /*修改用户电话*/
    @Action(value = "setThisUserTel")
    public void setThisUserTel(){
        User user = getUser(getThisUserId());
        if( user == null ) {
            write( "用户获取失败" );
        } else if( getNewThisUserTel() == null ) {
            write( "新手机号不能为空" );
        } else {
            user.setUserTel( newThisUserTel );
            setSuccess( user );
        }
    }

    /*修改用户权限*/
    @Action(value = "setThisUserPower")
    public void setThisUserPower(){
        User user = getUser(getThisUserId());
        if( user == null ) {
            write( "用户获取失败" );
        } else if( getNewThisUserPower() == null || getNewThisUserPower().length() < 1 ) {
            write( "组别不能为空" );
        } else if( user.getUserIsAdmin().equals( "false" ) ) {
            write( "用户组别异常" );
        } else {
            user.setUserIsAdmin( newThisUserPower );
            setSuccess(user);
        }
    }

    /*删除用户*/
    @Action(value = "delThisUser")
    public void delThisUser(){
        User user = getUser(getThisUserId());
        if( user == null ) {
            write( "用户获取失败" );
        } else if( ! user.getUserPwd().equals( getInputThisUserPwd() ) ) {
            write( "原密码错误，请重新输入" );
        } else {
            Logout logout = new Logout();
            logout.execute();
            accountOperations.delete( user );
            write( "删除成功！确定后将自动返回主页" );
        }
    }

    /*更新数据库并返回结果*/
    private void setSuccess(User user) {
        accountOperations.update( user );
        write( "修改成功！" );
    }

    /*修改指定用户信息*/
    @Action(value = "setSomeUser")
    public void setSomeUser(){
        User user = getUser(getThisUserId());
        if( user == null ) {
            write( "用户获取失败" );
        } else if( userName == null && userPwd == null ) {
            write( "用户名密码不能为空" );
        } else if( userId == null ) {
            accountOperations.update( new User( getUserName(),getUserPwd(),getUserTel(),getUserRealName(),getUserIsAdmin() ) );
            write( "添加用户成功！" );
        } else {
            if (userPwd == null){
                setSuccess( new User( getUserId(),getUserName(),getUserTel(),getUserRealName(),getUserIsAdmin() ) );
            } else {
                setSuccess( new User( getUserId(),getUserName(),getUserPwd(),getUserTel(),getUserRealName(),getUserIsAdmin() ) );
            }
        }
    }

    /*删除指定用户*/
    @Action(value = "delSomeUser")
    public void delSomeUser(){
        User user = getUser(getThisUserId());
        if( user == null ) {
            write( "用户获取失败" );
        } else {
            accountOperations.delete( user );
            write( "删除成功" );
        }
    }
}
