package com.qwq.service;

import com.alibaba.fastjson.JSONArray;
import com.qwq.dao.AccountDao;
import com.qwq.dao.implement.publicDaoImplements;
import com.qwq.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 业务层——账户操作
 * @Author: QWQ
 * @Date: 2019.12.14 14:14
 * @Version: 1.0
 */
@Service("accountOperations")
public class AccountOperations {
    @Autowired
    private AccountDao accountDao;

    /* <!--  删--> */
    public void delete(User user){
        accountDao.delete( user );
    }

    /* <!--  改 --> */
    /* 用户注册及更新 */
    public void update(User user){
        accountDao.saveOrUpdate( user );
    }

    /* <!--  查 --> */
    /* 获取所有用户 */
    public String getAllUsers(Integer userId,Integer page,Integer rows){
        if (getUser( userId ).getUserIsAdmin().equals( "false" )){
            return "操作非法";
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put( "total",accountDao.getAllNum() );
            map.put( "rows",accountDao.getAll( page,rows ) );
            return JSONArray.toJSONString(map);
        }
    }
    /* 获取指定ID的用户 */
    public User getUser( Integer userId ){
        return accountDao.get( userId );
    }
    /* 登录 */
    public User userLogin(String userName,String userPwd){
        return accountDao.userLogin(userName,userPwd);
    }
}
